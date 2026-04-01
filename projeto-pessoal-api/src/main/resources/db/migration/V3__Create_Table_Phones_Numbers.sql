CREATE TABLE IF NOT EXISTS `phones_numbers` (
  `person_id` int NOT NULL,
  `phones_number` varchar(255) DEFAULT NULL,
  KEY `FKhp03xgp9hk7kchddsy8fnwq93` (`person_id`),
  CONSTRAINT `FKhp03xgp9hk7kchddsy8fnwq93` FOREIGN KEY (`person_id`) REFERENCES `pessoas` (`id`)
);
