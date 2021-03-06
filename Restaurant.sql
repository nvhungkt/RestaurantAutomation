USE [master]
GO
/****** Object:  Database [AutomationRestaurant]    Script Date: 06/21/2017 11:11:33 ******/
CREATE DATABASE [AutomationRestaurant] ON  PRIMARY 
( NAME = N'AutomationRestaurant', FILENAME = N'D:\MSSQL\Data\AutomationRestaurant.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'AutomationRestaurant_log', FILENAME = N'D:\MSSQL\Data\AutomationRestaurant_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [AutomationRestaurant] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AutomationRestaurant].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AutomationRestaurant] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [AutomationRestaurant] SET ANSI_NULLS OFF
GO
ALTER DATABASE [AutomationRestaurant] SET ANSI_PADDING OFF
GO
ALTER DATABASE [AutomationRestaurant] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [AutomationRestaurant] SET ARITHABORT OFF
GO
ALTER DATABASE [AutomationRestaurant] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [AutomationRestaurant] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [AutomationRestaurant] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [AutomationRestaurant] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [AutomationRestaurant] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [AutomationRestaurant] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [AutomationRestaurant] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [AutomationRestaurant] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [AutomationRestaurant] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [AutomationRestaurant] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [AutomationRestaurant] SET  DISABLE_BROKER
GO
ALTER DATABASE [AutomationRestaurant] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [AutomationRestaurant] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [AutomationRestaurant] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [AutomationRestaurant] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [AutomationRestaurant] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [AutomationRestaurant] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [AutomationRestaurant] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [AutomationRestaurant] SET  READ_WRITE
GO
ALTER DATABASE [AutomationRestaurant] SET RECOVERY SIMPLE
GO
ALTER DATABASE [AutomationRestaurant] SET  MULTI_USER
GO
ALTER DATABASE [AutomationRestaurant] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [AutomationRestaurant] SET DB_CHAINING OFF
GO
USE [AutomationRestaurant]
GO
/****** Object:  Table [dbo].[tblTable]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblTable](
	[number] [int] NOT NULL,
	[capacity] [int] NULL,
	[status] [varchar](10) NULL,
	[isActive] [bit] NULL,
 CONSTRAINT [PK_tblTable] PRIMARY KEY CLUSTERED 
(
	[number] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (1, 4, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (2, 4, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (3, 2, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (4, 6, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (5, 8, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (6, 8, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (7, 6, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (8, 4, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (9, 2, NULL, NULL)
INSERT [dbo].[tblTable] ([number], [capacity], [status], [isActive]) VALUES (10, 2, NULL, NULL)
/****** Object:  Table [dbo].[tblStaff]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblStaff](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](50) NULL,
	[joinDate] [date] NULL,
	[role] [varchar](20) NULL,
	[isActive] [bit] NULL,
 CONSTRAINT [PK_tblStaff] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'001', N'Hermione', NULL, N'Manager', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'002', N'Molly', NULL, N'Kitchen', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'003', N'Fleur', NULL, N'Host', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'004', N'Ginny', NULL, N'Host', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'005', N'Luna', NULL, N'Host', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'006', N'Bill', NULL, N'Kitchen', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'007', N'Gabrielle', NULL, N'Kitchen', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'008', N'Harry', NULL, N'Waiter', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'009', N'Ron', NULL, N'Waiter', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'010', N'Malfoy', NULL, N'Waiter', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'011', N'Dudley', NULL, N'Kitchen', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'012', N'Seamus', NULL, N'Waiter', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'013', N'Dean', NULL, N'Waiter', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'014', N'Lee', NULL, N'Bus boy', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'015', N'George', NULL, N'Bus boy', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'016', N'Neville', NULL, N'Bus boy', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'017', N'Charlie', NULL, N'Kitchen', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'018', N'Percy', NULL, N'Bus boy', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'019', N'Albus', NULL, N'Bus boy', NULL)
INSERT [dbo].[tblStaff] ([id], [name], [joinDate], [role], [isActive]) VALUES (N'020', N'Scorpius', NULL, N'Kitchen', NULL)
/****** Object:  Table [dbo].[tblCategory]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCategory](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrder](
	[id] [varchar](50) NOT NULL,
	[tableNumber] [int] NULL,
	[waiterID] [varchar](20) NULL,
	[customerName] [nvarchar](50) NULL,
	[date] [date] NULL,
	[arriveTime] [time](7) NULL,
	[leaveTime] [time](7) NULL,
	[status] [varchar](20) NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'26051701', 1, N'008', N'Alice', CAST(0xDA3C0B00 AS Date), CAST(0x070050CFDF960000 AS Time), CAST(0x07009E22299D0000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'26051702', 2, N'010', N'Cody', CAST(0xDA3C0B00 AS Date), CAST(0x0700AE9F92970000 AS Time), CAST(0x07009E22299D0000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'26051703', 3, N'009', N'Caroline', CAST(0xDA3C0B00 AS Date), CAST(0x0700C810AB990000 AS Time), CAST(0x0700EC7572A30000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'26051704', 4, N'012', N'Alexander', CAST(0xDA3C0B00 AS Date), CAST(0x0700B893419F0000 AS Time), CAST(0x0700881C05B00000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'26051705', 8, N'013', N'Rapunzel', CAST(0xDA3C0B00 AS Date), CAST(0x07002058A3A70000 AS Time), CAST(0x0700881C05B00000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'27051701', 2, N'012', N'Snow White', CAST(0xDB3C0B00 AS Date), CAST(0x07000C7045980000 AS Time), CAST(0x0700B893419F0000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'27051702', 5, N'008', N'Cinderella', CAST(0xDB3C0B00 AS Date), CAST(0x07006A40F8980000 AS Time), CAST(0x070030D50CA20000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'27051703', 4, N'010', N'Bella', CAST(0xDB3C0B00 AS Date), CAST(0x070084B1109B0000 AS Time), CAST(0x07007434A7A00000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051701', 7, N'010', N'Eve', CAST(0xDC3C0B00 AS Date), CAST(0x0700B893419F0000 AS Time), CAST(0x07002058A3A70000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051702', 9, N'008', N'Tarzan', CAST(0xDC3C0B00 AS Date), CAST(0x07007434A7A00000 AS Time), CAST(0x07003AC9BBA90000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051703', 5, N'009', N'Oliver', CAST(0xDC3C0B00 AS Date), CAST(0x070006E78AA50000 AS Time), CAST(0x0700881C05B00000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051704', 3, N'012', N'Isabella', CAST(0xDC3C0B00 AS Date), CAST(0x07002058A3A70000 AS Time), CAST(0x0700005ED0B20000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051705', 2, N'013', N'Kim', CAST(0xDC3C0B00 AS Date), CAST(0x0700543AD4AB0000 AS Time), CAST(0x0700BCFE35B40000 AS Time), N'1')
INSERT [dbo].[tblOrder] ([id], [tableNumber], [waiterID], [customerName], [date], [arriveTime], [leaveTime], [status]) VALUES (N'28051706', 1, N'010', N'Jake Long', CAST(0xDC3C0B00 AS Date), CAST(0x0700B20A87AC0000 AS Time), CAST(0x0700BCFE35B40000 AS Time), N'1')
/****** Object:  Table [dbo].[tblMeal]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblMeal](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](50) NULL,
	[unit] [varchar](20) NULL,
	[isAvailable] [bit] NULL,
	[cateID] [varchar](20) NULL,
 CONSTRAINT [PK_tblMeal] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'001', N'Sushi-storm medley', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'002', N'Chicken curry', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'003', N'Spicy Sichuan tofu', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'004', N'Oriental egg fried rice', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'005', N'Texas BBQ medley', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'006', N'Tropical pizza', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'007', N'Lasagna', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'008', N'Chicken rice', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'009', N'Pho', N'bowl', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'010', N'Pad Thai', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'011', N'Egg tart', N'pack', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'012', N'French fried', N'pack', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'013', N'Hamburger mac''n', N'pack', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'014', N'Hawaii fruit medley', N'plate', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'015', N'Aquafina', N'bottle', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'016', N'Milo', N'bottle', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'017', N'Fami', N'bottle', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'018', N'7up', N'bottle', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'019', N'Coke', N'bottle', 1, NULL)
INSERT [dbo].[tblMeal] ([id], [name], [unit], [isAvailable], [cateID]) VALUES (N'020', N'Pepsi', N'bottle', 1, NULL)
/****** Object:  Table [dbo].[tblClean]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblClean](
	[tableNumber] [int] NOT NULL,
	[time] [datetime] NOT NULL,
	[busboyID] [varchar](20) NULL,
 CONSTRAINT [PK_tblClean] PRIMARY KEY CLUSTERED 
(
	[tableNumber] ASC,
	[time] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblClean] ([tableNumber], [time], [busboyID]) VALUES (1, CAST(0x0000A79201156B1A AS DateTime), N'014')
/****** Object:  Table [dbo].[tblPrice]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblPrice](
	[mealID] [varchar](20) NOT NULL,
	[price] [decimal](18, 2) NULL,
	[fromDate] [date] NOT NULL,
	[toDate] [date] NULL,
 CONSTRAINT [PK_tblPrice] PRIMARY KEY CLUSTERED 
(
	[mealID] ASC,
	[fromDate] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 06/21/2017 11:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderID] [varchar](50) NOT NULL,
	[no] [varchar](10) NOT NULL,
	[mealID] [varchar](20) NULL,
	[quantity] [decimal](18, 2) NULL,
	[takenTime] [time](7) NULL,
	[readyTime] [time](7) NULL,
	[status] [varchar](20) NULL,
	[kitchenID] [varchar](20) NULL,
 CONSTRAINT [PK_tblCook] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[no] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051701', N'01', N'001', CAST(1.00 AS Decimal(18, 2)), CAST(0x070050CFDF960000 AS Time), CAST(0x0700AE9F92970000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051701', N'02', N'003', CAST(2.00 AS Decimal(18, 2)), CAST(0x070050CFDF960000 AS Time), CAST(0x0700F462B6970000 AS Time), N'1', N'007')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051701', N'03', N'004', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700AE9F92970000 AS Time), CAST(0x070080E9FD970000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051702', N'01', N'002', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700AE9F92970000 AS Time), CAST(0x07006A40F8980000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051702', N'02', N'010', CAST(3.00 AS Decimal(18, 2)), CAST(0x0700AE9F92970000 AS Time), CAST(0x0700B0031C990000 AS Time), N'1', N'017')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051703', N'01', N'005', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700C810AB990000 AS Time), CAST(0x070026E15D9A0000 AS Time), N'1', N'020')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051703', N'02', N'007', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700C810AB990000 AS Time), CAST(0x0700F82AC99A0000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051703', N'03', N'006', CAST(1.00 AS Decimal(18, 2)), CAST(0x070026E15D9A0000 AS Time), CAST(0x0700CA74349B0000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051703', N'04', N'009', CAST(1.00 AS Decimal(18, 2)), CAST(0x070026E15D9A0000 AS Time), CAST(0x070084B1109B0000 AS Time), N'1', N'017')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051704', N'01', N'002', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700B893419F0000 AS Time), CAST(0x0700D0A0D09F0000 AS Time), N'1', N'007')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051704', N'02', N'003', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700B893419F0000 AS Time), CAST(0x07001664F49F0000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051705', N'01', N'005', CAST(4.00 AS Decimal(18, 2)), CAST(0x07002058A3A70000 AS Time), CAST(0x0700DCF808A90000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051705', N'02', N'001', CAST(1.00 AS Decimal(18, 2)), CAST(0x07002058A3A70000 AS Time), CAST(0x070022BC2CA90000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'26051705', N'03', N'010', CAST(2.00 AS Decimal(18, 2)), CAST(0x07002058A3A70000 AS Time), CAST(0x0700687F50A90000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'27051701', N'01', N'007', CAST(4.00 AS Decimal(18, 2)), CAST(0x07000C7045980000 AS Time), CAST(0x070026E15D9A0000 AS Time), N'1', N'020')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'27051701', N'02', N'003', CAST(1.00 AS Decimal(18, 2)), CAST(0x07000C7045980000 AS Time), CAST(0x07000ED4CE990000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'27051702', N'01', N'004', CAST(2.00 AS Decimal(18, 2)), CAST(0x07006A40F8980000 AS Time), CAST(0x07003C8A63990000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'27051703', N'01', N'001', CAST(1.00 AS Decimal(18, 2)), CAST(0x070084B1109B0000 AS Time), CAST(0x0700B4CB2E9C0000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'27051703', N'02', N'005', CAST(2.00 AS Decimal(18, 2)), CAST(0x070084B1109B0000 AS Time), CAST(0x0700FA8E529C0000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051701', N'01', N'012', CAST(3.00 AS Decimal(18, 2)), CAST(0x0700B893419F0000 AS Time), CAST(0x07007434A7A00000 AS Time), N'1', N'007')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051701', N'02', N'014', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700B893419F0000 AS Time), CAST(0x070030D50CA20000 AS Time), N'1', N'011')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051702', N'01', N'002', CAST(2.00 AS Decimal(18, 2)), CAST(0x07007434A7A00000 AS Time), CAST(0x07008C4136A10000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051702', N'02', N'003', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700BAF7CAA00000 AS Time), CAST(0x070018C87DA10000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051702', N'03', N'005', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700BAF7CAA00000 AS Time), CAST(0x07005E8BA1A10000 AS Time), N'1', N'011')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051703', N'01', N'008', CAST(2.00 AS Decimal(18, 2)), CAST(0x070006E78AA50000 AS Time), CAST(0x070064B73DA60000 AS Time), N'1', N'017')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051703', N'02', N'009', CAST(1.00 AS Decimal(18, 2)), CAST(0x070006E78AA50000 AS Time), CAST(0x07002058A3A70000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051704', N'01', N'005', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700661BC7A70000 AS Time), CAST(0x07005072C1A80000 AS Time), N'1', N'011')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051705', N'01', N'004', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700543AD4AB0000 AS Time), CAST(0x0700B20A87AC0000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051705', N'02', N'006', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700543AD4AB0000 AS Time), CAST(0x070010DB39AD0000 AS Time), N'1', N'017')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051705', N'03', N'002', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700543AD4AB0000 AS Time), CAST(0x07003E91CEAC0000 AS Time), N'1', N'020')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051705', N'04', N'010', CAST(4.00 AS Decimal(18, 2)), CAST(0x07009AFDF7AB0000 AS Time), CAST(0x07009C6181AD0000 AS Time), N'1', N'011')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051705', N'05', N'014', CAST(3.00 AS Decimal(18, 2)), CAST(0x0700CC7B9FAE0000 AS Time), CAST(0x0700881C05B00000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051706', N'01', N'003', CAST(2.00 AS Decimal(18, 2)), CAST(0x0700B20A87AC0000 AS Time), CAST(0x07006EABECAD0000 AS Time), N'1', N'006')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051706', N'02', N'004', CAST(1.00 AS Decimal(18, 2)), CAST(0x0700F8CDAAAC0000 AS Time), CAST(0x07008454F2AC0000 AS Time), N'1', N'002')
INSERT [dbo].[tblOrderDetail] ([orderID], [no], [mealID], [quantity], [takenTime], [readyTime], [status], [kitchenID]) VALUES (N'28051706', N'03', N'015', CAST(4.00 AS Decimal(18, 2)), CAST(0x0700F8CDAAAC0000 AS Time), CAST(0x0700569E5DAD0000 AS Time), N'1', N'007')
/****** Object:  ForeignKey [FK_tblOrder_tblStaff]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblStaff] FOREIGN KEY([waiterID])
REFERENCES [dbo].[tblStaff] ([id])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblStaff]
GO
/****** Object:  ForeignKey [FK_tblOrder_tblTable]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblTable] FOREIGN KEY([tableNumber])
REFERENCES [dbo].[tblTable] ([number])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblTable]
GO
/****** Object:  ForeignKey [FK_tblMeal_tblCategory]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblMeal]  WITH CHECK ADD  CONSTRAINT [FK_tblMeal_tblCategory] FOREIGN KEY([cateID])
REFERENCES [dbo].[tblCategory] ([id])
GO
ALTER TABLE [dbo].[tblMeal] CHECK CONSTRAINT [FK_tblMeal_tblCategory]
GO
/****** Object:  ForeignKey [FK_tblClean_tblStaff]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblClean]  WITH CHECK ADD  CONSTRAINT [FK_tblClean_tblStaff] FOREIGN KEY([busboyID])
REFERENCES [dbo].[tblStaff] ([id])
GO
ALTER TABLE [dbo].[tblClean] CHECK CONSTRAINT [FK_tblClean_tblStaff]
GO
/****** Object:  ForeignKey [FK_tblClean_tblTable]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblClean]  WITH CHECK ADD  CONSTRAINT [FK_tblClean_tblTable] FOREIGN KEY([tableNumber])
REFERENCES [dbo].[tblTable] ([number])
GO
ALTER TABLE [dbo].[tblClean] CHECK CONSTRAINT [FK_tblClean_tblTable]
GO
/****** Object:  ForeignKey [FK_tblPrice_tblMeal]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblPrice]  WITH CHECK ADD  CONSTRAINT [FK_tblPrice_tblMeal] FOREIGN KEY([mealID])
REFERENCES [dbo].[tblMeal] ([id])
GO
ALTER TABLE [dbo].[tblPrice] CHECK CONSTRAINT [FK_tblPrice_tblMeal]
GO
/****** Object:  ForeignKey [FK_tblOrderDetail_tblMeal]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblMeal] FOREIGN KEY([mealID])
REFERENCES [dbo].[tblMeal] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblMeal]
GO
/****** Object:  ForeignKey [FK_tblOrderDetail_tblOrder]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
/****** Object:  ForeignKey [FK_tblOrderDetail_tblStaff]    Script Date: 06/21/2017 11:11:35 ******/
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblStaff] FOREIGN KEY([kitchenID])
REFERENCES [dbo].[tblStaff] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblStaff]
GO
