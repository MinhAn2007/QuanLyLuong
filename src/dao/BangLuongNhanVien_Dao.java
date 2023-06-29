package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChamCongNhanVien;
import entity.CongDoan;
import entity.BangLuongNhanVien;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class BangLuongNhanVien_Dao {

	private ArrayList<BangLuongNhanVien> dsLNV;

	public BangLuongNhanVien_Dao() {
		dsLNV = new ArrayList<BangLuongNhanVien>();
	}

	public ArrayList<BangLuongNhanVien> getAllBangLuongNhanVien() {

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Exec proc_BangluongNV 0";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLuong = rs.getString(1);
				String thang = rs.getString(2);
				String nam = rs.getString(3);
				int BangLuongNhanVien = rs.getInt(6);
				String maNhanVien = rs.getString(5);

				BangLuongNhanVien lnv = new BangLuongNhanVien(maLuong, BangLuongNhanVien, thang, nam,
						new NhanVien(maNhanVien));

				dsLNV.add(lnv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLNV;
	}

	public ArrayList<ChamCongNhanVien> getDetailLuong(String maNhanVien) {
		ArrayList<ChamCongNhanVien> dsDetail = new ArrayList<ChamCongNhanVien>();
		BangLuongNhanVien Detail;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from ChamCongNhanVien where maNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			ResultSet rs = stmt.executeQuery();
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
				dsDetail.add(ccnv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsDetail;
	}

}


