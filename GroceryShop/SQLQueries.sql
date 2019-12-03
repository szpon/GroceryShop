use GroceryShop
Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(1,'Washing Liquid, Morning','004','Detergernt',400,'ml',56,'Lever','Wash@go',3)
Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(2,'Bread white slice, Morning','005','Bakery',300,'g',20,'Bake&go','Bakery1',2)
Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(3,'Bread Roll, white','006','Bakery',100,'g',20,'Bake&go','Bakery1',1)

Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(4,'Washing powder,Ariel 500g','007','Detergernt',500,'g',68,'Ariel','Wash@go',12)
Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(5,'Bananas, cavendish','008','Fruit',1,'kg',100,'Fresh','Fresh&GO',2.5)
Insert into Products(ProductID,ProductName,ProductCode, Category,Size,Units,StockLevel,Vendor, MainSuplier,Price) values(6, 'Apples, Pink Lady','009','Fruit',80,'kg',20,'Atko','Fresh&GO',4.5)

Select * from Products

DELETE Products




INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (1,'John', 'Smith', '1 Sunnydale St','Sunnydale','NSW','2011')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (2,'Mary', 'Jones', '15 Victoria Rd','Ryde','NSW', '2013')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (3,'Rick', 'Spanner', '6/4 Burns Bay Rd','Lane Cove','NSW', '2066')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (4,'Tracy', 'Monohan', '23 Premier St','Kogarah','NSW', '2045')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (5,'John', 'Smith', '72 Parramatta Rd','Liechhardt','NSW', '2089')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (6,'Sandra', 'Kindale', '7/36 Chiefly Ave','Surry Hills','NSW', '2030')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (7,'Margaret','Wilson', '6 Fouveau St','Surry Hills','NSW','2022')

INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (8,'Przemek', 'Einsporn', '2/101 Cramer St','Preston','VIC', '3072')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (9,'Paul', 'Hinsley', '2/101 Cramer St','Preston','VIC', '3072')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (10,'Abdul', 'Pappa', '36 Grove Ave','Surry Hills','NSW', '2030')
INSERT Into Customers (CustomerNumber, FirstName, LastName,Address,Suburb,State,PostCode) values (11,'Mick','Prenter', '2 Albion St','Surry Hills','VIC','3040')

 
 
 
 Select * from Customers





INSERT INTO Supplier(SupplierID,SupplierName,Email,Phone,Address,Notes) values(1,'Einsporn&Hindley','pp@einspornhindley.com','042498888','101 Cramer Street, Preston, 3072 Vic','CIPKA HAH HAA');

INSERT INTO Supplier(SupplierID,SupplierName,Email,Phone,Address,Notes) values(2,'Wash&Go','mark@.washandgo.com','04249090','1 Hoddle Street, Kew, 3045 Vic','Dupa parezysts');

INSERT INTO Supplier(SupplierID,SupplierName,Email,Phone,Address,Notes) values(3,'Coffee Shop Tukan','elizabethHull@google.com','04349873','23 Burke Street, Melbourne, 3001 Vic','Nic nie wiem z sakd to sie wzielo');

SELECt * from Supplier


Delete from Supplier

  Insert INTO Units(UnitsID,Units) Values (1,'kg');  
  Insert INTO Units(UnitsID,Units) Values (2,'g'); 
  Insert INTO Units(UnitsID,Units) Values (3,'L'); 
  Insert INTO Units(UnitsID,Units) Values (4,'ml'); 

Select*from Units




Select* from Category
DELETE FROm Category


Insert INTO Category (Category) values ('Alcohol');