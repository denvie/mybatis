/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学生表 Mapper
 * 
 * @author denvie
 * @since 2020/9/05
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 批量添加学生。
     *
     * @param students students
     * @return int
     */
    int batchInsert(@Param("students") List<Student> students);

    /**
     * 根据参数Map查询。
     *
     * @param params 参数Map
     * @return List<Student>
     */
    List<Student> selectByMap(Map<String, Object> params);
}