package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Submission;

public class SubmissionConnector implements ConnectorInterface<Submission> {

	@Override
	public boolean add(Submission submission) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `submissions`(`moduleId`, `studentId`, `examId`, `answerList`, `overallScore`, `grade`) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, submission.getModuleId());
			ps.setString(2, submission.getStudentId());
			ps.setInt(3, submission.getExamId());
			ps.setString(4, submission.getAnswerList());
			ps.setDouble(5, submission.getOverallScore());
			ps.setString(6, submission.getGrade());

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
	 * Submissions are not allowed to be updated by any user type
	 */
	@Override
	public boolean update(Submission submission) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * In order to remove a submission, moduleId, studentId, examId all three must be submitted
	 */
	@Override
	public boolean remove(Submission submission) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `submissions` WHERE `submissions`.`moduleId`=? AND `submissions`.`studentId`=? AND `submissions`.`examId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, submission.getModuleId());
			ps.setString(2, submission.getStudentId());
			ps.setInt(3, submission.getExamId());

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
	public Submission get(Submission submission) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = null;
			PreparedStatement ps = null;

			if (submission.getModuleId() != null) {
				sql = "SELECT * FROM `submissions` WHERE `submissions`.`moduleId`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, submission.getModuleId());
			} else if (submission.getStudentId() != null) {
				sql = "SELECT * FROM `submissions` WHERE `submissions`.`studentId`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, submission.getStudentId());
			} else if (submission.getExamId() != 0) {
				sql = "SELECT * FROM `submissions` WHERE `submissions`.`examId`=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, submission.getExamId());
			}

			ResultSet rs = ps.executeQuery();

			Submission s = new Submission();

			while (rs.next()) {

				s.setModuleId(rs.getString(1));
				s.setStudentId(rs.getString(2));
				s.setExamId(rs.getInt(3));
				s.setAnswerList(rs.getString(4));
				s.setOverallScore(rs.getDouble(5));
				s.setGrade(rs.getString(6));
				s.setSubmittedOn(rs.getTimestamp(7));
			}
			return s;
		}
		return null;
	}

	@Override
	public List<Submission> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `submissions`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Submission> submissionList = new ArrayList<>();

			while (rs.next()) {
				Submission s = new Submission();

				s.setModuleId(rs.getString(1));
				s.setStudentId(rs.getString(2));
				s.setExamId(rs.getInt(3));
				s.setAnswerList(rs.getString(4));
				s.setOverallScore(rs.getDouble(5));
				s.setGrade(rs.getString(6));
				s.setSubmittedOn(rs.getTimestamp(7));

				submissionList.add(s);
			}
			return submissionList;
		}
		return null;
	}

}
