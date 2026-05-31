SELECT Customers.CustomerName, COUNT(Orders.OrderID) AS OrderCount
FROM Customers 
JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
GROUP BY Customers.CustomerID, Customers.CustomerName 
HAVING COUNT(Orders.OrderID) = (
    SELECT COUNT(OrderID) 
    FROM Orders 
    GROUP BY CustomerID 
    ORDER BY COUNT(OrderID) DESC 
    LIMIT 1
);