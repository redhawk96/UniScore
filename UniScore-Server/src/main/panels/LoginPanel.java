package main.panels;

import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.utils.UI;

import admin.panels.content.LoginContentPanel;

@SuppressWarnings("serial")
public class LoginPanel extends JFrame{

	private ContentPanel contentPanel = new LoginContentPanel();
	
	public LoginPanel() {
		/*
		 * Setting the size of the application screen
		 */
		setTitle("UNISCORE | Admin");
		setSize(UI.APPLICATION_WIDTH, UI.APPLICATION_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(contentPanel.getContent());
	}
	
	public void navigateToDashboard() {
		
	}
}
