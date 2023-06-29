--Create Database
use master

drop Database QuanLyLuong

	Create Database QuanLyLuong

	go 
 
	use QuanLyLuong

	go 

	--Create table and key 

	--TaiKhoan

	CREATE TABLE TaiKhoan
	(
		tenDangNhap char(20) primary key not null,
		matKhau nvarchar(20) not null,
	)

	go 

	--NhanVien

	CREATE TABLE NhanVien
	(
		maNhanVien char(5) primary key not null,
		hoTenNhanVien nvarchar(70) not null,
		gioiTinh bit not null,
		ngaySinh date not null,
		ngayBatDauLamViec date not null, 
		CMND nvarchar(20) not null, 
		diaChi nvarchar (20) not null,
		troCap nvarchar(20),
		soDienThoai nvarchar (20) not null,
		heSoluong float not null,
		tenDangNhap Char(20) not null FOREIGN KEY REFERENCES TaiKhoan(tenDangNhap),
	)
	go

	CREATE TABLE BangLuongNhanVien
	(
		maLuongNV char(10) primary key not null,
		thang int not null,
		nam int not null,
		luong money not null,
		maNhanVien char (5) not null FOREIGN KEY REFERENCES NhanVien(maNhanVien),
	)

	CREATE TABLE ChamCongNhanVien
	(
		maChamCongNV char(10) primary key not null,
		ngayCham date not null, 
		vangMat bit not null,
		coPhep bit,
		ghiChu nvarchar(50),
		soGioTangCa int,
		maNhanVien char (5) not null FOREIGN KEY REFERENCES NhanVien(maNhanVien),

	)

	go

	--CongNhan

	CREATE TABLE CongNhan
	(
		maCongNhan char(5) primary key not null,
		hoTenCongNhan nvarchar (70) not null,
		gioiTinh bit not null,
		ngaySinh date not null,
		ngayBatDauLamViec date not null, 
		CMND nvarchar(20) not null, 
		diaChi nvarchar (20) not null,
		soDienThoai nvarchar (20) not null,
		troCap nvarchar(20),
		tenDangNhap Char(20) not null FOREIGN KEY REFERENCES TaiKhoan(tenDangNhap),
		trangThai bit
	)

	go 

	CREATE TABLE BangLuongCongNhan
	(
		maLuongCN char(10) primary key not null,
		thang int not null,
		nam int not null, 
		luong money not null,
		maCongNhan char (5) not null FOREIGN KEY REFERENCES CongNhan(maCongNhan),

	)

	go 


	--SanPham

	CREATE TABLE SanPham
	(
		maSanPham char(10) primary key not null ,
		tenSanPham nvarchar(20) not null,
		kieuDang nvarchar(20) not null,
		chatLieu nvarchar(20) not null,
		soLuong int not null,
		trangThai bit ,
	)

	go

	CREATE TABLE CongDoan
	(
		maCongDoan char(10) primary key not null,
		tenCongDoan nvarchar(20) not null,
		giaCongDoan money not null,
		soLuong int not null,
		maSanPham char(10) not null FOREIGN KEY REFERENCES SanPham(maSanPham),

	)

	go 

	CREATE TABLE ChamCongCongDoan 
	(
		maChamCongCD char(10) PRIMARY KEY,
		maCongDoan char(10) not null FOREIGN KEY REFERENCES CongDoan(maCongDoan),
		maCongNhan char (5) not null FOREIGN KEY REFERENCES CongNhan(maCongNhan),
		ngayCham datetime,
		vangMat bit,
		coPhep bit,
		caLam nvarchar(20),
		soLuong int,
	)
 
	go

	--Insert Value
	--TaiKhoan

	INSERT INTO TaiKhoan VALUES (N'ADMIN' ,1234);
	INSERT INTO TaiKhoan VALUES (N'QLNS' ,123);
	INSERT INTO TaiKhoan VALUES (N'QLSP' ,123);
	INSERT INTO TaiKhoan VALUES (N'KETOAN' ,123);

	--NhanVien

	INSERT INTO NhanVien VALUES (N'NV001',N'Võ Ngọc Minh An',0,'1992-10-20','2015-10-20',391626128,N'Long An',N'Hộ Nghèo',N'0123456789',2.34,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV002',N'Nguyễn Đức Chiến',0,'1993-11-20','2015-08-20',391626138,N'TP HCM',null,N'0123456789',1.86,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV003',N'Nguyễn Lê Mỹ Châu',1,'1980-12-20','2015-05-20',391626148,N'TP HCM',null,N'0123456789',2.1,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV004',N'Nguyễn Thanh Tuấn',0,'1987-01-20','2015-01-20',3916261218,N'An Giang',N'Gia đình khó khăn',N'0123456789',2.34,N'QLNS') ;

	INSERT INTO NhanVien VALUES (N'NV005',N'Nguyễn Văn Tài',0,'1992-10-20','2015-10-20',391626178,N'Long An',N'Hộ Nghèo',N'0123456789',2.34,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV006',N'Nguyễn Đức Tèo',0,'1993-11-20','2015-08-20',391626138,N'TP HCM',null,N'0123456789',1.86,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV007',N'Nguyễn Lê Mỹ ',1,'1980-12-20','2015-05-20',391626148,N'TP HCM',null,N'0123456789',2.1,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV008',N'Nguyễn Thanh Tèo',0,'1987-01-20','2015-01-20',3916261218,N'An Giang',N'Gia đình khó khăn',N'0123456789',2.34,N'QLNS') ;

	INSERT INTO NhanVien VALUES (N'NV009',N'Võ Văn Đức',0,'1992-10-20','2015-10-20',391626128,N'Long An',N'Hộ Nghèo',N'0123456789',2.34,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV010',N'Nguyễn Văn Tí',0,'1993-11-20','2015-08-20',391626138,N'TP HCM',null,N'0123456789',1.86,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV011',N'Nguyễn Mỹ Châu',1,'1980-12-20','2015-05-20',391626148,N'TP HCM',null,N'0123456789',2.1,N'QLNS') ;
	INSERT INTO NhanVien VALUES (N'NV012',N'Nguyễn Thanh Tâm',0,'1987-01-20','2015-01-20',3916261218,N'An Giang',N'Gia đình khó khăn',N'0123456789',2.34,N'QLNS') ;

	--BangLuongNhanVien

	INSERT INTO BangLuongNhanVien VALUES (N'BLNV001',7,2022,9000000,N'NV001');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV002',8,2022,9500000,N'NV002');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV003',9,2022,9400000,N'NV003');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV004',7,2022,4500000,N'NV004');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV005',7,2022,9000000,N'NV005');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV006',2,2022,9500000,N'NV006');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV007',3,2022,3600000,N'NV007');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV008',1,2022,9300000,N'NV008');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV009',5,2022,5000000,N'NV009');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV010',6,2022,3500000,N'NV010');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV011',9,2022,9400000,N'NV011');
	INSERT INTO BangLuongNhanVien VALUES (N'BLNV012',7,2022,1000000,N'NV012');

	--ChamCongNhanVien
	INSERT INTO ChamCongNhanVien VALUES (N'CNNV001','2022-01-01',1,null,null,2,N'NV001');
	INSERT INTO ChamCongNhanVien VALUES (N'CNNV002','2022-01-01',0,1,null,null,N'NV002');
	INSERT INTO ChamCongNhanVien VALUES (N'CNNV003','2022-01-01',0,0,'Vắng không phép',null,N'NV003');
	INSERT INTO ChamCongNhanVien VALUES (N'CNNV004','2022-01-01',1,null,null,null,N'NV004');


	--CongNhan
	INSERT INTO CongNhan VALUES (N'CN001',N'Mark Xoăn',0,'1999-07-20','2013-10-20',391626121,N'Long An',N'0123456789',N'Hộ Nghèo',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN002',N'Đoàn Văn Hậu',1,'1999-09-20','2020-9-20',291626121,N'Thái Bình',N'0123456789',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN003',N'Nguyễn Thị Nở',0,'1990-11-20','2012-10-20',392626121,N'Cà Mau',N'0123456789',N'Phụ sản',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN004',N'Chí Phèo',0,'1990-07-20','2013-11-20',391626122,N'Long An',N'0123456789',N'Gia đình khó khăn',N'QLNS',0) ;
	
	INSERT INTO CongNhan VALUES (N'CN005',N'Võ Ngọc Minh An',0,'1992-10-20','2015-10-20',391626121,N'Long An',N'0123456789',N'Hộ Nghèo',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN006',N'Nguyễn Đức Chiến',0,'1993-11-20','2015-08-20',391626191,N'Tiền Giang',N'0123456789',N'Gia đình khó khăn',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN007',N'Nguyễn Lê Mỹ Châu',1,'1980-12-20','2015-05-20',391126121,N'Long An',N'0123456789',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN008',N'Nguyễn Thanh Tuấn',0,'1987-01-20','2015-01-20',311626121,N'Bạc Liêu',N'0123456789',N'Phụ sản',N'QLNS',0) ;

	INSERT INTO CongNhan VALUES (N'CN009',N'Nguyễn Văn Tài',0,'1992-10-20','2015-10-20',311626121,N'Bạc Liêu',N'0123456759',N'Hộ Nghèo',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN010',N'Nguyễn Đức Tèo',0,'1993-11-20','2015-08-20',311626921,N'TP HCM',N'0123456389',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN011',N'Nguyễn Lê Mỹ ',1,'1980-12-20','2015-05-20',311626111,N'Đà Lạt',N'0123456789',N'Gia đình khó khăn',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN012',N'Nguyễn Thanh Tèo',0,'1987-01-20','2015-01-20',311676121,N'Nha Trang',N'0123456789',null,N'QLNS',0) ;

	INSERT INTO CongNhan VALUES (N'CN013',N'Võ Văn Đức',0,'1992-10-20','2015-10-20',391626128,N'Nha Trang',N'0123456789',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN014',N'Nguyễn Văn Tí',0,'1993-11-20','2015-08-20',391626138,N'Đắk Lắk',N'0123456789',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN015',N'Nguyễn Mỹ Châu',1,'1980-12-20','2015-05-20',391626148,N'Kom Tum',N'0123456789',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN016',N'Nguyễn Thanh Tâm',0,'1987-01-20','2015-01-20',3916261218,N'Vinh',N'0123456789',N'Gia đình khó khăn',N'QLNS',0) ;

	INSERT INTO CongNhan VALUES (N'CN017',N'Võ Văn Đức',0,'1992-10-20','2015-10-20',391626128,N'Cà Mau',N'0123456759',N'Hộ Nghèo',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN018',N'Nguyễn Văn Tí',0,'1993-11-20','2015-08-20',391626138,N'Bạc Liêu',N'0123456759',N'Hộ Nghèo',N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN019',N'Nguyễn Mỹ Châu',1,'1980-12-20','2015-05-20',391626148,N'Kiên Giang',N'0123456759',null,N'QLNS',0);
	INSERT INTO CongNhan VALUES (N'CN020',N'Nguyễn Thanh Tâm',0,'1987-01-20','2015-01-20',3916261218,N'An Giang',N'0123456759',N'Hộ Nghèo',N'QLNS',0) ;

	INSERT INTO CongNhan VALUES (N'CN021',N'Võ Văn Đức',0,'1992-10-20','2015-10-20',391626128,N'Bạc Liêu',N'0123456759',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN022',N'Nguyễn Văn Tí',0,'1993-11-20','2015-08-20',391626138,N'Vĩnh Phúc',N'0123456759',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN023',N'Nguyễn Mỹ Châu',1,'1980-12-20','2015-05-20',391626148,N'TP HCM',N'0123456759',null,N'QLNS',0) ;
	INSERT INTO CongNhan VALUES (N'CN024',N'Nguyễn Thanh Tâm',0,'1987-01-20','2015-01-20',3916261218,N'An Giang',N'0123456759',null,N'QLNS',0) ;
	--BangLuongCongNhan

	INSERT INTO BangLuongCongNhan VALUES (N'BLCN001',7,2022,9000000,N'CN001');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN002',8,2022,9500000,N'CN002');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN003',9,2022,9400000,N'CN003');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN004',7,2022,9300000,N'CN004');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN005',6,2022,7500000,N'CN005');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN006',5,2022,9500000,N'CN006');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN007',5,2022,9400000,N'CN007');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN008',4,2022,9300000,N'CN008');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN009',3,2022,7500000,N'CN009');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN010',2,2022,9500000,N'CN010');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN011',1,2022,8600000,N'CN011');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN012',2,2022,9300000,N'CN012');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN013',3,2022,5500000,N'CN013');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN014',4,2022,9500000,N'CN014');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN015',5,2022,9400000,N'CN015');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN016',6,2022,9300000,N'CN016');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN017',7,2022,4500000,N'CN017');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN018',8,2022,9500000,N'CN018');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN019',9,2022,2600000,N'CN019');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN020',10,2022,9300000,N'CN020');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN021',11,2022,6500000,N'CN021');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN022',12,2022,9500000,N'CN022');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN023',10,2022,9400000,N'CN023');
	INSERT INTO BangLuongCongNhan VALUES (N'BLCN024',7,2022,1000000,N'CN024');




	--SanPham

	INSERT INTO SanPham VALUES (N'SP001',N'Nike Air Force 1',N'Giày thể thao',N'Vải da tổng hợp',500,1);
	INSERT INTO SanPham VALUES (N'SP002',N'Cap Toe Oxford',N'Giày tây',N'Da thật',200,0);
	INSERT INTO SanPham VALUES (N'SP003',N'Chelsea boot',N'Boot',N'Da tổng hợp',300,0);

	--CongDoan

	INSERT INTO CongDoan VALUES (N'CDSP001001',N'Lên khuôn giày',10000,500,N'SP001');
	INSERT INTO CongDoan VALUES (N'CDSP001002',N'Hoàn thiện mũi giày',10000,500,N'SP001');
	INSERT INTO CongDoan VALUES (N'CDSP001003',N'Khâu dập',10000,500,N'SP001');
	INSERT INTO CongDoan VALUES (N'CDSP001004',N'Hoàn thiện đế giày',10000,500,N'SP001');

	INSERT INTO CongDoan VALUES (N'CDSP002001',N'Lên khuôn giày',11000,200,N'SP002');
	INSERT INTO CongDoan VALUES (N'CDSP002002',N'Hoàn thiện mũi giày',11000,200,N'SP002');
	INSERT INTO CongDoan VALUES (N'CDSP002003',N'Khâu dập',11000,300,N'SP002');
	INSERT INTO CongDoan VALUES (N'CDSP002004',N'Hoàn thiện đế giày',11000,300,N'SP002');

	INSERT INTO CongDoan VALUES (N'CDSP003001',N'Lên khuôn giày',12000,200,N'SP003');
	INSERT INTO CongDoan VALUES (N'CDSP003002',N'Hoàn thiện mũi giày',12000,200,N'SP003');
	INSERT INTO CongDoan VALUES (N'CDSP003003',N'Khâu dập',12000,200,N'SP003');
	INSERT INTO CongDoan VALUES (N'CDSP003004',N'Hoàn thiện đế giày',12000,200,N'SP003');



		-- Exec Bang lương Nhan vien
  GO
  create VIEW vw_BangluongNv AS
  select 
		[maLuongNV]
      ,[thang]
      ,[nam]
      ,[vangMat]
      ,ChamCongNhanVien.maNhanVien,
	luongNV = COUNT(vangMat)*120000 
  from [QuanLyLuong].[dbo].[BangLuongNhanVien] , [QuanLyLuong].[dbo].[ChamCongNhanVien]
  Where BangLuongNhanVien.maNhanVien = ChamCongNhanVien.maNhanVien

  group by vangMat, ChamCongNhanVien.maNhanVien, thang, nam , maLuongNV
  go

  create  PROC proc_BangluongNV
  @vang bit
  as select * from vw_BangluongNv
  Where vangMat like  @vang 
  go
  -- 
  Exec proc_BangluongNV 0;
  Go

	-------------------------
	----------------------

	----------------------
	--------------------
	-- Exec Bang lương Cong Nhan
  GO
  create VIEW vw_BangluongCn AS
  select 
		BangLuongCongNhan.maLuongCN
      ,[thang]
      ,[nam]
      ,BangLuongCongNhan.maCongNhan,
	  ChamCongCongDoan.soLuong,
	  tongSL= COUNT(ChamCongCongDoan.soLuong),
	luongCN = sum(ChamCongCongDoan.soLuong* CongDoan.giaCongDoan)

	
  from [QuanLyLuong].[dbo].[BangLuongCongNhan] , 
  [QuanLyLuong].[dbo].[ChamCongCongDoan], 
  [QuanLyLuong].[dbo].[CongDoan]
 Where BangLuongCongNhan.maCongNhan =  ChamCongCongDoan.maCongNhan 

  group by BangLuongCongNhan.maCongNhan, thang, nam , BangLuongCongNhan.maLuongCN, ChamCongCongDoan.soLuong
  --vangMat,
  
  go

  create  PROC proc_BangluongCN

  as select * from vw_BangluongCn

  go
  -- 
  Exec proc_BangluongCN ;
  Go


   drop VIEW vw_BangluongCn
   drop  PROC proc_BangluongCN


   -------------
   -------------