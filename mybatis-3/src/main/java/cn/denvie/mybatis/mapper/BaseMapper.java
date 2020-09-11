/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import java.util.List;

/**
 * BaseMapper。
 *
 * @param <Entity> 实体类
 * @author Denvie
 * @since 2020/9/5
 */
public interface BaseMapper<Entity> {
    /**
     * 添加。
     *
     * @param entity Entity
     * @return int
     */
    int insert(Entity entity);

    /**
     * 根据ID查询。
     *
     * @param id id
     * @return Entity
     */
    Entity selectById(int id);

    /**
     * 查询所有。
     *
     * @return List<Entity>
     */
    List<Entity> selectAll();
}
