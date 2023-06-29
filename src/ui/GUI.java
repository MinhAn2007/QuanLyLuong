/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.BangLuongCongNhan_Dao;
import dao.BangLuongNhanVien_Dao;
import dao.ChamCongCongDoan_DAO;
import dao.ChamCongNhanVien_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.ThongKe_DAO;
import entity.BangLuongCongNhan;
import entity.BangLuongNhanVien;
import entity.ChamCongCongDoan;
import entity.ChamCongNhanVien;
import entity.CongDoan;
import entity.CongNhan;

import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.tabulator.TableCell.CellType;
import net.sf.jasperreports.view.JasperViewer;
import ui.GUI.dialogPhanCongTuDong;

public class GUI extends javax.swing.JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	CardLayout cardLayout;
	private BangLuongNhanVien_Dao luongNhanVien_Dao;
	private BangLuongCongNhan_Dao luongCongNhan_Dao;

	private DefaultTableModel modeluongNhanVien;
	private DefaultTableModel modeluongCongNhan;

	private DefaultTableModel modelNhanVien;
	private NhanVien_DAO nhanVien;
	private DefaultTableModel modelCongNhan;
	private CongNhan_DAO congNhan;
	private DefaultTableModel modelSanPham;
	private SanPham_DAO sanPham;
	private DefaultTableModel modelCongDoanSP;
	private CongDoan_DAO congDoan;
	private DefaultTableModel modelNhanVienTK;
	private DefaultTableModel modelCongNhanTK;
	private DefaultTableModel modelSanPhamTK;
	private DefaultTableModel modelListSP;
	protected DefaultTableModel modelListCDSP;
	protected DefaultTableModel modelChamCongNV;
	private ChamCongNhanVien_DAO chamCongNV;
	protected DefaultTableModel modelListCNChuaPhanCong;
	protected DefaultTableModel modelListCNThucHienCDSP;
	private ChamCongCongDoan_DAO chamCongCD;
	protected int size;
	private DefaultTableModel modelListChamCongSP;
	private DefaultTableModel modelListCCCDSP;
	private DefaultTableModel modelListCNCanChamCong;
	private DefaultTableModel modellistTimKiemCongNhan;
	private DefaultTableModel modelListTimKiemNhanVien;

	private static JComboBox<String> combobox;
	private dialogPhanCongTuDong dialogPhanCongTuDong = new dialogPhanCongTuDong(this,
			"Chọn số lượng phân công tự động");
	private GUI_DangNhap dn_GUI;
	private ThongKe_DAO nhanVienTK;
	private ThongKe_DAO congNhanTK;
	private ThongKe_DAO thongKeSP;

	public GUI() {
		initComponents();
		dn_GUI = new GUI_DangNhap();
		System.out.println(dn_GUI.getRole());
		txtChucVuTC.setEditable(true);
		txtMaNhanVienTC.setEditable(false);
		txtTenNhanVienTC.setEditable(false);
		switch (dn_GUI.getRole()) {
		case "QLNS": {
			txtChucVuTC.setText("Quản lý nhân sự");
			txtMaNhanVienTC.setText("QLNS");
			txtTenNhanVienTC.setText("Nguyễn Thanh Tuấn");
			pnlBangLuongCN.removeAll();
			pnlBangLuongNV.removeAll();
			cardLayout = (CardLayout) (pnlCards.getLayout());
			break;
		}
		case "QLSP": {
			txtChucVuTC.setText("Quản lý sản phẩm");
			txtMaNhanVienTC.setText("QLSP");
			txtTenNhanVienTC.setText("Nguyen Duc Chien");
			cardLayout = (CardLayout) (pnlCards.getLayout());
			break;
		}
		case "KETOAN": {
			txtChucVuTC.setText("Kế toán");
			txtMaNhanVienTC.setText("KETOAN");
			txtTenNhanVienTC.setText("Nguyễn Lê Mỹ Châu");
			pnlDanhSachCN.removeAll();
			pnlDanhSachNV.removeAll();

			cardLayout = (CardLayout) (pnlCards.getLayout());
			break;
		}
		case "ADMIN":
			txtChucVuTC.setText("Quản trị hệ thống");
			txtMaNhanVienTC.setText("ADMIN");
			txtTenNhanVienTC.setText("Võ Ngọc Minh An");
			cardLayout = (CardLayout) (pnlCards.getLayout());
			break;
		}
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {
		ConnectDB.getInstance();

		ConnectDB.getConnection();
		jSplitPane1 = new javax.swing.JSplitPane();
		pnlMenu = new javax.swing.JPanel();
		btnTrangChu = new javax.swing.JButton();
		btnNhanVien = new javax.swing.JButton();
		btnCongNhan = new javax.swing.JButton();
		btnSanPham = new javax.swing.JButton();
		btnThongKe = new javax.swing.JButton();
		pnlCards = new javax.swing.JPanel();
		pnlCongNhan = new javax.swing.JPanel();
		pnlTieuDeCN = new javax.swing.JPanel();
		lblTieuDeCN = new javax.swing.JLabel();
		tbpCN = new javax.swing.JTabbedPane();
		pnlDanhSachCN = new javax.swing.JPanel();
		speDanhSachCN = new javax.swing.JScrollPane();
		tblListCongNhan = new javax.swing.JTable();
		btnThemCN = new javax.swing.JButton();
		btnXoaCN = new javax.swing.JButton();
		btnCapNhatCN = new javax.swing.JButton();
		btnLamMoiCN = new javax.swing.JButton();
		pnlBangLuongCN = new javax.swing.JPanel();
		speBangLuongCN = new javax.swing.JScrollPane();
		tblBangLuongCN = new javax.swing.JTable();
		lblThangCN = new javax.swing.JLabel();
		lblNamCN = new javax.swing.JLabel();
		lblTenSanPhamTK = new javax.swing.JLabel();
		lblNamCongNhanTK = new javax.swing.JLabel();
		lblThangCongNhanTK = new javax.swing.JLabel();
		cboThangCN = new javax.swing.JComboBox<>();
		btnInBangLuongCN = new javax.swing.JButton();
		cboNamCN = new javax.swing.JComboBox<>();
		cboThangCongNhanTK = new javax.swing.JComboBox<>();
		cboNamCongNhanTK = new javax.swing.JComboBox<>();
		cboThangNhanVienTK = new javax.swing.JComboBox<>();
		cboNamNhanVienTK = new javax.swing.JComboBox<>();
		btnCapNhatBangLuongCN = new javax.swing.JButton();
		pnlTimKiemCN = new javax.swing.JPanel();
		speTimKiemCN = new javax.swing.JScrollPane();
		tblTimKiemCN = new javax.swing.JTable();
		lblMaCN = new javax.swing.JLabel();
		lblHoTenCN = new javax.swing.JLabel();
		lblCmndCN = new javax.swing.JLabel();
		txtCmndCN = new javax.swing.JTextField();
		lblNgaySinhCN = new javax.swing.JLabel();
		lblNgayLamVienCN = new javax.swing.JLabel();
		radioBtnNamCN = new javax.swing.JRadioButton();
		radioBtnNuCN = new javax.swing.JRadioButton();
		btnTimCN = new javax.swing.JButton();
		lblGioiTinhCN = new javax.swing.JLabel();
		txtHoTenCN1 = new javax.swing.JTextField();
		dateNgaySinhCN = new com.toedter.calendar.JDateChooser();
		dateNgayLamViecCN = new com.toedter.calendar.JDateChooser();
		txtMaCongNhan = new javax.swing.JTextField();
		pnlSanPham = new javax.swing.JPanel();
		pnlTieuDeSP = new javax.swing.JPanel();
		lblTieuDeSP = new javax.swing.JLabel();
		tbpSP = new javax.swing.JTabbedPane();
		pnlSP = new javax.swing.JPanel();
		speCongDoanSP = new javax.swing.JScrollPane();
		tblCongDoanSP = new javax.swing.JTable();
		speSP = new javax.swing.JScrollPane();
		tblSP = new javax.swing.JTable();
		btnCapNhatSP = new javax.swing.JButton();
		lblMaSP = new javax.swing.JLabel();
		txtMaSP = new javax.swing.JTextField();
		lblTenSP = new javax.swing.JLabel();
		lblSoLuongSP = new javax.swing.JLabel();
		txtSoLuongSP = new javax.swing.JTextField();
		lblTrangThaiSP = new javax.swing.JLabel();
		cbxHoanThanhSP = new javax.swing.JCheckBox();
		btnLuuSP = new javax.swing.JButton();
		btnThemSP = new javax.swing.JButton();
		txtTenSP = new javax.swing.JTextField();
		lblDSCD = new javax.swing.JLabel();
		lblDSSP = new javax.swing.JLabel();
		pnlPhanCongCongDoanSP = new javax.swing.JPanel();
		speDanhSachCNThucHienCongDoanSP = new javax.swing.JScrollPane();
		tblDanhSachCNThucHienCongDoanSP = new javax.swing.JTable();
		speDanhSachSP = new javax.swing.JScrollPane();
		tblDanhSachSP = new javax.swing.JTable();
		lblDanhSachCongDoanSP = new javax.swing.JLabel();
		lblDanhSachCNThucHienCongDoanSP = new javax.swing.JLabel();
		speDanhSachCNChuaPhanCongSP = new javax.swing.JScrollPane();
		tblDanhSachCNChuaPhanCongSP = new javax.swing.JTable();
		speDanhSachCongDoanSP = new javax.swing.JScrollPane();
		tblDanhSachCongDoanSP = new javax.swing.JTable();
		lblDanhSachSP = new javax.swing.JLabel();
		lblDanhSachCNChuaPhanCongSP = new javax.swing.JLabel();
		btnPhanCongThuCongSP = new javax.swing.JButton();
		btnPhanCongTuDongSP = new javax.swing.JButton();
		btnCapNhatPC = new javax.swing.JButton();
		pnlChamCongCongDoanSP = new javax.swing.JPanel();
		speDanhSachChamCongSP = new javax.swing.JScrollPane();
		tblDanhSachChamCongSP = new javax.swing.JTable();
		lblDanhSachChamCongCongDoanSP = new javax.swing.JLabel();
		speDanhSachCNCanChamCongSP = new javax.swing.JScrollPane();
		tblDanhSachCNCanChamCongSP = new javax.swing.JTable();
		lblDanhSachChamCongSP = new javax.swing.JLabel();
		lblDanhSachCNCanChamCongSP = new javax.swing.JLabel();
		btnChamCongSP = new javax.swing.JButton();
		speDanhSachChamCongCongDoanSP = new javax.swing.JScrollPane();
		tblDanhSachChamCongCongDoanSP = new javax.swing.JTable();
		lblDSCongNhanChamCong = new javax.swing.JLabel();
		lblNgayChamCong = new javax.swing.JLabel();
		dateNgayChamCongCongDoan = new com.toedter.calendar.JDateChooser();
		pnlThongKe = new javax.swing.JPanel();
		pnlTieuDeTK = new javax.swing.JPanel();
		lblTieuDeTK = new javax.swing.JLabel();
		lblThangNhanVienTK = new javax.swing.JLabel();
		lblNamNhanVienTK = new javax.swing.JLabel();
		tbpTK = new javax.swing.JTabbedPane();
		pnlNhanVienTK = new javax.swing.JPanel();
		speNhanVienTK = new javax.swing.JScrollPane();
		tblNhanVienTK = new javax.swing.JTable();
		btnNhanVienTK = new javax.swing.JButton();
		btnCapNhatNhanVienTK = new javax.swing.JButton();
		btnCapNhatThongKeNV = new javax.swing.JButton();
		pnlCongNhanTK = new javax.swing.JPanel();
		speCongNhanTK = new javax.swing.JScrollPane();
		tblCongNhanTK = new javax.swing.JTable();
		btnCongNhanTK = new javax.swing.JButton();
		btnCapNhatCongNhanTK = new javax.swing.JButton();
		btnCapNhatThongKeCN = new javax.swing.JButton();
		pnlSanPhamTK = new javax.swing.JPanel();
		speSanPhamTK = new javax.swing.JScrollPane();
		tblSanPhamTK = new javax.swing.JTable();
		btnSanPhamTK = new javax.swing.JButton();
		btnCapNhatSanPhamTK = new javax.swing.JButton();
		btnCapNhatThongKeSP = new javax.swing.JButton();
		pnlTrangChu = new javax.swing.JPanel();
		pnlTC = new javax.swing.JPanel();
		btnNhanVienTC = new javax.swing.JButton();
		btnSanPhamTC = new javax.swing.JButton();
		lblImageTC = new javax.swing.JLabel();
		lblThongTinNhanVienTC = new javax.swing.JLabel();
		jSeparator = new javax.swing.JSeparator();
		lblTenNhanVienTC = new javax.swing.JLabel();
		lblChucVuTC = new javax.swing.JLabel();
		btnDangXuatTC = new javax.swing.JButton();
		btnDoiMatKhauTC = new javax.swing.JButton();
		btnThongKeTC = new javax.swing.JButton();
		btnCongNhanTC = new javax.swing.JButton();
		lblTruyCapDenTC = new javax.swing.JLabel();
		jSeparator5 = new javax.swing.JSeparator();
		lblDiaChiTC = new javax.swing.JLabel();
		lblTenCtyTC = new javax.swing.JLabel();
		jSeparator6 = new javax.swing.JSeparator();
		txtMaNhanVienTC = new javax.swing.JTextField();
		lblMaNhanVienTC = new javax.swing.JLabel();
		txtTenNhanVienTC = new javax.swing.JTextField();
		txtChucVuTC = new javax.swing.JTextField();
		pnlTieuDeTC = new javax.swing.JPanel();
		lblTieuDeTC = new javax.swing.JLabel();
		pnlNhanVien = new javax.swing.JPanel();
		pnlTieuDeNV = new javax.swing.JPanel();
		lblTieuDeNV = new javax.swing.JLabel();
		tbpNV = new javax.swing.JTabbedPane();
		pnlDanhSachNV = new javax.swing.JPanel();
		speDanhSachNV = new javax.swing.JScrollPane();
		tblListNhanVien = new javax.swing.JTable();
		btnThemNV = new javax.swing.JButton();
		btnXoaNV = new javax.swing.JButton();
		btnCapNhatNV = new javax.swing.JButton();
		btnLamMoiNV = new javax.swing.JButton();
		pnlChamCongNV = new javax.swing.JPanel();
		pneChamCongNV = new javax.swing.JScrollPane();
		tblChamCongNV = new javax.swing.JTable();
		lblNgayChamCongNV = new javax.swing.JLabel();
		btnChamCongNV = new javax.swing.JButton();
		dateNgayChamCongNV = new com.toedter.calendar.JDateChooser();
		pnlBangLuongNV = new javax.swing.JPanel();
		speBangLuong = new javax.swing.JScrollPane();
		tblBangLuongNV = new javax.swing.JTable();
		lblThangBangLuong = new javax.swing.JLabel();
		lblNamBangLuong = new javax.swing.JLabel();
		cboThangBangLuong = new javax.swing.JComboBox<>();
		cbboxTenSanPham = new javax.swing.JComboBox<>();
		btnInBangLuong = new javax.swing.JButton();
		cboNamBangLuong = new javax.swing.JComboBox<>();
		btnCapNhatBangLuong = new javax.swing.JButton();
		pnlTimKiemNV = new javax.swing.JPanel();
		speTimKiemNV = new javax.swing.JScrollPane();
		tblTimKiemNV = new javax.swing.JTable();
		lblMaNV = new javax.swing.JLabel();
		lblHoTenNV = new javax.swing.JLabel();
		txtHoTenNV = new javax.swing.JTextField();
		lblCmndNV = new javax.swing.JLabel();
		txtMaNhanVien = new javax.swing.JTextField();
		lblNgaySinhNV = new javax.swing.JLabel();
		lblNgayLamVienNV = new javax.swing.JLabel();
		radioBtnNamNV = new javax.swing.JRadioButton();
		radioBtnNuNV = new javax.swing.JRadioButton();
		btnTimNV = new javax.swing.JButton();
		lblGioiTinhNV = new javax.swing.JLabel();
		dateNgaySinhNV = new com.toedter.calendar.JDateChooser();
		dateNgayLamViecNV = new com.toedter.calendar.JDateChooser();
		txtCmndNV = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setDateMacDinh();
		disableFlied(false);
		font();

		pnlMenu.setBackground(new java.awt.Color(43, 124, 210));

		btnTrangChu.setBackground(new java.awt.Color(51, 153, 255));
		btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnTrangChu.setForeground(new java.awt.Color(255, 255, 255));
		btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/house.png"))); // NOI18N
		btnTrangChu.setText("Trang Chủ");
		btnTrangChu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTrangChuActionPerformed(evt);
			}
		});

		btnNhanVien.setBackground(new java.awt.Color(51, 153, 255));
		btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
		btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user.png"))); // NOI18N
		btnNhanVien.setText("Nhân Viên");
		btnNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNhanVienActionPerformed(evt);
			}
		});

		btnCongNhan.setBackground(new java.awt.Color(51, 153, 255));
		btnCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCongNhan.setForeground(new java.awt.Color(255, 255, 255));
		btnCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/construction-worker.png"))); // NOI18N
		btnCongNhan.setText("Công Nhân");
		btnCongNhan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnCongNhan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCongNhanActionPerformed(evt);
			}
		});

		btnSanPham.setBackground(new java.awt.Color(51, 153, 255));
		btnSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
		btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/package.png"))); // NOI18N
		btnSanPham.setText("Sản Phẩm");
		btnSanPham.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnSanPham.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSanPhamActionPerformed(evt);
			}
		});

		btnThongKe.setBackground(new java.awt.Color(51, 153, 255));
		btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
		btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pie-chart.png"))); // NOI18N
		btnThongKe.setText("Thống Kê");
		btnThongKe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnThongKe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThongKeActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
		pnlMenu.setLayout(pnlMenuLayout);
		pnlMenuLayout.setHorizontalGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(btnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		pnlMenuLayout
				.setVerticalGroup(
						pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlMenuLayout.createSequentialGroup().addGap(57, 57, 57)
										.addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(511, Short.MAX_VALUE)));

		jSplitPane1.setLeftComponent(pnlMenu);

		pnlCards.setLayout(new java.awt.CardLayout());

		lblTruyCapDenTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTruyCapDenTC.setForeground(new java.awt.Color(43, 124, 210));
		lblTruyCapDenTC.setText("Truy cập đến");

		jSeparator5.setForeground(new java.awt.Color(43, 124, 210));

		lblDiaChiTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblDiaChiTC.setText("Địa chỉ: 14 Nguyễn Văn Bảo, phường 14, Quận Gò Vấp, Thành phố Hồ Chí Minh");

		lblTenCtyTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblTenCtyTC.setText("CÔNG TY GIÀY DÉP ABC");

		jSeparator6.setForeground(new java.awt.Color(43, 124, 210));

		txtMaNhanVienTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		lblMaNhanVienTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblMaNhanVienTC.setText("Mã Nhân Viên:");

		txtTenNhanVienTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		txtChucVuTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout pnlTCLayout = new javax.swing.GroupLayout(pnlTC);
		pnlTC.setLayout(pnlTCLayout);
		pnlTCLayout.setHorizontalGroup(
				pnlTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSeparator6)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlTCLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblThongTinNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE,
												152, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(200, 200, 200))
						.addGroup(pnlTCLayout.createSequentialGroup().addGroup(pnlTCLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTCLayout.createSequentialGroup().addGap(35, 35, 35).addGroup(pnlTCLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
												btnNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												btnSanPhamTC, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(63, 63, 63)
										.addGroup(pnlTCLayout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(
														pnlTCLayout.createSequentialGroup()
																.addComponent(btnCongNhanTC,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 250,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(92, 92, 92).addComponent(lblImageTC)
																.addGap(33, 33, 33))
												.addGroup(pnlTCLayout.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING).addComponent(
																jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 285,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																pnlTCLayout.createSequentialGroup().addComponent(
																		lblMaNhanVienTC).addGap(18, 18, 18)
																		.addComponent(
																				txtMaNhanVienTC,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				162,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(pnlTCLayout.createSequentialGroup().addGroup(pnlTCLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(pnlTCLayout.createSequentialGroup()
																.addComponent(lblChucVuTC)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(txtChucVuTC,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 162,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(5, 5, 5))
														.addGroup(pnlTCLayout.createSequentialGroup()
																.addComponent(lblTenNhanVienTC).addGap(18, 18, 18)
																.addComponent(txtTenNhanVienTC,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 162,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGap(13, 13, 13))
												.addGroup(pnlTCLayout.createSequentialGroup()
														.addComponent(btnThongKeTC,
																javax.swing.GroupLayout.PREFERRED_SIZE, 250,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnDangXuatTC,
																javax.swing.GroupLayout.PREFERRED_SIZE, 92,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(btnDoiMatKhauTC,
																javax.swing.GroupLayout.PREFERRED_SIZE, 92,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(41, 41, 41))))
								.addGroup(
										pnlTCLayout.createSequentialGroup().addGap(18, 18, 18).addComponent(
												lblTruyCapDenTC, javax.swing.GroupLayout.PREFERRED_SIZE, 118,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlTCLayout.createSequentialGroup().addContainerGap()
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1073,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTenCtyTC, javax.swing.GroupLayout.PREFERRED_SIZE, 192,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(pnlTCLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblDiaChiTC, javax.swing.GroupLayout.PREFERRED_SIZE, 519,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlTCLayout.setVerticalGroup(pnlTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlTCLayout.createSequentialGroup().addContainerGap().addComponent(lblTruyCapDenTC)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(pnlTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTCLayout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lblImageTC, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlTCLayout.createSequentialGroup().addGap(53, 53, 53)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														127, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCongNhanTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														127, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(lblThongTinNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(pnlTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTCLayout.createSequentialGroup().addGap(75, 75, 75)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnSanPhamTC, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnThongKeTC, javax.swing.GroupLayout.PREFERRED_SIZE, 128,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(pnlTCLayout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblMaNhanVienTC).addComponent(txtMaNhanVienTC,
														javax.swing.GroupLayout.PREFERRED_SIZE, 22,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblTenNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														36, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTenNhanVienTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblChucVuTC, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtChucVuTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(pnlTCLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnDoiMatKhauTC, javax.swing.GroupLayout.PREFERRED_SIZE,
														31, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnDangXuatTC, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(61, 61, 61)))
						.addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(lblTenCtyTC, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblDiaChiTC,
								javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(68, 68, 68)));

		pnlTieuDeTC.setBackground(new java.awt.Color(43, 124, 210));

		lblTieuDeTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTieuDeTC.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDeTC.setText("TRANG CHỦ");

		javax.swing.GroupLayout pnlTieuDeTCLayout = new javax.swing.GroupLayout(pnlTieuDeTC);
		pnlTieuDeTC.setLayout(pnlTieuDeTCLayout);
		pnlTieuDeTCLayout
				.setHorizontalGroup(pnlTieuDeTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlTieuDeTCLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTieuDeTC, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(503, 503, 503)));
		pnlTieuDeTCLayout
				.setVerticalGroup(pnlTieuDeTCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeTCLayout.createSequentialGroup().addContainerGap().addComponent(lblTieuDeTC)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout pnlTrangChuLayout = new javax.swing.GroupLayout(pnlTrangChu);
		pnlTrangChu.setLayout(pnlTrangChuLayout);
		pnlTrangChuLayout.setHorizontalGroup(pnlTrangChuLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlTrangChuLayout.createSequentialGroup().addContainerGap().addGroup(pnlTrangChuLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(pnlTieuDeTC, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlTrangChuLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(
										pnlTC, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		pnlTrangChuLayout
				.setVerticalGroup(pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTrangChuLayout.createSequentialGroup().addContainerGap()
								.addComponent(pnlTieuDeTC, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(33, 33, 33).addComponent(pnlTC, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()));

		pnlCards.add(pnlTrangChu, "pnlCard1");

		pnlCongNhan.setBackground(new java.awt.Color(255, 255, 255));

		pnlTieuDeCN.setBackground(new java.awt.Color(43, 124, 210));

		lblTieuDeCN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTieuDeCN.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDeCN.setText("CÔNG NHÂN");

		javax.swing.GroupLayout pnlTieuDeCNLayout = new javax.swing.GroupLayout(pnlTieuDeCN);
		pnlTieuDeCN.setLayout(pnlTieuDeCNLayout);
		pnlTieuDeCNLayout
				.setHorizontalGroup(pnlTieuDeCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeCNLayout.createSequentialGroup().addGap(458, 458, 458)
								.addComponent(lblTieuDeCN, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlTieuDeCNLayout
				.setVerticalGroup(pnlTieuDeCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeCNLayout.createSequentialGroup().addContainerGap().addComponent(lblTieuDeCN)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tbpCN.setBackground(new java.awt.Color(0, 204, 255));
		tbpCN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		tbpCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

		pnlDanhSachCN.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderCN = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh", "Ngày bắt đầu làm việc",
				"CMND", "Địa chỉ", "Số điện thoại", "Trợ cấp" };
		modelCongNhan = new DefaultTableModel(colHeaderCN, 0);
		tblListCongNhan = new JTable(modelCongNhan);
		speDanhSachCN.setViewportView(tblListCongNhan);
		docDuLieuTuSQLTableCN();

		btnThemCN.setBackground(new java.awt.Color(255, 255, 102));
		btnThemCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThemCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
		btnThemCN.setText("Thêm");
		btnThemCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemCNActionPerformed(evt);
			}
		});

		btnXoaCN.setBackground(new java.awt.Color(255, 0, 0));
		btnXoaCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnXoaCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/x-button.png"))); // NOI18N
		btnXoaCN.setText("Xoá");
		btnXoaCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaCN(evt);
			}

		});

		btnCapNhatCN.setBackground(new java.awt.Color(0, 204, 255));
		btnCapNhatCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_book_and_pencil_30px.png"))); // NOI18N
		btnCapNhatCN.setText("Cập nhật");
		btnCapNhatCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatCNActionPerformed(evt);
			}
		});

		btnLamMoiCN.setBackground(new java.awt.Color(0, 204, 204));
		btnLamMoiCN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		btnLamMoiCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
		btnLamMoiCN.setText("Làm Mới");
		btnLamMoiCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				docDuLieuTuSQLTableCN();
			}
		});

		javax.swing.GroupLayout pnlDanhSachCNLayout = new javax.swing.GroupLayout(pnlDanhSachCN);
		pnlDanhSachCN.setLayout(pnlDanhSachCNLayout);
		pnlDanhSachCNLayout
				.setHorizontalGroup(pnlDanhSachCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlDanhSachCNLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnThemCN).addGap(83, 83, 83)
								.addComponent(btnXoaCN, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(90, 90, 90).addComponent(btnCapNhatCN).addGap(83, 83, 83)
								.addComponent(btnLamMoiCN, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(101, 101, 101))
						.addGroup(
								pnlDanhSachCNLayout.createSequentialGroup().addGap(28, 28, 28)
										.addComponent(speDanhSachCN, javax.swing.GroupLayout.PREFERRED_SIZE, 902,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(21, Short.MAX_VALUE)));
		pnlDanhSachCNLayout.setVerticalGroup(pnlDanhSachCNLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlDanhSachCNLayout.createSequentialGroup().addGap(26, 26, 26).addGroup(pnlDanhSachCNLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(pnlDanhSachCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThemCN).addComponent(btnXoaCN).addComponent(btnLamMoiCN,
										javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCapNhatCN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(speDanhSachCN, javax.swing.GroupLayout.PREFERRED_SIZE, 513,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 165, Short.MAX_VALUE)));

		tbpCN.addTab("Danh Sách Công Nhân", pnlDanhSachCN);

		pnlBangLuongCN.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderCongNhanLuong = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh", "Tháng", "Năm",
				"Lương" };
		modeluongCongNhan = new DefaultTableModel(colHeaderCongNhanLuong, 0);

		tblBangLuongCN = new JTable(modeluongCongNhan);
		tblBangLuongCN.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		speBangLuongCN.setViewportView(tblBangLuongCN);

		docDuLieuTuSQLTableLuongCN();

		lblThangCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblThangCN.setText("Tháng:");

		lblNamCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNamCN.setText("Năm");

		cboThangCN.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cboThangCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboThangCNActionPerformed(evt);
			}
		});

		cboThangCN.setSelectedItem("12");

		btnInBangLuongCN.setBackground(new java.awt.Color(255, 102, 102));
		btnInBangLuongCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnInBangLuongCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add.png"))); // NOI18N
		btnInBangLuongCN.setText("In Bảng Lương");

		btnInBangLuongCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {

					XSSFWorkbook workbook = new XSSFWorkbook();

					XSSFSheet sheet = workbook.createSheet("bangluongCN");
					XSSFRow row = null;
					Cell cell = null;
					row = sheet.createRow(6);

					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue("STT");

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue("Mã Lương");

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("Tên CN");

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue("Lương CN");

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue("Tháng");

					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue("Năm");

					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue("Mã CN");

					luongCongNhan_Dao = new BangLuongCongNhan_Dao();
					congNhan = new CongNhan_DAO();

					ArrayList<BangLuongCongNhan> listBangLuong = luongCongNhan_Dao.getAllBangLuongCongNhan();
					ArrayList<CongNhan> listCongNhan = congNhan.getAllCongNhan();

					for (int i = 0; i < listBangLuong.size(); i++) {

						row = sheet.createRow(7 + i);
						cell = row.createCell(0, CellType.NUMERIC);
						cell.setCellValue(i + 1);

						cell = row.createCell(1, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getMaLuongCN());

						cell = row.createCell(2, CellType.STRING);
						cell.setCellValue(congNhan.getCongNhan(listBangLuong.get(i).getCongNhan().getMaCongNhan())
								.getHoTenCongNhan());

						cell = row.createCell(3, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getLuong());

						cell = row.createCell(4, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getThang());

						cell = row.createCell(5, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getNam());

						cell = row.createCell(6, CellType.STRING);
						cell.setCellValue(congNhan.getCongNhan(listBangLuong.get(i).getCongNhan().getMaCongNhan())
								.getMaCongNhan());

					}

					File f = new File("D:\\Desktop\\bangluongCN.xlsx");
					try {
						FileOutputStream fis = new FileOutputStream(f);
						workbook.write(fis);
						fis.close();
					} catch (FileNotFoundException e) {
						// TODO: handle exception
						e.printStackTrace();
					} catch (IOException e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					System.out.println("IN thanh cong");

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

		cboNamCN.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
		cboNamCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboNamCNActionPerformed(evt);
			}
		});

		btnCapNhatBangLuongCN.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatBangLuongCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatBangLuongCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatBangLuongCN.setText("Làm Mới");

		btnCapNhatBangLuongCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				docDuLieuRefeshTableLuongCN();
			}
		});

		javax.swing.GroupLayout pnlBangLuongCNLayout = new javax.swing.GroupLayout(pnlBangLuongCN);
		pnlBangLuongCN.setLayout(pnlBangLuongCNLayout);
		pnlBangLuongCNLayout.setHorizontalGroup(pnlBangLuongCNLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlBangLuongCNLayout.createSequentialGroup().addGroup(pnlBangLuongCNLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlBangLuongCNLayout.createSequentialGroup().addGap(118, 118, 118)
								.addComponent(lblThangCN, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cboThangCN, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(lblNamCN, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cboNamCN, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(46, 46, 46).addComponent(btnInBangLuongCN).addGap(30, 30, 30)
								.addComponent(btnCapNhatBangLuongCN))
						.addGroup(pnlBangLuongCNLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(
								speBangLuongCN, javax.swing.GroupLayout.PREFERRED_SIZE, 908,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(0, 23, Short.MAX_VALUE)));
		pnlBangLuongCNLayout.setVerticalGroup(pnlBangLuongCNLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlBangLuongCNLayout.createSequentialGroup().addGap(21, 21, 21)
						.addGroup(pnlBangLuongCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblThangCN)
								.addComponent(cboThangCN, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNamCN)
								.addComponent(cboNamCN, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInBangLuongCN).addComponent(btnCapNhatBangLuongCN))
						.addGap(18, 18, 18).addComponent(speBangLuongCN, javax.swing.GroupLayout.PREFERRED_SIZE, 506,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(175, Short.MAX_VALUE)));

		tbpCN.addTab("Bảng Lương", pnlBangLuongCN);

		pnlTimKiemCN.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderTimKiemCongNhan = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh",
				"Ngày bắt đầu làm việc", "CMND", "Địa chỉ", "Số điện thoại", "Trợ cấp" };
		modellistTimKiemCongNhan = new DefaultTableModel(colHeaderTimKiemCongNhan, 0);
		tblTimKiemCN = new JTable(modellistTimKiemCongNhan);
		speTimKiemCN.setViewportView(tblTimKiemCN);

		lblMaCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblMaCN.setText("Mã Công Nhân:");

		lblHoTenCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblHoTenCN.setText("Họ Tên Công Nhân:");

		lblCmndCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblCmndCN.setText("CMND:");

		lblNgaySinhCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNgaySinhCN.setText("Ngày Sinh:");

		lblNgayLamVienCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNgayLamVienCN.setText("Ngày Làm Việc");

		radioBtnNamCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		radioBtnNamCN.setText("Nam");

		radioBtnNuCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		radioBtnNuCN.setText("Nữ");

		btnTimCN.setBackground(new java.awt.Color(255, 255, 51));
		btnTimCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnTimCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search.png"))); // NOI18N
		btnTimCN.setText("Tìm");
		btnTimCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimKiemCNActionPerformed(evt);
			}

		});

		lblGioiTinhCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblGioiTinhCN.setText("Giới tính:");

		dateNgaySinhCN.setDateFormatString("dd-MM-yyyy");

		dateNgayLamViecCN.setDateFormatString("dd-MM-yyyy");

		javax.swing.GroupLayout pnlTimKiemCNLayout = new javax.swing.GroupLayout(pnlTimKiemCN);
		pnlTimKiemCN.setLayout(pnlTimKiemCNLayout);
		pnlTimKiemCNLayout.setHorizontalGroup(pnlTimKiemCNLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemCNLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(pnlTimKiemCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblNgayLamVienCN, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCmndCN).addComponent(lblMaCN))
						.addGap(33, 33, 33)
						.addGroup(pnlTimKiemCNLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(txtCmndCN, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(dateNgayLamViecCN, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtMaCongNhan, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
						.addGap(117, 117, 117)
						.addGroup(pnlTimKiemCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblHoTenCN)
								.addComponent(lblGioiTinhCN, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinhCN))
						.addGap(45, 45, 45)
						.addGroup(pnlTimKiemCNLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(pnlTimKiemCNLayout.createSequentialGroup()
										.addComponent(radioBtnNamCN, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(radioBtnNuCN, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txtHoTenCN1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(dateNgaySinhCN, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(113, 113, 113))
				.addGroup(pnlTimKiemCNLayout.createSequentialGroup().addGap(15, 15, 15)
						.addGroup(pnlTimKiemCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btnTimCN, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(speTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 916,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(0, 20, Short.MAX_VALUE)));
		pnlTimKiemCNLayout.setVerticalGroup(pnlTimKiemCNLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemCNLayout.createSequentialGroup()
						.addGap(21, 21, 21)
						.addGroup(pnlTimKiemCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtHoTenCN1, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pnlTimKiemCNLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblMaCN).addComponent(lblHoTenCN).addComponent(txtMaCongNhan,
												javax.swing.GroupLayout.PREFERRED_SIZE, 30,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(21, 31, Short.MAX_VALUE)
						.addGroup(
								pnlTimKiemCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(pnlTimKiemCNLayout.createSequentialGroup()
												.addComponent(dateNgaySinhCN, javax.swing.GroupLayout.PREFERRED_SIZE,
														33, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addGroup(pnlTimKiemCNLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblGioiTinhCN).addComponent(radioBtnNamCN)
														.addComponent(radioBtnNuCN).addComponent(lblNgayLamVienCN)))
										.addGroup(pnlTimKiemCNLayout.createSequentialGroup()
												.addGroup(pnlTimKiemCNLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(txtCmndCN, javax.swing.GroupLayout.PREFERRED_SIZE,
																30, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblCmndCN).addComponent(lblNgaySinhCN))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(dateNgayLamViecCN, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(btnTimCN, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(24, 24, 24).addComponent(speTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 353,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(162, 162, 162)));

		tbpCN.addTab("Tìm Kiếm", pnlTimKiemCN);

		tbpNV.addMouseListener(new CustomMouseListenerNV());
		tbpCN.addMouseListener(new CustomMouseListenerCN());
		javax.swing.GroupLayout pnlCongNhanLayout = new javax.swing.GroupLayout(pnlCongNhan);
		pnlCongNhan.setLayout(pnlCongNhanLayout);
		pnlCongNhanLayout.setHorizontalGroup(pnlCongNhanLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlTieuDeCN,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(tbpCN));
		pnlCongNhanLayout
				.setVerticalGroup(pnlCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlCongNhanLayout.createSequentialGroup()
								.addComponent(pnlTieuDeCN, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(tbpCN, javax.swing.GroupLayout.PREFERRED_SIZE, 790,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));

		pnlCards.add(pnlCongNhan, "pnlCard3");

		pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));

		pnlTieuDeSP.setBackground(new java.awt.Color(43, 124, 210));

		lblTieuDeSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTieuDeSP.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDeSP.setText("SẢN PHẨM");

		javax.swing.GroupLayout pnlTieuDeSPLayout = new javax.swing.GroupLayout(pnlTieuDeSP);
		pnlTieuDeSP.setLayout(pnlTieuDeSPLayout);
		pnlTieuDeSPLayout
				.setHorizontalGroup(pnlTieuDeSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeSPLayout.createSequentialGroup().addGap(476, 476, 476)
								.addComponent(lblTieuDeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlTieuDeSPLayout
				.setVerticalGroup(pnlTieuDeSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlTieuDeSPLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTieuDeSP).addContainerGap()));

		tbpSP.setBackground(new java.awt.Color(0, 204, 255));
		tbpSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

		pnlSP.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderCD = { "Mã công đoạn", "Tên công đoạn", "Giá công đoạn", "Số lượng" };
		modelCongDoanSP = new DefaultTableModel(colHeaderCD, 0);
		tblCongDoanSP = new JTable(modelCongDoanSP);
		speCongDoanSP.setViewportView(tblCongDoanSP);

		String[] colHeaderSP = { "Mã sản phẩm", "Tên sản phẩm", "Kiểu dáng", "Chất liệu", "Số lượng", "Trạng thái" };
		modelSanPham = new DefaultTableModel(colHeaderSP, 0);
		tblSP = new JTable(modelSanPham);
		speSP.setViewportView(tblSP);
		docDuLieuTuSQLTableSP();

		btnCapNhatSP.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatSP.setText("Làm Mới");
		btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatSPActionPerformed(evt);
			}
		});

		lblMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblMaSP.setText("Mã Sản Phẩm");

		lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblTenSP.setText("Tên Sản Phẩm");

		lblSoLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblSoLuongSP.setText("Số Lượng");

		lblTrangThaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblTrangThaiSP.setText("Trạng Thái");

		cbxHoanThanhSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		cbxHoanThanhSP.setText("Hoàn Thành");
		cbxHoanThanhSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbxHoanThanhSPActionPerformed(evt);
			}
		});

		btnLuuSP.setBackground(new java.awt.Color(0, 204, 255));
		btnLuuSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnLuuSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save.png"))); // NOI18N
		btnLuuSP.setText("Lưu");

		btnLuuSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnluuSPActionPerformed(evt);
			}
		});

		btnThemSP.setBackground(new java.awt.Color(51, 255, 51));
		btnThemSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add (1).png"))); // NOI18N
		btnThemSP.setText("Thêm");
		btnThemSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemSPActionPerformed(evt);
			}
		});

		lblDSCD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDSCD.setText("DANH SÁCH CÔNG ĐOẠN");

		lblDSSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDSSP.setText("DANH SÁCH SẢN PHẨM");

		javax.swing.GroupLayout pnlSPLayout = new javax.swing.GroupLayout(pnlSP);
		pnlSP.setLayout(pnlSPLayout);
		pnlSPLayout.setHorizontalGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPLayout.createSequentialGroup().addGap(25, 25, 25).addGroup(pnlSPLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(lblDSSP)
						.addComponent(speSP, javax.swing.GroupLayout.PREFERRED_SIZE, 904,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(pnlSPLayout.createSequentialGroup()
								.addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(speCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE, 442,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDSCD))
								.addGap(38, 38, 38)
								.addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(pnlSPLayout.createSequentialGroup().addComponent(btnThemSP)
												.addGap(45, 45, 45).addComponent(btnCapNhatSP)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnLuuSP).addGap(10, 10, 10))
										.addGroup(pnlSPLayout.createSequentialGroup().addGroup(pnlSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(pnlSPLayout.createSequentialGroup()
														.addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE,
																101, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE,
																286, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(pnlSPLayout.createSequentialGroup().addGroup(pnlSPLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE,
																101, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblSoLuongSP,
																javax.swing.GroupLayout.PREFERRED_SIZE, 80,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(pnlSPLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																.addGroup(pnlSPLayout.createSequentialGroup()
																		.addComponent(txtSoLuongSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				85,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(lblTrangThaiSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				71,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(cbxHoanThanhSP))
																.addComponent(txtTenSP,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 286,
																		javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addGap(0, 0, Short.MAX_VALUE)))))
						.addContainerGap(24, Short.MAX_VALUE)));
		pnlSPLayout.setVerticalGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlSPLayout.createSequentialGroup().addGroup(pnlSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblMaSP, javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(txtMaSP, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(pnlSPLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTenSP,
																javax.swing.GroupLayout.Alignment.TRAILING))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(pnlSPLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblSoLuongSP)
														.addComponent(txtSoLuongSP,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTrangThaiSP).addComponent(cbxHoanThanhSP))
												.addGap(23, 23, 23)
												.addGroup(pnlSPLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnThemSP).addComponent(btnCapNhatSP)
														.addComponent(btnLuuSP))
												.addGap(60, 60, 60))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlSPLayout.createSequentialGroup()
												.addComponent(lblDSCD, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(speCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE,
														152, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(lblDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)))
						.addComponent(speSP, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(135, 135, 135)));

		tbpSP.addTab("Sản Phẩm", pnlSP);

		pnlPhanCongCongDoanSP.setBackground(new java.awt.Color(255, 255, 255));

		// Table danh sách công nhân thực hiện công đoạn
		String[] colHeaderDSCNThucHienCDSP = { "Mã công nhân", "Tên công nhân", "Giới tính" };
		modelListCNThucHienCDSP = new DefaultTableModel(colHeaderDSCNThucHienCDSP, 0);
		tblDanhSachCNThucHienCongDoanSP = new JTable(modelListCNThucHienCDSP);
		speDanhSachCNThucHienCongDoanSP.setViewportView(tblDanhSachCNThucHienCongDoanSP);

		// Table sản phẩm (phân công)
		String[] colHeaderDSSP = { "Mã sản phẩm", "Tên sản phẩm", "Kiểu dáng", "Chất liệu", "Số lượng" };
		modelListSP = new DefaultTableModel(colHeaderDSSP, 0);
		tblDanhSachSP = new JTable(modelListSP);
		speDanhSachSP.setViewportView(tblDanhSachSP);
		docDuLieuTuSQLTableDanhSachSP();

		lblDanhSachCongDoanSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachCongDoanSP.setText("2. DANH SÁCH CÔNG ĐOẠN");

		lblDanhSachCNThucHienCongDoanSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachCNThucHienCongDoanSP.setText("4. DANH SÁCH CÔNG NHÂN THỰC HIỆN CÔNG ĐOẠN");

		// Table danh sách công nhân chưa phân công
		String[] colHeaderDSCNChuaPhanCong = { "Mã công nhân", "Tên công nhân", "Giới tính" };
		modelListCNChuaPhanCong = new DefaultTableModel(colHeaderDSCNChuaPhanCong, 0);
		tblDanhSachCNChuaPhanCongSP = new JTable(modelListCNChuaPhanCong);
		speDanhSachCNChuaPhanCongSP.setViewportView(tblDanhSachCNChuaPhanCongSP);
		docDuLieuTuSQLTableDanhSachCNChuaPhanCong();

		// Table danh sách công đoạn sản phẩm (phân công)
		String[] colHeaderDSCDSP = { "Mã công đoạn", "Tên công đoạn", "Giá công đoạn", "Số lượng",
				"Số người tham gia" };
		modelListCDSP = new DefaultTableModel(colHeaderDSCDSP, 0);
		tblDanhSachCongDoanSP = new JTable(modelListCDSP);
		speDanhSachCongDoanSP.setViewportView(tblDanhSachCongDoanSP);

		lblDanhSachSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachSP.setText("1. DANH SÁCH SẢN PHẨM");

		lblDanhSachCNChuaPhanCongSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachCNChuaPhanCongSP.setText("3. DANH SÁCH CÔNG NHÂN CHƯA PHÂN CÔNG");

		btnPhanCongThuCongSP.setBackground(new java.awt.Color(0, 153, 255));
		btnPhanCongThuCongSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnPhanCongThuCongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/feedback.png"))); // NOI18N
		btnPhanCongThuCongSP.setText("Phân công thủ công");
		btnPhanCongThuCongSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPhanCongThuCongActionPerformed(evt);
			}

		});

		btnPhanCongTuDongSP.setBackground(new java.awt.Color(204, 255, 0));
		btnPhanCongTuDongSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnPhanCongTuDongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/contact.png"))); // NOI18N
		btnPhanCongTuDongSP.setText("Phân công tự động");
		btnPhanCongTuDongSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPhanCongTuDongSPActionPerformed(evt);
			}
		});

		btnCapNhatPC.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatPC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatPC.setText("Làm Mới");
		btnCapNhatPC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatPCActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlPhanCongCongDoanSPLayout = new javax.swing.GroupLayout(pnlPhanCongCongDoanSP);
		pnlPhanCongCongDoanSP.setLayout(pnlPhanCongCongDoanSPLayout);
		pnlPhanCongCongDoanSPLayout.setHorizontalGroup(pnlPhanCongCongDoanSPLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlPhanCongCongDoanSPLayout.createSequentialGroup().addGap(28, 28, 28)
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(pnlPhanCongCongDoanSPLayout.createSequentialGroup()
										.addComponent(btnPhanCongTuDongSP).addGap(118, 118, 118)
										.addComponent(btnCapNhatPC)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnPhanCongThuCongSP))
								.addGroup(pnlPhanCongCongDoanSPLayout.createSequentialGroup()
										.addGroup(pnlPhanCongCongDoanSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(speDanhSachSP, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
												.addComponent(speDanhSachCNChuaPhanCongSP,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
												.addComponent(lblDanhSachCNChuaPhanCongSP,
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblDanhSachSP, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 189,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(34, 34, 34)
										.addGroup(pnlPhanCongCongDoanSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(speDanhSachCongDoanSP,
														javax.swing.GroupLayout.PREFERRED_SIZE, 439,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(speDanhSachCNThucHienCongDoanSP,
														javax.swing.GroupLayout.PREFERRED_SIZE, 439,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDanhSachCNThucHienCongDoanSP).addComponent(
														lblDanhSachCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE,
														219, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(33, Short.MAX_VALUE)));
		pnlPhanCongCongDoanSPLayout.setVerticalGroup(pnlPhanCongCongDoanSPLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlPhanCongCongDoanSPLayout.createSequentialGroup()
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlPhanCongCongDoanSPLayout.createSequentialGroup().addGap(21, 21, 21)
										.addGroup(pnlPhanCongCongDoanSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnPhanCongThuCongSP).addComponent(btnPhanCongTuDongSP))
										.addGap(18, 18, 18))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnlPhanCongCongDoanSPLayout.createSequentialGroup().addContainerGap()
												.addComponent(btnCapNhatPC).addGap(10, 10, 10)))
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(lblDanhSachSP, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDanhSachCongDoanSP, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(speDanhSachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(speDanhSachCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblDanhSachCNChuaPhanCongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDanhSachCNThucHienCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE,
										22, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(pnlPhanCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(speDanhSachCNChuaPhanCongSP, javax.swing.GroupLayout.DEFAULT_SIZE, 239,
										Short.MAX_VALUE)
								.addComponent(speDanhSachCNThucHienCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE,
										0, Short.MAX_VALUE))
						.addContainerGap(120, Short.MAX_VALUE)));

		tbpSP.addTab("Phân Công Công Đoạn", pnlPhanCongCongDoanSP);

		pnlChamCongCongDoanSP.setBackground(new java.awt.Color(255, 255, 255));

		// Table Sản Phẩm (Chấm công công đoạn)
		String[] colHeaderDSCCSP = { "Mã sản phẩm", "Tên sản phẩm", "Kiểu dáng", "Chất liệu", "Số lượng" };
		modelListChamCongSP = new DefaultTableModel(colHeaderDSCCSP, 0);
		tblDanhSachChamCongSP = new JTable(modelListChamCongSP);
		speDanhSachChamCongSP.setViewportView(tblDanhSachChamCongSP);
		docDuLieuTuSQLTableDanhSachSanPhamCCCD();

		lblDanhSachChamCongCongDoanSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachChamCongCongDoanSP.setText("2. DANH SÁCH CÔNG ĐOẠN");

		// Table Công Nhân Cần Chấm Công (Chấm công công đoạn)
		modelListCNCanChamCong = (new DefaultTableModel(new Object[][] {},
				new String[] { "Mã công nhân", "Tên công nhân", "Vắng mặt", "Có phép", "Ca làm", "Số lượng" }) {
			Class[] types = new Class[] { Object.class, Object.class, Boolean.class, Boolean.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		tblDanhSachCNCanChamCongSP = new JTable(modelListCNCanChamCong);
		speDanhSachCNCanChamCongSP.setViewportView(tblDanhSachCNCanChamCongSP);

		JComboBox comboBoxCaLam = new JComboBox();
		comboBoxCaLam.addItem("Sáng");
		comboBoxCaLam.addItem("Chiều");
		comboBoxCaLam.addItem("Tối");
		TableColumn caLamCol = tblDanhSachCNCanChamCongSP.getColumnModel().getColumn(4);
		caLamCol.setCellEditor(new DefaultCellEditor(comboBoxCaLam));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		caLamCol.setCellRenderer(renderer);

		lblDanhSachChamCongSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachChamCongSP.setText("1. DANH SÁCH SẢN PHẨM");

		lblDanhSachCNCanChamCongSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDanhSachCNCanChamCongSP.setText("DANH SÁCH CÔNG NHÂN CẦN CHẤM CÔNG");

		btnChamCongSP.setBackground(new java.awt.Color(255, 255, 0));
		btnChamCongSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnChamCongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add.png"))); // NOI18N
		btnChamCongSP.setText("Chấm Công");
		btnChamCongSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChamCongSPActionPerformed(evt);
			}

		});

		// Table Công Đoạn (Chấm công công đoạn)
		String[] colHeaderDSCCCDSP = { "Mã công đoạn", "Tên công đoạn", "Giá công đoạn", "Số lượng",
				"Số lượng còn lại" };
		modelListCCCDSP = new DefaultTableModel(colHeaderDSCCCDSP, 0);
		tblDanhSachChamCongCongDoanSP = new JTable(modelListCCCDSP);
		speDanhSachChamCongCongDoanSP.setViewportView(tblDanhSachChamCongCongDoanSP);

		lblDSCongNhanChamCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblDSCongNhanChamCong.setText("3. DANH SÁCH CÔNG NHÂN CẦN CHẤM CÔNG");

		lblNgayChamCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblNgayChamCong.setText("Ngày Chấm Công:");

		dateNgayChamCongCongDoan.setDateFormatString("dd-MM-yyyy");

		javax.swing.GroupLayout pnlChamCongCongDoanSPLayout = new javax.swing.GroupLayout(pnlChamCongCongDoanSP);
		pnlChamCongCongDoanSP.setLayout(pnlChamCongCongDoanSPLayout);
		pnlChamCongCongDoanSPLayout.setHorizontalGroup(pnlChamCongCongDoanSPLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChamCongCongDoanSPLayout.createSequentialGroup().addGap(29, 29, 29)
						.addGroup(pnlChamCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnlChamCongCongDoanSPLayout.createSequentialGroup()
										.addComponent(lblDSCongNhanChamCong)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNgayChamCong)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(dateNgayChamCongCongDoan, javax.swing.GroupLayout.PREFERRED_SIZE,
												187, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(51, 51, 51).addComponent(btnChamCongSP).addGap(21, 21, 21))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChamCongCongDoanSPLayout
										.createSequentialGroup()
										.addGroup(pnlChamCongCongDoanSPLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
														pnlChamCongCongDoanSPLayout.createSequentialGroup().addGroup(
																pnlChamCongCongDoanSPLayout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(pnlChamCongCongDoanSPLayout
																				.createSequentialGroup()
																				.addGap(15, 15, 15).addComponent(
																						lblDanhSachCNCanChamCongSP))
																		.addComponent(speDanhSachChamCongSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				406,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblDanhSachChamCongSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				201,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(51, 51, 51)
																.addGroup(pnlChamCongCongDoanSPLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(speDanhSachChamCongCongDoanSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				431,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblDanhSachChamCongCongDoanSP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				218,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addComponent(speDanhSachCNCanChamCongSP,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 905,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(19, Short.MAX_VALUE)))));
		pnlChamCongCongDoanSPLayout.setVerticalGroup(pnlChamCongCongDoanSPLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChamCongCongDoanSPLayout.createSequentialGroup().addGap(27, 27, 27)
						.addGroup(pnlChamCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblDanhSachChamCongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDanhSachChamCongCongDoanSP))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(pnlChamCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(speDanhSachChamCongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 203,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(speDanhSachChamCongCongDoanSP, javax.swing.GroupLayout.PREFERRED_SIZE,
										197, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(pnlChamCongCongDoanSPLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btnChamCongSP)
								.addComponent(dateNgayChamCongCongDoan, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(pnlChamCongCongDoanSPLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblDSCongNhanChamCong).addComponent(lblNgayChamCong)))
						.addGap(18, 18, 18)
						.addComponent(speDanhSachCNCanChamCongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 262,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
						.addComponent(lblDanhSachCNCanChamCongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)));

		tbpSP.addTab("Chấm Công Công Đoạn", pnlChamCongCongDoanSP);

		javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
		pnlSanPham.setLayout(pnlSanPhamLayout);
		pnlSanPhamLayout.setHorizontalGroup(pnlSanPhamLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlTieuDeSP,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(tbpSP));
		pnlSanPhamLayout
				.setVerticalGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlSanPhamLayout.createSequentialGroup()
								.addComponent(pnlTieuDeSP, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(tbpSP)));

		pnlCards.add(pnlSanPham, "pnlCard4");

		pnlThongKe.setBackground(new java.awt.Color(255, 255, 255));

		pnlTieuDeTK.setBackground(new java.awt.Color(43, 124, 210));

		lblTieuDeTK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTieuDeTK.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDeTK.setText("THỐNG KÊ");

		javax.swing.GroupLayout pnlTieuDeTKLayout = new javax.swing.GroupLayout(pnlTieuDeTK);
		pnlTieuDeTK.setLayout(pnlTieuDeTKLayout);
		pnlTieuDeTKLayout
				.setHorizontalGroup(pnlTieuDeTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeTKLayout.createSequentialGroup().addGap(476, 476, 476)
								.addComponent(lblTieuDeTK, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlTieuDeTKLayout
				.setVerticalGroup(pnlTieuDeTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlTieuDeTKLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTieuDeTK).addContainerGap()));

		tbpTK.setBackground(new java.awt.Color(0, 204, 255));
		tbpTK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		tbpTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

		pnlNhanVienTK.setBackground(new java.awt.Color(255, 255, 255));

		tblNhanVienTK
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		speNhanVienTK.setViewportView(tblNhanVienTK);

		String[] colHeaderNhanVienTK = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Hệ số lương",
				"Tháng", "Năm", "Lương" };
		modelNhanVienTK = new DefaultTableModel(colHeaderNhanVienTK, 0);
		tblNhanVienTK = new JTable(modelNhanVienTK);
		speNhanVienTK.setViewportView(tblNhanVienTK);
		docDuLieuTuSQLTableNhanVienTK();

		cboThangCongNhanTK.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cboThangCongNhanTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboThangCongNhanTKActionPerformed(evt);
			}
		});

		cboNamCongNhanTK.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
		cboNamCongNhanTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboNamCongNhanTKActionPerformed(evt);
			}
		});

		btnNhanVienTK.setBackground(new java.awt.Color(255, 204, 204));
		btnNhanVienTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnNhanVienTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pie-chart.png"))); // NOI18N
		btnNhanVienTK.setText("Thống kê");
		btnNhanVienTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNhanVienTKActionPerformed(evt);
			}
		});

		btnCapNhatNhanVienTK.setBackground(new java.awt.Color(255, 255, 0));
		btnCapNhatNhanVienTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatNhanVienTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatNhanVienTK.setText("Làm mới");
		btnCapNhatNhanVienTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatNhanVienTKActionPerformed(evt);
			}
		});

		lblThangNhanVienTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblThangNhanVienTK.setText("Tháng");

		lblNamNhanVienTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNamNhanVienTK.setText("Năm");

		cboThangNhanVienTK.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cboThangNhanVienTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboThangNhanVienTKActionPerformed(evt);
			}
		});

		cboNamNhanVienTK.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));

		cboNamNhanVienTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboNamNhanVienTKActionPerformed(evt);
			}
		});

		btnCapNhatThongKeNV.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatThongKeNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatThongKeNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatThongKeNV.setText("Làm Mới");
		btnCapNhatThongKeNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatThongKeNVActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlNhanVienTKLayout = new javax.swing.GroupLayout(pnlNhanVienTK);
		pnlNhanVienTK.setLayout(pnlNhanVienTKLayout);
		pnlNhanVienTKLayout.setHorizontalGroup(pnlNhanVienTKLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienTKLayout.createSequentialGroup()
						.addGroup(pnlNhanVienTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnlNhanVienTKLayout.createSequentialGroup()
										.addContainerGap(176, Short.MAX_VALUE).addComponent(speNhanVienTK,
												javax.swing.GroupLayout.PREFERRED_SIZE, 865,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlNhanVienTKLayout.createSequentialGroup().addGap(127, 127, 127)
										.addComponent(lblThangNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(cboThangNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(24, 24, 24)
										.addComponent(lblNamNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cboNamNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCapNhatNhanVienTK)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnNhanVienTK)))
						.addGap(42, 42, 42)));
		pnlNhanVienTKLayout.setVerticalGroup(pnlNhanVienTKLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlNhanVienTKLayout.createSequentialGroup().addGap(24, 24, 24).addGroup(pnlNhanVienTKLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnlNhanVienTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCapNhatNhanVienTK).addComponent(btnNhanVienTK))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								pnlNhanVienTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblThangNhanVienTK)
										.addComponent(cboThangNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNamNhanVienTK).addComponent(cboNamNhanVienTK,
												javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18).addComponent(speNhanVienTK, javax.swing.GroupLayout.PREFERRED_SIZE, 487,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));

		tbpTK.addTab("Nhân Viên", pnlNhanVienTK);

		pnlCongNhanTK.setBackground(new java.awt.Color(255, 255, 255));

		tblCongNhanTK
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		speCongNhanTK.setViewportView(tblCongNhanTK);

		String[] colHeaderCongNhanTK = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh", "Tháng", "Năm",
				"Lương" };
		modelCongNhanTK = new DefaultTableModel(colHeaderCongNhanTK, 0);
		tblCongNhanTK = new JTable(modelCongNhanTK);
		speCongNhanTK.setViewportView(tblCongNhanTK);
		docDuLieuTuSQLTableCongNhanTK();

		btnCongNhanTK.setBackground(new java.awt.Color(255, 204, 204));
		btnCongNhanTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCongNhanTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pie-chart.png"))); // NOI18N
		btnCongNhanTK.setText("Thống kê");
		btnCongNhanTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCongNhanTKActionPerformed(evt);
			}
		});

		btnCapNhatCongNhanTK.setBackground(new java.awt.Color(255, 255, 0));
		btnCapNhatCongNhanTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatCongNhanTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatCongNhanTK.setText("Làm mới");
		btnCapNhatCongNhanTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatCongNhanTKActionPerformed(evt);
			}
		});

		lblThangCongNhanTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblThangCongNhanTK.setText("Tháng");

		lblNamCongNhanTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNamCongNhanTK.setText("Năm");

		btnCapNhatThongKeCN.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatThongKeCN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatThongKeCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatThongKeCN.setText("Làm Mới");
		btnCapNhatThongKeCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatThongKeCNActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlCongNhanTKLayout = new javax.swing.GroupLayout(pnlCongNhanTK);
		pnlCongNhanTK.setLayout(pnlCongNhanTKLayout);
		pnlCongNhanTKLayout.setHorizontalGroup(pnlCongNhanTKLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCongNhanTKLayout.createSequentialGroup()
						.addGroup(pnlCongNhanTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnlCongNhanTKLayout.createSequentialGroup()
										.addContainerGap(176, Short.MAX_VALUE).addComponent(speCongNhanTK,
												javax.swing.GroupLayout.PREFERRED_SIZE, 865,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlCongNhanTKLayout.createSequentialGroup().addGap(127, 127, 127)
										.addComponent(lblThangCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(cboThangCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(24, 24, 24)
										.addComponent(lblNamCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cboNamCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCapNhatCongNhanTK)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnCongNhanTK)))
						.addGap(42, 42, 42)));
		pnlCongNhanTKLayout.setVerticalGroup(pnlCongNhanTKLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlCongNhanTKLayout.createSequentialGroup().addGap(24, 24, 24).addGroup(pnlCongNhanTKLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnlCongNhanTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCapNhatCongNhanTK).addComponent(btnCongNhanTK))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								pnlCongNhanTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblThangCongNhanTK)
										.addComponent(cboThangCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNamCongNhanTK).addComponent(cboNamCongNhanTK,
												javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18).addComponent(speCongNhanTK, javax.swing.GroupLayout.PREFERRED_SIZE, 487,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));

		tbpTK.addTab("Công Nhân", pnlCongNhanTK);

		pnlSanPhamTK.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderSanPhamTK = { "Mã công đoạn", "Tên công đoạn", "Giá công đoạn", "Số lượng" };
		modelSanPhamTK = new DefaultTableModel(colHeaderSanPhamTK, 0);
		tblSanPhamTK = new JTable(modelSanPhamTK);
		speSanPhamTK.setViewportView(tblSanPhamTK);

		btnSanPhamTK.setBackground(new java.awt.Color(255, 204, 204));
		btnSanPhamTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnSanPhamTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pie-chart.png"))); // NOI18N
		btnSanPhamTK.setText("Thống kê");
		btnSanPhamTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSanPhamTKActionPerformed(evt);
			}
		});

		btnCapNhatSanPhamTK.setBackground(new java.awt.Color(255, 255, 0));
		btnCapNhatSanPhamTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatSanPhamTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatSanPhamTK.setText("Làm mới");
		btnCapNhatSanPhamTK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatSanPhamTKActionPerformed(evt);
			}
		});

		lblTenSanPhamTK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblTenSanPhamTK.setText("Tên sản phẩm");

		cbboxTenSanPham.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbboxTenSanPhamActionPerformed(evt);
			}
		});

		btnCapNhatThongKeSP.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatThongKeSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatThongKeSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatThongKeSP.setText("Làm Mới");
		btnCapNhatThongKeSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatThongKeSPActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlSanPhamTKLayout = new javax.swing.GroupLayout(pnlSanPhamTK);
		pnlSanPhamTK.setLayout(pnlSanPhamTKLayout);
		pnlSanPhamTKLayout
				.setHorizontalGroup(pnlSanPhamTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnlSanPhamTKLayout.createSequentialGroup().addGroup(pnlSanPhamTKLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(pnlSanPhamTKLayout.createSequentialGroup()
												.addContainerGap(176, Short.MAX_VALUE).addComponent(speSanPhamTK,
														javax.swing.GroupLayout.PREFERRED_SIZE, 865,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pnlSanPhamTKLayout.createSequentialGroup().addGap(127, 127, 127)
												.addComponent(lblTenSanPhamTK, javax.swing.GroupLayout.PREFERRED_SIZE,
														120, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(cbboxTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
														117, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnCapNhatSanPhamTK)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(btnSanPhamTK)))
										.addGap(42, 42, 42)));
		pnlSanPhamTKLayout.setVerticalGroup(pnlSanPhamTKLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlSanPhamTKLayout.createSequentialGroup().addGap(24, 24, 24).addGroup(pnlSanPhamTKLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnlSanPhamTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCapNhatSanPhamTK).addComponent(btnSanPhamTK))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								pnlSanPhamTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTenSanPhamTK).addComponent(cbboxTenSanPham,
												javax.swing.GroupLayout.PREFERRED_SIZE, 35,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18).addComponent(speSanPhamTK, javax.swing.GroupLayout.PREFERRED_SIZE, 487,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));

		tbpTK.addTab("Sản Phẩm", pnlSanPhamTK);

		javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
		pnlThongKe.setLayout(pnlThongKeLayout);
		pnlThongKeLayout.setHorizontalGroup(pnlThongKeLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlTieuDeTK,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(tbpTK));
		pnlThongKeLayout
				.setVerticalGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlThongKeLayout.createSequentialGroup()
								.addComponent(pnlTieuDeTK, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(tbpTK).addContainerGap()));

		pnlCards.add(pnlThongKe, "pnlCard5");

		pnlTrangChu.setBackground(new java.awt.Color(255, 255, 255));

		pnlTC.setBackground(new java.awt.Color(255, 255, 255));

		btnNhanVienTC.setBackground(new java.awt.Color(43, 124, 210));
		btnNhanVienTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnNhanVienTC.setForeground(new java.awt.Color(255, 255, 255));
		btnNhanVienTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_security_pass_100px.png"))); // NOI18N
		btnNhanVienTC.setText("Nhân Viên");
		btnNhanVienTC.setBorder(null);
		btnNhanVienTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNhanVienTCActionPerformed(evt);
			}
		});

		btnSanPhamTC.setBackground(new java.awt.Color(43, 124, 210));
		btnSanPhamTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnSanPhamTC.setForeground(new java.awt.Color(255, 255, 255));
		btnSanPhamTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_sneakers_100px.png"))); // NOI18N
		btnSanPhamTC.setText("Sản Phẩm");
		btnSanPhamTC.setBorder(null);
		btnSanPhamTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSanPhamTCActionPerformed(evt);
			}
		});

		lblImageTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_contacts_200px_1.png"))); // NOI18N

		lblThongTinNhanVienTC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblThongTinNhanVienTC.setForeground(new java.awt.Color(43, 124, 210));
		lblThongTinNhanVienTC.setText("Thông tin nhân viên");

		jSeparator.setForeground(new java.awt.Color(43, 142, 210));

		lblTenNhanVienTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblTenNhanVienTC.setText("Tên Nhân Viên: ");

		lblChucVuTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblChucVuTC.setText("Chức Vụ:");

		btnDangXuatTC.setBackground(new java.awt.Color(43, 124, 210));
		btnDangXuatTC.setForeground(new java.awt.Color(255, 255, 255));
		btnDangXuatTC.setText("Đăng Xuất");
		btnDangXuatTC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		btnDangXuatTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDangXuatTCActionPerformed(evt);
			}
		});

		btnDoiMatKhauTC.setBackground(new java.awt.Color(43, 124, 210));
		btnDoiMatKhauTC.setForeground(new java.awt.Color(255, 255, 255));
		btnDoiMatKhauTC.setText("Đổi mật khẩu");
		btnDoiMatKhauTC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		btnDoiMatKhauTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDoiMatKhauTCActionPerformed(evt);
			}
		});

		btnThongKeTC.setBackground(new java.awt.Color(43, 124, 210));
		btnThongKeTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnThongKeTC.setForeground(new java.awt.Color(255, 255, 255));
		btnThongKeTC
				.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_edit_graph_report_100px.png"))); // NOI18N
		btnThongKeTC.setText("Thống Kê");
		btnThongKeTC.setBorder(null);
		btnThongKeTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThongKeTCActionPerformed(evt);
			}
		});

		btnCongNhanTC.setBackground(new java.awt.Color(43, 124, 210));
		btnCongNhanTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnCongNhanTC.setForeground(new java.awt.Color(255, 255, 255));
		btnCongNhanTC.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_identification_documents_100px.png"))); // NOI18N
		btnCongNhanTC.setText("Công Nhân");
		btnCongNhanTC.setBorder(null);
		btnCongNhanTC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCongNhanTCActionPerformed(evt);
			}
		});

		pnlNhanVien.setBackground(new java.awt.Color(255, 255, 255));

		pnlTieuDeNV.setBackground(new java.awt.Color(43, 124, 210));

		lblTieuDeNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTieuDeNV.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDeNV.setText("NHÂN VIÊN");

		javax.swing.GroupLayout pnlTieuDeNVLayout = new javax.swing.GroupLayout(pnlTieuDeNV);
		pnlTieuDeNV.setLayout(pnlTieuDeNVLayout);
		pnlTieuDeNVLayout
				.setHorizontalGroup(pnlTieuDeNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeNVLayout.createSequentialGroup().addGap(470, 470, 470)
								.addComponent(lblTieuDeNV, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlTieuDeNVLayout
				.setVerticalGroup(pnlTieuDeNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlTieuDeNVLayout.createSequentialGroup().addContainerGap().addComponent(lblTieuDeNV)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tbpNV.setBackground(new java.awt.Color(0, 204, 255));
		tbpNV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		tbpNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

		pnlDanhSachNV.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderNV = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Ngày bắt đầu làm việc",
				"CMND", "Địa chỉ", "Số điện thoại", "Trợ cấp", "Hệ số lương" };
		modelNhanVien = new DefaultTableModel(colHeaderNV, 0);
		tblListNhanVien = new JTable(modelNhanVien);
		speDanhSachNV.setViewportView(tblListNhanVien);
		docDuLieuTuSQLTableNV();

		btnThemNV.setBackground(new java.awt.Color(255, 255, 102));
		btnThemNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
		btnThemNV.setText("Thêm");
		btnThemNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemNVActionPerformed(evt);
			}
		});

		btnXoaNV.setBackground(new java.awt.Color(255, 0, 0));
		btnXoaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/x-button.png"))); // NOI18N
		btnXoaNV.setText("Xoá");
		btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaNV(evt);
			}

		});

		btnCapNhatNV.setBackground(new java.awt.Color(0, 204, 255));
		btnCapNhatNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8_book_and_pencil_30px.png"))); // NOI18N
		btnCapNhatNV.setText("Cập nhật");
		btnCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatNVActionPerformed(evt);
			}
		});

		btnLamMoiNV.setBackground(new java.awt.Color(0, 204, 204));
		btnLamMoiNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		btnLamMoiNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
		btnLamMoiNV.setText("Làm Mới");
		btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLamMoiNVActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlDanhSachNVLayout = new javax.swing.GroupLayout(pnlDanhSachNV);
		pnlDanhSachNV.setLayout(pnlDanhSachNVLayout);
		pnlDanhSachNVLayout
				.setHorizontalGroup(pnlDanhSachNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlDanhSachNVLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnThemNV).addGap(72, 72, 72)
								.addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(74, 74, 74).addComponent(btnCapNhatNV).addGap(58, 58, 58)
								.addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(133, 133, 133))
						.addGroup(pnlDanhSachNVLayout.createSequentialGroup().addGap(22, 22, 22)
								.addComponent(speDanhSachNV, javax.swing.GroupLayout.PREFERRED_SIZE, 891,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlDanhSachNVLayout.setVerticalGroup(pnlDanhSachNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlDanhSachNVLayout.createSequentialGroup().addGap(23, 23, 23)
						.addGroup(pnlDanhSachNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThemNV).addComponent(btnXoaNV).addComponent(btnCapNhatNV)
								.addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(speDanhSachNV, javax.swing.GroupLayout.PREFERRED_SIZE, 494,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tbpNV.addTab("Danh Sách Nhân Viên", pnlDanhSachNV);

		pnlChamCongNV.setBackground(new java.awt.Color(255, 255, 255));

		modelChamCongNV = (new DefaultTableModel(new Object[][] {},
				new String[] { "Mã nhân viên", "Tên nhân viên", "Vắng mặt", "Có phép", "Ghi chú", "Số giờ tăng ca" }) {
			Class[] types = new Class[] { Object.class, Object.class, Boolean.class, Boolean.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		tblChamCongNV = new JTable(modelChamCongNV);
		pneChamCongNV.setViewportView(tblChamCongNV);

		combobox = new JComboBox();
		combobox.addItem("0");
		combobox.addItem("1");
		combobox.addItem("2");
		combobox.addItem("3");
		TableColumn soGioTangCaCol = tblChamCongNV.getColumnModel().getColumn(5);
		soGioTangCaCol.setCellEditor(new DefaultCellEditor(combobox));
		DefaultTableCellRenderer rendererTangCa = new DefaultTableCellRenderer();
		rendererTangCa.setToolTipText("Click for combo box");
		soGioTangCaCol.setCellRenderer(rendererTangCa);
		docDuLieuTuSQLTableChamCongNV();

		lblNgayChamCongNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNgayChamCongNV.setText("Ngày chấm công:");

		btnChamCongNV.setBackground(new java.awt.Color(255, 255, 0));
		btnChamCongNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnChamCongNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add.png"))); // NOI18N
		btnChamCongNV.setText("Chấm Công");
		btnChamCongNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChamCongNVActionPerformed(evt);
			}
		});

		dateNgayChamCongNV.setDateFormatString("dd-MM-yyyy");

		javax.swing.GroupLayout pnlChamCongNVLayout = new javax.swing.GroupLayout(pnlChamCongNV);
		pnlChamCongNV.setLayout(pnlChamCongNVLayout);
		pnlChamCongNVLayout.setHorizontalGroup(pnlChamCongNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChamCongNVLayout.createSequentialGroup().addGap(44, 44, 44)
						.addGroup(pnlChamCongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(pneChamCongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 870,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pnlChamCongNVLayout.createSequentialGroup().addComponent(lblNgayChamCongNV)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(dateNgayChamCongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 215,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(348, 348, 348).addComponent(btnChamCongNV)))
						.addContainerGap(31, Short.MAX_VALUE)));
		pnlChamCongNVLayout.setVerticalGroup(pnlChamCongNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlChamCongNVLayout.createSequentialGroup().addGap(27, 27, 27)
						.addGroup(pnlChamCongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btnChamCongNV)
								.addGroup(pnlChamCongNVLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(dateNgayChamCongNV, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNgayChamCongNV, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(9, 9, 9).addComponent(pneChamCongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));

		tbpNV.addTab("Chấm Công", pnlChamCongNV);

		pnlBangLuongNV.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeadeLuongCN = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh", "Tháng", "Năm",
				"Lương" };
		modeluongNhanVien = new DefaultTableModel(colHeaderCongNhanTK, 0);
		tblBangLuongNV = new JTable(modeluongNhanVien);
		tblBangLuongNV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable2MouseClicked(evt);
			}
		});
		speBangLuong.setViewportView(tblBangLuongNV);

		docDuLieuTuSQLTableLuongNV();

		lblThangBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblThangBangLuong.setText("Tháng:");

		lblNamBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNamBangLuong.setText("Năm");

		cboThangBangLuong.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

		cboThangBangLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboThangBangLuongActionPerformed(evt);
			}
		});

		cboThangBangLuong.setSelectedItem("12");

		btnInBangLuong.setBackground(new java.awt.Color(255, 102, 102));
		btnInBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnInBangLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add.png"))); // NOI18N
		btnInBangLuong.setText("In Bảng Lương");

		btnInBangLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {

					XSSFWorkbook workbook = new XSSFWorkbook();

					XSSFSheet sheet = workbook.createSheet("bangluongNV");
					XSSFRow row = null;
					Cell cell = null;
					row = sheet.createRow(6);

					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue("STT");

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue("Mã Lương");

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("Tên NV");

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue("Lương NV");

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue("Tháng");

					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue("Năm");

					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue("Mã NV");

					luongNhanVien_Dao = new BangLuongNhanVien_Dao();
					nhanVien = new NhanVien_DAO();

					ArrayList<BangLuongNhanVien> listBangLuong = luongNhanVien_Dao.getAllBangLuongNhanVien();
					ArrayList<NhanVien> listNhanVien = nhanVien.getAllNhanVien();
					// kaiwon

					for (int i = 0; i < listBangLuong.size(); i++) {

						row = sheet.createRow(7 + i);
						cell = row.createCell(0, CellType.NUMERIC);
						cell.setCellValue(i + 1);

						cell = row.createCell(1, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getMaLuongNV());

						cell = row.createCell(2, CellType.STRING);
						cell.setCellValue(nhanVien.getNhanVien(listBangLuong.get(i).getNhanVien().getMaNhanVien())
								.getHoTenNhanVien());

						cell = row.createCell(3, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getLuong());

						cell = row.createCell(4, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getThang());

						cell = row.createCell(5, CellType.STRING);
						cell.setCellValue(listBangLuong.get(i).getNam());

						cell = row.createCell(6, CellType.STRING);
						cell.setCellValue(nhanVien.getNhanVien(listBangLuong.get(i).getNhanVien().getMaNhanVien())
								.getMaNhanVien());

					}

					File f = new File("D:\\Desktop\\bangluongNV.xlsx");
					try {
						FileOutputStream fis = new FileOutputStream(f);
						workbook.write(fis);
						fis.close();
					} catch (FileNotFoundException e) {
						// TODO: handle exception
						e.printStackTrace();
					} catch (IOException e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					System.out.println("IN thanh cong");

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

		cboNamBangLuong.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));

		cboNamBangLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboNamBangLuongActionPerformed(evt);
			}
		});

		btnCapNhatBangLuong.setBackground(new java.awt.Color(0, 204, 204));
		btnCapNhatBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhatBangLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhatBangLuong.setText("Làm Mới");
		btnCapNhatBangLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// kaikai

				docDuLieuTuSQLTableLuongNV();
			}
		});

		javax.swing.GroupLayout pnlBangLuongNVLayout = new javax.swing.GroupLayout(pnlBangLuongNV);
		pnlBangLuongNV.setLayout(pnlBangLuongNVLayout);
		pnlBangLuongNVLayout.setHorizontalGroup(pnlBangLuongNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBangLuongNVLayout.createSequentialGroup()
						.addGroup(pnlBangLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnlBangLuongNVLayout.createSequentialGroup()
										.addContainerGap(38, Short.MAX_VALUE).addComponent(speBangLuong,
												javax.swing.GroupLayout.PREFERRED_SIZE, 865,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlBangLuongNVLayout.createSequentialGroup().addGap(127, 127, 127)
										.addComponent(lblThangBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(cboThangBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(24, 24, 24)
										.addComponent(lblNamBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cboNamBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnInBangLuong)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnCapNhatBangLuong)))
						.addGap(42, 42, 42)));
		pnlBangLuongNVLayout.setVerticalGroup(pnlBangLuongNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlBangLuongNVLayout.createSequentialGroup().addGap(24, 24, 24).addGroup(pnlBangLuongNVLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnlBangLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnInBangLuong).addComponent(btnCapNhatBangLuong))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								pnlBangLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblThangBangLuong)
										.addComponent(cboThangBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNamBangLuong).addComponent(cboNamBangLuong,
												javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18).addComponent(speBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 487,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));

		tbpNV.addTab("Bảng Lương", pnlBangLuongNV);

		pnlTimKiemNV.setBackground(new java.awt.Color(255, 255, 255));

		String[] colHeaderTimKiemNhanVien = { "Mã công nhân", "Tên công nhân", "Giới tính", "Ngày sinh",
				"Ngày bắt đầu làm việc", "CMND", "Địa chỉ", "Số điện thoại", "Trợ cấp", "Hệ số lương" };
		modelListTimKiemNhanVien = new DefaultTableModel(colHeaderTimKiemNhanVien, 0);
		tblTimKiemNV = new JTable(modelListTimKiemNhanVien);
		speTimKiemNV.setViewportView(tblTimKiemNV);

		lblMaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblMaNV.setText("Mã Nhân Viên: ");

		lblHoTenNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblHoTenNV.setText("Họ Tên Nhân Viên:");

		lblCmndNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblCmndNV.setText("CMND:");

		lblNgaySinhNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNgaySinhNV.setText("Ngày Sinh:");

		lblNgayLamVienNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblNgayLamVienNV.setText("Ngày Làm Việc");

		radioBtnNamNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		radioBtnNamNV.setText("Nam");

		radioBtnNuNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		radioBtnNuNV.setText("Nữ");

		btnTimNV.setBackground(new java.awt.Color(255, 255, 51));
		btnTimNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search.png"))); // NOI18N
		btnTimNV.setText("Tìm");
		btnTimNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimKiemNVActionPerformed(evt);
			}

		});

		lblGioiTinhNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lblGioiTinhNV.setText("Giới tính:");

		dateNgaySinhNV.setDateFormatString("dd-MM-yyyy");

		dateNgayLamViecNV.setDateFormatString("dd-MM-yyyy");

		txtCmndNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtCmndNVActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnlTimKiemNVLayout = new javax.swing.GroupLayout(pnlTimKiemNV);
		pnlTimKiemNV.setLayout(pnlTimKiemNVLayout);
		pnlTimKiemNVLayout.setHorizontalGroup(pnlTimKiemNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGroup(pnlTimKiemNVLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(btnTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(pnlTimKiemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(58, 58, 58)
										.addGroup(pnlTimKiemNVLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
														.addGroup(pnlTimKiemNVLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblCmndNV).addComponent(lblMaNV))
														.addGap(39, 39, 39)
														.addGroup(pnlTimKiemNVLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(txtCmndNV).addComponent(txtMaNhanVien,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 161,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
														.addComponent(lblNgayLamVienNV,
																javax.swing.GroupLayout.PREFERRED_SIZE, 122,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(42, 42, 42).addComponent(dateNgayLamViecNV,
																javax.swing.GroupLayout.PREFERRED_SIZE, 164,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(43, 43, 43)
										.addGroup(pnlTimKiemNVLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
														.addComponent(lblHoTenNV).addGap(75, 75, 75).addComponent(
																txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 206,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
														.addGroup(pnlTimKiemNVLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblGioiTinhNV,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 87,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNgaySinhNV))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(pnlTimKiemNVLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
																		.addComponent(radioBtnNamNV,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				78,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(65, 65, 65).addComponent(radioBtnNuNV,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				63,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(dateNgaySinhNV,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 206,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))))
								.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(28, 28, 28).addComponent(
										speTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 882,
										javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(35, Short.MAX_VALUE)));
		pnlTimKiemNVLayout.setVerticalGroup(pnlTimKiemNVLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemNVLayout.createSequentialGroup()
						.addGroup(pnlTimKiemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(21, 21, 21).addGroup(
										pnlTimKiemNVLayout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblMaNV)
												.addComponent(lblHoTenNV).addComponent(txtMaNhanVien,
														javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemNVLayout
										.createSequentialGroup().addContainerGap().addComponent(txtHoTenNV,
												javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(pnlTimKiemNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(40, 40, 40)
										.addComponent(lblNgaySinhNV).addGap(31, 31, 31)
										.addGroup(pnlTimKiemNVLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(lblNgayLamVienNV)
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup()
														.addGroup(pnlTimKiemNVLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblGioiTinhNV)
																.addComponent(radioBtnNamNV))
														.addGap(1, 1, 1)))
										.addGap(22, 22, 22).addComponent(btnTimNV,
												javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGroup(pnlTimKiemNVLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(31, 31, 31)
												.addGroup(pnlTimKiemNVLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblCmndNV).addComponent(txtCmndNV,
																javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(32, 32, 32))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemNVLayout
												.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(dateNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(28, 28, 28)))
										.addGroup(pnlTimKiemNVLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(radioBtnNuNV)
												.addGroup(pnlTimKiemNVLayout.createSequentialGroup().addGap(3, 3, 3)
														.addComponent(dateNgayLamViecNV,
																javax.swing.GroupLayout.PREFERRED_SIZE, 35,
																javax.swing.GroupLayout.PREFERRED_SIZE)))))
						.addGap(15, 15, 15).addComponent(speTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 330,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(16, Short.MAX_VALUE)));

		tbpNV.addTab("Tìm Kiếm", pnlTimKiemNV);

		javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
		pnlNhanVien.setLayout(pnlNhanVienLayout);
		pnlNhanVienLayout
				.setHorizontalGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(pnlTieuDeNV, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(pnlNhanVienLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(tbpNV)));
		pnlNhanVienLayout
				.setVerticalGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pnlNhanVienLayout.createSequentialGroup()
								.addComponent(pnlTieuDeNV, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tbpNV, javax.swing.GroupLayout.PREFERRED_SIZE, 636,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pnlCards.add(pnlNhanVien, "pnlCard2");

		jSplitPane1.setRightComponent(pnlCards);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE));

		tblSP.addMouseListener(this);
		tbpTK.addMouseListener(new CustomMouseListenerTK());
		tblDanhSachSP.addMouseListener(new DanhSachSPMouseListener());
		tblChamCongNV.addMouseListener(this);
		tblDanhSachCongDoanSP.addMouseListener(new DanhSachCDSPMouseList());
		tblDanhSachChamCongSP.addMouseListener(new DanhSachSanPhamCCCDMouseListener());
		tblDanhSachChamCongCongDoanSP.addMouseListener(new DanhSachCongDoanCCCDMouseListener());
		pack();
		setLocationRelativeTo(null);
		btnTimCN.addActionListener(this);
		btnCapNhatCN.addActionListener(this);
		ButtonGroup groupCN = new ButtonGroup();
		groupCN.add(radioBtnNuCN);
		groupCN.add(radioBtnNamCN);
		radioBtnNamCN.setSelected(true);

		ButtonGroup groupNV = new ButtonGroup();
		groupNV.add(radioBtnNuNV);
		groupNV.add(radioBtnNamNV);
		radioBtnNamNV.setSelected(true);
	}

	// CÔNG NHÂN
	// Danh sách công nhân
	private void docDuLieuTuSQLTableCN() {
		modelCongNhan.setRowCount(0);
		congNhan = new CongNhan_DAO();
		tblListCongNhan.setRowHeight(35);
		for (CongNhan cn : congNhan.getAllCongNhan()) {
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam",
					cn.getNgaySinh(), cn.getNgayBatDauLamViec(), cn.getCMND(), cn.getDiaChi(), cn.getSoDienThoai(),
					cn.getTroCap() };
			modelCongNhan.addRow(rowData);
		}
	}

	// NHÂN VIÊN
	// Danh sách Nhân Viên
	private void docDuLieuTuSQLTableNV() {
		modelNhanVien.setRowCount(0);
		nhanVien = new NhanVien_DAO();
		tblListNhanVien.setRowHeight(35);
		for (NhanVien nv : nhanVien.getAllNhanVien()) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getNgaySinh(), nv.getNgayBatDauLamViec(), nv.getCMND(), nv.getDiaChi(), nv.getTroCap(),
					nv.getSoDienThoai(), nv.getHeSoLuong() };
			modelNhanVien.addRow(rowData);
		}
	}

	// Chấm công nhân viên
	private void docDuLieuTuSQLTableChamCongNV() {
		nhanVien = new NhanVien_DAO();
		chamCongNV = new ChamCongNhanVien_DAO();
		tblChamCongNV.setRowHeight(35);
		ArrayList<ChamCongNhanVien> dsCCNV = chamCongNV.getDanhSachChamCongNhanVienHomNay();
		ArrayList<ChamCongNhanVien> listCCNV = new ArrayList<ChamCongNhanVien>();

		int soLuongChamCongNV = chamCongNV.getSoLuongChamCongNV();
		String maChamCongNV = null;
		ChamCongNhanVien ccnv;
		Date ngayCham = dateNgayChamCongNV.getDate();
		java.sql.Date sqlDate = null;
		sqlDate = new java.sql.Date(ngayCham.getTime());
		if (dsCCNV.size() == 0) {

			for (NhanVien nv : nhanVien.getAllNhanVien()) {
				if (soLuongChamCongNV < 10)
					maChamCongNV = "CCNV00" + soLuongChamCongNV;
				if (soLuongChamCongNV >= 10 && soLuongChamCongNV < 100)
					maChamCongNV = "CCNV0" + soLuongChamCongNV;
				if (soLuongChamCongNV >= 100)
					maChamCongNV = "CCNV" + soLuongChamCongNV;
				soLuongChamCongNV++;

				ccnv = new ChamCongNhanVien(maChamCongNV, sqlDate, false, false, "", 0,
						new NhanVien(nv.getMaNhanVien()));
				listCCNV.add(ccnv);
				chamCongNV.insertChamCongNV(ccnv);
			}

			for (ChamCongNhanVien ccnv1 : listCCNV) {
				String hoTenVN = nhanVien.getNhanVien(ccnv1.getNhanVien().getMaNhanVien()).getHoTenNhanVien();
				Object[] rowData = { ccnv1.getNhanVien().getMaNhanVien(), hoTenVN, ccnv1.isVangMat(), ccnv1.isCoPhep(),
						ccnv1.getGhiChu(), ccnv1.getSoGioTangCa() };
				modelChamCongNV.addRow(rowData);
			}
		} else {
			for (ChamCongNhanVien ccnv1 : dsCCNV) {
				String hoTenVN = nhanVien.getNhanVien(ccnv1.getNhanVien().getMaNhanVien()).getHoTenNhanVien();
				Object[] rowData = { ccnv1.getNhanVien().getMaNhanVien(), hoTenVN, ccnv1.isVangMat(), ccnv1.isCoPhep(),
						ccnv1.getGhiChu(), ccnv1.getSoGioTangCa() };
				modelChamCongNV.addRow(rowData);
			}
		}

	}

	// SẢN PHẨM
	// Danh sách sản phẩm
	private void docDuLieuTuSQLTableSP() {
		modelSanPham.setRowCount(0);
		sanPham = new SanPham_DAO();
		tblSP.setRowHeight(35);
		for (SanPham sp : sanPham.getAllSanPham()) {
			Object[] rowData = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getKieuDang(), sp.getChatLieu(),
					sp.getSoLuong(), sp.getTrangThai() ? "Hoàn thành" : "Chưa hoàn thành" };
			modelSanPham.addRow(rowData);
		}

	}

	// Danh sách công đoạn
	private void docDuLieuTuSQLTableCongDoanSP(String maSP) {
		modelCongDoanSP.setRowCount(0);
		congDoan = new CongDoan_DAO();
		tblCongDoanSP.setRowHeight(35);
		for (CongDoan cd : congDoan.getCongDoanTheoSanPham(maSP)) {
			String[] rowData = { cd.getMaCongDoan(), cd.getTenCongDoan(), cd.getGiaCongDoan() + "",
					cd.getSoLuong() + "" };
			modelCongDoanSP.addRow(rowData);
		}
		tblCongDoanSP.setModel(modelCongDoanSP);
	}

	// PHÂN CÔNG
	// Danh sách sản phẩm
	private void docDuLieuTuSQLTableDanhSachSP() {
		modelListSP.setRowCount(0);
		sanPham = new SanPham_DAO();
		tblDanhSachSP.setRowHeight(35);
		for (SanPham sp : sanPham.getAllSanPham()) {
			if (sp.getTrangThai() == false) {
				Object[] rowData = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getKieuDang(), sp.getChatLieu(),
						sp.getSoLuong() };
				modelListSP.addRow(rowData);
			}
		}
	}

	// Danh sách công đoạn
	private void docDuLieuTuSQLTableDanhSachCongDoanSP(String maSP) {
		congDoan = new CongDoan_DAO();
		tblDanhSachCongDoanSP.setRowHeight(35);
		modelListCDSP.setRowCount(0);
		chamCongCD = new ChamCongCongDoan_DAO();
		ArrayList<String> listMaCD = chamCongCD.getDanhSachMaCD();
		ArrayList<String> listMaCD2 = new ArrayList<String>();

		int soNguoi = 0;
		Object[] rowData = null;

		for (CongDoan cd : congDoan.getCongDoanTheoSanPham(maSP)) {
			listMaCD2.add(cd.getMaCongDoan());
		}

		listMaCD2.removeAll(listMaCD);

		for (String maCD : listMaCD) {
			for (CongDoan cd : congDoan.getCongDoanTheoSanPham(maSP)) {
				if (cd.getMaCongDoan().equals(maCD)) {
					soNguoi = chamCongCD.getDanhSachMaCNThucHienCD(maCD).size();
					rowData = new Object[] { cd.getMaCongDoan(), cd.getTenCongDoan(), cd.getGiaCongDoan() + "",
							cd.getSoLuong() + "", soNguoi + "" };
					modelListCDSP.addRow(rowData);
				}

			}

		}

		for (String maCD : listMaCD2) {
			for (CongDoan cd : congDoan.getCongDoanTheoSanPham(maSP)) {
				if (cd.getMaCongDoan().equals(maCD)) {
					soNguoi = chamCongCD.getSoNguoiThamGiaTheoMaCD(maCD);
					rowData = new Object[] { cd.getMaCongDoan(), cd.getTenCongDoan(), cd.getGiaCongDoan() + "",
							cd.getSoLuong() + "", soNguoi + "" };
					modelListCDSP.addRow(rowData);
				}

			}

		}

	}

	// Danh sách công Nhân Chưa Phân Công
	private void docDuLieuTuSQLTableDanhSachCNChuaPhanCong() {
		congNhan = new CongNhan_DAO();
		chamCongCD = new ChamCongCongDoan_DAO();
		tblDanhSachCNChuaPhanCongSP.setRowHeight(35);
		for (CongNhan cn : congNhan.getAllCongNhan()) {
			if (cn.isTrangThai() == false) {
				Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam" };
				modelListCNChuaPhanCong.addRow(rowData);
				size++;
			}
		}
	}

	// Danh sách công nhân thực hiện công đoạn
	private void docDuLieuTuSQLTableDanhSachCNThucHienCDSP(String maCongDoan) {
		modelListCNThucHienCDSP.setRowCount(0);
		tblDanhSachCNThucHienCongDoanSP.setRowHeight(35);
		congNhan = new CongNhan_DAO();
		chamCongCD = new ChamCongCongDoan_DAO();

		for (String maCN : chamCongCD.getDanhSachMaCNThucHienCD(maCongDoan)) {
			CongNhan cn = congNhan.getCongNhan(maCN);
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam" };
			modelListCNThucHienCDSP.addRow(rowData);
		}

	}

	// CHẤM CÔNG CÔNG ĐOẠN
	// Danh sách sản phẩm
	private void docDuLieuTuSQLTableDanhSachSanPhamCCCD() {
		sanPham = new SanPham_DAO();
		tblDanhSachChamCongSP.setRowHeight(35);
		for (SanPham sp : sanPham.getAllSanPham()) {
			if (sp.getTrangThai() == false) {
				Object[] rowData = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getKieuDang(), sp.getChatLieu(),
						sp.getSoLuong() };
				modelListChamCongSP.addRow(rowData);
			}
		}

	}

	// Danh sách công đoạn
	private void docDuLieuSQLTableDanhSachCongDoanCCCD(String maSP) {
		congDoan = new CongDoan_DAO();
		tblDanhSachChamCongCongDoanSP.setRowHeight(35);
		modelListCCCDSP.setRowCount(0);
		chamCongCD = new ChamCongCongDoan_DAO();
		congNhan = new CongNhan_DAO();
		Object[] rowData = null;
		for (CongDoan cd : congDoan.getCongDoanTheoSanPham(maSP)) {
			int soLuongTongCuaCN = chamCongCD.demSumSoLuongCCCD(cd.getMaCongDoan());
			rowData = new Object[] { cd.getMaCongDoan(), cd.getTenCongDoan(), cd.getGiaCongDoan() + "",
					cd.getSoLuong() + "", soLuongTongCuaCN + "" };
			modelListCCCDSP.addRow(rowData);

		}

	}

	// Danh sách công nhân cần chấm công
	private void docDuLieuTuSQLTableDanhSachCNCanChamCongCCCD(String maCD) {
		modelListCNCanChamCong.setRowCount(0);
		tblDanhSachCNCanChamCongSP.setRowHeight(35);
		congNhan = new CongNhan_DAO();
		chamCongCD = new ChamCongCongDoan_DAO();
		congDoan = new CongDoan_DAO();
		int soLuongTongCuaCN = chamCongCD.demSumSoLuongCCCD(maCD);
		int soLuongCD = congDoan.getSoLuongCongDoan(maCD);

		ArrayList<String> listMaCN = chamCongCD.getDanhSachMaCNThucHienCD(maCD);

		Date ngayCham = dateNgayChamCongCongDoan.getDate();
		java.sql.Date sqlDate = null;
		sqlDate = new java.sql.Date(ngayCham.getTime());
		ChamCongCongDoan cccd;
		int soLuongChamCongCD = chamCongCD.getSoLuongChamCongCongDoan();
		String maChamCongCD = null;
		ArrayList<ChamCongCongDoan> listCCCD3 = new ArrayList<ChamCongCongDoan>();

		if (soLuongCD > soLuongTongCuaCN) {
			ArrayList<ChamCongCongDoan> listCCCD = chamCongCD.getDanhSachChamCongCongDoanHomNay(maCD);
			if (listCCCD.size() == 0) {
				for (CongNhan cn : congNhan.getAllCongNhan()) {
					for (String maCN : listMaCN) {
						if (cn.getMaCongNhan().equalsIgnoreCase(maCN)) {
							if (soLuongChamCongCD < 10)
								maChamCongCD = "CCCD00" + soLuongChamCongCD;
							if (soLuongChamCongCD >= 10 && soLuongChamCongCD < 100)
								maChamCongCD = "CCCD0" + soLuongChamCongCD;
							if (soLuongChamCongCD >= 100)
								maChamCongCD = "CCCD" + soLuongChamCongCD;
							soLuongChamCongCD++;
							cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD), new CongNhan(maCN), sqlDate,
									false, false, "Sáng", 0);
							listCCCD3.add(cccd);
							chamCongCD.insertChamCongCD(cccd);
						}

					}

				}

				for (ChamCongCongDoan cccdToday : listCCCD3) {
					String maCN = cccdToday.getCongNhan().getMaCongNhan();
					CongNhan cn = congNhan.getCongNhan(maCN);
					Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cccdToday.isVangMat(),
							cccdToday.isCoPhep(), cccdToday.getCaLam(), cccdToday.getSoLuong() };
					modelListCNCanChamCong.addRow(rowData);
				}
			} else {
				for (ChamCongCongDoan cccdToday : listCCCD) {
					String maCN = cccdToday.getCongNhan().getMaCongNhan();
					CongNhan cn = congNhan.getCongNhan(maCN);
					Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cccdToday.isVangMat(),
							cccdToday.isCoPhep(), cccdToday.getCaLam(), cccdToday.getSoLuong() };
					modelListCNCanChamCong.addRow(rowData);
				}
			}

		} else {
			for (ChamCongCongDoan cccd1 : chamCongCD.getDanhSachChamCongCNThucHienCD(maCD)) {

				String maCN = cccd1.getCongNhan().getMaCongNhan();
				CongNhan cn = congNhan.getCongNhan(maCN);
				int soLuongCN = chamCongCD.getSoLuongCuaCongNhan(maCD, maCN);
				Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), true, true, "Sáng", soLuongCN };
				modelListCNCanChamCong.addRow(rowData);
			}
		}

	}

	private void docDuLieuTuSQLTableNhanVienTK() {
		nhanVienTK = new ThongKe_DAO();
		tblNhanVienTK.setRowHeight(35);
		for (NhanVien nv : nhanVienTK.getAllNhanVienTK()) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getNgaySinh(), nv.getHeSoLuong(), nhanVienTK.getThangNV(nv.getMaNhanVien()),
					nhanVienTK.getNamNV(nv.getMaNhanVien()), nhanVienTK.getLuongNV(nv.getMaNhanVien()) };
			modelNhanVienTK.addRow(rowData);
		}
	}

	private void docDuLieuTuSQLTableThangNamNhanVienTK(int thang, int nam) {
		nhanVienTK = new ThongKe_DAO();
		modelNhanVienTK.setRowCount(0);
		tblNhanVienTK.setRowHeight(35);
		for (NhanVien nv : nhanVienTK.getNhanVienTheoThangNam(thang, nam)) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getNgaySinh(), nv.getHeSoLuong(), nhanVienTK.getThangNV(nv.getMaNhanVien()),
					nhanVienTK.getNamNV(nv.getMaNhanVien()), nhanVienTK.getLuongNV(nv.getMaNhanVien()) };
			modelNhanVienTK.addRow(rowData);
		}
	}

	public void docDuLieuTuSQLTableCongNhanTK() {
		modelCongNhanTK.setRowCount(0);
		congNhanTK = new ThongKe_DAO();
		tblCongNhanTK.setRowHeight(35);
		for (CongNhan cn : congNhanTK.getAllCongNhanTK()) {
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam",
					cn.getNgaySinh(), congNhanTK.getThangCN(cn.getMaCongNhan()),
					congNhanTK.getNamCN(cn.getMaCongNhan()), congNhanTK.getLuongCN(cn.getMaCongNhan()) };
			modelCongNhanTK.addRow(rowData);
		}
	}

	private void docDuLieuTuSQLTableThangNamCongNhanTK(int thang, int nam) {
		congNhanTK = new ThongKe_DAO();
		modelCongNhanTK.setRowCount(0);
		tblCongNhanTK.setRowHeight(35);
		for (CongNhan cn : congNhanTK.getCongNhanTheoThangNam(thang, nam)) {
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam",
					cn.getNgaySinh(), congNhanTK.getThangCN(cn.getMaCongNhan()),
					congNhanTK.getNamCN(cn.getMaCongNhan()), congNhanTK.getLuongCN(cn.getMaCongNhan()) };
			modelCongNhanTK.addRow(rowData);
		}
	}

	private void docDuLieuTuSQLTableSanPhamTK(String tenSanPham) {
		modelSanPhamTK.setRowCount(0);
		thongKeSP = new ThongKe_DAO();
		tblSanPhamTK.setRowHeight(35);
		for (CongDoan cd : thongKeSP.getCongDoanTheoTenSanPham(tenSanPham)) {
			String[] rowData = { cd.getMaCongDoan(), cd.getTenCongDoan(), cd.getGiaCongDoan() + "",
					cd.getSoLuong() + "" };
			modelSanPhamTK.addRow(rowData);
		}
		tblSanPhamTK.setModel(modelSanPhamTK);
	}

	private void docDuLieuTuSQLTableLuongNV() {
//		modeluongNhanVien.setRowCount(0);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
////	   System.out.println(dtf.format(now)); 
//
//		nhanVien = new NhanVien_DAO();
//		luongNhanVien_Dao = new BangLuongNhanVien_Dao();
//		tblBangLuongNV.setRowHeight(35);
//		nhanVien = new NhanVien_DAO();
//		for (BangLuongNhanVien lnv : luongNhanVien_Dao.getAllBangLuongNhanVien()) {
//			String luongNhanVien = String.format("%,d", lnv.getLuong());
//			String hotenNV = nhanVien.getNhanVien(lnv.getNhanVien().getMaNhanVien()).getHoTenNhanVien();
//			Object[] rowData = { lnv.getMaLuongNV(), hotenNV, luongNhanVien + " đ",lnv.getThang(),lnv.getNam()
//					,lnv.getNhanVien().getMaNhanVien() };
//			modeluongNhanVien.addRow(rowData);
//		}
		nhanVienTK = new ThongKe_DAO();
		modeluongNhanVien.setRowCount(0);
		tblBangLuongNV.setRowHeight(35);
		for (NhanVien nv : nhanVienTK.getAllNhanVienTK()) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getNgaySinh(), nv.getHeSoLuong(), nhanVienTK.getThangNV(nv.getMaNhanVien()),
					nhanVienTK.getNamNV(nv.getMaNhanVien()), nhanVienTK.getLuongNV(nv.getMaNhanVien()) };
			modeluongNhanVien.addRow(rowData);
		}
	}

	private void docDuLieuTuSQLTableLuongCN() {
		modeluongCongNhan.setRowCount(0);
		congNhanTK = new ThongKe_DAO();
		tblBangLuongCN.setRowHeight(35);
		for (CongNhan cn : congNhanTK.getAllCongNhanTK()) {
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam",
					cn.getNgaySinh(), congNhanTK.getThangCN(cn.getMaCongNhan()),
					congNhanTK.getNamCN(cn.getMaCongNhan()), congNhanTK.getLuongCN(cn.getMaCongNhan()) };
			modeluongCongNhan.addRow(rowData);
		}
	}

	private void docDuLieuRefeshTableLuongCN() {
		modeluongCongNhan.setRowCount(0);
		luongCongNhan_Dao = new BangLuongCongNhan_Dao();
		tblBangLuongNV.setRowHeight(35);
		congNhan = new CongNhan_DAO();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
// 	   System.out.println(dtf.format(now)); 

		for (BangLuongCongNhan lcn : luongCongNhan_Dao.getAllBangLuongCongNhan()) {
			String luongCongNhan = String.format("%,d", lcn.getLuong());
			String hotenCN = congNhan.getCongNhan(lcn.getCongNhan().getMaCongNhan()).getHoTenCongNhan();

			Object[] rowData = { lcn.getMaLuongCN(),

					hotenCN,
//					lcn.getLuong(),
					luongCongNhan + "đ",

					dtf.format(now), lcn.getCongNhan().getMaCongNhan()

			};
			modeluongCongNhan.addRow(rowData);

		}
	}

	private void disableFlied(boolean b) {
		txtMaSP.setEnabled(b);
		txtTenSP.setEnabled(b);
		txtSoLuongSP.setEnabled(b);
		dateNgayChamCongNV.setEnabled(b);
		dateNgayChamCongCongDoan.setEnabled(b);
	}

	private void font() {
		Font font = new Font("Segoe UI", 0, 16);
		txtMaSP.setFont(font);
		txtTenSP.setFont(font);
		txtSoLuongSP.setFont(font);

	}

	private void setDateMacDinh() {
		dateNgayChamCongNV = new JDateChooser(new Date());
		dateNgayChamCongCongDoan = new JDateChooser(new Date());
		dateNgayLamViecCN = new JDateChooser(new Date());
		dateNgayLamViecNV = new JDateChooser(new Date());
		dateNgaySinhCN = new JDateChooser(new Date());
		dateNgaySinhNV = new JDateChooser(new Date());
	}

	private void btnTimKiemCNActionPerformed(ActionEvent evt) {
		tblTimKiemCN.setRowHeight(35);
		boolean gioiTinh = true;
		if (radioBtnNamCN.isSelected())
			gioiTinh = true;
		else
			gioiTinh = false;
		String maCN = txtMaCongNhan.getText().toString();
		String hoTenCN = txtHoTenCN1.getText().toString();
		Date sqlDate = dateNgayLamViecCN.getDate();
		if (sqlDate == null) {
			sqlDate = new Date();
		}
		java.sql.Date ngayBatDauLamViec = null;
		ngayBatDauLamViec = new java.sql.Date(sqlDate.getTime());
		Date sqlDate1 = dateNgaySinhCN.getDate();
		if (sqlDate1 == null) {
			sqlDate1 = new Date();
		}
		java.sql.Date ngaySinh = null;
		ngaySinh = new java.sql.Date(sqlDate1.getTime());
		String CMND = txtCmndCN.getText().toString();
		modellistTimKiemCongNhan.setRowCount(0);
		congNhan = new CongNhan_DAO();

		if (maCN.length() <= 0 || maCN.equalsIgnoreCase("")) {
			maCN = null;
		}
		if (hoTenCN.length() <= 0 || hoTenCN.equalsIgnoreCase("")) {
			hoTenCN = null;
		}
		if (ngayBatDauLamViec.toString().equalsIgnoreCase(LocalDate.now().toString())) {
			ngayBatDauLamViec = null;
		}
		if (ngaySinh.toString().equalsIgnoreCase(LocalDate.now().toString())) {
			ngaySinh = null;
		}
		if (CMND.length() <= 0 || CMND.equalsIgnoreCase("")) {
			CMND = null;
		}

		for (CongNhan cn : congNhan.search(maCN, hoTenCN, ngayBatDauLamViec, ngaySinh, CMND, gioiTinh)) {
			Object[] rowData = { cn.getMaCongNhan(), cn.getHoTenCongNhan(), cn.isGioiTinh() ? "Nữ" : "Nam",
					cn.getNgaySinh(), cn.getNgayBatDauLamViec(), cn.getCMND(), cn.getDiaChi(), cn.getSoDienThoai(),
					cn.getTroCap() };
			modellistTimKiemCongNhan.addRow(rowData);

		}
		tblTimKiemCN.setModel(modellistTimKiemCongNhan);
	}

	private void btnTimKiemNVActionPerformed(ActionEvent evt) {
		tblTimKiemNV.setRowHeight(35);
		boolean gioiTinh = true;
		if (radioBtnNamNV.isSelected())
			gioiTinh = true;
		else
			gioiTinh = false;
		String maNV = txtMaNhanVien.getText().toString();
		String hoTenNV = txtHoTenNV.getText().toString();
		Date sqlDate = dateNgayLamViecNV.getDate();
		if (sqlDate == null) {
			sqlDate = new Date();
		}
		java.sql.Date ngayBatDauLamViec = null;
		ngayBatDauLamViec = new java.sql.Date(sqlDate.getTime());
		Date sqlDate1 = dateNgaySinhNV.getDate();
		if (sqlDate1 == null) {
			sqlDate1 = new Date();
		}
		java.sql.Date ngaySinh = null;
		ngaySinh = new java.sql.Date(sqlDate1.getTime());
		String CMND = txtCmndNV.getText().toString();
		modelListTimKiemNhanVien.setRowCount(0);
		nhanVien = new NhanVien_DAO();

		if (maNV.length() <= 0 || maNV.equalsIgnoreCase("")) {
			maNV = null;
		}
		if (hoTenNV.length() <= 0 || hoTenNV.equalsIgnoreCase("")) {
			hoTenNV = null;
		}
		if (ngayBatDauLamViec.toString().equalsIgnoreCase(LocalDate.now().toString())) {
			ngayBatDauLamViec = null;
		}
		if (ngaySinh.toString().equalsIgnoreCase(LocalDate.now().toString())) {
			ngaySinh = null;
		}
		if (CMND.length() <= 0 || CMND.equalsIgnoreCase("")) {
			CMND = null;
		}
		for (NhanVien nv : nhanVien.search(maNV, hoTenNV, ngayBatDauLamViec, ngaySinh, CMND, gioiTinh)) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getNgaySinh(), nv.getNgayBatDauLamViec(), nv.getCMND(), nv.getDiaChi(), nv.getTroCap(),
					nv.getSoDienThoai(), nv.getHeSoLuong() };
			modelListTimKiemNhanVien.addRow(rowData);

		}
		;
		tblTimKiemNV.setModel(modelListTimKiemNhanVien);
	}

	private void btnluuSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemSPActionPerformed
		SanPham_DAO sp_DAO = new SanPham_DAO();
		if (cbxHoanThanhSP.isSelected()) {
			sp_DAO.update(txtMaSP.getText(), true);
		} else
			sp_DAO.update(txtMaSP.getText(), false);

		docDuLieuTuSQLTableSP();
	}

	private void btnChamCongNVActionPerformed(ActionEvent evt) {
		nhanVien = new NhanVien_DAO();
		chamCongNV = new ChamCongNhanVien_DAO();
		ArrayList<ChamCongNhanVien> listCCNV = new ArrayList<ChamCongNhanVien>();
		ArrayList<ChamCongNhanVien> listCCNV2 = new ArrayList<ChamCongNhanVien>();
		ChamCongNhanVien ccnv;
		Date ngayCham = dateNgayChamCongNV.getDate();
		java.sql.Date sqlDate = null;
		sqlDate = new java.sql.Date(ngayCham.getTime());
		int n = modelChamCongNV.getRowCount();

		for (int i = 0; i < n; i++) {
			boolean vangMat, coPhep;

			String maNV = modelChamCongNV.getValueAt(i, 0).toString();

			vangMat = (boolean) modelChamCongNV.getValueAt(i, 2);

			coPhep = (boolean) modelChamCongNV.getValueAt(i, 3);

			String ghiChu;
			if (modelChamCongNV.getValueAt(i, 4) == null)
				ghiChu = "";
			else
				ghiChu = modelChamCongNV.getValueAt(i, 4).toString();

			int soGioTangCa;
			if (modelChamCongNV.getValueAt(i, 5) == null)
				soGioTangCa = 0;
			else
				soGioTangCa = Integer.parseInt(modelChamCongNV.getValueAt(i, 5).toString());

			ccnv = new ChamCongNhanVien(vangMat, coPhep, ghiChu, soGioTangCa, new NhanVien(maNV));
			listCCNV.add(ccnv);

		}
		int selection = JOptionPane.showConfirmDialog(this, "Xác nhận chấm công ngày " + sqlDate, "Cảnh báo",
				JOptionPane.YES_NO_OPTION);
		if (selection == JOptionPane.YES_OPTION) {
			for (ChamCongNhanVien ccNV : listCCNV) {
				if (ccNV.isVangMat() == false && ccNV.isCoPhep() == true) {
					JOptionPane.showMessageDialog(this,
							"Nhân viên " + ccNV.getNhanVien().getMaNhanVien() + " không vắng thì không có phép");
				} else
					listCCNV2.add(ccNV);
			}

			if (listCCNV.size() == listCCNV2.size()) {
				for (ChamCongNhanVien ccNV : listCCNV) {
					if (ccNV.isVangMat() == true) {
						ccNV.setSoGioTangCa(0);
					}
					chamCongNV.updateChamCongNhanVienHomNay(ccNV);
				}

				JOptionPane.showMessageDialog(this, "Chấm công nhân viên thành công");
				modelChamCongNV.setRowCount(0);
				docDuLieuTuSQLTableChamCongNV();

			}

		}

	}

	private void btnPhanCongThuCongActionPerformed(ActionEvent evt) {
		chamCongCD = new ChamCongCongDoan_DAO();
		congNhan = new CongNhan_DAO();
		int rowCD = tblDanhSachCongDoanSP.getSelectedRow();
		if (rowCD == -1)
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn công đoạn để phân công");
		else {
			Date ngayCham = dateNgayChamCongCongDoan.getDate();
			java.sql.Date sqlDate = null;
			sqlDate = new java.sql.Date(ngayCham.getTime());
			int soLuongChamCongCD = chamCongCD.getSoLuongChamCongCongDoan();
			String maCD = modelListCDSP.getValueAt(rowCD, 0).toString();
			int rowCNChuaPhanCong = tblDanhSachCNChuaPhanCongSP.getSelectedRow();
			int soLuongTongCuaCN = chamCongCD.demSumSoLuongCCCD(maCD);
			if (rowCNChuaPhanCong == -1)
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn công nhân để phân công");
			else {
				String maChamCongCD = null;
				int soLuongCD = congDoan.getSoLuongCongDoan(maCD);

				if (soLuongChamCongCD < 10)
					maChamCongCD = "CCCD00" + soLuongChamCongCD;
				if (soLuongChamCongCD >= 10 && soLuongChamCongCD < 100)
					maChamCongCD = "CCCD0" + soLuongChamCongCD;
				if (soLuongChamCongCD >= 100)
					maChamCongCD = "CCCD" + soLuongChamCongCD;
				String maCN = modelListCNChuaPhanCong.getValueAt(rowCNChuaPhanCong, 0).toString();
				String tenCN = modelListCNChuaPhanCong.getValueAt(rowCNChuaPhanCong, 1).toString();
				boolean gioiTinh;
				if (modelListCNChuaPhanCong.getValueAt(rowCNChuaPhanCong, 2).toString() == "Nữ")
					gioiTinh = true;
				else
					gioiTinh = false;
				tblDanhSachCNThucHienCongDoanSP.setRowHeight(35);
				if (soLuongCD <= soLuongTongCuaCN)
					JOptionPane.showMessageDialog(this, "Công đoạn này đã hoàn thành");
				else {
					Object[] rowData = { maCN, tenCN, gioiTinh ? "Nữ" : "Nam" };
					congNhan.setTrangThaiCongNhan(true, maCN);
					modelListCNChuaPhanCong.removeRow(rowCNChuaPhanCong);
					modelListCNThucHienCDSP.addRow(rowData);
					ChamCongCongDoan cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD), new CongNhan(maCN),
							sqlDate, false, false, "Sáng", 0);
					chamCongCD.insertChamCongCD(cccd);
				}

			}

		}

	}

	private void btnChamCongSPActionPerformed(ActionEvent evt) {
		chamCongCD = new ChamCongCongDoan_DAO();
		Date ngayCham = dateNgayChamCongCongDoan.getDate();
		java.sql.Date sqlDate = null;
		sqlDate = new java.sql.Date(ngayCham.getTime());
		int n = modelListCNCanChamCong.getRowCount();
		ChamCongCongDoan cccd;
		int row = tblDanhSachChamCongCongDoanSP.getSelectedRow();

		if (row == -1)
			JOptionPane.showMessageDialog(this, "Chưa chọn công đoạn cần chấm công");
		else {
			String maCD = modelListCCCDSP.getValueAt(row, 0).toString();
			ArrayList<String> listMaCN = new ArrayList<String>();

			int soLuongTongCuaCN = chamCongCD.demSumSoLuongCCCD(maCD);
			int soLuongHomNay = chamCongCD.demSumSoLuongCCCDHomNay(maCD);
			int soLuongCD = congDoan.getSoLuongCongDoan(maCD);
			if (soLuongCD > soLuongTongCuaCN) {
				int soLuongTong = 0;
				ArrayList<ChamCongCongDoan> listCCCD = new ArrayList<ChamCongCongDoan>();
				for (int i = 0; i < n; i++) {
					Pattern pattern = Pattern.compile("\\d*");
					Matcher matcher = pattern.matcher(modelListCNCanChamCong.getValueAt(i, 5).toString());
					if (matcher.matches()) {
						String maCN = modelListCNCanChamCong.getValueAt(i, 0).toString();
						boolean vangMat, coPhep;
						vangMat = (boolean) modelListCNCanChamCong.getValueAt(i, 2);
						coPhep = (boolean) modelListCNCanChamCong.getValueAt(i, 3);
						String caLam;
						if (modelListCNCanChamCong.getValueAt(i, 4) == null)
							caLam = "Sáng";
						else
							caLam = modelListCNCanChamCong.getValueAt(i, 4).toString();

						int soLuong = Integer.parseInt(modelListCNCanChamCong.getValueAt(i, 5).toString());
						cccd = new ChamCongCongDoan(new CongDoan(maCD), new CongNhan(maCN), sqlDate, vangMat, coPhep,
								caLam, soLuong);
						soLuongTong = soLuongTong + soLuong;
						listCCCD.add(cccd);
						listMaCN.add(maCN);
					} else {

						JOptionPane.showMessageDialog(this, "Bạn phải nhập số dương và không nhập ký tự ở mã công nhân "
								+ modelListCNCanChamCong.getValueAt(i, 0).toString());
						continue;
					}

				}
				int selection = JOptionPane.showConfirmDialog(this, "Xác nhận chấm công ngày " + sqlDate, "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (selection == JOptionPane.YES_OPTION) {
					int soLuongVangMat = 0;
					ArrayList<ChamCongCongDoan> listCCCD2 = new ArrayList<ChamCongCongDoan>();
					for (ChamCongCongDoan chamCongCongDoan : listCCCD) {
						if (chamCongCongDoan.isVangMat() == false && chamCongCongDoan.isCoPhep() == true) {
							JOptionPane.showMessageDialog(this, "Công Nhân "
									+ chamCongCongDoan.getCongNhan().getMaCongNhan() + " không vắng thì không có phép");
						} else
							listCCCD2.add(chamCongCongDoan);

						if (chamCongCongDoan.isVangMat() == true) {
							soLuongVangMat += chamCongCongDoan.getSoLuong();
						}

					}

					if ((soLuongTong - soLuongVangMat) > soLuongCD && soLuongTongCuaCN == soLuongHomNay) {
						JOptionPane.showMessageDialog(this,
								"Số lượng tổng của công nhân phải bằng số lượng của công đoạn");
					} else if (soLuongTongCuaCN != soLuongHomNay
							&& (soLuongTongCuaCN - soLuongHomNay - soLuongVangMat + soLuongTong) > soLuongCD) {
						JOptionPane.showMessageDialog(this,
								"Số lượng tổng của công nhân phải bằng số lượng của công đoạn");

					}

					else {
						if (listCCCD.size() == listCCCD2.size()) {
							for (ChamCongCongDoan chamCongCongDoan : listCCCD) {
								if (chamCongCongDoan.isVangMat() == true) {
									chamCongCongDoan.setSoLuong(0);
								}
								chamCongCD.updateSoLuongCD(chamCongCongDoan);
								chamCongCD.updateChamCongCD(chamCongCongDoan);
							}
							modelCongDoanSP.setRowCount(0);
							docDuLieuTuSQLTableDanhSachCNCanChamCongCCCD(maCD);
							JOptionPane.showMessageDialog(this, "Chấm công thành công");
						}

						if (soLuongTongCuaCN == soLuongHomNay) {
							if ((soLuongTong - soLuongVangMat) == soLuongCD) {
								for (String maCN : listMaCN) {
									congNhan.setTrangThaiCongNhan(false, maCN);
								}
								JOptionPane.showMessageDialog(this, "Công đoạn hoàn thành");
							}
						} else if (soLuongTongCuaCN != soLuongHomNay) {
							if (soLuongCD == (soLuongTongCuaCN - soLuongHomNay - soLuongVangMat + soLuongTong)) {
								for (String maCN : listMaCN) {
									congNhan.setTrangThaiCongNhan(false, maCN);
								}
								JOptionPane.showMessageDialog(this, "Công đoạn hoàn thành");
							}
						}

					}

				}

			} else {
				JOptionPane.showMessageDialog(this, "Công đoạn đã hoàn thành, vui lòng không chấm công thêm");
			}
		}

	}

	class CustomMouseListenerNV implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int tabIndexNV = tbpNV.getSelectedIndex();
			System.out.println(txtMaNhanVienTC.getText().toString());
			if (tabIndexNV == 2) {
				switch (txtMaNhanVienTC.getText().toString()) {
				case "QLNS": {
					pnlBangLuongNV.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				case "QLSP": {
					pnlBangLuongNV.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				}
			} else {
				switch (txtMaNhanVienTC.getText().toString()) {
				case "KETOAN": {
					pnlTimKiemNV.removeAll();
					pnlChamCongNV.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class CustomMouseListenerTK implements MouseListener {
		public void mouseClicked(MouseEvent e) {

			int tbpIndexTK = tbpTK.getSelectedIndex();
			System.out.println(txtMaNhanVienTC.getText().toString());
			if (tbpIndexTK == 2) {
				switch (txtMaNhanVienTC.getText().toString()) {
				case "QLNS": {
					pnlSanPhamTK.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				}
			} else {
				switch (txtMaNhanVienTC.getText().toString()) {
				case "QLSP": {
					pnlCongNhanTK.removeAll();
					pnlNhanVienTK.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class CustomMouseListenerCN implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			int tabIndexCN = tbpCN.getSelectedIndex();
			System.out.println(txtMaNhanVienTC.getText().toString());
			if (tabIndexCN == 1) {

				switch (txtMaNhanVienTC.getText().toString()) {
				case "QLNS": {
					pnlBangLuongCN.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				case "QLSP": {
					pnlBangLuongCN.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}

				}
			} else {
				switch (txtMaNhanVienTC.getText().toString()) {
				case "KETOAN": {
					pnlTimKiemCN.removeAll();
					JOptionPane.showMessageDialog(null, "Bạn không được dùng chức năng này");
					break;
				}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatSPActionPerformed
		docDuLieuTuSQLTableSP();
	}

	private void btnPhanCongTuDongSPActionPerformed(java.awt.event.ActionEvent evt) {
		if (!dialogPhanCongTuDong.isVisible()) {
			dialogPhanCongTuDong.setVisible(true);
		}
	}

	private void txtCmndNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtCmndNVActionPerformed
	}

	private void btnCapNhatPCActionPerformed(java.awt.event.ActionEvent evt) {
		modelListCNChuaPhanCong.setRowCount(0);
		docDuLieuTuSQLTableDanhSachCNChuaPhanCong();
		modelListSP.setRowCount(0);
		docDuLieuTuSQLTableDanhSachSP();
	}

	private void btnCapNhatThongKeNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatThongKeNVActionPerformed

	}

	private void btnCapNhatThongKeCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatThongKeCNActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnCapNhatThongKeCNActionPerformed

	private void btnCapNhatThongKeSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatThongKeSPActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnCapNhatThongKeSPActionPerformed

	private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLamMoiNVActionPerformed
		// TODO add your handling code here:
		docDuLieuTuSQLTableNV();
	}// GEN-LAST:event_btnLamMoiNVActionPerformed

	private void btnLamMoiCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLamMoiCNActionPerformed
		// TODO add your handling code here:
		docDuLieuTuSQLTableCN();
	}// GEN-LAST:event_btnLamMoiCNActionPerformed

	private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTrangChuActionPerformed
		cardLayout.show(pnlCards, "pnlCard1");
	}// GEN-LAST:event_btnTrangChuActionPerformed

	private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNhanVienActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLNS": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		case "KETOAN": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
		}
	}// GEN-LAST:event_btnNhanVienActionPerformed

	private void btnCongNhanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCongNhanActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLNS": {
			cardLayout.show(pnlCards, "pnlCard3");
			System.out.println("check");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard3");
			break;
		}
		case "KETOAN": {
			cardLayout.show(pnlCards, "pnlCard3");
			break;
		}

		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
			break;
		}

	}// GEN-LAST:event_btnCongNhanActionPerformed

	private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSanPhamActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLSP": {
			cardLayout.show(pnlCards, "pnlCard4");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard4");
			break;
		}
		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
		}
	}// GEN-LAST:event_btnSanPhamActionPerformed

	private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThongKeActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "KETOAN": {
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
			break;
		}
		case "ADMIN": {
		}
		default:
			cardLayout.show(pnlCards, "pnlCard5");
		}
	}// GEN-LAST:event_btnThongKeActionPerformed

	private void btnThemCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemCNActionPerformed
		GUI_ThemCongNhan pi = new GUI_ThemCongNhan();
		pi.setVisible(true);
	}// GEN-LAST:event_btnThemCNActionPerformed

	private void cboThangCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboThangCNActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cboThangCNActionPerformed

	private void cboNamCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboNamCNActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cboNamCNActionPerformed

	private void cbxHoanThanhSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxHoanThanhSPActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cbxHoanThanhSPActionPerformed

	private void cboThangBangLuongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboThangBangLuongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cboThangBangLuongActionPerformed

	private void cboNamBangLuongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboNamBangLuongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cboNamBangLuongActionPerformed

	private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemNVActionPerformed

		GUI_ThemNhanVien pi = new GUI_ThemNhanVien();
		pi.setVisible(true);
	}// GEN-LAST:event_btnThemNVActionPerformed

	private void btnCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatNVActionPerformed
		int row = tblListNhanVien.getSelectedRow();
		if (row >= 0) {
			GUI_CapNhatNhanVien pi = new GUI_CapNhatNhanVien(this.getMaNV(row));
			pi.setVisible(true);
		} else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần cập nhật");
	}

	private void btnCapNhatCNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatCNActionPerformed
		int row = tblListCongNhan.getSelectedRow();
		if (row >= 0) {
			GUI_CapNhatCongNhan pi = new GUI_CapNhatCongNhan(this.getMaCN(row), row);
			pi.setVisible(true);
		} else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn công nhân cần cập nhật");

	}

	private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemSPActionPerformed

		GUI_ThemSanPham pi = new GUI_ThemSanPham();
		pi.setVisible(true);

	}

	private void btnCongNhanTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCongNhanTCActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLNS": {
			cardLayout.show(pnlCards, "pnlCard3");
			System.out.println("check");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard3");
			break;
		}
		case "KETOAN": {
			cardLayout.show(pnlCards, "pnlCard3");
			break;
		}

		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
			break;
		}
	}// GEN-LAST:event_btnCongNhanTCActionPerformed

	private void btnThongKeTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThongKeTCActionPerformed

		switch (txtMaNhanVienTC.getText().toString()) {
		case "KETOAN": {
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
			break;
		}
		case "ADMIN": {
		}
		default:
			cardLayout.show(pnlCards, "pnlCard5");
		}
	}// GEN-LAST:event_btnThongKeTCActionPerformed

	private void btnSanPhamTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSanPhamTCActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLSP": {
			cardLayout.show(pnlCards, "pnlCard4");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard4");
			break;
		}
		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
		}
	}// GEN-LAST:event_btnSanPhamTCActionPerformed

	private void btnNhanVienTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNhanVienTCActionPerformed
		switch (txtMaNhanVienTC.getText().toString()) {
		case "QLNS": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		case "ADMIN": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		case "KETOAN": {
			cardLayout.show(pnlCards, "pnlCard2");
			break;
		}
		default:
			JOptionPane.showMessageDialog(this, "Bạn không được sử dụng chức năng này");
		}
	}// GEN-LAST:event_btnNhanVienTCActionPerformed

	private void btnDoiMatKhauTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDoiMatKhauTCActionPerformed
		GUI_DoiMatKhau pi = new GUI_DoiMatKhau();
		pi.setVisible(true);
	}// GEN-LAST:event_btnDoiMatKhauTCActionPerformed

	private void btnDangXuatTCActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		dispose();
		GUI_DangNhap n = new GUI_DangNhap();
		n.setVisible(true);

	}// GEN-LAST:event_btnDangXuatTCActionPerformed

	private void btnXoaCN(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		CongNhan_DAO cn = new CongNhan_DAO();
		int row = tblListCongNhan.getSelectedRow();
		System.out.println(row);
		if (row >= 0) {
			int selection = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa công nhân này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (selection == JOptionPane.YES_OPTION) {
				String maCN = (String) tblListCongNhan.getValueAt(row, 0);
				if (cn.delete(maCN)) {
					modelCongNhan.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa Thành Công");
				}
			}
		} else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");

	}

	private void btnXoaNV(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		NhanVien_DAO nv = new NhanVien_DAO();
		int row = tblListNhanVien.getSelectedRow();
		System.out.println(row);
		if (row >= 0) {
			int selection = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (selection == JOptionPane.YES_OPTION) {
				String maCN = (String) tblListNhanVien.getValueAt(row, 0);
				if (nv.delete(maCN)) {
					modelNhanVien.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa Thành Công");
				}
			}
		} else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");

	}

	private void cboThangNhanVienTKActionPerformed(java.awt.event.ActionEvent evt) {
		int index = cboThangNhanVienTK.getSelectedIndex();
		if (index > -1) {
			int thang = Integer.parseInt(cboThangNhanVienTK.getSelectedItem().toString());
			int nam = Integer.parseInt(cboNamNhanVienTK.getSelectedItem().toString());
			docDuLieuTuSQLTableThangNamNhanVienTK(thang, nam);
		}
	}

	private void cboNamNhanVienTKActionPerformed(java.awt.event.ActionEvent evt) {
		int index = cboNamNhanVienTK.getSelectedIndex();
		if (index > -1) {
			int thang = Integer.parseInt(cboThangNhanVienTK.getSelectedItem().toString());
			int nam = Integer.parseInt(cboNamNhanVienTK.getSelectedItem().toString());
			docDuLieuTuSQLTableThangNamNhanVienTK(thang, nam);

		}
	}

	private void btnCapNhatNhanVienTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatSPActionPerformed
		modelNhanVienTK.setRowCount(0);
		docDuLieuTuSQLTableNhanVienTK();

	}

	private void btnNhanVienTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		try {
			Hashtable<String, Object> map = new Hashtable<>();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLuong";
			Connection con = DriverManager.getConnection(url, "sa", "sa1204");
			String rp = "src\\report\\rptNhanVienTK.jrxml";
			map.put("sMaNV", cboThangNhanVienTK.getSelectedItem().toString());
			map.put("sMaNV2", cboNamNhanVienTK.getSelectedItem().toString());
			JasperReport jr = JasperCompileManager.compileReport(rp);
			JasperPrint jp = JasperFillManager.fillReport(jr, map, con);
			JasperExportManager.exportReportToPdfFile(jp, "src\\report\\rptNhanVienTK.pdf");
			JasperViewer.viewReport(jp, false);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cboThangCongNhanTKActionPerformed(java.awt.event.ActionEvent evt) {
		int index = cboThangCongNhanTK.getSelectedIndex();
		if (index > -1) {
			int thang = Integer.parseInt(cboThangCongNhanTK.getSelectedItem().toString());
			int nam = Integer.parseInt(cboNamCongNhanTK.getSelectedItem().toString());
			docDuLieuTuSQLTableThangNamCongNhanTK(thang, nam);
		}
	}

	private void cboNamCongNhanTKActionPerformed(java.awt.event.ActionEvent evt) {
		int index = cboNamCongNhanTK.getSelectedIndex();
		if (index > -1) {
			int thang = Integer.parseInt(cboThangCongNhanTK.getSelectedItem().toString());
			int nam = Integer.parseInt(cboNamCongNhanTK.getSelectedItem().toString());
			docDuLieuTuSQLTableThangNamCongNhanTK(thang, nam);
		}
	}

	private void btnCapNhatCongNhanTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatSPActionPerformed
		modelCongNhanTK.setRowCount(0);
		docDuLieuTuSQLTableCongNhanTK();

	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

		int rowL = tblBangLuongCN.getSelectedRow();
		String maCN_L = tblBangLuongCN.getModel().getValueAt(rowL, 0).toString();
		if (rowL >= 0) {
			DetailsLuong rowLuongA = new DetailsLuong(maCN_L, rowL);
//				DetailsLuong rowLuong = new GUI_CapNhatCongNhan(this.getMaCN(row), row);
			rowLuongA.setVisible(true);
			System.out.println(maCN_L);
		} else
			JOptionPane.showMessageDialog(this, "Sai roi thang ngu");
//	        System.out.println(rowL);

	}

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		// kaikai
		int rowL = tblBangLuongNV.getSelectedRow();
		String maNV_L = tblBangLuongNV.getModel().getValueAt(rowL, 0).toString();
		if (rowL >= 0) {
			DetailNv rowLuongA = new DetailNv(maNV_L, rowL);
//				DetailsLuong rowLuong = new GUI_CapNhatCongNhan(this.getMaCN(row), row);
			rowLuongA.setVisible(true);
			System.out.println(maNV_L);
		} else
			JOptionPane.showMessageDialog(this, "Sai roi thang ngu");
