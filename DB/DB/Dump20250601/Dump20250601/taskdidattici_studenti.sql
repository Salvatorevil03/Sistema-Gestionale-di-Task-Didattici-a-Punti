-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: taskdidattici
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `numTaskCompletati` int DEFAULT NULL,
  `numTaskValutati` int DEFAULT NULL,
  `punteggioTotaleOttenuto` int DEFAULT NULL,
  `classe_codice` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  KEY `fk_studente_classe1_idx` (`classe_codice`),
  CONSTRAINT `fk_studente_classe1` FOREIGN KEY (`classe_codice`) REFERENCES `classi` (`codice`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
INSERT INTO `studenti` VALUES (1,'Daniel','Strickland','stephensjennifer@example.net','!ac8LtTxM#',2,2,12,4),(2,'Gabriel','Kramer','justin14@example.org',')iIm^ydl52',2,2,6,4),(3,'Jared','Henderson','leblancpeter@example.net','@ps4t1Em#O',2,2,5,1),(4,'Jennifer','Salinas','greyes@example.com','_^9aF+Ke5U',2,2,4,1),(5,'James','Foster','samantha09@example.net','dw@$2AYkS!',2,2,17,4),(6,'Sherry','Smith','kathrynmartin@example.org','%&06LN6u2V',2,2,4,3),(7,'Joseph','Curry','thomasebony@example.net','Rgw3RoEpr$',2,2,3,3),(8,'Thomas','Delgado','lauramoore@example.com','lljbMOYp$7',2,2,7,1),(9,'Leah','Haney','william43@example.com','x)u(1eazCE',2,2,3,4),(10,'Willie','Mitchell','cynthia12@example.com','(kkLaKdkv9',2,2,3,3),(11,'Joseph','Martin','anthony49@example.net','!4%PvzNNME',2,2,9,3),(12,'Courtney','Bush','vegajoshua@example.org','qU4NiIaiL+',2,2,7,2);
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-01 17:19:24
