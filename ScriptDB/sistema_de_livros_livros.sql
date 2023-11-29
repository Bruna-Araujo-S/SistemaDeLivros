CREATE DATABASE  IF NOT EXISTS `sistema_de_livros` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_de_livros`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistema_de_livros
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `livros`
--

DROP TABLE IF EXISTS `livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livros` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(255) NOT NULL,
  `Autor` varchar(255) NOT NULL,
  `GeneroLivro` enum('ROMANCE','FICCAO_CIENTIFICA','MISTERIO','AVENTURA','OUTRO') DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Nota` int DEFAULT NULL,
  `UsuarioAvaliadoId` int DEFAULT NULL,
  `numeroAvaliacoes` int DEFAULT '0',
  `mediaAvaliacoes` double DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (5,'Teste2','Teste','ROMANCE',89.9,6,10,7,8.5),(6,'Teste3','teste','ROMANCE',89.9,8,10,1,0),(7,'Teste Livro','Teste','AVENTURA',89.9,7,17,8,6.5),(8,'Teste','teste','ROMANCE',89.9,7,17,0,0),(9,'teste livro','teste','MISTERIO',89.9,8,26,8,0),(10,'Teste','teste','ROMANCE',89.9,6,26,0,0),(11,'Tes livro','teste','AVENTURA',89.9,8,27,0,0),(12,'Teste Final','Teste Final','AVENTURA',89.9,8,27,0,0),(13,'Teste Final','Autor','ROMANCE',89.9,6,27,0,0),(14,'Teste','Teste','ROMANCE',89.9,8,27,0,0),(15,'teste','teste','ROMANCE',89.9,10,27,0,0),(16,'Teste','teste','ROMANCE',89.9,10,27,0,0),(17,'teste','teste','ROMANCE',89.9,10,27,0,0),(18,'eeeee','eeeee','ROMANCE',89.9,10,27,0,0);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-29  2:27:46
