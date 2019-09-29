CREATE TABLE `blog`.`myUser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `token` varchar(100) NOT NULL,
  `avatar_url` varchar(200) NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);
