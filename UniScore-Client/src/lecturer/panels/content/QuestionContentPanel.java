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
import models.Exam;
import models.Module;

@SuppressWarnings("serial")
public class QuestionContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel examBodyPanel = new JPanel();
	JPanel examInfoPanel = new JPanel();
	
	public QuestionContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when selected
		 */
		contentPanel.setName("question");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setLayout(null);
		
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
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Questions");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setQuestionBody() {
		
		setNavigationIndicator();
		
		examBodyPanel.setBackground(Color.WHITE);
		examBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(examBodyPanel);
		examBodyPanel.setLayout(null);
		
		setSelectedExam("", "", -1, -1, "", -1, "", true);
		
		setExamListTable();
	}
	
	
	public void setSelectedExam(String moduleCode, String moduleName, int moduleYear, int moduleSemester, String examName, int examDuration, String examStatus, boolean isButtonDisabled) {
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
		
		JLabel selectedModuleCodeLabel = new JLabel(":  "+moduleCode);
		selectedModuleCodeLabel.setForeground(Color.WHITE);
		selectedModuleCodeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleCodeLabel.setBounds(158, 48, 279, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+moduleName);
		selectedModuleNameLabel.setForeground(Color.WHITE);
		selectedModuleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleNameLabel.setBounds(158, 76, 279, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		if(moduleYear == -1) {
			JLabel selectedModuleAllocationLabel = new JLabel(":  ");
			selectedModuleAllocationLabel.setForeground(Color.WHITE);
			selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
			examInfoPanel.add(selectedModuleAllocationLabel);
		}else {
			JLabel selectedModuleAllocationLabel = new JLabel(":  Y"+moduleYear+"  - S"+moduleSemester);
			selectedModuleAllocationLabel.setForeground(Color.WHITE);
			selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
			examInfoPanel.add(selectedModuleAllocationLabel);
		}
		
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
		
		JLabel selectedExamName = new JLabel(":  "+examName);
		selectedExamName.setForeground(Color.WHITE);
		selectedExamName.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamName.setBounds(674, 49, 362, 17);
		examInfoPanel.add(selectedExamName);
		
		if(examDuration == -1) {
			JLabel selectedExamDuration = new JLabel(":  ");
			selectedExamDuration.setForeground(Color.WHITE);
			selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedExamDuration.setBounds(674, 76, 362, 14);
			examInfoPanel.add(selectedExamDuration);
		}else {
			JLabel selectedExamDuration = new JLabel(":  "+examDuration);
			selectedExamDuration.setForeground(Color.WHITE);
			selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedExamDuration.setBounds(674, 76, 362, 14);
			examInfoPanel.add(selectedExamDuration);
		}
		
		JLabel selectedExamStatus = new JLabel(":  "+examStatus.toUpperCase());
		selectedExamStatus.setForeground(Color.WHITE);
		selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamStatus.setBounds(674, 104, 362, 17);
		examInfoPanel.add(selectedExamStatus);
		
		if(!isButtonDisabled) {
			JPanel showQuestionButtonPanel = new JPanel();
			showQuestionButtonPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					showQuestionButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.NAVIGATION_PANEL_BUTTON_CURSOR));
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
//					System.out.println(moduleCode);
				}
			});
			showQuestionButtonPanel.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			showQuestionButtonPanel.setBounds(1046, 0, 153, 138);
			examInfoPanel.add(showQuestionButtonPanel);
			showQuestionButtonPanel.setLayout(null);
			
			JLabel showQuestionButtonLabel = new JLabel("SHOW");
			showQuestionButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
			showQuestionButtonLabel.setBounds(0, 0, 153, 138);
			showQuestionButtonLabel.setForeground(Color.DARK_GRAY);
			showQuestionButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
			showQuestionButtonPanel.add(showQuestionButtonLabel);
		}else {
			JPanel showQuestionButtonPanel = new JPanel();
			showQuestionButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			showQuestionButtonPanel.setBounds(1046, 0, 153, 138);
			examInfoPanel.add(showQuestionButtonPanel);
			showQuestionButtonPanel.setLayout(null);
		}
		
		examInfoPanel.repaint();
	}
	
	
	public void setExamListTable() {
		
		try {
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Allocation", "Module", "Exam Name"}, 0);

			Module module = new Module();
			module.setTeacherId(UniScoreClient.authUser.getUserId());
			
			List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, 0, 0);

			for (Module mod : moduleList) {
				
				Exam exam = new Exam();
				exam.setModuleId(mod.getModuleId());
						
				List<Exam> examList = (List<Exam>) UniScoreClient.uniscoreInterface.getExamsByModule(exam);
				for(Exam e : examList) {
					// Adding a exam record to the table each time the loop executes
					if(e.getStatus().equalsIgnoreCase("disabled")) {
						model.addRow(new Object[] {e.getExamId(),  "     Y"+mod.getYear()+" - S"+mod.getSemester(), "     "+mod.getModuleName(),  "     "+e.getExamName()});
					}
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
							 Exam selectedExam = (Exam) UniScoreClient.uniscoreInterface.getExam(selectedTempExam);
							 
							 Module selectedTempModule = new Module();
							 selectedTempModule.setModuleId(selectedExam.getModuleId());
							 Module selectedModule = (Module) UniScoreClient.uniscoreInterface.getModule(selectedTempModule);
							 
							 setSelectedExam(selectedModule.getModuleId(), selectedModule.getModuleName(), selectedModule.getYear(), selectedModule.getSemester(), selectedExam.getExamName(), selectedExam.getDuration(), selectedExam.getStatus(), false);
						 }catch(Exception e) {
							 System.out.println(e);
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
            columnModel.getColumn(1).setCellRenderer(centerAlingedCell);
			
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
			examBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
