/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package main.panels;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.NavigationPanel;
import com.utils.UI;

import admin.panels.content.DashboardContentPanel;
import admin.panels.navigation.DashboardNavigationPanel;
import admin.panels.navigation.ExamsNavigationPanel;
import admin.panels.navigation.LecturerNavigationPanel;
import admin.panels.navigation.LogoutNavigationPanel;
import admin.panels.navigation.ModulesNavigationPanel;
import admin.panels.navigation.NavigationUserAvatar;
import admin.panels.navigation.StudentNavigationPanel;

import java.util.ArrayList;

@SuppressWarnings({ "serial" })
public class AdminPanel extends JFrame {
	/*
	 * Declaring the NavigationPanels on left side of the application which is used to navigate to different content panels 
	 * To identify the active navigation, NavigationPanel type arraylist(navigationPanelList) is implemented
	 */
	private DashboardNavigationPanel dashboardNavigationPanel = new DashboardNavigationPanel();
	private NavigationPanel logoutNavigationPanel = new LogoutNavigationPanel();
	private NavigationPanel lecturerNavigationPanel = new LecturerNavigationPanel();
	private NavigationPanel modulesNavigationPanel = new ModulesNavigationPanel();
	private NavigationPanel studentNavigationPanel = new StudentNavigationPanel();
	private NavigationPanel examsNavigationPanel = new ExamsNavigationPanel();
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

	public AdminPanel() {

		/*
		 * Setting JFrame title text
		 */
		setTitle("UNISCORE | Admin");
		
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
		navigationPanelList.add(modulesNavigationPanel);
		navigationPanelList.add(lecturerNavigationPanel);
		navigationPanelList.add(studentNavigationPanel);
		navigationPanelList.add(examsNavigationPanel);
		navigationPanelList.add(logoutNavigationPanel);
		
		
		/*
		 * Adding navigation JPanels to left-side JPanel
		 */
		leftSidePanel.add(dashboardNavigationPanel.getNavigation());
		leftSidePanel.add(modulesNavigationPanel.getNavigation());
		leftSidePanel.add(lecturerNavigationPanel.getNavigation());
		leftSidePanel.add(studentNavigationPanel.getNavigation());
		leftSidePanel.add(examsNavigationPanel.getNavigation());
		leftSidePanel.add(logoutNavigationPanel.getNavigation());
		

		// Adding left-side JPanel which is on the right side of the application. Used as the application's content panel
		rightSidePanel = new JPanel();
		rightSidePanel.setBounds(UI.NAVIGATION_PANEL_WIDTH, 0, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);

		/*
		 * Setting dashboard as root component on both navigation and content on application startup
		 */
		AdminPanel.selectedNavigation = new DashboardNavigationPanel();
		AdminPanel.selectedContent = new DashboardContentPanel();
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
			if (NavigationPanel.getNavigation().getName().toString().equalsIgnoreCase(AdminPanel.selectedNavigation.getNavigation().getName().toString())) {
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
		rightSidePanel.add(AdminPanel.selectedContent.getContent());
		rightSidePanel.repaint();
	}
}
