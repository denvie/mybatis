/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.Class;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * ClassMapperTest。
 *
 * @author denvie
 * @since 2020/9/5
 */
public class ClassMapperTest extends AbstractBaseTest {
    @Test
    public void testInsert() {
        ClassMapper mapper = session.getMapper(ClassMapper.class);
        Class myClass = new Class();
        myClass.setName("一班");
        myClass.setGrade(1);
        int insert = mapper.insert(myClass);
        session.commit();
        Assert.assertTrue(insert > 0);
        System.out.println(myClass);
    }

    @Test
    public void testSelectById() {
        ClassMapper mapper = session.getMapper(ClassMapper.class);
        Class myClass = mapper.selectById(1);
        System.out.println(myClass);
    }

    @Test
    public void testSelectAll() {
        ClassMapper mapper = session.getMapper(ClassMapper.class);
        List<Class> classes = mapper.selectAll();
        for (Class cls : classes) {
            System.out.println(cls);
        }
    }
}
