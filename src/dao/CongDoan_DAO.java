package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CongDoan;

import entity.SanPham;

public class CongDoan_DAO {

	public ArrayList<CongDoan> getCongDoanTheoSanPham(String maSanPham) {
		ArrayList<CongDoan> dscd = new ArrayList<CongDoan>();
		CongDoan cd;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from CongDoan where maSanPham = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maSanPham);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCongDoan = rs.getString(1);
				String tenCongDoan = rs.getString(2);
				double giaCongDoan = rs.getDouble(3);
				int soLuong = rs.getInt(4);
				String maSP = rs.getString(5);
				cd = new CongDoan(maCongDoan, tenCongDoan, giaCongDoan, soLuong, new SanPham(maSP));
				dscd.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dscd;
	}

	public ArrayList<String> getTenCongDoan() {
		String tenCongDoan = null;
		ArrayList<String> dsTenCongDoan = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT DISTINCT tenCongDoan from CongDoan";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				tenCongDoan = rs.getString("tenCongDoan");
				dsTenCongDoan.add(tenCongDoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTenCongDoan;
	}

	public boolean create(CongDoan cd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into CongDoan values(?, ?, ?, ?, ?)");
			stmt.setString(1, cd.getMaCongDoan());
			stmt.setString(2, cd.getTenCongDoan());
			stmt.setDouble(3, cd.getGiaCongDoan());
			stmt.setInt(4, cd.getSoLuong());
			stmt.setString(5, cd.getSanPham().getMaSanPham());
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public int getSoLuongCongDoan(String maCD) {
		int soLuong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "	SELECT soLuong FROM CongDoan WHERE maCongDoan = ?\r\n";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("soLuong");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soLuong;
	}

	public ArrayList<Object> getCongDoanCongNhan(String maCongNhan) {
		ArrayList<Object> cdcn = new ArrayList<Object>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT [maCongDoan], ngayCham\r\n" + "      ,tongSL = sum(soLuong)\r\n"
					+ "	FROM [QuanLyLuong].[dbo].[ChamCongCongDoan]\r\n" + "	where maCongNhan = ?\r\n"
					+ "	group by maCongDoan, ngayCham";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCongNhan);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCongDoan = rs.getString(1);
				String tongSL = rs.getString(3);
				String ngayChamString = rs.getString(2);
				cdcn.add(maCongDoan);
				cdcn.add(tongSL);
				cdcn.add(ngayChamString);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(cdcn.get(1));
		return cdcn;
	}

}
