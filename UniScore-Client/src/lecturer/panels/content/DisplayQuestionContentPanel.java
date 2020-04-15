package lecturer.panels.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import com.panels.ContentPanel;
import com.panels.content.ErrorNotifier;
import com.panels.content.SuccessNotifier;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import main.panels.LecturerPanel;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class DisplayQuestionContentPanel extends ContentPanel{
	
	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel questionBodyPanel = new JPanel();
	JPanel examInfoPanel = new JPanel();
	JComboBox<Object> answersComboBox;
	Module module;
	Exam exam;
	Question question;
	
	private JTextField questionText;
	private JTextField optionOneText;
	private JTextField optionTwoText;
	private JTextField optionThreeText;
	private JTextField optionFourText;
	
	public DisplayQuestionContentPanel(Module module, Exam exam, Question question) {
		this.module = module;
		this.exam = exam;
		this.question = question;
		/*
		 * Adding contentPanel
		 * JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("displayQuestion");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		
		displayNavigationIndicator();
		
		
		questionBodyPanel.setBackground(Color.WHITE);
		questionBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionBodyPanel);
		questionBodyPanel.setLayout(null);
		
		
		// exam info panel
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(Color.DARK_GRAY);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		questionBodyPanel.add(examInfoPanel);
		
		JLabel moduleInfoLabel = new JLabel("Module Information");
		moduleInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moduleInfoLabel.setForeground(Color.WHITE);
		moduleInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleInfoLabel.setBounds(31, 11, 381, 14);
		examInfoPanel.add(moduleInfoLabel);
		
		JLabel examInfoLabel = new JLabel("Exam Information");
		examInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examInfoLabel.setForeground(Color.WHITE);
		examInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examInfoLabel.setBounds(489, 11, 349, 14);
		examInfoPanel.add(examInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setBounds(437, 11, 11, 116);
		examInfoPanel.add(separator);
		
		JLabel moduleCodeLabel = new JLabel("Code");
		moduleCodeLabel.setForeground(Color.WHITE);
		moduleCodeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleCodeLabel.setBounds(31, 48, 99, 14);
		examInfoPanel.add(moduleCodeLabel);
		
		JLabel moduleNameLabel = new JLabel("Name");
		moduleNameLabel.setForeground(Color.WHITE);
		moduleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleNameLabel.setBounds(31, 76, 99, 14);
		examInfoPanel.add(moduleNameLabel);
		
		JLabel moduleLabel = new JLabel("Allocation");
		moduleLabel.setForeground(Color.WHITE);
		moduleLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleLabel.setBounds(31, 104, 118, 14);
		examInfoPanel.add(moduleLabel);
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+module.getModuleId());
		selectedModuleCodeLabel.setForeground(Color.WHITE);
		selectedModuleCodeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleCodeLabel.setBounds(158, 48, 269, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+module.getModuleName());
		selectedModuleNameLabel.setForeground(Color.WHITE);
		selectedModuleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleNameLabel.setBounds(158, 76, 269, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+module.getYear()+"  - S"+module.getSemester());
		selectedModuleAllocationLabel.setForeground(Color.WHITE);
		selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleAllocationLabel.setBounds(158, 105, 269, 14);
		examInfoPanel.add(selectedModuleAllocationLabel);
		
		JLabel examNameLabel = new JLabel("Name");
		examNameLabel.setForeground(Color.WHITE);
		examNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examNameLabel.setBounds(489, 48, 349, 14);
		examInfoPanel.add(examNameLabel);
		
		JLabel examDurationLabel = new JLabel("Duration");
		examDurationLabel.setForeground(Color.WHITE);
		examDurationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examDurationLabel.setBounds(489, 76, 349, 14);
		examInfoPanel.add(examDurationLabel);
		
		JLabel examStatusLabel = new JLabel("Status");
		examStatusLabel.setForeground(Color.WHITE);
		examStatusLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examStatusLabel.setBounds(489, 104, 349, 14);
		examInfoPanel.add(examStatusLabel);
		
		JLabel selectedExamName = new JLabel(":  "+ exam.getExamName());
		selectedExamName.setForeground(Color.WHITE);
		selectedExamName.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamName.setBounds(601, 49, 237, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+exam.getDuration());
		selectedExamDuration.setForeground(Color.WHITE);
		selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamDuration.setBounds(601, 76, 237, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+exam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(Color.WHITE);
		selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamStatus.setBounds(601, 104, 237, 17);
		examInfoPanel.add(selectedExamStatus);
		
		
		JPanel removeQuestionPanel = new JPanel();
		removeQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		removeQuestionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoveQuestionNotifier cn = new RemoveQuestionNotifier(module, exam, question);
				cn.setVisible(true);
			}
		});
		removeQuestionPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		removeQuestionPanel.setBounds(1046, 0, 153, 68);
		examInfoPanel.add(removeQuestionPanel);
		removeQuestionPanel.setLayout(null);
		
		JLabel removeQuestionLabel = new JLabel("REMOVE");
		removeQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeQuestionLabel.setBounds(0, 0, 153, 68);
		removeQuestionLabel.setForeground(Color.DARK_GRAY);
		removeQuestionLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		removeQuestionPanel.add(removeQuestionLabel);
		
		JPanel goBackButtonPanel = new JPanel();
		goBackButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		goBackButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
				LecturerPanel.selectedContent = new DisplayQuestionsContentPanel(module, exam);
				LecturerPanel.setSelectedPanel();
			}
		});
		goBackButtonPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		goBackButtonPanel.setLayout(null);
		goBackButtonPanel.setBackground(Color.DARK_GRAY);
		goBackButtonPanel.setBounds(891, 0, 153, 138);
		examInfoPanel.add(goBackButtonPanel);
		
		JLabel goBackButtonLabel = new JLabel("BACK");
		goBackButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goBackButtonLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		goBackButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		goBackButtonLabel.setBounds(0, 0, 153, 138);
		goBackButtonPanel.add(goBackButtonLabel);
		
		JPanel saveQuestionPanel = new JPanel();
		saveQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		saveQuestionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				if(questionText.getText().trim().length() < 1) {
					
					ErrorNotifier en = new ErrorNotifier("Question title is a required field");
					en.setVisible(true);
					
				} else if(optionOneText.getText().trim().length() < 1) {
					
					ErrorNotifier en = new ErrorNotifier("Option one is a required field");
					en.setVisible(true);
					
				} else if(optionTwoText.getText().trim().length() < 1) {
					
					ErrorNotifier en = new ErrorNotifier("Option two is a required field");
					en.setVisible(true);
					
				} else if(optionThreeText.getText().trim().length() < 1) {

					ErrorNotifier en = new ErrorNotifier("Option three is a required field");
					en.setVisible(true);
					
				} else if(optionFourText.getText().trim().length() < 1) {
					
					ErrorNotifier en = new ErrorNotifier("Option four is a required field");
					en.setVisible(true);
					
				} else if(answersComboBox.getSelectedIndex() == -1) {

					ErrorNotifier en = new ErrorNotifier("Answer is a required field");
					en.setVisible(true);
					
				} else {
					
					Question updatedQuestion = new Question();
					updatedQuestion.setQuestionId(question.getQuestionId());
					updatedQuestion.setQuestion(questionText.getText());
					updatedQuestion.setOption1(optionOneText.getText());
					updatedQuestion.setOption2(optionTwoText.getText());
					updatedQuestion.setOption3(optionThreeText.getText());
					updatedQuestion.setOption4(optionFourText.getText());
					updatedQuestion.setAnswer(answersComboBox.getSelectedIndex()+1);

					try {
						
						boolean executionStatus = (boolean) UniScoreClient.uniscoreInterface.updateQuestion(updatedQuestion);
		
						if(executionStatus) {
							SuccessNotifier sn = new SuccessNotifier("Question was successfully saved.\nRecord refferance : Question ID - "+question.getQuestionId(), new QuestionNavigationPanel(), new DisplayQuestionsContentPanel(module, exam));
							sn.setVisible(true);
						} else {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 501");
							en.setVisible(true);
						}
					
					} catch (RemoteException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 400");
						en.setVisible(true);
						System.out.println("RemoteException execution thrown on DisplayQuestionContentPanel.java file. Error : "+e.getCause());
					} catch (ClassNotFoundException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 600");
						en.setVisible(true);
						System.out.println("ClassNotFoundException execution thrown on DisplayQuestionContentPanel.java file. Error : "+e.getCause());
					} catch (SQLException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 500");
						en.setVisible(true);
						System.out.println("SQLException execution thrown on DisplayQuestionContentPanel.java file. Error : "+e.getCause());
					}
				}
			}
		});
		saveQuestionPanel.setLayout(null);
		saveQuestionPanel.setBackground(new Color(249, 168, 37));
		saveQuestionPanel.setBounds(1046, 70, 153, 68);
		examInfoPanel.add(saveQuestionPanel);
		
		JLabel saveQuestionLabel = new JLabel("SAVE");
		saveQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveQuestionLabel.setForeground(Color.DARK_GRAY);
		saveQuestionLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		saveQuestionLabel.setBounds(0, 0, 153, 68);
		saveQuestionPanel.add(saveQuestionLabel);
		
		JPanel displayQuestionPanel = new JPanel();
		displayQuestionPanel.setBackground(Color.WHITE);
		displayQuestionPanel.setBounds(0, 172, 1199, 641);
		questionBodyPanel.add(displayQuestionPanel);
		displayQuestionPanel.setLayout(null);
		
		JLabel questionIDLabel = new JLabel("Question ID  :  "+question.getQuestionId());
		questionIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		questionIDLabel.setForeground(Color.DARK_GRAY);
		questionIDLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		questionIDLabel.setBounds(1034, 24, 155, 38);
		displayQuestionPanel.add(questionIDLabel);
		
		JLabel questionLabel = new JLabel("Question                   :");
		questionLabel.setForeground(Color.DARK_GRAY);
		questionLabel.setBorder(null);
		questionLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		questionLabel.setBounds(31, 11, 155, 65);
		displayQuestionPanel.add(questionLabel);
		
		JLabel optionOneLabel = new JLabel("Option 01                  :");
		optionOneLabel.setForeground(Color.DARK_GRAY);
		optionOneLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionOneLabel.setBounds(31, 111, 155, 65);
		displayQuestionPanel.add(optionOneLabel);
		
		JLabel answerLabel = new JLabel("Answer                     :");
		answerLabel.setForeground(Color.DARK_GRAY);
		answerLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		answerLabel.setBounds(31, 511, 155, 65);
		displayQuestionPanel.add(answerLabel);
		
		JLabel optionTwoLabel = new JLabel("Option 02                  :");
		optionTwoLabel.setForeground(Color.DARK_GRAY);
		optionTwoLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionTwoLabel.setBounds(31, 211, 155, 65);
		displayQuestionPanel.add(optionTwoLabel);
		
		JLabel optionThreeLabel = new JLabel("Option 03                  :");
		optionThreeLabel.setForeground(Color.DARK_GRAY);
		optionThreeLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionThreeLabel.setBounds(31, 311, 155, 65);
		displayQuestionPanel.add(optionThreeLabel);
		
		JLabel optionFourLabel = new JLabel("Option 04                  :");
		optionFourLabel.setForeground(Color.DARK_GRAY);
		optionFourLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionFourLabel.setBounds(31, 411, 155, 65);
		displayQuestionPanel.add(optionFourLabel);
		
		questionText = new JTextField();
		questionText.setText(question.getQuestion());
		questionText.setForeground(Color.GRAY);
		questionText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		questionText.setFont(new Font("Roboto", Font.PLAIN, 16));
		questionText.setBounds(196, 25, 540, 38);
		displayQuestionPanel.add(questionText);
		questionText.setColumns(10);
		
		optionOneText = new JTextField();
		optionOneText.setText(question.getOption1());
		optionOneText.setForeground(Color.GRAY);
		optionOneText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		optionOneText.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionOneText.setColumns(10);
		optionOneText.setBounds(196, 124, 540, 38);
		displayQuestionPanel.add(optionOneText);
		
		optionTwoText = new JTextField();
		optionTwoText.setText(question.getOption2());
		optionTwoText.setForeground(Color.GRAY);
		optionTwoText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		optionTwoText.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionTwoText.setColumns(10);
		optionTwoText.setBounds(196, 224, 540, 38);
		displayQuestionPanel.add(optionTwoText);
		
		optionThreeText = new JTextField();
		optionThreeText.setText(question.getOption3());
		optionThreeText.setForeground(Color.GRAY);
		optionThreeText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		optionThreeText.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionThreeText.setColumns(10);
		optionThreeText.setBounds(196, 324, 540, 38);
		displayQuestionPanel.add(optionThreeText);
		
		optionFourText = new JTextField();
		optionFourText.setText(question.getOption4());
		optionFourText.setForeground(Color.GRAY);
		optionFourText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		optionFourText.setFont(new Font("Roboto", Font.PLAIN, 16));
		optionFourText.setColumns(10);
		optionFourText.setBounds(196, 424, 540, 38);
		displayQuestionPanel.add(optionFourText);
		
		answersComboBox = new JComboBox<Object>();
	
		answersComboBox.setRenderer(new DefaultListCellRenderer() {
	      @Override 
	      public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
	        JLabel lable = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
	        if (isSelected) {
	        	lable.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
	        	lable.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
	        } else {
	        	lable.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
	        	lable.setBackground(Color.WHITE);
	        }
	        return lable;
	      }
	    });
		
		answersComboBox.setUI(new BasicComboBoxUI() {
		    @Override
		    protected ComboPopup createPopup() {
		        BasicComboPopup basicComboPopup = new BasicComboPopup(answersComboBox);
		        basicComboPopup.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.DARK_GRAY));
		        return basicComboPopup;
		    }
		});
		
		answersComboBox.setOpaque(false);
		answersComboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
		answersComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"   Option 01", "   Option 02", "   Option 03", "   Option 04"}));
		answersComboBox.setSelectedIndex( question.getAnswer()-1);
		answersComboBox.setBounds(196, 524, 214, 38);
		displayQuestionPanel.add(answersComboBox);
		
		
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
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Question");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(1124, 8, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Exams  /");
		navigationIndicatorMainLabel.setForeground(Color.DARK_GRAY);
		navigationIndicatorMainLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		navigationIndicatorMainLabel.setBounds(875, 8, 59, 17);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorMainLabel1 = new JLabel("Questionnaire  /");
		navigationIndicatorMainLabel1.setForeground(Color.DARK_GRAY);
		navigationIndicatorMainLabel1.setFont(new Font("Roboto", Font.PLAIN, 14));
		navigationIndicatorMainLabel1.setBounds(937, 8, 105, 17);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel1);
		
		JLabel navigationIndicatorMainLabel3 = new JLabel("Questions  /");
		navigationIndicatorMainLabel3.setForeground(Color.DARK_GRAY);
		navigationIndicatorMainLabel3.setFont(new Font("Roboto", Font.PLAIN, 14));
		navigationIndicatorMainLabel3.setBounds(1041, 8, 76, 17);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel3);
	}
}
