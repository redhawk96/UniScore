/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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

@SuppressWarnings("serial")
public class QuestionnaireContentPanel extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and questionnaireBodyPanel
	private JPanel contentPanel = new JPanel();
		
	// Declaring and initializing new JPanel to act as an wrapper to contain examInfoPanel and scrollPane
	private JPanel questionnaireBodyPanel = new JPanel();
	
	// Declaring and initializing new JPanel to act as an wrapper to contain the elements which is responsible to show currently selected exam/module information. Located below navigationIndicatorPanel
	private JPanel examInfoPanel = new JPanel();
	
	// Declaring and initializing new ContentTable(JTable with overridden methods) to display all the questions for the selected exam
	private ContentTable table = new ContentTable();
	
	// Declaring and initializing new JScrollPane to contain the ContentTable in an overflow 
	private JScrollPane scrollPane = new JScrollPane();
	
	// Declaring properties to get required information about the selected exam
	private Exam selectedExam;
	private Module selectedExamModule;
	
	/*
	 * QuestionnaireContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 */
	public QuestionnaireContentPanel() {
		// Adding elements to the ContentPanel
		setContentPanel();
	}

	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		displayExamList();
	}
	
	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color
	 */
	private void initializeContentPanel() {
		contentPanel.setName("questionnaire");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		questionnaireBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		questionnaireBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionnaireBodyPanel);
		questionnaireBodyPanel.setLayout(null);
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
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(1073, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Exams");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1138, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 51, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	/*
	 * Method setExamInfoPanel adds UI layout(styling) to examInfoPanel which has the selected exam/module information on the top of questionnaireBodyPanel
	 * examInfoPanel is a sub element under questionBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries, JSeparator orientation/backgroung-color/boundaries
	 */
	private void setExamInfoPanel() {
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		questionnaireBodyPanel.add(examInfoPanel);
		
		JLabel moduleInfoLabel = new JLabel("Module Information");
		moduleInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moduleInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		moduleInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		moduleInfoLabel.setBounds(31, 11, 406, 14);
		examInfoPanel.add(moduleInfoLabel);
		
		JLabel examInfoLabel = new JLabel("Exam Information");
		examInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examInfoLabel.setBounds(562, 11, 474, 14);
		examInfoPanel.add(examInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		separator.setBounds(510, 11, 11, 116);
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
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+selectedExamModule.getModuleId());
		selectedModuleCodeLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleCodeLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleCodeLabel.setBounds(158, 48, 279, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+selectedExamModule.getModuleName());
		selectedModuleNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleNameLabel.setBounds(158, 76, 279, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+selectedExamModule.getYear()+"  - S"+selectedExamModule.getSemester());
		selectedModuleAllocationLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleAllocationLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
		examInfoPanel.add(selectedModuleAllocationLabel);
		
		JLabel examNameLabel = new JLabel("Name");
		examNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examNameLabel.setBounds(562, 48, 109, 14);
		examInfoPanel.add(examNameLabel);
		
		JLabel examDurationLabel = new JLabel("Duration");
		examDurationLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examDurationLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examDurationLabel.setBounds(562, 76, 109, 14);
		examInfoPanel.add(examDurationLabel);
		
		JLabel examStatusLabel = new JLabel("Status");
		examStatusLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examStatusLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		examStatusLabel.setBounds(562, 104, 109, 14);
		examInfoPanel.add(examStatusLabel);
		
		JLabel selectedExamName = new JLabel(":  "+selectedExam.getExamName());
		selectedExamName.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamName.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamName.setBounds(674, 49, 362, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+selectedExam.getDuration());
		selectedExamDuration.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamDuration.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamDuration.setBounds(674, 76, 362, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+selectedExam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamStatus.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamStatus.setBounds(674, 104, 362, 17);
		examInfoPanel.add(selectedExamStatus);
		
		JPanel showQuestionButtonPanel = new JPanel();
		showQuestionButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		showQuestionButtonPanel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events
			 * Lecturer will be navigated to DisplayQuestionsContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
				LecturerPanel.selectedContent = new DisplayQuestionsContentPanel(selectedExamModule, selectedExam);
				LecturerPanel.setSelectedPanel();
			}
		});
		showQuestionButtonPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		showQuestionButtonPanel.setBounds(1046, 0, 153, 138);
		examInfoPanel.add(showQuestionButtonPanel);
		showQuestionButtonPanel.setLayout(null);
		
		JLabel showQuestionButtonLabel = new JLabel("SHOW");
		showQuestionButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showQuestionButtonLabel.setBounds(0, 0, 153, 138);
		showQuestionButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		showQuestionButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		showQuestionButtonPanel.add(showQuestionButtonLabel);
		examInfoPanel.repaint();
	}
	
	/*
	 * Method displayExamList adds UI layout(styling) to questionnaireBodyPanel below the examInfoPanel
	 * scrollPane which acts as an wrapper to the ContentTable is a sub element under questionnaireBodyPanel
	 * UI layout categorized as ContentTable text/text-color/font-size/columns/background-color
	 */
	private void displayExamList(){
		try {
			// Creating a new DefaultTableModel to declare the column names  
			DefaultTableModel model = new DefaultTableModel(new String[] {"EID", "Allocation",  "Module ID", "Module", "Exam Name"}, 0);
	
			// Method getModulesByRelevance accepts a Module object with lecturer's id set and 2 integers(not relevant in this case, thereby both set to 0)
			Module module = new Module();
			module.setTeacherId(UniScoreClient.authUser.getUserId());
		
		
			// Method getModulesByRelevance will retrieve all the allocated modules for the signed-in lecturer
			List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, 0, 0);
			
			// Count is set to 0 to set the first row on the model to and display it on examInfoPanel
			int count = 0;
			
			// Looping the retrieved list of modules through a foreach loop
			for (Module mod : moduleList) {
				
				// Method getExamsByModule accepts an Exam object with module id set, hence a new Exam object is created and module id is set with the module in loop at the time
				Exam tempExam = new Exam();
				tempExam.setModuleId(mod.getModuleId());

				// Method getExamsByModule will retrieve all the exams for the module in loop
				List<Exam> examList = (List<Exam>) UniScoreClient.uniscoreInterface.getExamsByModule(tempExam);
				
				// Looping the retrieved list of exams for the module in loop through a foreach loop to add rows to the model(DefaultTableModel). One database record is equal to one row in the model(DefaultTableModel)
				for(Exam e : examList) {
					// Determining whether the exam in loop has a status of 'not started'
					if(e.getStatus().equalsIgnoreCase("Not started")) {
						/// When adding a row to the model, make sure than column values are parallel(relevant) to the column headers. White spaces are added to enhance UX and not required by default
						model.addRow(new Object[] {e.getExamId(), "Y" + mod.getYear() + " - S" + mod.getSemester(), mod.getModuleId(), "     "+mod.getModuleName(),  "     "+e.getExamName(), "     "+e.getStatus()});
						
						// This statement will only be true for the first exam, this will be the values to examInfoPanel by default
						if (count < 1) {
							selectedExam = e;
							selectedExamModule = mod;
							setExamInfoPanel();
						}
						// Inorder to avoid examInfoPanel being repainted only once
						count++;
					}
					
				}		
			}
		
			table.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
	
			table.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * examInfoPanel will be repainted with selected(clicked) exam on mouse click
				 * @param mouseEvent to get information about the mosue click
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(table.getSelectedRow() != -1) {
						try {
							 // Method getExam will retrieve all the properties regarding a paticular exam, method accepts an Exam object with exam id set
							 Exam selectedTempExam = new Exam();
							 selectedTempExam.setExamId(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
							 Exam sExam = (Exam) UniScoreClient.uniscoreInterface.getExam(selectedTempExam);
							 
							 // Method getModule will retrieve all the properties regarding a paticular module, method accepts a Module object with module id set
							 Module selectedTempModule = new Module();
							 selectedTempModule.setModuleId(sExam.getModuleId());
							 Module selectedModule = (Module) UniScoreClient.uniscoreInterface.getModule(selectedTempModule);
							 
							 // Setting existing values with selected exam and its module properties to repaint the examInfoPanel
							 selectedExam = sExam;
							 selectedExamModule = selectedModule;
							 
							 // Repainting examInfoPanel with the selected exam
							 setExamInfoPanel();
							 
						/*
						 * If there was exception thrown when executing the retrieval of module and exam properties for the selected exam,
						 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
						 */
						} catch (RemoteException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected question.\nError refferance : "+ExceptionList.REMOTE);
							en.setVisible(true);
							System.out.println("RemoteException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
						} catch (ClassNotFoundException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected question.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
							en.setVisible(true);
							System.out.println("ClassNotFoundException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
						} catch (SQLException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected question.\nError refferance : "+ExceptionList.SQL);
							en.setVisible(true);
							System.out.println("SQLException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
						}
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
			
			// DefaultTableCellRenderer object created to add alignment. In this case, setting the cloumn content alignment to center
	        DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
	        centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);
	        
	        // TableColumnModel object created to get the column structure in the ContentTable
	        TableColumnModel columnModel = table.getColumnModel();
	        
	        // Aligning cloumns by their index
	        columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
	        columnModel.getColumn(1).setCellRenderer(centerAlingedCell);
	        columnModel.getColumn(2).setCellRenderer(centerAlingedCell);
	        
	        // Removing exam id column from the ContentTable, but will still be able to access by column index
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
			scrollPane.setBounds(0, 171, 1199, 642);
			questionnaireBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
		
		/*
		 * If there was exception thrown when executing the retrieval of exams filtered through allocated modules for the signed-in lecturer,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on QuestionnaireContentPanel.java file. Error : "+e.getCause());
		}
	}

	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * @returns JPanel 	Contains completed layout of with the add sub elements 
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
