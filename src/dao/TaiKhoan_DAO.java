package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class TaiKhoan_DAO {
	public boolean login(String username, String password) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from TaiKhoan where tenDangNhap = ? and matKhau = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("true");
				return true;
			} else {
				System.out.println("false");

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void doiMK(String username, String password, String passChange) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "update TaiKhoan set matKhau =  ?  from TaiKhoan where tenDangNhap = ? and matKhau = ?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, passChange);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTK() {
		String taiKhoan = null;
		ArrayList<String> dsTaiKhoan = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select tenDangNhap from taiKhoan";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				taiKhoan = rs.getString("tenDangNhap");
				dsTaiKhoan.add(taiKhoan);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsTaiKhoan;
	}

	public boolean layMK(String username, String passChange) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TaiKhoan set matKhau =  ?  from TaiKhoan where tenDangNhap = ?");
			stmt.setString(1, passChange);
			stmt.setString(2, username);

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public String getMK(String username) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String mk = null;
		try {
			String sql = "select matKhau from TaiKhoan where tenDangNhap = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				mk = rs.getString("matKhau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mk;

	}
}
