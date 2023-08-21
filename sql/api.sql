/*
 Navicat Premium Data Transfer

 Source Server         : mysql_localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 127.0.0.1:3306
 Source Schema         : api

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 22/03/2023 19:20:17
*/

-- 创建库
create database if not exists api;

-- 切换库
use api;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求类型',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接口地址',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `request_header` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求头',
  `response_header` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '响应头',
  `status` int NOT NULL DEFAULT 0 COMMENT '接口状态（0-关闭 1-开启）',
  `user_id` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除（0-未删 1-已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES (1, 'getUserName', '获取用户名称', 'POST', 'http://localhost:8123/api/name/user', '[\n{\"name\": \"name\", \"type\": \"string\"}\n]', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 1, 1, '2023-03-16 20:41:03', '2023-03-21 09:46:01', 0);
INSERT INTO `interface_info` VALUES (2, 'getSoulSoother', '获取心灵鸡汤一条', 'GET', 'http://localhost:8123/api/soul', '无', NULL, '{\"Content-Type\": \"application/json\"}', 1, 1, '2023-03-14 21:43:20', '2023-03-21 19:32:50', 0);
INSERT INTO `interface_info` VALUES (3, 'getNickName', '获取随机昵称', 'GET', 'http://localhost:8123/api/name/nick', '无', '', '', 1, 1, '2023-03-15 18:47:05', '2023-03-22 09:55:00', 0);
INSERT INTO `interface_info` VALUES (4, 'getAvatar', '获取随机头像', 'GET', 'http://localhost:8123/api/avatar', '无', NULL, NULL, 1, 1, '2023-03-14 21:40:40', '2023-03-22 09:55:00', 0);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint NOT NULL DEFAULT 0 COMMENT '性别（0-男, 1-女）',
  `education` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学历',
  `place` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地点',
  `job` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职业',
  `contact` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `love_exp` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '感情经历',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容（个人介绍）',
  `photo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '照片地址',
  `review_status` int NOT NULL DEFAULT 0 COMMENT '状态（0-待审核, 1-通过, 2-拒绝）',
  `review_message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核信息',
  `view_num` int NOT NULL DEFAULT 0 COMMENT '浏览数',
  `thumb_num` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  `user_id` bigint NOT NULL COMMENT '创建用户 id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for soul_soother
