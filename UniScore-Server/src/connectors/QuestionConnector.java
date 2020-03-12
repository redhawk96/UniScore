package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Question;

public class QuestionConnector implements ConnectorInterface<Question> {

	@Override
	public boolean add(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `questions`(`examId`, `question`, `option1`, `option2`, `option3`, `option4`, `answer`) VALUES (?, ?, ?, ? ,? ,?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getExamId());
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getOption1());
			ps.setString(4, question.getOption2());
			ps.setString(5, question.getOption3());
			ps.setString(6, question.getOption4());
			ps.setString(7, question.getAnswer());

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
	public boolean update(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `questions` SET `examId`=?, `question`=?, `option1`=?, `option2`=?, `option3`=?, `option4`=?, `answer`=? WHERE `questions`.`questionsId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getExamId());
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getOption1());
			ps.setString(4, question.getOption2());
			ps.setString(5, question.getOption3());
			ps.setString(6, question.getOption4());
			ps.setString(7, question.getAnswer());
			ps.setInt(8, question.getQuestionId());

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
	public boolean remove(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `questions` WHERE `questions`.`questionsId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getQuestionId());

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
	public Question get(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `questions` WHERE `questions`.`questionId` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getQuestionId());
			ResultSet rs = ps.executeQuery();

			Question q = new Question();

			while (rs.next()) {

				q.setQuestionId(rs.getInt(1));
				q.setExamId(rs.getInt(2));
				q.setQuestion(rs.getString(3));
				q.setOption1(rs.getString(4));
				q.setOption2(rs.getString(5));
				q.setOption2(rs.getString(6));
				q.setOption3(rs.getString(7));
				q.setOption4(rs.getString(8));
			}
			return q;
		}
		return null;
	}

	@Override
	public List<Question> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `questions`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Question> questionList = new ArrayList<>();

			while (rs.next()) {
				Question q = new Question();
				q.setQuestionId(rs.getInt(1));
				q.setExamId(rs.getInt(2));
				q.setQuestion(rs.getString(3));
				q.setOption1(rs.getString(4));
				q.setOption2(rs.getString(5));
				q.setOption2(rs.getString(6));
				q.setOption3(rs.getString(7));
				q.setOption4(rs.getString(8));

				questionList.add(q);
			}
			return questionList;
		}
		return null;
	}
	
	
	public List<Question> getExaminationQuestionSet(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `questions` WHERE `questions`.`examId` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getExamId());
			ResultSet rs = ps.executeQuery();

			List<Question> questionList = new ArrayList<>();

			while (rs.next()) {
				Question q = new Question();
				q.setQuestionId(rs.getInt(1));
				q.setExamId(rs.getInt(2));
				q.setQuestion(rs.getString(3));
				q.setOption1(rs.getString(4));
				q.setOption2(rs.getString(5));
				q.setOption2(rs.getString(6));
				q.setOption3(rs.getString(7));
				q.setOption4(rs.getString(8));

				questionList.add(q);
			}
			return questionList;
		}
		return null;
	}

}
