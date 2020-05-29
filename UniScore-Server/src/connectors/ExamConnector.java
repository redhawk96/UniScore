/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Uditha Silva (UOB-1938086)
 */

package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Exam;
import models.Module;
import models.User;

public class ExamConnector implements ConnectorInterface<Exam> {

	/*
	 * add : This is will add a new exam into the database reffered to a paticular module
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean add(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `exams`(`examName`, `moduleId`, `duration`, `enrollmentKey`, `status`) VALUES (?, ?, ?, ? ,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, exam.getExamName());
			ps.setString(2, exam.getModuleId());
			ps.setInt(3, exam.getDuration());
			ps.setString(4, exam.getEnrollmentKey());
			ps.setString(5, exam.getStatus());

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
	 * update : This will update a paticular exam reffered by the exam id
	 * @params {Exam} 
	 * @return {boolen} returns true if the exam was updated to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean update(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `exams` SET `examName`=?, `moduleId`=?, `duration`=?, `enrollmentKey`=?, `status`=? WHERE `exams`.`examId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, exam.getExamName());
			ps.setString(2, exam.getModuleId());
			ps.setInt(3, exam.getDuration());
			ps.setString(4, exam.getEnrollmentKey());
			ps.setString(5, exam.getStatus());
			ps.setInt(6, exam.getExamId());

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
	 * remove : This will remove a paticular exam from the databse along with all the submission related to it
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added removed from the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean remove(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `exams` WHERE `exams`.`examId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, exam.getExamId());

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
	 * get : retrieves a paticular exam by its id
	 * @params {Exam} obtains exam id from exam object
	 * @return {Exam} returns an exam object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public Exam get(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `exams` WHERE `exams`.`examId` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, exam.getExamId());
			ResultSet rs = ps.executeQuery();

			Exam e = new Exam();

			while (rs.next()) {

				e.setExamId(rs.getInt(1));
				e.setExamName(rs.getString(2));
				e.setModuleId(rs.getString(3));
				e.setDuration(rs.getInt(4));
				e.setEnrollmentKey(rs.getString(5));
				e.setStatus(rs.getString(6));
				e.setCreatedAt(rs.getTimestamp(7));
				e.setUpdatedAt(rs.getTimestamp(8));
			}
			return e;
		}
		return null;
	}

	/*
	 * getAll : retrieves all available exams
	 * @params {} obtains module id from exam object
	 * @return {List<Exam>} returns a list of exams if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public List<Exam> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `exams`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Exam> examList = new ArrayList<>();

			while (rs.next()) {
				Exam e = new Exam();

				e.setExamId(rs.getInt(1));
				e.setExamName(rs.getString(2));
				e.setModuleId(rs.getString(3));
				e.setDuration(rs.getInt(4));
				e.setEnrollmentKey(rs.getString(5));
				e.setStatus(rs.getString(6));
				e.setCreatedAt(rs.getTimestamp(7));
				e.setUpdatedAt(rs.getTimestamp(8));

				examList.add(e);
			}
			return examList;
		}
		return null;
	}
	
	/*
	 * getByAvailability : retrieves all available exams filtered by a paticular module and exam status
	 * @params {Exam} obtains module id and status from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module with an active status if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Exam> getByAvailability(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `exams` WHERE  `exams`.`moduleId` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, exam.getModuleId());
			ResultSet rs = ps.executeQuery();

			List<Exam> examList = new ArrayList<>();

			while (rs.next()) {
				Exam e = new Exam();

				e.setExamId(rs.getInt(1));
				e.setExamName(rs.getString(2));
				e.setModuleId(rs.getString(3));
				e.setDuration(rs.getInt(4));
				e.setEnrollmentKey(rs.getString(5));
				e.setStatus(rs.getString(6));
				e.setCreatedAt(rs.getTimestamp(7));
				e.setUpdatedAt(rs.getTimestamp(8));

				examList.add(e);
			}
			return examList;
		}
		return null;
	}
	
	/*
	 * getByModule : retrieves all available exams filtered by a paticular module
	 * @params {Exam} obtains module id from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Exam> getByModule(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `exams` WHERE `exams`.`moduleId` = ?  ORDER BY `exams`.`examId` DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, exam.getModuleId());
			ResultSet rs = ps.executeQuery();

			List<Exam> examList = new ArrayList<>();

			while (rs.next()) {
				Exam e = new Exam();

				e.setExamId(rs.getInt(1));
				e.setExamName(rs.getString(2));
				e.setModuleId(rs.getString(3));
				e.setDuration(rs.getInt(4));
				e.setEnrollmentKey(rs.getString(5));
				e.setStatus(rs.getString(6));
				e.setCreatedAt(rs.getTimestamp(7));
				e.setUpdatedAt(rs.getTimestamp(8));

				examList.add(e);
			}
			return examList;
		}
		return null;
	}
	
	/*
	 * getCountByModules : retrieves count of all available exams filtered by allocated modules for an user
	 * @params {User} obtains user id from user object
	 * @return {int} returns returns an integer representing the number of exams if found and -1 if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public int getCountByModules(User user) throws ClassNotFoundException, SQLException {
		
		if (DBConnection.getDBConnection() != null) {
			ModuleConnector mc = new ModuleConnector();
			Module m = new Module();
			m.setTeacherId(user.getUserId());
			List<Module> moduleList = mc.getByYearAndUser(m, 0, 0);
			
			int eCount = 0;
			
			for (Module module : moduleList) {
				Exam tempExam = new Exam();
				tempExam.setModuleId(module.getModuleId());
				List<Exam> examList = getByModule(tempExam);

				for (@SuppressWarnings("unused") Exam e : examList) {
					eCount = eCount + 1;
				}
			}
			return eCount;
		}
		return -1;
	}

}
