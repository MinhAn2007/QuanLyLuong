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
import entity.CongDoan;
import entity.CongNhan;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class ThongKe_DAO {
		public ArrayList<NhanVien> getAllNhanVienTK() {
			ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
			try {
				ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				String sql = "Select * from NhanVien";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String naNV = rs.getString(1);
					String tenNV = rs.getString(2);
					boolean gioiTinh = rs.getBoolean(3);
					Date ngaySinh = rs.getDate(4);
					Date ngayBatDauLamViec = rs.getDate(5);
					String CMND = rs.getString(6);
					String diaChi = rs.getString(7);
					String soDienThoai = rs.getString(8);
					String troCap = rs.getString(9);
					Float heSoLuong = rs.getFloat(10);
					String tenDangNhap = rs.getString(11);

					NhanVien nv = new NhanVien(naNV, tenNV, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, troCap,
							soDienThoai, heSoLuong, new TaiKhoan(tenDangNhap));

					dsNV.add(nv);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dsNV;
	}
	
	public ArrayList<CongNhan> getAllCongNhanTK() {
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
	
	List<entity.SanPham> dssp;

	public ThongKe_DAO() {
		dssp = new ArrayList<entity.SanPham>();

	}

	public List<entity.SanPham> getAllSanPhamTK() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from SanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
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
		}
		return dssp;
	}
	
	public double getLuongNV(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		double luong = 0;
		try {
			String sql = "	select luong from BangLuongNhanVien where maNhanVien = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				luong = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return luong;
	}
	
	public double getThangNV(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int thang = 0;
		try {
			String sql = "	select thang from BangLuongNhanVien where maNhanVien = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				thang = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thang;
	}
	
	public double getNamNV(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int nam = 0;
		try {
			String sql = "	select nam from BangLuongNhanVien where maNhanVien = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nam = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nam;
	}
	
	public double getLuongCN(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		double luong = 0;
		try {
			String sql = "	select luong from BangLuongCongNhan where maCongNhan = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				luong = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return luong;

	}
	
	public double getThangCN(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int thang = 0;
		try {
			String sql = "	select thang from BangLuongCongNhan where maCongNhan = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				thang = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thang;
	}
	
	public double getNamCN(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int nam = 0;
		try {
			String sql = "	select nam from BangLuongCongNhan where maCongNhan = ? \r\n"
					+ "";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nam = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nam;
	}
	
	public ArrayList<CongDoan> getCongDoanTheoTenSanPham(String tenSanPham) {
		ArrayList<CongDoan> dscd = new ArrayList<CongDoan>();
		CongDoan cd;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from CongDoan as cd inner join SanPham as sp on cd.maSanPham = sp.maSanPham where sp.tenSanPham like ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenSanPham);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCongDoan = rs.getString(1);
				String tenCongDoan = rs.getString(2);
				double giaCongDoan = rs.getDouble(3);
				int soLuong = rs.getInt(4);
				String tenSP = rs.getString(5);
				cd = new CongDoan(maCongDoan, tenCongDoan, giaCongDoan, soLuong, new SanPham(tenSP));
				dscd.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dscd;
	}
	
	public String getMaSanPham(String tenSP) {
		String maSanPham = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select maSanPham from SanPham where tenSanPham like ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				maSanPham = rs.getString("maSanPham");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maSanPham;
	}
	
	public ArrayList<String> getMaCongNhanTK(int thang, int nam) {
		String maCongNhanTK = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<String> dsMaCongNhan = new ArrayList<String>();
		PreparedStatement stmt = null;
		try {
			String sql = "select maCongNhan from BangLuongCongNhan where thang like ? and nam like ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				maCongNhanTK = rs.getString("maCongNhan");
				dsMaCongNhan.add(maCongNhanTK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMaCongNhan;
	}
	
	public ArrayList<String> getTenSanPhamTK() {
		String tenSanPham = null;
		ArrayList<String> dsTenSanPham = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT DISTINCT tenSanPham from SanPham";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả v ? đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả v ?.
			while (rs.next()) {// Di chuyển con tr ? xuống bản ghi kế tiếp.
				tenSanPham = rs.getString("tenSanPham");
				dsTenSanPham.add(tenSanPham);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTenSanPham;
	}
	
	public ArrayList<NhanVien> getNhanVienTheoThangNam(int thang, int nam) {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		NhanVien nv;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from NhanVien as nv inner join BangLuongNhanVien as blnv on nv.maNhanVien = blnv.maNhanVien where blnv.thang like ? and blnv.nam like ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String naNV = rs.getString(1);
				String tenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				Date ngayBatDauLamViec = rs.getDate(5);
				String CMND = rs.getString(6);
				String diaChi = rs.getString(7);
				String soDienThoai = rs.getString(8);
				String troCap = rs.getString(9);
				Float heSoLuong = rs.getFloat(10);
				String tenDangNhap = rs.getString(11);
				nv = new NhanVien(naNV, tenNV, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, troCap,
						soDienThoai, heSoLuong, new TaiKhoan(tenDangNhap));

				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsnv;
	}
	
	public ArrayList<CongNhan> getCongNhanTheoThangNam(int thang, int nam) {
		ArrayList<CongNhan> dscn = new ArrayList<CongNhan>();
		CongNhan cn;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from CongNhan as cn inner join BangLuongCongNhan as blcn on cn.maCongNhan = blcn.maCongNhan where blcn.thang like ? and blcn.nam like ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
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
				cn = new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi,
						soDienThoai, troCap, new TaiKhoan(tenDangNhap), trangThai);
				dscn.add(cn);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dscn;
	}
}
