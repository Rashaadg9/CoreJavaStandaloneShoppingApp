DROP DATABASE IF EXISTS ShoppingDB;
CREATE DATABASE ShoppingDB;
USE ShoppingDB;

CREATE TABLE User
(
	UserId INT AUTO_INCREMENT,
    UserName VARCHAR(255) NOT NULL,
    Name VARCHAR(255) NOT NULL,
	Password VARCHAR(255) NOT NULL,
    AddressId INT,
    PRIMARY KEY (UserId)
);

CREATE TABLE Address
(
	AddressId INT AUTO_INCREMENT,
    Street VARCHAR(255) NOT NULL,
	City VARCHAR(255) NOT NULL,
    State VARCHAR(2) NOT NULL,
    Zip VARCHAR(255) NOT NULL,
    PRIMARY KEY (AddressId)
);

CREATE TABLE Inventory
(
    ItemCode VARCHAR(255) NOT NULL,
	ItemName VARCHAR(255) NOT NULL,
    ItemPrice INT NOT NULL,
    ItemCount INT DEFAULT 0,
    PRIMARY KEY (ItemCode)
);

INSERT INTO USER VALUES(null, "user1", "John Doe", "pass", 1);
INSERT INTO USER VALUES(null, "user2", "Jane Doe", "pass2", 2);

INSERT INTO Address VALUES(null, "16 North South Street", "Mason", "OH", "45040");
INSERT INTO Address VALUES(null, "500 Ryan Street", "Long Branch", "NJ", "07740");

INSERT INTO Inventory VALUES("Ja1", "Green Jacket", 20, 20);
INSERT INTO Inventory VALUES("Ja2", "Blue Jacket", 20, 15);
INSERT INTO Inventory VALUES("Je1", "Jeans", 15, 30);
INSERT INTO Inventory VALUES("Sh1", "White T-Shirt", 5, 25);