CREATE TABLE `blog`.`notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `outer_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `gmt_create` bigint(15) NOT NULL,
  `status` int(11) NOT NULL,
  `notifier_name` varchar(20) NOT NULL,
  `outer_title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);