package student.panels.content;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

import main.panels.StudentPanel;
import student.panels.content.ExamContentPanel;
import student.panels.content.ModuleContentPanel;
import student.panels.navigation.ExamNavigationPanel;
import student.panels.navigation.ModuleNavigationPanel;
import student.panels.navigation.SubmissionNavigationPanel;

@SuppressWarnings("serial")
public class DashboardContentPanel extends ContentPanel{
	
	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();

	public DashboardContentPanel() {
		contentPanel.setName("dashboard");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
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
				StudentPanel.selectedNavigation = new ModuleNavigationPanel();
				StudentPanel.selectedContent = new ModuleContentPanel();
				StudentPanel.setSelectedPanel();
			}
		});
		moduleCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		moduleCard.setBounds(30, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		moduleCard.setLayout(null);
		moduleCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		contentPanel.add(moduleCard);
		
		JPanel moduleCardTextPanel = new JPanel();
		moduleCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		moduleCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardTextPanel);
		moduleCardTextPanel.setLayout(null);
		
		JLabel moduleCardText = new JLabel("MODULES");
		moduleCardText.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardText.setIcon(null);
		moduleCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		moduleCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		moduleCardTextPanel.add(moduleCardText);
		
		JLabel moduleCardStatNumber = new JLabel("03");
		moduleCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		moduleCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		moduleCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		moduleCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		moduleCard.add(moduleCardStatNumber);
		
		JPanel submissionCard = new JPanel();
		submissionCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				StudentPanel.selectedNavigation = new SubmissionNavigationPanel();
				StudentPanel.selectedContent = new SubmissionContentPanel();
				StudentPanel.setSelectedPanel();
			}
		});
		submissionCard.setLayout(null);
		submissionCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		submissionCard.setBounds(640, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		submissionCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		contentPanel.add(submissionCard);
		
		JPanel submissionCardTextPanel = new JPanel();
		submissionCardTextPanel.setLayout(null);
		submissionCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		submissionCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		submissionCard.add(submissionCardTextPanel);
		
		JLabel submissionCardText = new JLabel("SUBMISSIONS");
		submissionCardText.setHorizontalAlignment(SwingConstants.CENTER);
		submissionCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		submissionCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		submissionCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		submissionCardTextPanel.add(submissionCardText);
		
		JLabel submissionCardStatNumber = new JLabel("01");
		submissionCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		submissionCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		submissionCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		submissionCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		submissionCard.add(submissionCardStatNumber);
		
		JPanel examCard = new JPanel();
		examCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will be set as selectedNavigation and current ContentPanel will be set as selectedContent 
				 * setSelectedPanel() will be executed to update the already selected NavigationPanel with current selected NavigationPanel along with relevant ContentPanel
				 */
				StudentPanel.selectedNavigation = new ExamNavigationPanel();
				StudentPanel.selectedContent = new ExamContentPanel();
				StudentPanel.setSelectedPanel();
			}
		});
		examCard.setLayout(null);
		examCard.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examCard.setBounds(340, 66, UI.CARD_WIDTH, UI.CARD_HEIGHT);
		examCard.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		contentPanel.add(examCard);
		
		JPanel examCardTextPanel = new JPanel();
		examCardTextPanel.setLayout(null);
		examCardTextPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examCardTextPanel.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardTextPanel);
		
		JLabel examCardText = new JLabel("EXAMS");
		examCardText.setHorizontalAlignment(SwingConstants.CENTER);
		examCardText.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examCardText.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		examCardText.setBounds(0, 0, UI.CARD_LABEL_TEXT_WIDTH, UI.CARD_HEIGHT);
		examCardTextPanel.add(examCardText);
		
		JLabel examCardStatNumber = new JLabel("00");
		examCardStatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		examCardStatNumber.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		examCardStatNumber.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		examCardStatNumber.setBounds(189, 0, UI.CARD_LABEL_NUMBER_WIDTH, UI.CARD_HEIGHT);
		examCard.add(examCardStatNumber);
		
	
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
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Home");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1119, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 65, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}


}
