/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 *
 * @author CHAU
 */
public class GUI_ThemNhanVien extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI_ThemNhanVien() {
		initComponents();
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		new com.toedter.calendar.JCalendar();
		new javax.swing.JButton();
		pThemNhanVien = new javax.swing.JPanel();
		pTieuDe = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		lblGioiTinh = new javax.swing.JLabel();
		txtDiaChi = new javax.swing.JTextField();
		lblHoTenNV = new javax.swing.JLabel();
		lblMaNhanVien = new javax.swing.JLabel();
		lblNgaySinh = new javax.swing.JLabel();
		lblNgayBatDauLamViec = new javax.swing.JLabel();
		lblCMND = new javax.swing.JLabel();
		lblDiaChi = new javax.swing.JLabel();
		lblSoDienThoai = new javax.swing.JLabel();
		lblTroCap = new javax.swing.JLabel();
		lblHeSoLuong = new javax.swing.JLabel();
		txtHoTenNV = new javax.swing.JTextField();
		txtCMND = new javax.swing.JTextField();
		rbtnNam = new javax.swing.JRadioButton();
		rbtnNu = new javax.swing.JRadioButton();
		dateNgaySinh = new com.toedter.calendar.JDateChooser();
		dateNgayBatDauLamViec = new com.toedter.calendar.JDateChooser();
		txtMaNhanVien = new javax.swing.JTextField();
		txtSoDienThoai = new javax.swing.JTextField();
		cbboxTroCap = new javax.swing.JComboBox<>();
		cbboxHeSoLuong = new javax.swing.JComboBox<>();
		btnThoat = new javax.swing.JButton();
		btnXoaRong = new javax.swing.JButton();
		btnThem = new javax.swing.JButton();
		setDateMacDinh();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		pThemNhanVien.setBackground(new java.awt.Color(255, 255, 255));

		pTieuDe.setBackground(new java.awt.Color(43, 124, 210));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("THÊM NHÂN VIÊN");

		javax.swing.GroupLayout pTieuDeLayout = new javax.swing.GroupLayout(pTieuDe);
		pTieuDe.setLayout(pTieuDeLayout);
		pTieuDeLayout.setHorizontalGroup(pTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pTieuDeLayout.createSequentialGroup().addGap(271, 271, 271).addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pTieuDeLayout.setVerticalGroup(pTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTieuDeLayout.createSequentialGroup()
						.addContainerGap(16, Short.MAX_VALUE).addComponent(jLabel1).addContainerGap()));

		lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblGioiTinh.setText("Giới Tính");

		lblHoTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblHoTenNV.setText("Họ Tên Nhân Viên");

		lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblMaNhanVien.setText("Mã Nhân Viên");

		lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblNgaySinh.setText("Ngày Sinh");

		lblNgayBatDauLamViec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblNgayBatDauLamViec.setText("Ngày Bắt Đầu Làm Việc");

		lblCMND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblCMND.setText("CMND");

		lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblDiaChi.setText("Địa Chỉ");

		lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblSoDienThoai.setText("Số Điện Thoại");

		lblTroCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblTroCap.setText("Trợ Cấp");

		lblHeSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblHeSoLuong.setText("Hệ Số Lương");

		rbtnNam.setText("Nam");

		rbtnNu.setText("Nữ");

		ButtonGroup groupCN = new ButtonGroup();
		groupCN.add(rbtnNam);
		groupCN.add(rbtnNu);
		rbtnNam.setSelected(true);
		
		dateNgaySinh.setDateFormatString("dd-MM-yyyy");

		dateNgayBatDauLamViec.setDateFormatString("dd-MM-yyyy");

		cbboxTroCap.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Không", "Hộ Nghèo", "Phụ Sản", "Gia Đình Khó Khăn" }));

