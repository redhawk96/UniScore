/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package main.panels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.NavigationPanel;
import com.utils.UI;

import lecturer.panels.content.DashboardContentPanel;
import lecturer.panels.navigation.DashboardNavigationPanel;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.LogoutNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.AvatarPanel;
import lecturer.panels.navigation.QuestionNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;
import lecturer.panels.navigation.NamePanel;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class LecturerPanel extends JFrame {

	/*
	 * Declaring the NavigationPanels on left side of the application which is used to navigate to different content panels 
	 * To identify the active navigation, NavigationPanel type arraylist(navigationPanelList) is implemented
	 */
	private DashboardNavigationPanel dashboardNavigationPanel = new DashboardNavigationPanel();
	private ModuleNavigationPanel moduleNavigationPanel = new ModuleNavigationPanel();
	private NavigationPanel studentNavigationPanel = new StudentNavigationPanel();
	private NavigationPanel questionNavigationPanel = new QuestionNavigationPanel();
	private NavigationPanel examNavigationPanel = new ExamNavigationPanel();
	private NavigationPanel logoutNavigationPanel = new LogoutNavigationPanel();
	private static ArrayList<NavigationPanel> navigationPanelList;
	public static NavigationPanel selectedNavigation;
	
	/*
	 * Declaring the ContentPanels on right side of the application which consists of dynamic data retrieved from the databse
	 * To identify the active content panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	public static ContentPanel selectedContent;
	
	// Avatar and auth user name components
	private AvatarPanel avatar = new AvatarPanel();
	private NamePanel authUserName = new NamePanel();
	private static JPanel rightSidePanel;
	
	/*
	 * LecturerPanel method : used to initialize JFrame, UI elements to style and support functionality of JFrame implemented here  
	 */
	public LecturerPanel() {

		// Setting LecturerPanel JFrame's icon
		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());
		
		// Setting JFrame title text
		setTitle("UNISCORE | Lecturer");
		
		// Setting the size of the application screen
		setSize(UI.APPLICATION_PRIMARY_FRAME_WIDTH, UI.APPLICATION_PRIMARY_FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// setLocationRelativeTo set to null inorder to start the application center of the screen
		setLocationRelativeTo(null); 
		
		// Disabling frame resizing
		setResizable(false);

		// Adding left-side JPanel which is on the left side of the application. Used as the application's navigation panel
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		leftSidePanel.setBounds(UI.NAVIGATION_PANEL_X_AXIS, UI.NAVIGATION_PANEL_Y_AXIS, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_HEIGHT);
		getContentPane().add(leftSidePanel);
		leftSidePanel.setLayout(null);

		// Adding avatar and auth user's name to left-side JPanel
		leftSidePanel.add(avatar.getAvatar());
		leftSidePanel.add(authUserName.getName());

		/*
		 * Adding the navigation panels to an ArrayList.
		 * setSelectedNavigationPanel method has the implementation to loop through the arraylist to set color to selected-navigation panel and set relevant content panel accordingly  
		 */
		navigationPanelList = new ArrayList<NavigationPanel>();
		navigationPanelList.add(dashboardNavigationPanel);
		navigationPanelList.add(moduleNavigationPanel);
		navigationPanelList.add(studentNavigationPanel);
		navigationPanelList.add(questionNavigationPanel);
		navigationPanelList.add(examNavigationPanel);
		navigationPanelList.add(logoutNavigationPanel);
		
		// Adding navigation JPanels to left-side JPanel
		leftSidePanel.add(dashboardNavigationPanel.getNavigation());
		leftSidePanel.add(moduleNavigationPanel.getNavigation());
		leftSidePanel.add(studentNavigationPanel.getNavigation());
		leftSidePanel.add(questionNavigationPanel.getNavigation());
		leftSidePanel.add(examNavigationPanel.getNavigation());
		leftSidePanel.add(logoutNavigationPanel.getNavigation());

		// Adding left-side JPanel which is on the right side of the application. Used as the application's content panel
		rightSidePanel = new JPanel();
		rightSidePanel.setBounds(UI.NAVIGATION_PANEL_WIDTH, 0, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);

		// Setting dashboard as root component on both navigation and content on application startup
		LecturerPanel.selectedNavigation = new DashboardNavigationPanel();
		LecturerPanel.selectedContent = new DashboardContentPanel();
		setSelectedPanel();
	}

	/*
	 * setSelectedPanel method : used to call setSelectedNavigationPanel and setSelectedContentPanel methods to keep implemention simple and abstract(reduce complexity)
	 */
	public static void setSelectedPanel() {
		// Calling setSelectedNavigationPanel method to set color to selected NavigationPanel
		setSelectedNavigationPanel();
		
		// Calling setSelectedContentPanel method to set relevant ContentPanel according to the NavigationPanel selected
		setSelectedContentPanel();
	}

	/*
	 * setSelectedNavigationPanel method : used to set the selected navigation panel, set one color to the selected navigation panel and repaint rest with another color
	 * This method is implemented to enhance UX   
	 */
	private static void setSelectedNavigationPanel() {
		// Executing a foreach loop to set the color of the selected navigation panel, if block of the selected navigation and else block for the rest
		for (NavigationPanel NavigationPanel : navigationPanelList) {
			/*
			 * Selected navigation is cross-checked against the existing static nagivation panels, inorder to avoid creation of multiple navigationPanels
			 * NavigationPanel's (JPanel) name is used as a unique key to identify the navigationPanels 
			 */
			if (NavigationPanel.getNavigation().getName().toString().equalsIgnoreCase(LecturerPanel.selectedNavigation.getNavigation().getName().toString())) {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_SELECTED_BUTTON_COLOR);
			} else {
				NavigationPanel.getNavigation().setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
		}
	}
	
	
	/*
	 * setSelectedContentPanel method : used to remove the contentPanel in currently use and add the ContentPanel which is relevant to the selected navigation panel 
	 */
	private static void setSelectedContentPanel() {
		// Setting the relevant content panel according to the selected navigation panel
		rightSidePanel.removeAll();
		rightSidePanel.add(LecturerPanel.selectedContent.getContent());
		rightSidePanel.repaint();
	}
}
