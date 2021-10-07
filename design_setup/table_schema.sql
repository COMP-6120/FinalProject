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

CREATE TABLE customer (
	CustomerID int NOT NULL,
	LastName varchar(50) NOT NULL,
	FirstName varchar(50) NOT NULL,
	Phone char(11) NOT NULL,
	PRIMARY KEY (CustomerID)
);

CREATE TABLE employee (
	EmployeeID int NOT NULL,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	PRIMARY KEY (EmployeeID)
);

CREATE TABLE shipper (
	ShipperID int NOT NULL,
	ShipperName char(8) NOT NULL,
	PRIMARY KEY (ShipperID)
);

CREATE TABLE orders (
	OrderID int NOT NULL,
	CustomerID int NOT NULL,
	EmployeeID int NOT NULL,
	OrderDate char(8) NOT NULL,
	ShippedDate char(8) NULL,
	ShipperID int NULL,
	PRIMARY KEY (OrderID),
	FOREIGN KEY (CustomerID) REFERENCES customer(CustomerID),
	FOREIGN KEY (EmployeeID) REFERENCES employee(EmployeeID),
	FOREIGN KEY (ShipperID) REFERENCES shipper(ShipperID)
);

CREATE TABLE order_detail (
	BookID int NOT NULL,
	OrderID int NOT NULL,
	Quantity int NOT NULL,
	FOREIGN KEY (BookID) REFERENCES book(BookID),
	FOREIGN KEY (OrderID) REFERENCES orders(OrderID)
);