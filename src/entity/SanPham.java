package entity;

import java.util.Objects;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private String kieuDang;
	private String chatLieu;
	private int soLuong;
	private Boolean trangThai;
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSanPham, String tenSanPham, String kieuDang, String chatLieu, int soLuong,
			Boolean trangThai) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.kieuDang = kieuDang;
		this.chatLieu = chatLieu;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
	}
	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getKieuDang() {
		return kieuDang;
	}
	public void setKieuDang(String kieuDang) {
		this.kieuDang = kieuDang;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Boolean getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}
	
}
