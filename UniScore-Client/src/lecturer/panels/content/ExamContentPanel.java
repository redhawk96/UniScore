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
import com.panels.content.ErrorNotifier;
import com.panels.content.SuccessNotifier;
import com.utils.BarChartFrame;
import com.utils.ContentTable;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Activity;
import models.Exam;
import models.Module;
import models.Submission;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings("serial")
public class ExamContentPanel extends ContentPanel {

	private JPanel contentPanel = new JPanel();
	private JPanel examBodyPanel = new JPanel();
	private JPanel examInfoPanel = new JPanel();
	private ContentTable table = new ContentTable();
	private JScrollPane scrollPane = new JScrollPane();
	
	private Exam selectedExam;
	private Module selectedExamModule;
	
	public ExamContentPanel() {
		setContentPanel();	
	}
	
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addExamListTable();
	}
	
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		examBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		examBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(examBodyPanel);
		examBodyPanel.setLayout(null);
	}
	
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
		
		
		JPanel examStatPanel = new JPanel();
		examStatPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		examStatPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		examStatPanel.setBounds(1046, 0, 153, 68);
		examStatPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		examInfoPanel.add(examStatPanel);
		examStatPanel.setLayout(null);
		
		examStatPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					Submission tempSubmission = new Submission();
					tempSubmission.setExamId(selectedExam.getExamId());
					
					
					BarChartFrame examMarkStats = new BarChartFrame("Exam Statistics", selectedExam.getExamName() + " Exam Statistics", "Score Range", "No of Students", UniScoreClient.uniscoreInterface.getSubmissionDatasetByExam(tempSubmission));

					examMarkStats.setSize(950, 600);
					examMarkStats.setLocationRelativeTo(null);
					examMarkStats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					examMarkStats.setVisible(true);

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
		
		JPanel examSubmissionCountPanel = new JPanel();
		examSubmissionCountPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		examSubmissionCountPanel.setLayout(null);
		examSubmissionCountPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		examSubmissionCountPanel.setBounds(891, 0, 153, 138);
		examInfoPanel.add(examSubmissionCountPanel);
		
		Integer examSubmissionCount = -1; 
		
		try {
			
			Submission submission = new Submission();
			submission.setExamId(selectedExam.getExamId());;
			examSubmissionCount = UniScoreClient.uniscoreInterface.getExaminationSubmissionCount(submission);
			
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
		
		if(examSubmissionCount.toString().length() < 2) {
			examSubmissionCountStr = "0"+examSubmissionCount.toString();
		} else {
			examSubmissionCountStr = examSubmissionCount.toString();
		}
		
		JLabel examSubmissionCountPanelLabel = new JLabel("ES  "+examSubmissionCountStr);
		examSubmissionCountPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examSubmissionCountPanelLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		examSubmissionCountPanelLabel.setFont(UI.APPLICATION_THEME_FONT_18_PLAIN);
		examSubmissionCountPanelLabel.setBounds(0, 0, 153, 138);
		examSubmissionCountPanel.add(examSubmissionCountPanelLabel);
		
		

		JPanel printSubmissionReportPanel = new JPanel();
		printSubmissionReportPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		printSubmissionReportPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// report location by index, file name by index, SQL query by index and option parameters
				try {
					
					UniScoreClient.uniscoreInterface.printSubmissionReport(selectedExam.getExamId(), selectedExam.getExamName(), selectedExam.getModuleId());

					UniScoreClient.uniscoreInterface.addLogActivity(new Activity("New submissions report for exam "+selectedExam.getExamId()+" was printed from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
					
					SuccessNotifier sn = new SuccessNotifier("Report was successfully saved.", null, null);
					sn.setVisible(true);
					
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
		
		examInfoPanel.repaint();
	}
	
	private void addExamListTable() {
	
		try { 
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Module", "Exam Name", "Exam Status"}, 0);
		
			Module module = new Module();
			module.setTeacherId(UniScoreClient.authUser.getUserId());
			
			List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, 0, 0);
			int count = 0;
			
			for (Module mod : moduleList) {
				
				Exam tempExam = new Exam();
				tempExam.setModuleId(mod.getModuleId());
						
				List<Exam> examList = (List<Exam>) UniScoreClient.uniscoreInterface.getExamsByModule(tempExam);
				for(Exam e : examList) {
					// Adding a exam record to the table each time the loop executes
					model.addRow(new Object[] {e.getExamId(),   "     "+mod.getModuleName(),  "     "+e.getExamName(), "     "+e.getStatus()});
					
					if (count < 1) {
						selectedExam = e;
						selectedExamModule = mod;
						setExamInfoPanel();
					}
					count++;
				}		
			}
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(table.getSelectedRow() != -1) {
						 try {
							 
							 Exam selectedTempExam = new Exam();
							 selectedTempExam.setExamId(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
							 Exam sExam = (Exam) UniScoreClient.uniscoreInterface.getExam(selectedTempExam);
							 
							 Module selectedTempModule = new Module();
							 selectedTempModule.setModuleId(sExam.getModuleId());
							 Module selectedModule = (Module) UniScoreClient.uniscoreInterface.getModule(selectedTempModule);
	
							 selectedExam = sExam;
							 selectedExamModule = selectedModule;
							 
							 setExamInfoPanel();
							 
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
			
			table.setModel(model);
			
			//  To align text to center in a column 
	        DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
	        centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);
	        
	        // Setting width to colums in JTable
	        TableColumnModel columnModel = table.getColumnModel();
	        
	        columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
	        columnModel.getColumn(3).setCellRenderer(centerAlingedCell);
			
	        // Removing horizontal cell borders
	        table.setShowHorizontalLines(false);
	        
	        // Setting cursor type on table hover
			table.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
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
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}

