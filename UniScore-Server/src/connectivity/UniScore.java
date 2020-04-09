/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package connectivity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.List;

import com.utils.Encryptor;

import connectors.ActivityConnector;
import connectors.ExamConnector;
import connectors.GradeConnector;
import connectors.LoginConnector;
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
	 * Checks for the connection status to the databse
	 * @return {boolen} returns true if the connection to database is established and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException {
		if(DBConnection.getDBConnection() != null) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * addActivityLog : This will add a new user triggerd activity into the databse
	 * @params {Activity} 
	 * @return {boolen} returns true if the activity was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.add(activity);
	}

	/*
	 * getLogActivity : retrieves a paticular activity by its id
	 * @params {Activity} Obtains activity id from the activity object
	 * @return {Activity} returns an activity object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Activity getLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.get(activity);
	}

	/*
	 * getLogActivitiesByUser : retrieves all available activities filtered by a paticular user
	 * @params {Activity} Obtains user id from the activity object
	 * @return {List<Activity>} returns a list of activities of a paticular user if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Activity> getLogActivitiesByUser(Activity activity) throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.getbyUser(activity);
	}

	/*
	 * getLogActivities : retrieves all available activities
	 * @return {List<Activity>} returns a list activities of if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Activity> getLogActivities() throws RemoteException, ClassNotFoundException, SQLException {
		ActivityConnector ac = new ActivityConnector();
		return ac.getAll();
	}

	/*
	 * addExam : This is will add a new exam into the database reffered to a paticular module
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.add(exam);
	}

	/*
	 * updateExam : This will update a paticular exam reffered by the exam id
	 * @params {Exam} 
	 * @return {boolen} returns true if the exam was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.update(exam);
	}

	/*
	 * removeExam : This will remove a paticular exam from the databse along with all the submission related to it
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.remove(exam);
	}

	/*
	 * getExam : retrieves a paticular exam by its id
	 * @params {Exam} obtains exam id from exam object
	 * @return {Exam} returns an exam object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.get(exam);
	}

	/*
	 * getExamsByModule : retrieves all available exams filtered by a paticular module
	 * @params {Exam} obtains module id from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Exam> getExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getByModule(exam);
	}

	/*
	 * getAvailableExamsByModule : retrieves all available exams filtered by a paticular module and active status
	 * @params {Exam} obtains module id and status from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module with an active status if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Exam> getAvailableExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getByAvailability(exam);
	}

	/*
	 * getExams : retrieves all available exams
	 * @return {List<Exam>} returns a list of exams if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException {
		ExamConnector ec = new ExamConnector();
		return ec.getAll();
	}

	/*
	 * addGrade : This will add a new grade into the database and will not have any refferance tables
	 * @params {Grade}
	 * @return {boolen} returns true if the grade was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.add(grade);
	}

	/*
	 * updateGrade : This will update a paticular grade's pass-mark reffered by the grade
	 * @params {Grade} 
	 * @return {boolen} returns true if the grade was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.update(grade);
	}


	/*
	 * getGrade : retrieves a paticular grade by the pass mark (Eg : A will retrieve, grade - A, if passmark > 85)
	 * @params {Grade} obtains pass mark from the grade object
	 * @return {Grade} returns a grade object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.get(grade);
	}

	/*
	 * getGrades : retrieves all available grades
	 * @return {List<Grade>} returns a list grades of if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException {
		GradeConnector gc = new GradeConnector();
		return gc.getAll();
	}

	/*
	 * addModule : This will add a module into the database reffered by the year and semester
	 * @params {Module}
	 * @return {boolen} returns true if the module was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.add(module);
	}

	/*
	 * updateModule :  This will update a paticular module reffered by the module id
	 * @params {Module}
	 * @return {boolen} returns true if the module was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.update(module);
	}

	/*
	 * removeModule : This will remove a paticular module from the database along with the associated exams and submissions related to it
	 * @params {Module}
	 * @return {boolen} returns true if the module was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.remove(module);
	}

	/*
	 * getModule : retrieves a paticular module by its id
	 * @params {Module} obtains module id from the module object
	 * @return {Module} returns a module object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.get(module);
	}

	/*
	 * getModules : retrieves all available modules
	 * @return {List<Module>} returns a list of modules if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getAll();
	}
	
	/*
	 * getByYearAndUser : retrieves all available modules reffered by year, semester and user or year and semester
	 * @params {Module, int, int} Obtains teacher id form module and year and semester to be filtered accordingly
	 * @return {List<Module>} returns a list of modules for a paticular year, semester and  user or year and semester if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Module> getModulesByRelevance(Module module, int year, int semester) throws RemoteException, ClassNotFoundException, SQLException {
		ModuleConnector mc = new ModuleConnector();
		return mc.getByYearAndUser(module, year, semester);
	}

	/*
	 * addQuestion : This will add a question into the databse reffered by a module
	 * @params {Question}
	 * @return {boolen} returns true if the question was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.add(question);
	}

	/*
	 * updateModule : This will update a paticular question reffered by the question id
	 * @params {Question} 
	 * @return {boolen} returns true if the question was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.update(question);
	}

	/*
	 * removeQuestion : This will remove a paticular question from the database
	 * @params {Question}
	 * @return {boolen} returns true if the question was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.remove(question);
	}

	/*
	 * getQuestion : retrieves a paticular question by its id
	 * @params {Question} obtains questions id from the question object
	 * @return {Question} returns question object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.get(question);
	}

	/*
	 * getQuestions : retrieves all available questions
	 * @return {List<Question>} returns a list of questions if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getAll();
	}

	/*
	 * getExamQuestions : retrieves all available questions for the paticular exam
	 * @params {Question} Obtains exam id from question object
	 * @return {List<Question>} returns a list of questions for a paticular exam if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		QuestionConnector qc = new QuestionConnector();
		return qc.getByExamination(question);
	}

	/*
	 * addSubmission : This will add a submission into the databse reffered by exam and module
	 * @params {Submission}
	 * @return {boolen} returns true if the submission was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.add(submission);
	}

	/*
	 * getSubmission : retrieves a paticular submission by its id
	 * @params {Submission} obtains submission id from the submission object
	 * @return {Submission} returns submission object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.get(submission);
	}

	/*
	 * getSubmissionsByRelevance : retrieves all submissions filtered by its either the module id, student id, or exam id
	 * @params {Submission} obtains either submission id, student id or module id from the submission object
	 * @return {List<Submission>} returns a list of submissions filtered by the relevance if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Submission> getSubmissionsByRelevance(Submission submission) throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getByRelevance(submission);
	}

	/*
	 * getSubmissions : retrieves all available submissions
	 * @return {List<Submission>} returns a list of submissions if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException {
		SubmissionConnector sc = new SubmissionConnector();
		return sc.getAll();
	}

	/*
	 * addUser : This will add a user into the databse and will not have any refference tables
	 * @params {User}
	 * @return {boolen} returns true if the user was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.add(user);
	}

	/*
	 * updateUser : This will update a paticular user reffered by the user id
	 * @params {User} 
	 * @return {boolen} returns true if the user was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.update(user);
	}

	/*
	 * removeUser : This will a paticular user from the databse along with all the related submissions
	 * @params {User}
	 * @return {boolen} returns true if the user was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.remove(user);
	}

	/*
	 * getUser : retrieves a paticular user by its id
	 * @params {User} obtains user id from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.get(user);
	}
	
	/*
	 * getUserByCredentials : retrieves a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public User getUserByCredentials(User user) throws RemoteException, ClassNotFoundException, SQLException{
		UserConnector uc = new UserConnector();
		return uc.getbyCredentials(user);
	}

	/*
	 * getUsers : retrieves all available users
	 * @return {List<User>} returns a list of users if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	@Override
	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		UserConnector uc = new UserConnector();
		return uc.getAll();
	}

	/*
	 * getUsersByType : retrieves all available users filtered by either students, lecturers or administrators
	 * @params {User} obtains user role from the user object
	 * @return {List<User>} returns a list of filtered users by type if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<User> getUsersByType(User user) throws RemoteException, ClassNotFoundException, SQLException{
		UserConnector uc = new UserConnector();
		return uc.getByType(user);
	}
	
	/*
	 * isUserAvailable : retrieves a boolean to whether a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {boolean} returns a user object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public boolean isUserAvailable(User user) throws RemoteException, ClassNotFoundException, SQLException{
		LoginConnector lc = new LoginConnector();
		return lc.authenticateUser(user);
	}
	
	/*
	 * encrypt : encrypts the password text provided by the user using combined algorithms and receives the encrypted password in return
	 * @params {User} obtains username and password from the user object
	 * @return {String} returns the encrypted password
	 * @throws RemoteException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, NoSuchProviderException
	 */
	public String encrypt(User user) throws RemoteException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, NoSuchProviderException{
		return Encryptor.getEncryptedPassword(user);
	}
	
}
