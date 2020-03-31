package lecturer.panels.navigation;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.panels.NavigationPanel;
import com.utils.UI;

import lecturer.panels.content.QuestionContentPanel;
import main.panels.LecturerPanel;

@SuppressWarnings("serial")
public class QuestionNavigationPanel extends NavigationPanel {

	private JPanel panel = new JPanel();

	public QuestionNavigationPanel() {

		/*
		 * Adding navigation button to NavigationPanel
		 * JPanel name is set to identify navigation panel when selected
		 */
		panel.setName("question"); 
		panel.setBackground(UI.NAVIGATION_PANEL_BUTTON_COLOR);
		panel.setBounds(0, 435,UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_BUTTON_HEIGHT);
		panel.setLayout(null);
		panel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
				LecturerPanel.selectedContent = new QuestionContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});

		/*
		 * Adding navigation button text to NavigationPanel
		 */
		JLabel navigationLabel = new JLabel("QUESTIONS");
		navigationLabel.setForeground(UI.NAVIGATION_PANEL_BUTTON_TEXT_COLOR);
		navigationLabel.setFont(UI.NAVIGATION_PANEL_BUTTON_FONT);
		navigationLabel.setBounds(UI.NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_WIDTH, UI.NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT);
		panel.add(navigationLabel);

		/*
		 * Adding navigation button icon to NavigationPanel
		 */
		JLabel navigationIcon = new JLabel("");
		navigationIcon.setIcon(new ImageIcon(LecturerPanel.class.getResource("/resources/questions_icon.png")));
		navigationIcon.setHorizontalAlignment(SwingConstants.CENTER);
		navigationIcon.setBounds(UI.NAVIGATION_PANEL_BUTTON_ICON_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_WIDTH, UI.NAVIGATION_PANEL_BUTTON_ICON_HEIGHT);
		panel.add(navigationIcon);

	}

	@Override
	public JPanel getNavigation() {
		return panel;
	}
}
