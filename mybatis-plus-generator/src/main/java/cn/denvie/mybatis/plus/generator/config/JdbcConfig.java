/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

/**
 * Jdbc配置。
 *
 * @author Denvie
 * @since 2020/9/6
 */
@Data
public class JdbcConfig {
    /**
     * 数据库类型
     */
    private String dbType = DbType.MYSQL.getDb();
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;
}
