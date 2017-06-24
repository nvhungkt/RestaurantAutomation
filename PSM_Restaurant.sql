USE AutomationRestaurant

--CLEAN TABLE
--
--for busboy

IF OBJECT_ID('cleanTable','P') IS NOT NULL
	DROP PROCEDURE cleanTable
GO

CREATE PROCEDURE cleanTable
	@tableNumber INT,
	@busboyID VARCHAR(20)
AS 
	DECLARE @time DATETIME
	SELECT @time = GETDATE()
	
	DECLARE @currentStatus VARCHAR(10)
	SELECT @currentStatus = [status]
	FROM tblTable
	WHERE number = @tableNumber
	
	IF (@currentStatus = 'dirty')
	BEGIN
		INSERT INTO tblClean VALUES (@tableNumber, @time, @busboyID)
	END
	ELSE 
	BEGIN
		RAISERROR('Current table is not dirty!!', 16, 1)
	END
GO

--FOR HOST
--
--viewTable
IF OBJECT_ID('getAllTableStatus','P') IS NOT NULL
	DROP PROCEDURE getAllTableStatus
GO

CREATE PROCEDURE getAllTableStatus
AS 
	SELECT number, capacity, [status]
	FROM tblTable
	WHERE isActive = 1 AND [status] <> 'occupied'
GO

--take table
IF OBJECT_ID('takeTable','P') IS NOT NULL
	DROP PROCEDURE takeTable
GO

CREATE PROCEDURE takeTable
	@tableNumber INT
AS 
	DECLARE @tblstatus VARCHAR(10)
	SELECT @tblstatus = [status]
	FROM tblTable
	WHERE number = @tableNumber
	
	IF (@tblstatus = 'cleaned')
		UPDATE tblTable SET [status] = 'occupied' WHERE number = @tableNumber
GO

--reser table
IF OBJECT_ID('resetTable','P') IS NOT NULL
	DROP PROCEDURE resetTable
GO

CREATE PROCEDURE resetTable
	@tableNumber INT
AS 
	DECLARE @tblstatus VARCHAR(10)
	SELECT @tblstatus = [status]
	FROM tblTable
	WHERE number = @tableNumber
	
	DECLARE @orderID VARCHAR(50)
	SET @orderID = 'null'
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	IF (@tblstatus = 'occupied' AND @orderID = 'null')
		UPDATE tblTable SET [status] = 'cleaned' WHERE number = @tableNumber
GO
	

--for waiter
--
--create new order
IF OBJECT_ID('createNewOrder', 'P') IS NOT NULL
	DROP PROCEDURE createNewOrder
GO

CREATE PROCEDURE createNewOrder
	@tableNumber INT,
	@waiterID VARCHAR(20),
	@customerName NVARCHAR(50)
AS
	DECLARE @tblstatus VARCHAR(10)
	SELECT @tblstatus = [status]
	FROM tblTable
	WHERE number = @tableNumber
	
	IF (@tblstatus = 'cleaned')
	BEGIN
		DECLARE @date DATE
		SELECT @date = GETDATE()
		
		DECLARE @currentID VARCHAR(50)
		SELECT @currentID = MAX(r.id)
		FROM 
			(SELECT id
			FROM tblOrder
			WHERE [date] = @date) r
			
		IF (@currentID IS NULL)
		BEGIN
			DECLARE @prefix VARCHAR(50)
			SET @prefix = REPLACE(CAST(@date AS VARCHAR(50)), '-', '')
			SET @prefix = RIGHT(@prefix, 6)
			SET @currentID = @prefix + '001' --ID contains the current date
		END
		ELSE 
			SET @currentID = @currentID + 1
		
		DECLARE @arriveTime TIME
		SELECT @arriveTime = GETDATE()
		
		INSERT INTO tblOrder VALUES(@currentID, @tableNumber, @waiterID, @customerName, @date, @arriveTime, NULL)
	END
GO

--archive order
IF OBJECT_ID('archiveOrder', 'P') IS NOT NULL
	DROP PROCEDURE archiveOrder
GO

CREATE PROCEDURE archiveOrder
	@tableNumber INT
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @leavingTime TIME
	SELECT @leavingTime = GETDATE()
	
	UPDATE tblOrder SET leavingTime = @leavingTime WHERE id = @orderID
	
	UPDATE tblTable SET [status] = 'dirty' WHERE number = @tableNumber
