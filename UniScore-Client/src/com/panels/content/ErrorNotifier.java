package com.panels.content;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ErrorNotifier extends JFrame {

	private JPanel contentPane;

	public ErrorNotifier(String errorMessage) {
		setTitle("ERROR");
		setBounds(100, 100, 443, 177);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		errorText.setForeground(Color.DARK_GRAY);
		errorText.setSelectionColor(Color.WHITE);
		scrollPane.setViewportView(errorText);
		errorText.setFont(new Font("Roboto", Font.PLAIN, 13));
		
		JLabel errorIconLabel = new JLabel("");
		errorIconLabel.setIcon(new ImageIcon(ErrorNotifier.class.getResource("/resources/error_icon.png")));
		errorIconLabel.setBounds(21, 21, 50, 50);
		contentPane.add(errorIconLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 92, 437, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel okButtonPanel = new JPanel();
		okButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		okButtonPanel.setBorder(new LineBorder(Color.DARK_GRAY));
		okButtonPanel.setBackground(new Color(240, 240, 240));
		okButtonPanel.setBounds(341, 11, 82, 32);
		panel.add(okButtonPanel);
		okButtonPanel.setLayout(null);
		
		JLabel okButtonLabel = new JLabel("OK");
		okButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		okButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		okButtonLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		okButtonLabel.setBounds(0, 0, 82, 32);
		okButtonPanel.add(okButtonLabel);
	}
}
