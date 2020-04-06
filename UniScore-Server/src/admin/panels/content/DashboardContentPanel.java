/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package admin.panels.content;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

import admin.panels.navigation.ModulesNavigationPanel;
import admin.panels.content.ExamsContentPanel;
import admin.panels.content.ModulesContentPanel;
import admin.panels.content.StudentContentPanel;
import admin.panels.navigation.ExamsNavigationPanel;
import admin.panels.navigation.LecturerNavigationPanel;
import admin.panels.navigation.ModulesNavigationPanel;
import admin.panels.navigation.StudentNavigationPanel;
import main.panels.AdminPanel;
import main.panels.AdminPanel;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();

	/*Constructor - initialise the class */
	public DashboardContentPanel() {
		contentPanel.setName("dashboard");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);
		
		/*catching and handling errors errors */
		try {
			displayCards();
			
			displayNavigationIndicator();
			
			
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
	
	private void displayCards() {
		JPanel moduleCard = new JPanel();
		moduleCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				AdminPanel.selectedNavigation = new ModulesNavigationPanel();
				AdminPanel.selectedContent = new ModulesContentPanel();
				AdminPanel.setSelectedPanel();
			}
		});
		moduleCard.setBorder(UI.CARD_BORDER);
		moduleCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		moduleCard.setBounds(30, 66, 230, 82);
		moduleCard.setLayout(null);
		moduleCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(moduleCard);
		
		JPanel moduleCardTextPanel = new JPanel();
		moduleCardTextPanel.setBorder(UI.CARD_BORDER);
		moduleCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		moduleCardTextPanel.setBounds(0, 0, 163, 77);
		moduleCard.add(moduleCardTextPanel);
		moduleCardTextPanel.setLayout(null);
		
		JLabel moduleCardText = new JLabel("MODULES");
		moduleCardText.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText.setIcon(null);
		moduleCardText.setBounds(0, 0, 158, 79);
		moduleCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		moduleCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardTextPanel.add(moduleCardText);
		
		JLabel moduleCardStatNumber = new JLabel("03");
		moduleCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		moduleCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		moduleCardStatNumber.setBounds(158, 0, 71, 82);
		moduleCard.add(moduleCardStatNumber);
		
		JPanel lecturerCard = new JPanel();
		lecturerCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				AdminPanel.selectedNavigation = new LecturerNavigationPanel();
				AdminPanel.selectedContent = new LecturerContentPanel();
				AdminPanel.setSelectedPanel();
			}
		});
		lecturerCard.setLayout(null);
		lecturerCard.setBorder(UI.CARD_BORDER);
		lecturerCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		lecturerCard.setBounds(301, 66, 230, 82);
		lecturerCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(lecturerCard);
		
		JPanel lecturerCardTextPanel = new JPanel();
		lecturerCardTextPanel.setLayout(null);
		lecturerCardTextPanel.setBorder(UI.CARD_BORDER);
		lecturerCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		lecturerCardTextPanel.setBounds(0, 0, 159, 79);
		lecturerCard.add(lecturerCardTextPanel);
		
		JLabel lecturerCardText = new JLabel("LECTURER");
		lecturerCardText.setHorizontalAlignment(SwingConstants.CENTER);
		lecturerCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		lecturerCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		lecturerCardText.setBounds(0, 0, 159, 79);
		lecturerCardTextPanel.add(lecturerCardText);
		
		JLabel lecturerCardStatNumber = new JLabel("01");
		lecturerCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lecturerCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		lecturerCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		lecturerCardStatNumber.setBounds(169, 0, 68, 79);
		lecturerCard.add(lecturerCardStatNumber);
		
		JPanel studentCard = new JPanel();
		studentCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				AdminPanel.selectedNavigation = new StudentNavigationPanel();
				AdminPanel.selectedContent = new StudentContentPanel();
				AdminPanel.setSelectedPanel();
			}
		});
		studentCard.setLayout(null);
		studentCard.setBorder(UI.CARD_BORDER);
		studentCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		studentCard.setBounds(570, 66, 230, 82);
		studentCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(studentCard);
		
		JPanel studentCardTextPanel = new JPanel();
		studentCardTextPanel.setLayout(null);
		studentCardTextPanel.setBorder(UI.CARD_BORDER);
		studentCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		studentCardTextPanel.setBounds(0, 0, 162, 82);
		studentCard.add(studentCardTextPanel);
		
		JLabel studentCardText = new JLabel("STUDENTS");
		studentCardText.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		studentCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		studentCardText.setBounds(0, 0, 161, 82);
		studentCardTextPanel.add(studentCardText);
		
		JLabel studentCardStatNumber = new JLabel("01");
		studentCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		studentCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		studentCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		studentCardStatNumber.setBounds(160, 0, 70, 82);
		studentCard.add(studentCardStatNumber);
		
		JPanel examCard = new JPanel();
		examCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				AdminPanel.selectedNavigation = new ExamsNavigationPanel();
				AdminPanel.selectedContent = new ExamsContentPanel();
				AdminPanel.setSelectedPanel();
			}
		});
		examCard.setLayout(null);
		examCard.setBorder(UI.CARD_BORDER);
		examCard.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		examCard.setBounds(835, 66, 230, 82);
		examCard.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		contentPanel.add(examCard);
		
		JPanel examCardTextPanel = new JPanel();
		examCardTextPanel.setLayout(null);
		examCardTextPanel.setBorder(UI.CARD_BORDER);
		examCardTextPanel.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		examCardTextPanel.setBounds(0, 0, 163, 82);
		examCard.add(examCardTextPanel);
		
		JLabel examCardText = new JLabel("EXAMS");
		examCardText.setHorizontalAlignment(SwingConstants.CENTER);
		examCardText.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		examCardText.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardText.setBounds(0, 0, 178, 79);
		examCardTextPanel.add(examCardText);
		
		JLabel examCardStatNumber = new JLabel("00");
		examCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		examCardStatNumber.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		examCardStatNumber.setFont(UI.CARD_LABEL_TEXT_FONT);
		examCardStatNumber.setBounds(157, 0, 73, 82);
		examCard.add(examCardStatNumber);
		
		/*
		JPanel StudentRegistrationCard_3 = new JPanel();
		StudentRegistration_3.setLayout(null);
		StudentRegistration_3.setBorder(UI.CARD_BORDER);
		StudentRegistration_3.setBackground(UI.CARD_PRIMARY_BACKGROUND_COLOR);
		StudentRegistration_3.setBounds(343, 255, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		StudentRegistration.add(moduleCard_3);
		
		JPanel StudentRegistrationCardTextPanel_3 = new JPanel();
		StudentRegistrationCardTextPanel_3.setLayout(null);
		StudentRegistrationCardTextPanel_3.setBorder(UI.CARD_BORDER);
		StudentRegistrationCardTextPanel_3.setBackground(UI.CARD_SECONDARY_BACKGROUND_COLOR);
		StudentRegistrationCardTextPanel_3.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard_3.add(moduleCardTextPanel_3);
		
		JLabel StudentRegistrationCardText_3 = new JLabel("Student Registration");
		StudentRegistrationCardText_3.setHorizontalAlignment(SwingConstants.CENTER);
		StudentRegistrationCardText_3.setForeground(UI.CARD_LABEL_TEXT_COLOR);
		StudentRegistrationCardText_3.setFont(UI.CARD_LABEL_TEXT_FONT);
		StudentRegistrationCardText_3.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		StudentRegistrationCardTextPanel_3.add(moduleCardText_3);
		
		JLabel StudentRegistrationStatNumber_3 = new JLabel("01");
		StudentRegistrationStatNumber_3.setHorizontalAlignment(SwingConstants.CENTER);
		StudentRegistrationStatNumber_3.setForeground(UI.CARD_LABEL_NUMBER_COLOR);
		StudentRegistrationStatNumber_3.setFont(UI.CARD_LABEL_TEXT_FONT);
		StudentRegistrationStatNumber_3.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		StudentRegistrationCard_3.add(StudentRegistrationCardStatNumber_3);
		*/
	}
	
	public void displayNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.NAVIGATION_INDICATOR_PANEL_BACKGRIOUND_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1085, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Admin /");
		navigationIndicatorMainLabel.setBounds(932, 8, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Home");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(1000, 8, 50, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
}
