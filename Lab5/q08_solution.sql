SELECT DISTINCT Customers.* FROM Customers 
JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
JOIN Products ON Orders.ProductID = Products.ProductID 
JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID 
WHERE Customers.City = Suppliers.City;