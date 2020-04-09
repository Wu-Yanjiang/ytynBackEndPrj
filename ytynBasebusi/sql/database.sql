DROP TABLE IF EXISTS COMMON_USER;
create table COMMON_USER (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USERNAME` varchar(255) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '密码',
  `SALT` varchar(255) NOT NULL COMMENT '盐',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `TEL` varchar (15)   DEFAULT NULL COMMENT '手机号',
  `ENABLE` BOOL DEFAULT TRUE COMMENT '启用',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
);

DROP TABLE IF EXISTS COMMON_USER_ROLE;
create table COMMON_USER_ROLE (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户Id',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色Id',
  PRIMARY KEY (`ID`)
);

/*
Navicat MySQL Data Transfer
Source Server         : arikyDB
Source Server Version : 50721
Source Host           : 47.106.95.168:3306
Source Database       : ariky
Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001
Date: 2018-05-14 16:05:51
*/


-- ----------------------------
-- Table structure for common_permssion
-- ----------------------------
DROP TABLE IF EXISTS `common_permission`;
CREATE TABLE `common_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '类型按钮(button)或者菜单(menu) ',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '上级ID',
  `URL` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `ICONCLS` varchar(255) DEFAULT NULL COMMENT '图标(可以不要)',
  `PERMISSION` varchar(255) DEFAULT NULL COMMENT '权限(如user:list)',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='该表用来存储资源权限信息';

-- ----------------------------
-- Table structure for common_role
-- ----------------------------
DROP TABLE IF EXISTS `common_role`;
CREATE TABLE `common_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ENABLE` bool DEFAULT true COMMENT '判断该角色是否在使用（1：使用，2：禁用）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for common_role_permssion
-- ----------------------------
DROP TABLE IF EXISTS `common_role_permission`;
CREATE TABLE `common_role_permssion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色Id',
  `RESOURCE_ID` int(11) DEFAULT NULL COMMENT '资源（权限）Id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=493 DEFAULT CHARSET=utf8 COMMENT='角色资源权限表中间表';