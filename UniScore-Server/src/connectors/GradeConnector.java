/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Ishani Welagedara (UOB-1940672)
 */

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

	/*
	 * add : This will add a new grade into the database and will not have any refferance tables
	 * @params {Grade}
	 * @return {boolen} returns true if the grade was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
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

	/*
	 * update : This will update a paticular grade's pass-mark reffered by the grade
	 * @params {Grade} 
	 * @return {boolen} returns true if the grade was updated to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
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

	/*
	 * Removal of grades will not be allowed to any type of user
	 */
	@Override
	public boolean remove(Grade grade) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * get : retrieves a paticular grade by the pass mark (Eg : A will retrieve, grade - A, if passmark > 85)
	 * @params {Grade} obtains pass mark from the grade object
	 * @return {Grade} returns a grade object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public Grade get(Grade grade) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `grades` WHERE `grades`.`passMark`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, grade.getPassMark());
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

	/*
	 * getAll : retrieves all available grades
	 * @return {List<Grade>} returns a list grades of if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
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
