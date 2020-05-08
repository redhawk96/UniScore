/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.panels.ContentPanel;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Module;

@SuppressWarnings("serial")
public class ModuleContentPanel extends ContentPanel {

	private JPanel contentPanel = new JPanel();
	private JPanel modulesBodyPanel = new JPanel();

	public ModuleContentPanel() {
		setContentPanel();
	}

	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addModuleTrees();
	}
	
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		modulesBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		modulesBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(modulesBodyPanel);
		modulesBodyPanel.setLayout(null);
	}
	
	private void addNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(30, 11, 1199, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Lecturer /");
		navigationIndicatorMainLabel.setBounds(1065, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 71, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Modules");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1130, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 59, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
		
	}
	
	private void addModuleTrees() {
		JPanel yearOnePanel = new JPanel();
		yearOnePanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		yearOnePanel.setBounds(0, 205, 249, 76);
		modulesBodyPanel.add(yearOnePanel);
		yearOnePanel.setLayout(null);
		
		JLabel yearOnePanelLabel = new JLabel("YEAR  1");
		yearOnePanelLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		yearOnePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearOnePanelLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		yearOnePanelLabel.setBounds(0, 0, 249, 76);
		yearOnePanel.add(yearOnePanelLabel);
		
		JPanel yearTwoPanel = new JPanel();
		yearTwoPanel.setLayout(null);
		yearTwoPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		yearTwoPanel.setBounds(323, 205, 249, 76);
		modulesBodyPanel.add(yearTwoPanel);
		
		JLabel yearTwoPanelLabel = new JLabel("YEAR  2");
		yearTwoPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearTwoPanelLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		yearTwoPanelLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		yearTwoPanelLabel.setBounds(0, 0, 249, 76);
		yearTwoPanel.add(yearTwoPanelLabel);
		
		JPanel yearThreePanel = new JPanel();
		yearThreePanel.setLayout(null);
		yearThreePanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		yearThreePanel.setBounds(636, 205, 249, 76);
		modulesBodyPanel.add(yearThreePanel);
		
		JLabel yearThreePanelLabel = new JLabel("YEAR  3");
		yearThreePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearThreePanelLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		yearThreePanelLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		yearThreePanelLabel.setBounds(0, 0, 249, 76);
		yearThreePanel.add(yearThreePanelLabel);
		
		JPanel yearFourPanel = new JPanel();
		yearFourPanel.setLayout(null);
		yearFourPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		yearFourPanel.setBounds(950, 205, 249, 76);
		modulesBodyPanel.add(yearFourPanel);
		
		JLabel yearFourPanelLabel = new JLabel("YEAR  4");
		yearFourPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearFourPanelLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		yearFourPanelLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		yearFourPanelLabel.setBounds(0, 0, 249, 76);
		yearFourPanel.add(yearFourPanelLabel);
		
		JTree yearOneTree = new JTree();
		yearOneTree.setModel(setTreeValues(1)); // Setting values to tree
		yearOneTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearOneTree.setRowHeight(30);
		yearOneTree.setBorder(null);
		yearOneTree.setVisibleRowCount(30);
		yearOneTree.setBounds(0, 284, 249, 477);
		modulesBodyPanel.add(yearOneTree);
		
		
		JTree yearTwoTree = new JTree();
		yearTwoTree.setModel(setTreeValues(2)); // Setting values to tree
		yearTwoTree.setVisibleRowCount(30);
		yearTwoTree.setRowHeight(30);
		yearTwoTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearTwoTree.setBorder(null);
		yearTwoTree.setBounds(323, 284, 249, 477);
		modulesBodyPanel.add(yearTwoTree);

		
		JTree yearThreeTree = new JTree();
		yearThreeTree.setModel(setTreeValues(3)); // Setting values to tree
		yearThreeTree.setVisibleRowCount(30);
		yearThreeTree.setRowHeight(30);
		yearThreeTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearThreeTree.setBorder(null);
		yearThreeTree.setBounds(636, 284, 249, 477);
		modulesBodyPanel.add(yearThreeTree);
		
		JTree yearFourTree = new JTree();
		yearFourTree.setModel(setTreeValues(4)); // Setting values to tree
		yearFourTree.setVisibleRowCount(30);
		yearFourTree.setRowHeight(30);
		yearFourTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearFourTree.setBorder(null);
		yearFourTree.setBounds(950, 284, 249, 477);
		modulesBodyPanel.add(yearFourTree);
		
		JPanel panel = new JPanel();
		panel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		panel.setBounds(0, 0, 1199, 90);
		modulesBodyPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblModules = new JLabel("MODULES");
		lblModules.setHorizontalAlignment(SwingConstants.CENTER);
		lblModules.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		lblModules.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		lblModules.setBounds(0, 0, 1199, 93);
		panel.add(lblModules);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator.setBounds(121, 153, 952, 4);
		modulesBodyPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(121, 153, 4, 41);
		modulesBodyPanel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_1.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_1.setBounds(447, 153, 4, 41);
		modulesBodyPanel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setOrientation(SwingConstants.VERTICAL);
		separator_1_2.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_2.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_2.setBounds(756, 153, 4, 41);
		modulesBodyPanel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setOrientation(SwingConstants.VERTICAL);
		separator_1_3.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_3.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_3.setBounds(1071, 153, 4, 41);
		modulesBodyPanel.add(separator_1_3);
		
		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setOrientation(SwingConstants.VERTICAL);
		separator_1_4.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		separator_1_4.setBounds(600, 106, 4, 47);
		modulesBodyPanel.add(separator_1_4);
		
	}
	
	private DefaultTreeModel setTreeValues(int year) {
		return new DefaultTreeModel(new DefaultMutableTreeNode("Year 0" + year) {
			{
				Module module = new Module();
				module.setTeacherId(UniScoreClient.authUser.getUserId());
				
				DefaultMutableTreeNode node_1;
				DefaultMutableTreeNode node_2;

				try {
					for (int i = 1; i <= 2; i++) {
						node_1 = new DefaultMutableTreeNode("Semester 0"+i);
						node_2 = new DefaultMutableTreeNode("Modules");

						List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, year, i);
						if (moduleList.isEmpty()) {
							node_2.add(new DefaultMutableTreeNode("No modules"));
						} else {
							for (Module mod : moduleList) {
								node_2.add(new DefaultMutableTreeNode(mod.getModuleId()));
							}
						}
						node_1.add(node_2);
						add(node_1);
					}

				} catch (RemoteException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated modules.\nError refferance : "+ExceptionList.REMOTE);
					en.setVisible(true);
					System.out.println("RemoteException execution thrown on ModuleContentPanel.java file. Error : "+e.getCause());
				} catch (ClassNotFoundException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated modules.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on ModuleContentPanel.java file. Error : "+e.getCause());
				} catch (SQLException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve allocated modules.\nError refferance : "+ExceptionList.SQL);
					en.setVisible(true);
					System.out.println("SQLException execution thrown on ModuleContentPanel.java file. Error : "+e.getCause());
				}
			}
		});
	}
	
	/*
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
