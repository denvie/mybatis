/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.generator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TableFillVo。
 *
 * @author Denvie
 * @since 2020/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableFillVo {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 忽略类型
     */
    private String fieldFill;
}
