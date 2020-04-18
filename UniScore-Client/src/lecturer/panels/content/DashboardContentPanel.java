package lecturer.panels.content;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.panels.ContentPanel;
import com.panels.content.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.LogoutNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import models.Activity;
import models.User;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {

	private JPanel contentPanel = new JPanel();
	private JPanel dashboardBodyPanel = new JPanel();
	private Integer moduleCount, studentCount, examCount;
	
	public DashboardContentPanel() {
		setContentPanel();
	}

	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addDisplayCards();
	}
	
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		dashboardBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		dashboardBodyPanel.setBounds(0, 66, 1256, 845);
		contentPanel.add(dashboardBodyPanel);
		dashboardBodyPanel.setLayout(null);
	}
	
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
	
	private void addDisplayCards() {
		
		try {
			
			moduleCount = UniScoreClient.uniscoreInterface.getModuleCountByUser(UniScoreClient.authUser);
			
			User user = new User();
			user.setRole("Student");
			studentCount = UniScoreClient.uniscoreInterface.getUserCountByRole(user);
			
			examCount = UniScoreClient.uniscoreInterface.getExamCountByModules(UniScoreClient.authUser);
			
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
		
		JLabel dashboardBgLabel = new JLabel("");
		dashboardBgLabel.setIcon(new ImageIcon(DashboardContentPanel.class.getResource("/resources/dashboard-background.png")));
		dashboardBgLabel.setBounds(-36, 146, 1336, 699);
		dashboardBodyPanel.add(dashboardBgLabel);

		JPanel moduleCard = new JPanel();
		moduleCard.setBounds(31, 0, 270, 90);
		dashboardBodyPanel.add(moduleCard);
		moduleCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
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
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
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
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new LogoutNavigationPanel();
				LecturerPanel.setSelectedPanel();
				
				try {
					
					UniScoreClient.uniscoreInterface.addLogActivity(new Activity(getFormatedLecturerId(UniScoreClient.authUser)+" has ended session from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
				
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
	}
	
	private String refactorStatFigures(Integer statFigure) {
		if(statFigure.toString().length() < 2) {
			return "0"+statFigure.toString();
		} else {
			return statFigure.toString();
		}
	} 
	
	private String getFormatedLecturerId(User user) {
		String userId = "L";
		
		switch(user.getUserId().length()) {
			case 1 : userId = userId.concat("00000").concat(user.getUserId()); break;
			case 2 : userId = userId.concat("0000").concat(user.getUserId()); break;
			case 3 : userId = userId.concat("000").concat(user.getUserId()); break;
			case 4 : userId = userId.concat("00").concat(user.getUserId()); break;
			case 5 : userId = userId.concat("0").concat(user.getUserId()); break;
		}
		return userId;
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
