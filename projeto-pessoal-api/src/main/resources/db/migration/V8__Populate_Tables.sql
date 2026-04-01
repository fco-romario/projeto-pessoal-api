START TRANSACTION;

INSERT INTO `pessoas` (`gender`, `id`, `created_at`, `created_by`, `updated_at`, `updated_by`, `cpf`, `rg`, `name`, `email`, `mathers_name`) VALUES
	(1, 1, '2026-03-04 01:17:32.073313', NULL, '2026-03-04 01:17:32.073313', NULL, '11122233344', '99988877744', 'Romário Alves de Lima', 'test@gmail.com', 'Maria alves');

INSERT INTO `phones_numbers` (`person_id`, `phones_number`) VALUES
	(1, '85900001111'),
	(1, '85900002222');

INSERT INTO `addresses` (`id`, `person_fk`, `cep`, `created_at`, `created_by`, `updated_at`, `updated_by`, `numero`, `bairro`, `complemento`, `logradouro`) VALUES
	(1, 1, '41180083', '2026-03-04 01:17:32.177820', NULL, '2026-03-04 01:17:32.371783', NULL, '15b', 'Saboeiro', 'Proximo a farmácia', 'Rua da Lagoa'),
	(2, 1, '49096267', '2026-03-04 01:17:32.187829', NULL, '2026-03-04 01:17:32.373921', NULL, '1000b', 'Jabotiana', 'Proximo a padaria', 'Rua N');

INSERT INTO `courses` (`category`, `id`, `person_fk`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`, `name`, `url`) VALUES
	(1, 1, 1, 1, '2026-03-04 01:17:32.191827', NULL, '2026-03-04 01:17:32.411744', NULL, 'Curso de Angular para iniciantes', 'https://www.youtube.com'),
	(2, 2, 1, 2, '2026-03-04 01:17:32.202829', NULL, '2026-03-04 01:17:32.412748', NULL, 'Curso de Java para iniciantes', 'https://www.youtube.com');

INSERT INTO `permissao` (`id`, `description`) VALUES
	(1, 'ADMIN'),
	(2, 'MANAGER'),
	(3, 'COMMON_USER');

INSERT INTO `users` (`account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `person_id`, `id`, `password`, `user_name`) VALUES
	(b'1', b'1', b'1', b'1', 1, 1, '{pbkdf2}9be08e09a38c9a60ae4f75efa836c58b32a31f61b695f22ad2c9543895d589d97c2c0a868abfa4ae', 'rom');

INSERT INTO `user_permission` (`id_permission`, `id_user`) VALUES
	(1, 1);

COMMIT;