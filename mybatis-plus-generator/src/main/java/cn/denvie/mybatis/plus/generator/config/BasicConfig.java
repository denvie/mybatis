/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import lombok.Data;

/**
 * 基础配置。
 *
 * @author Denvie
 * @since 2020/9/6
 */
@Data
public class BasicConfig {
    /**
     * 版权信息
     */
    private String copyright = "Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.";
    /**
     * 开发人员
     */
    private String author = "Denvie";
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;
    /**
     * 是否打开输出目录
     */
    private boolean open = false;
    /**
     * 是否在xml中添加二级缓存配置
     */
    private boolean enableCache = false;
    /**
     * 开启 BaseResultMap
     */
    private boolean baseResultMap = true;
    /**
     * 开启 baseColumnList
     */
    private boolean baseColumnList = true;
    /**
     * 开启 swagger2 模式
     */
    private boolean swagger2 = false;
    /**
     * 生成主键的ID类型
     */
    private String idType = "NONE";
    /**
     * mapper类名后缀，如：Dao, Mapper, Repository等
     */
    private String mapperSuffix = "Mapper";
    /**
     * 父包名
     */
    private String parentPackage = "cn.denvie";
    /**
     * Entity包名
     */
    private String entityPackage = "entity";
    /**
     * Service包名
     */
    private String servicePackage = "service";
    /**
     * Service Impl包名
     */
    private String serviceImplPackage = "service.impl";
    /**
     * Mapper包名
     */
    private String mapperPackage = "mapper";
    /**
     * Controller包名
     */
    private String controllerPackage = "controller";
}
