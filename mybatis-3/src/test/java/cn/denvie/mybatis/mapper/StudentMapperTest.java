/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.common.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * StudentMapperTest。
 *
 * @author denvie
 * @since 2020/9/5
 */
public class StudentMapperTest extends AbstractBaseTest {
    @Test
    public void testInsert() {
        StudentMapper mapper = getSession().getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("尛飛俠");
        student.setAge(18);
        student.setClassId(1);
        int insert = mapper.insert(student);
        getSession().commit();
        Assert.assertTrue(insert > 0);
        System.out.println(student);
    }

    @Test
    public void testBatchInsert() {
        StudentMapper studentMapper = getSession().getMapper(StudentMapper.class);
        ClassMapper classMapper = getSession().getMapper(ClassMapper.class);

        int classCount = classMapper.selectAll().size();
        Random random = new Random();

        List<Student> students = new ArrayList<>();
        Student student;
        for (int i = 1; i < 100; i++) {
            student = new Student();
            student.setName("Denvie" + i);
            student.setAge(18 + random.nextInt(10));
            student.setClassId(random.nextInt(classCount) + 1);
            students.add(student);
        }
        studentMapper.batchInsert(students);
        getSession().commit();
    }

    @Test
    public void testSelectById() {
        StudentMapper mapper = getSession().getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student);
    }

    @Test
    public void testSelectByMap() {
        StudentMapper mapper = getSession().getMapper(StudentMapper.class);
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Denvie1");
        params.put("age", 22);
        List<Student> students = mapper.selectByMap(params);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectAll() {
        StudentMapper mapper = getSession().getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
