package main.panels;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.NavigationPanel;
import com.utils.UI;

import admin.panels.content.DashboardContentPanel;
import admin.panels.navigation.DashboardNavigationPanel;
import admin.panels.navigation.NavigationUserAvatar;
import connectivity.UniScoreServer;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings({ "serial", "unused" })
public class AdminPanel extends JFrame{

	/*
	 * Declaring the NavigationPanels on left side of the application which is used to navigate to different content panels 
	 * To identify the active navigation, NavigationPanel type arraylist(navigationPanelList) is implemented
	 */
	private DashboardNavigationPanel dashboardNavigationPanel = new DashboardNavigationPanel();
	private NavigationUserAvatar navigationUserAvatar = new NavigationUserAvatar();
	private static ArrayList<NavigationPanel> navigationPanelList;
	public static NavigationPanel selectedNavigation;
	
	/*
	 * Declaring the ContentPanels on right side of the application which consists of dynamic data retrieved from the databse
	 * To identify the active content panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	private ContentPanel dashboardContentPanel = new DashboardContentPanel();
	private static ArrayList<ContentPanel> contentPanelList = null;
	public static ContentPanel selectedContent;
	
	/*
	 * User avatar icon component
	 */
	private NavigationUserAvatar avatar = new NavigationUserAvatar();
	
	private JLabel authUserFNameLabel;

	public AdminPanel() {

		/*
		 * Setting the size of the application screen
		 */
		setSize(1091, 672);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		/*
		 * Adding left-side JPanel which is on the left side of the application. Used as the application's navigation panel
		 */
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBackground(UI.NAVIGATION_PANEL_COLOR);
		leftSidePanel.setBounds(0, 0, 223, 633);
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

		/*
		 * Adding navigation JPanels to left-side JPanel
		 */
		leftSidePanel.add(dashboardNavigationPanel.getNavigation());
		leftSidePanel.add(navigationUserAvatar.getAvatar());
		
		/*
		 * Setting auth user's first name by cookie
		 */
		authUserFNameLabel = new JLabel(UniScoreServer.authUser.getFirstName());
		authUserFNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authUserFNameLabel.setForeground(UI.NAVIGATION_PANEL_BUTTON_TEXT_COLOR);
		authUserFNameLabel.setFont(UI.NAVIGATION_PANEL_BUTTON_FONT);
		authUserFNameLabel.setBounds(34, 143, 148, 20);
		leftSidePanel.add(authUserFNameLabel);

		/*
		 * Adding left-side JPanel which is on the right side of the application. Used as the application's content panel
		 */
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setBounds(223, 0, 852, 633);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);
		
		/*
		 * Adding the content panels to an ArrayList.
		 * updatePanels() has the implementation to loop through the arraylist to set color to selected-navigation panel and set relevant content panel accordingly  
		 * 
		 */
		contentPanelList = new ArrayList<ContentPanel>();
		contentPanelList.add(dashboardContentPanel);

		/*
		 * Adding content JPanels to right-side JPanel
		 */
		rightSidePanel.add(dashboardContentPanel.getContent());


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
	
	public static void main(String args[]) {
		AdminPanel ap = new AdminPanel();
		ap.setVisible(true);
	}
}
