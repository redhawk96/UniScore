package main.panels;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.NavigationPanel;
import com.utils.UI;

import lecturer.panels.content.DashboardContentPanel;
import lecturer.panels.navigation.DashboardNavigationPanel;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.LogoutNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.NavigationUserAvatar;
import lecturer.panels.navigation.QuestionNavigationPanel;
import lecturer.panels.navigation.SettingsNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;

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
	private NavigationPanel settingsNavigationPanel = new SettingsNavigationPanel();
	private NavigationPanel logoutNavigationPanel = new LogoutNavigationPanel();
	private static ArrayList<NavigationPanel> navigationPanelList;
	public static NavigationPanel selectedNavigation;
	
	/*
	 * Declaring the ContentPanels on right side of the application which consists of dynamic data retrieved from the databse
	 * To identify the active content panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	public static ContentPanel selectedContent;
	
	/*
	 * User avatar icon component
	 */
	private NavigationUserAvatar avatar = new NavigationUserAvatar();

	private static JPanel rightSidePanel;
	
	public LecturerPanel() {

		/*
		 * Setting JFrame title text
		 */
		setTitle("UNISCORE | Lecturer");
		
		/*
		 * Setting the size of the application screen
		 */
		setSize(UI.APPLICATION_WIDTH, UI.APPLICATION_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		/*
		 * setLocationRelativeTo set to null inorder to start the application center of the screen
		 */
		setLocationRelativeTo(null); 
		
		/*
		 * Disabling frame resizing
		 */
		setResizable(false);

		/*
		 * Adding left-side JPanel which is on the left side of the application. Used as the application's navigation panel
		 */
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBackground(UI.NAVIGATION_PANEL_COLOR);
		leftSidePanel.setBounds(UI.NAVIGATION_PANEL_X_AXIS, UI.NAVIGATION_PANEL_Y_AXIS, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_HEIGHT);
		getContentPane().add(leftSidePanel);
		leftSidePanel.setLayout(null);

		/*
		 * Adding user avatar to left-side JPanel
		 */
		leftSidePanel.add(avatar.getAvatar());

		/*
		 * Adding the navigation panels to an ArrayList.
		 * updatePanels() has the implementation to loop through the arraylist to set color to selected-navigation panel and set relevant content panel accordingly  
		 * 
		 */
		navigationPanelList = new ArrayList<NavigationPanel>();
		navigationPanelList.add(dashboardNavigationPanel);
		navigationPanelList.add(moduleNavigationPanel);
		navigationPanelList.add(studentNavigationPanel);
		navigationPanelList.add(questionNavigationPanel);
		navigationPanelList.add(examNavigationPanel);
		navigationPanelList.add(settingsNavigationPanel);
		navigationPanelList.add(logoutNavigationPanel);
		
		/*
		 * Adding navigation JPanels to left-side JPanel
		 */
		leftSidePanel.add(dashboardNavigationPanel.getNavigation());
		leftSidePanel.add(moduleNavigationPanel.getNavigation());
		leftSidePanel.add(studentNavigationPanel.getNavigation());
		leftSidePanel.add(questionNavigationPanel.getNavigation());
		leftSidePanel.add(examNavigationPanel.getNavigation());
		leftSidePanel.add(settingsNavigationPanel.getNavigation());
		leftSidePanel.add(logoutNavigationPanel.getNavigation());

		/*
		 * Adding left-side JPanel which is on the right side of the application. Used as the application's content panel
		 */
		rightSidePanel = new JPanel();
		rightSidePanel.setBounds(UI.NAVIGATION_PANEL_WIDTH, 0, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);

		/*
		 * Setting dashboard as root component on both navigation and content on application startup
		 */
		LecturerPanel.selectedNavigation = new DashboardNavigationPanel();
		LecturerPanel.selectedContent = new DashboardContentPanel();
		setSelectedPanel();
	}

	public static void setSelectedPanel() {
		/*
		 * Calling setSelectedNavigationPanel() to set color to selected NavigationPanel
		 * Calling setSelectedContentPanel() to set relevant ContentPanel according to the NavigationPanel selected
		 */
		setSelectedNavigationPanel();
		setSelectedContentPanel();
	}

	
	private static void setSelectedNavigationPanel() {
		/*
		 * Executing a foreach loop to set the color of the selected navigation panel
		 */
		for (NavigationPanel NavigationPanel : navigationPanelList) {
			if (NavigationPanel.getNavigation().getName().toString().equalsIgnoreCase(LecturerPanel.selectedNavigation.getNavigation().getName().toString())) {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_SELECTED_BUTTON_COLOR);
			} else {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_BUTTON_COLOR);
			}
		}
	}
	
	
	private static void setSelectedContentPanel() {
		/*
		 * Setting the relevant content panel according to the selected navigation panel
		 */
		rightSidePanel.removeAll();
		rightSidePanel.add(LecturerPanel.selectedContent.getContent());
		rightSidePanel.repaint();
	}

	/*
	 * For development purposes only
	 */
	public static void main(String args[]) {
		LecturerPanel tp = new LecturerPanel();
		tp.setVisible(true);
	}
}
