CREATE DATABASE  IF NOT EXISTS `librarymanagement1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `librarymanagement1`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: librarymanagement1
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ReaderID` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','$2a$10$1NbzTRUnOCgBQEtD4ADrHufPUaRyq8bwzuyV7YJGBUn3ad8fTLRT2','Admin','admin@gmail.com','Admin',NULL),(2,'employee','$2a$10$1NbzTRUnOCgBQEtD4ADrHufPUaRyq8bwzuyV7YJGBUn3ad8fTLRT2','Employee','employee@gmail.com','Employee',NULL),(3,'client','$2a$10$1NbzTRUnOCgBQEtD4ADrHufPUaRyq8bwzuyV7YJGBUn3ad8fTLRT2','Dinh Chuong','chu@gmail.com','Customer',3),(4,'cho','$2a$10$cEGyiJ7sNdLYB7ANyaXKjewwqPwOkEq.FLAC6IKEdiiYXQruTJLnO','Đình Chương ','chuong59@gmail.com','Customer',4),(6,'chuot','$2a$10$w8ZwfFvCVLY6ZIlXHS.iq.zlN5L0bj6S3ng92XFiu8cdYv.WeKFdS','chuot','a@gmail.com','Admin',7),(7,'gaucon','$2a$10$HREB3ETDu1RCt8SEAN4s.elzQQgLDtIs87.4RbbWLrzZSZQREe7Fy','ab','d@gmail.com','Employee',8);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookreservations`
--

DROP TABLE IF EXISTS `bookreservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookreservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ReaderID` int DEFAULT NULL,
  `BookID` int DEFAULT NULL,
  `ReservationDate` date DEFAULT NULL,
  `ExpiryDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ReaderID` (`ReaderID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `bookreservations_ibfk_1` FOREIGN KEY (`ReaderID`) REFERENCES `readers` (`id`),
  CONSTRAINT `bookreservations_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookreservations`
--

LOCK TABLES `bookreservations` WRITE;
/*!40000 ALTER TABLE `bookreservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookreservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `BookTitle` varchar(255) DEFAULT NULL,
  `Author` varchar(255) DEFAULT NULL,
  `Description` text,
  `PublicationYear` int DEFAULT NULL,
  `PublicationPlace` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `CategoryID` int DEFAULT NULL,
  `CreateAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Sách A','Tác giả A','Mô Tả A',2020,'A','A',1,'2023-03-14 19:03:42',100),(2,'Sách B ','Tác giả B','Mô tả B',2021,'B','B',2,'2023-03-14 19:04:46',200),(3,'Sách C','Tác giả C','Mô tả C',2022,'C','C',1,'2023-04-03 19:02:28',459),(6,'sách d','tác giả d','mô tả d',2022,'d','d',1,'2023-04-03 21:53:02',328),(7,'sachcuabang','bang','',2021,'','day a',1,'2023-04-13 12:27:52',21),(8,'sachcuabang','bang','bang',2021,'bangcho','day a',1,'2023-04-13 14:06:44',21);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowcards`
--

DROP TABLE IF EXISTS `borrowcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowcards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ReaderID` int DEFAULT NULL,
  `IssuedDate` date DEFAULT NULL,
  `ExpiryDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ReaderID` (`ReaderID`),
  CONSTRAINT `borrowcards_ibfk_1` FOREIGN KEY (`ReaderID`) REFERENCES `readers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowcards`
--

LOCK TABLES `borrowcards` WRITE;
/*!40000 ALTER TABLE `borrowcards` DISABLE KEYS */;
INSERT INTO `borrowcards` VALUES (1,2,'2022-11-12','2023-01-02'),(2,1,'2021-08-09','2024-03-04'),(3,3,'2023-03-20','2023-06-23');
/*!40000 ALTER TABLE `borrowcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowdetails`
--

DROP TABLE IF EXISTS `borrowdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `BorrowCardID` int DEFAULT NULL,
  `BookID` int DEFAULT NULL,
  `BorrowDate` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `BorrowCardID` (`BorrowCardID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `borrowdetails_ibfk_1` FOREIGN KEY (`BorrowCardID`) REFERENCES `borrowcards` (`id`),
  CONSTRAINT `borrowdetails_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowdetails`
--

LOCK TABLES `borrowdetails` WRITE;
/*!40000 ALTER TABLE `borrowdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'1','Truyện '),(2,'2','Sách');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loanslip`
--

DROP TABLE IF EXISTS `loanslip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loanslip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `BookID` int DEFAULT NULL,
  `BookName` varchar(45) DEFAULT NULL,
  `BookAuthor` varchar(45) DEFAULT NULL,
  `BorrowedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ExpirationDate` datetime DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `isReturned` tinyint(1) DEFAULT '0',
  `isOnline` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loanslip`
--

LOCK TABLES `loanslip` WRITE;
/*!40000 ALTER TABLE `loanslip` DISABLE KEYS */;
INSERT INTO `loanslip` VALUES (4,2,1,'Sách A','Tác giả A','2023-03-28 00:00:00','2023-04-05 00:00:00',1,1,1),(6,2,2,'Sách B ','Tác giả B','2022-03-28 00:00:00','2022-04-05 00:00:00',3,1,1),(7,1,1,'Sách A','Tác giả A','2023-03-29 00:00:00','2023-04-05 00:00:00',2,1,1),(8,1,2,'Sách B ','Tác giả B','2023-03-29 00:00:00','2023-04-28 00:00:00',4,0,1),(9,3,1,'Sách A','Tác giả A','2023-03-29 00:00:00','2023-04-28 00:00:00',2,0,1),(10,3,3,'Sách C','Tác giả C','2023-11-05 00:00:00','2023-12-05 00:00:00',3,1,1),(11,3,6,'sách d','tác giả d','2022-04-05 00:00:00','2022-05-05 00:00:00',2,1,1);
/*!40000 ALTER TABLE `loanslip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readers`
--

DROP TABLE IF EXISTS `readers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(255) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readers`
--

LOCK TABLES `readers` WRITE;
/*!40000 ALTER TABLE `readers` DISABLE KEYS */;
INSERT INTO `readers` VALUES (1,'phu','nam','2003-09-08'),(2,'chuong','nu','2003-09-08'),(3,'phan','nu','2004-02-01'),(4,'Đình Chương ',NULL,NULL),(5,'Gia Heo',NULL,NULL),(6,'MeoH',NULL,NULL),(7,'chuot',NULL,NULL),(8,'ab',NULL,NULL);
/*!40000 ALTER TABLE `readers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13 14:10:54
