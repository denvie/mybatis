/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 自定义插件实现为生成Mapper、Service、ServiceImpl、Controller及对应注释。
 *
 * @author Denvie
 * @since 2020/8/16
 */
public class MyGeneratorPlugin extends PluginAdapter {
    private boolean addRemarkComments = false;
    private DateFormat dateFormat = null;
    private String author;
    private String copyright;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(
                properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        this.dateFormat = new SimpleDateFormat(properties.getProperty(
                PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss"));
        this.author = properties.getProperty("author", System.getProperty("user.name"));
        this.copyright = properties.getProperty("copyright",
                "Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.");
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 生成Mapper接口。
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加父接口
        interfaze.addSuperInterface(
                new FullyQualifiedJavaType("BaseMapper<"
                        + introspectedTable.getTableConfiguration().getDomainObjectName() + ">"));

        // 导包
        interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        interfaze.addImportedType(
                new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        interfaze.addImportedType(
                new FullyQualifiedJavaType("tk.mybatis.mapper.common.BaseMapper"));

        // 添加类注解
        interfaze.addAnnotation("@Mapper");

        // 添加类注释
        if (addRemarkComments) {
            GeneratorUtils.addClassComment(interfaze, getRemarks(introspectedTable) + "Mapper",
                    author, dateFormat.format(new Date()));
        }

        return true;
    }

    private String getRemarks(IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        if (!StringUtility.stringHasValue(remarks)) {
            remarks = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        }
        return remarks;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> result = new ArrayList<>();
        String targetPackage = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
        targetPackage = targetPackage.substring(0, targetPackage.lastIndexOf("."));
        // 生成Service接口
        GeneratedJavaFile serviceFile = generateService(introspectedTable, targetPackage);
        result.add(serviceFile);
        // 生成ServiceImpl
        result.add(generateServiceImpl(introspectedTable, targetPackage, serviceFile.getCompilationUnit()));
        // 生成Controller
        result.add(generateController(introspectedTable, targetPackage, serviceFile.getCompilationUnit()));
        return result;
    }

    private GeneratedJavaFile generateService(IntrospectedTable introspectedTable, String targetPackage) {
        Interface serviceInterface = new Interface(
                targetPackage + ".service." + getDomainName(introspectedTable) + "Service");
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        // 添加Java文件头信息
        GeneratorUtils.addFileComment(serviceInterface, copyright);
        // 添加类注释
        if (addRemarkComments) {
            GeneratorUtils.addClassComment(serviceInterface, getRemarks(introspectedTable) + "Service",
                    author, dateFormat.format(new Date()));
        }
        return new GeneratedJavaFile(serviceInterface,
                introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetProject(),
                introspectedTable.getContext().getJavaFormatter());
    }

    private GeneratedJavaFile generateServiceImpl(IntrospectedTable introspectedTable, String targetPackage,
                                                  CompilationUnit serviceInterface) {
        TopLevelClass serviceImplClass = new TopLevelClass(
                targetPackage + ".service.impl." + getDomainName(introspectedTable) + "ServiceImpl");
        serviceImplClass.setVisibility(JavaVisibility.PUBLIC);
        // 添加Java文件头信息
        GeneratorUtils.addFileComment(serviceImplClass, copyright);
        // 添加父接口
        serviceImplClass.addSuperInterface(new FullyQualifiedJavaType(serviceInterface.getType().getShortName()));
        // 导包
        serviceImplClass.addImportedType(serviceInterface.getType());
        serviceImplClass.addImportedType(
                new FullyQualifiedJavaType("lombok.RequiredArgsConstructor"));
        serviceImplClass.addImportedType(
                new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        serviceImplClass.addImportedType(
                new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        // 添加类注释
        serviceImplClass.addAnnotation("@Service");
        serviceImplClass.addAnnotation("@RequiredArgsConstructor(onConstructor_ = {@Autowired})");
        // 添加类注释
        if (addRemarkComments) {
            GeneratorUtils.addClassComment(serviceImplClass, getRemarks(introspectedTable) + "Service实现类",
                    author, dateFormat.format(new Date()));
        }
        return new GeneratedJavaFile(serviceImplClass,
                introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetProject(),
                introspectedTable.getContext().getJavaFormatter());
    }

    private GeneratedJavaFile generateController(IntrospectedTable introspectedTable, String targetPackage,
                                                 CompilationUnit serviceInterface) {
        TopLevelClass controllerClass = new TopLevelClass(
                targetPackage + ".controller." + getDomainName(introspectedTable) + "Controller");
        controllerClass.setVisibility(JavaVisibility.PUBLIC);
        // 添加Java文件头信息
        GeneratorUtils.addFileComment(controllerClass, copyright);
        // 导包
        controllerClass.addImportedType(serviceInterface.getType());
        controllerClass.addImportedType(
                new FullyQualifiedJavaType("lombok.RequiredArgsConstructor"));
        controllerClass.addImportedType(
                new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        controllerClass.addImportedType(
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
        controllerClass.addImportedType(
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        // 添加类注解
        controllerClass.addAnnotation("@RestController");
        controllerClass.addAnnotation("@RequestMapping(\"/"
                + GeneratorUtils.changeHumpTo(getDomainName(introspectedTable), "-") + "\")");
        controllerClass.addAnnotation("@RequiredArgsConstructor(onConstructor_ = {@Autowired})");
        // 添加类注释
        if (addRemarkComments) {
            GeneratorUtils.addClassComment(controllerClass, getRemarks(introspectedTable) + "Controller",
                    author, dateFormat.format(new Date()));
        }
        // 添加属性
        String serviceName = serviceInterface.getType().getShortName();
        Field serviceField = new Field(GeneratorUtils.lowerFirstChar(serviceName),
                new FullyQualifiedJavaType(serviceName));
        serviceField.setVisibility(JavaVisibility.PRIVATE);
        serviceField.setFinal(true);
        controllerClass.addField(serviceField);
        return new GeneratedJavaFile(controllerClass,
                introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetProject(),
                introspectedTable.getContext().getJavaFormatter());
    }

    private String getDomainName(IntrospectedTable introspectedTable) {
        String domainName = introspectedTable.getTableConfiguration().getDomainObjectName();
        if (domainName.endsWith("Entity")) {
            domainName = domainName.replace("Entity", "");
        } else if (domainName.endsWith("Bean")) {
            domainName = domainName.replace("Bean", "");
        } else if (domainName.endsWith("Model")) {
            domainName = domainName.replace("Model", "");
        }
        return domainName;
    }
}
