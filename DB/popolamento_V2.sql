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
-- Table structure for table `classi`
--

DROP TABLE IF EXISTS `classi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classi` (
  `codice` int NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `numeroTask` int DEFAULT NULL,
  `docente_id` int DEFAULT NULL,
  PRIMARY KEY (`codice`),
  KEY `fk_classe_docente1_idx` (`docente_id`),
  CONSTRAINT `fk_classe_docente1` FOREIGN KEY (`docente_id`) REFERENCES `docenti` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classi`
--

LOCK TABLES `classi` WRITE;
/*!40000 ALTER TABLE `classi` DISABLE KEYS */;
INSERT INTO `classi` VALUES (1001,'Company',3,2),(1002,'Discussion',3,3),(1003,'Employee',3,1),(1004,'Reflect',3,2);
/*!40000 ALTER TABLE `classi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consegne`
--

DROP TABLE IF EXISTS `consegne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegne` (
  `id` int NOT NULL,
  `punteggio` int DEFAULT NULL,
  `soluzione` blob,
  `task_id` int NOT NULL,
  `studente_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_consegna_task1_idx` (`task_id`),
  KEY `fk_consegna_studente1_idx` (`studente_id`),
  CONSTRAINT `fk_consegna_studente1` FOREIGN KEY (`studente_id`) REFERENCES `studenti` (`id`),
  CONSTRAINT `fk_consegna_task1` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consegne`
--

LOCK TABLES `consegne` WRITE;
/*!40000 ALTER TABLE `consegne` DISABLE KEYS */;
INSERT INTO `consegne` VALUES (1,0,_binary 'Expect network piece candidate red law employee.',5,1),(2,6,_binary 'Trial discussion heart the west indicate.',4,1),(3,6,_binary 'Nation money cause always doctor.',10,2),(4,2,_binary 'Green world protect.',11,2),(5,7,_binary 'Material or speech risk rise themselves hard.',8,3),(6,3,_binary 'Goal through situation according.',7,3),(7,6,_binary 'Oil think radio language.',7,4),(8,10,_binary 'Than later ability employee daughter.',8,4),(9,1,_binary 'Drive hundred simply prove which.',6,5),(10,3,_binary 'Know fact score. Note deep head during start his.',4,5),(11,2,_binary 'Item interview common sometimes.',5,6),(12,4,_binary 'Which already huge can. Without statement out.',6,6),(13,2,_binary 'Book seat share this. Especially away on.',11,7),(14,8,_binary 'President memory civil.',10,7),(15,4,_binary 'Could who direction traditional.',3,8),(16,5,_binary 'Girl find perform walk which arm camera.',1,8),(17,1,_binary 'Own machine describe agree mention husband.',6,9),(18,2,_binary 'Lead soon cost trouble detail. Major for himself.',4,9),(19,4,_binary 'Start statement make doctor majority enough.',7,10),(20,0,_binary 'Accept attack war.',9,10),(21,2,_binary 'Remember catch knowledge because.',7,11),(22,7,_binary 'Special public help firm bag.',9,11),(23,2,_binary 'Staff tell act. Across international sport lose.',6,12),(24,3,_binary 'Manager ground night five. His still heavy none.',5,12);
/*!40000 ALTER TABLE `consegne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docenti`
--

DROP TABLE IF EXISTS `docenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docenti` (
  `id` int NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docenti`
--

LOCK TABLES `docenti` WRITE;
/*!40000 ALTER TABLE `docenti` DISABLE KEYS */;
INSERT INTO `docenti` VALUES (1,'Cameron','Leonard','joanne86@example.org','%5fBpDfLu6'),(2,'Martin','Oneill','zwilliams@example.com','5L2+#Ln&y)'),(3,'Christopher','Long','amandaball@example.org','@9yGHpYG1o');
/*!40000 ALTER TABLE `docenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenti` (
  `id` int NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
INSERT INTO `studenti` VALUES (1,'Yesenia','Oliver','amanda87@example.com','N+2EvXifFn',2,2,6,1002),(2,'Nicole','Terry','katiesnyder@example.com','@e9WoM+J*)',2,2,8,1004),(3,'Paul','Castillo','rachel37@example.net','bCfS04jrF&',2,2,10,1003),(4,'Devon','Ruiz','qmoore@example.org','&4TXKypO3(',2,2,16,1003),(5,'Robert','Carson','tiffanysullivan@example.net','%1XqYVrFq#',2,2,4,1002),(6,'John','Kelley','elizabethmorgan@example.com','9Y*2XJOl6W',2,2,6,1002),(7,'Randall','Giles','amanda14@example.com','Y*074)Fr#K',2,2,10,1004),(8,'Danielle','Harper','ymartin@example.com','j9SUVDYr#+',2,2,9,1001),(9,'Paul','Hill','charles12@example.com','g89Z%@Lhe#',2,2,3,1002),(10,'Kelly','Kemp','rsolomon@example.org','@2$!AoUwbb',2,2,4,1003),(11,'Christopher','Lucas','heathermueller@example.org','Pf5Rz8om(&',2,2,9,1003),(12,'Robert','Warren','william34@example.com','qr0klMhc$7',2,2,5,1002);
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` int NOT NULL,
  `titolo` varchar(45) DEFAULT NULL,
  `descrizione` varchar(150) DEFAULT NULL,
  `dataScadenza` varchar(10) DEFAULT NULL,
  `maxPuntiAssegnabili` int DEFAULT NULL,
  `classe_codice` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task_classe1_idx` (`classe_codice`),
  CONSTRAINT `fk_task_classe1` FOREIGN KEY (`classe_codice`) REFERENCES `classi` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Inside peace.','Today focus history fill word line dream open. Avoid safe central off Mrs later push deep.','2025-05-19',10,1001),(2,'Able.','House former create party day budget should. Hold whose nothing chair ten free.','2025-04-29',9,1001),(3,'Along lead.','Message possible food southern. Subject country today. Force understand financial reality base.','2025-04-26',6,1001),(4,'Again account risk.','Moment risk address first business everyone kid fast. Let certain candidate.','2025-03-14',7,1002),(5,'Know right.','Check paper out three. Decision big identify rest ten station health.','2025-03-22',6,1002),(6,'Federal however.','Position relate water them. Memory body site weight.','2025-04-19',6,1002),(7,'Indicate care sometimes.','Up describe trial page down however significant blue. Lot result executive black expect investment.','2025-02-07',8,1003),(8,'Buy interest sure.','Civil development great.\nInclude probably appear usually clearly.','2025-01-27',10,1003),(9,'Senior movie enough.','Religious include threat defense clear word many. Record education agent.','2025-05-09',7,1003),(10,'Treatment several.','Almost then paper family. Pretty financial admit own result instead.','2025-05-09',9,1004),(11,'Blue address.','Foot image type maintain manage. Dog support eye move industry heart stand.','2025-02-23',5,1004),(12,'Commercial recognize.','Action air me month answer. Vote quality suffer but draw financial sign.','2025-01-08',8,1004);
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

-- Dump completed on 2025-05-22 13:18:52
