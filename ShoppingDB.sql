DROP DATABASE IF EXISTS ShoppingDB;
CREATE DATABASE ShoppingDB;
USE ShoppingDB;

CREATE TABLE User
(
	UserId INT AUTO_INCREMENT,
    UserName VARCHAR(255) NOT NULL,
    Name VARCHAR(255) NOT NULL,
	Password VARCHAR(255) NOT NULL,
    Balance INT DEFAULT 0,
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

CREATE TABLE Invoice
(
    InvoiceNo INT AUTO_INCREMENT,
	UserId INT NOT NULL,
    DATE TIMESTAMP NOT NULL,
    Items VARCHAR(255),
    PRIMARY KEY (InvoiceNo),
    FOREIGN KEY (UserId) REFERENCES User(UserId)
		ON UPDATE CASCADE
);
ALTER TABLE Invoice AUTO_INCREMENT = 1000;

DELIMITER //
CREATE TRIGGER CHECK_Inventory BEFORE UPDATE ON Inventory FOR EACH ROW
BEGIN
IF (NEW.ItemCount < 0)
THEN SIGNAL SQLSTATE "12345" SET MESSAGE_TEXT = "ERROR";
END IF;
END //
DELIMITER ;

INSERT INTO USER VALUES(null, "user1", "John Doe", "pass", 100, 1);
INSERT INTO USER VALUES(null, "user2", "Jane Doe", "pass2", 100, 2);

INSERT INTO Address VALUES(null, "16 North South Street", "Mason", "OH", "45040");
INSERT INTO Address VALUES(null, "500 Ryan Street", "Long Branch", "NJ", "07740");

INSERT INTO Inventory VALUES("Ja1", "Green Jacket", 20, 20);
INSERT INTO Inventory VALUES("Ja2", "Blue Jacket", 20, 15);
INSERT INTO Inventory VALUES("Je1", "Jeans", 15, 30);
INSERT INTO Inventory VALUES("Sh1", "White T-Shirt", 5, 25);
