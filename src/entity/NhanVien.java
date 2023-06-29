package entity;

import java.sql.Date;

public class NhanVien {
	private String maNhanVien;
	private String hoTenNhanVien;
	private boolean gioiTinh;
	private Date ngaySinh;
	private Date ngayBatDauLamViec;
	private String CMND;
	private String diaChi;
	private String troCap;
	private String soDienThoai;
	private float heSoLuong;
	private TaiKhoan taiKhoan;

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}

	public void setHoTenNhanVien(String hoTenNhanVien) {
		this.hoTenNhanVien = hoTenNhanVien;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayBatDauLamViec() {
		return ngayBatDauLamViec;
	}

	public void setNgayBatDauLamViec(Date ngayBatDauLamViec) {
		this.ngayBatDauLamViec = ngayBatDauLamViec;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTroCap() {
		return troCap;
	}

	public void setTroCap(String troCap) {
		this.troCap = troCap;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public NhanVien(String maNhanVien, String hoTenNhanVien, boolean gioiTinh, Date ngaySinh, Date ngayBatDauLamViec,
			String cMND, String diaChi, String troCap, String soDienThoai, float heSoLuong, TaiKhoan taiKhoan) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTenNhanVien = hoTenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayBatDauLamViec = ngayBatDauLamViec;
		CMND = cMND;
		this.diaChi = diaChi;
		this.troCap = troCap;
		this.soDienThoai = soDienThoai;
		this.heSoLuong = heSoLuong;
		this.taiKhoan = taiKhoan;
	}

	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", ngayBatDauLamViec=" + ngayBatDauLamViec + ", CMND=" + CMND + ", diaChi="
				+ diaChi + ", troCap=" + troCap + ", soDienThoai=" + soDienThoai + ", heSoLuong=" + heSoLuong
				+ ", taiKhoan=" + taiKhoan + "]";
	}

}
