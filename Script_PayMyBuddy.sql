-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: pay_my_buddy
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `iban` varchar(27) NOT NULL,
  `bic` varchar(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bankaccount_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
INSERT INTO `bankaccount` VALUES (12,1,'FR6666666666666666666666666','55','actif');
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datetransaction` date NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `amount` double NOT NULL,
  `fees` double NOT NULL,
  `user_sender_id` int NOT NULL,
  `user_receiver_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_sender_id` (`user_sender_id`),
  KEY `user_receiver_id` (`user_receiver_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_sender_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`user_receiver_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (56,'2023-06-05','test41',1,0.005,1,25),(57,'2023-06-05','test42',1,0.005,1,25),(58,'2023-06-05','test 44',10,0.05,1,25),(59,'2023-06-05','test',10,0.05,1,25),(60,'2023-06-06','new test',10,0.05,1,27),(61,'2023-06-06','new test2',100,0.5,1,27),(62,'2023-06-06','test',2,0.01,1,29),(63,'2023-06-07','test fees',10,0.05,1,29),(64,'2023-06-07','test fees',20,0.1,1,29),(65,'2023-06-07','test fees2',10,0.05,1,29),(66,'2023-06-07','test fees 3',10,0.05,1,29),(67,'2023-06-07','test fees4',10,0.05,1,29),(68,'2023-06-08','test ',12,0.06,1,25),(69,'2023-06-08','test 2',10,0.05,1,25),(70,'2023-06-09','test decimal',10,0.05,1,24),(71,'2023-06-09','test 2 decimal',10,0.05,1,24),(72,'2023-06-09','test 3 decimal',10,0.05,1,24),(73,'2023-06-09','test 3 decimal',10,0.05,1,24),(74,'2023-06-09','test decimal final',33,0.165,1,24),(75,'2023-06-09','test final',44,0.22,1,24),(76,'2023-06-09','test final final',33,0.165,1,24),(77,'2023-06-09',NULL,13,0.065,1,24),(78,'2023-06-09',NULL,33,0.165,1,24),(79,'2023-06-09','final',44,0.22,1,24),(80,'2023-06-09',NULL,10,0.05,1,24),(94,'2023-06-13','final test',11,0.055,1,24);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_with_bankaccount`
--

DROP TABLE IF EXISTS `transaction_with_bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_with_bankaccount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int NOT NULL,
  `bankaccount_id` int NOT NULL,
  `origin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `bankaccount_id` (`bankaccount_id`),
  CONSTRAINT `transaction_with_bankaccount_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `transaction_with_bankaccount_ibfk_2` FOREIGN KEY (`bankaccount_id`) REFERENCES `bankaccount` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_with_bankaccount`
--

LOCK TABLES `transaction_with_bankaccount` WRITE;
/*!40000 ALTER TABLE `transaction_with_bankaccount` DISABLE KEYS */;
INSERT INTO `transaction_with_bankaccount` VALUES (11,20,'2023-06-08','Test transfer to bankaccount',1,12,1),(12,10,'2023-06-08','test to take money from my bank',1,12,0),(21,10,'2023-06-13','test final',1,12,0);
/*!40000 ALTER TABLE `transaction_with_bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `first_name` varchar(250) DEFAULT NULL,
  `last_name` varchar(250) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John@hotmail.fr','John','DUTTON',747.68,'jjjj'),(2,'DD@gmail.fr','Didier','MULLER',28,'dddd'),(24,'Beth@hotmail.fr','Beth','Dutton',261,'bbbb'),(25,'meyer@gmail.com','Jaque','Meyer',72,'mmmm'),(26,'wolf@gmail.com','Marcel','Wolf',0,'wwww'),(27,'bard@hotmail.fr','Lucas','BARD',218,'bbbb'),(28,'PAYMYBUDDY','PAYMYBUDDYOFFICIEL','PAYMYBUDDYOFFICIEL',0.2,'pppp'),(29,'will@gmail.fr','Will','Turner',62,'wwww');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_friends`
--

DROP TABLE IF EXISTS `user_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_friends` (
  `user_id` int NOT NULL,
  `friends_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`friends_id`),
  KEY `friends_id` (`friends_id`),
  CONSTRAINT `user_friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_friends_ibfk_2` FOREIGN KEY (`friends_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friends`
--

LOCK TABLES `user_friends` WRITE;
/*!40000 ALTER TABLE `user_friends` DISABLE KEYS */;
INSERT INTO `user_friends` VALUES (29,1);
/*!40000 ALTER TABLE `user_friends` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-13 15:38:00
