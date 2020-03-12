package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Grade;

public class GradeConnector implements ConnectorInterface<Grade> {

	@Override
	public boolean add(Grade grade) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `grades`(`grade`, `passMark`) VALUES (? ,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, grade.getGrade());
			ps.setInt(2, grade.getPassMark());

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean update(Grade grade) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `grades` SET `passMark`=? WHERE `grades`.`grade`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, grade.getPassMark());
			ps.setString(2, grade.getGrade());

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Grade grade) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `grades` WHERE `grades`.`grade`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, grade.getGrade());

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Grade get(Grade grade) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `grades` WHERE `grades`.`grade`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, grade.getGrade());
			ResultSet rs = ps.executeQuery();

			Grade g = new Grade();

			while (rs.next()) {

				g.setGrade(rs.getString(1));
				g.setPassMark(rs.getInt(2));
			}
			return g;
		}
		return null;
	}

	@Override
	public List<Grade> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `exams`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Grade> gradeList = new ArrayList<>();

			while (rs.next()) {
				Grade g = new Grade();

				g.setGrade(rs.getString(1));
				g.setPassMark(rs.getInt(2));

				gradeList.add(g);
			}
			return gradeList;
		}
		return null;
	}

}
