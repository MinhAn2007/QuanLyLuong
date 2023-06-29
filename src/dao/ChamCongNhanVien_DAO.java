package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChamCongNhanVien;
import entity.NhanVien;

public class ChamCongNhanVien_DAO {
	private ArrayList<ChamCongNhanVien> dsChamCongNV;

	public ChamCongNhanVien_DAO() {
		dsChamCongNV = new ArrayList<ChamCongNhanVien>();
	}

	public List<entity.ChamCongNhanVien> getDanhSachChamCongNhanVien() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChamCongNhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maChamCongNV = rs.getString(1);
				Date ngayCham = rs.getDate(2);
				boolean vangMat = rs.getBoolean(3);
				boolean coPhep = rs.getBoolean(4);
				String ghiChu = rs.getString(5);
				int soGioTangCa = rs.getInt(6);
				String maNV = rs.getString(7);

				ChamCongNhanVien ccnv = new ChamCongNhanVien(maChamCongNV, ngayCham, vangMat, coPhep, ghiChu,
						soGioTangCa, new NhanVien(maNV));
				dsChamCongNV.add(ccnv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChamCongNV;
	}

	public void insertChamCongNV(ChamCongNhanVien ccnv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "INSERT into ChamCongNhanVien VALUES(?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, ccnv.getMaChamCongNV());
			statement.setDate(2, ccnv.getNgayCham());
			statement.setBoolean(3, ccnv.isVangMat());
			statement.setBoolean(4, ccnv.isCoPhep());
			statement.setString(5, ccnv.getGhiChu());
			statement.setInt(6, ccnv.getSoGioTangCa());
			statement.setString(7, ccnv.getNhanVien().getMaNhanVien());

			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public ArrayList<entity.ChamCongNhanVien> getDanhSachChamCongNhanVienHomNay() {
		ArrayList<ChamCongNhanVien> dsDaChamCongNV = new ArrayList<ChamCongNhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT *\r\n" + "	FROM ChamCongNhanVien \r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maChamCongNV = rs.getString(1);
				Date ngayCham = rs.getDate(2);
				boolean vangMat = rs.getBoolean(3);
				boolean coPhep = rs.getBoolean(4);
				String ghiChu = rs.getString(5);
				int soGioTangCa = rs.getInt(6);
				String maNV = rs.getString(7);

				ChamCongNhanVien ccnv = new ChamCongNhanVien(maChamCongNV, ngayCham, vangMat, coPhep, ghiChu,
						soGioTangCa, new NhanVien(maNV));
				dsDaChamCongNV.add(ccnv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDaChamCongNV;
	}

	public int getSoLuongChamCongNV() {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) AS Total FROM ChamCongNhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count + 1;
	}
	
	public int getSoLuongChamCongNVHomNay() {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) As Total FROM ChamCongNhanVien\r\n"
					+ "WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham)\r\n"
					+ "AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void updateChamCongNhanVienHomNay(ChamCongNhanVien ccnv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "UPDATE ChamCongNhanVien\r\n"
					+ "SET vangMat = ?, coPhep = ?, ghiChu = ?, soGioTangCa = ?\r\n"
					+ "WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)\r\n"
					+ "	AND maNhanVien = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, ccnv.isVangMat());
			statement.setBoolean(2, ccnv.isCoPhep());
			statement.setString(3, ccnv.getGhiChu());
			statement.setInt(4, ccnv.getSoGioTangCa());
			statement.setString(5, ccnv.getNhanVien().getMaNhanVien());

			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}
}
