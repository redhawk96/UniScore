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
	 * The remote interface is required to be present on both client and server projects under the same package name. 
	 * UniScore class consist of all the implemented methods
	 */

	/*
	 * Checks for the connection status to the databse
	 * @return {boolen} returns true if the connection to database is established and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean getServer() throws RemoteException, ClassNotFoundException, SQLException;
	

	/*
	 * Starting declaration of activity methods  
	 * ActivityLog is maintained to track user activity when using the application
	 * Activity can only be added, this is to avoid data record manipulation
	 */
	
	/*
	 * addActivityLog : This will add a new user triggerd activity into the databse
	 * @params {Activity} 
	 * @return {boolen} returns true if the activity was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getLogActivity : retrieves a paticular activity by its id
	 * @params {Activity} Obtains activity id from the activity object
	 * @return {Activity} returns an activity object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Activity getLogActivity(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getLogActivitiesByUser : retrieves all available activities filtered by a paticular user
	 * @params {Activity} Obtains user id from the activity object
	 * @return {List<Activity>} returns a list of activities of a paticular user if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Activity> getLogActivitiesByUser(Activity activity) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getLogActivities : retrieves all available activities
	 * @return {List<Activity>} returns a list activities of if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Activity> getLogActivities() throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Ending declaration of activity methods
	 */
	
	
	/*
	 * Starting declaration of exam methods  
	 * Exams are assigned to a module which is created by an administrator 
	 * Exams can only be added, updated or removed by an administrator
	 */
	
	/*
	 * addExam : This is will add a new exam into the database reffered to a paticular module
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * updateExam : This will update a paticular exam reffered by the exam id
	 * @params {Exam} 
	 * @return {boolen} returns true if the exam was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean updateExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * removeExam : This will remove a paticular exam from the databse along with all the submission related to it
	 * @params {Exam}
	 * @return {boolen} returns true if the exam was added removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean removeExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getExam : retrieves a paticular exam by its id
	 * @params {Exam} obtains exam id from exam object
	 * @return {Exam} returns an exam object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Exam getExam(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getExamsByModule : retrieves all available exams filtered by a paticular module
	 * @params {Exam} obtains module id from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Exam> getExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getAvailableExamsByModule : retrieves all available exams filtered by a paticular module and active status
	 * @params {Exam} obtains module id and status from exam object
	 * @return {List<Exam>} returns a list of exams of a paticular module with an active status if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Exam> getAvailableExamsByModule(Exam exam) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getExams : retrieves all available exams
	 * @return {List<Exam>} returns a list of exams if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Exam> getExams() throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * Ending declaration of exam methods
	 */
	
	
	/*
	 * Starting declaration of grade methods
	 * Grades are which a student is presented with when an exam is submitted, eg: Grade is A and the pass mark is 85
	 * Grades can only be added or updated by an administrator
	 */

	/*
	 * addGrade : This will add a new grade into the database and will not have any refferance tables
	 * @params {Grade}
	 * @return {boolen} returns true if the grade was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * updateGrade : This will update a paticular grade's pass-mark reffered by the grade
	 * @params {Grade} 
	 * @return {boolen} returns true if the grade was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean updateGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * getGrade : retrieves a paticular grade by the pass mark (Eg : A will retrieve, grade - A, if passmark > 85)
	 * @params {Grade} obtains pass mark from the grade object
	 * @return {Grade} returns a grade object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Grade getGrade(Grade grade) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getGrades : retrieves all available grades
	 * @return {List<Grade>} returns a list grades of if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Grade> getGrades() throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * Ending declaration of grade methods
	 */

	
	/*
	 * Starting declaration of module methods
	 * Modules are the subjects which a semester is made-up of
	 * Modules can only be added, updated or removed by an administrators
	 * Modules are be assigned to a teacher by an administrator
	 */

	/*
	 * addModule : This will add a module into the database reffered by the year and semester
	 * @params {Module}
	 * @return {boolen} returns true if the module was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * updateModule :  This will update a paticular module reffered by the module id
	 * @params {Module}
	 * @return {boolen} returns true if the module was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean updateModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * removeModule : This will remove a paticular module from the database along with the associated exams and submissions related to it
	 * @params {Module}
	 * @return {boolen} returns true if the module was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean removeModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * getModule : retrieves a paticular module by its id
	 * @params {Module} obtains module id from the module object
	 * @return {Module} returns a module object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Module getModule(Module module) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * getModules : retrieves all available modules
	 * @return {List<Module>} returns a list of modules if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Module> getModules() throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * Ending declaration of module methods
	 */
	
	
	/*
	 * Starting declaration of question methods 
	 * Questions are the which an exam is made-up of
	 * Once an administrator has added a module and added a exam in refference to a module, teacher will be able to add questions to it
	 */
	
	/*
	 * addQuestion : This will add a question into the databse reffered by a module
	 * @params {Question}
	 * @return {boolen} returns true if the question was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * updateModule : This will update a paticular question reffered by the question id
	 * @params {Question} 
	 * @return {boolen} returns true if the question was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * removeQuestion : This will remove a paticular question from the database
	 * @params {Question}
	 * @return {boolen} returns true if the question was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getQuestion : retrieves a paticular question by its id
	 * @params {Question} obtains questions id from the question object
	 * @return {Question} returns question object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Question getQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getQuestions : retrieves all available questions
	 * @return {List<Question>} returns a list of questions if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Question> getQuestions() throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getExamQuestions : retrieves all available questions for the paticular exam
	 * @params {Question} Obtains exam id from question object
	 * @return {List<Question>} returns a list of questions for a paticular exam if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Question> getExamQuestions(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Ending declaration of question methods
	 */
	
	
	/*
	 * Starting declaration of submission methods 
	 * Submissions are the answers provided by students to a paticular exam, and can only be submitted once
	 * Submissions can only be added by a student, and other user types will only have the ability to view submissions
	 */
	
	/*
	 * addSubmission : This will add a submission into the databse reffered by exam and module
	 * @params {Submission}
	 * @return {boolen} returns true if the submission was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getSubmission : retrieves a paticular submission by its id
	 * @params {Submission} obtains submission id from the submission object
	 * @return {Submission} returns submission object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public Submission getSubmission(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getSubmissionsByRelevance : retrieves all submissions filtered by its either the module id, student id, or exam id
	 * @params {Submission} obtains either submission id, student id or module id from the submission object
	 * @return {List<Submission>} returns a list of submissions filtered by the relevance if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Submission> getSubmissionsByRelevance(Submission submission) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getSubmissions : retrieves all available submissions
	 * @return {List<Submission>} returns a list of submissions if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<Submission> getSubmissions() throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * Ending declaration of submission methods
	 */

	
	/*
	 * Starting declaration of user methods 
	 * Three types of users are availabe in the databsse as students, teacher and administrator and will have different access level corresponding the the user type 
	 */

	/*
	 * addUser : This will add a user into the databse and will not have any refference tables
	 * @params {User}
	 * @return {boolen} returns true if the user was added to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean addUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * updateUser : This will update a paticular user reffered by the user id
	 * @params {User} 
	 * @return {boolen} returns true if the user was updated to the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * removeUser : This will a paticular user from the databse along with all the related submissions
	 * @params {User}
	 * @return {boolen} returns true if the user was removed from the database and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	/*
	 * getUser : retrieves a paticular user by its id
	 * @params {User} obtains user id from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public User getUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getUserByCredentials : retrieves a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public User getUserByCredentials(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getUsers : retrieves all available users
	 * @return {List<User>} returns a list of users if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<User> getUsers() throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * getUsersByType : retrieves all available users filtered by either students, lecturers or administrators
	 * @params {User} obtains user role from the user object
	 * @return {List<User>} returns a list of filtered users by type if found and null if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public List<User> getUsersByType(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Ending declaration of user methods
	 */
	
	
	/*
	 * Starting declaration on common methods
	 */
	
	/*
	 * isUserAvailable : retrieves a boolean to whether a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {boolean} returns a true if user found and false if not
	 * @throws RemoteException, ClassNotFoundException, SQLException
	 */
	public boolean isUserAvailable(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/*
	 * Ending declaration of common methods
	 */
}
