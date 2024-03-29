USE [master]
GO
/****** Object:  Database [PAWNSHOP]    Script Date: 7/27/2019 11:28:53 AM ******/
CREATE DATABASE [PAWNSHOP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PAWNSHOP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\PAWNSHOP.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PAWNSHOP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\PAWNSHOP_log.ldf' , SIZE = 16576KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PAWNSHOP] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PAWNSHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PAWNSHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PAWNSHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PAWNSHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PAWNSHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PAWNSHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [PAWNSHOP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PAWNSHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PAWNSHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PAWNSHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PAWNSHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PAWNSHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PAWNSHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PAWNSHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PAWNSHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PAWNSHOP] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PAWNSHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PAWNSHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PAWNSHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PAWNSHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PAWNSHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PAWNSHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PAWNSHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PAWNSHOP] SET RECOVERY FULL 
GO
ALTER DATABASE [PAWNSHOP] SET  MULTI_USER 
GO
ALTER DATABASE [PAWNSHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PAWNSHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PAWNSHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PAWNSHOP] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PAWNSHOP] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PAWNSHOP', N'ON'
GO
USE [PAWNSHOP]
GO
/****** Object:  Table [dbo].[Asset]    Script Date: 7/27/2019 11:28:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Asset](
	[AssetId] [int] NOT NULL,
	[ContractId] [int] NOT NULL,
	[PropertyType] [nvarchar](50) NOT NULL,
	[AssetName] [nvarchar](50) NOT NULL,
	[StartDate] [nvarchar](20) NOT NULL,
	[EndDate] [nvarchar](20) NOT NULL,
	[LicensePlate] [nvarchar](50) NULL,
	[ChassisId] [nvarchar](50) NULL,
	[EnginesId] [nvarchar](50) NULL,
	[Imei] [nvarchar](50) NULL,
	[Passcode] [nvarchar](50) NULL,
 CONSTRAINT [PK_Asset] PRIMARY KEY CLUSTERED 
(
	[ContractId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Contract]    Script Date: 7/27/2019 11:28:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contract](
	[ContractId] [int] NOT NULL,
	[CustomerId] [int] NOT NULL,
	[PropertyType] [nvarchar](50) NOT NULL CONSTRAINT [DF_Contract_PropertyType]  DEFAULT ('Xe máy'),
	[AssetName] [nvarchar](50) NOT NULL,
	[TotalLoanAmount] [bigint] NOT NULL CONSTRAINT [DF_Contract_TotalLoanAmount]  DEFAULT ((0)),
	[InterestRate] [bigint] NOT NULL,
	[StartDate] [nvarchar](20) NOT NULL,
	[EndDate] [nvarchar](20) NOT NULL,
	[CreditPeriod] [bigint] NULL,
	[Note] [nvarchar](200) NULL,
	[Cashier] [nvarchar](20) NOT NULL,
	[Status] [int] NOT NULL,
	[ContractImage] [nvarchar](50) NULL,
	[TotalMoney] [bigint] NOT NULL,
	[RedeemAtDay] [nvarchar](20) NULL,
	[StoreId] [int] NOT NULL,
 CONSTRAINT [PK_Contract] PRIMARY KEY CLUSTERED 
(
	[ContractId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 7/27/2019 11:28:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerId] [int] NOT NULL,
	[CustomerName] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](20) NOT NULL,
	[SocialId] [nvarchar](50) NOT NULL,
	[DateRange] [nvarchar](50) NOT NULL,
	[RegisteredPlace] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NULL,
	[IsActive] [int] NOT NULL CONSTRAINT [DF_Customer_IsActive]  DEFAULT ((0)),
	[CustomerImage] [nvarchar](50) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CustomerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Employee]    Script Date: 7/27/2019 11:28:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[EmpId] [int] NOT NULL,
	[Username] [nvarchar](20) NOT NULL,
	[EPassword] [nvarchar](60) NOT NULL,
	[FullName] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](20) NOT NULL,
	[Email] [nvarchar](50) NULL,
	[EAddress] [nvarchar](100) NULL,
	[ERole] [nvarchar](50) NOT NULL CONSTRAINT [DF_Employee_Role]  DEFAULT ('Nhân Viên'),
	[IsActive] [int] NOT NULL CONSTRAINT [DF_Employee_IsActive]  DEFAULT ((1)),
	[StoreId] [int] NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Store]    Script Date: 7/27/2019 11:28:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Store](
	[StoreId] [int] NOT NULL,
	[StoreName] [nvarchar](50) NOT NULL,
	[SAddress] [nvarchar](100) NOT NULL,
	[Representative] [nvarchar](20) NOT NULL,
	[InvestmentCapital] [bigint] NOT NULL,
	[CashFund] [bigint] NOT NULL CONSTRAINT [DF_Store_CashFund]  DEFAULT ((0)),
	[InterestCollected] [bigint] NOT NULL CONSTRAINT [DF_Store_PawnLoan]  DEFAULT ((0)),
 CONSTRAINT [PK_Store] PRIMARY KEY CLUSTERED 
(
	[StoreId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Index [Asset_AssetId_uindex]    Script Date: 7/27/2019 11:28:53 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [Asset_AssetId_uindex] ON [dbo].[Asset]
(
	[AssetId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Asset]  WITH CHECK ADD  CONSTRAINT [FK_Asset_Contract] FOREIGN KEY([ContractId])
REFERENCES [dbo].[Contract] ([ContractId])
GO
ALTER TABLE [dbo].[Asset] CHECK CONSTRAINT [FK_Asset_Contract]
GO
ALTER TABLE [dbo].[Contract]  WITH CHECK ADD  CONSTRAINT [FK_Contract_Customer] FOREIGN KEY([CustomerId])
REFERENCES [dbo].[Customer] ([CustomerId])
GO
ALTER TABLE [dbo].[Contract] CHECK CONSTRAINT [FK_Contract_Customer]
GO
ALTER TABLE [dbo].[Contract]  WITH CHECK ADD  CONSTRAINT [FK_Contract_Store1] FOREIGN KEY([StoreId])
REFERENCES [dbo].[Store] ([StoreId])
GO
ALTER TABLE [dbo].[Contract] CHECK CONSTRAINT [FK_Contract_Store1]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Store] FOREIGN KEY([StoreId])
REFERENCES [dbo].[Store] ([StoreId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Store]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ID tự tăng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'AssetId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hợp đồng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'ContractId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Loại tài sản' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'PropertyType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên tài sản' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'AssetName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày bắt đầu' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'StartDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày kết thúc' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'EndDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Biển kiểm soát' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'LicensePlate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số khung' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'ChassisId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số máy' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'EnginesId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Imei' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'Imei'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Password' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset', @level2type=N'COLUMN',@level2name=N'Passcode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tài sản' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Asset'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hợp đồng tự tăng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'ContractId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã khách hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'CustomerId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Loại tài sản: Xe máy / Ô tô / Điện thoại / Laptop / Vàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'PropertyType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên tài sản' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'AssetName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số tiền cầm' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'TotalLoanAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Lãi theo ngày' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'InterestRate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Thời hạn cầm' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'CreditPeriod'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Nhân viên thu tiền' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'Cashier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 Đang cầm/1 Đã thanh toán/2 Đáo hạn' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Có ảnh hay không?' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'ContractImage'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hợp đồng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ID khách hàng (tự tăng)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'CustomerId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên khách hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'CustomerName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số điện thoại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'PhoneNumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CMND/CCCD/HC' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'SocialId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày cấp' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'DateRange'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Nơi cấp' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'RegisteredPlace'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 Đang hoạt động / 1 Disable' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'IsActive'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Có ảnh hay không?' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer', @level2type=N'COLUMN',@level2name=N'CustomerImage'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Khách hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Customer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Id nhân viên (tự tăng)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'EmpId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên tài khoản' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'Username'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mật khẩu (mã hóa bcrypt) - Độ dài 60' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'EPassword'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Họ và Tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'FullName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số điện thoại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'PhoneNumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Email' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'Email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'EAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Chức năng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'ERole'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Đang làm việc' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'IsActive'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Id cửa hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'StoreId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Nhân viên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Id cửa hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'StoreId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên cửa hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'StoreName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'SAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Người đại diện' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'Representative'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Vốn đầu tư' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'InvestmentCapital'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Quỹ tiền mặt' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'CashFund'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số tiền cầm đồ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store', @level2type=N'COLUMN',@level2name=N'InterestCollected'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Cửa hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Store'
GO
USE [master]
GO
ALTER DATABASE [PAWNSHOP] SET  READ_WRITE 
GO
