SELECT DISTINCT CustomerName 
FROM Customers 
JOIN Orders ON Customers.CustomerID = Orders.CustomerID;