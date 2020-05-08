/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.panels.ContentPanel;
import com.utils.BarChartFrame;
import com.utils.ContentTable;
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.Identification;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Activity;
import models.Module;
import models.Submission;
import models.User;

@SuppressWarnings("serial")
public class StudentContentPanel extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain navigationIndicatorPanel and studentBodyPanel
	private JPanel contentPanel = new JPanel();
		
	// Declaring and initializing new JPanel to act as an wrapper to contain studentInfoPanel and scrollPane
	private JPanel studentBodyPanel = new JPanel();
	
	// Declaring and initializing new JPanel to act as an wrapper to contain the elements which is responsible to display selected student information. Located below navigationIndicatorPanel
	private JPanel studentInfoPanel = new JPanel();
	
	// Declaring and initializing new ContentTable(JTable with overridden methods) to display all the students 
	private ContentTable table = new ContentTable();
	
	// Declaring and initializing new JScrollPane to contain the ContentTable in an overflow 
	private JScrollPane scrollPane = new JScrollPane();
	
	// Declaring JTextField to act as the search field to lookup for any paticular student by first/last names or student id
	private JTextField searchText;
	
	// Declaring User object selectedStudent to hold the selectd student's properties(information)
	private User selectedStudent;
	
	/*
	 * StudentContentPanel method : used to initialize ContentPanel, required properties and add UI elements to the ContentPanel
	 */
	public StudentContentPanel() {
		// Adding elements to the ContentPanel
		setContentPanel();
	}
	
	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		setSearchField();
		// Calling displayStudentList method with an empty string to retrieve all students without filtering
		displayStudentList("");
	}
	
	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color
	 */
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		studentBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(studentBodyPanel);
		studentBodyPanel.setLayout(null);
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
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Students");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1130, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 59, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}

	/*
	 * Method setSelectedStudent adds UI layout(styling) to studentInfoPanel which holds the information of the selected student on the top of studentBodyPanel
	 * studentInfoPanel is a sub element under studentBodyPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color/border, JLabel text/text-color/font-size/boundaries, JSeparator orientation/backgroung-color/boundaries
	 */
	private void setSelectedStudent() {
		studentInfoPanel.removeAll();
		studentInfoPanel = new JPanel();
		studentInfoPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		studentInfoPanel.setBounds(0, 0, 1199, 138);
		studentBodyPanel.add(studentInfoPanel);
		studentInfoPanel.setLayout(null);
		
		JLabel studentAvatar = new JLabel("");
		studentAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		studentAvatar.setIcon(new ImageIcon(StudentContentPanel.class.getResource("/resources/avatar.png")));
		studentAvatar.setBounds(1036, 11, 163, 100);
		studentInfoPanel.add(studentAvatar);
		
		JLabel personalInfoLabel = new JLabel("Personal Information");
		personalInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		personalInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		personalInfoLabel.setBounds(110, 11, 139, 14);
		studentInfoPanel.add(personalInfoLabel);
		
		JLabel contactInfoLabel = new JLabel("Contact Information");
		contactInfoLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contactInfoLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		contactInfoLabel.setBounds(597, 11, 139, 14);
		studentInfoPanel.add(contactInfoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(354, 11, 686, 116);
		studentInfoPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(1036, 11, 4, 116);
		studentInfoPanel.add(separator_1);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		firstNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		firstNameLabel.setBounds(31, 48, 70, 14);
		studentInfoPanel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		lastNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		lastNameLabel.setBounds(31, 76, 70, 14);
		studentInfoPanel.add(lastNameLabel);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		genderLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		genderLabel.setBounds(31, 104, 70, 14);
		studentInfoPanel.add(genderLabel);
		
		JLabel studentFirstNameLabel = new JLabel(":  "+selectedStudent.getFirstName());
		studentFirstNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentFirstNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentFirstNameLabel.setBounds(140, 48, 204, 14);
		studentInfoPanel.add(studentFirstNameLabel);
		
		JLabel studentLastNameLabel = new JLabel(":  "+selectedStudent.getLastName());
		studentLastNameLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentLastNameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentLastNameLabel.setBounds(140, 76, 204, 14);
		studentInfoPanel.add(studentLastNameLabel);
		
		JLabel studentGender = new JLabel(":  "+selectedStudent.getGender());
		studentGender.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentGender.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentGender.setBounds(140, 104, 204, 14);
		studentInfoPanel.add(studentGender);
		
		JLabel phoneNumberLabel = new JLabel("Phone");
		phoneNumberLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		phoneNumberLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		phoneNumberLabel.setBounds(405, 48, 70, 14);
		studentInfoPanel.add(phoneNumberLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		emailLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		emailLabel.setBounds(405, 76, 70, 14);
		studentInfoPanel.add(emailLabel);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		addressLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		addressLabel.setBounds(405, 104, 70, 14);
		studentInfoPanel.add(addressLabel);
		
		JLabel studentPhoneNumberLabel = new JLabel(":  "+selectedStudent.getPhone());
		studentPhoneNumberLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentPhoneNumberLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentPhoneNumberLabel.setBounds(517, 48, 509, 14);
		studentInfoPanel.add(studentPhoneNumberLabel);
		
		JLabel studentEmailLabel = new JLabel(":  "+selectedStudent.getEmail());
		studentEmailLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentEmailLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentEmailLabel.setBounds(517, 76, 509, 14);
		studentInfoPanel.add(studentEmailLabel);
		
		JLabel studentAddressLabel = new JLabel(":  "+selectedStudent.getAddress());
		studentAddressLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentAddressLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentAddressLabel.setBounds(517, 104, 509, 17);
		studentInfoPanel.add(studentAddressLabel);
		
		JLabel studentIDLabel = new JLabel(Identification.getFormatedId(selectedStudent.getUserId(), "S"));
		studentIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		studentIDLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentIDLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentIDLabel.setBounds(1036, 113, 163, 14);
		studentInfoPanel.add(studentIDLabel);
		
		studentInfoPanel.repaint();
	}

	/*
	 * Method displayStudentList adds UI layout(styling) to studentBodyPanel below the studentInfoPanel
	 * @param searchText contains the text which is used to filter the student list by first/last or student id
	 * scrollPane which acts as an wrapper to the ContentTable is a sub element under studentBodyPanel
	 * UI layout categorized as ContentTable text/text-color/font-size/columns/background-color
	 */
	private void displayStudentList(String searchText) {
		try {
			// Creating a new DefaultTableModel to declare the column names  
			DefaultTableModel model = new DefaultTableModel(new String[] { "UID", "SID", "First Name", "Last Name", "Gender", "Email", "Phone" }, 0);
			
			// Method getUsersBySearch will retrieve all the user filtered through the searched keyword, initially the search text will be empty to retrieve all users without filtering
			List<User> userList = (List<User>) UniScoreClient.uniscoreInterface.getUsersBySearch(searchText);
			
			// Count is set to 0 to set the first row on the model to and display it on studentInfoPanel
			int count = 0;
			
			// Looping the retrieved list of users through a foreach loop to add rows to the model(DefaultTableModel). One database record is equal to one row in the model(DefaultTableModel)
			for (User user : userList) {
				if(user.getRole().equalsIgnoreCase("Student")) {
					// When adding a row to the model, make sure than column values are parallel(relevant) to the column headers. White spaces are added to enhance UX and not required by default
					model.addRow(new Object[] { user.getUserId(), Identification.getFormatedId(user.getUserId(), "S"), "     " + user.getFirstName(), "     " + user.getLastName(), user.getGender(), "     " + user.getEmail(), "     " + user.getPhone() });
					
					// This statement will only be true for the first student, this will be the values to studentInfoPanel by default
					if (count < 1) {
						selectedStudent = user;
						setSelectedStudent();
					}
					// Inorder to avoid studentInfoPanel being repainted only once
					count++;
				}
			}
			
			table.addMouseListener(new MouseAdapter() {
				/*
				 * Method mouseClicked to handle mouse click events
				 * studentInfoPanel will be repainted with selected(clicked) exam on mouse click
				 * @param mouseEvent to get information about the mosue click
				 */
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if (table.getSelectedRow() != -1) {
						try {
							// If click count is > 2, if block will execute and else if not
							 if (mouseEvent.getClickCount() == 2) { 
								/* 
								 * Method getGradedDatasetByStudent accepts 2 parameters, Submission and a Module object
								 * student id from the submission object, hence submission object is create and student id is set
								 * lecturer id from the module object, hence module object is create and lecturer id is set
								 */
						        Submission submission = new Submission();
						        submission.setStudentId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
								Module module = new Module();
								module.setTeacherId(UniScoreClient.authUser.getUserId());
											
								// Plotting a bar chart of the student's marks for the most recent exams of signed-in lecturer's allocated modules 
								BarChartFrame studentStats = new BarChartFrame(table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim()+" "+table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim()+"'s Statistics", "Recent Performance", "Modules", "No of Marks", UniScoreClient.uniscoreInterface.getGradedDatasetByStudent(module, submission));

								// Defining JFrame properties
								studentStats.setSize(950, 600);
								studentStats.setLocationRelativeTo(null);
								studentStats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
								studentStats.setVisible(true);
								
								// Adding a record to the database of the display of academic performances for the selected student followed by the lecturer id under activities table
								UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Academic performance of "+Identification.getFormatedId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), "S")+" was viewed from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));	
						         
							} else if (mouseEvent.getClickCount() == 1) {
								
								// Method getModule will retrieve all the student properties, method accepts a User object with user id set
								User selectedTempUser = new User();
								selectedTempUser.setUserId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
								
								// Setting existing values with selected student properties to repaint the studentInfoPanel
								selectedStudent = (User) UniScoreClient.uniscoreInterface.getUser(selectedTempUser);
								
								// Repainting studentInfoPanel with the selected student
								setSelectedStudent();
							}
							 
						 /*
						 * If there was exception thrown when executing the retrieval of student properties or plotting the graph for the selected student,
						 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
						 */
						} catch (RemoteException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected student details.\nError refferance : "+ExceptionList.REMOTE);
							en.setVisible(true);
							System.out.println("RemoteException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
						} catch (ClassNotFoundException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected student details.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
							en.setVisible(true);
							System.out.println("ClassNotFoundException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
						} catch (SQLException e) {
							ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve selected student details.\nError refferance : "+ExceptionList.SQL);
							en.setVisible(true);
							System.out.println("SQLException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
						}
					}
				}
			});
		
			
			// Styling ContentTable to enhance UX
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
	
			// Adding model(DefaultTableModel) the the ContentTable
			table.setModel(model);
			
			// Setting width of 'SID' column
			table.getColumn("SID").setMinWidth(0);
			table.getColumn("SID").setMaxWidth(120);
			table.getColumn("SID").setWidth(120);

			// DefaultTableCellRenderer object created to add alignment. In this case, setting the cloumn content alignment to center
	        DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
	        centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);

	        // TableColumnModel object created to get the column structure in the ContentTable
	        TableColumnModel columnModel = table.getColumnModel();
	       
	        // Removing exam id column from the ContentTable, but will still be able to access by column index
	        columnModel.removeColumn(columnModel.getColumn(0));
            
            // Aligning cloumns by their index
			columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
			columnModel.getColumn(3).setCellRenderer(centerAlingedCell);

			// Removing horizontal cell borders
	        table.setShowHorizontalLines(false);
	        
	        // Setting cursor type to pointer
			table.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
			
			// Styling ContentTable to enhance UX
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
			studentBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
		/*
		 * If there was exception thrown when executing the retrieval of students,
		 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
		 */
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve available students.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve available students.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve available students.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on StudentContentPanel.java file. Error : "+e.getCause());
		}
	}
	
	/*
	 * Method addSearchField adds UI layout(styling) to studentBodyPanel below the studentInfoPanel
	 * searchText and searchLabel are a sub element under studentBodyPanel
	 * UI layout categorized as JLabel text/text-color/font-size/boundaries, JTextField text/text-color/font-size/boundaries/columns/background-color
	 */
	private void setSearchField() {
		searchText = new JTextField();
		searchText.addKeyListener(new KeyAdapter() {
			/*
			 * Method keyTyped to handle keyboard type events
			 * displayStudentList method will be executed(repainted) on keyboard press to search of the typed text in the JTextField
			 * @param arg0 to get information about the key press event 
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {
				displayStudentList(searchText.getText().trim());	
			}
		});
		searchText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		searchText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		searchText.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		searchText.setBounds(978, 172, 219, 31);
		studentBodyPanel.add(searchText);
		searchText.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search    :");
		searchLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		searchLabel.setBounds(908, 172, 60, 31);
		studentBodyPanel.add(searchLabel);
	}
	
	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * @returns JPanel 	Contains completed layout of with the add sub elements 
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
