package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPham_DAO {

	List<entity.SanPham> dssp;

	public SanPham_DAO() {
		dssp = new ArrayList<entity.SanPham>();

	}

	public List<entity.SanPham> getAllSanPham() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from SanPham";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				String maSanPham = rs.getString(1);
				String tenSanPham = rs.getString(2);
				String kieuDang = rs.getString(3);
				String chatLieu = rs.getString(4);
				int soLuong = rs.getInt(5);
				boolean trangThai = rs.getBoolean(6);
				SanPham s = new SanPham(maSanPham, tenSanPham, kieuDang, chatLieu, soLuong, trangThai);

				dssp.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// ?óng kết nối
		}
		return dssp;
	}
	
	public boolean update(String maSP, boolean trangThai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update SanPham set trangThai = ? where maSanPham = ?");
			stmt.setBoolean(1, trangThai);
			stmt.setString(2, maSP);

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public int getSoLuongSP() {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM SanPham";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				count = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<String> getKieuDang() {
		String kieuDang = null;
		ArrayList<String> dsKieuDang = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT DISTINCT kieuDang from SanPham";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				kieuDang = rs.getString("kieuDang");
				dsKieuDang.add(kieuDang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKieuDang;
	}
	public ArrayList<String> getTenSP() {
		String tenSP = null;
		ArrayList<String> dsTenSP = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT DISTINCT tenSanPham from SanPham";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				tenSP = rs.getString("tenSanPham");
				dsTenSP.add(tenSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTenSP;
	}

	public boolean create(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into SanPham values(?, ?, ?, ? ,? ,?)");
			stmt.setString(1, sp.getMaSanPham());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setString(3, sp.getKieuDang());
			stmt.setString(4, sp.getChatLieu());
			stmt.setInt(5, sp.getSoLuong());
			stmt.setBoolean(6, sp.getTrangThai());
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
