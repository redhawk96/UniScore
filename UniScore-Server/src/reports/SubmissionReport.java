package reports;

import java.sql.SQLException;

import com.utils.GenerateReport;

import net.sf.jasperreports.engine.JRException;

public class SubmissionReport {

	public SubmissionReport(int examId, String examName, String moduleId) throws ClassNotFoundException, SQLException, JRException {
		
		String reportName = "exam_submission_report.jrxml";
		String nameOfTheFileToBeSaved = moduleId+"-"+examName+"-submission-results";
		String query = "SELECT s.studentId AS 'studentId', s.overallScore AS 'overallScore', s.grade AS 'grade', e.examName AS 'exam', e.moduleId AS 'module' FROM submissions s, exams e WHERE s.examId =  '"+examId + "' AND e.examId = '"+examId+"' AND s.moduleId = '"+moduleId+"'";
        String pathOfTheFolderToSaveReport = "C:\\Users\\RED-HAWK\\Desktop\\UniScore\\Reports\\";
		
		new GenerateReport(reportName, nameOfTheFileToBeSaved, query, pathOfTheFolderToSaveReport);
	}
	
}
