/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
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
import com.panels.content.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import models.Activity;
import models.User;

@SuppressWarnings("serial")
public class LogoutNavigationPanel extends NavigationPanel {

	private JPanel panel = new JPanel();
	
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
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				LecturerPanel.selectedNavigation = new LogoutNavigationPanel();
				LecturerPanel.setSelectedPanel();
				
				try {
					
					UniScoreClient.uniscoreInterface.addLogActivity(new Activity(getFormatedLecturerId(UniScoreClient.authUser)+" has ended session from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
				
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
				
				UniScoreClient.authUser = null;
				UniScoreClient.loginPanel = new LoginPanel();
				UniScoreClient.loginPanel.setVisible(true);
				UniScoreClient.lecturerPanel.dispose();
			}
		});

		/*
		 * Adding navigation button text to NavigationPanel
		 */
		JLabel navigationLabel = new JLabel("LOGOUT");
		navigationLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationLabel.setFont(UI.APPLICATION_THEME_FONT_14_BOLD);
		navigationLabel.setBounds(UI.NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_WIDTH, UI.NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT);
		panel.add(navigationLabel);

		/*
		 * Adding navigation button icon to NavigationPanel
		 */
		JLabel navigationIcon = new JLabel("");
		navigationIcon.setIcon(new ImageIcon(LecturerPanel.class.getResource("/resources/logout_icon.png")));
		navigationIcon.setHorizontalAlignment(SwingConstants.CENTER);
		navigationIcon.setBounds(UI.NAVIGATION_PANEL_BUTTON_ICON_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_WIDTH, UI.NAVIGATION_PANEL_BUTTON_ICON_HEIGHT);
		panel.add(navigationIcon);

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

	@Override
	public JPanel getNavigation() {
		return panel;
	}

}
