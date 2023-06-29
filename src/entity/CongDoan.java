package entity;

import java.util.Objects;

public class CongDoan {
	private String maCongDoan;
	private String tenCongDoan;
	private double giaCongDoan;
	private int soLuong;
	private SanPham SanPham;
	public CongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CongDoan(String maCongDoan, String tenCongDoan, double giaCongDoan, int soLuong, entity.SanPham sanPham) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.giaCongDoan = giaCongDoan;
		this.soLuong = soLuong;
		SanPham = sanPham;
	}
	public CongDoan(String maCongDoan) {
		super();
		this.maCongDoan = maCongDoan;
	}
	public String getMaCongDoan() {
		return maCongDoan;
	}
	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public double getGiaCongDoan() {
		return giaCongDoan;
	}
	public void setGiaCongDoan(double giaCongDoan) {
		this.giaCongDoan = giaCongDoan;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public SanPham getSanPham() {
		return SanPham;
	}
	public void setSanPham(SanPham sanPham) {
		SanPham = sanPham;
	}
	@Override
	public int hashCode() {
		return Objects.hash(tenCongDoan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoan other = (CongDoan) obj;
		return Objects.equals(tenCongDoan, other.tenCongDoan);
	}

	
	
}
