/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import lombok.Data;

/**
 * 业务模块配置。
 *
 * @author Denvie
 * @since 2020/9/6
 */
@Data
public class ModuleConfig {
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块路径（文件夹名称）
     */
    private String modulePath;
    /**
     * 需要包含的表名（与exclude二选一配置）
     */
    private String include;
    /**
     * 需要排除的表名
     */
    private String exclude;
    /**
     * 表前缀
     */
    private String tablePrefix;
}
