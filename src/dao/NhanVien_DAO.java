package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsCN = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien";
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
				Float heSoLuong = rs.getFloat(10);
				String tenDangNhap = rs.getString(11);

				NhanVien nv = new NhanVien(maCN, tenCN, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, troCap,
						soDienThoai, heSoLuong, new TaiKhoan(tenDangNhap));

				dsCN.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}

	public NhanVien getNhanVien(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		NhanVien nv = null;
		try {
			String sql = "Select * from NhanVien Where maNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				Date ngayBatDauLamViec = rs.getDate(5);
				String CMND = rs.getString(6);
				String diaChi = rs.getString(7);
				String troCap = rs.getString(8);
				String soDienThoai = rs.getString(9);
				float heSoLuong = rs.getFloat(10);
				String tenDangNhap = rs.getString(11);

				nv = new NhanVien(maNhanVien, tenNV, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, troCap,
						soDienThoai, heSoLuong, new TaiKhoan(tenDangNhap));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;

	}

	public int getSoLuongNhanVien() {
		return getAllNhanVien().size();
	}

	public ArrayList<NhanVien> search(String maNVFromTable, String hoTenNhanVienFromTable,
			java.util.Date ngayBatDauLamVien, java.util.Date ngaySinh2, String CMNDFormTable,
			boolean gioiTinhFormTable) {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;

		String maNV = null;
		String tenNV = null;
		String ngayBatDauLamViec = null;
		String ngaySinh = null;
		String CMND = null;
		if (maNVFromTable == null) {
			maNV = "%%";
		} else
			maNV = "%" + maNVFromTable + "%";

		if (hoTenNhanVienFromTable == null) {
			tenNV = "%%";
		} else
			tenNV = "%" + hoTenNhanVienFromTable + "%";
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
			String sql = "select * from NhanVien where maNhanVien like ? and hoTenNhanVien like ? and ngayBatDauLamViec like ? and ngaySinh like ? and CMND like ? and gioiTinh = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setString(2, tenNV);
			stmt.setString(3, ngayBatDauLamViec);
			stmt.setString(4, ngaySinh);
			stmt.setString(5, CMND);
			stmt.setString(6, gioiTinhFormTable ? "0" : "1");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getFloat(10), new TaiKhoan(rs.getString(11)));

				dsNV.add(nv);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;

	}

	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,?)");
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoTenNhanVien());
			stmt.setBoolean(3, nv.isGioiTinh());
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setDate(5, nv.getNgayBatDauLamViec());
			stmt.setString(6, nv.getCMND());
			stmt.setString(7, nv.getDiaChi());
			stmt.setString(8, nv.getTroCap());
			stmt.setString(9, nv.getSoDienThoai());
			stmt.setFloat(10, nv.getHeSoLuong());
			stmt.setString(11, nv.getTaiKhoan().getTenDangNhap());
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt3 = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from BangLuongNhanVien where maNhanVien = ?");
			stmt.setString(1, maNV);
			n = stmt.executeUpdate();
			stmt1 = con.prepareStatement("delete from ChamCongNhanVien where maNhanVien = ?");
			stmt1.setString(1, maNV);
			n = stmt1.executeUpdate();
			stmt3 = con.prepareStatement("delete from NhanVien where maNhanVien = ?");
			stmt3.setString(1, maNV);
			n = stmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set hoTenNhanVien = ?, gioiTinh = ?,ngayBatDauLamViec = ?, "
					+ "ngaySinh = ? , CMND = ?, diaChi = ? , soDienThoai = ?, troCap = ? , heSoLuong = ? where maNhanVien = ?");
			stmt.setString(1, nv.getHoTenNhanVien());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setDate(3, nv.getNgayBatDauLamViec());
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setString(5, nv.getCMND());
			stmt.setString(6, nv.getDiaChi());
			stmt.setString(7, nv.getSoDienThoai());
			stmt.setString(8, nv.getTroCap());
			stmt.setFloat(9, nv.getHeSoLuong());
			stmt.setString(10, nv.getMaNhanVien());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

}
