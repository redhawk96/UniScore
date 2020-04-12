package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import main.panels.LecturerPanel;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class DisplayQuestionsContentPanel extends ContentPanel{
	
	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel questionBodyPanel = new JPanel();
	JPanel examInfoPanel = new JPanel();
	Module module;
	Exam exam;
	
	public DisplayQuestionsContentPanel(Module module, Exam exam) {
		this.module = module;
		this.exam = exam;
		/*
		 * Adding contentPanel
		 * JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("displayQuestions");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		
		setQuestionBody();
		
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
		
		JLabel navigationIndicatorMainLabel = new JLabel("Questions /");
		navigationIndicatorMainLabel.setBounds(UI.NAVIGATION_INDICATOR_SUB_PANEL_MAIN_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_SUB_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Exam Questions");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_SUB_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_SUB_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setQuestionBody() {
		
		setNavigationIndicator();
		
		questionBodyPanel.setBackground(Color.WHITE);
		questionBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionBodyPanel);
		questionBodyPanel.setLayout(null);
		
		setSelectedExam();
		
		setExamListTable();
	}
	
	
	public void setSelectedExam() {
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(Color.DARK_GRAY);
		examInfoPanel.setBounds(0, 0, 1199, 138);
		questionBodyPanel.add(examInfoPanel);
		
		JLabel moduleInfoLabel = new JLabel("Module Information");
		moduleInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moduleInfoLabel.setForeground(Color.WHITE);
		moduleInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleInfoLabel.setBounds(31, 11, 406, 14);
		examInfoPanel.add(moduleInfoLabel);
		
		JLabel examInfoLabel = new JLabel("Exam Information");
		examInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examInfoLabel.setForeground(Color.WHITE);
		examInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examInfoLabel.setBounds(562, 11, 474, 14);
		examInfoPanel.add(examInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setBounds(510, 11, 11, 116);
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
		selectedModuleCodeLabel.setBounds(158, 48, 279, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+module.getModuleName());
		selectedModuleNameLabel.setForeground(Color.WHITE);
		selectedModuleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleNameLabel.setBounds(158, 76, 279, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+module.getYear()+"  - S"+module.getSemester());
		selectedModuleAllocationLabel.setForeground(Color.WHITE);
		selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
		examInfoPanel.add(selectedModuleAllocationLabel);
		
		JLabel examNameLabel = new JLabel("Name");
		examNameLabel.setForeground(Color.WHITE);
		examNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examNameLabel.setBounds(562, 48, 109, 14);
		examInfoPanel.add(examNameLabel);
		
		JLabel examDurationLabel = new JLabel("Duration");
		examDurationLabel.setForeground(Color.WHITE);
		examDurationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examDurationLabel.setBounds(562, 76, 109, 14);
		examInfoPanel.add(examDurationLabel);
		
		JLabel examStatusLabel = new JLabel("Status");
		examStatusLabel.setForeground(Color.WHITE);
		examStatusLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examStatusLabel.setBounds(562, 104, 109, 14);
		examInfoPanel.add(examStatusLabel);
		
		JLabel selectedExamName = new JLabel(":  "+ exam.getExamName());
		selectedExamName.setForeground(Color.WHITE);
		selectedExamName.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamName.setBounds(674, 49, 362, 17);
		examInfoPanel.add(selectedExamName);
		
		JLabel selectedExamDuration = new JLabel(":  "+exam.getDuration());
		selectedExamDuration.setForeground(Color.WHITE);
		selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamDuration.setBounds(674, 76, 362, 14);
		examInfoPanel.add(selectedExamDuration);
		
		JLabel selectedExamStatus = new JLabel(":  "+exam.getStatus().toUpperCase());
		selectedExamStatus.setForeground(Color.WHITE);
		selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamStatus.setBounds(674, 104, 362, 17);
		examInfoPanel.add(selectedExamStatus);
		
		JPanel showQuestionButtonPanel = new JPanel();
		showQuestionButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				showQuestionButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LecturerPanel.selectedNavigation = new QuestionNavigationPanel();
				LecturerPanel.selectedContent = new QuestionnaireContentPanel();
				LecturerPanel.setSelectedPanel();
			}
		});
		showQuestionButtonPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		showQuestionButtonPanel.setBounds(1046, 0, 153, 138);
		examInfoPanel.add(showQuestionButtonPanel);
		showQuestionButtonPanel.setLayout(null);
		
		JLabel showQuestionButtonLabel = new JLabel("BACK");
		showQuestionButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showQuestionButtonLabel.setBounds(0, 0, 153, 138);
		showQuestionButtonLabel.setForeground(Color.DARK_GRAY);
		showQuestionButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		showQuestionButtonPanel.add(showQuestionButtonLabel);
		
		
		examInfoPanel.repaint();
	}
	
	
	public void setExamListTable() {
		
		try {
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"}, 0);

			Question question = new Question();
			question.setExamId(1);
			
			List<Question> questionList = (List<Question>) UniScoreClient.uniscoreInterface.getExamQuestions(question);

			for (Question qes : questionList) {
				// Adding a exam record to the table each time the loop executes
				model.addRow(new Object[] {qes.getQuestionId(), "     "+qes.getQuestion(),  "     "+qes.getOption1(), "     "+qes.getOption2(),  "     "+qes.getOption3(),   "     "+qes.getOption4(), qes.getAnswer()});
			}
			
			table.setForeground(Color.DARK_GRAY);

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
				
//				@Override
//				public void mouseClicked(MouseEvent arg0) {
//					if(table.getSelectedRow() != -1) {
//						 try {
//							 Exam selectedTempExam = new Exam();
//							 selectedTempExam.setExamId(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
//							 Exam selectedExam = (Exam) UniScoreClient.uniscoreInterface.getExam(selectedTempExam);
//							 
//							 Module selectedTempModule = new Module();
//							 selectedTempModule.setModuleId(selectedExam.getModuleId());
//							 Module selectedModule = (Module) UniScoreClient.uniscoreInterface.getModule(selectedTempModule);
//							 
////							 setSelectedExam(selectedModule.getModuleId(), selectedModule.getModuleName(), selectedModule.getYear(), selectedModule.getSemester(), selectedExam.getExamId(), selectedExam.getExamName(), selectedExam.getDuration(), selectedExam.getStatus());
//						 }catch(Exception e) {
//							 System.out.println(e);
//						 }
//					 }
//				}
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
            columnModel.getColumn(6).setCellRenderer(centerAlingedCell);
            
            // Removing question id column, but will still be able to access by column index
            columnModel.removeColumn(columnModel.getColumn(0));
			
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
			scrollPane.setBounds(0, 172, 1199, 641);
			questionBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
