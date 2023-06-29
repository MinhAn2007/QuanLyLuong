package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CongNhan;
import entity.TaiKhoan;

public class CongNhan_DAO {

	public ArrayList<CongNhan> getAllCongNhan() {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from CongNhan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString(1);
				String tenCN = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				Date ngayBatDauLamViec = rs.getDate(5);
				String CMND = rs.getString(6);
				String diaChi = rs.getString(7);
				String soDienThoai = rs.getString(8);
				String troCap = rs.getString(9);
				String tenDangNhap = rs.getString(10);
				boolean trangThai = rs.getBoolean(11);

				CongNhan cn = new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi,
						soDienThoai, troCap, new TaiKhoan(tenDangNhap), trangThai);

				dsCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}

	public CongNhan getCongNhan(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		CongNhan cn = null;
		try {
			String sql = "Select * from CongNhan Where maCongNhan = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCongNhan = rs.getString(1);
				String tenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				Date ngayBatDauLamViec = rs.getDate(5);
				String CMND = rs.getString(6);
				String diaChi = rs.getString(7);
				String soDienThoai = rs.getString(8);
				String troCap = rs.getString(9);
				String tenDangNhap = rs.getString(10);
				boolean trangThai = rs.getBoolean(11);
				cn = new CongNhan(maCongNhan, tenNV, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, soDienThoai,
						troCap, new TaiKhoan(tenDangNhap), trangThai);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cn;

	}

	public int getSoLuongCongNhan() {
		return getAllCongNhan().size();
	}

	public ArrayList<CongNhan> search(String maCNFromTable, String hoTenCongNhanFromTable,
			java.util.Date ngayBatDauLamVien, java.util.Date ngaySinh2, String CMNDFormTable,
			boolean gioiTinhFormTable) {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;

		String maCN = null;
		String tenCN = null;
		String ngayBatDauLamViec = null;
		String ngaySinh = null;
		String CMND = null;
		if (maCNFromTable == null) {
			maCN = "%%";
		} else
			maCN = "%" + maCNFromTable + "%";

		if (hoTenCongNhanFromTable == null) {
			tenCN = "%%";
		} else
			tenCN = "%" + hoTenCongNhanFromTable + "%";
		if (ngayBatDauLamVien == null) {
			ngayBatDauLamViec = "%%";
		} else
			ngayBatDauLamViec = "%" + ngayBatDauLamVien + "%";
		if (ngaySinh2 == null) {
			ngaySinh = "%%";
		} else
			ngaySinh = "%" + ngaySinh2 + "%";
		if (CMNDFormTable == null) {
			CMND = "%%";
		} else
			CMND = "%" + CMNDFormTable + "%";
		try {
			String sql = "select * from CongNhan where maCongNhan like ? and hoTenCongNhan like ? and ngayBatDauLamViec like ? and ngaySinh like ? and CMND like ? and gioiTinh = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			stmt.setString(2, tenCN);
			stmt.setString(3, ngayBatDauLamViec);
			stmt.setString(4, ngaySinh);
			stmt.setString(5, CMND);
			stmt.setString(6, gioiTinhFormTable ? "0" : "1");
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				String tenDangNhap = rs.getString(10);
				CongNhan cn = new CongNhan(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						new TaiKhoan(tenDangNhap), rs.getBoolean(11));

				dsCN.add(cn);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;

	}

	public boolean create(CongNhan cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into CongNhan values(?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,?)");
			stmt.setString(1, cn.getMaCongNhan());
			stmt.setString(2, cn.getHoTenCongNhan());
			stmt.setBoolean(3, cn.isGioiTinh());
			stmt.setDate(4, cn.getNgaySinh());
			stmt.setDate(5, cn.getNgayBatDauLamViec());
			stmt.setString(6, cn.getCMND());
			stmt.setString(7, cn.getDiaChi());
			stmt.setString(8, cn.getSoDienThoai());
			stmt.setString(9, cn.getTroCap());
			stmt.setString(10, cn.getTaiKhoan().getTenDangNhap());
			stmt.setBoolean(11, cn.isTrangThai());
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String mlop) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from BangLuongCongNhan where maCongNhan = ?");
			stmt.setString(1, mlop);
			n = stmt.executeUpdate();
			stmt2 = con.prepareStatement("delete from ChamCongCongDoan where maCongNhan = ?");
			stmt2.setString(1, mlop);
			n = stmt2.executeUpdate();
			stmt3 = con.prepareStatement("delete from CongNhan where maCongNhan = ?");
			stmt3.setString(1, mlop);
			n = stmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean update(CongNhan cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update CongNhan set hoTenCongNhan = ?, gioiTinh = ?,ngayBatDauLamViec = ?, "
					+ "ngaySinh = ? , CMND = ?, diaChi = ? , soDienThoai = ?, troCap = ? " + "where maCongNhan = ?");
			stmt.setString(1, cn.getHoTenCongNhan());
			stmt.setBoolean(2, cn.isGioiTinh());
			stmt.setDate(3, cn.getNgayBatDauLamViec());
			stmt.setDate(4, cn.getNgaySinh());
			stmt.setString(5, cn.getCMND());
			stmt.setString(6, cn.getDiaChi());
			stmt.setString(7, cn.getSoDienThoai());
			stmt.setString(8, cn.getTroCap());
			stmt.setString(9, cn.getMaCongNhan());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public void setTrangThaiCongNhan(boolean trangThai, String maCN) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "UPDATE CongNhan\r\n"
					+ "	SET trangThai = ?\r\n"
					+ "	WHERE maCongNhan = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, trangThai);
			statement.setString(2, maCN);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getDanhSachMaCN() {
		ArrayList<String> listMaCN = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "	SELECT DISTINCT maCongNhan FROM CongNhan\r\n";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString("maCongNhan");
				listMaCN.add(maCN);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMaCN;
	}
}
