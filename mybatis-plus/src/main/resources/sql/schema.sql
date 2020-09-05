/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 16/08/2020 12:02:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
    `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
    `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `lock_version` int(11) NULL DEFAULT NULL COMMENT '乐观锁版本',
    `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识',
    PRIMARY KEY (`id`),
    INDEX `pk_id`(`id`) USING BTREE,
    UNIQUE INDEX `uk_username`(`username`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;