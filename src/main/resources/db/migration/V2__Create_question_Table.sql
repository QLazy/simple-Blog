CREATE TABLE `blog`.`question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NULL,
  `description` varchar(2048) NULL,
  `creator` int(11) NOT NULL,
  `gmt_create` bigint(15) NULL,
  `gmt_modified` bigint(15) NULL,
  `comment_count` int(11) NOT NULL DEFAULT 0,
  `like_count` int(11) NOT NULL DEFAULT 0,
  `view_count` int(11) NOT NULL DEFAULT 0,
  `tag` varchar(100) NULL,
  PRIMARY KEY (`id`)
);