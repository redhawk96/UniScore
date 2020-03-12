package lecturer.panels.content;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.panels.ContentPanel;
import com.utils.UI;

@SuppressWarnings("serial")
public class ModuleContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	
	public ModuleContentPanel() {
		/*
		 * Adding contentPanel
		 * JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("module");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL__X_AXIS, UI.CONTENT_PANEL__Y_AXIS, UI.CONTENT_PANEL__WIDTH, UI.CONTENT_PANEL__HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);

		JLabel contentLabel = new JLabel("module");
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