GO

--Cancel order
IF OBJECT_ID('cancelOrder', 'P') IS NOT NULL
	DROP PROCEDURE cancelOrder
GO
CREATE PROCEDURE cancelOrder
	@tableNumber INT
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @number INT
	DECLARE detail CURSOR 
		FOR SELECT [no] FROM tblOrderDetail WHERE orderID = @orderID
	OPEN detail
	FETCH NEXT FROM detail INTO @number
	IF @@FETCH_STATUS <> 0
	BEGIN
		DELETE FROM tblOrder WHERE id = @orderID
		
		UPDATE tblTable SET [status] = 'cleaned' WHERE number = @tableNumber
	END
	IF @@FETCH_STATUS = 0
	BEGIN
		RAISERROR('Cannot delete this order', 16, 1)
	END
	CLOSE detail
	DEALLOCATE detail
GO

--create order detail
IF OBJECT_ID('createDetail', 'P') IS NOT NULL
	DROP PROCEDURE createDetail
GO
CREATE PROCEDURE createDetail
	@tableNumber INT,
	@mealID VARCHAR(20),
	@quantity DECIMAL(18, 2)
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @no INT
	SET @no = 0
	SELECT @no = MAX([no]) 
	FROM tblOrderDetail
	WHERE orderID = @orderID
	SET @no = @no + 1
	
	DECLARE @takenTime TIME
	SET @takenTime = GETDATE()
	
	INSERT INTO tblOrderDetail VALUES(@orderID, @no, @mealID, @quantity, @takenTime, NULL, NULL, 'ordered')
GO


--deliver meal
IF OBJECT_ID('deliverMeal', 'P') IS NOT NULL
	DROP PROCEDURE deliverMeal
GO
CREATE PROCEDURE deliverMeal
	@tableNumber INT,
	@no INT
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	UPDATE tblOrderDetail SET [status] = 'delivered' WHERE orderID = @orderID AND [no] = @no
GO


--get bill with table
IF OBJECT_ID('getBillWithTable', 'P') IS NOT NULL
	DROP PROCEDURE getBillWithTable
GO
CREATE PROCEDURE getBillWithTable
	@tableNumber INT
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @total DECIMAL(18, 2)
	SET @total = 0
	
	DECLARE @mealID VARCHAR(20)
	DECLARE @quantity DECIMAL(18, 2)
	DECLARE @price DECIMAL(18, 2)
	DECLARE detail CURSOR
		FOR SELECT mealID, quantity FROM tblOrderDetail WHERE orderID = @orderID
	OPEN detail
	FETCH NEXT FROM detail INTO @mealID, @quantity
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SELECT @price = price
		FROM tblPrice
		WHERE mealID = @mealID AND toDate IS NULL
		
		SET @total = @total + @price * @quantity
		
		FETCH NEXT FROM detail INTO @mealID, @quantity
	END
	CLOSE detail
	DEALLOCATE detail
	
	SELECT @total AS totalPrice
GO

--get bill details
IF OBJECT_ID('getBillDetailWithTable', 'P') IS NOT NULL
	DROP PROCEDURE getBillDetailWithTable
GO
CREATE PROCEDURE getBillDetailWithTable
	@tableNumber INT
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @date DATE
	SELECT @date = [date]
	FROM tblOrder
	WHERE id = @orderID
	
	SELECT [no], name, quantity, price, price * quantity AS total
	FROM tblOrderDetail, tblMeal, tblPrice
	WHERE orderID = @orderID 
	AND tblOrderDetail.mealID = tblMeal.id AND tblOrderDetail.mealID = tblPrice.mealID
	AND @date >= fromDate AND toDate IS NULL
GO

--get bill with order id
IF OBJECT_ID('getBillWithOrderID', 'P') IS NOT NULL
	DROP PROCEDURE getBillWithOrderID
GO
CREATE PROCEDURE getBillWithOrderID
	@orderID VARCHAR(50)
