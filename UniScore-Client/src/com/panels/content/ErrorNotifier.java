package com.panels.content;

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

import com.utils.UI;

@SuppressWarnings("serial")
public class ErrorNotifier extends JFrame {

	private JPanel contentPane;

	public ErrorNotifier(String errorMessage) {
		setTitle("ERROR");
		setBounds(100, 100, 443, 177);
		contentPane = new JPanel();
		contentPane.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null); 
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(102, 24, 325, 62);
		contentPane.add(scrollPane);
		
		JTextPane errorText = new JTextPane();
		errorText.setText(errorMessage);
		errorText.setEditable(false);
		errorText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		errorText.setSelectionColor(UI.APPLICATION_THEME_TERTIARY_COLOR);
		scrollPane.setViewportView(errorText);
		errorText.setFont(UI.APPLICATION_THEME_FONT_13_PLAIN);
		
		JLabel errorIconLabel = new JLabel("");
		errorIconLabel.setIcon(new ImageIcon(ErrorNotifier.class.getResource("/resources/error_icon.png")));
		errorIconLabel.setBounds(21, 21, 50, 50);
		contentPane.add(errorIconLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 92, 437, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
			@Override
			public void mouseEntered(MouseEvent e) {
				okButtonPanel.setBorder(new LineBorder(new Color(240, 240, 240)));
				okButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				okButtonLabel.setForeground(new Color(240, 240, 240));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				okButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
				okButtonPanel.setBackground(new Color(240, 240, 240));
				okButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
	}
}
