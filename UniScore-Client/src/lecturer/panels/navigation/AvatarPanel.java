/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.navigation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.panels.LecturerPanel;

public class AvatarPanel {
	
	// Declaring and initializing new JLabel to be used for styling of lecturer avatar
	private JLabel icon = new JLabel("");
	
	/*
	 * AvatarPanel method : used to initialize JPanel, required properties and add UI elements to the JPanel
	 */
	public AvatarPanel() {
		// Adding lecturer avatar to navigation panel
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(LecturerPanel.class.getResource("/resources/avatar.png")));
		icon.setBounds(52, 45, 158, 179);
	}
	
	/*
	 * getAvatar : Method is used to return the JPanel which has the styling of its sub elements for lecturer avatar
	 * @return JPanel which contains the sub elements with added styling 
	 */
	public JLabel getAvatar() {
		return icon;
	}
	
}
