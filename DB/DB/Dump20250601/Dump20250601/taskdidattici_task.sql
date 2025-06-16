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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(45) DEFAULT NULL,
  `descrizione` varchar(150) DEFAULT NULL,
  `dataScadenza` varchar(10) DEFAULT NULL,
  `maxPuntiAssegnabili` int DEFAULT NULL,
  `classe_codice` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task_classe1_idx` (`classe_codice`),
  CONSTRAINT `fk_task_classe1` FOREIGN KEY (`classe_codice`) REFERENCES `classi` (`codice`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Leg old.','Language such five whose stand allow.','2025-02-22',8,1),(2,'Fight century night.','Today remain evening end network understand get early. Ago kind or.','2025-05-07',5,1),(3,'Floor this reveal.','Medical carry into very study line. Senior water Democrat tough agent usually trip position.','2025-05-02',6,1),(4,'Investment rise message.','Specific than peace least. Stuff oil care quickly finally pay.','2025-02-06',10,2),(5,'They.','President like foreign large what actually.','2025-04-20',7,2),(6,'Perform necessary dinner.','Enough hold determine voice general bed. Cause health American.','2025-05-15',7,2),(7,'Stop gas.','Guess although worry after once off.\nGo its child into source owner career.','2025-04-07',10,3),(8,'Attorney how goal.','Hair important study stop up safe. Actually author it daughter.','2025-05-07',8,3),(9,'Test record score.','Just nothing notice offer major exactly. Continue discuss stop whom myself study.','2025-01-07',5,3),(10,'Realize choice.','Three do really serve environmental. Second analysis particularly under throughout campaign enjoy.','2025-02-05',10,4),(11,'Seek with baby.','Behavior believe wind yard pull perform. Film all people likely option.','2025-03-04',6,4),(12,'Talk back.','Attention what it final news either. Hot material public herself. Total event lawyer not boy.','2025-05-31',7,4);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
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
