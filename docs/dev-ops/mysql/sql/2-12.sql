DROP TABLE IF EXISTS `notify_task`;

CREATE TABLE `notify_task` (
                               `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                               `activity_id` bigint(8) NOT NULL COMMENT '活动ID',
                               `team_id` varchar(8) NOT NULL COMMENT '拼单组队ID',
                               `notify_url` varchar(128) NOT NULL COMMENT '回调接口',
                               `notify_count` int(8) NOT NULL COMMENT '回调次数',
                               `notify_status` tinyint(1) NOT NULL COMMENT '回调状态【0初始、1完成、2重试、3失败】',
                               `parameter_json` varchar(256) NOT NULL COMMENT '参数对象',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `notify_task` WRITE;
/*!40000 ALTER TABLE `notify_task` DISABLE KEYS */;

INSERT INTO `notify_task` (`id`, `activity_id`, `team_id`, `notify_url`, `notify_count`, `notify_status`, `parameter_json`, `create_time`, `update_time`)
VALUES
    (1,100123,'46832479','暂无',0,0,'{\"teamId\":\"46832479\",\"outTradeNoList\":[\"581909866926\",\"155123092895\",\"451517755304\"]}','2025-01-26 19:11:46','2025-01-26 19:11:46');

/*!40000 ALTER TABLE `notify_task` ENABLE KEYS */;
UNLOCK TABLES;
