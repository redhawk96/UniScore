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

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.utils.Identification;

import connectivity.DBConnection;
import models.Exam;
import models.Module;
import models.Submission;

public class SubmissionConnector implements ConnectorInterface<Submission> {

	/*
	 * add : This will add a submission into the databse reffered by exam and module
	 * 
	 * @params {Submission}
	 * 
	 * @return {boolen} returns true if the submission was added to the database and false if not
	 * 
	 * @throws ClassNotFoundException, SQLException
	 */
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
	 * Ammendment of submissions are not allowed to any user type
	 */
	@Override
	public boolean update(Submission submission) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * Removal of submissions is not allowed to any type of user
	 */
	@Override
	public boolean remove(Submission submission) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * get : retrieves a paticular submission by its id
	 * 
	 * @params {Submission} obtains submission id from the submission object
	 * 
	 * @return {Submission} returns submission object if found and null if not
	 * 
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
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

	/*
	 * getAll : retrieves all available submissions
	 * 
	 * @return {List<Submission>} returns a list of submissions if found and null if
	 * not
	 * 
	 * @throws ClassNotFoundException, SQLException
	 */
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

	/*
	 * getByRelevance : retrieves all submissions filtered by its either the module id, student id, or exam id
	 * @params {Submission} obtains either submission id, student id or module id from the submission object
	 * @return {List<Submission>} returns a list of submissions filtered by the relevance if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Submission> getByRelevance(Submission submission) throws ClassNotFoundException, SQLException {
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
			} else {
				return null;
			}

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

	/*
	 * getSubmissionCountByExamination : retrieves the count for submissions for the paticular exam
	 * @params {Submission} Obtains exam id from submission object
	 * @return {int} returns an integer representing the number of submissions for a paticular exam if exam is found and -1 if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public int getSubmissionCountByExamination(Submission submission) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT COUNT(*) AS 'eCount' FROM `submissions` WHERE `submissions`.`examId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, submission.getExamId());
			ResultSet rs = ps.executeQuery();

			int eCount = -1;

			while (rs.next()) {
				eCount = rs.getInt(1);
			}
			return eCount;
		}
		return -1;
	}

	/*
	 * getDatasetByExam : generates a new dataset based on a specific exam
	 * @params {Submission} Obtains exam id from submission object
	 * @return {CategoryDataset} returns a categoryDataset contaning all the submission scores of a specific exam successfully generated and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public CategoryDataset getDatasetByExam(Submission submission) throws ClassNotFoundException, SQLException {
		List<Submission> examSubmissionList = getByRelevance(submission);

		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		
		if (examSubmissionList != null) {
			for (Submission sub : examSubmissionList) {
				switch (sub.getGrade()) {
				case "A":
					a = a + 1;
					break;
				case "B":
					b = b + 1;
					break;
				case "C":
					c = c + 1;
					break;
				case "D":
					d = d + 1;
					break;
				case "E":
					e = e + 1;
					break;
				}
			}
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(a, "MARKS", "75-100");
		dataset.addValue(b, "MARKS", "65-74");
		dataset.addValue(c, "MARKS", "55-64");
		dataset.addValue(d, "MARKS", "35-54");
		dataset.addValue(e, "MARKS", "0-34");

		return dataset;
	}

	/*
	 * getDatasetByStudent : generates a new dataset based on a specific student's last submission on all modules, modules will be filtered according to the logged in lecturer
	 * @params {Module, Submission} Obtains teacher id from module object and student id from submission object
	 * @return {CategoryDataset} returns a categoryDataset contaning all the scores of last submission on each module is successfully generated and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public CategoryDataset getDatasetByStudent(Module module, Submission submission) throws ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		List<Module> moduleList = mc.getByYearAndUser(module, 0, 0);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Module mod : moduleList) {
			dataset.addValue(getLastSubmissionByModule(mod, submission), "MARKS", mod.getModuleId());
		}
		return dataset;
	}

	/*
	 * getLastSubmissionByModule : returns the score of a specific module's most recent exam
	 * @params {Module, Submission} Obtains module id from module object and student id from submission object
	 * @return {int} returns the score of last exam followed by a module if found and 0 if not
	 * @throws ClassNotFoundException, SQLException
	 */
	private int getLastSubmissionByModule(Module module, Submission submission) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT `overallScore` FROM `submissions` WHERE `moduleId` = ? AND `studentId` = ? ORDER BY `examId` DESC LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, module.getModuleId());
			ps.setString(2, submission.getStudentId());
			ResultSet rs = ps.executeQuery();

			int sScore = 0;

			while (rs.next()) {
				sScore = rs.getInt(1);
			}
			return sScore;
		}
		return 0;
	}

	/*
	 * getListAsTable : returns a string contaning html table of all the submissions for a paticular exam of a paticular module
	 * @params {Exam} Obtains exam id and module id from exam object
	 * @return {String} returns a string contaning html table if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public String getListAsTable(Exam exam) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT s.studentId AS 'studentId', s.overallScore AS 'overallScore', s.grade AS 'grade' FROM submissions s, exams e WHERE s.examId = ? AND e.examId = ? AND s.moduleId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, exam.getExamId());
			ps.setInt(2, exam.getExamId());
			ps.setString(3, exam.getModuleId());
			ResultSet rs = ps.executeQuery();

			String submissionDoc = "<html><head></head><body>";
			
			String submissionTable = "<table style=\"border-spacing: 0px !important; border: 1px solid #404040 !important; border-bottom: 0 !important;\">"
					+ "<tr style=\"background-color: #404040!important; color : #f9a825!important; font-size: 17px !important;\">"
					+ "<th style=\"padding: 7px !important;\"> </th>"	
					+ "<th style=\"padding: 7px !important;\">SID</th>"
					+ "<th style=\"text-align: center !important; padding: 7px !important;\">SCORE</th>"
					+ "<th style=\"padding: 7px !important;\">GRADE</th></tr>";

			String bgColor = "";

			int count = 1;
			// Data for the table
			while (rs.next()) {

				if (rs.getString(3).equals("E")) {
					bgColor = "#ff000059";
				} else {
					bgColor = "#ffffff";
				}
				submissionTable = submissionTable + "<tr style=\"font-size: 17px !important; background-color:"
						+ bgColor
						+ " !important;\">"
						+ "<td style=\"width: 100px !important; border-bottom: 1px solid #404040 !important; padding: 5px !important; text-align: center !important;\">"+count+""
						+ "</td><td style=\"width: 100px !important; border-bottom: 1px solid #404040 !important; padding: 5px !important; text-align: center !important;\">"
						+ Identification.getFormatedId(rs.getString(1), "S")
						+ "</td><td style=\"width: 100px !important;  text-align: center !important; border-bottom: 1px solid #404040 !important; padding: 5px !important;\">"
						+ rs.getString(2)
						+ "</td><td style=\"width: 50px !important; text-align: center !important; border-bottom: 1px solid #404040 !important; padding: 5px !important;\">"
						+ rs.getString(3) + "</td></tr>";
				count ++;
			}


			// Getting data for the graph
			Submission submission = new Submission();
			submission.setExamId(exam.getExamId());
			List<Submission> examSubmissionList = getByRelevance(submission);

			int a = 0;
			int b = 0;
			int c = 0;
			int d = 0;
			int e = 0;

			for (Submission sub : examSubmissionList) {
				switch (sub.getGrade()) {
				case "A":
					a = a + 1;
					break;
				case "B":
					b = b + 1;
					break;
				case "C":
					c = c + 1;
					break;
				case "D":
					d = d + 1;
					break;
				case "E":
					e = e + 1;
					break;
				}
			}
			
			String submissionBarChart = "<img src=\"https://quickchart.io/chart?width=400&height=300&format=png&c={'type':'pie','data':{'labels':['75-100','65-74','55-64','35-54','0-34'],'datasets':[{'label':'Marks','data':["+a+","+b+","+c+","+d+","+e+"]}]}}\" style=\"float: right;padding-right: 200px;\"><br><br>";
			submissionDoc = submissionDoc + submissionBarChart;
			submissionDoc = submissionDoc + submissionTable;
			submissionDoc = submissionDoc + "</table></body></html>";
			return submissionDoc;
		}
		return null;
	}

}