AS	
	DECLARE @date DATE
	SELECT @date = [date]
	FROM tblOrder
	WHERE id = @orderID

	DECLARE @total DECIMAL(18, 2)
	SET @total = 0
	
	DECLARE @mealID VARCHAR(20)
	DECLARE @quantity DECIMAL(18, 2)
	DECLARE @price DECIMAL(18, 2)
	
	DECLARE detail CURSOR
		FOR SELECT mealID, quantity FROM tblOrderDetail WHERE orderID = @orderID
	OPEN detail
	FETCH NEXT FROM detail INTO @mealID, @quantity
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SELECT @price = price
		FROM tblPrice
		WHERE mealID = @mealID AND @date >= fromDate AND (@date <= toDate OR toDate IS NULL)
		
		SET @total = @total + @price * @quantity
		
		FETCH NEXT FROM detail INTO @mealID, @quantity
	END
	CLOSE detail
	DEALLOCATE detail
	
	SELECT @total AS totalPrice
GO


--FOR COOK
--
--receive meal
IF OBJECT_ID('cook', 'P') IS NOT NULL
	DROP PROCEDURE cook
GO
CREATE PROCEDURE cook
	@tableNumber INT,
	@no INT, 
	@cookID VARCHAR(20)
AS	
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	UPDATE tblOrderDetail SET cookID = @cookID, [status] = 'cooking' WHERE orderID = @orderID AND [no] = @no
GO

--inform waiter
IF OBJECT_ID('informWaiter', 'P') IS NOT NULL
	DROP PROCEDURE informWaiter
GO
CREATE PROCEDURE informWaiter
	@tableNumber INT,
	@no INT, 
	@cookID VARCHAR(20)
AS
	DECLARE @orderID VARCHAR(50)
	SELECT @orderID = id
	FROM tblOrder
	WHERE tableNumber = @tableNumber AND leavingTime IS NULL
	
	DECLARE @readyTime TIME
	SET @readyTime = GETDATE()
	
	UPDATE tblOrderDetail SET readyTime = @readyTime, [status] = 'ready' WHERE orderID = @orderID AND [no] = @no AND cookID = @cookID
GO

--Manager
--
--
--
--Table
--
--update table
IF OBJECT_ID('updateTable', 'P') IS NOT NULL
	DROP PROCEDURE updateTable
GO
CREATE PROCEDURE updateTable
	@tableNumber INT,
	@capacity INT
AS
	DECLARE @test INT
	DECLARE [table] CURSOR
		FOR SELECT number FROM tblTable WHERE number = @tableNumber
	OPEN [table]
	FETCH NEXT FROM [table] INTO @test
	IF @@FETCH_STATUS <> 0
	BEGIN
		INSERT INTO tblTable VALUES (@tableNumber, @capacity, 'cleaned', 1)
	END
	ELSE IF @@FETCH_STATUS = 0
	BEGIN
		UPDATE tblTable SET capacity = @capacity, isActive = 1 WHERE number = @tableNumber
	END
	CLOSE [table]
	DEALLOCATE [table]
GO

--Deactive table
IF OBJECT_ID('removeTable', 'P') IS NOT NULL
	DROP PROCEDURE removeTable
GO
CREATE PROCEDURE removeTable
	@tableNumber INT
AS
	UPDATE tblTable SET isActive = 0 WHERE number = @tableNumber
GO

--Meal
--
--add meal
IF OBJECT_ID('addMeal', 'P') IS NOT NULL
	DROP PROCEDURE addMeal
GO
CREATE PROCEDURE addMeal
	@name NVARCHAR(50),
	@unit VARCHAR(20),
	@price DECIMAL(18, 2),
	@cateID VARCHAR(20)
AS
	DECLARE @id VARCHAR(20)
	SELECT @id = MAX(id)
	FROM tblMeal
	SET @id = @id + 1
	
	IF (@id < 100)
		SET @id = '0' + @id
	IF (@id < 10)
		SET @id = '00' + @id
	
	DECLARE @fromDate DATE
	SET @fromDate = GETDATE()
	
	INSERT INTO tblMeal VALUES(@id, @name, @unit, 1, @cateID)
	INSERT INTO tblPrice VALUES(@id, @price, @fromDate, NULL)
GO

--update meal
IF OBJECT_ID('updateMeal', 'P') IS NOT NULL
	DROP PROCEDURE updateMeal
GO
CREATE PROCEDURE updateMeal
	@id VARCHAR(20),
	@name NVARCHAR(50),
	@unit VARCHAR(20),
	@price DECIMAL(18, 2),
	@cateID VARCHAR(20)
