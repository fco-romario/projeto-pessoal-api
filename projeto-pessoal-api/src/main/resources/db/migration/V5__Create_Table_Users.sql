CREATE TABLE IF NOT EXISTS `users` (
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `person_id` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk8d0f2n7n88w1a16yhua64onx` (`user_name`),
  UNIQUE KEY `UK97ih1g5lcdf1s3fg7oo4e18jw` (`person_id`),
  CONSTRAINT `FKjgy256pjxk3hr5md1prjnpiyf` FOREIGN KEY (`person_id`) REFERENCES `pessoas` (`id`)
);

