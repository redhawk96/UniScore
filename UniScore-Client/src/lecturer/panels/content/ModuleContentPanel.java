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

	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and modulesBodyPanel
	private JPanel contentPanel = new JPanel();
			
	// Declaring and initializing new JPanel to act as an wrapper to contain module trees
	private JPanel modulesBodyPanel = new JPanel();

	/*
	 * ModuleContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 */
	public ModuleContentPanel() {
		// Adding elements to the ContentPanel
		setContentPanel();
	}

	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		addModuleTrees();
	}
	
	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color
	 */
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		modulesBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		modulesBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(modulesBodyPanel);
		modulesBodyPanel.setLayout(null);
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
	
	/*
	 * Method addModuleTrees adds UI layout(styling) to modulesBodyPanel below the navigationIndicatorPanel
	 * addModuleTrees is a sub element under modulesBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries, JTree text/text-color/font-size/boundaries/border, JSeparator orientation/backgroung-color/boundaries
	 */
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
		// Calling method setTreeValues to set allocated modules for the academic year 01  
		yearOneTree.setModel(setTreeValues(1));
		yearOneTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearOneTree.setRowHeight(30);
		yearOneTree.setBorder(null);
		yearOneTree.setVisibleRowCount(30);
		yearOneTree.setBounds(0, 284, 249, 477);
		modulesBodyPanel.add(yearOneTree);
		
		
		JTree yearTwoTree = new JTree();
		// Calling method setTreeValues to set allocated modules for the academic year 02  
		yearTwoTree.setModel(setTreeValues(2));
		yearTwoTree.setVisibleRowCount(30);
		yearTwoTree.setRowHeight(30);
		yearTwoTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearTwoTree.setBorder(null);
		yearTwoTree.setBounds(323, 284, 249, 477);
		modulesBodyPanel.add(yearTwoTree);

		
		JTree yearThreeTree = new JTree();
		// Calling method setTreeValues to set allocated modules for the academic year 03  
		yearThreeTree.setModel(setTreeValues(3));
		yearThreeTree.setVisibleRowCount(30);
		yearThreeTree.setRowHeight(30);
		yearThreeTree.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		yearThreeTree.setBorder(null);
		yearThreeTree.setBounds(636, 284, 249, 477);
		modulesBodyPanel.add(yearThreeTree);
		
		JTree yearFourTree = new JTree();
		// Calling method setTreeValues to set allocated modules for the academic year 04
		yearFourTree.setModel(setTreeValues(4));
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
	
	/*
	 * Method DefaultTreeModel used to draw a DefaultTreeModel
	 * @param year contains the academic year which the tree needs to plotted to
	 * @return DefaultTreeModel contaning the allocated modules for the signed-in lecturer for the given year
	 */
	private DefaultTreeModel setTreeValues(int year) {
		// Creating a new DefaultMutableTreeNode with passed year from the parameter
		return new DefaultTreeModel(new DefaultMutableTreeNode("Year 0" + year) {
			{
				// Method getModulesByRelevance accepts a Module object with the lecturer id set and 2 more integers(academic year and semester)	
				Module module = new Module();
				module.setTeacherId(UniScoreClient.authUser.getUserId());
				
				// node_1 is to hold the title of the semester, and node_2 to hold title 'Modules'
				DefaultMutableTreeNode node_1;
				DefaultMutableTreeNode node_2;

				try {
					// Creating a for loop to retrieve modules for both semesters, loop will be running 2 times for a given year
					for (int i = 1; i <= 2; i++) {
						// Setting titles to nodes
						node_1 = new DefaultMutableTreeNode("Semester 0"+i);
						node_2 = new DefaultMutableTreeNode("Modules");

						// Method getModulesByRelevance will retrieve all the allocated modules for the signed-in lecturer filtered by year and semester
						List<Module> moduleList = (List<Module>) UniScoreClient.uniscoreInterface.getModulesByRelevance(module, year, i);
						
						// Incase of the moduleList is empty, if block will execute to show that no modules are allocated the paticular semester in the provided year and else block if not
						if (moduleList.isEmpty()) {
							node_2.add(new DefaultMutableTreeNode("No modules"));
						} else {
							// Looping the retrieved list of modules through a foreach loop and adding one after the other to node_2
							for (Module mod : moduleList) {
								// node_2 will hold all the allocated modules as its sub elements
								node_2.add(new DefaultMutableTreeNode(mod.getModuleId()));
							}
						}
						// node_1 will hold node_2 as its sub element
						node_1.add(node_2);
						add(node_1);
					}

				/*
				 * If there was exception thrown when executing the retrieval of allocated modules filtered through signed in lecturer, academic year and semester
				 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
				 */
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
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * @returns JPanel 	Contains completed layout of with the add sub elements 
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
