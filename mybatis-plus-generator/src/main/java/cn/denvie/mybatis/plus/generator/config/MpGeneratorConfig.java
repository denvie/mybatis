/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import lombok.Data;

import java.util.List;

/**
 * MpGeneratorConfig。
 *
 * @author denvie
 * @since 2020/9/6
 */
@Data
public class MpGeneratorConfig {
    /**
     * 基础配置
     */
    private BasicConfig basic;

    /**
     * JDBC配置
     */
    private JdbcConfig jdbc;

    /**
     * 表策略配置
     */
    private TableConfig table;

    /**
     * 业务模块配置
     */
    private List<ModuleConfig> modules;
}
