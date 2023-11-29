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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `Idade` int DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Senha` varchar(255) DEFAULT NULL,
  `NivelAcesso` varchar(15) DEFAULT 'NORMAL',
  `Telefone` varchar(20) DEFAULT NULL,
  `sexo` enum('FEMININO','MASCULINO','NAO_BINARIO','PREFIRO_NAO_DIZER','OUTRO'),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Silvinao',30,NULL,NULL,'NORMAL',NULL,'OUTRO'),(6,'Teste Teste',29,'admin@admin.com','TesteParaVer01@','ADMIN',NULL,'OUTRO'),(7,'Bruna',29,NULL,'TesteTeste01@','NORMAL',NULL,'OUTRO'),(10,'Teste Testes65A',55,'teste2@gmail.com','TesteTesteteste45@','NORMAL','11999999987','OUTRO'),(11,'Teste Testerr',77,'teste3@gmail.com','SenhaDoNovoAdmin!@','NORMAL','11999999987e','OUTRO'),(12,'Teste Teste2',76,'teste@teste.com','TesteTeste09@','NORMAL','9999999998','OUTRO'),(14,'Teste',66,'teste@teste.com','TesteTestetete','NORMAL','9999999999','OUTRO'),(15,'TesteSenha',66,'testesenha#teste.com','$2a$10$u33ZE8K5XLr0Zwp0HlgbI.iIllnMrwwuY6bmSsHGZbIU8.iWQo.c6','NORMAL','9999999999','OUTRO'),(16,'Teste3',67,'teste@gmail.com','$2a$10$ZGwDhf73bQUxzsz3D6nRpOmXYXfn07.OqjNS6sUj7KBpJ/n1eOqwC','NORMAL','11999999999','OUTRO'),(17,'Teste Senha',67,'testesenha@gmail.com','$2a$10$LZO2JHCUT1z37wiqMpG.3uPlRnYaJof.x/LWHviIGWjFHKSWAp9zy','NORMAL','11888888888','OUTRO'),(18,'Testew',78,'teste#teste.com','$2a$10$nuf5QAcyAvqbnKiuOFWt6OHLeG.qAJjxvQU1g0oMoO1d5m4xv6wSK','NORMAL','9999999999','OUTRO'),(19,'Tese Teste',78,'teste@gmail.com','$2a$10$mxUOTi2qs6ohHzFODnSJJeDO1iQlBjaOSzG/jusWpTLJWkiCIC.Zu','NORMAL','890009090909','OUTRO'),(20,'Nome',78,'teste@teste.com','$2a$10$D7G/2K8HBwl.78.Mh89g/eJNjwCrHfj7J/mgr23/nJiqJI7exGc8G','NORMAL','9999999999','OUTRO'),(22,'Teste Teste',78,'teste@gmail.com','$2a$10$2Uv6XnscbGWe946hxki.z.M4mnevom.n5S4izyU6ZEbTWVJ2g60aC','NORMAL','11888888787','OUTRO'),(23,'teste controller',78,'teste@gmail.com','$2a$10$iQc/RKbjaDHXvVNyOxtWc.TuKcT1M2aqUYALwQCuJSLuQcKkMNjSy','NORMAL','11888888888','OUTRO'),(24,'Teste Teste',78,'teste@gmail.com','$2a$10$iMHQm1m0O/1EiwBtjlD3NurEe4g5OdhHjjgtgWIXFmtJXGPV9HU56','NORMAL','11999999999','OUTRO'),(25,'Teste Admin',78,'teste@gmail.com','$2a$10$f6wqA7KjTFAOnk1aHJtTPu/RZWtar/2UN46kwK5LdUJafpvZ5BDIe','NORMAL','11999999999','OUTRO'),(26,'Teste Teste',78,'teste5@gmail.com','$2a$10$2BjBZalbvKi3y3yp5i7gr.4Lk7UYmlsHqWPS.TfGpv.bDlhwH6KPq','NORMAL','11999999999','OUTRO'),(27,'Usuario Teste',78,'usuario@gmail.com','$2a$10$aX9XJAV7sSUccIYkOsl/qu1nFdpw/ufEbK8BIBsREHtcf26gIj6wu','NORMAL','11999999999','MASCULINO'),(28,'Teste Final',78,'testefinal@gmail.com','$2a$10$xP7UWv.YKjhR3Mk23m.rM.SJumgqXtVwWXFg6bRHjTHwBi9RK9sXC','NORMAL','11999999999','FEMININO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
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
