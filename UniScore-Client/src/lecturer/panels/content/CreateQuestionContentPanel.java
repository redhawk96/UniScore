package lecturer.panels.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import main.panels.LecturerPanel;
import models.Activity;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class CreateQuestionContentPanel extends ContentPanel{
	
	private JPanel contentPanel = new JPanel();
	private JPanel questionBodyPanel = new JPanel();
	private JPanel examInfoPanel = new JPanel();
	
	private JTextField questionText;
	private JTextField optionOneText;
	private JTextField optionTwoText;
	private JTextField optionThreeText;
	private JTextField optionFourText;
	private JComboBox<Object> answersComboBox;
	
	private Module module;
	private Exam exam;
	private int questionCount;
	
	public CreateQuestionContentPanel(Module module, Exam exam, int questionCount) {
		this.module = module;
		this.exam = exam;
		this.questionCount = questionCount;
		
		setContentPanel();
	}
	
	
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addExamInfoPanel();
		addCreateQuestionPanel();
	} 
	
	private void initializeContentPanel() {
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		
		questionBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		questionBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionBodyPanel);
		questionBodyPanel.setLayout(null);
	}
	
	
	private void addNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);

		JLabel navigationIndicatorMainLabel = new JLabel("Exams  /");
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setBounds(880, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 59, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);

		JLabel navigationIndicatorMainLabel1 = new JLabel("Questionnaire  /");
		navigationIndicatorMainLabel1.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorMainLabel1.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel1.setBounds(942, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 105, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel1);
		
		JLabel navigationIndicatorMainLabel2 = new JLabel("Questions  /");
		navigationIndicatorMainLabel2.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorMainLabel2.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel2.setBounds(1046, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 76, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel2);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Create");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1125, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 65, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	private void addExamInfoPanel() {
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		questionBodyPanel.add(examInfoPanel);
		
		JLabel moduleInfoLabel = new JLabel("Module Information");
		moduleInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moduleInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		moduleInfoLabel.setBounds(31, 11, 381, 14);
		examInfoPanel.add(moduleInfoLabel);
		
		JLabel examInfoLabel = new JLabel("Exam Information");
		examInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examInfoLabel.setBounds(489, 11, 349, 14);
		examInfoPanel.add(examInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		separator.setBounds(437, 11, 11, 116);
		examInfoPanel.add(separator);
		
		JLabel moduleCodeLabel = new JLabel("Code");
		moduleCodeLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleCodeLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		moduleCodeLabel.setBounds(31, 48, 99, 14);
		examInfoPanel.add(moduleCodeLabel);
		
		JLabel moduleNameLabel = new JLabel("Name");
		moduleNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		moduleNameLabel.setBounds(31, 76, 99, 14);
		examInfoPanel.add(moduleNameLabel);
		
		JLabel moduleLabel = new JLabel("Allocation");
		moduleLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		moduleLabel.setBounds(31, 104, 118, 14);
		examInfoPanel.add(moduleLabel);
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+module.getModuleId());
		selectedModuleCodeLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleCodeLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleCodeLabel.setBounds(158, 48, 269, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+module.getModuleName());
		selectedModuleNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleNameLabel.setBounds(158, 76, 269, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+module.getYear()+"  - S"+module.getSemester());
		selectedModuleAllocationLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleAllocationLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleAllocationLabel.setBounds(158, 105, 269, 14);
		examInfoPanel.add(selectedModuleAllocationLabel);
		
		JLabel examNameLabel = new JLabel("Name");
		examNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examNameLabel.setBounds(489, 48, 349, 14);
		examInfoPanel.add(examNameLabel);
		
		JLabel examDurationLabel = new JLabel("Duration");
		examDurationLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examDurationLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examDurationLabel.setBounds(489, 76, 349, 14);
		examInfoPanel.add(examDurationLabel);
		
		JLabel examStatusLabel = new JLabel("Status");
		examStatusLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examStatusLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examStatusLabel.setBounds(489, 104, 349, 14);
		examInfoPanel.add(examStatusLabel);
		
		JLabel selectedExamName = new JLabel(":  "+ exam.getExamName());
		selectedExamName.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamName.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamName.setBounds(601, 49, 237, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+exam.getDuration());
		selectedExamDuration.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamDuration.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamDuration.setBounds(601, 76, 237, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+exam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamStatus.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamStatus.setBounds(601, 104, 237, 17);
		examInfoPanel.add(selectedExamStatus);
		
		JPanel questionCountPanel = new JPanel();
		questionCountPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		questionCountPanel.setBounds(1046, 0, 153, 68);
		questionCountPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		examInfoPanel.add(questionCountPanel);
		questionCountPanel.setLayout(null);
		
		Integer remaningQuestionCount = (30-questionCount);
		String remaningQuestionCountStr = "";
		
		if(remaningQuestionCount.toString().length() < 2) {
			remaningQuestionCountStr = "0"+remaningQuestionCount.toString();
		} else {
			remaningQuestionCountStr = remaningQuestionCount.toString();
		}
		
		JLabel remaningQuestionCountLabel = new JLabel("RQ  "+remaningQuestionCountStr);
		remaningQuestionCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		remaningQuestionCountLabel.setBounds(0, 0, 153, 68);
		remaningQuestionCountLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		remaningQuestionCountLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
		questionCountPanel.add(remaningQuestionCountLabel);
		
		JPanel goBackButtonPanel = new JPanel();
		goBackButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
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
		goBackButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		goBackButtonPanel.setBounds(891, 0, 153, 138);
		examInfoPanel.add(goBackButtonPanel);
		
		JLabel goBackButtonLabel = new JLabel("BACK");
		goBackButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goBackButtonLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		goBackButtonLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
		goBackButtonLabel.setBounds(0, 0, 153, 138);
		goBackButtonPanel.add(goBackButtonLabel);
		
		JPanel saveQuestionPanel = new JPanel();
		saveQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
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

					Question newQuestion = new Question();
					newQuestion.setExamId(exam.getExamId());
					newQuestion.setQuestion(questionText.getText());
					newQuestion.setOption1(optionOneText.getText());
					newQuestion.setOption2(optionTwoText.getText());
					newQuestion.setOption3(optionThreeText.getText());
					newQuestion.setOption4(optionFourText.getText());
					newQuestion.setAnswer(answersComboBox.getSelectedIndex()+1);

					try {
						
						boolean executionStatus = (boolean) UniScoreClient.uniscoreInterface.addQuestion(newQuestion);
		
						if(executionStatus) {
							
							int updatedQuestionCount = questionCount + 1;
							
							if(updatedQuestionCount == 30) {	
								SuccessNotifier sn = new SuccessNotifier("Question was successfully created.", new QuestionNavigationPanel(), new DisplayQuestionsContentPanel(module, exam));
								
								UniScoreClient.uniscoreInterface.addLogActivity(new Activity("New question was added to exam "+exam.getExamId()+" from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
							
								sn.setVisible(true);
							}else {	
								SuccessNotifier sn = new SuccessNotifier("Question was successfully created.", new QuestionNavigationPanel(), new CreateQuestionContentPanel(module, exam, updatedQuestionCount));
								sn.setVisible(true);
							}
							
						} else {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to create question.\nError refferance : "+ExceptionList.SQL_FAILED_EXECUTION);
							en.setVisible(true);
						}
						
					} catch (RemoteException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to create question.\nError refferance : "+ExceptionList.REMOTE);
						en.setVisible(true);
						System.out.println("RemoteException execution thrown on CreateQuestionContentPanel.java file. Error : "+e.getCause());
					} catch (ClassNotFoundException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to create question.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
						en.setVisible(true);
						System.out.println("ClassNotFoundException execution thrown on CreateQuestionContentPanel.java file. Error : "+e.getCause());
					} catch (SQLException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to create question.\nError refferance : "+ExceptionList.SQL);
						en.setVisible(true);
						System.out.println("SQLException execution thrown on CreateQuestionContentPanel.java file. Error : "+e.getCause());
					}
				}
			}
				
		});
		saveQuestionPanel.setLayout(null);
		saveQuestionPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		saveQuestionPanel.setBounds(1046, 70, 153, 68);
		examInfoPanel.add(saveQuestionPanel);
		
		JLabel saveQuestionLabel = new JLabel("SAVE");
		saveQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveQuestionLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		saveQuestionLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
		saveQuestionLabel.setBounds(0, 0, 153, 68);
		saveQuestionPanel.add(saveQuestionLabel);
	}
	
	
	private void addCreateQuestionPanel() {
		JPanel displayQuestionPanel = new JPanel();
		displayQuestionPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		displayQuestionPanel.setBounds(0, 172, 1199, 641);
		questionBodyPanel.add(displayQuestionPanel);
		displayQuestionPanel.setLayout(null);
		
		JLabel questionLabel = new JLabel("Question                   :");
		questionLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		questionLabel.setBorder(null);
		questionLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		questionLabel.setBounds(31, 11, 155, 65);
		displayQuestionPanel.add(questionLabel);
		
		JLabel optionOneLabel = new JLabel("Option 01                  :");
		optionOneLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionOneLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionOneLabel.setBounds(31, 111, 155, 65);
		displayQuestionPanel.add(optionOneLabel);
		
		JLabel answerLabel = new JLabel("Answer                     :");
		answerLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		answerLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		answerLabel.setBounds(31, 511, 155, 65);
		displayQuestionPanel.add(answerLabel);
		
		JLabel optionTwoLabel = new JLabel("Option 02                  :");
		optionTwoLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionTwoLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionTwoLabel.setBounds(31, 211, 155, 65);
		displayQuestionPanel.add(optionTwoLabel);
		
		JLabel optionThreeLabel = new JLabel("Option 03                  :");
		optionThreeLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionThreeLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionThreeLabel.setBounds(31, 311, 155, 65);
		displayQuestionPanel.add(optionThreeLabel);
		
		JLabel optionFourLabel = new JLabel("Option 04                  :");
		optionFourLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionFourLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionFourLabel.setBounds(31, 411, 155, 65);
		displayQuestionPanel.add(optionFourLabel);
		
		questionText = new JTextField();
		questionText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		questionText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		questionText.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		questionText.setBounds(196, 25, 540, 38);
		displayQuestionPanel.add(questionText);
		questionText.setColumns(10);
		
		optionOneText = new JTextField();
		optionOneText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionOneText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		optionOneText.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionOneText.setColumns(10);
		optionOneText.setBounds(196, 124, 540, 38);
		displayQuestionPanel.add(optionOneText);
		
		optionTwoText = new JTextField();
		optionTwoText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionTwoText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		optionTwoText.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionTwoText.setColumns(10);
		optionTwoText.setBounds(196, 224, 540, 38);
		displayQuestionPanel.add(optionTwoText);
		
		optionThreeText = new JTextField();
		optionThreeText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionThreeText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		optionThreeText.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		optionThreeText.setColumns(10);
		optionThreeText.setBounds(196, 324, 540, 38);
		displayQuestionPanel.add(optionThreeText);
		
		optionFourText = new JTextField();
		optionFourText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		optionFourText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		optionFourText.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
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
	        	lable.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
	        }
	        return lable;
	      }
	    });
		
		answersComboBox.setUI(new BasicComboBoxUI() {
		    @Override
		    protected ComboPopup createPopup() {
		        BasicComboPopup basicComboPopup = new BasicComboPopup(answersComboBox);
		        basicComboPopup.setBorder(new MatteBorder(0, 1, 1, 1, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		        return basicComboPopup;
		    }
		});
		

		answersComboBox.setOpaque(false);
		answersComboBox.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		answersComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"   Option 01", "   Option 02", "   Option 03", "   Option 04"}));
		answersComboBox.setSelectedIndex(-1);
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
}
