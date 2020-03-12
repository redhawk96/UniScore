package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Exam;

public class ExamConnector implements ConnectorInterface<Exam> {

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

}
