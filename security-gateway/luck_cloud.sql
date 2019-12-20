/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : luck_cloud

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 20/12/2019 17:17:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(1) NULL DEFAULT NULL COMMENT '状态 0-停用 1-启用',
  `uri` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转发地址',
  `predicates` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `filters` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过滤',
  `order` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '顺序',
  `creator_id` int(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `up_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES (1, 'service-provider', 1, 'service-provider', '/api/**', '1', '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `resources` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限资源',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型，menu或者button',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `up_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, 'pre', NULL, '权限设置', 'pre_admin', 'menu', NULL, '2019-11-15 20:28:45', '2019-11-15 20:28:45');
INSERT INTO `permission` VALUES (2, 0, 'sys', NULL, '系统设置', 'sys_set', 'menu', NULL, '2019-11-16 11:29:32', '2019-11-16 11:29:32');
INSERT INTO `permission` VALUES (3, 1, 'pre_perm', NULL, '权限管理', 'pre_perm_admin', 'menu', NULL, '2019-11-16 11:38:22', '2019-11-16 11:38:22');
INSERT INTO `permission` VALUES (4, 1, 'pre_user', NULL, '用户管理', 'pre_user_admin', 'menu', NULL, '2019-11-16 11:39:17', '2019-11-18 10:16:26');
INSERT INTO `permission` VALUES (5, 1, 'pre_role', NULL, '角色管理', 'pre_role_admin', 'menu', NULL, '2019-11-16 11:40:00', '2019-11-16 11:40:35');
INSERT INTO `permission` VALUES (6, 3, 'pre_perm:new', 'permission', '新增权限', NULL, 'button', NULL, '2019-11-16 19:58:00', '2019-11-22 16:50:29');
INSERT INTO `permission` VALUES (7, 3, 'pre_perm:delete', 'permission/', '删除权限', NULL, 'button', NULL, '2019-11-16 19:58:21', '2019-11-22 17:00:46');
INSERT INTO `permission` VALUES (8, 3, 'pre_perm:update', 'permission', '修改权限', NULL, 'button', NULL, '2019-11-16 19:58:50', '2019-11-22 16:50:31');
INSERT INTO `permission` VALUES (9, 3, 'pre_perm:view', 'permission/all,permission/tree', '查看权限', NULL, 'button', NULL, '2019-11-16 19:59:10', '2019-11-21 16:28:13');
INSERT INTO `permission` VALUES (12, 4, 'pre_user:new', 'upload/avatar,user', '新增用户', NULL, 'button', NULL, '2019-11-18 09:57:51', '2019-11-22 17:05:32');
INSERT INTO `permission` VALUES (13, 4, 'pre_user:delete', 'user/', '删除用户', NULL, 'button', NULL, '2019-11-18 09:58:09', '2019-11-22 17:05:34');
INSERT INTO `permission` VALUES (14, 4, 'pre_user:update', 'user', '修改用户', NULL, 'button', NULL, '2019-11-18 09:58:24', '2019-11-22 17:05:44');
INSERT INTO `permission` VALUES (15, 4, 'pre_user:view', 'user/info,user/page,role/all', '查看用户', NULL, 'button', NULL, '2019-11-18 09:58:38', '2019-11-22 10:14:04');
INSERT INTO `permission` VALUES (16, 5, 'pre_role:new', 'role', '新增角色', NULL, 'button', NULL, '2019-11-18 09:59:19', '2019-11-22 17:05:50');
INSERT INTO `permission` VALUES (17, 5, 'pre_role:delete', 'role/', '删除角色', NULL, 'button', NULL, '2019-11-18 09:59:32', '2019-11-22 17:05:57');
INSERT INTO `permission` VALUES (18, 5, 'pre_role:update', 'role', '修改角色', NULL, 'button', NULL, '2019-11-18 09:59:41', '2019-11-22 17:05:49');
INSERT INTO `permission` VALUES (19, 5, 'pre_role:view', 'role/page', '查看角色', NULL, 'button', NULL, '2019-11-18 09:59:51', '2019-11-22 16:25:52');
INSERT INTO `permission` VALUES (20, 2, 'sys_database', NULL, '数据库监控', 'sys_database', 'menu', NULL, '2019-11-18 10:01:33', '2019-11-18 10:02:27');
INSERT INTO `permission` VALUES (21, 2, 'sys_logs', NULL, '系统日志', 'sys_logs', 'menu', NULL, '2019-11-18 10:01:55', '2019-11-18 10:02:27');
INSERT INTO `permission` VALUES (22, 2, 'sys_wechat', NULL, '微信设置', 'sys_wechat', 'menu', NULL, '2019-11-18 10:02:07', '2019-11-18 10:02:27');
INSERT INTO `permission` VALUES (23, 2, 'sys_backstage', NULL, '后台设置', 'sys_backstage', 'menu', NULL, '2019-11-18 10:02:24', '2019-11-18 10:02:27');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '角色状态[0.停用，1.正常]',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `up_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 'ROLE_ROOT', 1, '2019-11-15 20:27:54', '2019-11-15 20:27:54');
INSERT INTO `role` VALUES (2, '用户', 'ROLE_USER', 1, '2019-11-19 15:59:27', '2019-11-19 15:59:27');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1, '2019-11-15 20:29:08');
INSERT INTO `role_permission` VALUES (2, 2, 1, '2019-11-16 17:35:35');
INSERT INTO `role_permission` VALUES (3, 3, 1, '2019-11-16 17:35:40');
INSERT INTO `role_permission` VALUES (4, 4, 1, '2019-11-16 19:59:33');
INSERT INTO `role_permission` VALUES (5, 5, 1, '2019-11-16 19:59:34');
INSERT INTO `role_permission` VALUES (6, 6, 1, '2019-11-16 19:59:36');
INSERT INTO `role_permission` VALUES (7, 7, 1, '2019-11-16 19:59:47');
INSERT INTO `role_permission` VALUES (8, 8, 1, '2019-11-16 19:59:48');
INSERT INTO `role_permission` VALUES (9, 9, 1, '2019-11-16 19:59:50');
INSERT INTO `role_permission` VALUES (10, 15, 1, '2019-11-18 10:12:29');
INSERT INTO `role_permission` VALUES (11, 12, 1, '2019-11-18 11:17:40');
INSERT INTO `role_permission` VALUES (12, 13, 1, '2019-11-18 11:17:42');
INSERT INTO `role_permission` VALUES (13, 14, 1, '2019-11-18 11:17:44');
INSERT INTO `role_permission` VALUES (14, 16, 1, '2019-11-18 16:59:31');
INSERT INTO `role_permission` VALUES (15, 17, 1, '2019-11-18 16:59:33');
INSERT INTO `role_permission` VALUES (16, 18, 1, '2019-11-18 16:59:35');
INSERT INTO `role_permission` VALUES (17, 19, 1, '2019-11-18 16:59:37');
INSERT INTO `role_permission` VALUES (85, 1, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (86, 19, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (87, 3, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (88, 4, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (89, 5, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (90, 6, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (91, 9, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (92, 12, 2, '2019-11-22 17:19:52');
INSERT INTO `role_permission` VALUES (93, 15, 2, '2019-11-22 17:19:52');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信open_id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别[ 0.女  1.男  2.未知]',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '状态 【0.禁用 1.正常 2.被删除】',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `up_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'admin', 'a121bc@163.com', NULL, '超级管理员', '$2a$10$xJ6pI2TLs/Ok84V9JgrJIOtLy091bnypr0RYsnxKN5Qz9d4rw7Qmq', 1, '2019-01-01 00:00:00', 1, '2019-11-15 20:25:22', '2019-11-18 16:25:41');
INSERT INTO `user` VALUES (2, 'https://i.loli.net/2019/11/19/E4kWjbFg1uA6HhI.jpg', 'xiaoming', 'xm@163.com', NULL, '小明', '$2a$10$/UsbOtIrQrkfz1SWumBtFey5kr5NEmo6GgFFGckWH.sXjABnhc4Be', 1, '2019-11-01 00:00:00', 1, '2019-11-19 15:58:39', '2019-11-19 15:58:39');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, '2019-11-15 20:29:02');
INSERT INTO `user_role` VALUES (5, 2, 2, '2019-11-21 14:25:55');

SET FOREIGN_KEY_CHECKS = 1;
