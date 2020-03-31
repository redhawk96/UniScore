package lecturer.panels.content;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.panels.ContentPanel;
import com.utils.ContentTable;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.User;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class StudentContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	ContentTable table = new ContentTable();
	JScrollPane scrollPane = new JScrollPane();

	public StudentContentPanel() {
		contentPanel.setName("student");
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);
		contentPanel.setLayout(null);
		
		try {
			DefaultTableModel model = new DefaultTableModel(new String[] { "UID", "Last Name", "First Name", "Sex", "Phone", "Email"}, 0);

			User tempUser = new User();
			tempUser.setRole("Student");
			List<User> userList = (List<User>) UniScoreClient.uniscoreInterface.getUsersByType(tempUser);

			for (User user : userList) {
				// Adding a new user record to the table each time the loop executes
				model.addRow(new Object[] {user.getUserId(), user.getLastName(), user.getFirstName(), user.getGender(), user.getPhone(), user.getEmail()});
			}
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

			scrollPane.setBounds(18, 25, 815, 580);
			
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.getTableHeader().setOpaque(false);
			table.getTableHeader().setBackground(Color.WHITE);
			table.getTableHeader().setForeground(Color.BLACK);
			table.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 14));
			table.setSelectionBackground(SystemColor.inactiveCaption);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(32);
			table.setFont(new Font("Roboto", Font.PLAIN, 13));
			table.isCellEditable(1, 1);
			scrollPane.setViewportView(table);
			contentPanel.add(scrollPane);
			
			
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
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
}
