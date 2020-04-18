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
import com.panels.content.ErrorNotifier;
import com.utils.ContentTable;
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
	
	private JPanel contentPanel = new JPanel();
	private ContentTable table = new ContentTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel questionBodyPanel = new JPanel();
	private JPanel examInfoPanel = new JPanel();
	
	private JTextField searchText;
	
	private Module module;
	private Exam exam;
	private Integer questionCount = -1;

	public DisplayQuestionsContentPanel(Module module, Exam exam) {
		this.module = module;
		this.exam = exam;
		
		setContentPanel();
	}

	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addExamInfoPanel();
		addSearchField();
		addQuestionListTable("");
	}
	
	private void initializeContentPanel(){
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
	
	
	public void setRemaningQuestionCount() {
		
		try {
			Question q = new Question();
			q.setExamId(exam.getExamId());
			questionCount = (int)UniScoreClient.uniscoreInterface.getExaminationQuestionCount(q);
			
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
	
	private void addExamInfoPanel() {
		
		setRemaningQuestionCount();
		
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
		goBackButtonPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		goBackButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		goBackButtonPanel.addMouseListener(new MouseAdapter() {
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
		
		
		if(remaningQuestionCount != 0) {
			
			JPanel createQuestionPanel = new JPanel();
			createQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			createQuestionPanel.addMouseListener(new MouseAdapter() {
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
	
	
	private void addSearchField() {
		searchText = new JTextField();
		searchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				addQuestionListTable(searchText.getText().trim());
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
	
	private void addQuestionListTable(String searchText) {
			
		try {
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"}, 0);
				
			List<Question> questionList  = (List<Question>) UniScoreClient.uniscoreInterface.getExamQuestionsBySearch(searchText);
			
			for (Question qes : questionList) {
				if(qes.getExamId() == exam.getExamId()) {
					// Adding a exam record to the table each time the loop executes
					model.addRow(new Object[] {qes.getQuestionId(), "     "+qes.getQuestion(),  "     "+qes.getOption1(), "     "+qes.getOption2(),  "     "+qes.getOption3(),   "     "+qes.getOption4(), qes.getAnswer()});
				}
			}
				

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent mouseEvent) {
			        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

			            Question selectedQuestion = new Question();
			            selectedQuestion.setQuestionId(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
			            selectedQuestion.setQuestion(table.getModel().getValueAt(table.getSelectedRow(), 1).toString().trim());
			            selectedQuestion.setOption1(table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim());
			            selectedQuestion.setOption2(table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim());
			            selectedQuestion.setOption3(table.getModel().getValueAt(table.getSelectedRow(), 4).toString().trim());
			            selectedQuestion.setOption4(table.getModel().getValueAt(table.getSelectedRow(), 5).toString().trim());
			            selectedQuestion.setAnswer(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()));
			            
			            LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
						LecturerPanel.selectedContent = new DisplayQuestionContentPanel(module, exam, selectedQuestion);
						LecturerPanel.setSelectedPanel();
			            
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
			
			// Setting column width
			table.getColumn("Answer").setMinWidth(0);
			table.getColumn("Answer").setMaxWidth(120);
			table.getColumn("Answer").setWidth(120);

			
			//  To align text to center in a column 
            DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
            centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);
            
            // Setting width to colums in JTable
            TableColumnModel columnModel = table.getColumnModel();
            
            columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
            columnModel.getColumn(6).setCellRenderer(centerAlingedCell);
            
            // Removing question id column, but will still be able to access by column index
            columnModel.removeColumn(columnModel.getColumn(0));
			
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
			scrollPane.setBounds(0, 220, 1199, 593);
			questionBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
		
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
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
