/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.User;
import com.github.pagehelper.Page;

/**
 * @author denvie
 * @since 2020/8/16
 */
public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    Page<User> selectAll();

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer id);
}