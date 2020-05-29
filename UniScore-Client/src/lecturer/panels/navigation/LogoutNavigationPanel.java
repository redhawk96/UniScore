/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.navigation;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.panels.NavigationPanel;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.Identification;
import com.utils.UI;

import connectivity.UniScoreClient;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import models.Activity;

@SuppressWarnings("serial")
public class LogoutNavigationPanel extends NavigationPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain the sub UI elements and their properties and styling
	private JPanel panel = new JPanel();
	
	/*
	 * LogoutNavigationPanel method : used to initialize JPanel, required properties and add UI elements to the JPanel
	 */
	public LogoutNavigationPanel() {
		/*
		 * Adding navigation button to NavigationPanel
		 * JPanel name is set to identify navigation panel when selected
		 */
		panel.setName("logout");
		panel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		panel.setBounds(0, 615, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_BUTTON_HEIGHT);
		panel.setLayout(null);
		panel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		
		panel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be signed-off from the system on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
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
					System.out.println("RemoteException execution thrown on LogoutNavigationPanel.java file. Error : "+ex.getCause());
				} catch (ClassNotFoundException ex) {
					ErrorNotifier en = new ErrorNotifier("Failed to terminate connection with the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on LogoutNavigationPanel.java file. Error : "+ex.getCause());
				} catch (SQLException ex) {
					ErrorNotifier en = new ErrorNotifier("Failed to terminate connection with the server !\nPlease contact the administrator\nError refferance : "+ExceptionList.SQL);
					en.setVisible(true);
					System.out.println("SQLException execution thrown on LogoutNavigationPanel.java file. Error : "+ex.getCause());
				}
				
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
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

		// Adding navigation button(JPanel) text to panel
		JLabel navigationLabel = new JLabel("LOGOUT");
		navigationLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationLabel.setFont(UI.APPLICATION_THEME_FONT_14_BOLD);
		navigationLabel.setBounds(UI.NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_WIDTH, UI.NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT);
		panel.add(navigationLabel);

		// Adding navigation button(JPanel) icon to panel
		JLabel navigationIcon = new JLabel("");
		navigationIcon.setIcon(new ImageIcon(LecturerPanel.class.getResource("/resources/logout_icon.png")));
		navigationIcon.setHorizontalAlignment(SwingConstants.CENTER);
		navigationIcon.setBounds(UI.NAVIGATION_PANEL_BUTTON_ICON_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_WIDTH, UI.NAVIGATION_PANEL_BUTTON_ICON_HEIGHT);
		panel.add(navigationIcon);

	}

	/*
	 * getNavigation : Method is used to return the JPanel which wrapped by NavigationPanel 
	 * @return JPanel which contains the sub elements with added styling 
	 */
	@Override
	public JPanel getNavigation() {
		return panel;
	}

}
