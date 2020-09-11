/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.common.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;

import java.util.Random;

/**
 * UserMapperTest。
 *
 * @author Denvie
 * @since 2020/8/16
 */
public class UserMapperTest extends AbstractBaseTest {

    @Test
    public void testInsert() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("denvie");
        user.setPassword("pwd12345");
        user.setNickname("尛飛俠");
        user.setAge(18);
        user.setEmail("7382763@qq.com");
        user.setDescription("闭家锁");
        userMapper.insert(user);
        getSession().commit();
    }

    @Test
    public void testInsertSelective() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user;
        Random random = new Random();
        for (int i = 1; i < 50; i++) {
            int no = random.nextInt(90000) + 10000;
            user = new User();
            user.setUsername("denvie" + i);
            user.setPassword("pwd" + no);
            user.setNickname("尛飛俠" + no);
            user.setAge(18 + random.nextInt(42));
            user.setEmail(no + "@qq.com");
            user.setDescription("居里大叔" + no);
            userMapper.insertSelective(user);
        }
        getSession().commit();
    }

    @Test
    public void testSelectAll() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        PageHelper.startPage(2, 10);
        Page<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByPrimaryKey() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user = new User();
        user.setId(2);
        user.setUsername("Denvie" + 666);
        user.setPassword("12345678");
        userMapper.updateByPrimaryKeySelective(user);
        getSession().commit();
    }

    @Test
    public void testUpdateByPrimaryKey() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user = new User();
        user.setId(2);
        user.setUsername("Denvie" + 888);
        user.setPassword("654321");
        userMapper.updateByPrimaryKey(user);
        getSession().commit();
    }

    @Test
    public void testDeleteByPrimaryKey() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        userMapper.deleteByPrimaryKey(2);
        getSession().commit();
    }
}
