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
import models.Question;

public class QuestionConnector implements ConnectorInterface<Question> {

	/*
	 * add : This will add a question into the databse reffered by a module
	 * @params {Question}
	 * @return {boolen} returns true if the question was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
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
			ps.setInt(7, question.getAnswer());

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
	 * update : This will update a paticular question reffered by the question id
	 * @params {Question} 
	 * @return {boolen} returns true if the question was updated to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean update(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `questions` SET `question`=?, `option1`=?, `option2`=?, `option3`=?, `option4`=?, `answer`=? WHERE `questions`.`questionId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, question.getQuestion());
			ps.setString(2, question.getOption1());
			ps.setString(3, question.getOption2());
			ps.setString(4, question.getOption3());
			ps.setString(5, question.getOption4());
			ps.setInt(6, question.getAnswer());
			ps.setInt(7, question.getQuestionId());

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
	 * remove : This will remove a paticular question from the database
	 * @params {Question}
	 * @return {boolen} returns true if the question was removed from the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean remove(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `questions` WHERE `questions`.`questionId`=?";
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
	
	/*
	 * get : retrieves a paticular question by its id
	 * @params {Question} obtains questions id from the question object
	 * @return {Question} returns question object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
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
				q.setOption3(rs.getString(6));
				q.setOption4(rs.getString(7));
				q.setAnswer(rs.getInt(8));
				q.setCreatedAt(rs.getTimestamp(9));
				q.setUpdatedAt(rs.getTimestamp(10));
			}
			return q;
		}
		return null;
	}

	/*
	 * getAll : retrieves all available questions
	 * @return {List<Question>} returns a list of questions if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
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
				q.setOption3(rs.getString(6));
				q.setOption4(rs.getString(7));
				q.setAnswer(rs.getInt(8));
				q.setCreatedAt(rs.getTimestamp(9));
				q.setUpdatedAt(rs.getTimestamp(10));
				
				questionList.add(q);
			}
			return questionList;
		}
		return null;
	}
	
	/*
	 * getByExamination : retrieves all available questions for the paticular exam
	 * @params {Question} Obtains exam id from question object
	 * @return {List<Question>} returns a list of questions for a paticular exam if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Question> getByExamination(Question question) throws ClassNotFoundException, SQLException {
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
				q.setOption3(rs.getString(6));
				q.setOption4(rs.getString(7));
				q.setAnswer(rs.getInt(8));
				q.setCreatedAt(rs.getTimestamp(9));
				q.setUpdatedAt(rs.getTimestamp(10));
				
				questionList.add(q);
			}
			return questionList;
		}
		return null;
	}
	
	
	/*
	 * getQuestionCountByExamination : retrieves the count for questions for the paticular exam
	 * @params {Question} Obtains exam id from question object
	 * @return {int} returns an integer representing the number of questions for a paticular exam if exam is found and -1 if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public int getQuestionCountByExamination(Question question) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT COUNT(*) as 'qCount' FROM `questions` WHERE `questions`.`examId` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getExamId());
			ResultSet rs = ps.executeQuery();

			int qCount = -1;

			while (rs.next()) {
				qCount = rs.getInt(1);
			}
			return qCount;
		}
		return -1;
	}
	
	/*
	 * getBySearch : retrieves all available questions filtered by either question id or tile
	 * @params {String, Question} obtains a string to base the search and exam id from question object 
	 * @return {List<Question>} returns a list of filtered questions by either question id or tile if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Question> getBySearch(String searchString) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `questions` WHERE `questions`.`questionId` LIKE ? OR `questions`.`question` LIKE ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+searchString+"%");
			ps.setString(2, "%"+searchString+"%");
			ResultSet rs = ps.executeQuery();

			List<Question> questionList = new ArrayList<>();

			while (rs.next()) {
				Question q = new Question();
				q.setQuestionId(rs.getInt(1));
				q.setExamId(rs.getInt(2));
				q.setQuestion(rs.getString(3));
				q.setOption1(rs.getString(4));
				q.setOption2(rs.getString(5));
				q.setOption3(rs.getString(6));
				q.setOption4(rs.getString(7));
				q.setAnswer(rs.getInt(8));
				q.setCreatedAt(rs.getTimestamp(9));
				q.setUpdatedAt(rs.getTimestamp(10));
				
				questionList.add(q);
			}
			return questionList;
		}
		return null;
	}

}
