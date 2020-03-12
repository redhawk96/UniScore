package connectivity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import models.Activity;
import models.Exam;
import models.Grade;
import models.Module;
import models.Question;
import models.Submission;
import models.User;

public interface UniScoreInterface extends Remote {

	/*
	 * This interface is required to be present on both client and server. UniScore
	 * class consist of all the implemented methods
	 */

	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Below methods will return a boolean according to the execution status on insertion of record
	 */
	public boolean addActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Below methods will return a boolean according to the execution status on record update
	 */
	public boolean updateActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Below methods will return a boolean according to the execution status on removal of record
	 */
	public boolean removeActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Below methods will return specific record as a model object
	 */
	public Activity getActivityLog(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;

	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;

	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * Below methods will return all availabe records as a list
	 */
	public List<Activity> getActivityLogs() throws RemoteException, ClassNotFoundException, SQLException;

	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException;

	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException;

	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException;

	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException;

	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException;
}
