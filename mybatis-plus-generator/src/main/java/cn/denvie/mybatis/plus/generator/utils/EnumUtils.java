/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * EnumUtils。
 *
 * @author Denvie
 * @since 2020/9/6
 */
public class EnumUtils {
    /**
     * 获取生成主键的ID类型
     *
     * @param idTYpe 生成主键的ID类型
     * @return IdType
     */
    public static IdType getIdType(String idTYpe) {
        for (IdType type : IdType.values()) {
            if (type.name().equalsIgnoreCase(idTYpe)) {
                return type;
            }
        }
        return IdType.NONE;
    }

    /**
     * 获取字段填充类型
     *
     * @param fieldFill 字段填充类型
     * @return FieldFill
     */
    public static FieldFill getFieldFill(String fieldFill) {
        for (FieldFill type : FieldFill.values()) {
            if (type.name().equalsIgnoreCase(fieldFill)) {
                return type;
            }
        }
        return FieldFill.DEFAULT;
    }
}
