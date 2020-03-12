package lecturer.panels.content;

import javax.swing.JPanel;

import com.panels.ContentPanel;
import com.utils.UI;

import javax.swing.JLabel;

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
		contentPanel.setBounds(UI.CONTENT_PANEL__X_AXIS, UI.CONTENT_PANEL__Y_AXIS, UI.CONTENT_PANEL__WIDTH, UI.CONTENT_PANEL__HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);

		JLabel contentLabel = new JLabel("dashboard");
		contentLabel.setBounds(283, 187, 95, 37);
		contentPanel.add(contentLabel);
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
