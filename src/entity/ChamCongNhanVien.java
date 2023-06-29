package entity;

import java.sql.Date;

public class ChamCongNhanVien {
	private String maChamCongNV;
	private Date ngayCham;
	private boolean vangMat;
	private boolean coPhep;
	private String ghiChu;
	private int soGioTangCa;
	private NhanVien nhanVien;

	public String getMaChamCongNV() {
		return maChamCongNV;
	}

	public void setMaChamCongNV(String maChamCongNV) {
		this.maChamCongNV = maChamCongNV;
	}

	public Date getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(Date ngayCham) {
		this.ngayCham = ngayCham;
	}

	public boolean isVangMat() {
		return vangMat;
	}

	public void setVangMat(boolean vangMat) {
		this.vangMat = vangMat;
	}

	public boolean isCoPhep() {
		return coPhep;
	}

	public void setCoPhep(boolean coPhep) {
		this.coPhep = coPhep;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getSoGioTangCa() {
		return soGioTangCa;
	}

	public void setSoGioTangCa(int soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public ChamCongNhanVien(String maChamCongNV, Date ngayCham, boolean vangMat, boolean coPhep, String ghiChu,
			int soGioTangCa, NhanVien nhanVien) {
		super();
		this.maChamCongNV = maChamCongNV;
		this.ngayCham = ngayCham;
		this.vangMat = vangMat;
		this.coPhep = coPhep;
		this.ghiChu = ghiChu;
		this.soGioTangCa = soGioTangCa;
		this.nhanVien = nhanVien;
	}

	public ChamCongNhanVien() {
		super();
	}

	public ChamCongNhanVien(String maChamCongNV) {
		super();
		this.maChamCongNV = maChamCongNV;
	}
	
	

	public ChamCongNhanVien(boolean vangMat, boolean coPhep, String ghiChu, int soGioTangCa, NhanVien nhanVien) {
		super();
		this.vangMat = vangMat;
		this.coPhep = coPhep;
		this.ghiChu = ghiChu;
		this.soGioTangCa = soGioTangCa;
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "ChamCongNhanVien [maChamCongNV=" + maChamCongNV + ", ngayCham=" + ngayCham + ", vangMat=" + vangMat
				+ ", coPhep=" + coPhep + ", ghiChu=" + ghiChu + ", soGioTangCa=" + soGioTangCa + ", nhanVien="
				+ nhanVien + "]";
	}

}