AS	
	UPDATE tblMeal SET name = @name, unit = @unit, cateID = @cateID WHERE id = @id
	BEGIN
		DECLARE @oldPrice DECIMAL(18, 2)
		SELECT @oldPrice = price 
		FROM tblPrice
		WHERE mealID = @id AND toDate IS NULL
		
		IF (@oldPrice <> @price)
			EXEC updatePrice @id, @price
	END
GO

--remove meal
IF OBJECT_ID('removeMeal', 'P') IS NOT NULL
	DROP PROCEDURE removeMeal
GO
CREATE PROCEDURE removeMeal
	@id VARCHAR(20)
AS
	UPDATE tblMeal SET isAvailable = 0 WHERE id = @id
GO

--update price
IF OBJECT_ID('updatePrice', 'P') IS NOT NULL
	DROP PROCEDURE updatePrice
GO
CREATE PROCEDURE updatePrice
	@id VARCHAR(20),
	@price DECIMAL(18, 2)
AS
	DECLARE @fromDate DATE
	SET @fromDate = GETDATE()
	
	DECLARE @currentFromDate DATE
	SELECT @currentFromDate = fromDate
	FROM tblPrice
	WHERE mealID = @id AND toDate IS NULL
	
	IF (@fromDate <> @currentFromDate)
	BEGIN
		UPDATE tblPrice SET toDate = @fromDate WHERE mealID = @id AND toDate IS NULL
		INSERT INTO tblPrice VALUES (@id, @price, @fromDate, NULL)
	END
GO


--report
--
--get total revenue
IF OBJECT_ID('getTotalRevenue', 'P') IS NOT NULL
	DROP PROCEDURE getTotalRevenue
GO
CREATE PROCEDURE getTotalRevenue
AS
	DECLARE @revenue DECIMAL(18, 2)
	SET @revenue = 0
	
	DECLARE @orderID VARCHAR(50)
	DECLARE [order] CURSOR
		FOR SELECT id FROM tblOrder 
	OPEN [order]
	FETCH NEXT FROM [order] INTO @orderID
	WHILE @@FETCH_STATUS = 0
	BEGIN
		DECLARE @date DATE
		SELECT @date = [date]
		FROM tblOrder
		WHERE id = @orderID

		DECLARE @total DECIMAL(18, 2)
		SET @total = 0
		
		DECLARE @mealID VARCHAR(20)
		DECLARE @quantity DECIMAL(18, 2)
		DECLARE @price DECIMAL(18, 2)
		
		DECLARE detail CURSOR
			FOR SELECT mealID, quantity FROM tblOrderDetail WHERE orderID = @orderID
		OPEN detail
		FETCH NEXT FROM detail INTO @mealID, @quantity
		WHILE @@FETCH_STATUS = 0
		BEGIN
			SELECT @price = price
			FROM tblPrice
			WHERE mealID = @mealID AND @date >= fromDate AND (@date <= toDate OR toDate IS NULL)
			
			SET @total = @total + @price * @quantity
			
			FETCH NEXT FROM detail INTO @mealID, @quantity
		END
		CLOSE detail
		DEALLOCATE detail
		
		SET @revenue = @revenue + @total
		
		FETCH NEXT FROM [order] INTO @orderID
	END
	CLOSE [order]
	DEALLOCATE [order]
	
	SELECT @revenue AS revenue
GO

--Get Revenue Per Day
IF OBJECT_ID('getRevenuePerDay', 'P') IS NOT NULL
	DROP PROCEDURE getRevenuePerDay 
GO
CREATE PROCEDURE getRevenuePerDay
AS
	DECLARE @revenueDay DECIMAL(18, 2)
	SET @revenueDay = 0
	
	DECLARE @date DATE
	DECLARE allDate CURSOR FOR	SELECT DISTINCT [date]
								FROM tblOrder
	OPEN allDate
	FETCH NEXT FROM allDate INTO @date
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SELECT @revenueDay = SUM(r.price)
		FROM
			(SELECT no, s.mealID, s.quantity * r.price AS price
			FROM tblOrderDetail s,
				(SELECT mealID, price
				FROM tblPrice
				WHERE @date > fromDate AND (@date < toDate OR toDate IS NULL)) r
			WHERE orderID IN
				(SELECT id
				FROM tblOrder
				WHERE [date] = @date)
				AND s.mealID = r.mealID) r
				
		SELECT @revenueDay AS Revenue, @date AS [Date]
		
		FETCH NEXT FROM allDate INTO @date
	END
	CLOSE allDate
	DEALLOCATE allDate
