-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: order_trading
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_trading`
--

DROP TABLE IF EXISTS `order_trading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_trading` (
  `id` int NOT NULL AUTO_INCREMENT,
  `side` smallint DEFAULT NULL,
  `stock_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `amount` int DEFAULT NULL,
  `order_price` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `order_sign` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account_id` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) CHARACTER SET armscii8 COLLATE armscii8_general_ci DEFAULT NULL COMMENT 'order_type: Loại lệnh là LO, MP, ATC.',
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trigger_conditions` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `channel` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `order_history`;
CREATE TABLE `order_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `stock_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `order_price` decimal(2,0) DEFAULT NULL COMMENT 'giá đặt',
  `amount` int DEFAULT NULL COMMENT 'khối lượng đặt lệnh',
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'order_type: Loại lệnh là LO, MP, ATC.',
  `side` smallint DEFAULT NULL COMMENT 'bên mua , bán',
  `trading_date` datetime DEFAULT NULL,
  `match_price` decimal(2,0) DEFAULT NULL,
  `match_value` decimal(2,0) DEFAULT NULL,
  `match_vol` int DEFAULT NULL,
  `channel` int DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'trạng thái lệnh. CHO_KHOP, DA_HUY, DA_KHOP, HET_HIEU_LUC,\n    DA_DAT, DA_SUA, KHOP_HET, HOAN_TAT',
  `no_order` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'số hiệu lệnh. để giao tiếp với bên ngoài',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_trading`
--

LOCK TABLES `order_trading` WRITE;
/*!40000 ALTER TABLE `order_trading` DISABLE KEYS */;
INSERT INTO `order_trading` VALUES (1,0,'OIL',100,'10900','2023-07-13 21:24:44','CN','100','LO','WAIT',NULL,NULL),(2,1,'CEO',200,'24000','2023-07-13 21:25:30','CN','101','LO','WAIT',NULL,NULL),(3,0,'FPT',100,'10900','2023-07-13 22:39:18','CN','100','LO','WAIT',NULL,NULL);
/*!40000 ALTER TABLE `order_trading` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-16  4:51:35
