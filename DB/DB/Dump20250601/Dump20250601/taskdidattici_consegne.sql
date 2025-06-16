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
-- Table structure for table `consegne`
--

DROP TABLE IF EXISTS `consegne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `punteggio` int DEFAULT NULL,
  `soluzione` blob,
  `task_id` int NOT NULL,
  `studente_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_consegna_task1_idx` (`task_id`),
  KEY `fk_consegna_studente1_idx` (`studente_id`),
  CONSTRAINT `fk_consegna_studente1` FOREIGN KEY (`studente_id`) REFERENCES `studenti` (`id`),
  CONSTRAINT `fk_consegna_task1` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE taskdidattici.consegne 
ADD CONSTRAINT unique_studente_task UNIQUE (task_id, studente_id);

--
-- Dumping data for table `consegne`
--

LOCK TABLES `consegne` WRITE;
/*!40000 ALTER TABLE `consegne` DISABLE KEYS */;
INSERT INTO `consegne` VALUES (1,4,_binary 'Number support sport think reach.',12,1),(2,8,_binary 'Culture mouth help several.',10,1),(3,5,_binary 'Care door him national eat learn focus.',11,2),(4,1,_binary 'Probably ask try economic peace.',12,2),(5,4,_binary 'Work paper son.',1,3),(6,1,_binary 'Office top worry garden.',2,3),(7,4,_binary 'Wall officer alone.',1,4),(8,0,_binary 'Partner story structure fill.',2,4),(9,10,_binary 'Computer shoulder try wait win day.',10,5),(10,7,_binary 'Skill end cause many back.',12,5),(11,2,_binary 'Land north enough hear.',8,6),(12,2,_binary 'Let begin fund cause pretty.',9,6),(13,3,_binary 'Me power past technology condition.',8,7),(14,0,_binary 'Ground tell activity production room term see.',9,7),(15,2,_binary 'Individual often spring class reflect.',2,8),(16,5,_binary 'Including per yet pattern enough.',1,8),(17,2,_binary 'Price already oil.',12,9),(18,1,_binary 'Ball ask during sport.',11,9),(19,3,_binary 'President rise three bill add.',9,10),(20,0,_binary 'Service manage Mr specific.',8,10),(21,0,_binary 'Sing gun possible order time always threat.',8,11),(22,9,_binary 'Strategy wrong down citizen issue player.',7,11),(23,6,_binary 'Job little add talk citizen.',4,12),(24,1,_binary 'Responsibility yes throw character give.',5,12);
/*!40000 ALTER TABLE `consegne` ENABLE KEYS */;
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
