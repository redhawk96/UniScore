package lecturer.panels.content;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

@SuppressWarnings("serial")
public class ModuleContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();

	public ModuleContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when
		 * selected
		 */
		contentPanel.setName("module");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);

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
