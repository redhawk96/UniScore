/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.panels.ContentPanel;
import com.panels.NavigationPanel;

import main.panels.LecturerPanel;

@SuppressWarnings("serial")
public class SuccessNotifier extends JFrame {
	/*
	 * SuccessNotifier method : used to initialize JPanel and required properties and add UI elements to the JFrame, JFrame will be used as a pop-up windows for success messages/confirmation messages
	 * @param confirmMessage    String is accepted to display the type of the success/confirmation message according to different situvations 
	 */
	public SuccessNotifier(String confirmMessage, NavigationPanel navigationRouter, ContentPanel contentRouter) {
		// Defining the JFrame properties
		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());
		setTitle("SUCCESS");
		setBounds(100, 100, 443, 177);
		
		// Creating a JPanel to containt all the sub elements, panel buttons, message and icon
		JPanel contentPane = new JPanel();
		contentPane.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Adding a JScrollPane incase of an overflow with the message
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(102, 24, 325, 62);
		contentPane.add(scrollPane);
		
		// Defining error message in JTextPane
		JTextPane errorText = new JTextPane();
		errorText.setText(confirmMessage);
		errorText.setEditable(false);
		errorText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		errorText.setSelectionColor(UI.APPLICATION_THEME_TERTIARY_COLOR);
		scrollPane.setViewportView(errorText);
		errorText.setFont(UI.APPLICATION_THEME_FONT_13_PLAIN);
		
		// Adding icon 
		JLabel errorIconLabel = new JLabel("");
		errorIconLabel.setIcon(new ImageIcon(SuccessNotifier.class.getResource("/resources/success_icon.png")));
		errorIconLabel.setBounds(21, 21, 50, 50);
		contentPane.add(errorIconLabel);
		
		// Adding a new JPanel to set the look and feel of the pop-up like native windows pop-up(UX)
		JPanel panel = new JPanel();
		panel.setBounds(0, 92, 437, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Styling JPanel as a button 
		JPanel okButtonPanel = new JPanel();
		okButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		okButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
		okButtonPanel.setBackground(new Color(240, 240, 240));
		okButtonPanel.setBounds(341, 11, 82, 32);
		panel.add(okButtonPanel);
		okButtonPanel.setLayout(null);
		
		JLabel okButtonLabel = new JLabel("OK");
		okButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		okButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		okButtonLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		okButtonLabel.setBounds(0, 0, 82, 32);
		okButtonPanel.add(okButtonLabel);
		
		
		okButtonPanel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseEntered to handle mouse click events
			 * SuccessNotifier JFrame okButtonPanel and okButtonLabel will change color accordingly on mouse enter to enhance UX, user will know that panel can be clicked 
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				okButtonPanel.setBorder(new LineBorder(new Color(240, 240, 240)));
				okButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				okButtonLabel.setForeground(new Color(240, 240, 240));
			}
			
			/*
			 * Method mouseExited to handle mouse click events
			 * SuccessNotifier JFrame okButtonPanel and okButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that mouse is not in click range
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				okButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
				okButtonPanel.setBackground(new Color(240, 240, 240));
				okButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
			
			/*
			 * Method mouseClicked to handle mouse click events
			 * SuccessNotifier JFrame will be either disposed(closed) or redirected to a ContentPanel on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(navigationRouter != null && contentRouter != null) {
					LecturerPanel.selectedNavigation = navigationRouter;
					LecturerPanel.selectedContent = contentRouter;
					LecturerPanel.setSelectedPanel();
					dispose();
				} else {
					dispose();
				}
			}
		});
	}
	
}