//	        System.out.println(rowL);

	}

	private void btnCongNhanTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		try {
			Hashtable<String, Object> map = new Hashtable<>();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLuong";
			Connection con = DriverManager.getConnection(url, "sa", "sa1204");
			String rp = "src\\report\\rptCongNhanTK.jrxml";
			map.put("sMaCN", cboThangCongNhanTK.getSelectedItem().toString());
			map.put("sMaCN2", cboNamCongNhanTK.getSelectedItem().toString());
			JasperReport jr = JasperCompileManager.compileReport(rp);
			JasperPrint jp = JasperFillManager.fillReport(jr, map, con);
			JasperExportManager.exportReportToPdfFile(jp, "src\\report\\rptCongNhanTK.pdf");
			JasperViewer.viewReport(jp, false);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getTenSanPham() {
		cbboxTenSanPham.removeAll();
		ArrayList<String> dsTenSanPham = new ArrayList<String>();
		ThongKe_DAO thongKe_DAO = new ThongKe_DAO();
		dsTenSanPham = thongKe_DAO.getTenSanPhamTK();
		for (String sanPham : dsTenSanPham) {
			cbboxTenSanPham.addItem(sanPham);
		}
	}

	private void cbboxTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
		int index = cbboxTenSanPham.getSelectedIndex();
		if (index > -1) {
			docDuLieuTuSQLTableSanPhamTK(cbboxTenSanPham.getSelectedItem().toString().trim());
		}
	}

	private void btnCapNhatSanPhamTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCapNhatSPActionPerformed
		modelSanPhamTK.setRowCount(0);
		cbboxTenSanPham.removeAllItems();
		docDuLieuTuSQLTableSP();
		getTenSanPham();
	}

	private void btnSanPhamTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatTCActionPerformed
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ThongKe_DAO thongKe_DAO = new ThongKe_DAO();
		String maSP = thongKe_DAO.getMaSanPham(cbboxTenSanPham.getSelectedItem().toString());
		try {
			Hashtable<String, Object> map = new Hashtable<>();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLuong";
			con = DriverManager.getConnection(url, "sa", "sa1204");
			String rp = "src\\report\\rptSanPhamTK.jrxml";
			map.put("sMaSP", maSP);
			JasperReport jr = JasperCompileManager.compileReport(rp);
			JasperPrint jp = JasperFillManager.fillReport(jr, map, con);
			JasperExportManager.exportReportToPdfFile(jp, "src\\report\\rptSanPhamTK.pdf");
			JasperViewer.viewReport(jp, false);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getMaCN(int row) {
		return tblListCongNhan.getValueAt(row, 0).toString();
	}

	public String getMaNV(int row) {
		return tblListNhanVien.getValueAt(row, 0).toString();
	}

	public void updateCN(int row, String hoTenCongNhan, boolean gioiTinh, String ngaySinh, String ngayBatDauLamViec,
			String CMND, String diaChi, String sDT, String troCap) {
		tblListCongNhan.setValueAt(hoTenCongNhan, row, 1);
		tblListCongNhan.setValueAt(gioiTinh ? "Nam" : "Nữ", row, 2);
		tblListCongNhan.setValueAt(ngaySinh, row, 3);
		tblListCongNhan.setValueAt(ngayBatDauLamViec, row, 4);
		tblListCongNhan.setValueAt(CMND, row, 5);
		tblListCongNhan.setValueAt(diaChi, row, 6);
		tblListCongNhan.setValueAt(sDT, row, 7);
		tblListCongNhan.setValueAt(troCap, row, 8);

	}

	public void stateChanged(ChangeEvent e) {
		int selectedIndex = tbpCN.getSelectedIndex();
		JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCapNhatBangLuong;
	private javax.swing.JButton btnCapNhatBangLuongCN;
	private javax.swing.JButton btnCapNhatCN;
	private javax.swing.JButton btnCapNhatNV;
	private javax.swing.JButton btnCapNhatPC;
	private javax.swing.JButton btnCapNhatSP;
	private javax.swing.JButton btnCapNhatThongKeCN;
	private javax.swing.JButton btnCapNhatThongKeNV;
	private javax.swing.JButton btnCapNhatThongKeSP;
	private javax.swing.JButton btnChamCongNV;
	private javax.swing.JButton btnChamCongSP;
	private javax.swing.JButton btnCongNhan;
	private javax.swing.JButton btnCongNhanTC;
	private javax.swing.JButton btnCongNhanTK;
	private javax.swing.JButton btnCapNhatCongNhanTK;
	private javax.swing.JButton btnDangXuatTC;
	private javax.swing.JButton btnDoiMatKhauTC;
	private javax.swing.JButton btnInBangLuong;
	private javax.swing.JButton btnInBangLuongCN;
	private javax.swing.JButton btnLamMoiCN;
	private javax.swing.JButton btnLamMoiNV;
	private javax.swing.JButton btnLuuSP;
	private javax.swing.JButton btnNhanVien;
	private javax.swing.JButton btnNhanVienTC;
	private javax.swing.JButton btnNhanVienTK;
	private javax.swing.JButton btnCapNhatNhanVienTK;
	private javax.swing.JButton btnPhanCongThuCongSP;
	private javax.swing.JButton btnPhanCongTuDongSP;
	private javax.swing.JButton btnSanPham;
	private javax.swing.JButton btnSanPhamTC;
	private javax.swing.JButton btnSanPhamTK;
	private javax.swing.JButton btnCapNhatSanPhamTK;
	private javax.swing.JButton btnThemCN;
	private javax.swing.JButton btnThemNV;
	private javax.swing.JButton btnThemSP;
	private javax.swing.JButton btnThongKe;
	private javax.swing.JButton btnThongKeTC;
	private javax.swing.JButton btnTimCN;
	private javax.swing.JButton btnTimNV;
	private javax.swing.JButton btnTrangChu;
	private javax.swing.JButton btnXoaCN;
	private javax.swing.JButton btnXoaNV;
	private javax.swing.JComboBox<String> cboNamBangLuong;
	private javax.swing.JComboBox<String> cboNamCN;
	private javax.swing.JComboBox<String> cboThangCongNhanTK;
	private javax.swing.JComboBox<String> cboNamCongNhanTK;
	private javax.swing.JComboBox<String> cboThangNhanVienTK;
	private javax.swing.JComboBox<String> cboNamNhanVienTK;
	private javax.swing.JComboBox<String> cboThangBangLuong;
	private javax.swing.JComboBox<String> cboThangCN;
	private javax.swing.JComboBox<String> cbboxTenSanPham;
	private javax.swing.JCheckBox cbxHoanThanhSP;
	private com.toedter.calendar.JDateChooser dateNgayChamCongCongDoan;
	private com.toedter.calendar.JDateChooser dateNgayChamCongNV;
	private com.toedter.calendar.JDateChooser dateNgayLamViecCN;
	private com.toedter.calendar.JDateChooser dateNgayLamViecNV;
	private com.toedter.calendar.JDateChooser dateNgaySinhCN;
	private com.toedter.calendar.JDateChooser dateNgaySinhNV;
	private javax.swing.JSeparator jSeparator;
	private javax.swing.JSeparator jSeparator5;
	private javax.swing.JSeparator jSeparator6;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JLabel lblChucVuTC;
	private javax.swing.JLabel lblCmndCN;
	private javax.swing.JLabel lblCmndNV;
	private javax.swing.JLabel lblDSCD;
	private javax.swing.JLabel lblDSCongNhanChamCong;
	private javax.swing.JLabel lblDSSP;
	private javax.swing.JLabel lblDanhSachCNCanChamCongSP;
	private javax.swing.JLabel lblDanhSachCNChuaPhanCongSP;
	private javax.swing.JLabel lblDanhSachCNThucHienCongDoanSP;
	private javax.swing.JLabel lblDanhSachChamCongCongDoanSP;
	private javax.swing.JLabel lblDanhSachChamCongSP;
	private javax.swing.JLabel lblDanhSachCongDoanSP;
	private javax.swing.JLabel lblDanhSachSP;
	private javax.swing.JLabel lblThangNhanVienTK;
	private javax.swing.JLabel lblNamNhanVienTK;
	private javax.swing.JLabel lblDiaChiTC;
	private javax.swing.JLabel lblGioiTinhCN;
	private javax.swing.JLabel lblGioiTinhNV;
	private javax.swing.JLabel lblHoTenCN;
	private javax.swing.JLabel lblHoTenNV;
	private javax.swing.JLabel lblImageTC;
	private javax.swing.JLabel lblMaCN;
	private javax.swing.JLabel lblMaNV;
	private javax.swing.JLabel lblMaNhanVienTC;
	private javax.swing.JLabel lblMaSP;
	private javax.swing.JLabel lblNamBangLuong;
	private javax.swing.JLabel lblNamCN;
	private javax.swing.JLabel lblTenSanPhamTK;
	private javax.swing.JLabel lblThangCongNhanTK;
	private javax.swing.JLabel lblNamCongNhanTK;
	private javax.swing.JLabel lblNgayChamCong;
	private javax.swing.JLabel lblNgayChamCongNV;
	private javax.swing.JLabel lblNgayLamVienCN;
	private javax.swing.JLabel lblNgayLamVienNV;
	private javax.swing.JLabel lblNgaySinhCN;
	private javax.swing.JLabel lblNgaySinhNV;
	private javax.swing.JLabel lblSoLuongSP;
	private javax.swing.JLabel lblTenCtyTC;
	private javax.swing.JLabel lblTenNhanVienTC;
	private javax.swing.JLabel lblTenSP;
	private javax.swing.JLabel lblThangBangLuong;
	private javax.swing.JLabel lblThangCN;
	private javax.swing.JLabel lblThongTinNhanVienTC;
	private javax.swing.JLabel lblTieuDeCN;
	private javax.swing.JLabel lblTieuDeNV;
	private javax.swing.JLabel lblTieuDeSP;
	private javax.swing.JLabel lblTieuDeTC;
	private javax.swing.JLabel lblTieuDeTK;
	private javax.swing.JLabel lblTrangThaiSP;
	private javax.swing.JLabel lblTruyCapDenTC;
	private javax.swing.JScrollPane pneChamCongNV;
	private javax.swing.JPanel pnlBangLuongCN;
	private javax.swing.JPanel pnlBangLuongNV;
	private javax.swing.JPanel pnlCards;
	private javax.swing.JPanel pnlChamCongCongDoanSP;
	private javax.swing.JPanel pnlChamCongNV;
	private javax.swing.JPanel pnlCongNhan;
	private javax.swing.JPanel pnlCongNhanTK;
	private javax.swing.JPanel pnlDanhSachCN;
	private javax.swing.JPanel pnlDanhSachNV;
	private javax.swing.JPanel pnlMenu;
	private javax.swing.JPanel pnlNhanVien;
	private javax.swing.JPanel pnlNhanVienTK;
	private javax.swing.JPanel pnlPhanCongCongDoanSP;
	private javax.swing.JPanel pnlSP;
	private javax.swing.JPanel pnlSanPham;
	private javax.swing.JPanel pnlSanPhamTK;
	private javax.swing.JPanel pnlTC;
	private javax.swing.JPanel pnlThongKe;
	private javax.swing.JPanel pnlTieuDeCN;
	private javax.swing.JPanel pnlTieuDeNV;
	private javax.swing.JPanel pnlTieuDeSP;
	private javax.swing.JPanel pnlTieuDeTC;
	private javax.swing.JPanel pnlTieuDeTK;
	private javax.swing.JPanel pnlTimKiemCN;
	private javax.swing.JPanel pnlTimKiemNV;
	private javax.swing.JPanel pnlTrangChu;
	private javax.swing.JRadioButton radioBtnNamCN;
	private javax.swing.JRadioButton radioBtnNamNV;
	private javax.swing.JRadioButton radioBtnNuCN;
	private javax.swing.JRadioButton radioBtnNuNV;
	private javax.swing.JScrollPane speBangLuong;
	private javax.swing.JScrollPane speBangLuongCN;
	private javax.swing.JScrollPane speCongDoanSP;
	private javax.swing.JScrollPane speCongNhanTK;
	private javax.swing.JScrollPane speDanhSachCN;
	private javax.swing.JScrollPane speDanhSachCNCanChamCongSP;
	private javax.swing.JScrollPane speDanhSachCNChuaPhanCongSP;
	private javax.swing.JScrollPane speDanhSachCNThucHienCongDoanSP;
	private javax.swing.JScrollPane speDanhSachChamCongCongDoanSP;
	private javax.swing.JScrollPane speDanhSachChamCongSP;
	private javax.swing.JScrollPane speDanhSachCongDoanSP;
	private javax.swing.JScrollPane speDanhSachNV;
	private javax.swing.JScrollPane speDanhSachSP;
	private javax.swing.JScrollPane speNhanVienTK;
	private javax.swing.JScrollPane speSP;
	private javax.swing.JScrollPane speSanPhamTK;
	private javax.swing.JScrollPane speTimKiemCN;
	private javax.swing.JScrollPane speTimKiemNV;
	private javax.swing.JTable tblBangLuongCN;
	private javax.swing.JTable tblBangLuongNV;
	private javax.swing.JTable tblChamCongNV;
	private javax.swing.JTable tblCongDoanSP;
	private javax.swing.JTable tblCongNhanTK;
	private javax.swing.JTable tblDanhSachCNCanChamCongSP;
	private javax.swing.JTable tblDanhSachCNChuaPhanCongSP;
	private javax.swing.JTable tblDanhSachCNThucHienCongDoanSP;
	private javax.swing.JTable tblDanhSachChamCongCongDoanSP;
	private javax.swing.JTable tblDanhSachChamCongSP;
	private javax.swing.JTable tblDanhSachCongDoanSP;
	private javax.swing.JTable tblDanhSachSP;
	private javax.swing.JTable tblListCongNhan;
	private javax.swing.JTable tblListNhanVien;
	private javax.swing.JTable tblNhanVienTK;
	private javax.swing.JTable tblSP;
	private javax.swing.JTable tblSanPhamTK;
	private javax.swing.JTable tblTimKiemCN;
	private javax.swing.JTable tblTimKiemNV;
	private javax.swing.JTabbedPane tbpCN;
	private javax.swing.JTabbedPane tbpNV;
	private javax.swing.JTabbedPane tbpSP;
	private javax.swing.JTabbedPane tbpTK;
	private javax.swing.JTextField txtChucVuTC;
	private javax.swing.JTextField txtCmndCN;
	private javax.swing.JTextField txtCmndNV;
	private javax.swing.JTextField txtHoTenCN1;
	private javax.swing.JTextField txtHoTenNV;
	private javax.swing.JTextField txtMaCongNhan;
	private javax.swing.JTextField txtMaNhanVien;
	private javax.swing.JTextField txtMaNhanVienTC;
	private javax.swing.JTextField txtMaSP;
	private javax.swing.JTextField txtTenSP;
	private javax.swing.JTextField txtSoLuongSP;
	private javax.swing.JTextField txtTenNhanVienTC;
	// End of variables declaration//GEN-END:variables

	class dialogPhanCongTuDong extends JDialog {

		private static final long serialVersionUID = 1L;

		public dialogPhanCongTuDong(JFrame frame, String title) {
			super(frame, title, true);
			initComponents();
		}

		private javax.swing.JButton btnPhanCong;
		private javax.swing.JButton btnThoat;
		private javax.swing.JLabel lblTieuDe;
		private javax.swing.JPanel pPhanCongTuDong;
		private javax.swing.JTextField txtPhanCongTuDong;

		private void initComponents() {

			pPhanCongTuDong = new javax.swing.JPanel();
			lblTieuDe = new javax.swing.JLabel();
			txtPhanCongTuDong = new javax.swing.JTextField();
			btnPhanCong = new javax.swing.JButton();
			btnThoat = new javax.swing.JButton();

			setLocationByPlatform(true);
			setUndecorated(true);

			pPhanCongTuDong.setBackground(new java.awt.Color(255, 255, 255));

			lblTieuDe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
			lblTieuDe.setText("Chọn số lượng cần phân công");

			txtPhanCongTuDong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
			txtPhanCongTuDong.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					txtPhanCongTuDongActionPerformed(evt);
				}
			});

			btnPhanCong.setBackground(new java.awt.Color(0, 153, 102));
			btnPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
			btnPhanCong.setForeground(new java.awt.Color(255, 255, 255));
			btnPhanCong.setText("Phân Công");
			btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btnPhanCongActionPerformed(evt);
				}

			});

			btnThoat.setBackground(new java.awt.Color(255, 102, 102));
			btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
			btnThoat.setForeground(new java.awt.Color(255, 255, 255));
			btnThoat.setText("Thoát");
			btnThoat.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btnThoatActionPerformed(evt);
				}
			});

			javax.swing.GroupLayout pPhanCongTuDongLayout = new javax.swing.GroupLayout(pPhanCongTuDong);
			pPhanCongTuDong.setLayout(pPhanCongTuDongLayout);
			pPhanCongTuDongLayout.setHorizontalGroup(pPhanCongTuDongLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(pPhanCongTuDongLayout.createSequentialGroup().addGap(53, 53, 53)
							.addGroup(pPhanCongTuDongLayout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
									.addGroup(pPhanCongTuDongLayout.createSequentialGroup().addComponent(btnPhanCong)
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
													javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnThoat))
									.addComponent(txtPhanCongTuDong, javax.swing.GroupLayout.PREFERRED_SIZE, 209,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTieuDe))
							.addContainerGap(70, Short.MAX_VALUE)));
			pPhanCongTuDongLayout.setVerticalGroup(
					pPhanCongTuDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(pPhanCongTuDongLayout.createSequentialGroup().addGap(31, 31, 31)
									.addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(txtPhanCongTuDong, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(18, 18, 18)
									.addGroup(pPhanCongTuDongLayout
											.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
											.addComponent(btnPhanCong)
											.addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
													javax.swing.GroupLayout.PREFERRED_SIZE))
									.addContainerGap(45, Short.MAX_VALUE)));

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(pPhanCongTuDong, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
			layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
					pPhanCongTuDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE));
			pack();
			setLocationRelativeTo(null);

		}

		private void txtPhanCongTuDongActionPerformed(java.awt.event.ActionEvent evt) {
			// TODO add your handling code here:
		}

		private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {
			dispose();
		}

		private void btnPhanCongActionPerformed(ActionEvent evt) {
			ChamCongCongDoan_DAO chamCongCD = new ChamCongCongDoan_DAO();
			Date ngayCham = dateNgayChamCongCongDoan.getDate();
			java.sql.Date sqlDate = null;
			sqlDate = new java.sql.Date(ngayCham.getTime());
			congNhan = new CongNhan_DAO();
			Random rd = new Random();
			int viTri;
			System.out.println("size: " + size);
			String maChamCongCD = null;
			int soLuongChamCongCD = chamCongCD.getSoLuongChamCongCongDoan();
			int row = tblDanhSachCongDoanSP.getSelectedRow();
			if (row != -1) {
				Pattern pattern = Pattern.compile("\\d*");
				Matcher matcher = pattern.matcher(txtPhanCongTuDong.getText());
				if (matcher.matches()) {
					int n = Integer.parseInt(txtPhanCongTuDong.getText());
					if (n > size)
						JOptionPane.showMessageDialog(this, "Công nhân trong danh sách không đủ");
					else {

						String maCD = modelListCDSP.getValueAt(row, 0).toString();
						int soLuongTongCuaCN = chamCongCD.demSumSoLuongCCCD(maCD);
						int soLuongCD = congDoan.getSoLuongCongDoan(maCD);
						if (soLuongCD <= soLuongTongCuaCN)
							JOptionPane.showMessageDialog(this, "Công đoạn này đã hoàn thành");
						else {
							for (int i = 0; i < n; i++) {
								if (soLuongChamCongCD < 10)
									maChamCongCD = "CCCD00" + soLuongChamCongCD;
								if (soLuongChamCongCD >= 10 && soLuongChamCongCD < 100)
									maChamCongCD = "CCCD0" + soLuongChamCongCD;
								if (soLuongChamCongCD >= 100)
									maChamCongCD = "CCCD" + soLuongChamCongCD;
								soLuongChamCongCD++;
								viTri = rd.nextInt(size);
								String maCN = modelListCNChuaPhanCong.getValueAt(viTri, 0).toString();

								String tenCN = modelListCNChuaPhanCong.getValueAt(viTri, 1).toString();

								boolean gioiTinh;
								if (modelListCNChuaPhanCong.getValueAt(viTri, 2).toString() == "Nữ")
									gioiTinh = true;
								else
									gioiTinh = false;

								tblDanhSachCNThucHienCongDoanSP.setRowHeight(35);

								Object[] rowData = { maCN, tenCN, gioiTinh ? "Nữ" : "Nam" };
								congNhan.setTrangThaiCongNhan(true, maCN);
								modelListCNChuaPhanCong.removeRow(viTri);
								modelListCNThucHienCDSP.addRow(rowData);
								size--;
								ChamCongCongDoan cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD),
										new CongNhan(maCN), sqlDate, false, false, "Sáng", 0);
								chamCongCD.insertChamCongCD(cccd);

							}
						}

					}
				} else
					JOptionPane.showMessageDialog(this, "Bạn phải nhập số dương và không nhập ký tự");

			} else
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn công đoạn cần phân công");

		}
	}

	class DanhSachCongDoanCCCDMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (tblDanhSachChamCongCongDoanSP.getSelectedRow() != -1) {
				int row = tblDanhSachChamCongCongDoanSP.getSelectedRow();
				String maCD = modelListCCCDSP.getValueAt(row, 0).toString();
				docDuLieuTuSQLTableDanhSachCNCanChamCongCCCD(maCD);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class DanhSachSanPhamCCCDMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (tblDanhSachChamCongSP.getSelectedRow() != -1) {
				int row = tblDanhSachChamCongSP.getSelectedRow();
				String maSP = modelListChamCongSP.getValueAt(row, 0).toString();
				docDuLieuSQLTableDanhSachCongDoanCCCD(maSP);

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class DanhSachSPMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (tblDanhSachSP.getSelectedRow() != -1) {
				int rowDSSP = tblDanhSachSP.getSelectedRow();
				String maSP = modelListSP.getValueAt(rowDSSP, 0).toString();
				docDuLieuTuSQLTableDanhSachCongDoanSP(maSP);

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class DanhSachCDSPMouseList implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (tblDanhSachCongDoanSP.getSelectedRow() != -1) {
				int rowDSCDSP = tblDanhSachCongDoanSP.getSelectedRow();
				String maCD = modelListCDSP.getValueAt(rowDSCDSP, 0).toString();
				docDuLieuTuSQLTableDanhSachCNThucHienCDSP(maCD);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (tblSP.getSelectedRow() != -1) {
			int row = tblSP.getSelectedRow();
			txtMaSP.setText(modelSanPham.getValueAt(row, 0).toString());
			docDuLieuTuSQLTableCongDoanSP(txtMaSP.getText().toString());
			txtTenSP.setText(modelSanPham.getValueAt(row, 1).toString());
			txtSoLuongSP.setText(modelSanPham.getValueAt(row, 4).toString());
			if (modelSanPham.getValueAt(row, 5).toString() == "Hoàn thành")
				cbxHoanThanhSP.setSelected(true);
			else
				cbxHoanThanhSP.setSelected(false);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
