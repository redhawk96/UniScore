package admin.panels.content;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreServer;
import models.Module;

public class ModulesContentPanel extends ContentPanel {
	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	
	public ModulesContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when
		 * selected
		 */
		contentPanel.setName("module");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);

		displayNavigationIndicator();
		
		displayModuleTrees();
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
		navigationIndicatorPanel.setBounds(30, 11, 1085, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Admin /");
		navigationIndicatorMainLabel.setBounds(932, 8, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Modules");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
		
	}
	
	public void displayModuleTrees() {
		JPanel modulesPanel = new JPanel();
		modulesPanel.setBackground(Color.WHITE);
		modulesPanel.setBounds(30, 66, 1096, 761);
		contentPanel.add(modulesPanel);
		modulesPanel.setLayout(null);
		
		JPanel yearOnePanel = new JPanel();
		yearOnePanel.setBackground(Color.DARK_GRAY);
		yearOnePanel.setBounds(0, 205, 249, 76);
		modulesPanel.add(yearOnePanel);
		yearOnePanel.setLayout(null);
		
		JLabel yearOnePanelLabel = new JLabel("YEAR  1");
		yearOnePanelLabel.setForeground(Color.WHITE);
		yearOnePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearOnePanelLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		yearOnePanelLabel.setBounds(0, 0, 249, 76);
		yearOnePanel.add(yearOnePanelLabel);
		
		JPanel yearTwoPanel = new JPanel();
		yearTwoPanel.setLayout(null);
		yearTwoPanel.setBackground(Color.DARK_GRAY);
		yearTwoPanel.setBounds(269, 205, 249, 76);
		modulesPanel.add(yearTwoPanel);
		
		JLabel yearTwoPanelLabel = new JLabel("YEAR  2");
		yearTwoPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearTwoPanelLabel.setForeground(Color.WHITE);
		yearTwoPanelLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		yearTwoPanelLabel.setBounds(0, 0, 249, 76);
		yearTwoPanel.add(yearTwoPanelLabel);
		
		JPanel yearThreePanel = new JPanel();
		yearThreePanel.setLayout(null);
		yearThreePanel.setBackground(Color.DARK_GRAY);
		yearThreePanel.setBounds(541, 205, 249, 76);
		modulesPanel.add(yearThreePanel);
		
		JLabel yearThreePanelLabel = new JLabel("YEAR  3");
		yearThreePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearThreePanelLabel.setForeground(Color.WHITE);
		yearThreePanelLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		yearThreePanelLabel.setBounds(0, 0, 249, 76);
		yearThreePanel.add(yearThreePanelLabel);
		
		JPanel yearFourPanel = new JPanel();
		yearFourPanel.setLayout(null);
		yearFourPanel.setBackground(Color.DARK_GRAY);
		yearFourPanel.setBounds(811, 205, 249, 76);
		modulesPanel.add(yearFourPanel);
		
		JLabel yearFourPanelLabel = new JLabel("YEAR  4");
		yearFourPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearFourPanelLabel.setForeground(Color.WHITE);
		yearFourPanelLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		yearFourPanelLabel.setBounds(0, 0, 249, 76);
		yearFourPanel.add(yearFourPanelLabel);
		
		JScrollPane yearOneScrollPane = new JScrollPane();
		yearOneScrollPane.setBorder(null);
		yearOneScrollPane.setBounds(0, 284, 249, 477);
		modulesPanel.add(yearOneScrollPane);
		
		JTree yearOneTree = new JTree();
		yearOneTree.setModel(setTreeValues(1)); // Setting values to tree
		yearOneScrollPane.setViewportView(yearOneTree);
		yearOneTree.setFont(new Font("Roboto", Font.PLAIN, 14));
		yearOneTree.setRowHeight(30);
		yearOneTree.setBorder(null);
		yearOneTree.setVisibleRowCount(30);
		
		JScrollPane yearTwoScrollPane = new JScrollPane();
		yearTwoScrollPane.setBorder(null);
		yearTwoScrollPane.setBounds(269, 284, 249, 477);
		modulesPanel.add(yearTwoScrollPane);
		
		JTree yearTwoTree = new JTree();
		yearTwoTree.setModel(setTreeValues(2)); // Setting values to tree
		yearTwoScrollPane.setViewportView(yearTwoTree);
		yearTwoTree.setVisibleRowCount(30);
		yearTwoTree.setRowHeight(30);
		yearTwoTree.setFont(new Font("Roboto", Font.PLAIN, 14));
		yearTwoTree.setBorder(null);
		
		JScrollPane yearThreeScrollPane = new JScrollPane();
		yearThreeScrollPane.setBorder(null);
		yearThreeScrollPane.setBounds(541, 284, 249, 477);
		modulesPanel.add(yearThreeScrollPane);
		
		JTree yearThreeTree = new JTree();
		yearThreeTree.setModel(setTreeValues(3)); // Setting values to tree
		yearThreeScrollPane.setViewportView(yearThreeTree);
		yearThreeTree.setVisibleRowCount(30);
		yearThreeTree.setRowHeight(30);
		yearThreeTree.setFont(new Font("Roboto", Font.PLAIN, 14));
		yearThreeTree.setBorder(null);
		
		JScrollPane yearFourScrollPane = new JScrollPane();
		yearFourScrollPane.setBorder(null);
		yearFourScrollPane.setBounds(811, 284, 249, 477);
		modulesPanel.add(yearFourScrollPane);
		
		JTree yearFourTree = new JTree();
		yearFourTree.setModel(setTreeValues(4)); // Setting values to tree
		yearFourScrollPane.setViewportView(yearFourTree);
		yearFourTree.setVisibleRowCount(30);
		yearFourTree.setRowHeight(30);
		yearFourTree.setFont(new Font("Roboto", Font.PLAIN, 14));
		yearFourTree.setBorder(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1086, 90);
		modulesPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblModules = new JLabel("MODULES");
		lblModules.setHorizontalAlignment(SwingConstants.CENTER);
		lblModules.setForeground(Color.WHITE);
		lblModules.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblModules.setBounds(0, 0, 1094, 93);
		panel.add(lblModules);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(121, 153, 818, 4);
		modulesPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(121, 153, 4, 41);
		modulesPanel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(Color.DARK_GRAY);
		separator_1_1.setBackground(Color.DARK_GRAY);
		separator_1_1.setBounds(387, 153, 4, 41);
		modulesPanel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setOrientation(SwingConstants.VERTICAL);
		separator_1_2.setForeground(Color.DARK_GRAY);
		separator_1_2.setBackground(Color.DARK_GRAY);
		separator_1_2.setBounds(655, 153, 4, 41);
		modulesPanel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setOrientation(SwingConstants.VERTICAL);
		separator_1_3.setForeground(Color.DARK_GRAY);
		separator_1_3.setBackground(Color.DARK_GRAY);
		separator_1_3.setBounds(935, 153, 4, 41);
		modulesPanel.add(separator_1_3);
		
		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setOrientation(SwingConstants.VERTICAL);
		separator_1_4.setForeground(Color.DARK_GRAY);
		separator_1_4.setBackground(Color.DARK_GRAY);
		separator_1_4.setBounds(541, 101, 4, 47);
		modulesPanel.add(separator_1_4);
		
	}
	
	public DefaultTreeModel setTreeValues(int year) {
		return new DefaultTreeModel(new DefaultMutableTreeNode("Year 0" + year) {
			{
				Module module = new Module();
				module.setTeacherId("T001");
//				module.setTeacherId(UniScoreClient.authUser.getUserId());
				
				DefaultMutableTreeNode node_1;
				DefaultMutableTreeNode node_2;

				try {
					for (int i = 1; i <= 2; i++) {
						node_1 = new DefaultMutableTreeNode("Semester 0"+i);
						node_2 = new DefaultMutableTreeNode("Modules");

						List<Module> moduleList = (List<Module>) UniScoreServer.uniscoreInterface.getModulesByRelevance(module, year, i);
						if (moduleList.isEmpty()) {
							node_2.add(new DefaultMutableTreeNode("No modules"));
						} else {
							for (Module mod : moduleList) {
								node_2.add(new DefaultMutableTreeNode(mod.getModuleId() + " - " + mod.getModuleName()));
							}
						}
						node_1.add(node_2);
						add(node_1);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}
}
