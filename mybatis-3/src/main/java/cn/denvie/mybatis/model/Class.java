/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.model;

import java.util.List;

/**
 * 班级表
 * 
 * @author denvie
 * @since 2020/9/05
 */
public class Class {
    /**
     * 班级ID
     */
    private Integer id;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 年级
     */
    private int grade;
    /**
     * 学生列表
     */
    List<Student> students;

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", students=" + students +
                '}';
    }
}