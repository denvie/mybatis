/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator;

import cn.denvie.mybatis.plus.generator.config.JdbcConfig;
import cn.denvie.mybatis.plus.generator.config.ModuleConfig;
import cn.denvie.mybatis.plus.generator.config.MpGeneratorConfig;
import cn.denvie.mybatis.plus.generator.utils.EnumUtils;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.base.Splitter;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis Plus Generator Main。
 *
 * @author denvie
 * @since 2020/9/6
 */
public class MpGeneratorMain {
    private static final String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 加载自定义配置
        MpGeneratorConfig mpConfig = loadMpGeneratorConfig();
        // 数据源配置
        setDataSource(generator, mpConfig.getJdbc());
        // 配置模板
        setTemplate(generator);

        // 分模块生成代码
        for (ModuleConfig moduleConfig : mpConfig.getModules()) {
            // 全局配置
            setGlobalConfig(generator, mpConfig, moduleConfig);
            // 包配置
            setPackageInfo(generator, mpConfig, moduleConfig);
            // 自定义配置
            setCustomConfig(generator, mpConfig, moduleConfig);
            // 策略配置
            setStrategyConfig(generator, mpConfig, moduleConfig);
            // 执行
            generator.execute();
        }
    }

    private static void setDataSource(AutoGenerator generator, JdbcConfig jdbc) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.getDbType(jdbc.getDbType()));
        dsc.setUrl(jdbc.getUrl());
        dsc.setDriverName(jdbc.getDriverName());
        dsc.setUsername(jdbc.getUsername());
        dsc.setPassword(jdbc.getPassword());
        generator.setDataSource(dsc);
    }

    private static MpGeneratorConfig loadMpGeneratorConfig() {
        return new Yaml().loadAs(
                MpGeneratorMain.class.getClassLoader().getResourceAsStream("generatorConfig.yml"),
                MpGeneratorConfig.class);
    }

    private static void setGlobalConfig(AutoGenerator generator, MpGeneratorConfig mpConfig,
                                                ModuleConfig moduleConfig) {
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(mpConfig.getBasic().getAuthor());
        gc.setFileOverride(mpConfig.getBasic().isFileOverride());
        gc.setOpen(mpConfig.getBasic().isOpen());
        gc.setEnableCache(mpConfig.getBasic().isEnableCache());
        gc.setBaseResultMap(mpConfig.getBasic().isBaseResultMap());
        gc.setBaseColumnList(mpConfig.getBasic().isBaseColumnList());
        gc.setSwagger2(mpConfig.getBasic().isSwagger2());
        gc.setIdType(EnumUtils.getIdType(mpConfig.getBasic().getIdType()));
        gc.setOutputDir(projectPath + "/" + moduleConfig.getModulePath() + "/src/main/java");
        gc.setMapperName("%s" + mpConfig.getBasic().getMapperSuffix());
        gc.setServiceName("%sService"); // 去除Service的'I'前缀
        generator.setGlobalConfig(gc);
    }

    private static void setPackageInfo(AutoGenerator generator, MpGeneratorConfig mpConfig,
                                                ModuleConfig moduleConfig) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleConfig.getModuleName());
        pc.setParent(mpConfig.getBasic().getParentPackage());
        pc.setEntity(mpConfig.getBasic().getEntityPackage());
        pc.setMapper(mpConfig.getBasic().getMapperPackage());
        pc.setService(mpConfig.getBasic().getServicePackage());
        pc.setServiceImpl(mpConfig.getBasic().getServiceImplPackage());
        pc.setController(mpConfig.getBasic().getControllerPackage());
        generator.setPackageInfo(pc);
    }

    private static void setTemplate(AutoGenerator generator) {
        TemplateConfig templateConfig = new TemplateConfig();
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.setTemplate(templateConfig);
    }

    private static void setCustomConfig(AutoGenerator generator, MpGeneratorConfig mpConfig,
                                                   ModuleConfig moduleConfig) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // 自定义属性注入: copyright，在.ftl(或者是.vm)模板中，通过${cfg.copyright}获取属性
                Map<String, Object> map = new HashMap<>();
                map.put("copyright", mpConfig.getBasic().getCopyright());
                this.setMap(map);
            }
        };
        // 自定义mapper输出配置
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名，如果Entity设置了前后缀，此处注意xml的名称会跟着发生变化！！
                return projectPath + "/" + moduleConfig.getModulePath() + "/src/main/resources/mapper/"
                        + moduleConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
    }

    private static void setStrategyConfig(AutoGenerator generator, MpGeneratorConfig mpConfig,
                                                    ModuleConfig moduleConfig) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(mpConfig.getTable().isEntityLombokModel());
        strategy.setVersionFieldName(mpConfig.getTable().getVersionFieldName());
        strategy.setLogicDeleteFieldName(mpConfig.getTable().getLogicDeleteFieldName());
        strategy.setEntityTableFieldAnnotationEnable(mpConfig.getTable().isEntityTableFieldAnnotationEnable());
        strategy.setRestControllerStyle(mpConfig.getTable().isRestControllerStyle());
        strategy.setControllerMappingHyphenStyle(mpConfig.getTable().isControllerMappingHyphenStyle());
        strategy.setEntitySerialVersionUID(mpConfig.getTable().isEntitySerialVersionUID());
        strategy.setSuperEntityClass(mpConfig.getTable().getSuperEntityClass());
        if (!StringUtils.isBlank(mpConfig.getTable().getSuperEntityColumns())) {
            List<String> superEntityColumns = Splitter.on(",").trimResults().omitEmptyStrings()
                    .splitToList(mpConfig.getTable().getSuperEntityColumns());
            strategy.setSuperEntityColumns(superEntityColumns.toArray(new String[0]));
        }
        strategy.setTableFillList(mpConfig.getTable().getTableFillList());

        if (!StringUtils.isBlank(moduleConfig.getInclude())) {
            List<String> includes = Splitter.on(",").trimResults().omitEmptyStrings()
                    .splitToList(moduleConfig.getInclude());
            strategy.setInclude(includes.toArray(new String[0]));
        }
        if (!StringUtils.isBlank(moduleConfig.getTablePrefix())) {
            List<String> prefixes = Splitter.on(",").trimResults().omitEmptyStrings()
                    .splitToList(moduleConfig.getTablePrefix());
            strategy.setTablePrefix(prefixes.toArray(new String[0]));
        }
        generator.setStrategy(strategy);
    }
}
