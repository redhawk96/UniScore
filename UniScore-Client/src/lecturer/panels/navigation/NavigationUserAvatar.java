package lecturer.panels.navigation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.panels.LecturerPanel;

public class NavigationUserAvatar {
	
	private JLabel icon = new JLabel("");
	
	public NavigationUserAvatar() {
		/*
		 * Adding user avatar to navigation panel
		 */
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(LecturerPanel.class.getResource("/resources/avatar.png")));
		icon.setBounds(32, 11, 158, 179);
	}
	
	public JLabel getAvatar() {
		return icon;
	}
	
}
