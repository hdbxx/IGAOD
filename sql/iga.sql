SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for experiment
-- ----------------------------
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE `experiment` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT,
  `data` text,
  `selection_operator` int(8) DEFAULT NULL,
  `crossover_operator` int(8) DEFAULT NULL,
  `mutation_operator` int(8) DEFAULT NULL,
  `code` int(8) DEFAULT NULL,
  `size` int(8) DEFAULT NULL,
  `generation` int(8) DEFAULT NULL,
  `crossover` double(2,2) DEFAULT NULL,
  `mutation` double(2,2) DEFAULT NULL,
  `app` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
