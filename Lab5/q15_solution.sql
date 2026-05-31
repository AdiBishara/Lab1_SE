SELECT City, SUM(NumCustomers) AS CustomerCount, SUM(NumSuppliers) AS SupplierCount 
FROM (
    SELECT City, 1 AS NumCustomers, 0 AS NumSuppliers FROM Customers
    UNION ALL 
    SELECT City, 0 AS NumCustomers, 1 AS NumSuppliers FROM Suppliers
) AS CityStats 
GROUP BY City 
ORDER BY City ASC;