DROP TABLE IF EXISTS `group_buy_order`;

CREATE TABLE `group_buy_order` (
                                   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                   `team_id` varchar(8) NOT NULL COMMENT '拼单组队ID',
                                   `activity_id` bigint(8) NOT NULL COMMENT '活动ID',
                                   `source` varchar(8) NOT NULL COMMENT '渠道',
                                   `channel` varchar(8) NOT NULL COMMENT '来源',
                                   `original_price` decimal(8,2) NOT NULL COMMENT '原始价格',
                                   `deduction_price` decimal(8,2) NOT NULL COMMENT '折扣金额',
                                   `pay_price` decimal(8,2) NOT NULL COMMENT '支付价格',
                                   `target_count` int(5) NOT NULL COMMENT '目标数量',
                                   `complete_count` int(5) NOT NULL COMMENT '完成数量',
                                   `lock_count` int(5) NOT NULL COMMENT '锁单数量',
                                   `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0-拼单中、1-完成、2-失败）',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uq_team_id` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `group_buy_order` WRITE;
/*!40000 ALTER TABLE `group_buy_order` DISABLE KEYS */;

INSERT INTO `group_buy_order` (`id`, `team_id`, `activity_id`, `source`, `channel`, `original_price`, `deduction_price`, `pay_price`, `target_count`, `complete_count`, `lock_count`, `status`, `create_time`, `update_time`)
VALUES
    (7,'64675056',100123,'s01','c01',100.00,90.00,0.00,3,3,0,0,'2025-01-11 15:13:08','2025-01-11 15:13:47'),
    (8,'31724477',100123,'s01','c01',100.00,90.00,0.00,3,0,3,0,'2025-01-11 16:02:23','2025-01-11 16:06:49'),
    (9,'33063446',100123,'s01','c01',100.00,90.00,0.00,3,0,3,0,'2025-01-11 18:32:17','2025-01-11 18:33:35'),
    (10,'RNPZQnlL',100123,'s01','c01',100.00,90.00,0.00,3,0,1,0,'2025-01-11 19:15:54','2025-01-11 19:15:54'),
    (11,'tQvVMQWS',100123,'s01','c01',100.00,90.00,0.00,3,0,1,0,'2025-01-11 19:16:30','2025-01-11 19:16:30'),
    (12,'64451200',100123,'s01','c01',100.00,90.00,0.00,3,0,2,0,'2025-01-11 19:18:01','2025-01-11 19:18:38');

/*!40000 ALTER TABLE `group_buy_order` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 group_buy_order_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_buy_order_list`;

CREATE TABLE `group_buy_order_list` (
                                        `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                        `user_id` varchar(64) NOT NULL COMMENT '用户ID',
                                        `team_id` varchar(8) NOT NULL COMMENT '拼单组队ID',
                                        `order_id` varchar(12) NOT NULL COMMENT '订单ID',
                                        `activity_id` bigint(8) NOT NULL COMMENT '活动ID',
                                        `start_time` datetime NOT NULL COMMENT '活动开始时间',
                                        `end_time` datetime NOT NULL COMMENT '活动结束时间',
                                        `goods_id` varchar(16) NOT NULL COMMENT '商品ID',
                                        `source` varchar(8) NOT NULL COMMENT '渠道',
                                        `channel` varchar(8) NOT NULL COMMENT '来源',
                                        `original_price` decimal(8,2) NOT NULL COMMENT '原始价格',
                                        `deduction_price` decimal(8,2) NOT NULL COMMENT '折扣金额',
                                        `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态；0初始锁定、1消费完成',
                                        `out_trade_no` varchar(12) NOT NULL COMMENT '外部交易单号-确保外部调用唯一幂等',
                                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `uq_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `group_buy_order_list` WRITE;
/*!40000 ALTER TABLE `group_buy_order_list` DISABLE KEYS */;

INSERT INTO `group_buy_order_list` (`id`, `user_id`, `team_id`, `order_id`, `activity_id`, `start_time`, `end_time`, `goods_id`, `source`, `channel`, `original_price`, `deduction_price`, `status`, `out_trade_no`, `create_time`, `update_time`)
VALUES
    (9,'xiaofuge','64675056','394859646148',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'314870877261','2025-01-11 15:13:08','2025-01-11 15:13:08'),
    (10,'xiaofuge','64675056','141036625362',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'485524644458','2025-01-11 15:13:35','2025-01-11 15:13:35'),
    (11,'xiaofuge','64675056','686664095344',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'942065764153','2025-01-11 15:13:47','2025-01-11 15:13:47'),
    (12,'xiaofuge','31724477','755647827138',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'782446346690','2025-01-11 16:02:23','2025-01-11 16:02:23'),
    (13,'xiaofuge','31724477','356055747145',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'648016222817','2025-01-11 16:06:27','2025-01-11 16:06:27'),
    (14,'xiaofuge','31724477','831212748893',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'494004780611','2025-01-11 16:06:49','2025-01-11 16:06:49'),
    (15,'xiaofuge','33063446','391398265383',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'285608029258','2025-01-11 18:32:18','2025-01-11 18:32:18'),
    (16,'xiaofuge','33063446','479801370153',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'561239558595','2025-01-11 18:33:13','2025-01-11 18:33:13'),
    (17,'xiaofuge','33063446','371204389323',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'987071087883','2025-01-11 18:33:35','2025-01-11 18:33:35'),
    (18,'xiaofuge','RNPZQnlL','934890879756',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'628931704712','2025-01-11 19:15:54','2025-01-11 19:15:54'),
    (19,'xiaofuge','tQvVMQWS','321326266333',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'445825049409','2025-01-11 19:16:30','2025-01-11 19:16:30'),
    (20,'xiaofuge','64451200','028993411110',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'911193728147','2025-01-11 19:18:01','2025-01-11 19:18:01'),
    (21,'xiaofuge','64451200','950295523573',100123,'2024-12-07 10:19:40','2024-12-07 10:19:40','9890001','s01','c01',90.00,90.00,0,'054537636261','2025-01-11 19:18:38','2025-01-11 19:18:38');

/*!40000 ALTER TABLE `group_buy_order_list` ENABLE KEYS */;
UNLOCK TABLES;