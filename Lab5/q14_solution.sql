SELECT DISTINCT Suppliers.SupplierName 
FROM Suppliers 
JOIN Products ON Suppliers.SupplierID = Products.SupplierID 
WHERE Products.Price > 1000;