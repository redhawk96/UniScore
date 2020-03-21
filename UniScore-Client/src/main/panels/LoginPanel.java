/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package main.panels;

import javax.swing.JFrame;
import com.panels.ContentPanel;
import com.panels.content.LoginContentPanel;
import com.utils.UI;

@SuppressWarnings("serial")
public class LoginPanel extends JFrame{

	private ContentPanel contentPanel = new LoginContentPanel();
	
	public LoginPanel() {
		
		/*
		 * Setting JFrame title text
		 */
		setTitle("UNISCORE | Client Login");
		
		/*
		 * Setting the size of the application screen
		 */
		setSize(UI.APPLICATION_WIDTH, UI.APPLICATION_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		/*
		 * setLocationRelativeTo set to null inorder to start the application center of the screen
		 */
		setLocationRelativeTo(null); 
		
		/*
		 * Disabling frame resizing
		 */
		setResizable(false);
		
		/*
		 * Adding login screen to JFrame
		 */
		getContentPane().add(contentPanel.getContent());
	}
	
	public void navigateToDashboard() {
		
	}
}
