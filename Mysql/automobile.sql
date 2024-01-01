/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : automobile

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 01/01/2024 21:55:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `cid` int NOT NULL,
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ccolour` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cpro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ctime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cprice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cbrand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ctype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (2, '奔驰B级', '白色', '1.3T ', '2021', '26.22-w27.66w', '奔驰', '小型车');
INSERT INTO `car` VALUES (3, '奔驰C级奔驰CLA', '黄色', '1.5T', '2021', '27.11w-28.33w', '奔驰', '大型车');
INSERT INTO `car` VALUES (4, '奔驰E级', '红色', '1.8T', '2020', '21.22w-22.33w', '奔驰', '中型车');
INSERT INTO `car` VALUES (5, '奔驰S级', '灰色', '2.0T', '2021', '51.11w-55.33w', '奔驰', '中型车');
INSERT INTO `car` VALUES (6, '奔驰GLE', '蓝色', '1.1T', '2010', '28.21w-26.22w', '奔驰', '中型车');
INSERT INTO `car` VALUES (7, '奔驰G级', '绿色', '1.2T', '2020', '20.13w-33.55w', '奔驰', '中型车');
INSERT INTO `car` VALUES (8, '宝马1系', '黑色', '1.1T', '2008', '27.55w-32.44w', '宝马', '大型车');
INSERT INTO `car` VALUES (9, '宝马3系', '白色', '1.3T', '2009', '28.33w-29.33w', '宝马', '大型车');
INSERT INTO `car` VALUES (10, '宝马i3', '黄色', '1.3T', '2021', '21.27w-36.22w', '宝马', '小型车');
INSERT INTO `car` VALUES (11, '宝马5系', '红色', '2.2T', '2020', '35.21w-39.21w', '宝马', '小型车');
INSERT INTO `car` VALUES (12, '宝马x1', '绿色', '1.4T', '2020', '24.22w-28.33w', '宝马', '小型车');
INSERT INTO `car` VALUES (13, '宝马x2', '蓝色', '2.0T', '2020', '30.34w-38.74w', '宝马', '中型车');
INSERT INTO `car` VALUES (14, '梅德赛斯-AMG', '玫红色', '2.3T', '2020', '69.44w-80.77w', '奔驰', '中型车');
INSERT INTO `car` VALUES (15, '梅德赛斯-迈巴赫', '酒红色', '2.5T', '2022', '99.99w-120.33w', '奔驰', '中型车');
INSERT INTO `car` VALUES (16, 'Durango', '天蓝色', '3.0T', '2021', '340.33w-489.44w', '道奇', '大型车');
INSERT INTO `car` VALUES (20, 'ni', 'ss', '1.5', 'sh', 'sd', 'sd', '大型车');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `clid` int NOT NULL,
  `clname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `clsex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `clage` int NULL DEFAULT NULL,
  `clplace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `clnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `clrecord` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`clid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES (1, '海平', '男', 18, '山东省', '150', '无');
INSERT INTO `client` VALUES (2, '云落', '男', 20, '河北省', '162', '无');

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `saleid` int NULL DEFAULT NULL,
  `salename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `saleclient` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `saletime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salestaff` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES (2, '奔驰A级', 'ww', '2023.11', '22as');
INSERT INTO `sale` VALUES (3, '宝马X系', 'ww', '2023.12', '33aa');
INSERT INTO `sale` VALUES (1, '法拉利488spider', '小A', '2023.9', '海平');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `sid` int NULL DEFAULT NULL,
  `sname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ssex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `spwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sedu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `splace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '王海平', '男', '123', '北京科技大学', '北京');
INSERT INTO `staff` VALUES (2, '杜建业', '男', '123', '西北工业大学', '西安');
INSERT INTO `staff` VALUES (3, '崔大虫', '男', '20', '山东第一医科大学', '济南');
INSERT INTO `staff` VALUES (4, '郭平川', '男', '21', '北京工业大学', '北京');
INSERT INTO `staff` VALUES (6, '胡长青', '男', '22', '东京大学', '东京');
INSERT INTO `staff` VALUES (7, '我', '男', '21', '清华大学', '北京');
INSERT INTO `staff` VALUES (8, '李文静', '女', '20', '复旦大学', '上海');
INSERT INTO `staff` VALUES (9, 'test', '男', '111', 'null', 'null');
INSERT INTO `staff` VALUES (321, '第三方', '男', '123', '地方', '23');

SET FOREIGN_KEY_CHECKS = 1;
