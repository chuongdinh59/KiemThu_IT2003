CREATE DATABASE LibraryManagement;

USE LibraryManagement;

CREATE TABLE category 
(
	CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    code varchar(100),
    value nvarchar(100)
);
CREATE TABLE Books (
  BookID INT PRIMARY KEY,
  BookTitle VARCHAR(255),
  Author VARCHAR(255),
  Description TEXT,
  PublicationYear INT,
  PublicationPlace VARCHAR(255),
  Location VARCHAR(255),
  CategoryID INT,
  FOREIGN KEY (CategoryID) REFERENCES category(CategoryID),
  CreateAt datetime default now()
);

CREATE TABLE Readers (
  ReaderID INT PRIMARY KEY,
  FullName VARCHAR(255),
  Gender VARCHAR(10),
  DateOfBirth DATE,
  ReaderType VARCHAR(50)
);

CREATE TABLE BorrowCards (
  BorrowCardID INT PRIMARY KEY,
  ReaderID INT,
  IssuedDate DATE,
  ExpiryDate DATE,
  FOREIGN KEY (ReaderID) REFERENCES Readers(ReaderID)
);

CREATE TABLE BorrowDetails (
  BorrowDetailID INT PRIMARY KEY,
  BorrowCardID INT,
  BookID INT,
  BorrowDate DATE,
  DueDate DATE,
  ReturnDate DATE,
  FOREIGN KEY (BorrowCardID) REFERENCES BorrowCards(BorrowCardID),
  FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

CREATE TABLE BookReservations (
  BookReservationID INT PRIMARY KEY,
  ReaderID INT,
  BookID INT,
  ReservationDate DATE,
  ExpiryDate DATE,
  FOREIGN KEY (ReaderID) REFERENCES Readers(ReaderID),
  FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

CREATE TABLE report (
    ReportID INT AUTO_INCREMENT PRIMARY KEY,
    report_time Date,
    borrowed INT,
    returned INT,
    on_loan INT,
    overdue INT,
    fine double
);

insert into librarymanagement.readers(ReaderID,FullName, Gender,DateOfBirth,ReaderType) values (1,'phu','nam',2002-09-08,'quanly');
insert into librarymanagement.readers(ReaderID,FullName, Gender,DateOfBirth,ReaderType) values (2,'chuong','nu',2003-09-08,'nhanvien');
