CREATE TABLE supplier (
	SupplierID int NOT NULL,
	CompanyName varchar(255) NOT NULL,
	ContactLastName varchar(50) NOT NULL,
	ContactFirstName varchar(50) NOT NULL,
	Phone char(10) NOT NULL,
	PRIMARY KEY (SupplierID)
);

CREATE TABLE subject (
	SubjectID int NOT NULL,
	CategoryName varchar(50) NOT NULL,
	PRIMARY KEY (SubjectID)
);

CREATE TABLE book (
	BookID int NOT NULL,
	Title varchar(255) NOT NULL,
	UnitPrice decimal(6,2) NOT NULL,
	Author varchar(100) NOT NULL,
	Quantity int NOT NULL,
	SupplierID int NOT NULL,
	SubjectID int NOT NULL,
	PRIMARY KEY (BookID),
	FOREIGN KEY (SupplierID) REFERENCES supplier(SupplierID),
	FOREIGN KEY (SubjectID) REFERENCES subject(SubjectID)
);

