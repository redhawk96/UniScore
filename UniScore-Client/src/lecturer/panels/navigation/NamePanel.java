package lecturer.panels.navigation;

import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.utils.UI;

import connectivity.UniScoreClient;

public class NamePanel {

	private JPanel panel = new JPanel();

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
		/*
		 * Adding navigation button text to NavigationPanel
		 */
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

	public JPanel getName() {
		return panel;
	}
}
