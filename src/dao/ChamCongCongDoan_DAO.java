package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChamCongCongDoan;
import entity.CongDoan;
import entity.CongNhan;

public class ChamCongCongDoan_DAO {

	public ArrayList<ChamCongCongDoan> getDanhSachChamCongCNThucHienCD(String maCongDoan) {
		ArrayList<ChamCongCongDoan> dsChamCongCongDoan = new ArrayList<ChamCongCongDoan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "Select distinct maCongDoan, maCongNhan from ChamCongCongDoan WHERE maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCongDoan);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCD = rs.getString("maCongDoan");
				String maCN = rs.getString("maCongNhan");
				ChamCongCongDoan cccd = new ChamCongCongDoan(new CongDoan(maCD), new CongNhan(maCN));
				dsChamCongCongDoan.add(cccd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChamCongCongDoan;
	}

	public ArrayList<String> getDanhSachMaCNThucHienCD(String maCongDoan) {
		ArrayList<String> dsMaCN = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "	Select DISTINCT maCongNhan from ChamCongCongDoan WHERE maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCongDoan);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCongNhan = rs.getString("maCongNhan");

				dsMaCN.add(maCongNhan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMaCN;
	}
	public void insertPhanCong(ChamCongCongDoan cccd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO ChamCongCongDoan(maChamCongCD, maCongDoan, maCongNhan, ngayCham)\r\n" + "	VALUES (?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, cccd.getMaChamCongCD());
			statement.setString(2, cccd.getCongDoan().getMaCongDoan());
			statement.setString(3, cccd.getCongNhan().getMaCongNhan());
			statement.setDate(4, cccd.getNgayCham());
			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public int getSoNguoiThamGiaTheoMaCD(String maCD) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "SELECT COUNT(*) AS Total FROM ChamCongCongDoan WHERE maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getSoLuongCuaCongNhan(String maCD, String maCN) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "SELECT SUM(soLuong) As Total \r\n"
					+ "	FROM ChamCongCongDoan \r\n"
					+ "	WHERE maCongDoan = ? AND maCongNhan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			statement.setString(2, maCN);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<String> getDanhSachMaCD() {
		ArrayList<String> listMaCD = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "	SELECT DISTINCT maCongDoan FROM ChamCongCongDoan\r\n";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maCD = rs.getString("maCongDoan");
				listMaCD.add(maCD);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMaCD;
	}

	public ArrayList<ChamCongCongDoan> getAllDanhSachChamCongCongDoan() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChamCongCongDoan> dsAllCCCD = new ArrayList<ChamCongCongDoan>();
		try {
			String sql = "Select * from ChamCongCongDoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maChamCongCD = rs.getString(1);
				String maCD = rs.getString(2);
				String maCN = rs.getString(3);
				Date ngayCham = rs.getDate(4);
				Boolean vangMat = rs.getBoolean(5);
				Boolean coPhep = rs.getBoolean(6);
				String caLam = rs.getString(7);
				int soLuong = rs.getInt(8);

				ChamCongCongDoan cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD), new CongNhan(maCN),
						ngayCham, vangMat, coPhep, caLam, soLuong);
				dsAllCCCD.add(cccd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsAllCCCD;
	}

	public void updateChamCongCD(ChamCongCongDoan cccd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "	UPDATE ChamCongCongDoan\r\n"
					+ "	SET soLuong = ?, vangMat = ?, coPhep = ?, caLam = ?\r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)\r\n"
					+ "	AND maCongDoan = ? AND maCongNhan = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, cccd.getSoLuong());
			statement.setBoolean(2, cccd.isVangMat());
			statement.setBoolean(3, cccd.isCoPhep());
			statement.setString(4, cccd.getCaLam());
			statement.setString(5, cccd.getCongDoan().getMaCongDoan());
			statement.setString(6, cccd.getCongNhan().getMaCongNhan());

			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public void updateSoLuongCD(ChamCongCongDoan cccd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "	UPDATE ChamCongCongDoan\r\n"
					+ "	SET soLuong = 0\r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)\r\n"
					+ "	AND maCongDoan = ? AND maCongNhan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, cccd.getCongDoan().getMaCongDoan());
			statement.setString(2, cccd.getCongNhan().getMaCongNhan());

			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public int demSoLuongCCCD(String maCD) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "	SELECT COUNT(soLuong) As Total\r\n" + "	FROM ChamCongCongDoan\r\n"
					+ "	WHERE maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int demSumSoLuongCCCD(String maCD) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "	SELECT SUM(soLuong) As Total\r\n" + "	FROM ChamCongCongDoan\r\n"
					+ "	WHERE maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int demSumSoLuongCCCDHomNay(String maCD) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "SELECT SUM(soLuong) As Total	FROM ChamCongCongDoan\r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)\r\n"
					+ "	AND maCongDoan = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void deleteCongNhanPC(String maCN) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "DELETE FROM ChamCongCongDoan \r\n" + "	WHERE maCongNhan = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCN);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSoLuongNULL(String maCD) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "	DELETE FROM ChamCongCongDoan WHERE soLuong IS NULL AND maCongDoan = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getSoLuongChamCongCongDoan() {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) AS Total FROM ChamCongCongDoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count + 1;
	}
	
