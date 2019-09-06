/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 39.105.78.173:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/09/2019 09:58:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `path` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端路由路径',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端路由组件',
  `hideInBread` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hideInMenu` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `redirect` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `notCache` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `beforeCloseName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `href` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parentId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/jichu', 'jichu', 'views/main/index', NULL, NULL, 'md-settings', '基础设置', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (2, 'librarymanagement', 'librarymanagement', 'components/librarymanage/index', NULL, NULL, 'ios-book', '图书管理', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `menu` VALUES (3, 'scrapmanagement', 'scrapmanagement', 'components/scrapmanage/index', NULL, NULL, 'ios-trash-outline', '报废管理', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `menu` VALUES (4, 'lendingmanagement', 'lendingmanagement', 'components/lendingmanage/index', NULL, NULL, 'md-bookmarks', '借阅管理', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `menu` VALUES (5, '/xitong', 'xitong', 'views/main/index', NULL, NULL, 'ios-hammer', '系统管理', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (6, 'vipmanagement', 'vipmanagement', 'components/vipmanage/index', NULL, NULL, 'md-person', '会员管理', NULL, NULL, NULL, NULL, NULL, '5');
INSERT INTO `menu` VALUES (7, 'lendingstatements', 'lendingstatements', 'components/lendingstate/index', NULL, NULL, 'md-trending-up', '借出报表', NULL, NULL, NULL, NULL, NULL, '5');
INSERT INTO `menu` VALUES (8, 'incomestatement', 'incomestatement', 'components/incomestatement/index', NULL, NULL, 'logo-yen', '收入报表', NULL, NULL, NULL, NULL, NULL, '5');
INSERT INTO `menu` VALUES (9, '/test', 'test', 'components/test', NULL, NULL, 'logo-snapchat', '测试', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `No` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Role_admin', '管理员');
INSERT INTO `role` VALUES (2, 'Role_user', '用户');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 1, 5);
INSERT INTO `role_menu` VALUES (3, 1, 9);
INSERT INTO `role_menu` VALUES (4, 2, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `telephone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '移动电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '住址',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `userface` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '李白', '13859675214', '358468', '上海市普陀区', 'libai', '$2a$10$oliyZNUQOjIwM.RxCnKKfegYd8UZkvs4oTfWkUxh6C8f5wJN.gEUK', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png', 1, '');
INSERT INTO `user` VALUES (2, '杜甫', '13889541284', '854621', '上海市浦东新区', 'dufu', '$2a$10$rCOMiNjm0V24EwcVpUcyp.EtQclNJbhUl6nEKgEzlRfc0FkMWfnM.', NULL, 1, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (3, 2, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `name` varchar(26) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '用户姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(11) DEFAULT NULL COMMENT '性别（1:男，2:女）',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '联系电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '家庭住址',
  `nativeplace` varchar(4) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '籍贯',
  `jointime` date DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '漩涡鸣人', 18, 1, '18221372651', '木ノ叶の裏隠れの村', '火の国', '2019-05-28');
INSERT INTO `users` VALUES (2, '宇智波佐助', 19, 1, '18217692350', '木ノ叶の裏隠れの村', '火の国', '2017-02-17');
INSERT INTO `users` VALUES (3, '春野樱', 18, 2, '18221567392', '木ノ叶の裏隠れの村', '火の国', '2019-06-21');
INSERT INTO `users` VALUES (4, '旗木卡卡西', 27, 1, '18221362092', '木ノ叶の裏隠れの村', '火の国', '2019-07-18');
INSERT INTO `users` VALUES (5, '照美冥', 30, 2, '18217058792', '水影村', '水の国', '2019-07-19');
INSERT INTO `users` VALUES (6, '矢仓', 27, 2, '18221782791', '水影村', '水の国', '2018-10-24');
INSERT INTO `users` VALUES (7, '桃地再不斩', 31, 1, '18221710591', '水影村', '水の国', '2019-02-20');
INSERT INTO `users` VALUES (8, '两天秤大野木', 78, 1, '18221713552', '土影村', '土の国', '2019-06-20');
INSERT INTO `users` VALUES (11, '我爱罗', 19, 1, '13918375198', '風影村', '風の国', '2019-04-17');
INSERT INTO `users` VALUES (12, '手鞠', 21, 2, '13917792158', '風影村', '風の国', '2019-02-20');
INSERT INTO `users` VALUES (13, '千代婆婆', 79, 2, '13916677335', '風影村', '風の国', '2019-01-23');
INSERT INTO `users` VALUES (14, '奇拉比', 36, 1, '13817156518', '雷影村', '雷の国', '2019-03-13');
INSERT INTO `users` VALUES (15, 'エー(叡)夜月霭', 43, 1, '13918073558', '雷影村', '雷の国', '2019-08-13');
INSERT INTO `users` VALUES (16, '由木人', 29, 2, '13917898658', '雷影村', '雷の国', '2019-04-18');
INSERT INTO `users` VALUES (17, '宇智波鼬', 21, 1, '13916872878', '木ノ叶の裏隠れの村', '火の国', '2019-08-21');
INSERT INTO `users` VALUES (18, '迪达拉', 19, 1, '13918773132', '土影村', '土の国', '2019-06-11');
INSERT INTO `users` VALUES (19, '小南', 21, 2, '13817636998', '雾隐村', '雾', '2019-05-28');
INSERT INTO `users` VALUES (20, '红莲', 31, 2, '13816135565', '音忍村', '音', '2019-07-10');
INSERT INTO `users` VALUES (21, '君麻吕', 15, 1, '13818395505', '音忍村', '音', '2019-06-19');
INSERT INTO `users` VALUES (22, '药师兜', 21, 1, '13761379558', '音忍村', '音', '2019-06-11');
INSERT INTO `users` VALUES (23, '天秤重吾', 18, 1, '13916967181', '音忍村', '音', '2019-05-31');
INSERT INTO `users` VALUES (24, '大筒木羽村', 1000, 1, '13918361985', '月球', '神', '2019-07-03');
INSERT INTO `users` VALUES (25, '大筒木羽衣', 1200, 1, '15901732222', '地球', '神', '2019-03-13');
INSERT INTO `users` VALUES (26, '因陀罗', 800, 1, '15901601111', '地球', '鬼知道', '2019-04-24');
INSERT INTO `users` VALUES (27, '阿修罗', 750, 1, '15901938882', '地球', '鬼知道', '2019-05-30');
INSERT INTO `users` VALUES (28, '大筒木辉夜', 8000, 2, '15901938881', '地球', '神他妈', '2019-01-08');
INSERT INTO `users` VALUES (30, '干柿鬼鲛', 32, 1, '15901938887', '雾隐村', '雾', '2019-06-30');
INSERT INTO `users` VALUES (31, '千手柱间', 32, 1, '15535848756', '火之国木叶忍者村', '火之国', '2019-09-02');
INSERT INTO `users` VALUES (38, '漩涡博人', 14, 1, '18221372651', '木ノ叶の裏隠れの村', '火の国', '2019-05-28');
INSERT INTO `users` VALUES (39, '佐良娜', 14, 2, '1822222651', '木ノ叶の裏隠れの村', '火の国', '2019-04-28');

SET FOREIGN_KEY_CHECKS = 1;
