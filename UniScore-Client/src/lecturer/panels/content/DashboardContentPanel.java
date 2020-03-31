package lecturer.panels.content;

import javax.swing.JPanel;

import com.panels.ContentPanel;
import com.utils.UI;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {

	private JPanel contentPanel = new JPanel();

	public DashboardContentPanel() {

		/*
		 * Adding contentPanel
		 * JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("dashboard");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);

	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @param {}
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}

}