GO


--Get most popular item
IF OBJECT_ID('getMostPopularItem', 'P') IS NOT NULL
	DROP PROCEDURE getMostPopularItem 
GO
CREATE PROCEDURE getMostPopularItem
AS
	SELECT name, r.times
	FROM tblMeal,
		(SELECT mealID, COUNT(*) AS times
		FROM tblOrderDetail
		GROUP BY mealID
		HAVING COUNT(*) =
			(SELECT MAX(r.times)
			FROM 
				(SELECT COUNT(*) AS times
				FROM tblOrderDetail
				GROUP BY mealID) r)) r
	WHERE id = r.mealID
GO

--get busboy table
IF OBJECT_ID('getBusboyEfficiency', 'P') IS NOT NULL
	DROP PROCEDURE getBusboyEfficiency 
GO
CREATE PROCEDURE getBusboyEfficiency
AS
	SELECT r.busboyID, name, r.[tables]
	FROM tblStaff,
		(SELECT busboyID, COUNT(*) AS [tables]
		FROM tblClean
		GROUP BY busboyID) r
	WHERE id = r.busboyID
	ORDER BY r.[tables] DESC
GO

--get waiter orders
IF OBJECT_ID('getWaiterEfficiency', 'P') IS NOT NULL
	DROP PROCEDURE getWaiterEfficiency 
GO
CREATE PROCEDURE getWaiterEfficiency
AS
	SELECT r.waiterID, name, r.orders
	FROM tblStaff,
		(SELECT waiterID, COUNT(*) AS orders
		FROM tblOrder
		GROUP BY waiterID) r
	WHERE r.waiterID = id
	ORDER BY r.orders DESC
GO

--get cook meals
IF OBJECT_ID('getKitchenEfficiency', 'P') IS NOT NULL
	DROP PROCEDURE getKitchenEfficiency 
GO
CREATE PROCEDURE getKitchenEfficiency
AS
	SELECT r.cookID, name, r.meals
	FROM tblStaff,
		(SELECT cookID, COUNT(*) AS meals
		FROM tblOrderDetail
		GROUP BY cookID) r
	WHERE id = r.cookID
	ORDER BY r.meals DESC
GO

--avg turn around time
IF OBJECT_ID('getAvgTurnAroundTime', 'P') IS NOT NULL
	DROP PROCEDURE getAvgTurnAroundTime 
GO
CREATE PROCEDURE getAvgTurnAroundTime
AS
	SELECT AVG(r.[Minutes]) AS avgMinutes
	FROM
		(SELECT DATEDIFF(MI, arrivingTime, leavingTime) AS [Minutes]
		FROM tblOrder) r
GO

--avg preparation time
IF OBJECT_ID('getAvgPreparationTime', 'P') IS NOT NULL
	DROP PROCEDURE getAvgPreparationTime 
GO
CREATE PROCEDURE getAvgPreparationTime
AS
	SELECT AVG(r.[minutes]) AS avgMinutes
	FROM
		(SELECT DATEDIFF(MI, takenTime, readyTime) AS [minutes]
		FROM tblOrderDetail) r
GO

--ADDITION
--
--
--
--take menu
IF OBJECT_ID('getMenu', 'P') IS NOT NULL
	DROP PROCEDURE getMenu 
GO
CREATE PROCEDURE getMenu
AS
	SELECT id, name, unit, price
	FROM tblMeal, tblPrice
	WHERE isAvailable = 1 AND id = mealID AND toDate IS NULL
GO

--load Meal
IF OBJECT_ID('loadAllMeals', 'P') IS NOT NULL
	DROP PROCEDURE loadAllMeals 
GO
CREATE PROCEDURE loadAllMeals
AS
	SELECT m.id, m.name, m.unit, p.price, m.cateID
	FROM tblMeal m, tblPrice p
	WHERE m.id = p.mealID AND isAvailable = 1 AND p.toDate IS NULL
GO

--load Category
IF OBJECT_ID('loadCategory', 'P') IS NOT NULL
	DROP PROCEDURE loadCategory 
GO
CREATE PROCEDURE loadCategory
AS
	SELECT *
	FROM tblCategory
GO