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
import com.panels.content.ErrorNotifier;
import com.utils.BarChart;
import com.utils.ContentTable;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Module;
import models.Submission;
import models.User;

@SuppressWarnings("serial")
public class StudentContentPanel extends ContentPanel {

	private JPanel contentPanel = new JPanel();
	private ContentTable table = new ContentTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel studentBodyPanel = new JPanel();
	private JPanel studentInfoPanel = new JPanel();
	private JTextField searchText;
	
	private User selectedStudent;
	
	public StudentContentPanel() {
		setContentPanel();
	}
	
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
		setSearchField();
		addStudentListTable("");
	}
	
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		
		studentBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentBodyPanel.setBounds(30, 66, 1199, 813);
		contentPanel.add(studentBodyPanel);
		studentBodyPanel.setLayout(null);
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
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Students");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(1130, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, 59, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}

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
		
		JLabel studentIDLabel = new JLabel(getFormatedStudentId(selectedStudent));
		studentIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		studentIDLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		studentIDLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		studentIDLabel.setBounds(1036, 113, 163, 14);
		studentInfoPanel.add(studentIDLabel);
		
		studentInfoPanel.repaint();
	}

	private void addStudentListTable(String searchText) {
		try {
			
			DefaultTableModel model = new DefaultTableModel(new String[] { "UID", "SID", "First Name", "Last Name", "Gender", "Email", "Phone" }, 0);
			
				
				User tempUser = new User();
				tempUser.setRole("Student");
				List<User> userList = (List<User>) UniScoreClient.uniscoreInterface.getUsersBySearch(searchText);
				int count = 0;
				
				for (User user : userList) {
					if(user.getRole().equalsIgnoreCase("Student")) {
						// Adding a new user record to the table each time the loop executes
						model.addRow(new Object[] { user.getUserId(), getFormatedStudentId(user), "     " + user.getFirstName(), "     " + user.getLastName(), user.getGender(), "     " + user.getEmail(), "     " + user.getPhone() });
						
						if (count < 1) {
							selectedStudent = user;
							setSelectedStudent();
						}
						count++;
					}
				}
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if (table.getSelectedRow() != -1) {
						try {
							 if (mouseEvent.getClickCount() == 2) {
						           
						        Submission submission = new Submission();
						        submission.setStudentId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
								
								Module module = new Module();
								module.setTeacherId(UniScoreClient.authUser.getUserId());
									
								BarChart studentStats = new BarChart(table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim()+" "+table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim()+"'s Statistics", "Recent Performance", "Modules", "No of Marks", UniScoreClient.uniscoreInterface.getGradedDatasetByStudent(module, submission));

								studentStats.setSize(950, 600);
								studentStats.setLocationRelativeTo(null);
								studentStats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
								studentStats.setVisible(true);
						         
							} else if (mouseEvent.getClickCount() == 1) {
								User selectedTempUser = new User();
								selectedTempUser.setUserId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
								selectedStudent = (User) UniScoreClient.uniscoreInterface.getUser(selectedTempUser);
								setSelectedStudent();
							}
							 
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
			table.getColumn("SID").setMinWidth(0);
			table.getColumn("SID").setMaxWidth(120);
			table.getColumn("SID").setWidth(120);

			// To align text to center in a column
			DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
			centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);

			// Setting width to colums in JTable
			TableColumnModel columnModel = table.getColumnModel();

			// Removing question id column, but will still be able to access by column index
            columnModel.removeColumn(columnModel.getColumn(0));
            
			columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
			columnModel.getColumn(3).setCellRenderer(centerAlingedCell);

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
			studentBodyPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
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
	
	private String getFormatedStudentId(User user) {
		String studentId = "S";
		
		switch(user.getUserId().length()) {
			case 1 : studentId = studentId.concat("00000").concat(user.getUserId()); break;
			case 2 : studentId = studentId.concat("0000").concat(user.getUserId()); break;
			case 3 : studentId = studentId.concat("000").concat(user.getUserId()); break;
			case 4 : studentId = studentId.concat("00").concat(user.getUserId()); break;
			case 5 : studentId = studentId.concat("0").concat(user.getUserId()); break;
		}
		return studentId;
	}
	
	private void setSearchField() {
		searchText = new JTextField();
		searchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				addStudentListTable(searchText.getText().trim());	
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
	 * returns the JPanel inside ContentPanel
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
