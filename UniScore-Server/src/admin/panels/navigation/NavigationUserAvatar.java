/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package admin.panels.navigation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.panels.AdminPanel;

public class NavigationUserAvatar {
	
	private JLabel icon = new JLabel("");
	
	public NavigationUserAvatar() {
		/*
		 * Adding user avatar to navigation panel
		 */
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(AdminPanel.class.getResource("/resources/avatar.png")));
		icon.setBounds(48, 45, 158, 179);
	}
	
	public JLabel getAvatar() {
		return icon;
	}
	
}