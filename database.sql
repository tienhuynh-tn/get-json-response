USE master
GO 

IF EXISTS (SELECT * FROM sys.databases WHERE name='GetJSONRespone')
DROP DATABASE GetJSONRespone
GO 
CREATE DATABASE GetJSONRespone
GO 
USE GetJSONRespone
GO

CREATE TABLE [User](
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	lastname VARCHAR(20),
	isAdmin BIT,
	PRIMARY KEY(username)
) 
GO

INSERT INTO [User](username, password, lastname, isAdmin) values('tien123', 'tientien', 'Huynh Tien', 1)
INSERT INTO [User](username, password, lastname, isAdmin) values('dat123', 'dat123', 'Thanh Dat', 0)
INSERT INTO [User](username, password, lastname, isAdmin) values('phuong123', 'phuongphuong', 'Thuy Phuong', 0)