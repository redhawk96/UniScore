package lecturer.panels.content;

import java.awt.Color;
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
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.panels.ContentPanel;
import com.panels.content.ErrorNotifier;
import com.panels.content.SuccessNotifier;
import com.utils.BarChart;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Activity;
import models.Exam;
import models.Module;
import models.Submission;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings("serial")
public class ExamContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	JPanel examBodyPanel = new JPanel();
	JPanel examInfoPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	Exam selectedExam;
	Module selectedExamModule;
	
	public ExamContentPanel() {
		
		try {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("exam");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);

		setExamsBody();		
		
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 500");
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
	
	
	
	
	public void setNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.NAVIGATION_INDICATOR_PANEL_BACKGRIOUND_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Exams");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setExamsBody() throws RemoteException, ClassNotFoundException, SQLException {
		
		setNavigationIndicator();
		
		examBodyPanel.setBackground(Color.WHITE);
		examBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(examBodyPanel);
		examBodyPanel.setLayout(null);
		
		setExamListTable();
	}
	
	
	public void setSelectedExam() {
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(Color.DARK_GRAY);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		examBodyPanel.add(examInfoPanel);
		
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
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+selectedExamModule.getModuleId());
		selectedModuleCodeLabel.setForeground(Color.WHITE);
		selectedModuleCodeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleCodeLabel.setBounds(158, 48, 269, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+selectedExamModule.getModuleName());
		selectedModuleNameLabel.setForeground(Color.WHITE);
		selectedModuleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleNameLabel.setBounds(158, 76, 269, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+selectedExamModule.getYear()+"  - S"+selectedExamModule.getSemester());
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
		
		JLabel selectedExamName = new JLabel(":  "+ selectedExam.getExamName());
		selectedExamName.setForeground(Color.WHITE);
		selectedExamName.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamName.setBounds(601, 49, 237, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+selectedExam.getDuration());
		selectedExamDuration.setForeground(Color.WHITE);
		selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamDuration.setBounds(601, 76, 237, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+selectedExam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(Color.WHITE);
		selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamStatus.setBounds(601, 104, 237, 17);
		examInfoPanel.add(selectedExamStatus);
		
		
		JPanel questionCountPanel = new JPanel();
		questionCountPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		questionCountPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		questionCountPanel.setBounds(1046, 0, 153, 68);
		questionCountPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		examInfoPanel.add(questionCountPanel);
		questionCountPanel.setLayout(null);
		
		questionCountPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					BarChart examMarkStats = new BarChart("Exam Statistics", selectedExam.getExamName() + " Exam Statistics", "Score Range", "No of Students", getNewDataset());

					examMarkStats.setSize(950, 600);
					examMarkStats.setLocationRelativeTo(null);
					examMarkStats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					examMarkStats.setVisible(true);

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JLabel remaningQuestionCountLabel = new JLabel("STATS");
		remaningQuestionCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		remaningQuestionCountLabel.setBounds(0, 0, 153, 68);
		remaningQuestionCountLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		remaningQuestionCountLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		questionCountPanel.add(remaningQuestionCountLabel);
		
		JPanel goBackButtonPanel = new JPanel();
		goBackButtonPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		goBackButtonPanel.setLayout(null);
		goBackButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		goBackButtonPanel.setBounds(891, 0, 153, 138);
		examInfoPanel.add(goBackButtonPanel);
		
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
		
		JLabel goBackButtonLabel = new JLabel("ES  "+examSubmissionCountStr);
		goBackButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goBackButtonLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		goBackButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		goBackButtonLabel.setBounds(0, 0, 153, 138);
		goBackButtonPanel.add(goBackButtonLabel);
		
		

		JPanel createQuestionPanel = new JPanel();
		createQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
		createQuestionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// report location by index, file name by index, SQL query by index and option parameters
				try {
					
					UniScoreClient.uniscoreInterface.printReport(1, 1, 1, ""+selectedExam.getExamId());
										
					UniScoreClient.uniscoreInterface.addLogActivity(new Activity("New submissions report for exam "+selectedExam.getExamId()+" was printed from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
					
					SuccessNotifier sn = new SuccessNotifier("Report was successfully saved.", null, null);
					sn.setVisible(true);
					
				} catch (RemoteException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : 400");
					en.setVisible(true);
					System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
				} catch (ClassNotFoundException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : 600");
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
				} catch (SQLException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : 500");
					en.setVisible(true);
					System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
				} catch (JRException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to save exam submissions report.\nError refferance : 700");
					en.setVisible(true);
					System.out.println("JRException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
				}				
			}
		});
		createQuestionPanel.setLayout(null);
		createQuestionPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		createQuestionPanel.setBounds(1046, 70, 153, 68);
		examInfoPanel.add(createQuestionPanel);
		
		JLabel createQuestionLabel = new JLabel("REPORT");
		createQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createQuestionLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		createQuestionLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		createQuestionLabel.setBounds(0, 0, 153, 68);
		createQuestionPanel.add(createQuestionLabel);	
		
		examInfoPanel.repaint();
	}
	
	
	public void setExamListTable() throws RemoteException, ClassNotFoundException, SQLException {
			
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
						setSelectedExam();
					}
					count++;
				}		
			}
			
			table.setForeground(Color.DARK_GRAY);

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
							 
							 setSelectedExam();
							 
						 	} catch (RemoteException e) {
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 400");
								en.setVisible(true);
								System.out.println("RemoteException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
							} catch (ClassNotFoundException e) {
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 600");
								en.setVisible(true);
								System.out.println("ClassNotFoundException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
							} catch (SQLException e) {
								ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated exams.\nError refferance : 500");
								en.setVisible(true);
								System.out.println("SQLException execution thrown on ExamContentPanel.java file. Error : "+e.getCause());
							}
					 }
				}
			});
			
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
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.getTableHeader().setOpaque(false);
			table.getTableHeader().setBackground(Color.WHITE);
			table.getTableHeader().setForeground(Color.BLACK);
			table.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 14));
			table.setSelectionBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(32);
			table.setFont(new Font("Roboto", Font.PLAIN, 14));
			table.isCellEditable(1, 1);
			scrollPane.setBounds(0, 171, 1199, 593);
			examBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
	}
	
	private CategoryDataset getNewDataset() throws RemoteException, ClassNotFoundException, SQLException {
		
		Submission eSubmission = new Submission();
		eSubmission.setExamId(selectedExam.getExamId());
		
		List<Submission> examSubmissionList = (List<Submission>)UniScoreClient.uniscoreInterface.getSubmissionsByRelevance(eSubmission);
		
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		
		for (Submission sub : examSubmissionList) {
			switch (sub.getGrade()) {
			case "A": a = a + 1; break;
			case "B": b = b + 1; break;
			case "C": c = c + 1; break;
			case "D": d = d + 1; break;
			case "E": e = e + 1; break;
			}
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(a, "MARKS", "75-100");
		dataset.addValue(b, "MARKS", "65-74");
		dataset.addValue(c, "MARKS", "55-64");
		dataset.addValue(d, "MARKS", "35-54");
		dataset.addValue(e, "MARKS", "0-34");

		return dataset;
	}
}

