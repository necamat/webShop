
--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` VALUES (7);



--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `user_profile`
--


INSERT INTO `user_profile` VALUES (1,'ADMIN'),(3,'EMPMENAGER'),(5,'SHOPMENAGER'),(2,'USER'),(4,'WARMENAGER');

--
-- Table structure for table `user_user_profile`
--

DROP TABLE IF EXISTS `user_user_profile`;

CREATE TABLE `user_user_profile` (
  `user_id` bigint NOT NULL,
  `user_profile_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'admin','$2a$10$94FgHW3muCFTDS4ipVhnm.Z1W1/v2m1PUyYT8LhLW0BJpF.3pbt7K','Nemanja','Matovic','neca@gmai.com','Active');

--
-- Dumping data for table `user_user_profile`
--
INSERT INTO `test_assigment_web`.`user_user_profile` (`user_id`, `user_profile_id`) VALUES ('1', '1');

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `PRODUCT_NUM_ID` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(150) NOT NULL,
  `PHOTO_NAME` varchar(145) DEFAULT NULL,
  `PRICE` double NOT NULL,
  `QUANTITY` int NOT NULL,
  PRIMARY KEY (`PRODUCT_NUM_ID`),
  UNIQUE KEY `PRODUCT_NUM_ID_UNIQUE` (`PRODUCT_NUM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `order_pr`
--

DROP TABLE IF EXISTS `order_pr`;

CREATE TABLE `order_pr` (
  `ORDER_NUM_ID` varchar(255) NOT NULL,
  `DATETIME_CREATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DATETIME_UPDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `STATE` varchar(255) NOT NULL,
  `USER_ID` bigint NOT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ORDER_NUM_ID`),
  UNIQUE KEY `ORDER_NUM_ID_UNIQUE` (`ORDER_NUM_ID`),
  KEY `fk_user_idx` (`USER_ID`),
  KEY `fk_user_orderpr_idx` (`USER_ID`),
  CONSTRAINT `fk_user_orderpr` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;

CREATE TABLE `order_details` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `QUANTITY` int NOT NULL,
  `ORDER_NUM_ID` varchar(255) NOT NULL,
  `PRODUCT_NUM_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_8hv670ptsypn4v16hskxyse5y` (`ORDER_NUM_ID`),
  KEY `FK_r9mmsid6gvtoifv0ag61spbpt` (`PRODUCT_NUM_ID`),
  CONSTRAINT `FK_8hv670ptsypn4v16hskxyse5y` FOREIGN KEY (`ORDER_NUM_ID`) REFERENCES `order_pr` (`ORDER_NUM_ID`),
  CONSTRAINT `FK_r9mmsid6gvtoifv0ag61spbpt` FOREIGN KEY (`PRODUCT_NUM_ID`) REFERENCES `product` (`PRODUCT_NUM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Dodavanje dva proizvoda, cije se slike nalaze u projektu da bi se dobio lesi prikaz u Assigment. Stvorila jedna celina sa pocetnom stranicom.
--
INSERT INTO `test_assigment_web`.`product` (`PRODUCT_NUM_ID`, `DESCRIPTION`, `NAME`, `PHOTO_NAME`, `PRICE`, `QUANTITY`) VALUES ('PR00001', 'Siva trenerka', 'Trenerka', 'PR00001_12.jpg', '5000', '100');
INSERT INTO `test_assigment_web`.`product` (`PRODUCT_NUM_ID`, `DESCRIPTION`, `NAME`, `PHOTO_NAME`, `PRICE`, `QUANTITY`) VALUES ('PR00002', 'Majica kratkih rukava', 'Majica', 'PR00002_1.jpg', '1000', '10');















