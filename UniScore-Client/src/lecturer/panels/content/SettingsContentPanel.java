package lecturer.panels.content;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.panels.ContentPanel;
import com.utils.UI;
import java.awt.Color;

@SuppressWarnings("serial")
public class SettingsContentPanel extends ContentPanel{
	
JPanel contentPanel = new JPanel();
	
	public SettingsContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("settings");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		
		setSettingsBody();
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}

	public void displayNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.NAVIGATION_INDICATOR_PANEL_BACKGRIOUND_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Settings");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setSettingsBody() {
		
		displayNavigationIndicator();
		
		JPanel settingsBodyPanel = new JPanel();
		settingsBodyPanel.setBackground(Color.WHITE);
		settingsBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(settingsBodyPanel);
	}
}
