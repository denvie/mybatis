/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.common.model;

/**
 * 学生表
 * 
 * @author Denvie
 * @since 2020/9/05
 */
public class Student {
    /**
     * 学生ID
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 级别ID
     */
    private Integer classId;
    /**
     * 学生所在班级
     */
    private Class myClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Class getMyClass() {
        return myClass;
    }

    public void setMyClass(Class myClass) {
        this.myClass = myClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classId=" + classId +
                ", myClass=" + myClass +
                '}';
    }
}