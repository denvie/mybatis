/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import cn.denvie.mybatis.plus.generator.utils.EnumUtils;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 表策略配置。
 *
 * @author Denvie
 * @since 2020/9/6
 */
@Data
public class TableConfig {
    /**
     * 是否为lombok模型
     */
    private boolean entityLombokModel = true;
    /**
     * 乐观锁属性名称
     */
    private String versionFieldName;
    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteFieldName;
    /**
     * 生成实体时是否生成字段注解
     */
    private boolean entityTableFieldAnnotationEnable = true;
    /**
     * 生成 @RestController 控制器
     */
    private boolean restControllerStyle = true;
    /**
     * 生成 @RequestMapping 驼峰转连字符
     */
    private boolean controllerMappingHyphenStyle = true;
    /**
     * 实体是否生成 serialVersionUID
     */
    private boolean entitySerialVersionUID = false;
    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass;
    /**
     * 自定义基础的Entity类公共字段
     */
    private String superEntityColumns;
    /**
     * 表填充字段
     */
    private List<TableFillVo> tableFillVoList = null;

    /**
     * get TableFillList.
     *
     * @return List<TableFill>
     */
    public List<TableFill> getTableFillList() {
        if (tableFillVoList == null || tableFillVoList.isEmpty()) {
            return null;
        }
        return tableFillVoList.stream()
                .map(tableFillVo ->
                        new TableFill(tableFillVo.getFieldName(), EnumUtils.getFieldFill(tableFillVo.getFieldFill())))
                .collect(Collectors.toList());
    }
}
