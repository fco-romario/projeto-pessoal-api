CREATE TABLE IF NOT EXISTS `courses` (
  `category` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `person_fk` int DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `url` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKblh2l7wnekl778vo9bmpq8lop` (`person_fk`),
  CONSTRAINT `FKblh2l7wnekl778vo9bmpq8lop` FOREIGN KEY (`person_fk`) REFERENCES `pessoas` (`id`)
);
