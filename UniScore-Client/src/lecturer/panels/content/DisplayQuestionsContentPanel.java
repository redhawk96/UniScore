package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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
	Integer questionCount = -1;
	private JTextField searchText;
	
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
		
		JLabel navigationIndicatorMainLabel = new JLabel("Exams  /");
		navigationIndicatorMainLabel.setBounds(947, 8, 59, 17);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorMainLabel1 = new JLabel("Questionnaire  /");
		navigationIndicatorMainLabel1.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel1.setBounds(1009, 8, 105, 17);
		navigationIndicatorMainLabel1.setForeground(Color.DARK_GRAY);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel1);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Questions");
		navigationIndicatorActiveLabel.setForeground(new Color(249, 168, 37));
		navigationIndicatorActiveLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		navigationIndicatorActiveLabel.setBounds(1113, 8, 76, 17);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setQuestionBody() {
		
		setNavigationIndicator();
		
		questionBodyPanel.setBackground(Color.WHITE);
		questionBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(questionBodyPanel);
		questionBodyPanel.setLayout(null);
		
		setSelectedExam();
		
		setSearchField();
		
		setQuestionListTable("");
	}
	
	public void setRemaningQuestionCount() {
		
		try {
			Question q = new Question();
			q.setExamId(exam.getExamId());
			questionCount = (int)UniScoreClient.uniscoreInterface.getExaminationQuestionCount(q);
			
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve remaning question count.\nError refferance : 500");
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		}
	}
	
	public void setSelectedExam() {
		
		setRemaningQuestionCount();
		
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
		remaningQuestionCountLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		questionCountPanel.add(remaningQuestionCountLabel);
		
		JPanel goBackButtonPanel = new JPanel();
		goBackButtonPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) UI.APPLICATION_THEME_PRIMARY_COLOR));
		goBackButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
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
		goBackButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		goBackButtonLabel.setBounds(0, 0, 153, 138);
		goBackButtonPanel.add(goBackButtonLabel);
		
		
		if(remaningQuestionCount != 0) {
			
			JPanel createQuestionPanel = new JPanel();
			createQuestionPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
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
			createQuestionLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
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
			createQuestionLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
			createQuestionLabel.setBounds(0, 0, 153, 68);
			createQuestionPanel.add(createQuestionLabel);
		}
		
		examInfoPanel.repaint();
	}
	
	
	public void setQuestionListTable(String searchText) {
			
		try {
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"}, 0);
				
			List<Question> questionList  = (List<Question>) UniScoreClient.uniscoreInterface.getExamQuestionsBySearch(searchText);
			
			for (Question qes : questionList) {
				if(qes.getExamId() == exam.getExamId()) {
					// Adding a exam record to the table each time the loop executes
					model.addRow(new Object[] {qes.getQuestionId(), "     "+qes.getQuestion(),  "     "+qes.getOption1(), "     "+qes.getOption2(),  "     "+qes.getOption3(),   "     "+qes.getOption4(), qes.getAnswer()});
				}
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
			scrollPane.setBounds(0, 220, 1199, 593);
			questionBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
		
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve exam questions.\nError refferance : 500");
			en.setVisible(true);
			System.out.println("SQLException execution thrown on DisplayQuestionsContentPanel.java file. Error : "+e.getCause());
		} 
	}

	public void setSearchField() {
		searchText = new JTextField();
		searchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				setQuestionListTable(searchText.getText().trim());
			}
		});
		searchText.setForeground(Color.GRAY);
		searchText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		searchText.setFont(new Font("Roboto", Font.PLAIN, 14));
		searchText.setBounds(978, 172, 219, 31);
		questionBodyPanel.add(searchText);
		searchText.setColumns(10);

		JLabel searchLabel = new JLabel("Search    :");
		searchLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		searchLabel.setBounds(908, 172, 60, 31);
		questionBodyPanel.add(searchLabel);
	}
}
