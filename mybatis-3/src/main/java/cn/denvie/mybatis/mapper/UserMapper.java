/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.model.User;
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
     * 新增用户。
     *
     * @param record User
     * @return int
     */
    int insert(User record);

    /**
     * 新增用户Selective。
     *
     * @param record User
     * @return int
     */
    int insertSelective(User record);

    /**
     * 分页查询。
     *
     * @return Page<User>
     */
    Page<User> selectAll();

    /**
     * 根据主键查询用户。
     *
     * @param id id
     * @return User
     */
    User selectByPrimaryKey(Integer id);

    /**
     * Selective更新用户。
     * @param record User
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户。
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据主键删除用户。
     *
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);
}