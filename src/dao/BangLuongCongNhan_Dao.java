package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

import entity.CongNhan;
import entity.BangLuongCongNhan;


public class BangLuongCongNhan_Dao {
	private ArrayList<BangLuongCongNhan> dsLCN ;
	public BangLuongCongNhan_Dao() {
		dsLCN = new ArrayList<BangLuongCongNhan>();
		}
	
	public ArrayList<BangLuongCongNhan> getAllBangLuongCongNhan() {
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Exec proc_BangluongCN 0";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLuong = rs.getString(1);
				String thang = rs.getString(2);
				String nam = rs.getString(3);
				int luong = rs.getInt(7);
				String maCongNhan = rs.getString(5);
				BangLuongCongNhan lcn = new BangLuongCongNhan(maLuong, luong, thang, nam, new CongNhan(maCongNhan));
//				BangLuongCongNhan lcn = new BangLuongCongNhan(maLuong, luong, thang,nam , new CongNhan(maCongNhan));

				dsLCN.add(lcn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLCN;
	}

	
//	public ArrayList<entity.BangLuongCongNhan> getDanhSachLuongCongNhan() {
//		ArrayList<BangLuongCongNhan> dsBangLuongCongNhan = new ArrayList<BangLuongCongNhan>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT *\r\n"
//					+ "	FROM ChamCongCongNhan \r\n"
//					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
//					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
//					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maChamCongCN = rs.getString(1);
//				String maCN = rs.getString(2);
//				Date ngayCham = rs.getDate(3);
//				boolean vangMat = rs.getBoolean(4);
//				boolean coPhep = rs.getBoolean(5);
//				String caLam = rs.getString(6);
//				String ghiChu = rs.getString(7);
//
//				ChamCongCongNhan cccn = new ChamCongCongNhan(maChamCongCN, new CongNhan(maCN), ngayCham, vangMat,
//						coPhep, caLam, ghiChu);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dsBangLuongCongNhan;
//	}
//	
	
}
