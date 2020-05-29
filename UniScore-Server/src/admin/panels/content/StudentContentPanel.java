package admin.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
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
import admin.panels.content.StudentContentPanel;
import models.User;

@SuppressWarnings("serial")
public class StudentContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();
	JPanel moduleContentPanel = new JPanel();
	JPanel studentInfoPanel = new JPanel();
	
	public StudentContentPanel() {
		contentPanel.setName("student");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);
		
		try {
			displayNavigationIndicator();
			
			moduleContentPanel.setBackground(Color.WHITE);
			moduleContentPanel.setBounds(30, 66, 1096, 793);
			contentPanel.add(moduleContentPanel);
			moduleContentPanel.setLayout(null);
			
			// Setting studentInfo panel to default
			setSelectedStudent("", "", "", -1, "" , "", "");
			
			DefaultTableModel model = new DefaultTableModel(new String[] { "UID", "First Name", "Last Name", "Gender", "Email", "Status"}, 0);

			User tempUser = new User();
			tempUser.setRole("Student");
			List<User> userList = (List<User>) UniScoreServer.uniscoreInterface.getUsersByType(tempUser);

			for (User user : userList) {
				// Adding a new user record to the table each time the loop executes
				model.addRow(new Object[] {user.getUserId(),  "     "+user.getFirstName(), "     "+user.getLastName(), "     "+ user.getGender(), "     "+user.getEmail(), "     "+user.getStatus()});
			}
			table.setForeground(Color.DARK_GRAY);
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					 if(table.getSelectedRow() != -1) {
						 try {
							 User selectedTempUser = new User();
							 selectedTempUser.setUserId(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
							 User selectedUser = (User)UniScoreServer.uniscoreInterface.getUser(selectedTempUser);
							 
							 setSelectedStudent(selectedUser.getFirstName(), selectedUser.getLastName(), selectedUser.getGender(), selectedUser.getPhone(), selectedUser.getEmail(), selectedUser.getAddress(), selectedUser.getUserId());
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
			scrollPane.setBounds(0, 172, 1086, 75);
			moduleContentPanel.add(scrollPane);
			scrollPane.setViewportView(table);
			
			
			
		} catch (Exception e) {
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
		navigationIndicatorPanel.setBounds(30, 11, 1085, 36);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);
		
		JLabel navigationIndicatorMainLabel = new JLabel("Admin /");
		navigationIndicatorMainLabel.setBounds(932, 8, UI.NAVIGATION_INDICATOR_PANEL_MAIN_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorMainLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_MAIN_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);
		
		JLabel navigationIndicatorActiveLabel = new JLabel("Students");
		navigationIndicatorActiveLabel.setFont(UI.NAVIGATION_INDICATOR_PANEL_FONT);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_LABEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.NAVIGATION_INDICATOR_PANEL_ACTIVE_TEXT_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}
	
	public void setSelectedStudent(String firstName, String lastName, String gender, int phone, String email, String address, String studentId) {
		studentInfoPanel.removeAll();
		studentInfoPanel = new JPanel();
		studentInfoPanel.setBackground(Color.DARK_GRAY);
		studentInfoPanel.setBounds(0, 0, 1086, 138);
		moduleContentPanel.add(studentInfoPanel);
		studentInfoPanel.setLayout(null);
		
		JLabel studentAvatar = new JLabel("");
		studentAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		studentAvatar.setIcon(new ImageIcon(StudentContentPanel.class.getResource("/resources/avatar.png")));
		studentAvatar.setBounds(917, 18, 94, 100);
		studentInfoPanel.add(studentAvatar);
		
		JLabel personalInfoLabel = new JLabel("Personal Information");
		personalInfoLabel.setForeground(Color.WHITE);
		personalInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		personalInfoLabel.setBounds(110, 11, 139, 14);
		studentInfoPanel.add(personalInfoLabel);
		
		JLabel contactInfoLabel = new JLabel("Contact Information");
		contactInfoLabel.setForeground(Color.WHITE);
		contactInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
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
		separator_1.setBounds(821, 11, 4, 116);
		studentInfoPanel.add(separator_1);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		firstNameLabel.setBounds(31, 48, 70, 14);
		studentInfoPanel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		lastNameLabel.setBounds(31, 76, 70, 14);
		studentInfoPanel.add(lastNameLabel);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		genderLabel.setBounds(31, 104, 70, 14);
		studentInfoPanel.add(genderLabel);
		
		JLabel studentFirstNameLabel = new JLabel(":  "+firstName);
		studentFirstNameLabel.setForeground(Color.WHITE);
		studentFirstNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentFirstNameLabel.setBounds(140, 48, 204, 14);
		studentInfoPanel.add(studentFirstNameLabel);
		
		JLabel studentLastNameLabel = new JLabel(":  "+lastName);
		studentLastNameLabel.setForeground(Color.WHITE);
		studentLastNameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentLastNameLabel.setBounds(140, 76, 204, 14);
		studentInfoPanel.add(studentLastNameLabel);
		
		JLabel studentGender = new JLabel(":  "+gender);
		studentGender.setForeground(Color.WHITE);
		studentGender.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentGender.setBounds(140, 104, 204, 14);
		studentInfoPanel.add(studentGender);
		
		JLabel phoneNumberLabel = new JLabel("Phone");
		phoneNumberLabel.setForeground(Color.WHITE);
		phoneNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		phoneNumberLabel.setBounds(405, 48, 70, 14);
		studentInfoPanel.add(phoneNumberLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		emailLabel.setBounds(405, 76, 70, 14);
		studentInfoPanel.add(emailLabel);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		addressLabel.setBounds(405, 104, 70, 14);
		studentInfoPanel.add(addressLabel);
		
		if(phone == -1) {
			JLabel studentPhoneNumberLabel = new JLabel(":  ");
			studentPhoneNumberLabel.setForeground(Color.WHITE);
			studentPhoneNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			studentPhoneNumberLabel.setBounds(517, 48, 509, 14);
			studentInfoPanel.add(studentPhoneNumberLabel);
		}else {
			JLabel studentPhoneNumberLabel = new JLabel(":  "+phone);
			studentPhoneNumberLabel.setForeground(Color.WHITE);
			studentPhoneNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
			studentPhoneNumberLabel.setBounds(517, 48, 509, 14);
			studentInfoPanel.add(studentPhoneNumberLabel);
		}
		
		JLabel studentEmailLabel = new JLabel(":  "+email);
		studentEmailLabel.setForeground(Color.WHITE);
		studentEmailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentEmailLabel.setBounds(517, 76, 509, 14);
		studentInfoPanel.add(studentEmailLabel);
		
		JLabel studentAddressLabel = new JLabel(":  "+address);
		studentAddressLabel.setForeground(Color.WHITE);
		studentAddressLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentAddressLabel.setBounds(517, 104, 509, 17);
		studentInfoPanel.add(studentAddressLabel);
		
		JLabel studentIDLabel = new JLabel(studentId);
		studentIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		studentIDLabel.setForeground(Color.WHITE);
		studentIDLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		studentIDLabel.setBounds(1083, 113, 94, 14);
		studentInfoPanel.add(studentIDLabel);
		
		studentInfoPanel.repaint();
	}
}
