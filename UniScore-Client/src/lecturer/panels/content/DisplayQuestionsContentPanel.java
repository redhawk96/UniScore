/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import main.panels.LecturerPanel;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class DisplayQuestionsContentPanel extends ContentPanel{
	
	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();
		
	// Declaring and initializing new JPanel to act as an wrapper to contain examInfoPanel and scrollPane
	private JPanel questionBodyPanel = new JPanel();
	
	// Declaring and initializing new JPanel to act as an wrapper to contain the elements which is responsible to display selected exam/module information. Located below navigationIndicatorPanel
	private JPanel examInfoPanel = new JPanel();
	
	// Declaring and initializing new ContentTable(JTable with overridden methods) to display all the questions for the selected exam
	private ContentTable table = new ContentTable();
	
	// Declaring and initializing new JScrollPane to contain the ContentTable in an overflow 
	private JScrollPane scrollPane = new JScrollPane();
	
	// Declaring JTextField to act as the search field to lookup for any paticular question by question title
	private JTextField searchText;
	
	// Declaring properties to get required information to display all questions under a paticular exam
	private Module module;
	private Exam exam;
	
	// Declaring and initializing questionCount, questionCount will be used to determine whether a lecturer is allowed to create a new question or not depending on allowed (questions - available question)
	private Integer questionCount = -1;

	/*
	 * DisplayQuestionsContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 * @param module 			Module object contains the necessary module information about the selected exam
	 * @param exam  			Exam object contains the necessary information about the selected exam
	 */
	public DisplayQuestionsContentPanel(Module module, Exam exam) {
		// Initializing properties required to display the information of the selected exam and filter questions accordingly
		this.module = module;
		this.exam = exam;
		
		// Adding elements to the ContentPanel
		setContentPanel();
	}

	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addExamInfoPanel();
		addSearchField();
		// Calling displayQuestionList method with an empty string to retrieve all questions without filtering
		displayQuestionList("");
	}
	
	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color
	 */
	private void initializeContentPanel(){
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		
		questionBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		questionBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionBodyPanel);
		questionBodyPanel.setLayout(null);
	}

	/*
	 * Method addNavigationIndicator adds UI layout(styling) to navigationIndicatorPanel which shows the current navigated panel on the top of ContentPanel
	 * navigationIndicatorPanel is a sub element under ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color, JLabel text/text-color/font-size/boundaries 
	 */
	private void addNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Exams  /");
		navigationIndicatorMainLabel.setBounds(947, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 59, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorMainLabel1 = new JLabel("Questionnaire  /");
		navigationIndicatorMainLabel1.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel1.setBounds(1009, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 105, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel1.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel1);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Questions");
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1113, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 76, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	/*
	 * Method addExamInfoPanel adds UI layout(styling) to examInfoPanel which holds selected exam/module information on the top of questionBodyPanel
	 * examInfoPanel is a sub element under questionBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries, JSeparator orientation/backgroung-color/boundaries
	 */
	private void addExamInfoPanel() {
		// Method setQuestionCount called to change the default -1 figure to the actual question cout for the selected exam
		setQuestionCount();
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
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
		
		/*
		 * Calculating the number of remaning questions for the selected exam out of 30
		 */
		Integer remaningQuestionCount = (30-questionCount);
		String remaningQuestionCountStr = "";
		
		// If the number is less than 10, 0 is being added at the front of the number. If greater than 10, number will be displayed as it is
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
		goBackButtonPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		goBackButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		goBackButtonPanel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be navigated to QuestionnaireContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
				LecturerPanel.selectedContent = new QuestionnaireContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
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
		
		/*
		 * If remaningQuestionCount is equal to 0, then lecturer will not be allowed to create a new question for the selected exam, hence the else block will be executed
		 * and if not, lecturer will be directed to CreateQuestionContentPanel on mouseClick event 
		 */
		if(remaningQuestionCount != 0) {
			
			JPanel createQuestionPanel = new JPanel();
			createQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			createQuestionPanel.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * Lecturer will be navigated to CreateQuestionContentPanel on mouse click
				 * @param arg0 to get information about the mosue click 
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
					LecturerPanel.selectedContent = new CreateQuestionContentPanel(module, exam, questionCount);
					LecturerPanel.setSelectedPanel();
				}
			});
			createQuestionPanel.setLayout(null);
			createQuestionPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			createQuestionPanel.setBounds(1046, 70, 153, 68);
			examInfoPanel.add(createQuestionPanel);
			
			JLabel createQuestionLabel = new JLabel("CREATE");
			createQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
			createQuestionLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			createQuestionLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			createQuestionLabel.setBounds(0, 0, 153, 68);
			createQuestionPanel.add(createQuestionLabel);
			
		} else {
			
			JPanel createQuestionPanel = new JPanel();
			createQuestionPanel.setLayout(null);
			createQuestionPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			createQuestionPanel.setBounds(1046, 70, 153, 68);
			createQuestionPanel.setBorder(new MatteBorder(1, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
			examInfoPanel.add(createQuestionPanel);
			
			JLabel createQuestionLabel = new JLabel("MAX Q");
			createQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
			createQuestionLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			createQuestionLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			createQuestionLabel.setBounds(0, 0, 153, 68);
			createQuestionPanel.add(createQuestionLabel);
		}
		
		examInfoPanel.repaint();
	}
	
	/*
	 * Method setQuestionCount retrieves the question count for a paticular exam
	 */
	public void setQuestionCount() {
		
		try {
			/*
			 * Method getExaminationQuestionCount accepts a Question object which has the exam id set, hence a new Question object is created to set the selected exam id
			 * Method will return an integer of the question count recoreded from the database for a paticular exam
			 */
			Question q = new Question();
			q.setExamId(exam.getExamId());
			questionCount = (int)UniScoreClient.uniscoreInterface.getExaminationQuestionCount(q);
			
		/*
		 * If there was exception thrown when executing the retrieval of question count for the selected exam,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		}
	}
	
	/*
	 * Method displayQuestionList adds UI layout(styling) to questionBodyPanel below the examInfoPanel
	 * @param searchText contains the text which is used to filter the question list by question title
	 * scrollPane which acts as an wrapper to the ContentTable is a sub element underquestionBodyPanel
	 * UI layout categorized as ContentTable text/text-color/font-size/columns/background-color
	 */
	private void displayQuestionList(String searchText) {
			
		try {
			// Creating a new DefaultTableModel to declare the column names  
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"}, 0);
			
			// Method getExamQuestionsBySearch will retrieve all the questions for the selected exam filtered through the searched keyword, initially the search text will be empty to retrieve all questions without filtering
			List<Question> questionList  = (List<Question>) UniScoreClient.uniscoreInterface.getExamQuestionsBySearch(searchText);
			
			// Looping the retrieved list of questions through a foreach loop to add rows to the model(DefaultTableModel). One database record is equal to one row in the model(DefaultTableModel)
			for (Question qes : questionList) {
				if(qes.getExamId() == exam.getExamId()) {
					// When adding a row to the model, make sure than column values are parallel(relevant) to the column headers. White spaces are added to enhance UX and not required by default
					model.addRow(new Object[] {qes.getQuestionId(), "     "+qes.getQuestion(),  "     "+qes.getOption1(), "     "+qes.getOption2(),  "     "+qes.getOption3(),   "     "+qes.getOption4(), qes.getAnswer()});
				}
			}

			table.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * Lecturer will be navigated to DisplayQuestionContentPanel on double mouse click
				 * @param mouseEvent to get information about the mosue click
				 */
				@Override
				public void mousePressed(MouseEvent mouseEvent) {
					// Checking if the click event has a count > 2 to execute the following, and selected row is not empty/null
			        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

			        	// New question object is created and the selected question properties will be set accordingly to be updated on the DisplayQuestionContentPanel
			            Question selectedQuestion = new Question();
			            selectedQuestion.setQuestionId(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
			            selectedQuestion.setQuestion(table.getModel().getValueAt(table.getSelectedRow(), 1).toString().trim());
			            selectedQuestion.setOption1(table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim());
			            selectedQuestion.setOption2(table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim());
			            selectedQuestion.setOption3(table.getModel().getValueAt(table.getSelectedRow(), 4).toString().trim());
			            selectedQuestion.setOption4(table.getModel().getValueAt(table.getSelectedRow(), 5).toString().trim());
			            selectedQuestion.setAnswer(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()));
			            
			            // Navigating the lecturer to the DisplayQuestionContentPanel to update the selected question
			            LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
						LecturerPanel.selectedContent = new DisplayQuestionContentPanel(module, exam, selectedQuestion);
						LecturerPanel.setSelectedPanel();
			        }
			    }
			});
			
			// Styling ContentTable to enhance UX
			table.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			table.setUpdateSelectionOnSort(false);
			table.setFocusTraversalKeysEnabled(false);
			table.setFocusable(false);
			table.setAutoCreateRowSorter(true);
			table.setEditingColumn(0);
			table.setEditingRow(0);
			table.setRequestFocusEnabled(false);
			table.setVerifyInputWhenFocusTarget(false);
			table.setBorder(null);
			
			// Adding model(DefaultTableModel) the the ContentTable
			table.setModel(model);
			
			// Setting width of 'Answers' column
			table.getColumn("Answer").setMinWidth(0);
			table.getColumn("Answer").setMaxWidth(120);
			table.getColumn("Answer").setWidth(120);

			
			// DefaultTableCellRenderer object created to add alignment. In this case, setting the cloumn content alignment to center
            DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
            centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);
            
            // TableColumnModel object created to get the column structure in the ContentTable
            TableColumnModel columnModel = table.getColumnModel();
            
            // Aligning cloumns by their index
            columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
            columnModel.getColumn(6).setCellRenderer(centerAlingedCell);
            
            // Removing question id column from the ContentTable, but will still be able to access by column index
            columnModel.removeColumn(columnModel.getColumn(0));
			
            // Removing horizontal cell borders
            table.setShowHorizontalLines(false);
            
            // Setting cursor type to pointer
			table.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			
			// Styling ContentTable to enhance UX
			table.setFillsViewportHeight(true);
			table.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
			table.getTableHeader().setOpaque(false);
			table.getTableHeader().setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
			table.getTableHeader().setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			table.getTableHeader().setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
			table.setSelectionBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(32);
			table.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
			table.isCellEditable(1, 1);
			scrollPane.setBounds(0, 220, 1199, 593);
			questionBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
		
		/*
		 * If there was exception thrown when executing the retrieval of questions for the selected exam,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} 
	}
	
	/*
	 * Method addSearchField adds UI layout(styling) to questionBodyPanel below the examInfoPanel
	 * searchText and searchLabel are a sub element under questionBodyPanel
	 * UI layout categorized as JLabel text/text-color/font-size/boundaries, JTextField text/text-color/font-size/boundaries/columns/background-color
	 */
	private void addSearchField() {
		searchText = new JTextField();
		searchText.addKeyListener(new KeyAdapter() {
			/*
			 * Method keyTyped to handle keyboard type events
			 * displayQuestionList method will be executed(repainted) on keyboard press to search of the typed text in the JTextField
			 * @param arg0 to get information about the key press event 
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {
				displayQuestionList(searchText.getText().trim());
			}
		});
		searchText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		searchText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		searchText.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		searchText.setBounds(978, 172, 219, 31);
		questionBodyPanel.add(searchText);
		searchText.setColumns(10);

		JLabel searchLabel = new JLabel("Search    :");
		searchLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		searchLabel.setBounds(908, 172, 60, 31);
		questionBodyPanel.add(searchLabel);
	}

	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * @returns JPanel 	Contains completed layout of with the add sub elements 
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
