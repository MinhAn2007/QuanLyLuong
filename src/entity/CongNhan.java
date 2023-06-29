package entity;

import java.sql.Date;

public class CongNhan {
	private String maCongNhan;
	private String hoTenCongNhan;
	private boolean gioiTinh;
	private Date ngaySinh;
	private Date ngayBatDauLamViec;
	private String CMND;
	private String diaChi;
	private String soDienThoai;
	private String troCap;
	private TaiKhoan taiKhoan;
	private boolean trangThai;
	public CongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CongNhan(String maCongNhan, String hoTenCongNhan, boolean gioiTinh, Date ngaySinh, Date ngayBatDauLamViec,
			String cMND, String diaChi, String soDienThoai, String troCap, TaiKhoan taiKhoan, boolean trangThai) {
		super();
		this.maCongNhan = maCongNhan;
		this.hoTenCongNhan = hoTenCongNhan;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayBatDauLamViec = ngayBatDauLamViec;
		CMND = cMND;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.troCap = troCap;
		this.taiKhoan = taiKhoan;
		this.trangThai = trangThai;
	}
	public CongNhan(String maCongNhan) {
		super();
		this.maCongNhan = maCongNhan;
	}
	public String getMaCongNhan() {
		return maCongNhan;
	}
	public void setMaCongNhan(String maCongNhan) {
		this.maCongNhan = maCongNhan;
	}
	public String getHoTenCongNhan() {
		return hoTenCongNhan;
	}
	public void setHoTenCongNhan(String hoTenCongNhan) {
		this.hoTenCongNhan = hoTenCongNhan;
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
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getTroCap() {
		return troCap;
	}
	public void setTroCap(String troCap) {
		this.troCap = troCap;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	
}
