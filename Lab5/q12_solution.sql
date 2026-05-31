SELECT Customers.CustomerName, COALESCE(SUM(Orders.Quantity), 0) AS TotalQuantity
FROM Customers 
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
GROUP BY Customers.CustomerID, Customers.CustomerName 
ORDER BY TotalQuantity DESC;