package admin.panels.content;

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

import connectivity.UniScoreServer;
import models.Exam;
import models.Module;

public class ExamsContentPanel extends ContentPanel {
	JPanel contentPanel = new JPanel();
	JPanel examContentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel examInfoPanel = new JPanel();


	public ExamsContentPanel() {
		contentPanel.setName("exams");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);
		
		try {
			
			displayNavigationIndicator();
			
			examContentPanel.setBackground(Color.WHITE);
			examContentPanel.setBounds(30, 66, 1096, 793);
			contentPanel.add(examContentPanel);
			examContentPanel.setLayout(null);
			
			setSelectedExam("", "", -1, -1, "", -1, "");
			
			
			DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Module", "Exam Name", "Exam Status"}, 0);

			Module module = new Module();
			module.setTeacherId("T001");
			//module.setTeacherId(UniScoreClient.authUser.getUserId());
			
			List<Module> moduleList = (List<Module>) UniScoreServer.uniscoreInterface.getModulesByRelevance(module, 0, 0);

			for (Module mod : moduleList) {
				
				Exam exam = new Exam();
				exam.setModuleId(mod.getModuleId());
						
				List<Exam> examList = (List<Exam>) UniScoreServer.uniscoreInterface.getExamsByModule(exam);
				for(Exam e : examList) {
					// Adding a exam record to the table each time the loop executes
					model.addRow(new Object[] {e.getExamId(),   "     "+mod.getModuleName(),  "     "+e.getExamName(), "     "+e.getStatus()});
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
							 Exam selectedExam = (Exam) UniScoreServer.uniscoreInterface.getExam(selectedTempExam);
							 
							 Module selectedTempModule = new Module();
							 selectedTempModule.setModuleId(selectedExam.getModuleId());
							 Module selectedModule = (Module) UniScoreServer.uniscoreInterface.getModule(selectedTempModule);
							 
							 setSelectedExam(selectedModule.getModuleId(), selectedModule.getModuleName(), selectedModule.getYear(), selectedModule.getSemester(), selectedExam.getExamName(), selectedExam.getDuration(), selectedExam.getStatus());
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
			scrollPane.setBounds(0, 171, 1086, 70);
			examContentPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	/*
	 * returns the JPanel inside ContentPanel
	 * @param {}
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
	
	public void displayNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.NAVIGATION_INDICATOR_PANEL_BACKGRIOUND_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1083, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Admin /");
		navigationIndicatorMainLabel.setBounds(932, 8, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Exams");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	
	public void setSelectedExam(String moduleCode, String moduleName, int moduleYear, int moduleSemester, String examName, int examDuration, String examStatus) {
		examInfoPanel.removeAll();
		examInfoPanel = new JPanel();
		examInfoPanel.setLayout(null);
		examInfoPanel.setBackground(Color.DARK_GRAY);
		examInfoPanel.setBounds(0, 0, 1086, 138);
		examContentPanel.add(examInfoPanel);
		
		JLabel moduleInfoLabel = new JLabel("Module Information");
		moduleInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moduleInfoLabel.setForeground(Color.WHITE);
		moduleInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		moduleInfoLabel.setBounds(31, 11, 518, 14);
		examInfoPanel.add(moduleInfoLabel);
		
		JLabel examInfoLabel = new JLabel("Exam Information");
		examInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examInfoLabel.setForeground(Color.WHITE);
		examInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examInfoLabel.setBounds(580, 11, 545, 14);
		examInfoPanel.add(examInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setBounds(559, 11, 11, 116);
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
		selectedModuleCodeLabel.setBounds(158, 48, 391, 14);
		examInfoPanel.add(selectedModuleCodeLabel);
		
		JLabel selectedModuleNameLabel = new JLabel(":  "+moduleName);
		selectedModuleNameLabel.setForeground(Color.WHITE);
		selectedModuleNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedModuleNameLabel.setBounds(158, 76, 391, 14);
		examInfoPanel.add(selectedModuleNameLabel);
		
		if(moduleYear == -1) {
			JLabel selectedModuleAllocationLabel = new JLabel(":  ");
			selectedModuleAllocationLabel.setForeground(Color.WHITE);
			selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
			examInfoPanel.add(selectedModuleAllocationLabel);
		}else {
			JLabel selectedModuleAllocationLabel = new JLabel(":  "+moduleYear+"Y  - "+moduleSemester+"S");
			selectedModuleAllocationLabel.setForeground(Color.WHITE);
			selectedModuleAllocationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedModuleAllocationLabel.setBounds(158, 105, 391, 14);
			examInfoPanel.add(selectedModuleAllocationLabel);
		}
		
		JLabel examNameLabel = new JLabel("Name");
		examNameLabel.setForeground(Color.WHITE);
		examNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examNameLabel.setBounds(644, 48, 109, 14);
		examInfoPanel.add(examNameLabel);
		
		JLabel examDurationLabel = new JLabel("Duration");
		examDurationLabel.setForeground(Color.WHITE);
		examDurationLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examDurationLabel.setBounds(644, 76, 109, 14);
		examInfoPanel.add(examDurationLabel);
		
		JLabel examStatusLabel = new JLabel("Status");
		examStatusLabel.setForeground(Color.WHITE);
		examStatusLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		examStatusLabel.setBounds(644, 104, 109, 14);
		examInfoPanel.add(examStatusLabel);
		
		JLabel selectedExamDuration = new JLabel(":  "+examName);
		selectedExamDuration.setForeground(Color.WHITE);
		selectedExamDuration.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamDuration.setBounds(756, 76, 362, 14);
		examInfoPanel.add(selectedExamDuration);
		
		if(examDuration == -1) {
			JLabel selectedExamStatus = new JLabel(":  ");
			selectedExamStatus.setForeground(Color.WHITE);
			selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedExamStatus.setBounds(756, 104, 362, 17);
			examInfoPanel.add(selectedExamStatus);
		}else {
			JLabel selectedExamStatus = new JLabel(":  "+examDuration);
			selectedExamStatus.setForeground(Color.WHITE);
			selectedExamStatus.setFont(new Font("Roboto", Font.PLAIN, 14));
			selectedExamStatus.setBounds(756, 104, 362, 17);
			examInfoPanel.add(selectedExamStatus);
		}
		
		JLabel selectedExamName = new JLabel(":  "+examStatus);
		selectedExamName.setForeground(Color.WHITE);
		selectedExamName.setFont(new Font("Roboto", Font.PLAIN, 14));
		selectedExamName.setBounds(756, 49, 362, 17);
		examInfoPanel.add(selectedExamName);
	
		examInfoPanel.repaint();
	}
}
