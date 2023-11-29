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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `idade` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nivel_acesso` enum('NORMAL','ADMIN') NOT NULL DEFAULT 'ADMIN',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'Nome do Administrador',25,'admin@example.com','senha123','ADMIN'),(2,'Teste',25,'admin@example.com','SenhaSenha24@','ADMIN'),(3,'Teste',78,'teste@teste.com','TesteTeste','NORMAL'),(4,'Teste Teste',35,'','TesteParaver01@','NORMAL'),(5,'Teste Teste',35,'','TesteParaver01@','NORMAL'),(6,'Bruna Teste2',87,'','BrunaTeste09@ ','NORMAL'),(7,'TesteTeste3',76,'','Testetesteer@2','NORMAL'),(8,'TesteTeste4',76,'','Testeteste09@','NORMAL'),(9,'Testeteste3',65,'','Testetesteoi09@','NORMAL'),(10,'Teste5',88,'teste5@teste.com','TestetestetetetW2@','NORMAL'),(11,'Teste',89,'teste#gmail.com','Testetets09@','NORMAL'),(12,'Teste',89,'teste@gmail.com','$2a$10$uufSLoe2Rxk2SmO2k0sZM.2X924jYpf365fWePVxeq/XzZuQyLrx6','NORMAL'),(13,'Teste',78,'teste89@gmail.com','$2a$10$HRMBss.QmeIOOK9q.zw.uu3UrbkS0UyMwPB5pmuM3M0iHkSFyF4oC','NORMAL'),(14,'Teste',78,'teste6@gmail.com','$2a$10$seGK4cABHemm86MKwfoLBen34VUpDeedqdbLX0gca0m/JpzOmQzs2','NORMAL'),(15,'teste',78,'teste@gmail.com','$2a$10$aMQyhH8fF./Bs9bK8Ax6dumyxl1ZbVyAmfulUKcWb0qjex.ujIDse','NORMAL'),(16,'Admin',38,'admin@admin.com','$2a$10$Wh0m2Ux5OHlne6ufXcLo9.N0bmMg/un2ZqLyFrz6OTvXW8rJzN6Li','NORMAL'),(17,'Teste',78,'admin@gmail.com ','$2a$10$gZ.bu9NfO85quRjdwsROKuv2eSZqGI9WAI5mRajlemJhyz9kYYA2y','NORMAL');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
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
