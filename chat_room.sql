/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50724
Source Host           : localhost:3309
Source Database       : chat_room

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-04-19 09:51:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for imchatlog
-- ----------------------------
DROP TABLE IF EXISTS `imchatlog`;
CREATE TABLE `imchatlog` (
  `chatid` int(11) NOT NULL AUTO_INCREMENT,
  `fromuser` int(11) DEFAULT NULL,
  `touser` int(11) DEFAULT NULL,
  `chattime` varchar(100) DEFAULT NULL,
  `chat` varchar(1000) DEFAULT NULL,
  `chatflag` int(11) DEFAULT NULL COMMENT '0--未发送\r\n            1--已发送（默认）',
  PRIMARY KEY (`chatid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imchatlog
-- ----------------------------
INSERT INTO `imchatlog` VALUES ('1', '4', null, '2018-12-06 17:34:50', ' 17:34:50 123(4) 对大家说\n  31311\n', '0');
INSERT INTO `imchatlog` VALUES ('2', '4', null, '2018-12-06 17:37:31', ' 17:37:31 123(4) 对大家说\n  21313\n', '0');
INSERT INTO `imchatlog` VALUES ('3', '4', null, '2018-12-06 17:37:32', ' 17:37:32 123(4) 对大家说\n  3121\n', '0');
INSERT INTO `imchatlog` VALUES ('4', '4', null, '2018-12-06 17:37:35', ' 17:37:35 123(4) 对大家说\n  woshi \n', '0');
INSERT INTO `imchatlog` VALUES ('5', '4', null, '2018-12-06 19:15:39', ' 19:15:39 123(4) 对大家说\n  vdzaf \n', '0');
INSERT INTO `imchatlog` VALUES ('6', '4', null, '2018-12-06 19:17:15', ' 19:17:15 123(4) 对大家说\n  nfd\n', '0');
INSERT INTO `imchatlog` VALUES ('7', '4', null, '2018-12-06 19:38:51', ' 19:38:51 123(4) 对大家说\n  asd\n', '0');
INSERT INTO `imchatlog` VALUES ('8', '4', null, '2020-04-17 18:28:09', ' 18:28:09 123(4) 对大家说\n  啊\n', '0');
INSERT INTO `imchatlog` VALUES ('9', '4', null, '2020-04-17 18:28:11', ' 18:28:11 123(4) 对大家说\n  啊\n', '0');
INSERT INTO `imchatlog` VALUES ('10', '4', null, '2020-04-17 18:28:12', ' 18:28:12 123(4) 对大家说\n  啊\n', '0');
INSERT INTO `imchatlog` VALUES ('11', '4', null, '2020-04-17 18:28:12', ' 18:28:12 123(4) 对大家说\n  啊\n', '0');
INSERT INTO `imchatlog` VALUES ('12', '4', '29', '2020-04-18 08:32:17', ' 08:32:17 123(4) 对lllll(29)说\n  当代带\n', '0');
INSERT INTO `imchatlog` VALUES ('13', '4', '29', '2020-04-18 08:33:40', ' 08:33:40 123(4) 对lllll(29)说\n  哈哈哈\n', '0');
INSERT INTO `imchatlog` VALUES ('14', '4', '29', '2020-04-18 08:35:02', ' 08:35:02 123(4) 对lllll(29)说\n  333\n', '0');
INSERT INTO `imchatlog` VALUES ('15', '4', '29', '2020-04-18 08:35:05', ' 08:35:05 123(4) 对lllll(29)说\n  222\n', '0');
INSERT INTO `imchatlog` VALUES ('16', '4', '29', '2020-04-18 08:35:06', ' 08:35:06 123(4) 对lllll(29)说\n  333\n', '0');
INSERT INTO `imchatlog` VALUES ('17', '4', '29', '2020-04-18 08:35:07', ' 08:35:07 123(4) 对lllll(29)说\n  222\n', '0');
INSERT INTO `imchatlog` VALUES ('18', '4', '29', '2020-04-18 08:35:08', ' 08:35:08 123(4) 对lllll(29)说\n  222\n', '0');
INSERT INTO `imchatlog` VALUES ('19', '4', '29', '2020-04-18 08:35:09', ' 08:35:09 123(4) 对lllll(29)说\n  22\n', '0');
INSERT INTO `imchatlog` VALUES ('20', '4', '29', '2020-04-18 08:35:10', ' 08:35:10 123(4) 对lllll(29)说\n  22\n', '0');
INSERT INTO `imchatlog` VALUES ('21', '0', '0', '2020-04-18 16:55:17', ' 16:55:17 123(4) 对大家说\n  按时\n', '0');
INSERT INTO `imchatlog` VALUES ('22', '4', '0', '2020-04-18 16:57:23', ' 16:57:23 123(4) 对大家说\n  啊是大\n', '0');
INSERT INTO `imchatlog` VALUES ('23', '4', '0', '2020-04-18 16:59:12', ' 16:59:12 123(4) 对大家说\n  啊是大\n', '0');
INSERT INTO `imchatlog` VALUES ('24', '4', '0', '2020-04-18 16:59:53', ' 16:59:53 123(4) 对大家说\n  按时\n', '0');
INSERT INTO `imchatlog` VALUES ('25', '4', '0', '2020-04-18 17:15:17', ' 17:15:17 123(4) 对大家说\n  啊是大\n', '0');
INSERT INTO `imchatlog` VALUES ('26', '4', '0', '2020-04-18 17:17:51', ' 17:17:51 123(4) 对大家说\n  按时\n', '0');
INSERT INTO `imchatlog` VALUES ('27', '32', '0', '2020-04-18 21:17:44', ' 21:17:44 11(32) 对大家说\n  啊啊啊\n', '0');
INSERT INTO `imchatlog` VALUES ('28', '33', '0', '2020-04-18 21:22:29', ' 21:22:29 1234(33) 对大家说\n  啊啊啊\n', '0');
INSERT INTO `imchatlog` VALUES ('29', '32', '33', '2020-04-18 21:22:37', ' 21:22:37 11(32) 对1234(33)说\n  哈哈哈\n', '1');
INSERT INTO `imchatlog` VALUES ('30', '32', '33', '2020-04-18 21:22:42', ' 21:22:42 11(32) \n  哈哈哈\n', '1');
INSERT INTO `imchatlog` VALUES ('31', '32', '31', '2020-04-18 21:22:54', ' 21:22:54 11(32) \n  嘿嘿嘿\n', '1');

-- ----------------------------
-- Table structure for imfriends
-- ----------------------------
DROP TABLE IF EXISTS `imfriends`;
CREATE TABLE `imfriends` (
  `frdid` int(11) NOT NULL AUTO_INCREMENT,
  `fromuser` int(11) DEFAULT NULL,
  `touser` int(11) DEFAULT NULL,
  `frdstate` int(11) DEFAULT NULL,
  PRIMARY KEY (`frdid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imfriends
-- ----------------------------
INSERT INTO `imfriends` VALUES ('1', '4', '4', '0');
INSERT INTO `imfriends` VALUES ('2', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('3', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('4', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('5', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('6', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('7', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('8', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('9', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('10', '4', '4', '0');
INSERT INTO `imfriends` VALUES ('11', '4', '5', '0');
INSERT INTO `imfriends` VALUES ('12', '4', '6', '0');
INSERT INTO `imfriends` VALUES ('13', '4', '4', '0');
INSERT INTO `imfriends` VALUES ('14', '4', '4', '0');
INSERT INTO `imfriends` VALUES ('15', '4', '12', '0');
INSERT INTO `imfriends` VALUES ('16', '4', '28', '0');

-- ----------------------------
-- Table structure for imuserlog
-- ----------------------------
DROP TABLE IF EXISTS `imuserlog`;
CREATE TABLE `imuserlog` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `logtime` varchar(225) DEFAULT NULL,
  `loguserid` int(11) DEFAULT NULL,
  `loguser` varchar(20) DEFAULT NULL,
  `logip` varchar(15) DEFAULT NULL,
  `logcontent` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imuserlog
-- ----------------------------
INSERT INTO `imuserlog` VALUES ('1', '2001', '4', '123', '123', '123');
INSERT INTO `imuserlog` VALUES ('2', 'asd', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('3', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('4', '1970-01-01 08:00:00', '5', '234', '127.0.0.1', '234上线了');
INSERT INTO `imuserlog` VALUES ('5', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('6', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('7', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('8', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('9', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('10', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('11', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('12', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('13', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('14', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('15', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('16', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('17', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('18', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('19', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('20', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('21', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('22', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('23', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('24', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('25', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('26', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('27', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('28', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('29', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('30', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('31', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('32', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('33', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('34', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('35', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('36', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('37', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('38', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('39', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('40', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('41', '1970-01-01 08:00:00', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('42', '1970-01-01 08:00:00', '5', '234', '127.0.0.1', '234上线了');
INSERT INTO `imuserlog` VALUES ('43', '2020-04-18 19:35:55', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('44', '2020-04-18 19:37:29', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('45', '2020-04-18 19:38:11', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('46', '2020-04-18 19:39:21', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('47', '2020-04-18 20:35:18', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('48', '2020-04-18 20:35:32', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('49', '2020-04-18 20:37:11', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('50', '2020-04-18 20:40:50', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('51', '2020-04-18 20:41:02', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('52', '2020-04-18 21:12:29', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('53', '2020-04-18 21:13:26', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('54', '2020-04-18 21:13:33', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('55', '2020-04-18 21:15:22', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('56', '2020-04-18 21:15:34', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('57', '2020-04-18 21:17:21', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('58', '2020-04-18 21:17:54', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('59', '2020-04-18 21:20:56', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('60', '2020-04-18 21:21:04', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('61', '2020-04-18 21:21:31', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('62', '2020-04-18 21:21:44', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('63', '2020-04-18 21:22:23', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('64', '2020-04-18 21:23:50', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('65', '2020-04-18 21:24:10', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('66', '2020-04-18 21:24:44', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('67', '2020-04-18 21:24:52', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('68', '2020-04-18 21:25:10', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('69', '2020-04-18 21:28:48', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('70', '2020-04-18 21:30:00', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('71', '2020-04-18 21:30:23', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('72', '2020-04-18 21:31:10', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('73', '2020-04-18 21:31:17', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('74', '2020-04-18 21:31:57', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('75', '2020-04-18 21:32:07', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('76', '2020-04-18 21:32:11', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('77', '2020-04-18 22:08:56', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('78', '2020-04-18 22:12:07', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('79', '2020-04-18 22:12:45', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('80', '2020-04-18 22:13:10', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('81', '2020-04-18 22:17:29', '4', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('82', '2020-04-18 22:17:41', '31', '111', '127.0.0.1', '111上线了');
INSERT INTO `imuserlog` VALUES ('83', '2020-04-18 22:48:58', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('84', '2020-04-18 22:49:27', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('85', '2020-04-18 23:03:02', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('86', '2020-04-18 23:03:20', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('87', '2020-04-18 23:03:38', '39', '13888', '127.0.0.1', '13888上线了');
INSERT INTO `imuserlog` VALUES ('88', '2020-04-19 08:49:57', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('89', '2020-04-19 08:50:22', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('90', '2020-04-19 08:52:08', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('91', '2020-04-19 08:53:38', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('92', '2020-04-19 08:55:17', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('93', '2020-04-19 08:56:14', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('94', '2020-04-19 08:56:23', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('95', '2020-04-19 08:57:35', '32', '11', '127.0.0.1', '11上线了');
INSERT INTO `imuserlog` VALUES ('96', '2020-04-19 09:00:25', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('97', '2020-04-19 09:01:05', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('98', '2020-04-19 09:02:43', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('99', '2020-04-19 09:03:19', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('100', '2020-04-19 09:03:52', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('101', '2020-04-19 09:08:05', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('102', '2020-04-19 09:10:28', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('103', '2020-04-19 09:11:47', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('104', '2020-04-19 09:11:54', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('105', '2020-04-19 09:15:07', '33', '1234', '127.0.0.1', '1234上线了');
INSERT INTO `imuserlog` VALUES ('106', '2020-04-19 09:15:08', '40', '123', '127.0.0.1', '123上线了');
INSERT INTO `imuserlog` VALUES ('107', '2020-04-19 09:28:33', '40', '123', '127.0.0.1', '123上线了');

-- ----------------------------
-- Table structure for imusers
-- ----------------------------
DROP TABLE IF EXISTS `imusers`;
CREATE TABLE `imusers` (
  `userid` int(4) NOT NULL AUTO_INCREMENT,
  `userName` varchar(16) NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `Sex` varchar(2) DEFAULT NULL,
  `head` int(11) DEFAULT NULL,
  `nikename` varchar(255) DEFAULT NULL,
  `type` varchar(6) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `phoneNumber` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userName` (`userName`) USING BTREE,
  UNIQUE KEY `phonenumber` (`phoneNumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imusers
-- ----------------------------
INSERT INTO `imusers` VALUES ('32', '11', '123', '女', '0', '123', 'user', '11', '17366669900');
INSERT INTO `imusers` VALUES ('33', '1234', '123', '女', '0', '哈哈哈', 'user', '11', '17366669999');
INSERT INTO `imusers` VALUES ('40', '123', '123', '女', '0', '哈哈哈', 'user', '11', '17366669992');
INSERT INTO `imusers` VALUES ('41', '321', '123', '男', '0', '啊是大', 'user', '11', '16788553569');
