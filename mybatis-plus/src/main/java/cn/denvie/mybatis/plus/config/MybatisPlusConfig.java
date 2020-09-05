/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Plus Config。
 *
 * @author denvie
 * @since 2020/9/6
 */
@Configuration
@MapperScan(basePackages = "cn.denvie.mybatis.plus.mapper")
public class MybatisPlusConfig {
    /**
     * 配置MybatisPlusInterceptor。
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*
         * 配置分页插件。
         * 一缓和二缓遵循Mybatis的规则，需要设置 MybatisConfiguration#useDeprecatedExecutor = false
         * 避免缓存出现问题（该属性会在旧插件移除后一同移除）
         */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        /*
         * 配置乐观锁插件。
         * 乐观锁实现方式：
         * 1. 取出记录时，获取当前version
         * 2. 更新时，带上这个version
         * 3. 执行更新时， set version = newVersion where version = oldVersion
         * 4. 如果version不对，就更新失败
         * 使用方式：
         * 1. 字段上加上@Version注解
         * 2. 配置 OptimisticLockerInnerInterceptor 拦截器
         */
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        /*
         * 配置防止全表更新与删除插件。
         */
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