-- ----------------------------
DROP TABLE IF EXISTS `soul_soother`;
CREATE TABLE `soul_soother`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '心灵鸡汤' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of soul_soother
-- ----------------------------
INSERT INTO `soul_soother` VALUES (1, '你要学着无论遇到什么事情，欣喜，悲伤或是濒临崩溃，都能够不动声色地自己处理事情，自己辨别对错，好好成长。');
INSERT INTO `soul_soother` VALUES (2, '与其担心未来，不如现在好好努力。这条路上，只由奋斗才能给你安全感。不要轻易把梦想寄托在某个人身上，也不要太在乎身旁的耳语，因为未来是你自己的，只有你自己才能给你自己最大的安全感。');
INSERT INTO `soul_soother` VALUES (3, '日子，要的是知足，生活，要的是幸福；生命，要的是健康；做人，要的是骨气；做事，要的是尽心；人生，要的是无悔。珍惜身边的幸福；欣赏自己的拥有。');
INSERT INTO `soul_soother` VALUES (4, '不要沮丧，不必惊慌，做努力爬的蜗牛或坚持飞的笨鸟，我们试着长大，一路跌跌撞撞，哪怕遍体鳞伤。');
INSERT INTO `soul_soother` VALUES (5, '不要跟自己过不去，不要纠结于别人的评说，照着自己舒服的感觉生活。幸福如人饮水，冷暖自知，你的幸福，不在别人眼里，而在自己心里。');
INSERT INTO `soul_soother` VALUES (6, '人生的许多痛苦，是因为你的选择错误，计较的大多，走上极端之路。我们的选择是，心灵要丰富，生活要简单，才会拥有真正的快乐。你快乐别人也快乐，才能获得心灵的安宁。');
INSERT INTO `soul_soother` VALUES (7, '自己要坚强，顽强给自己，自己要拼搏，拼搏为将来。要有自信心，自己就是这道亮丽的风景线，没必要在别人风景里边仰望。');
INSERT INTO `soul_soother` VALUES (8, '人存好心，善良为本。慈善胸怀。厚德宽人。让人感受到你的存在，这世界充满阳光，好人多好报。社会温暖，人心温暖，得道多助，路自宽行。');
INSERT INTO `soul_soother` VALUES (9, '看不到太阳，就成为太阳，成不了太阳，就追着太阳。有时候感到寸步难行，也许是你已长了翅膀，却不相信自己可以飞。请相信自己，你可以成为自己世界里的英雄。');
INSERT INTO `soul_soother` VALUES (10, '挫折会来，也会过去，热泪会流下，也会收起，没有什么可以让你气馁的。');
INSERT INTO `soul_soother` VALUES (11, '遇到困难时不要抱怨，既然改变不了过去，那么就努力改变未来。\r\n生活不是等待风暴过去，而是学会在雨中翩翩起舞。');
INSERT INTO `soul_soother` VALUES (12, '努力了的才叫梦想，不努力的就是空想！如果你一直空想的话，无论看多少正能量语录，也赶不走满满的负能量！你还是原地踏步的你，一直在看别人进步。');
INSERT INTO `soul_soother` VALUES (13, '永远不要自卑，这个世界上不如你的人多得是，永远不要自傲，这个世界上你不如的人也多得是，你要做的，就是努力让不如你的人变多，你不如的人变少。');
INSERT INTO `soul_soother` VALUES (14, '人生不能靠心情活着，而要靠心态去生活。生活中，不言弃，因为活着；不言苦，因为幸福；不言累，因为有阳光，有微笑，有温暖。没有感情，也许就没有疼痛；没有经历，也许就不懂珍惜；没有思念，也许就不知道深深的爱。');
INSERT INTO `soul_soother` VALUES (15, '一个人最好的状态，就是当你的本事配得上你的情怀，你可以脚踏实地，又可以仰望星空。');
INSERT INTO `soul_soother` VALUES (16, '再见少年，愿你前程似锦，不忘初心。再见少年，愿你洗去浮华，负铸前行。再见少年，愿你蜕变成蝶，实现所望。再见少年，愿你不负此生，归来仍是少年！');
INSERT INTO `soul_soother` VALUES (17, '遇事别先想着抱怨，笑看花开是一种好心情，静看花落也是种好境界。人生无尽的悲欢离合，不过是不同的心路。有遇见，就有分别；有惊喜，就有遗憾。与其抱怨，不如祝愿。');
INSERT INTO `soul_soother` VALUES (18, '无缘的花开，无缘的碎片，伤感人生的全世界，是思念的委屈，也是人生的泪，最后的情，只是人生的读懂，也是最后的脆弱，是命运，也是人生的坎坷。');
INSERT INTO `soul_soother` VALUES (19, '既然青葱留不住，就让白驹过隙，莫遣沧海水，再憾巫行云，道是逍遥子，佛非意马心。');
INSERT INTO `soul_soother` VALUES (20, '无论遭遇什么打击，我们都不要怀疑自己的能力，更不要与过去的失败计较。只要我们放下计较的情绪与时间，就一定能将事情做得更好。生命太短，没时间留给遗憾；若不是终点，请微笑一直向前。');
INSERT INTO `soul_soother` VALUES (21, '孤独的行走，目标是人生的尽头。路，百回干转少不了磕磕绊绊，人，百孔干面却也有知己红颜。看遍了人情冷暖，习惯了世事变迁。欣赏着沿途风景，不忘这山高水远。');
INSERT INTO `soul_soother` VALUES (22, '养好受伤的头发，照顾好桃剔的胃和爱笑的眼睛，交一个能一路废话的朋友，给自己疲惫的生活找一个温柔的梦想。');
INSERT INTO `soul_soother` VALUES (23, '无杂念则心纯，无疑虑侧心净，无对立则心平，无矛盾侧心安，无妄想则心定，无执着则心慧，无私欲则心和，无悖逆则心善。');
INSERT INTO `soul_soother` VALUES (24, '堕落也需要资格，真是振聋发聩的呐喊。爱自己的最好方式其实是努力奋斗让自己优秀起来，须知人生最宝贵的不是金钱而是一颗奋斗的心啊。');
INSERT INTO `soul_soother` VALUES (25, '学会感谢那些不想你，不能爱你，不愿爱你的人，是他们教会你如何爱自己。');
INSERT INTO `soul_soother` VALUES (26, '你可以倒下，但是要记得站起来；你可以流泪，但是要记得长大。每天多一点点的努力，不为别的，只为了日后能够多一些选择，选择云卷云舒的小日子，选择自己说了算的生活，选择自己喜欢的人。');
INSERT INTO `soul_soother` VALUES (27, '把眼泪留给最疼你的人，把微笑留给伤你最深的人。一直往前走，别往后看。顺其自然，内心就会逐渐清朗，时光越老，人心越淡。');
INSERT INTO `soul_soother` VALUES (28, '还有这世上，最不缺的就是玻璃心。你需要让自己的内心更强大，你需要让自己的能力配得上自己的梦想。');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_account` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `user_role` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `user_password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `access_key` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'accessKey',
  `secret_key` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'secretKey',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 'admin', NULL, NULL, 'admin', '9e901f5b21bf9830f2b1ec0761e6bc25', 'han', 'asdjkl', '2023-03-14 21:00:28', '2023-03-15 21:38:43', 0);
