/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.springboot.mapper;

import cn.denvie.mybatis.common.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper
 * 
 * @author denvie
 * @since 2020/9/05
 */
@Mapper
public interface UserMapper {
    /**
     * 分页查询。
     *
     * @return Page<User>
     */
    Page<User> selectAll();
}