		cbboxHeSoLuong.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "1.86", "2.1", "2.34"}));

		btnThoat.setBackground(new java.awt.Color(255, 0, 0));
		btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/x-button.png"))); // NOI18N
		btnThoat.setText("Thoát");
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});

		btnXoaRong.setBackground(new java.awt.Color(255, 255, 0));
		btnXoaRong.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
		btnXoaRong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/trash.png"))); // NOI18N
		btnXoaRong.setText("Xoá Rỗng");
		btnXoaRong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaRongActionPerformed(evt);
			}
		});

		btnThem.setBackground(new java.awt.Color(51, 255, 51));
		btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
		btnThem.setText("Thêm");
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}

		});
		javax.swing.GroupLayout pThemNhanVienLayout = new javax.swing.GroupLayout(pThemNhanVien);
		pThemNhanVien.setLayout(pThemNhanVienLayout);
		pThemNhanVienLayout.setHorizontalGroup(pThemNhanVienLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(pThemNhanVienLayout.createSequentialGroup().addGroup(pThemNhanVienLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pThemNhanVienLayout.createSequentialGroup().addGap(29, 29, 29)
								.addGroup(pThemNhanVienLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThemNhanVienLayout
												.createSequentialGroup()
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lblNgaySinh).addComponent(lblCMND))
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(pThemNhanVienLayout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(dateNgaySinh,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(pThemNhanVienLayout.createSequentialGroup()
																.addGap(49, 49, 49)
																.addComponent(txtCMND,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThemNhanVienLayout
												.createSequentialGroup().addComponent(lblMaNhanVien)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE,
														165, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pThemNhanVienLayout.createSequentialGroup()
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(lblSoDienThoai,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblHeSoLuong,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(txtSoDienThoai,
																javax.swing.GroupLayout.DEFAULT_SIZE, 165,
																Short.MAX_VALUE)
														.addComponent(cbboxHeSoLuong, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))))
								.addGap(58, 58, 58)
								.addGroup(pThemNhanVienLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(pThemNhanVienLayout.createSequentialGroup()
												.addComponent(lblHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThemNhanVienLayout
												.createSequentialGroup().addComponent(lblDiaChi)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pThemNhanVienLayout.createSequentialGroup()
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(pThemNhanVienLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblNgayBatDauLamViec,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblGioiTinh))
														.addComponent(lblTroCap))
												.addGap(18, 18, 18)
												.addGroup(pThemNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(dateNgayBatDauLamViec,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(cbboxTroCap, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(pThemNhanVienLayout.createSequentialGroup()
																.addComponent(rbtnNam,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 57,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		36, Short.MAX_VALUE)
																.addComponent(rbtnNu,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 59,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(13, 13, 13))))))
						.addGroup(pThemNhanVienLayout.createSequentialGroup().addGap(151, 151, 151)
								.addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(47, 47, 47).addComponent(btnXoaRong).addGap(47, 47, 47).addComponent(btnThem,
										javax.swing.GroupLayout.PREFERRED_SIZE, 107,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(22, Short.MAX_VALUE)));
		pThemNhanVienLayout.setVerticalGroup(pThemNhanVienLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pThemNhanVienLayout.createSequentialGroup()
						.addComponent(pTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(47, 47, 47)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHoTenNV)
								.addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblCMND)
								.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rbtnNam).addComponent(rbtnNu))
						.addGap(18, 18, 18)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dateNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pThemNhanVienLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(
										dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(28, 28, 28)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblSoDienThoai)
								.addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTroCap)
								.addComponent(cbboxTroCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(38, 38, 38)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cbboxHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeSoLuong))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
						.addGroup(pThemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThoat).addComponent(btnXoaRong).addComponent(btnThem))
						.addGap(42, 42, 42)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pThemNhanVien,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pThemNhanVien,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
		phatSinhMaNVTuDong();
	}// </editor-fold>//GEN-END:initComponents

	

	private NhanVien getNVForAdd() {
		String maNV = txtMaNhanVien.getText();
		String tenNV = txtHoTenNV.getText();
		boolean gioiTinh = rbtnNu.isSelected();
		Date sqlDate = dateNgaySinh.getDate();
		java.sql.Date ngaySinh = null;
		ngaySinh = new java.sql.Date(sqlDate.getTime());
		Date sqlDate1 = dateNgayBatDauLamViec.getDate();
		java.sql.Date ngayBatDauLamViec = null;
		ngayBatDauLamViec = new java.sql.Date(sqlDate1.getTime());
		String CMND = txtCMND.getText();
		String diaChi = txtDiaChi.getText();
		String sDT = txtSoDienThoai.getText();
		String troCap = cbboxTroCap.getSelectedItem().toString();
		String tenDangNhap = "QLNS";
		Float heSoLuong = Float.parseFloat(cbboxHeSoLuong.getSelectedItem().toString());
		return new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, troCap, sDT, heSoLuong,
				new TaiKhoan(tenDangNhap));
	}

	private boolean validateNV() {
		String regexTen = "^\\D+";

		if (!txtHoTenNV.getText().matches(regexTen)) {

			JOptionPane.showMessageDialog(this, "Tên không được chứ số và kí tự đặc biệt");
			txtHoTenNV.requestFocus();
			return false;
		}
		if (!txtDiaChi.getText().matches(regexTen)) {

			JOptionPane.showMessageDialog(this, "Địa chỉ không được chứ số và kí tự đặc biệt");
			txtDiaChi.requestFocus();
			return false;
		}
		String regexSDT = "(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})";

		if (!txtSoDienThoai.getText().matches(regexSDT)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 chữ số, bắt đầu bằng số 0");
			txtSoDienThoai.requestFocus();
			return false;
		}
		LocalDate today = LocalDate.now();
		LocalDate birthday = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period p = Period.between(birthday, today);
		if (p.getYears() < 18) {

			JOptionPane.showMessageDialog(this, " Nhân viên phải lớn hơn 18 tuổi");
			dateNgayBatDauLamViec.requestFocus();
			return false;
		}
		String regex = "^\\d{9}$";
		if (!txtCMND.getText().matches(regex)) {
			JOptionPane.showMessageDialog(this, "Số chứng minh thư phải gồm 9 kí tự số");
			txtCMND.requestFocus();
			return false;
		}
		return true;

	}

	private void ThemCongNhan() {

		if (validateNV()) {
			NhanVien cn = this.getNVForAdd();
			NhanVien_DAO nv_DAO = new NhanVien_DAO();
			nv_DAO.create(cn);
			JOptionPane.showMessageDialog(this, "Thêm Thành Công");
			super.dispose();

		}
	}

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThoatActionPerformed
		dispose();
	}// GEN-LAST:event_btnThoatActionPerformed

	private void btnXoaRongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaRongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnXoaRongActionPerformed

	private void btnThemActionPerformed(ActionEvent evt) {
		ThemCongNhan();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI_ThemNhanVien().setVisible(true);
			}
		});
	}

	private void setDateMacDinh() {
		dateNgaySinh = new JDateChooser(new Date());
		dateNgayBatDauLamViec = new JDateChooser(new Date());
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnThoat;
	private javax.swing.JButton btnXoaRong;
	private javax.swing.JComboBox<String> cbboxHeSoLuong;
	private javax.swing.JComboBox<String> cbboxTroCap;
	private com.toedter.calendar.JDateChooser dateNgayBatDauLamViec;
	private com.toedter.calendar.JDateChooser dateNgaySinh;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel lblCMND;
	private javax.swing.JLabel lblDiaChi;
	private javax.swing.JLabel lblGioiTinh;
	private javax.swing.JLabel lblHeSoLuong;
	private javax.swing.JLabel lblHoTenNV;
	private javax.swing.JLabel lblMaNhanVien;
	private javax.swing.JLabel lblNgayBatDauLamViec;
	private javax.swing.JLabel lblNgaySinh;
	private javax.swing.JLabel lblSoDienThoai;
	private javax.swing.JLabel lblTroCap;
	private javax.swing.JPanel pThemNhanVien;
	private javax.swing.JPanel pTieuDe;
	private javax.swing.JRadioButton rbtnNam;
	private javax.swing.JRadioButton rbtnNu;
	private javax.swing.JTextField txtCMND;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtHoTenNV;
	private javax.swing.JTextField txtMaNhanVien;
	private javax.swing.JTextField txtSoDienThoai;
	// End of variables declaration//GEN-END:variables
	
	private void phatSinhMaNVTuDong() {
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setEnabled(false);
		NhanVien_DAO nv = new NhanVien_DAO();
		String sp1 = "NV00" + (nv.getSoLuongNhanVien() + 1);
		String sp2 = "NV0" + (nv.getSoLuongNhanVien() + 1);
		for (NhanVien nhanVien : nv.getAllNhanVien()) {
			if (nv.getSoLuongNhanVien() <= 10 && !nhanVien.getMaNhanVien().equalsIgnoreCase(sp1)) {
				txtMaNhanVien.setText(sp1);
			} else
				txtMaNhanVien.setText("NV00" + (nv.getSoLuongNhanVien() + 2));
			if (nv.getSoLuongNhanVien() >= 10 && !nhanVien.getMaNhanVien().equalsIgnoreCase(sp2)) {
				txtMaNhanVien.setText(sp2);
			} else
				txtMaNhanVien.setText("NV0" + (nv.getSoLuongNhanVien() + 2));
		}
	}
}
