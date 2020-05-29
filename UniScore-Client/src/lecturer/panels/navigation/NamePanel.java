/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.navigation;

import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.utils.UI;

import connectivity.UniScoreClient;

public class NamePanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain the sub UI elements and their properties and styling
	private JPanel panel = new JPanel();

	/*
	 * NamePanel method : used to initialize JPanel, required properties and add UI elements to the JPanel
	 */
	public NamePanel() {
		/*
		 * Adding navigation button to NavigationPanel
		 * JPanel name is set to identify navigation panel when selected
		 */
		panel.setName("authUserFName");
		panel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		panel.setBounds(0, 225, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_BUTTON_HEIGHT);
		panel.setLayout(null);
		panel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));

		// navigationLabel used to display the logged in lecturer's first and last name
		JLabel navigationLabel = new JLabel(UniScoreClient.authUser.getFirstName().toUpperCase()+" "+UniScoreClient.authUser.getLastName().toUpperCase());
		navigationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		navigationLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationLabel.setFont(UI.APPLICATION_THEME_FONT_17_BOLD);
		navigationLabel.setBounds(0, 0, 255, 45);
		panel.add(navigationLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(110, 43, 40, 2);
		separator.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		separator.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		panel.add(separator);
	}

	/*
	 * getName : Method is used to return the JPanel which has the styling of its sub elements (signed-in lecturer's name)
	 * @return JPanel which contains the sub elements with added styling 
	 */
	public JPanel getName() {
		return panel;
	}
}
