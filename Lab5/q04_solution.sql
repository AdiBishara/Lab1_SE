SELECT DISTINCT ProductName 
FROM Products 
JOIN Orders ON Products.ProductID = Orders.ProductID 
WHERE Orders.Quantity > 4;