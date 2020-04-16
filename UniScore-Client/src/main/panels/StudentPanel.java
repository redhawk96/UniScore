package main.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.panels.ContentPanel;
import com.panels.NavigationPanel;
import com.utils.UI;

import student.panels.content.DashboardContentPanel;
import student.panels.content.ExamContentPanel;
import student.panels.content.ModuleContentPanel;
import student.panels.content.SubmissionContentPanel;
import student.panels.navigation.DashboardNavigationPanel;
import student.panels.navigation.ExamNavigationPanel;
import student.panels.navigation.LogoutNavigationPanel;
import student.panels.navigation.ModuleNavigationPanel;
import student.panels.navigation.NavigationUserAvatar;
import student.panels.navigation.SubmissionNavigationPanel;



@SuppressWarnings("serial")
public class StudentPanel extends JFrame implements ActionListener{
	
	/*
	 * Declaring the NavigationPanels on left side of the application which is used to navigate to different content panels 
	 * To identify the active navigation, NavigationPanel type arraylist(navigationPanelList) is implemented
	 */
	private DashboardNavigationPanel dashboardNavigationPanel = new DashboardNavigationPanel();
	private ModuleNavigationPanel moduleNavigationPanel = new ModuleNavigationPanel();
	private NavigationPanel submissionNavigationPanel = new SubmissionNavigationPanel();
	private NavigationPanel examNavigationPanel = new ExamNavigationPanel();
	private NavigationPanel logoutNavigationPanel = new LogoutNavigationPanel();
	private static ArrayList<NavigationPanel> navigationPanelList;
	public static NavigationPanel selectedNavigation;
	
	/*
	 * Declaring the ContentPanels on right side of the application which consists of dynamic data retrieved from the databse
	 * To identify the active content panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	private ContentPanel dashboardContentPanel = new DashboardContentPanel();
	private ContentPanel moduleContentPanel = new ModuleContentPanel();
	private ContentPanel submissionContentPanel = new SubmissionContentPanel();
	private ContentPanel examContentPanel = new ExamContentPanel();
	private static ArrayList<ContentPanel> contentPanelList = null;
	public static ContentPanel selectedContent;
	
	/*
	 * User avatar icon component
	 */
	private NavigationUserAvatar avatar = new NavigationUserAvatar();

	public StudentPanel() {

		/*
		 * Setting JFrame title text
		 */
		setTitle("UNISCORE | Student");
		
		/*
		 * Setting the size of the application screen
		 */
		setSize(UI.APPLICATION_PRIMARY_FRAME_WIDTH, UI.APPLICATION_PRIMARY_FRAME_HEIGHT);
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
		leftSidePanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
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
		navigationPanelList.add(submissionNavigationPanel);
		navigationPanelList.add(examNavigationPanel);
		navigationPanelList.add(logoutNavigationPanel);
		
		/*
		 * Adding navigation JPanels to left-side JPanel
		 */
		leftSidePanel.add(dashboardNavigationPanel.getNavigation());
		leftSidePanel.add(moduleNavigationPanel.getNavigation());
		leftSidePanel.add(submissionNavigationPanel.getNavigation());
		leftSidePanel.add(examNavigationPanel.getNavigation());
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
		contentPanelList.add(moduleContentPanel);
		contentPanelList.add(submissionContentPanel);
		contentPanelList.add(examContentPanel);

		/*
		 * Adding content JPanels to right-side JPanel
		 */
		rightSidePanel.add(dashboardContentPanel.getContent());
		rightSidePanel.add(moduleContentPanel.getContent());
		rightSidePanel.add(submissionContentPanel.getContent());
		rightSidePanel.add(examContentPanel.getContent());


		/*
		 * Setting dashboard as root component on both navigation and content on application startup
		 */
		StudentPanel.selectedNavigation = new DashboardNavigationPanel();
		StudentPanel.selectedContent = new DashboardContentPanel();
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
			if (NavigationPanel.getNavigation().getName().toString().equalsIgnoreCase(StudentPanel.selectedNavigation.getNavigation().getName().toString())) {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_SELECTED_BUTTON_COLOR);
			} else {
				NavigationPanel.getNavigation().setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
		}
	}
	
	
	private static void setSelectedContentPanel() {
		/*
		 * Executing a foreach loop to set the relevant content panel according to the selected navigation panel
		 */
		for (ContentPanel contentPanel : contentPanelList) {
			if (contentPanel.getContent().getName().toString().equalsIgnoreCase(StudentPanel.selectedContent.getContent().getName().toString())) {
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
		StudentPanel tp = new StudentPanel();
		tp.setVisible(true);
	}


}
