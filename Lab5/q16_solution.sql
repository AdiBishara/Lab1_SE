SELECT Customers.CustomerName 
FROM Customers 
JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
GROUP BY Customers.CustomerID, Customers.CustomerName, Orders.ProductID 
HAVING COUNT(Orders.OrderID) >= 2;