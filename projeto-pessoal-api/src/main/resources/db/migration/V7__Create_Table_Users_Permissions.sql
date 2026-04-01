CREATE TABLE IF NOT EXISTS `user_permission` (
  `id_permission` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  KEY `FK4bl1ecn56jrvhrmsl2bi6wwt0` (`id_permission`),
  KEY `FKprpp02ivhe66b5nrc0a3a4lk8` (`id_user`),
  CONSTRAINT `FK4bl1ecn56jrvhrmsl2bi6wwt0` FOREIGN KEY (`id_permission`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKprpp02ivhe66b5nrc0a3a4lk8` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
);

