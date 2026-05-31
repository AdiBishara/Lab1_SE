SELECT Suppliers.SupplierName, COUNT(Products.ProductID) AS ProductCount
FROM Suppliers 
LEFT JOIN Products ON Suppliers.SupplierID = Products.SupplierID 
GROUP BY Suppliers.SupplierID, Suppliers.SupplierName;