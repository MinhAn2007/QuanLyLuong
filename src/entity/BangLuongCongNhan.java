package entity;

public class BangLuongCongNhan {
	private String maLuongCN;
	private String thang;
	private String nam;
	private int luong;
	private CongNhan congNhan;
	public BangLuongCongNhan(String maLuongCN, int luong, String thang, String nam, CongNhan congNhan) {
		super();
		this.maLuongCN = maLuongCN;
		this.thang = thang;
		this.nam = nam;
		this.luong = luong;
		this.congNhan = congNhan;
	}
	public BangLuongCongNhan() {
		super();
	}
	public String getMaLuongCN() {
		return maLuongCN;
	}
	public void setMaLuongCN(String maLuongCN) {
		this.maLuongCN = maLuongCN;
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
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
		this.luong = luong;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	@Override
	public String toString() {
		return "LuongCn [maLuongCN=" + maLuongCN + ", thang=" + thang + ", nam=" + nam + ", luong=" + luong
				+ ", congNhan=" + congNhan + "]";
	}
	
	
	
	
	
	
	

}
