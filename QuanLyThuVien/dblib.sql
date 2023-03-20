CREATE DATABASE LibraryManagement;

USE LibraryManagement;

CREATE TABLE category 
(
	CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    code varchar(100),
    value nvarchar(100)
);
CREATE TABLE Books (
  BookID INT AUTO_INCREMENT PRIMARY KEY,
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
  ReaderID INT AUTO_INCREMENT PRIMARY KEY,
  FullName VARCHAR(255),
  Gender VARCHAR(10),
  DateOfBirth DATE,
  ReaderType VARCHAR(50)
);

CREATE TABLE BorrowCards (
  BorrowCardID INT AUTO_INCREMENT PRIMARY KEY,
  ReaderID INT,
  IssuedDate DATE,
  ExpiryDate DATE,
  FOREIGN KEY (ReaderID) REFERENCES Readers(ReaderID)
);

CREATE TABLE BorrowDetails (
  BorrowDetailID INT AUTO_INCREMENT PRIMARY KEY,
  BorrowCardID INT,
  BookID INT,
  BorrowDate DATE,
  DueDate DATE,
  ReturnDate DATE,
  FOREIGN KEY (BorrowCardID) REFERENCES BorrowCards(BorrowCardID),
  FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

CREATE TABLE BookReservations (
  BookReservationID INT AUTO_INCREMENT PRIMARY KEY,
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

CREATE TABLE account (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255),
    password VARCHAR(255),
    full_name VARCHAR(255),
    email VARCHAR(255),
    type VARCHAR(255)
);

insert into librarymanagement.readers(FullName, Gender,DateOfBirth,ReaderType) values ('phu','nam',2002-09-08,'quanly');
insert into librarymanagement.readers(FullName, Gender,DateOfBirth,ReaderType) values ('chuong','nu',2003-09-08,'nhanvien');

insert into librarymanagement.account(user_name,password,full_name,email,type) values ('admin','123','Admin','admin@gmail.com','Admin');
insert into librarymanagement.account(user_name,password,full_name,email,type) values ('employee','123','Employee','employee@gmail.com','Employee');

