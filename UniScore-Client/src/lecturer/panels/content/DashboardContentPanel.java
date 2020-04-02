package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.ExamNavigationPanel;
import lecturer.panels.navigation.ModuleNavigationPanel;
import lecturer.panels.navigation.StudentNavigationPanel;
import main.panels.LecturerPanel;
import models.User;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();

	public DashboardContentPanel() {
		contentPanel.setName("dashboard");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);
		
		try {
			DefaultTableModel model = new DefaultTableModel(new String[] { "UID", "Last Name", "First Name", "Sex", "Phone", "Email"}, 0);

			User tempUser = new User();
			tempUser.setRole("Student");
			List<User> userList = (List<User>) UniScoreClient.uniscoreInterface.getUsersByType(tempUser);

			for (User user : userList) {
				// Adding a new user record to the table each time the loop executes
				model.addRow(new Object[] {user.getUserId(), user.getLastName(), user.getFirstName(), user.getGender(), user.getPhone(), user.getEmail()});
			}
			table.setUpdateSelectionOnSort(false);
			table.setFocusTraversalKeysEnabled(false);
			table.setFocusable(false);
			table.setAutoCreateRowSorter(true);
			table.setEditingColumn(0);
			table.setEditingRow(0);
			table.setRequestFocusEnabled(false);
			table.setVerifyInputWhenFocusTarget(false);
			table.setBorder(null);
			
			table.setModel(model);

			scrollPane.setBounds(407, 299, 815, 580);
			
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.getTableHeader().setOpaque(false);
			table.getTableHeader().setBackground(Color.WHITE);
			table.getTableHeader().setForeground(Color.BLACK);
			table.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 14));
			table.setSelectionBackground(SystemColor.inactiveCaption);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(32);
			table.setFont(new Font("Roboto", Font.PLAIN, 13));
			table.isCellEditable(1, 1);
			scrollPane.setViewportView(table);
			contentPanel.add(scrollPane);
			
			addCards();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * @param {}
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
	
	
	private void addCards() {
		JPanel moduleCard = new JPanel();
		moduleCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				LecturerPanel.selectedNavigation = new ModuleNavigationPanel();
				LecturerPanel.selectedContent = new ModuleContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		moduleCard.setBorder(UI.CARD_BORDER);
		moduleCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		moduleCard.setBounds(30, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		moduleCard.setLayout(null);
		moduleCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(moduleCard);
		
		JPanel moduleCardTextPanel = new JPanel();
		moduleCardTextPanel.setBorder(UI.CARD_BORDER);
		moduleCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		moduleCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardTextPanel);
		moduleCardTextPanel.setLayout(null);
		
		JLabel moduleCardText = new JLabel("MODULES");
		moduleCardText.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText.setIcon(null);
		moduleCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		moduleCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardTextPanel.add(moduleCardText);
		
		JLabel moduleCardStatNumber = new JLabel("03");
		moduleCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		moduleCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardStatNumber);
		
		JPanel studentCard = new JPanel();
		studentCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				LecturerPanel.selectedNavigation = new StudentNavigationPanel();
				LecturerPanel.selectedContent = new StudentContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		studentCard.setLayout(null);
		studentCard.setBorder(UI.CARD_BORDER);
		studentCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		studentCard.setBounds(343, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		studentCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(studentCard);
		
		JPanel studentCardTextPanel = new JPanel();
		studentCardTextPanel.setLayout(null);
		studentCardTextPanel.setBorder(UI.CARD_BORDER);
		studentCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		studentCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCard.add(studentCardTextPanel);
		
		JLabel studentCardText = new JLabel("STUDENTS");
		studentCardText.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		studentCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		studentCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		studentCardTextPanel.add(studentCardText);
		
		JLabel studentCardStatNumber = new JLabel("01");
		studentCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		studentCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		studentCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		studentCard.add(studentCardStatNumber);
		
		JPanel examCard = new JPanel();
		examCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				LecturerPanel.selectedNavigation = new ExamNavigationPanel();
				LecturerPanel.selectedContent = new ExamContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		examCard.setLayout(null);
		examCard.setBorder(UI.CARD_BORDER);
		examCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		examCard.setBounds(650, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		examCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(examCard);
		
		JPanel examCardTextPanel = new JPanel();
		examCardTextPanel.setLayout(null);
		examCardTextPanel.setBorder(UI.CARD_BORDER);
		examCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		examCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardTextPanel);
		
		JLabel examCardText = new JLabel("EXAMS");
		examCardText.setHorizontalAlignment(SwingConstants.CENTER);
		examCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		examCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCardTextPanel.add(examCardText);
		
		JLabel examCardStatNumber = new JLabel("00");
		examCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		examCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		examCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardStatNumber);
		
		JPanel moduleCard_3 = new JPanel();
		moduleCard_3.setLayout(null);
		moduleCard_3.setBorder(UI.CARD_BORDER);
		moduleCard_3.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		moduleCard_3.setBounds(957, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		contentPanel.add(moduleCard_3);
		
		JPanel moduleCardTextPanel_3 = new JPanel();
		moduleCardTextPanel_3.setLayout(null);
		moduleCardTextPanel_3.setBorder(UI.CARD_BORDER);
		moduleCardTextPanel_3.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		moduleCardTextPanel_3.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard_3.add(moduleCardTextPanel_3);
		
		JLabel moduleCardText_3 = new JLabel("  MODULES");
		moduleCardText_3.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText_3.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		moduleCardText_3.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardText_3.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCardTextPanel_3.add(moduleCardText_3);
		
		JLabel moduleCardStatNumber_3 = new JLabel("01");
		moduleCardStatNumber_3.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber_3.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		moduleCardStatNumber_3.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardStatNumber_3.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		moduleCard_3.add(moduleCardStatNumber_3);
		
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		navigationIndicatorPanel.setBackground(Color.WHITE);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(1074, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Home");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(1139, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 50, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
}
