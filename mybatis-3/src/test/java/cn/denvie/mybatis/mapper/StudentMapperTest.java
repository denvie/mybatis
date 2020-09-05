/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * StudentMapperTest。
 *
 * @author denvie
 * @since 2020/9/5
 */
public class StudentMapperTest extends AbstractBaseTest {
    @Test
    public void testInsert() {
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("尛飛俠");
        student.setAge(18);
        student.setClassId(1);
        int insert = mapper.insert(student);
        session.commit();
        Assert.assertTrue(insert > 0);
        System.out.println(student);
    }

    @Test
    public void testSelectById() {
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student);
    }

    @Test
    public void testSelectAll() {
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
