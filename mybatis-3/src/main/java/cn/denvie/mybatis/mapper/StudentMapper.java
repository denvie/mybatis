/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生表 Mapper
 * 
 * @author denvie
 * @since 2020/9/05
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}