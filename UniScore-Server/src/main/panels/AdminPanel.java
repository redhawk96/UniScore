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
import admin.panels.content.ExamsContentPanel;
import admin.panels.content.LecturerContentPanel;
import admin.panels.content.ModulesContentPanel;
import admin.panels.content.StudentContentPanel;
import admin.panels.navigation.DashboardNavigationPanel;
import admin.panels.navigation.ExamsNavigationPanel;
import admin.panels.navigation.LecturerNavigationPanel;
import admin.panels.navigation.LogoutNavigationPanel;
import admin.panels.navigation.ModulesNavigationPanel;
import admin.panels.navigation.NavigationUserAvatar;
import admin.panels.navigation.StudentNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings({ "serial" })
public class AdminPanel extends JFrame implements ActionListener {
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
	private ContentPanel dashboardContentPanel = new DashboardContentPanel();
	private static ArrayList<ContentPanel> contentPanelList = null;
	private ContentPanel lecturerContentPanel = new LecturerContentPanel();
	private ContentPanel modulesContentPanel = new ModulesContentPanel();
	private ContentPanel studentContentPanel = new StudentContentPanel();
	private ContentPanel examsContentPanel = new ExamsContentPanel();
	public static ContentPanel selectedContent;
	
	/*
	 * User avatar icon component
	 */
	private NavigationUserAvatar avatar = new NavigationUserAvatar();

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
		

		/*
		 * Adding left-side JPanel which is on the right side of the application. Used as the application's content panel
		 */
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setBounds(UI.NAVIGATION_PANEL_WIDTH, 0, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);
		
		/*
		 * Adding the content panels to an ArrayList.
		 * updatePanels() has the implementation to loop through the arraylist to set color to selected-navigation panel and set relevant content panel accordingly  
		 * 
		 */
		contentPanelList = new ArrayList<ContentPanel>();
		contentPanelList.add(dashboardContentPanel);
		contentPanelList.add(lecturerContentPanel);
		contentPanelList.add(modulesContentPanel);
		contentPanelList.add(examsContentPanel);
		contentPanelList.add(studentContentPanel);

		/*
		 * Adding content JPanels to right-side JPanel
		 */
		rightSidePanel.add(dashboardContentPanel.getContent());
		rightSidePanel.add(lecturerContentPanel.getContent());
		rightSidePanel.add(modulesContentPanel.getContent());
		rightSidePanel.add(examsContentPanel.getContent());
		rightSidePanel.add(studentContentPanel.getContent());


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

	
	private static void setSelectedNavigationPanel() {
		/*
		 * Executing a foreach loop to set the color of the selected navigation panel
		 */
		for (NavigationPanel NavigationPanel : navigationPanelList) {
			if (NavigationPanel.getNavigation().getName().toString().equalsIgnoreCase(AdminPanel.selectedNavigation.getNavigation().getName().toString())) {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_SELECTED_BUTTON_COLOR);
			} else {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_BUTTON_COLOR);
			}
		}
	}
	
	
	private static void setSelectedContentPanel() {
		/*
		 * Executing a foreach loop to set the relevant content panel according to the selected navigation panel
		 */
		for (ContentPanel contentPanel : contentPanelList) {
			if (contentPanel.getContent().getName().toString().equalsIgnoreCase(AdminPanel.selectedContent.getContent().getName().toString())) {
				contentPanel.getContent().setVisible(true);
			} else {
				contentPanel.getContent().setVisible(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String args[]) {
		AdminPanel ap = new AdminPanel();
		ap.setVisible(true);
	}
}
