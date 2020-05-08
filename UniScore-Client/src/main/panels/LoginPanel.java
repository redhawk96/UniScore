/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package main.panels;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.content.LoginContentPanel;
import com.utils.UI;

@SuppressWarnings("serial")
public class LoginPanel extends JFrame{

	// Declaring and initializing new LoginContentPanel
	private ContentPanel loginContentPanel = new LoginContentPanel();
	
	/*
	 * LoginPanel method : used to initialize JFrame, UI elements to style and support functionality of JFrame implemented here  
	 */
	public LoginPanel() {
		
		// Setting LoginPanel JFrame's icon
		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());
		
		// Setting JFrame title text
		setTitle("UNISCORE | Client Login");
		
		// Setting the size of the application screen
		setSize(UI.LOGIN_FRAME_WIDTH, UI.LOGIN_FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// setLocationRelativeTo set to null inorder to start the application center of the screen
		setLocationRelativeTo(null); 
		
		// Disabling frame resizing
		setResizable(false);
		
		// Adding login screen to JFrame
		getContentPane().add(loginContentPanel.getContent());
	}
}
