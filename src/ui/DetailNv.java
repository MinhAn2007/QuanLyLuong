
package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BangLuongCongNhan_Dao;
import dao.BangLuongNhanVien_Dao;
import dao.ChamCongNhanVien_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import entity.BangLuongNhanVien;
import entity.ChamCongNhanVien;

public class DetailNv extends javax.swing.JFrame {

	private DefaultTableModel modelCongDoan;

	private com.toedter.calendar.JDateChooser dateCham;

	private CongNhan_DAO congNhan;
	private String maCN_L;
	private int rowL;

	public DetailNv(String maCN_L, int rowL) {
		this.maCN_L = maCN_L;
		this.rowL = rowL;
		initComponents();
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jTextField1 = new javax.swing.JTextField();

		jTextField1.setText(maCN_L);
		jTextField1.setEnabled(false);
		jTextField1.setEditable(false);
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		String[] congDoanDTS = { "Ngày chấm", "Văng mặt","Có phép", "Số giờ tăng ca" };

		modelCongDoan = new DefaultTableModel(congDoanDTS, 0);

		jTable1 = new JTable(modelCongDoan);

		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jLabel1.setText("Mã nhân viên");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(35, 35, 35)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(34, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();

		setLocationRelativeTo(null);

		docDuLieuDetailNv();

	}// </editor-fold>

	private void docDuLieuDetailNv() {
		BangLuongNhanVien_Dao ccnv_DAO = new BangLuongNhanVien_Dao();
		jTable1.setRowHeight(35);

		for (ChamCongNhanVien ccnv : ccnv_DAO.getDetailLuong(maCN_L)) {

			Object[] rowData = {

					ccnv.getNgayCham(), ccnv.isVangMat() ? "Vắng" : "", ccnv.isCoPhep() ? "Có" : "Không",
					ccnv.getSoGioTangCa() };
			modelCongDoan.addRow(rowData);
		}
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(DetailNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DetailNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DetailNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DetailNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
//                new DetailNv().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration
}
