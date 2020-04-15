package lecturer.panels.content;

import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.panels.ContentPanel;
import com.panels.content.ErrorNotifier;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.LogoutNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import models.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel dashboardBodyPanel = new JPanel();
	Integer moduleCount, studentCount, examCount;
	
	public DashboardContentPanel() {
		
		try {
			
			moduleCount = UniScoreClient.uniscoreInterface.getModuleCountByUser(UniScoreClient.authUser);
			
			User user = new User();
			user.setRole("Student");
			studentCount = UniScoreClient.uniscoreInterface.getUserCountByRole(user);
			
			examCount = UniScoreClient.uniscoreInterface.getExamCountByModules(UniScoreClient.authUser);
			
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve statistical figures.\nError refferance : 500");
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DashboardContentPanel.java file. Error : "+e.getCause());
		}
		
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("dashboard");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);

		setDashboardBody();
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
	
	
	public void setNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.NAVIGATION_INDICATOR_PANEL_BACKGRIOUND_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(1073, 8, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Home");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(1138, 8, 51, 17);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setDashboardBody() {
		
		setNavigationIndicator();
		
		dashboardBodyPanel.setBackground(Color.WHITE);
		dashboardBodyPanel.setBounds(0, 66, 1256, 845);
		contentPanel.add(dashboardBodyPanel);
		dashboardBodyPanel.setLayout(null);
		
		displayCards();
	}
	
	
	private void displayCards() {
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
		moduleCard.setBorder(UI.CARD_BORDER);
		moduleCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		moduleCard.setLayout(null);
		moduleCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		
		JPanel moduleCardTextPanel = new JPanel();
		moduleCardTextPanel.setBorder(UI.CARD_BORDER);
		moduleCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		moduleCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardTextPanel);
		moduleCardTextPanel.setLayout(null);
		
		JLabel moduleCardText = new JLabel("MODULES");
		moduleCardText.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText.setIcon(null);
		moduleCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		moduleCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardTextPanel.add(moduleCardText);
		
		JLabel moduleCardStatNumber = new JLabel(refactorStatFigures(moduleCount));
		moduleCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		moduleCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
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
		studentCard.setBorder(UI.CARD_BORDER);
		studentCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		studentCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		
		JPanel studentCardTextPanel = new JPanel();
		studentCardTextPanel.setLayout(null);
		studentCardTextPanel.setBorder(UI.CARD_BORDER);
		studentCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		studentCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCard.add(studentCardTextPanel);
		
		JLabel studentCardText = new JLabel("STUDENTS");
		studentCardText.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		studentCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		studentCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCardTextPanel.add(studentCardText);
		
		JLabel studentCardStatNumber = new JLabel(refactorStatFigures(studentCount));
		studentCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		studentCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
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
		examCard.setBorder(UI.CARD_BORDER);
		examCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		examCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		
		JPanel examCardTextPanel = new JPanel();
		examCardTextPanel.setLayout(null);
		examCardTextPanel.setBorder(UI.CARD_BORDER);
		examCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		examCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardTextPanel);
		
		JLabel examCardText = new JLabel("EXAMS");
		examCardText.setHorizontalAlignment(SwingConstants.CENTER);
		examCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		examCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCardTextPanel.add(examCardText);
		
		JLabel examCardStatNumber = new JLabel(refactorStatFigures(examCount));
		examCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		examCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		examCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardStatNumber);
		
		JPanel logoutCard = new JPanel();
		logoutCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new LogoutNavigationPanel();
				LecturerPanel.setSelectedPanel();
				UniScoreClient.authUser = null;
				UniScoreClient.loginPanel = new LoginPanel();
				UniScoreClient.loginPanel.setVisible(true);
				UniScoreClient.lecturerPanel.dispose();
			}
		});
		logoutCard.setBounds(958, 0, 270, 90);
		dashboardBodyPanel.add(logoutCard);
		logoutCard.setLayout(null);
		logoutCard.setBorder(UI.CARD_BORDER);
		logoutCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		
		JPanel logoutCardTextPanel = new JPanel();
		logoutCardTextPanel.setLayout(null);
		logoutCardTextPanel.setBorder(UI.CARD_BORDER);
		logoutCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		logoutCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		logoutCard.add(logoutCardTextPanel);
		
		JLabel logoutCardText = new JLabel("LOGOUT");
		logoutCardText.setHorizontalAlignment(SwingConstants.CENTER);
		logoutCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		logoutCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		logoutCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		logoutCardTextPanel.add(logoutCardText);
		
		JLabel logoutCardIcon = new JLabel("");
		logoutCardIcon.setIcon(new ImageIcon(DashboardContentPanel.class.getResource("/resources/logout_icon.png")));
		logoutCardIcon.setHorizontalAlignment(SwingConstants.CENTER);
		logoutCardIcon.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		logoutCardIcon.setFont(UI.CARD_LABEL_TEXT_FONT);
		logoutCardIcon.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		logoutCard.add(logoutCardIcon);
		
		JLabel dashboardBgLabel = new JLabel("");
		dashboardBgLabel.setIcon(new ImageIcon(DashboardContentPanel.class.getResource("/resources/dashboard-bg.png")));
		dashboardBgLabel.setBounds(-11, 145, 1300, 700);
		dashboardBodyPanel.add(dashboardBgLabel);
		
		JLabel loogedInIPAddressLabel = new JLabel("IP Address : "+UniScoreClient.authLocation);
		loogedInIPAddressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loogedInIPAddressLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		loogedInIPAddressLabel.setBounds(958, 130, 270, 14);
		dashboardBodyPanel.add(loogedInIPAddressLabel);
	}
	
	private String refactorStatFigures(Integer statFigure) {
		if(statFigure.toString().length() < 2) {
			return "0"+statFigure.toString();
		} else {
			return statFigure.toString();
		}
	} 
}
