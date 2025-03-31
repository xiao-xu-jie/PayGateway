/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : paygateway

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 01/03/2025 20:50:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notify_log
-- ----------------------------
DROP TABLE IF EXISTS `notify_log`;
CREATE TABLE `notify_log`  (
  `id` bigint NOT NULL,
  `open_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单号',
  `site_appid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点appID',
  `notifu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '通知地址',
  `res_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '站点返回体',
  `current_count` int NOT NULL DEFAULT 1 COMMENT '当前第几次通知',
  `notify_type` int NOT NULL COMMENT '通知方式 1/自动 2/手动',
  `notify_status` int NOT NULL COMMENT '是否成功',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for outbox_message
-- ----------------------------
DROP TABLE IF EXISTS `outbox_message`;
CREATE TABLE `outbox_message`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `aggregate_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聚合根类型，如Order、Product等',
  `aggregate_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务实体ID，如订单号',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '事件类型，如OrderPaidEvent',
  `payload` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '消息内容/负载',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `priority` int NOT NULL DEFAULT 0 COMMENT '消息优先级，数字越大优先级越高',
  `processed` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已处理',
  `processed_at` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '处理错误信息',
  `retry_count` int NOT NULL DEFAULT 0 COMMENT '重试次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_processed_priority_created_at`(`processed` ASC, `priority` DESC, `created_at` ASC) USING BTREE,
  INDEX `idx_aggregate_type_id`(`aggregate_type` ASC, `aggregate_id` ASC) USING BTREE,
  INDEX `idx_type`(`type` ASC) USING BTREE,
  INDEX `idx_retry_count`(`retry_count` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '事务性消息发送箱表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for site_info
-- ----------------------------
DROP TABLE IF EXISTS `site_info`;
CREATE TABLE `site_info`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `site_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点名称',
  `site_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点描述',
  `notify_type` int NOT NULL DEFAULT 0 COMMENT '回调类型',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '回调URL',
  `site_appid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点ID',
  `site_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点SEC',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `site_appid`(`site_appid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for site_order
-- ----------------------------
DROP TABLE IF EXISTS `site_order`;
CREATE TABLE `site_order`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `transaction_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '第三方ID',
  `open_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '平台统一NO',
  `trade_no` bigint NOT NULL COMMENT '站点内NO',
  `site_appid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '站点ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单标题',
  `order_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单描述',
  `real_fee` decimal(10, 2) NOT NULL COMMENT '实际金额',
  `discount` decimal(10, 2) NOT NULL DEFAULT 1.00,
  `total_fee` decimal(10, 2) NOT NULL COMMENT '总金额',
  `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '支付通道',
  `url_qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `json_data` json NULL,
  `create_time` datetime NOT NULL,
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `update_time` datetime NOT NULL,
  `order_status` int NOT NULL COMMENT '订单状态',
  `notify_status` int NULL DEFAULT NULL COMMENT '通知状态',
  `is_delete` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `site_order`(`trade_no` ASC, `site_appid` ASC) USING BTREE,
  UNIQUE INDEX `open_no`(`open_no` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
