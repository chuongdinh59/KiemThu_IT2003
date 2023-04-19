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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'client','$2a$10$1NbzTRUnOCgBQEtD4ADrHufPUaRyq8bwzuyV7YJGBUn3ad8fTLRT2','Dinh Chuong','chu@gmail.com','Customer',1),(2,'cho','$2a$10$cEGyiJ7sNdLYB7ANyaXKjewwqPwOkEq.FLAC6IKEdiiYXQruTJLnO','Đình Chương ','chuong59@gmail.com','Customer',2),(3,'chuot','$2a$10$w8ZwfFvCVLY6ZIlXHS.iq.zlN5L0bj6S3ng92XFiu8cdYv.WeKFdS','chuot','a@gmail.com','Admin',3),(4,'gaucon','$2a$10$HREB3ETDu1RCt8SEAN4s.elzQQgLDtIs87.4RbbWLrzZSZQREe7Fy','ab','d@gmail.com','Employee',4),(5,'tester1','$2a$10$Op6ukm7D8EnfZ7KYTmcuXefoyqors0F5MwxLSgYOJFIyDeuiRypyK','Tôi Dại Dột','tester1@gmail.com','Customer',5),(6,'khachhang1','$2a$10$QgR6K8AEScA.SxH.AsYQKuvK4MshNtTX4TcUR43xLpuxC4aEQnw5u','Hoàng Phi Hồng','hhp112@gmail.com','Customer',6),(7,'employee','$2a$10$rhznYoAsaAoMJDa.cbmafuI94zfrw.evUzZPVql/46BHSZn7/k2/2','Nhân Viên Đẹp Trai','nhanvienOUL@gmail.com','Employee',7),(8,'admin','$2a$10$88kPao9McvbLrpnkBRpDj.C/BhDNxRb9qA4EptzqkQVe8agC.ASwC','Admin Hệ Thống','adminTV@gmail.com','Admin',8),(9,'testemail','$2a$10$gOOdbyUaQMKahZYprv3k4.qHVLoESY0ES.dZRkEqiLH2rjqthLDtW','Lệnh Hồ Xung','chuongd505@gmail.com','Customer',9),(10,'khachhang2','$2a$10$3yPCYcInxKAxmvHmbTQMMe/y8JHdWpHsN2omJa9g5hqYH7lmYOZcu','Trần Văn Lượng','tvl123@gmail.com','Customer',10),(11,'khachhang3','$2a$10$Ms4u755KkkgcT4UFDqUM0.T9yTyORvcSGgKvhRs3r78rEomjBfL4m','Phan Đình Phùng','pdp123@gmail.com','Customer',11),(12,'khachhang4','$2a$10$4E0mHdzvmdCsLLfPYu0G3uTsMJeIPj4FUzHmqYibYANWT59LF/f8G','Trương Anh Ngọc','tinchuan@gmail.com','Customer',12),(13,'khachhang5','$2a$10$LQXS4jtkSfBaTzzTQqnP4OFgOunTeGmAlwmIUF/MVnOJAbaTri6ma','Hồ Văn Huê','hhh@gmail.com','Employee',13),(23,'phuLib','$2a$10$Mjho79uS/Q2qfiokpgFU4OGx/SO3DlrMdat48.FkxSdr3qiwfxrkK','phu','abc@gmail.com','Customer',24);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
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
  `Image` text,
  PRIMARY KEY (`id`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Murachs Oracle SQL and PL/SQL for Developers, 2nd Edition','Mike Murach & Associates','If youre developing applications that access Oracle databases, you can save time and work by having SQL do more of the data handling for you: This new book shows you how. It teaches you how to create effective SQL queries to extract and update the data in an Oracle database. It teaches you how to design and implement a database, giving you insight into performance and security issues. It teaches you how to use PL/SQL to take advantage of powerful features like stored procedures, functions, and triggers. In short, it teaches you to create the kind of efficient database applications that make you a more effective and valuable developer.',2014,'NXB ABC','A',2,'2023-03-14 19:03:42',25,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681888639/yxjzd1yrydvm1xdjfje5.jpg'),(2,'An Introduction To Statistical Learning (2021)','Gareth James, Daniela Witten, Trevor Hastie, Robert Tibshirani','An Introduction To Statistical Learning provides an accessible overview of the field of statistical learning, an essential toolset for making sense of the vast and complex data sets that have emerged in fields ranging from biology to finance to marketing to astrophysics in the past twenty years.',2017,'NXB Hà Nội','B',2,'2023-03-14 19:04:46',19,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681888875/nexzhrthquoxmbvi8d5w.jpg'),(3,'Lập trình cơ sở dữ liệu','Lê Viết Tuấn','Tài liệu gồm các mục chính: lập trình cơ sở dữ liệu với SQL Sever, C#.NET, ADO.NET, liên kết dữ liệu, N-Layer, thiết kế report.',2017,'NXB TPHCM','A',2,'2023-04-03 19:02:28',21,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681889461/mjrix9k2ql3crd4kpifh.jpg'),(4,'Lập Trình Java','Dương Hữu Thành','Trường Đại học Mở Thành phố Hồ Chí Minh. Khoa Công nghệ thông tin',2020,'NXB Hà Nội','A',2,'2023-04-03 21:53:02',30,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681889303/bmkwmtp3vujpitisprfi.jpg'),(5,'Công nghệ phần mềm','Dương Hữu Thành','Sách học Công nghệ phần mềm',2017,'ĐH Mở','A',2,'2023-04-13 12:27:52',19,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792055/pdihsmlpyvao1ytq7rxy.jpg'),(6,'Kiểm thử phần mềm','Dương Hữu Thành','Sách học Kiểm thử phần mềm',2017,'ĐH Mở','A',2,'2023-04-13 14:06:44',21,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792017/yvmzvuaf8dhpwgy7yntb.jpg'),(7,'Doraemon và Chuyến phiêu lưu vào xứ quỷ','Fujiko Fujio oo','Doraemon: Nobita no Shin Makai Daibōken ~Shichinin no Mahō Tsukai hay còn gọi là Doraemon the Magic là phim khoa học viễn tưởng thứ 27 trong xê-ri phim dài Doraemon. Phim được làm tại từ Doraemon: Nobita và chuyến phiêu lưu vào xứ quỷ bởi đạo diễn Teramoto Yukiyo và chính thức được ra mắt vào ngày 15 tháng 2 năm 2007.',2000,'NXB Kim Đồng','C',1,'2023-04-16 02:00:11',4,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681791862/tx9l6jvsdlyklvemheee.png'),(8,'Trắc địa đại cương','Nguyễn Tấn Lộc','Trắc địa kun',2018,'Thành phố Hồ Chí Minh : Đại học Quốc gia Thành phố Hồ Chí Minh','A',2,'2023-04-17 13:35:34',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792187/myxxisprae7b2hqndt1i.jpg'),(9,'Tuyển tập truyện cười Dì Mai','RemindTV','Chiến thắng phải dùng đến Tia laze chết chóc thì gọi là gì? VictorE',2023,'NXB CKG','D',1,'2023-04-18 11:34:16',1,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792469/evt3k17fx197xfwrxn42.jpg'),(10,'The Shining','Stephen King','Truyện kinh dị ',2010,'Random House USA Inc','D',1,'2023-04-18 11:38:57',5,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792773/trbnfsgot3rddlswlzka.jpg'),(11,'Bí mật của trí tuệ siêu phàm','Eran Katz','Haha',2015,'NXB Lao động xã hội','B',2,'2023-04-18 11:42:29',15,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681792963/gxu0rfpfjwf9cr2y3z00.jpg'),(12,'Effective Java','Joshua Bloch','Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.',2018,'NXB Hà Nội','B',2,'2023-04-18 11:46:35',15,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681793210/flqcfo4oicqetvrsq5b1.jpg'),(13,'Java Cookbook Solutions and Examples for Java Developers 3rd Edition','Ian F.Darwin','Java Can Cook',2014,'NXB Hà Lội','B',2,'2023-04-18 11:48:49',23,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681793344/uu6bws7rrestbxsqjgqb.jpg'),(14,'Mathematics for Machine Learning','Marc Peter Deisenroth, A. Aldo Faisal, Cheng Soon Ong','Mathematics for Machine Learning',2020,'NXB Hà Lội','B',2,'2023-04-18 11:51:27',12,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681793501/pspzoanzutcklatgju6y.jpg'),(15,'Truyền kỳ mạn lục','Nguyễn Dữ','Truyền kỳ mạn lục, là tác phẩm duy nhất của danh sĩ Nguyễn Dữ, sống vào khoảng thế kỷ 16 tại Việt Nam. Đây là tác phẩm được Hà Thiện Hán viết lời tựa, Nguyễn Bỉnh Khiêm phủ chính, Nguyễn Thế Nghi dịch ra chữ Nôm, và đã được Tiến sĩ Vũ Khâm Lân đánh giá là một \"áng thiên cổ kỳ bút\". ',2022,'NXB Kim Đồng','C',1,'2023-04-18 11:54:03',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681793658/gasgmqha5qhpmwpzyc7m.jpg'),(16,'Ruination: A League of Legends Novel Hardcover','Anthony Reynolds','Discover an epic tale of magic, revenge, and an empire on the verge of ruin in the first ever novel set in the blockbuster universe of League of Legends.',2022,'Orbit','C',1,'2023-04-18 11:57:52',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681793886/iw06z0pi9v0feup9vffk.jpg'),(17,'Nhật ký trong tù (Tái bản)','Hồ Chí Minh','Nhật ký trong tù là tập thơ chữ Hán gồm 134 bài theo thể Đường luật do Hồ Chí Minh sáng tác trong thời gian bị chính quyền Tưởng Giới Thạch bắt giam ở Quảng Tây, Trung Quốc, từ ngày 29 tháng 8 năm 1942 đến ngày 10 tháng 9 năm 1943.',2020,'NXB Hà Nội','C',2,'2023-04-18 12:00:43',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681794058/pitz4mjrdcjtkwtse0nz.jpg'),(18,'Java in a Nutshell: A Desktop Quick Reference 6th Edition','Benjamin J Evans, David Flanagan','The latest edition of Java in a Nutshell is designed to help experienced Java programmers get the most out of Java 7 and 8, but it’s also a learning path for new developers. Chock full of examples that demonstrate how to take complete advantage of modern Java APIs and development best practices, the first section of this thoroughly updated book provides a fast-paced, no-fluff introduction to the Java programming language and the core runtime aspects of the Java platform.',2014,'O\'Reilly Media','A',2,'2023-04-18 12:03:19',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681794213/sqjamipicbs30bskcojg.jpg'),(19,'Lập trình giao diện','Nguyễn Thị Mai Trang','C#.Net (Ngôn ngữ lập trình điện toán); Điện toán, Giao diện',2018,'TPHCM','A',2,'2023-04-19 15:18:47',18,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681892344/sxofxzqqtjwwukyf73zf.jpg'),(20,'GIÁO TRÌNH C++ VÀ LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG','Phạm Văn Ất, Lê Trường Thông','Quyển sách này trình bày các vấn đề: Lập trình C++ và lập trình hướng đối tượng, hàm trong C++, Khái niệm về lớp, các vấn đề về hàm, dẫn xuất và thừa kế, tương ứng bội và phương thức ảo, thao tác trên các tệp tin, các dòng tin, đồ họa,...',2019,'Hà Nội: Hồng Đức','A',2,'2023-04-19 15:20:06',10,'https://res.cloudinary.com/dsxybm23c/image/upload/v1681892422/sufuxqpzeckijvl5vlk3.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowcards`
--

LOCK TABLES `borrowcards` WRITE;
/*!40000 ALTER TABLE `borrowcards` DISABLE KEYS */;
INSERT INTO `borrowcards` VALUES (1,1,'2023-04-19','2024-04-19'),(2,4,'2023-04-19','2024-04-19'),(3,3,'2023-04-19','2024-04-19'),(4,2,'2023-04-19','2023-04-18'),(5,5,'2023-04-19','2024-04-19'),(6,6,'2023-04-19','2024-04-19'),(7,8,'2023-04-19','2024-04-19'),(8,7,'2023-04-19','2024-04-19'),(9,10,'2023-04-19','2024-04-19'),(11,11,'2023-04-19','2024-04-19'),(12,12,'2023-04-19','2024-04-19'),(13,13,'2023-04-19','2024-04-19'),(35,9,'2023-04-19','2024-04-19');
/*!40000 ALTER TABLE `borrowcards` ENABLE KEYS */;
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
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
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
  `BookName` varchar(100) DEFAULT NULL,
  `BookAuthor` varchar(100) DEFAULT NULL,
  `BorrowedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ExpirationDate` datetime DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `isReturned` tinyint(1) DEFAULT '0',
  `isOnline` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loanslip`
--

LOCK TABLES `loanslip` WRITE;
/*!40000 ALTER TABLE `loanslip` DISABLE KEYS */;
INSERT INTO `loanslip` VALUES (1,6,8,'Truyền kỳ mạn lục','Nguyễn Dữ','2022-01-19 00:00:00','2022-02-19 00:00:00',1,1,1),(3,2,6,'Kiểm thử phần mềm','Dương Hữu Thành','2022-07-19 00:00:00','2022-08-19 00:00:00',2,1,1),(4,5,10,'The Shining','Stephen King','2022-04-19 00:00:00','2022-05-19 00:00:00',1,1,1),(5,6,7,'Doraemon và Chuyến phiêu lưu vào xứ quỷ','Fujiko Fujio oo','2022-04-09 00:00:00','2022-05-09 00:00:00',1,1,1),(6,2,3,'Lập trình cơ sở dữ liệu','Lê Viết Tuấn','2022-04-20 00:00:00','2022-05-19 00:00:00',2,1,1),(7,10,7,'Doraemon và Chuyến phiêu lưu vào xứ quỷ','Fujiko Fujio oo','2022-11-19 00:00:00','2022-12-19 00:00:00',2,1,1),(8,6,6,'Kiểm thử phần mềm','Dương Hữu Thành','2022-05-19 00:00:00','2022-06-19 00:00:00',2,1,1),(9,6,3,'Lập trình cơ sở dữ liệu','Lê Viết Tuấn','2023-04-19 00:00:00','2023-05-19 00:00:00',2,1,1),(10,5,6,'Kiểm thử phần mềm','Dương Hữu Thành','2023-04-19 00:00:00','2023-05-19 00:00:00',2,1,1),(11,10,5,'Công nghệ phần mềm','Dương Hữu Thành','2023-03-16 00:00:00','2023-04-15 00:00:00',2,0,1),(12,5,9,'Tuyển tập truyện cười Dì Mai','RemindTV','2021-10-19 00:00:00','2021-11-19 00:00:00',3,1,1),(26,12,9,'Tuyển tập truyện cười Dì Mai','RemindTV','2023-02-19 00:00:00','2023-03-19 00:00:00',2,0,1),(27,13,19,'Lập trình giao diện','Nguyễn Thị Mai Trang','2023-04-19 00:00:00','2023-05-19 00:00:00',2,0,1),(28,1,7,'Doraemon và Chuyến phiêu lưu vào xứ quỷ','Fujiko Fujio oo','2023-04-19 00:00:00','2023-05-19 00:00:00',2,1,1),(29,1,6,'Kiểm thử phần mềm','Dương Hữu Thành','2023-04-19 00:00:00','2023-05-19 00:00:00',1,1,1),(30,1,5,'Công nghệ phần mềm','Dương Hữu Thành','2023-04-19 00:00:00','2023-05-19 00:00:00',1,1,1),(31,8,10,'The Shining','Stephen King','2023-04-19 00:00:00','2023-05-19 00:00:00',1,0,1);
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
  `Phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readers`
--

LOCK TABLES `readers` WRITE;
/*!40000 ALTER TABLE `readers` DISABLE KEYS */;
INSERT INTO `readers` VALUES (1,'phan','Nam','2004-02-25','01312312234'),(2,'Đình Chương ','Nam','2004-02-14','01234567892'),(3,'phan2','Nữ',NULL,NULL),(4,'Gấu Con','Nam','2025-01-11','01010122314'),(5,'Tôi Dại Dột','Nữ','2004-05-09','01020302041'),(6,'Hoàng Phi Hồng','Nam','2002-04-19','09021228912'),(7,'Nhân Viên Đẹp Trai','Nam','2022-11-28','02023254845'),(8,'Admin Hệ Thống','Nam','2002-03-02','03765790461'),(9,'Lệnh Hồ Xung','Nam','2023-07-20','09061355546'),(10,'Trần Văn Lượng','Nam','1997-03-08','01025481507'),(11,'Phan Đình Phùng','Nam','2022-12-15','07845512478'),(12,'Trương Anh Ngọc',NULL,NULL,NULL),(13,'Hồ Văn Huê',NULL,NULL,NULL),(24,'phu',NULL,NULL,NULL);
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

-- Dump completed on 2023-04-19 19:49:42
