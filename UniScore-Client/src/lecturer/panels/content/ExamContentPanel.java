/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.panels.ContentPanel;
import com.utils.BarChartFrame;
import com.utils.ContentTable;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.SuccessNotifier;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Activity;
import models.Exam;
import models.Module;
import models.Submission;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings("serial")
public class ExamContentPanel extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and examBodyPanel
	private JPanel contentPanel = new JPanel();
			
	// Declaring and initializing new JPanel to act as an wrapper to contain examInfoPanel and scrollPane
	private JPanel examBodyPanel = new JPanel();
	
	// Declaring and initializing new JPanel to act as an wrapper to contain the elements which is responsible to show currently selected exam/module information. Located below navigationIndicatorPanel
	private JPanel examInfoPanel = new JPanel();
	
	// Declaring and initializing new ContentTable(JTable with overridden methods) to display all the allocated exams
	private ContentTable table = new ContentTable();
	
	// Declaring and initializing new JScrollPane to contain the ContentTable in an overflow 
	private JScrollPane scrollPane = new JScrollPane();
	
	// Declaring properties to get required information about the selected(clicked) exam
	private Exam selectedExam;
	private Module selectedExamModule;
	
	/*
	 * ExamContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 */
	public ExamContentPanel() {
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
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		examBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(examBodyPanel);
		examBodyPanel.setLayout(null);
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
	 * Method setExamInfoPanel adds UI layout(styling) to examInfoPanel which has the selected exam/module information on the top of questionBodyPanel
	 * addExamInfoPanel is a sub element under questionBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries, JSeparator orientation/backgroung-color/boundaries
	 */
	private void setExamInfoPanel() {
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		examBodyPanel.add(examInfoPanel);
		
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
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+selectedExamModule.getModuleId());
		selectedModuleCodeLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleCodeLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleCodeLabel.setBounds(158, 48, 269, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+selectedExamModule.getModuleName());
		selectedModuleNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedModuleNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedModuleNameLabel.setBounds(158, 76, 269, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+selectedExamModule.getYear()+"  - S"+selectedExamModule.getSemester());
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
		
		JLabel selectedExamName = new JLabel(":  "+ selectedExam.getExamName());
		selectedExamName.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamName.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamName.setBounds(601, 49, 237, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+selectedExam.getDuration());
		selectedExamDuration.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamDuration.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamDuration.setBounds(601, 76, 237, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+selectedExam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		selectedExamStatus.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		selectedExamStatus.setBounds(601, 104, 237, 17);
		examInfoPanel.add(selectedExamStatus);
		
		JPanel examSubmissionCountPanel = new JPanel();
		examSubmissionCountPanel.setBorder(new MatteBorder(0, 1, 1, 1, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		examSubmissionCountPanel.setLayout(null);
		examSubmissionCountPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examSubmissionCountPanel.setBounds(891, 0, 153, 68);
		examInfoPanel.add(examSubmissionCountPanel);
		
		// Submission count is set to -1 by default
		Integer examSubmissionCount = -1; 
		
		try {
			/*
			 * Method getExaminationSubmissionCount accepts a Submission object which has the exam id set, hence a new Submission object is created to set the selected exam id
			 * Method will return an integer of the submission count recoreded from the database for a paticular exam
			 */
			Submission submission = new Submission();
			submission.setExamId(selectedExam.getExamId());;
			examSubmissionCount = UniScoreClient.uniscoreInterface.getExaminationSubmissionCount(submission);
			
		/*
		 * If there was exception thrown when executing the retrieval of submission count for the selected exam,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam submissions.\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam submissions.\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam submissions.\nError refferance : 500");
			en.setVisible(true);
			System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		}
		
		String examSubmissionCountStr = "";
		
		// Formatting the number format to 2 integers. If the number is less than 10, 0 is being added at the front of the number. If greater than 10, number will be displayed as it is
		if(examSubmissionCount.toString().length() < 2) {
			examSubmissionCountStr = "0"+examSubmissionCount.toString();
		} else {
			examSubmissionCountStr = examSubmissionCount.toString();
		}
		
		JLabel examSubmissionCountPanelLabel = new JLabel("ES  "+examSubmissionCountStr);
		examSubmissionCountPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examSubmissionCountPanelLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		examSubmissionCountPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
		examSubmissionCountPanelLabel.setBounds(0, 0, 153, 68);
		examSubmissionCountPanel.add(examSubmissionCountPanelLabel);

		// If the examSubmissionCount > 0,  meaning the exam has already started or finished, lecturer can e-mail, print and view real-time statistics over a bar chart of academic performance based on the selected exam
		if(examSubmissionCount != 0) {
			
			JPanel examStatPanel = new JPanel();
			examStatPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			examStatPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			examStatPanel.setBounds(1046, 0, 153, 68);
			examStatPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
			examInfoPanel.add(examStatPanel);
			examStatPanel.setLayout(null);
			
			examStatPanel.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * Lecturer will be poped-up with a new JFrame contaning a bar chart of selected exam student performances(mark allocations) on mouse click
				 * @param arg0 to get information about the mosue click 
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						// Method getSubmissionDatasetByExam accepts a Submission object with the exam id set, hence new Submission onject is created to set the selected exam id
						Submission tempSubmission = new Submission();
						tempSubmission.setExamId(selectedExam.getExamId());
						
						// Plotting a bar chart on a new JFrame of the student marks for the selected exam 
						BarChartFrame examMarkStats = new BarChartFrame("Exam Statistics", selectedExam.getExamName() + " Exam Statistics", "Score Range", "No of Students", UniScoreClient.uniscoreInterface.getSubmissionDatasetByExam(tempSubmission));

						// Defining JFrame properties
						examMarkStats.setSize(950, 600);
						examMarkStats.setLocationRelativeTo(null);
						examMarkStats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						examMarkStats.setVisible(true);
						
						// Adding a record to the database of the display of academic performances for the selected exam followed by the lecturer id under activities table
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Academic performance on exam "+selectedExam.getExamId()+" was viewed from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));

					
					/*
					 * If there was exception thrown when plotting the graph or recording activity,
					 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
					 */
					} catch (RemoteException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.REMOTE);
						en.setVisible(true);
						System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					} catch (ClassNotFoundException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
						en.setVisible(true);
						System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					} catch (SQLException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to generate exam submission statistics.\nError refferance : "+ExceptionList.SQL);
						en.setVisible(true);
						System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					}
				}
			});
			
			JLabel examStatPanelLabel = new JLabel("STATS");
			examStatPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			examStatPanelLabel.setBounds(0, 0, 153, 68);
			examStatPanelLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			examStatPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			examStatPanel.add(examStatPanelLabel);
			
			
			JPanel sendMailPanel = new JPanel();
			sendMailPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			sendMailPanel.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * Lecturer will be popped-up with a new SubmissionMailer(JFrame) to enter the mail address(es) and mail subject to mail the academic perormances of the selected exam on mouse click
				 * @param arg0 to get information about the mosue click 
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					SubmissionMailer sm = new SubmissionMailer(selectedExam);	
					sm.setVisible(true);
				}
			});
			sendMailPanel.setLayout(null);
			sendMailPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			sendMailPanel.setBounds(891, 70, 153, 68);
			examInfoPanel.add(sendMailPanel);
			
			JLabel sendMailPanelLabel = new JLabel("MAIL");
			sendMailPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			sendMailPanelLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			sendMailPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			sendMailPanelLabel.setBounds(0, 0, 153, 68);
			sendMailPanel.add(sendMailPanelLabel);	
			
			JPanel printSubmissionReportPanel = new JPanel();
			printSubmissionReportPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			printSubmissionReportPanel.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * Lecturer can generate a report of the academic perormances of the selected exam to a pre-defined location on mouse click
				 * @param arg0 to get information about the mosue click 
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						// Method printSubmissionReport accepts 2 Strings(modules id and exam name) and an Integer(exam id) as parameters to generate a report of the academic performances of the selected exam
						UniScoreClient.uniscoreInterface.printSubmissionReport(selectedExam.getExamId(), selectedExam.getExamName(), selectedExam.getModuleId());
						
						// Adding a record to the database of the report creation of academic performances for the selected exam followed by the lecturer id under activities table
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity("New submissions report for exam "+selectedExam.getExamId()+" was printed from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
						
						// If the report was successfully generated, then success message will be popped-up in a new JFrame
						SuccessNotifier sn = new SuccessNotifier("Report was successfully saved.", null, null);
						sn.setVisible(true);
						
					/*
					 * If there was exception thrown when generating the academic permormance report for the selected exam,
					 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
					 */
					} catch (RemoteException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : "+ExceptionList.REMOTE);
						en.setVisible(true);
						System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					} catch (ClassNotFoundException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
						en.setVisible(true);
						System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					} catch (SQLException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : "+ExceptionList.SQL);
						en.setVisible(true);
						System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					} catch (JRException e) {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : "+ExceptionList.JASPER_REPORT);
						en.setVisible(true);
						System.out.println("JRException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
					}				
				}
			});
			printSubmissionReportPanel.setLayout(null);
			printSubmissionReportPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			printSubmissionReportPanel.setBounds(1046, 70, 153, 68);
			examInfoPanel.add(printSubmissionReportPanel);
			
			JLabel printSubmissionReportPanelLabel = new JLabel("REPORT");
			printSubmissionReportPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			printSubmissionReportPanelLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			printSubmissionReportPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			printSubmissionReportPanelLabel.setBounds(0, 0, 153, 68);
			printSubmissionReportPanel.add(printSubmissionReportPanelLabel);	
			
		} else {
			
			JPanel examStatPanel = new JPanel();
			examStatPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			examStatPanel.setBounds(1046, 0, 153, 68);
			examStatPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
			examInfoPanel.add(examStatPanel);
			examStatPanel.setLayout(null);
			
			JLabel examStatPanelLabel = new JLabel("STATS");
			examStatPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			examStatPanelLabel.setBounds(0, 0, 153, 68);
			examStatPanelLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			examStatPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			examStatPanel.add(examStatPanelLabel);
			
			JPanel sendMailPanel = new JPanel();
			sendMailPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
			sendMailPanel.setLayout(null);
			sendMailPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			sendMailPanel.setBounds(891, 70, 153, 68);
			examInfoPanel.add(sendMailPanel);
			
			JLabel sendMailPanelLabel = new JLabel("MAIL");
			sendMailPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			sendMailPanelLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			sendMailPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			sendMailPanelLabel.setBounds(0, 0, 153, 68);
			sendMailPanel.add(sendMailPanelLabel);	
			
			JPanel printSubmissionReportPanel = new JPanel();
			printSubmissionReportPanel.setBorder(new MatteBorder(1, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
			printSubmissionReportPanel.setLayout(null);
			printSubmissionReportPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			printSubmissionReportPanel.setBounds(1046, 70, 153, 68);
			examInfoPanel.add(printSubmissionReportPanel);
			
			JLabel printSubmissionReportPanelLabel = new JLabel("REPORT");
			printSubmissionReportPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			printSubmissionReportPanelLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			printSubmissionReportPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
			printSubmissionReportPanelLabel.setBounds(0, 0, 153, 68);
			printSubmissionReportPanel.add(printSubmissionReportPanelLabel);	
		}
		examInfoPanel.repaint();
	}
	
	/*
	 * Method displayExamList adds UI layout(styling) to examBodyPanel below the examInfoPanel
	 * scrollPane whch acts as an wrapper to the ContentTable is a sub element under examBodyPanel
	 * UI layout categorized as ContentTable text/text-color/font-size/columns/background-color
	 */
	private void displayExamList() {
	
		try { 
			// Creating a new DefaultTableModel to declare the column names  
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Allocation", "Module ID", "Module", "Exam Name", "Exam Status"}, 0);
		
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
					// Determining whether the exam in loop has a status either started or finished
					if(e.getStatus().equalsIgnoreCase("Started") || e.getStatus().equalsIgnoreCase("Finished")) {
						// When adding a row to the model, make sure than column values are parallel(relevant) to the column headers. White spaces are added to enhance UX and not required by default
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
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected exam details.\nError refferance : "+ExceptionList.REMOTE);
								en.setVisible(true);
								System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
							} catch (ClassNotFoundException e) {
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected exam details.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
								en.setVisible(true);
								System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
							} catch (SQLException e) {
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected exam details.\nError refferance : "+ExceptionList.SQL);
								en.setVisible(true);
								System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
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
	        columnModel.getColumn(1).setCellRenderer(centerAlingedCell);
	        columnModel.getColumn(2).setCellRenderer(centerAlingedCell);
	        columnModel.getColumn(5).setCellRenderer(centerAlingedCell);
	        
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
			examBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
	
		/*
		 * If there was exception thrown when executing the retrieval of exams filtered through allocated modules for the signed-in lecturer,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
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

