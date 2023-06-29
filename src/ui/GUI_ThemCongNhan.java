/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.Toolkit;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import dao.CongNhan_DAO;
import entity.CongNhan;
import entity.TaiKhoan;

/**
 *
 * @author CHAU
 */
public class GUI_ThemCongNhan extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form ThemNhanVien
	 */
	public GUI_ThemCongNhan() {
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
		pThemCongNhan = new javax.swing.JPanel();
		pTieuDe = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		lblGioiTinh = new javax.swing.JLabel();
		txtDiaChi = new javax.swing.JTextField();
		lblHoTenCN = new javax.swing.JLabel();
		lblMaCongNhan = new javax.swing.JLabel();
		lblNgaySinh = new javax.swing.JLabel();
		lblNgayBatDauLamViec = new javax.swing.JLabel();
		lblCMND = new javax.swing.JLabel();
		lblDiaChi = new javax.swing.JLabel();
		lblSoDienThoai = new javax.swing.JLabel();
		lblTroCap = new javax.swing.JLabel();
		txtHoTenCN = new javax.swing.JTextField();
		txtCMND = new javax.swing.JTextField();
		rbtnNam = new javax.swing.JRadioButton();
		rbtnNu = new javax.swing.JRadioButton();
		dateNgaySinh = new com.toedter.calendar.JDateChooser();
		dateNgayBatDauLamViec = new com.toedter.calendar.JDateChooser();
		txtMaCongNhan = new javax.swing.JTextField();
		txtSoDienThoai = new javax.swing.JTextField();
		cbboxTroCap = new javax.swing.JComboBox<>();
		btnXoaRong = new javax.swing.JButton();
		btnThoat = new javax.swing.JButton();
		btnThem = new javax.swing.JButton();
		setDateMacDinh();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		pThemCongNhan.setBackground(new java.awt.Color(255, 255, 255));

		pTieuDe.setBackground(new java.awt.Color(43, 124, 210));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("THÊM CÔNG NHÂN");

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

		lblHoTenCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblHoTenCN.setText("Họ Tên Công Nhân");

		lblMaCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblMaCongNhan.setText("Mã Công Nhân");

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

		rbtnNam.setText("Nam");

		rbtnNu.setText("Nữ");

		ButtonGroup groupCN = new ButtonGroup();
		groupCN.add(rbtnNam);
		groupCN.add(rbtnNu);
		rbtnNam.setSelected(true);

		dateNgaySinh.setDateFormatString("dd-MM-yyyy");

		dateNgayBatDauLamViec.setDateFormatString("dd-MM-yyyy");

		cbboxTroCap.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Không", "Hộ Nghèo", "Gia Đình Khó Khăn", "Phụ Sản", }));

		btnXoaRong.setBackground(new java.awt.Color(255, 255, 0));
		btnXoaRong.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
		btnXoaRong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/trash.png"))); // NOI18N
		btnXoaRong.setText("Xoá Rỗng");
		btnXoaRong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaRongActionPerformed(evt);
			}
		});

		btnThoat.setBackground(new java.awt.Color(255, 0, 0));
		btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/x-button.png"))); // NOI18N
		btnThoat.setText("Thoát");
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
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

		javax.swing.GroupLayout pThemCongNhanLayout = new javax.swing.GroupLayout(pThemCongNhan);
		pThemCongNhan.setLayout(pThemCongNhanLayout);
		pThemCongNhanLayout.setHorizontalGroup(pThemCongNhanLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(pThemCongNhanLayout.createSequentialGroup().addGap(29, 29, 29).addGroup(pThemCongNhanLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(pThemCongNhanLayout.createSequentialGroup().addGroup(pThemCongNhanLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThemCongNhanLayout
										.createSequentialGroup()
										.addGroup(pThemCongNhanLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblNgaySinh).addComponent(lblCMND))
										.addGroup(pThemCongNhanLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(pThemCongNhanLayout.createSequentialGroup()
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(dateNgaySinh,
																javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(pThemCongNhanLayout.createSequentialGroup().addGap(49, 49, 49)
														.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
																165, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(0, 0, Short.MAX_VALUE))))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										pThemCongNhanLayout.createSequentialGroup().addComponent(lblMaCongNhan)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE,
														165, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pThemCongNhanLayout.createSequentialGroup().addComponent(lblSoDienThoai)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(58, 58, 58)
								.addGroup(pThemCongNhanLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(pThemCongNhanLayout.createSequentialGroup()
												.addComponent(lblHoTenCN, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtHoTenCN, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pThemCongNhanLayout.createSequentialGroup()
												.addGroup(pThemCongNhanLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(pThemCongNhanLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblNgayBatDauLamViec,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblGioiTinh))
														.addComponent(lblTroCap))
												.addGap(18, 18, 18)
												.addGroup(pThemCongNhanLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(dateNgayBatDauLamViec,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(cbboxTroCap, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(pThemCongNhanLayout.createSequentialGroup()
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
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pThemCongNhanLayout.createSequentialGroup().addComponent(lblDiaChi)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(pThemCongNhanLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(pThemCongNhanLayout.createSequentialGroup()
														.addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE,
																106, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(55, 55, 55).addComponent(btnXoaRong).addGap(54, 54, 54)
														.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE,
																107, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 553,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(22, Short.MAX_VALUE)));
		pThemCongNhanLayout.setVerticalGroup(pThemCongNhanLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pThemCongNhanLayout.createSequentialGroup()
						.addComponent(pTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(47, 47, 47)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHoTenCN)
								.addComponent(txtHoTenCN, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblCMND)
								.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rbtnNam).addComponent(rbtnNu))
						.addGap(18, 18, 18)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dateNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pThemCongNhanLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(
										dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNgayBatDauLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(28, 28, 28)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblSoDienThoai)
								.addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTroCap)
								.addComponent(cbboxTroCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(33, 33, 33)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblDiaChi).addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(42, 42, 42)
						.addGroup(pThemCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnXoaRong).addComponent(btnThoat).addComponent(btnThem))
						.addContainerGap(30, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pThemCongNhan,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pThemCongNhan,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
		phatSinhMaCNTuDong();


	}// </editor-fold>//GEN-END:initComponents

	private void btnXoaRongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaRongActionPerformed
		// TODO add your handling code here:

		txtCMND.setText("");
		txtDiaChi.setText("");
		txtHoTenCN.setText("");
		txtSoDienThoai.setText("");
		cbboxTroCap.setSelectedIndex(0);
	}// GEN-LAST:event_btnXoaRongActionPerformed

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThoatActionPerformed
		dispose();
	}// GEN-LAST:event_btnThoatActionPerformed

	private void btnThemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
			java.util.logging.Logger.getLogger(GUI_ThemCongNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemCongNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemCongNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI_ThemCongNhan.class.getName()).log(java.util.logging.Level.SEVERE,
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
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI_ThemCongNhan().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnThoat;
	private javax.swing.JButton btnXoaRong;
	private javax.swing.JComboBox<String> cbboxTroCap;
	private com.toedter.calendar.JDateChooser dateNgayBatDauLamViec;
	private com.toedter.calendar.JDateChooser dateNgaySinh;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel lblCMND;
	private javax.swing.JLabel lblDiaChi;
	private javax.swing.JLabel lblGioiTinh;
	private javax.swing.JLabel lblHoTenCN;
	private javax.swing.JLabel lblMaCongNhan;
	private javax.swing.JLabel lblNgayBatDauLamViec;
	private javax.swing.JLabel lblNgaySinh;
	private javax.swing.JLabel lblSoDienThoai;
	private javax.swing.JLabel lblTroCap;
	private javax.swing.JPanel pThemCongNhan;
	private javax.swing.JPanel pTieuDe;
	private javax.swing.JRadioButton rbtnNam;
	private javax.swing.JRadioButton rbtnNu;
	private javax.swing.JTextField txtCMND;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtHoTenCN;
	private javax.swing.JTextField txtMaCongNhan;
	private javax.swing.JTextField txtSoDienThoai;
	// End of variables declaration//GEN-END:variables

	private void phatSinhMaCNTuDong() {
		txtMaCongNhan.setEditable(false);
		txtMaCongNhan.setEnabled(false);
		CongNhan_DAO cn = new CongNhan_DAO();
		String sp1 = "CN00" + (cn.getSoLuongCongNhan() + 1);
		String sp2 = "CN0" + (cn.getSoLuongCongNhan() + 1);
		for (CongNhan congNhan : cn.getAllCongNhan()) {
			if (cn.getSoLuongCongNhan() <= 10 && !congNhan.getMaCongNhan().equalsIgnoreCase(sp1)) {
				txtMaCongNhan.setText(sp1);
			} else
				txtMaCongNhan.setText("CN00" + (cn.getSoLuongCongNhan() + 2));
			if (cn.getSoLuongCongNhan() >= 10 && !congNhan.getMaCongNhan().equalsIgnoreCase(sp2)) {
				txtMaCongNhan.setText(sp2);
			} else
				txtMaCongNhan.setText("CN0" + (cn.getSoLuongCongNhan() + 2));
		}
	}

	private void setDateMacDinh() {
		dateNgaySinh = new JDateChooser(new Date());
		dateNgayBatDauLamViec = new JDateChooser(new Date());
	}

	private CongNhan getCNForAdd() {
		String maCN = txtMaCongNhan.getText();
		String tenCN = txtHoTenCN.getText();
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
		boolean trangThai = false;
		return new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, ngayBatDauLamViec, CMND, diaChi, sDT, troCap,
				new TaiKhoan(tenDangNhap), trangThai);
	}

	private boolean validateCN() {
		String regexTen = "^\\D+";

		if (!txtHoTenCN.getText().matches(regexTen)) {

			JOptionPane.showMessageDialog(this, "Tên không được chứ số và kí tự đặc biệt");
			txtHoTenCN.requestFocus();
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


			JOptionPane.showMessageDialog(this, "Công Nhân phải lớn hơn 18 tuổi");
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

		if (validateCN()) {
			CongNhan cn = this.getCNForAdd();
			CongNhan_DAO cn_DAO = new CongNhan_DAO();
			cn_DAO.create(cn);
			JOptionPane.showMessageDialog(this, "Thêm Thành Công");
			super.dispose();

		}
	}
}
