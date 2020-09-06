/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * BaseEntity。
 * 根据阿里巴巴开发手册，每张数据库表必备三字段：id, gmt_create, gmt_modified。
 *
 * @author denvie
 * @since 2020/9/6
 */
public abstract class BaseEntity {
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    protected LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.UPDATE)
    protected LocalDateTime gmtModified;
    /**
     * 乐观锁版本
     */
    @ApiModelProperty(value = "乐观锁版本")
    @Version
    @TableField(value = "lock_version")
    protected Integer lockVersion = 0;
    /**
     * 逻辑删除标识
     */
    @ApiModelProperty(value = "逻辑删除标识")
    @TableLogic
    @TableField(value = "deleted")
    protected Integer deleted = 0;

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
