/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;

import java.util.Random;

/**
 * @author denvie
 * @since 2020/8/16
 */
public class UserMapperTest extends AbstractBaseTest {

    @Test
    public void testInsert() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("尛飛俠");
        user.setPassword("123456");
        user.setAge(18);
        user.setDescription("闭家锁");
        int insert = userMapper.insert(user);
        getSession().commit();
    }

    @Test
    public void testInsertSelective() {
        UserMapper userMapper = getSession().getMapper(UserMapper.class);
        User user;
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            user = new User();
            user.setUsername("Denvie" + i);
            user.setPassword("123456");
            user.setAge(18 + random.nextInt(42));
            user.setDescription("居里大叔" + i);
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
