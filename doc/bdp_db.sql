/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : bdp_db

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-07-06 22:18:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `opera_date` datetime DEFAULT NULL,
  `opera_ip` varchar(20) DEFAULT NULL,
  `module_name` varchar(20) DEFAULT NULL,
  `opera_name` varchar(20) DEFAULT NULL,
  `opera_type` tinyint(4) DEFAULT NULL,
  `opera_url` varchar(100) DEFAULT NULL,
  `opera_params` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_log` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `code` varchar(40) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `iconimg` varchar(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `display_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `display_order` smallint(6) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `last_modified_by` varchar(10) DEFAULT NULL,
  `last_modified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO t_sys_role VALUES ('1', '系统管理员', '管理员权限', '99', '2015-06-01 15:13:11', '0', null, null);

-- ----------------------------
-- Table structure for `t_sys_roleresource`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_roleresource`;
CREATE TABLE `t_sys_roleresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_rolefunction` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_roleresource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `randomcode` varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `last_modified_at` datetime DEFAULT NULL,
  `last_modified_by` varchar(10) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_t_sys_user_2` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO t_sys_user VALUES ('1', 'admin', 'a1b778a821b88ff3f9924a8e1539a5e2', '674080', '1', '马林', null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for `t_sys_userlogin`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_userlogin`;
CREATE TABLE `t_sys_userlogin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_ip` varchar(20) DEFAULT NULL,
  `terminal` varchar(20) DEFAULT NULL,
  `explorerType` varchar(40) DEFAULT NULL,
  `explorerVersion` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_t_sys_userlogin` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_userlogin
-- ----------------------------
