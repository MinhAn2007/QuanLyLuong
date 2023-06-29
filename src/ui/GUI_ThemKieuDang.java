/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GUI_ThemKieuDang extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public GUI_ThemKieuDang() {
		initComponents();
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		pThemKieuDang = new javax.swing.JPanel();
		lblTieuDe = new javax.swing.JLabel();
		txtTenKieuDang = new javax.swing.JTextField();
		btnThem = new javax.swing.JButton();
		btnThoat = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		pThemKieuDang.setBackground(new java.awt.Color(255, 255, 255));

		lblTieuDe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		lblTieuDe.setText("Nhập tên kiểu dáng");

		txtTenKieuDang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		txtTenKieuDang.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTenKieuDangActionPerformed(evt);
			}
		});

		btnThem.setBackground(new java.awt.Color(0, 153, 102));
		btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		btnThem.setForeground(new java.awt.Color(255, 255, 255));
		btnThem.setText("Thêm");

		btnThoat.setBackground(new java.awt.Color(255, 102, 102));
		btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		btnThoat.setForeground(new java.awt.Color(255, 255, 255));
		btnThoat.setText("Thoát");
		btnThoat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pThemKieuDangLayout = new javax.swing.GroupLayout(pThemKieuDang);
		pThemKieuDang.setLayout(pThemKieuDangLayout);
		pThemKieuDangLayout.setHorizontalGroup(pThemKieuDangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pThemKieuDangLayout.createSequentialGroup()
						.addGroup(pThemKieuDangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pThemKieuDangLayout.createSequentialGroup().addGap(78, 78, 78)
										.addComponent(btnThem).addGap(32, 32, 32).addComponent(btnThoat))
								.addGroup(pThemKieuDangLayout.createSequentialGroup().addGap(57, 57, 57).addComponent(
										txtTenKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 217,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(58, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThemKieuDangLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE).addComponent(lblTieuDe).addGap(92, 92, 92)));
		pThemKieuDangLayout.setVerticalGroup(pThemKieuDangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pThemKieuDangLayout.createSequentialGroup().addGap(28, 28, 28)
						.addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(txtTenKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(pThemKieuDangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem).addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE,
										28, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(46, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pThemKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pThemKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
		setLocationRelativeTo(null);
		btnThem.addActionListener(this);
	}// </editor-fold>

	private void txtTenKieuDangActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI_ThemKieuDang().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnThoat;
	private javax.swing.JLabel lblTieuDe;
	private javax.swing.JPanel pThemKieuDang;
	private javax.swing.JTextField txtTenKieuDang;
	public static GUI_ThemSanPham gui_ThemSanPham;
	// End of variables declaration

	public String getKieuDang() {
		return txtTenKieuDang.getText().toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			GUI_ThemSanPham.addkieuDang(txtTenKieuDang.getText().toString());
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			super.dispose();
			super.repaint();
		}
	}
}
