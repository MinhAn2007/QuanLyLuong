package entity;

import java.sql.Date;

public class ChamCongCongDoan {
	private String maChamCongCD;
	private CongDoan congDoan;
	private CongNhan congNhan;
	private Date ngayCham;
	private boolean vangMat;
	private boolean coPhep;
	private String caLam;
	private int soLuong;

	public String getMaChamCongCD() {
		return maChamCongCD;
	}

	public void setMaChamCongCD(String maChamCongCD) {
		this.maChamCongCD = maChamCongCD;
	}

	public CongDoan getCongDoan() {
		return congDoan;
	}

	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public Date getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(Date ngayCham) {
		this.ngayCham = ngayCham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public ChamCongCongDoan() {
		super();
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

	public String getCaLam() {
		return caLam;
	}

	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}

	public ChamCongCongDoan(String maChamCongCD, CongDoan congDoan, CongNhan congNhan) {
		super();
		this.maChamCongCD = maChamCongCD;
		this.congDoan = congDoan;
		this.congNhan = congNhan;
	}

	public ChamCongCongDoan(String maChamCongCD, CongDoan congDoan, CongNhan congNhan, Date ngayCham, boolean vangMat,
			boolean coPhep, String caLam, int soLuong) {
		super();
		this.maChamCongCD = maChamCongCD;
		this.congDoan = congDoan;
		this.congNhan = congNhan;
		this.ngayCham = ngayCham;
		this.vangMat = vangMat;
		this.coPhep = coPhep;
		this.caLam = caLam;
		this.soLuong = soLuong;
	}

	public ChamCongCongDoan(CongDoan congDoan, CongNhan congNhan) {
		super();
		this.congDoan = congDoan;
		this.congNhan = congNhan;
	}

	public ChamCongCongDoan(String maChamCongCD, CongDoan congDoan, CongNhan congNhan, Date ngayCham) {
		super();
		this.maChamCongCD = maChamCongCD;
		this.congDoan = congDoan;
		this.congNhan = congNhan;
		this.ngayCham = ngayCham;
	}
	
	

	public ChamCongCongDoan(CongDoan congDoan, CongNhan congNhan, Date ngayCham, boolean vangMat, boolean coPhep,
			String caLam, int soLuong) {
		super();
		this.congDoan = congDoan;
		this.congNhan = congNhan;
		this.ngayCham = ngayCham;
		this.vangMat = vangMat;
		this.coPhep = coPhep;
		this.caLam = caLam;
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChamCongCongDoan [congDoan=" + congDoan + ", congNhan=" + congNhan + ", ngayCham=" + ngayCham
				+ ", soLuong=" + soLuong + "]";
	}

}
