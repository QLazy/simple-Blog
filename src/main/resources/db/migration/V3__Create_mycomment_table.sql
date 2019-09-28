CREATE TABLE `blog`.`comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `parent_type` int(11) NOT NULL,
  `commentator` int(11) NOT NULL,
  `gmt_create` bigint(15) NOT NULL,
  `gmt_modified` bigint(15) NOT NULL,
  `like_count` int(11) NOT NULL DEFAULT 0,
  `comment_count` int(11) NOT NULL DEFAULT 0,
  `content` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
);