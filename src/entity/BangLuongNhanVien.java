package entity;

public class BangLuongNhanVien {
	private String maLuongNV;
		private int luong;
	private String thang;
	private String nam;

	private NhanVien nhanVien;

	public BangLuongNhanVien(String maLuongNV, int luong, String thang, String nam, NhanVien nhanVien) {
		super();
		this.maLuongNV = maLuongNV;
		this.luong = luong;
		this.thang = thang;
		this.nam = nam;
		this.nhanVien = nhanVien;
	}
	
	public BangLuongNhanVien() {
		super();
	}

	public String getMaLuongNV() {
		return maLuongNV;
	}

	public void setMaLuongNV(String maLuongNV) {
		this.maLuongNV = maLuongNV;
	}

	public int getLuong() {
		return luong;
	}

	public void setLuong(int luong) {
		this.luong = luong;
	}

	public String getThang() {
		return thang;
	}

	public void setThang(String thang) {
		this.thang = thang;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "LuongNv [maLuongNV=" + maLuongNV + ", luong=" + luong + ", thang=" + thang + ", nam=" + nam
				+ ", nhanVien=" + nhanVien + "]";
	}
	
	
	
	
	
}
