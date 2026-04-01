CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `person_fk` int DEFAULT NULL,
  `cep` varchar(8) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `numero` varchar(10) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `complemento` varchar(150) NOT NULL,
  `logradouro` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqmyj41ysdtl0aaiq3rlwe4fov` (`person_fk`),
  CONSTRAINT `FKqmyj41ysdtl0aaiq3rlwe4fov` FOREIGN KEY (`person_fk`) REFERENCES `pessoas` (`id`)
);