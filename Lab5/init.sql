DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Suppliers;


CREATE TABLE Suppliers (
    SupplierID INT PRIMARY KEY,
    SupplierName VARCHAR(255),
    City VARCHAR(255),
    ContactEmail VARCHAR(255)
);


CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR(255),
    City VARCHAR(255),
    Email VARCHAR(255)
);


CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(255),
    Price INT,
    StockQuantity INT,
    SupplierID INT,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    ProductID INT,
    OrderDate DATE,
    Quantity INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);


INSERT INTO Suppliers (SupplierID, SupplierName, City, ContactEmail) VALUES
(101, 'Tech Supplies Ltd', 'Tel Aviv', 'contact@techsupplies.com'),
(102, 'Mobile World', 'Haifa', 'sales@mobileworld.com'),
(103, 'Office Essentials', 'Jerusalem', 'info@officeessentials.com'),
(104, 'Gadget Hub', 'Tel Aviv', 'support@gadgethub.com');


INSERT INTO Customers (CustomerID, CustomerName, City, Email) VALUES
(201, 'Alice Cohen', 'Tel Aviv', 'alice@gmail.com'),
(202, 'Bob Levy', 'Haifa', 'bob@levy.com'),
(203, 'Carol Segal', 'Jerusalem', 'carol@segal.org'),
(204, 'David King', 'Tel Aviv', 'david.king@kingmail.com'),
(205, 'Eve Green', 'Be''er Sheva', 'eve.green@greenmail.co.il');


INSERT INTO Products (ProductID, ProductName, Price, StockQuantity, SupplierID) VALUES
(1, 'Laptop', 5000, 15, 101),
(2, 'Smartphone', 3000, 25, 102),
(3, 'Keyboard', 150, 50, 103),
(4, 'Monitor', 1200, 10, 101),
(5, 'Mouse', 100, 60, 104),
(6, 'Headphones', 250, 30, 102),
(7, 'Printer', 800, 5, 103),
(8, 'Desk Chair', 700, 20, 104),
(9, 'External Hard Drive', 400, 18, 101),
(10, 'Webcam', 300, 40, 102);


INSERT INTO Orders (OrderID, CustomerID, ProductID, OrderDate, Quantity) VALUES
(301, 201, 1, '2024-01-10', 1),
(302, 202, 2, '2024-02-05', 2),
(303, 203, 3, '2024-03-15', 5),
(304, 204, 4, '2024-01-18', 1),
(305, 205, 5, '2024-02-22', 3),
(306, 201, 6, '2024-01-25', 2),
(307, 202, 7, '2024-02-10', 1),
(308, 203, 8, '2024-03-01', 1),
(309, 204, 9, '2024-01-30', 1),
(310, 205, 10, '2024-03-10', 4);