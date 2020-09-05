/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.springboot;

import cn.denvie.mybatis.common.model.User;
import cn.denvie.mybatis.springboot.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mybatis SpringBoot Application.
 *
 * @author denvie
 * @since 2020/9/2
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.denvie.mybatis.springboot.mapper")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MybatisSpringBootApplication implements CommandLineRunner {
    private final UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) {
        PageHelper.startPage(1, 10);
        Page<User> users = userMapper.selectAll();
        System.out.println("Total: " + users.getTotal());
        for (User user : users) {
            System.out.println(user);
        }
    }
}
