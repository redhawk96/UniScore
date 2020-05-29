/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.panels.ContentPanel;
import com.utils.BarChartPanel;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.Identification;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.LogoutNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import models.Activity;
import models.Exam;
import models.Module;
import models.Submission;
import models.User;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {
	
	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();
	
	// Declaring and initializing new JPanel to act as an wrapper to contain card JPanels
	private JPanel dashboardBodyPanel = new JPanel();
		
	// Declaring properties to store total count of allocated modules for the logged in lecturer, all registered students, and exams based on allocated modules
	private Integer moduleCount, studentCount, examCount;
	
	/*
	 * DashboardContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 */
	public DashboardContentPanel() {
		// Adding elements to the ContentPanel
		setContentPanel();
	}

	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addDisplayCards();
	}
	
	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color
	 */
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		dashboardBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		dashboardBodyPanel.setBounds(0, 66, 1256, 845);
		contentPanel.add(dashboardBodyPanel);
		dashboardBodyPanel.setLayout(null);
	}
	
	/*
	 * Method addNavigationIndicator adds UI layout(styling) to navigationIndicatorPanel which shows the current navigated panel on the top of ContentPanel
	 * navigationIndicatorPanel is a sub element under ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color, JLabel text/text-color/font-size/boundaries 
	 */
	private void addNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(1073, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Home");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1138, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 51, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	/*
	 * Method addDisplayCards add cards(custom element) UI layout(styling) to dashboardBodyPanel
	 * addDisplayCards is a sub element under dashboardBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries
	 */
	private void addDisplayCards() {
		
		try {
			
			// Retrieves the number of allocated modules for the signed in lecturer from the database	
			moduleCount = UniScoreClient.uniscoreInterface.getModuleCountByUser(UniScoreClient.authUser);
			
			/*
			 * Method getUserCountByRole() only accepts an User object, hence creating a User object to set the role.
			 * In this case, we need to get the total count of active students, hence setting the role to student
			 * In return we recieve the total count of students from the database up-todate
			 */
			User user = new User();
			user.setRole("Student");
			studentCount = UniScoreClient.uniscoreInterface.getUserCountByRole(user);
			
			//  Retrieves the number of exams which is categorized by allocated modules for the signed-in lecturer
			examCount = UniScoreClient.uniscoreInterface.getExamCountByModules(UniScoreClient.authUser);
			

	 	/*
	 	 * If there was exception thrown when executing the retrieval of total count of either module, students or exams, 
	 	 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
	 	 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		}

		JPanel moduleCard = new JPanel();
		moduleCard.setBounds(31, 0, 270, 90);
		dashboardBodyPanel.add(moduleCard);
		moduleCard.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be navigated to ModuleContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new ModuleNavigationPanel();
				LecturerPanel.selectedContent = new ModuleContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		moduleCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		moduleCard.setLayout(null);
		moduleCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		
		JPanel moduleCardTextPanel = new JPanel();
		moduleCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		moduleCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardTextPanel);
		moduleCardTextPanel.setLayout(null);
		
		JLabel moduleCardText = new JLabel("MODULES");
		moduleCardText.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText.setIcon(null);
		moduleCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		moduleCardTextPanel.add(moduleCardText);
		
		JLabel moduleCardStatNumber = new JLabel(refactorStatFigures(moduleCount));
		moduleCardStatNumber.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		moduleCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		moduleCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		moduleCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardStatNumber);
		
		JPanel studentCard = new JPanel();
		studentCard.setBounds(344, 0, 270, 90);
		dashboardBodyPanel.add(studentCard);
		studentCard.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be navigated to StudentContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				LecturerPanel.selectedNavigation = new StudentNavigationPanel();
				LecturerPanel.selectedContent = new StudentContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		studentCard.setLayout(null);
		studentCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		studentCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		
		JPanel studentCardTextPanel = new JPanel();
		studentCardTextPanel.setLayout(null);
		studentCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		studentCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCard.add(studentCardTextPanel);
		
		JLabel studentCardText = new JLabel("STUDENTS");
		studentCardText.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		studentCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCardTextPanel.add(studentCardText);
		
		JLabel studentCardStatNumber = new JLabel(refactorStatFigures(studentCount));
		studentCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		studentCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		studentCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		studentCard.add(studentCardStatNumber);
		
		JPanel examCard = new JPanel();
		examCard.setBounds(651, 0, 270, 90);
		dashboardBodyPanel.add(examCard);
		examCard.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be navigated to ExamContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				LecturerPanel.selectedNavigation = new ExamNavigationPanel();
				LecturerPanel.selectedContent = new ExamContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		examCard.setLayout(null);
		examCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		
		JPanel examCardTextPanel = new JPanel();
		examCardTextPanel.setLayout(null);
		examCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardTextPanel);
		
		JLabel examCardText = new JLabel("EXAMS");
		examCardText.setHorizontalAlignment(SwingConstants.CENTER);
		examCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		examCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCardTextPanel.add(examCardText);
		
		JLabel examCardStatNumber = new JLabel(refactorStatFigures(examCount));
		examCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		examCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		examCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		examCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardStatNumber);
		
		JPanel logoutCard = new JPanel();
		logoutCard.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be logged out from the system on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					// Adding a record to the database of the system sing-off by the lecturer followed by lecturer id under activities table
					UniScoreClient.uniscoreInterface.addLogActivity(new Activity(Identification.getFormatedId(UniScoreClient.authUser.getUserId(), "L")+" has ended session from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
				
				/*
			 	 * If there was exception thrown when recording the signing-off the lecturer
			 	 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
			 	 */
				} catch (RemoteException ex) {
					ErrorNotifier en = new ErrorNotifier("Failed to terminate connection with the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.REMOTE);
					en.setVisible(true);
					System.out.println("RemoteException execution thrown on DashboardContentPanel.java file. Error : "+ex.getCause());
				} catch (ClassNotFoundException ex) {
					ErrorNotifier en = new ErrorNotifier("Failed to terminate connection with the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on DashboardContentPanel.java file. Error : "+ex.getCause());
				} catch (SQLException ex) {
					ErrorNotifier en = new ErrorNotifier("Failed to terminate connection with the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.SQL);
					en.setVisible(true);
					System.out.println("SQLException execution thrown on DashboardContentPanel.java file. Error : "+ex.getCause());
				}

				LecturerPanel.selectedNavigation = new LogoutNavigationPanel();
				LecturerPanel.setSelectedPanel();
				
				/*
				 * Setting the auth user to null inorder to invalidate cookie values
				 * Opening up LoginPanel inoder for future login
				 * Disposing/removing lecturerPanel from the desktop
				 */
				UniScoreClient.authUser = null;
				UniScoreClient.loginPanel = new LoginPanel();
				UniScoreClient.loginPanel.setVisible(true);
				UniScoreClient.lecturerPanel.dispose();
			}
		});
		logoutCard.setBounds(958, 0, 270, 90);
		dashboardBodyPanel.add(logoutCard);
		logoutCard.setLayout(null);
		logoutCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		
		JPanel logoutCardTextPanel = new JPanel();
		logoutCardTextPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		logoutCardTextPanel.setLayout(null);
		logoutCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		logoutCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		logoutCard.add(logoutCardTextPanel);
		
		JLabel logoutCardText = new JLabel("LOGOUT");
		logoutCardText.setHorizontalAlignment(SwingConstants.CENTER);
		logoutCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		logoutCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		logoutCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		logoutCardTextPanel.add(logoutCardText);
		
		JLabel logoutCardIcon = new JLabel("");
		logoutCardIcon.setIcon(new ImageIcon(DashboardContentPanel.class.getResource("/resources/logout_icon.png")));
		logoutCardIcon.setHorizontalAlignment(SwingConstants.CENTER);
		logoutCardIcon.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		logoutCardIcon.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		logoutCardIcon.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		logoutCard.add(logoutCardIcon);
		
		JLabel loogedInIPAddressLabel = new JLabel("  IP Address  :  "+UniScoreClient.authLocation);
		loogedInIPAddressLabel.setIcon(new ImageIcon(DashboardContentPanel.class.getResource("/resources/active_icon.png")));
		loogedInIPAddressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loogedInIPAddressLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		loogedInIPAddressLabel.setBounds(956, 130, 270, 14);
		dashboardBodyPanel.add(loogedInIPAddressLabel);
		
		
		try { 
			
			// Method getModulesByRelevance accepts a module object contaning userid(in this case lecturer id) to retrieve allocated modules 
			Module module = new Module();
			module.setTeacherId(UniScoreClient.authUser.getUserId());
			
			// Retrieving the allocated module list for the singed in lecturer from the databse
			List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, 0, 0);
			
			// count property initialized to 0 inorder to plot the chart with only 1 exam
			int count = 0;
			
			Submission recentExamStats = new Submission();
			String recentExamName = "";
			
			// Looping through the retrieved allocated module list using a foreach loop to get all the exams 
			for (Module mod : moduleList) {
				
				Exam tempExam = new Exam();
				tempExam.setModuleId(mod.getModuleId());
						
				// Retrieving the exam list for the module in loop
				List<Exam> examList = (List<Exam>) UniScoreClient.uniscoreInterface.getExamsByModule(tempExam);
				
				// Looping through the retrieved exam list using a foreach loop to get the most recent exam which has already started or finished
				for(Exam e : examList) {
					if((e.getStatus().equalsIgnoreCase("Started") || e.getStatus().equalsIgnoreCase("Finished")) && count == 0) {
						// Getting the module id and exam name of the most recent exam to set title for the bar chart
						recentExamName = e.getModuleId()+" - "+e.getExamName();
						// Setting the exam id of the most recent, started/finished exam id to generate a dataset
						recentExamStats.setExamId(e.getExamId());
						// Setting count to 1 in the first loop itself inorder to avoid another loop  
						count++;
					}
				}		
			}
			
			// Plotting a bar chart of the student marks for the most recent allocated exam 
			BarChartPanel examMarkStats = new BarChartPanel( recentExamName + " Exam Statistics", "Score Range", "No of Students", UniScoreClient.uniscoreInterface.getSubmissionDatasetByExam(recentExamStats));
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(examMarkStats.getChart());
			lblNewLabel.setBounds(31, 169, 1197, 657);
			dashboardBodyPanel.add(lblNewLabel);
			
		/*
	 	 * If there was exception thrown when plotting the graph,
	 	 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
	 	 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		}
	}
	
	/*
	 * Method refactorStatFigures is implemented to format the stat figures on card elements to 2 integers
	 * @param statFigure contains an integer to be formatted to 2 integer places 
	 * @returns a formatted number with 2 integers, if 1 is inputed, method will return 01
	 */
	private String refactorStatFigures(Integer statFigure) {
		if(statFigure.toString().length() < 2) {
			return "0"+statFigure.toString();
		} else {
			return statFigure.toString();
		}
	} 

	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * @returns JPanel 	Contains completed layout of with the add sub elements 
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
