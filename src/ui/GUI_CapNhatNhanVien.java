/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.Toolkit;
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
public class GUI_CapNhatNhanVien extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form ThemNhanVien
	 */
	public GUI_CapNhatNhanVien(String maNV) {
		this.maNV = maNV;
		initComponents();
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		new com.toedter.calendar.JCalendar();
		pCapNhatNhanVien = new javax.swing.JPanel();
		pTieuDe = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		lblGioiTinh = new javax.swing.JLabel();
		txtDiaChi = new javax.swing.JTextField();
		lblHoTenNV = new javax.swing.JLabel();
		lblMaNV = new javax.swing.JLabel();
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
		txtMaNV = new javax.swing.JTextField();
		txtSoDienThoai = new javax.swing.JTextField();
		cbboxTroCap = new javax.swing.JComboBox<>();
		cbboxHeSoLuong = new javax.swing.JComboBox<>();
		btnThoat = new javax.swing.JButton();
		btnCapNhat = new javax.swing.JButton();
		setDateMacDinh();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		pCapNhatNhanVien.setBackground(new java.awt.Color(255, 255, 255));

		pTieuDe.setBackground(new java.awt.Color(43, 124, 210));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("CẬP NHẬT NHÂN VIÊN");

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

		lblMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblMaNV.setText("Mã Nhân Viên");

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

		dateNgaySinh.setDateFormatString("dd-MM-yyyy");

		dateNgayBatDauLamViec.setDateFormatString("dd-MM-yyyy");

		cbboxTroCap.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		cbboxHeSoLuong.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		btnThoat.setBackground(new java.awt.Color(255, 0, 0));
		btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/x-button.png"))); // NOI18N
		btnThoat.setText("Thoát");
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});

		btnCapNhat.setBackground(new java.awt.Color(0, 204, 255));
		btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/update.png"))); // NOI18N
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapnhatActionPerformed(evt);
			}

		});

		javax.swing.GroupLayout pCapNhatNhanVienLayout = new javax.swing.GroupLayout(pCapNhatNhanVien);
		pCapNhatNhanVien.setLayout(pCapNhatNhanVienLayout);
		pCapNhatNhanVienLayout.setHorizontalGroup(pCapNhatNhanVienLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(pCapNhatNhanVienLayout.createSequentialGroup().addGroup(pCapNhatNhanVienLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(pCapNhatNhanVienLayout.createSequentialGroup().addGap(29, 29, 29)
								.addGroup(pCapNhatNhanVienLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCapNhatNhanVienLayout
												.createSequentialGroup()
												.addGroup(pCapNhatNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lblNgaySinh).addComponent(lblCMND))
												.addGroup(pCapNhatNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(dateNgaySinh,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
																.addGap(49, 49, 49)
																.addComponent(txtCMND,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCapNhatNhanVienLayout
												.createSequentialGroup().addComponent(lblMaNV)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
												.addGroup(pCapNhatNhanVienLayout
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
												.addGroup(pCapNhatNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(txtSoDienThoai,
																javax.swing.GroupLayout.DEFAULT_SIZE, 165,
																Short.MAX_VALUE)
														.addComponent(cbboxHeSoLuong, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)))))
						.addGroup(pCapNhatNhanVienLayout.createSequentialGroup().addGap(216, 216, 216).addComponent(
								btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(34, 34, 34)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pCapNhatNhanVienLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
												.addComponent(lblHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCapNhatNhanVienLayout
												.createSequentialGroup().addComponent(lblDiaChi)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
												.addGroup(pCapNhatNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(pCapNhatNhanVienLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblNgayBatDauLamViec,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblGioiTinh))
														.addComponent(lblTroCap))
												.addGap(18, 18, 18)
												.addGroup(pCapNhatNhanVienLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(dateNgayBatDauLamViec,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(cbboxTroCap, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
																.addComponent(rbtnNam,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 57,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		36, Short.MAX_VALUE)
																.addComponent(rbtnNu,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 59,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(13, 13, 13)))))
								.addComponent(btnCapNhat))
						.addContainerGap(22, Short.MAX_VALUE)));
		pCapNhatNhanVienLayout.setVerticalGroup(pCapNhatNhanVienLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pCapNhatNhanVienLayout.createSequentialGroup()
						.addComponent(pTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(47, 47, 47)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHoTenNV)
								.addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblCMND)
								.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rbtnNam).addComponent(rbtnNu))
						.addGap(18, 18, 18)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dateNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pCapNhatNhanVienLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(
										dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(28, 28, 28)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblSoDienThoai)
								.addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTroCap)
								.addComponent(cbboxTroCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(38, 38, 38)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cbboxHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeSoLuong))
						.addGap(52, 52, 52)
						.addGroup(pCapNhatNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThoat).addComponent(btnCapNhat))
						.addContainerGap(37, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pCapNhatNhanVien,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pCapNhatNhanVien,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		
		cbboxTroCap.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Không", "Hộ Nghèo", "Phụ sản", "Gia đình khó khăn" }));

		cbboxHeSoLuong.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "1.86", "2.1", "2.34"}));
		
		ButtonGroup groupCN = new ButtonGroup();
		groupCN.add(rbtnNam);
		groupCN.add(rbtnNu);
		rbtnNam.setSelected(true);
		pack();
		setLocationRelativeTo(null);
		txtMaNV.setText(maNV);
		txtMaNV.setEnabled(false);
		txtMaNV.setEditable(false);
		setField();
	}// </editor-fold>//GEN-END:initComponents

	private void setDateMacDinh() {
		dateNgaySinh = new JDateChooser(new Date());
		dateNgayBatDauLamViec = new JDateChooser(new Date());
	}

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThoatActionPerformed
		close();

	}// GEN-LAST:event_btnThoatActionPerformed

	private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {
		NhanVien_DAO nv = new NhanVien_DAO();
		if (validateCN()) {
			if (nv.update(getNVForEdit())) {
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				super.dispose();
			}
		}
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
			java.util.logging.Logger.getLogger(GUI_CapNhatNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI_CapNhatNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI_CapNhatNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI_CapNhatNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCapNhat;
	private javax.swing.JButton btnThoat;
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
	private javax.swing.JLabel lblMaNV;
	private javax.swing.JLabel lblNgayBatDauLamViec;
	private javax.swing.JLabel lblNgaySinh;
	private javax.swing.JLabel lblSoDienThoai;
	private javax.swing.JLabel lblTroCap;
	private javax.swing.JPanel pCapNhatNhanVien;
	private javax.swing.JPanel pTieuDe;
	private javax.swing.JRadioButton rbtnNam;
	private javax.swing.JRadioButton rbtnNu;
	private javax.swing.JTextField txtCMND;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtHoTenNV;
	private javax.swing.JTextField txtMaNV;
	private javax.swing.JTextField txtSoDienThoai;
	private String maNV;
	// End of variables declaration//GEN-END:variables

	private NhanVien getNVForEdit() {
		String maNV = txtMaNV.getText();
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

	private boolean validateCN() {
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
	
	private void setField() {
		NhanVien_DAO nv_DAO = new NhanVien_DAO();
		NhanVien nv = nv_DAO.getNhanVien(maNV);
		txtHoTenNV.setText(nv.getHoTenNhanVien());
		txtCMND.setText(nv.getCMND());
		txtSoDienThoai.setText(nv.getSoDienThoai());
		txtDiaChi.setText(nv.getDiaChi());
		cbboxTroCap.setSelectedItem(nv.getTroCap());
		cbboxHeSoLuong.setSelectedItem(nv.getHeSoLuong());
		dateNgayBatDauLamViec.setDate(nv.getNgayBatDauLamViec());
		dateNgaySinh.setDate(nv.getNgaySinh());
		if(nv.isGioiTinh() == false) {
			rbtnNam.setSelected(true);
		}
		else 
			rbtnNu.setSelected(true);
	}

}