	public ArrayList<entity.ChamCongCongDoan> getDanhSachChamCongCongDoanHomNay(String maCongDoan) {
		ArrayList<ChamCongCongDoan> dsDaChamCongCD = new ArrayList<ChamCongCongDoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement pStatement = null;
			String sql = "SELECT * FROM ChamCongCongDoan \r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)"
					+ "AND maCongDoan = ?";
			pStatement = con.prepareStatement(sql);
			pStatement.setString(1, maCongDoan);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				String maChamCongCD = rs.getString(1);
				String maCD = rs.getString(2);
				String maCN = rs.getString(3);
				Date ngayCham = rs.getDate(4);
				Boolean vangMat = rs.getBoolean(5);
				Boolean coPhep = rs.getBoolean(6);
				String caLam = rs.getString(7);
				int soLuong = rs.getInt(8);

				ChamCongCongDoan cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD), new CongNhan(maCN),
						ngayCham, vangMat, coPhep, caLam, soLuong);
				dsDaChamCongCD.add(cccd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDaChamCongCD;
	}
	
	public ArrayList<entity.ChamCongCongDoan> getDanhSachChamCongCongDoanHomNay2(String maCongDoan) {
		ArrayList<ChamCongCongDoan> dsDaChamCongCD = new ArrayList<ChamCongCongDoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement pStatement = null;
			String sql = "SELECT * FROM ChamCongCongDoan \r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)"
					+ "OR maCongDoan = ?";
			pStatement = con.prepareStatement(sql);
			pStatement.setString(1, maCongDoan);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				String maChamCongCD = rs.getString(1);
				String maCD = rs.getString(2);
				String maCN = rs.getString(3);
				Date ngayCham = rs.getDate(4);
				Boolean vangMat = rs.getBoolean(5);
				Boolean coPhep = rs.getBoolean(6);
				String caLam = rs.getString(7);
				int soLuong = rs.getInt(8);

				ChamCongCongDoan cccd = new ChamCongCongDoan(maChamCongCD, new CongDoan(maCD), new CongNhan(maCN),
						ngayCham, vangMat, coPhep, caLam, soLuong);
				dsDaChamCongCD.add(cccd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDaChamCongCD;
	}
	
	public void insertChamCongCD(ChamCongCongDoan cccd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO ChamCongCongDoan\r\n" + "	VALUES (?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, cccd.getMaChamCongCD());
			statement.setString(2, cccd.getCongDoan().getMaCongDoan());
			statement.setString(3, cccd.getCongNhan().getMaCongNhan());
			statement.setDate(4, cccd.getNgayCham());
			statement.setBoolean(5, cccd.isVangMat());
			statement.setBoolean(6, cccd.isCoPhep());
			statement.setString(7, cccd.getCaLam());
			statement.setInt(8, cccd.getSoLuong());
			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public int getSoLuongChamCongCongDoanTheoMaCD(String maCD) {
		int count = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "SELECT COUNT(*) AS Total \r\n"
					+ "	FROM ChamCongCongDoan\r\n"
					+ "	WHERE DATEPART(YEAR, GETDATE()) = DATEPART(YEAR, ngayCham) \r\n"
					+ "	AND  DATEPART(MONTH, GETDATE()) = DATEPART(MONTH, ngayCham) \r\n"
					+ "	AND	DATEPART(DAY, GETDATE()) = DATEPART(DAY, ngayCham)\r\n"
					+ "	AND maCongDoan = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("Total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
