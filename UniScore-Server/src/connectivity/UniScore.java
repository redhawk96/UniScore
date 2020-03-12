package connectivity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import connectors.ActivityConnector;
import connectors.ExamConnector;
import connectors.GradeConnector;
import connectors.ModuleConnector;
import connectors.QuestionConnector;
import connectors.SubmissionConnector;
import connectors.UserConnector;
import models.Activity;
import models.Exam;
import models.Grade;
import models.Module;
import models.Question;
import models.Submission;
import models.User;

@SuppressWarnings("serial")
public class UniScore extends UnicastRemoteObject implements UniScoreInterface {

	protected UniScore() throws RemoteException, ClassNotFoundException, SQLException {
		super();
	}

	/*
	 * prints out a welcome message to system on console
	 * 
	 * @param {}
	 * 
	 * @returns {boolean}
	 */
	@Override
	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException {
		if(DBConnection.getDBConnection() != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.add(activity);
	}

	@Override
	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.add(exam);
	}

	@Override
	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.add(grade);
	}

	@Override
	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.add(module);
	}

	@Override
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.add(question);
	}

	@Override
	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.add(submission);
	}

	@Override
	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.add(user);
	}

	@Override
	public boolean updateActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.update(activity);
	}

	@Override
	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.update(exam);
	}

	@Override
	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.update(grade);
	}

	@Override
	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.update(module);
	}

	@Override
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.update(question);
	}

	@Override
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.update(user);
	}

	@Override
	public boolean removeActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.remove(activity);
	}

	@Override
	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.remove(exam);
	}

	@Override
	public boolean removeGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.remove(grade);
	}

	@Override
	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.remove(module);
	}

	@Override
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.remove(question);	
	}

	@Override
	public boolean removeSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.remove(submission);
	}

	@Override
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.remove(user);
	}

	@Override
	public Activity getActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.get(activity);
	}

	@Override
	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.get(exam);
	}

	@Override
	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.get(grade);
	}

	@Override
	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.get(module);
	}

	@Override
	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector q = new QuestionConnector();
		return q.get(question);
	}

	@Override
	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.get(submission);
	}

	@Override
	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.get(user);
	}

	@Override
	public List<Activity> getActivityLogs() throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.getAll();
	}

	@Override
	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getAll();
	}

	@Override
	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.getAll();
	}

	@Override
	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getAll();
	}

	@Override
	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector q = new QuestionConnector();
		return q.getAll();
	}

	@Override
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getExaminationQuestionSet(question);	
	}

	@Override
	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getAll();
	}

	@Override
	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.getAll();
	}

}
