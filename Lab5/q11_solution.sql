SELECT Customers.CustomerName, Products.ProductName, Orders.OrderDate 
FROM Orders 
JOIN Customers ON Orders.CustomerID = Customers.CustomerID 
JOIN Products ON Orders.ProductID = Products.ProductID 
WHERE YEAR(Orders.OrderDate) = 2024;