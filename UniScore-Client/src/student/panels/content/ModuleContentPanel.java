package student.panels.content;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class ModuleContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	private JPanel panel3;
	private JPanel panel2;
	private JPanel panel1;
	private Container layeredPane;

	public ModuleContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when
		 * selected
		 */
		contentPanel.setName("module");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);

		displayNavigationIndicator();
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @param {}
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
	
	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		
	
	}
	public void displayNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Student /");
		navigationIndicatorMainLabel.setBounds(1054, 1119, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Modules");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1054, 1119, 65, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(30, 46, 1199, 764);
		contentPanel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel1 = new JPanel();
		layeredPane.add(panel1, "name_21040911902100");
		
		JButton rmiPanel = new JButton("RMI");
		panel1.add(rmiPanel);
		
		JButton javaPanel = new JButton("JAVA");
		panel1.add(javaPanel);
		
		panel2 = new JPanel();
		layeredPane.add(panel2, "name_21098091074400");
		
		JLabel lblModulePanel = new JLabel("Module Panel");
		panel2.add(lblModulePanel);
		
		panel3 = new JPanel();
		layeredPane.add(panel3, "name_21127448283400");
		panel3.setLayout(null);
		
		JLabel lblExamPaperPanel = new JLabel("Exam paper Panel");
		lblExamPaperPanel.setBounds(548, 5, 103, 16);
		panel3.add(lblExamPaperPanel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(114, 240, 172, 47);
		panel3.add(lblNewLabel);
		
		JButton Module1 = new JButton("RMI");
		layeredPane.add(Module1, "name_21143466642200");
	}
}