INSERT INTO `user` VALUES (2, NULL, 'yang', NULL, NULL, 'user', '9e901f5b21bf9830f2b1ec0761e6bc25', '38595f9f963505d5c25b2b79cd713c30', '5dff9682a1e2f5bc9f4f072828eae306', '2023-03-16 20:31:46', '2023-03-22 19:19:22', 0);

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '调用者 id',
  `interface_info_id` bigint NOT NULL COMMENT '接口 id',
  `total_num` int NOT NULL DEFAULT 0 COMMENT '总调用次数',
  `left_num` int NOT NULL DEFAULT 0 COMMENT '剩余调用次数',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态 0-正常 1-禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删 1-已删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------
INSERT INTO `user_interface_info` VALUES (1, 1, 1, 2, 98, 0, '2023-03-17 09:49:45', '2023-03-22 19:17:27', 0);
INSERT INTO `user_interface_info` VALUES (2, 2, 1, 0, 100, 0, '2023-03-21 15:24:26', '2023-03-22 19:17:15', 0);
INSERT INTO `user_interface_info` VALUES (3, 1, 2, 3, 97, 0, '2023-03-21 15:24:48', '2023-03-22 19:17:35', 0);
INSERT INTO `user_interface_info` VALUES (4, 1, 3, 1, 99, 0, '2023-03-21 15:25:08', '2023-03-22 19:17:40', 0);
INSERT INTO `user_interface_info` VALUES (5, 1, 4, 5, 95, 0, '2023-03-21 16:39:25', '2023-03-22 19:17:45', 0);
INSERT INTO `user_interface_info` VALUES (6, 2, 2, 0, 100, 0, '2023-03-22 19:17:59', '2023-03-22 19:17:59', 0);
INSERT INTO `user_interface_info` VALUES (7, 2, 3, 0, 100, 0, '2023-03-22 19:18:17', '2023-03-22 19:18:17', 0);
INSERT INTO `user_interface_info` VALUES (8, 2, 4, 0, 100, 0, '2023-03-22 19:18:26', '2023-03-22 19:18:26', 0);

SET FOREIGN_KEY_CHECKS = 1;
