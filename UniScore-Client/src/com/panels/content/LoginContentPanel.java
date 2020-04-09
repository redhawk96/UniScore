/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package com.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.panels.ContentPanel;
import com.utils.UI;

import connectivity.UniScoreClient;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import main.panels.StudentPanel;
import models.User;
import java.awt.Insets;

@SuppressWarnings("serial")
public class LoginContentPanel  extends ContentPanel{

	private JPanel contentPanel = new JPanel();
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JPanel loginButton;
	private JLabel signinLabel;
	private JLabel loginBackground;
	private JLabel logo;
	private JLabel loadingLabel;
	private JLabel errorLabel;

	public LoginContentPanel() {
		/*
		 * Adding elements to login content panel
		 * Login content panel size will the the same as the application size
		 */
		contentPanel.setName("login");
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, UI.LOGIN_PANEL_APPLICATION_WIDTH, UI.LOGIN_PANEL_APPLICATION_HEIGHT);
		contentPanel.setBackground(Color.WHITE);
		
		/*
		 * Adding username label
		 */
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(UI.LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT);
		usernameLabel.setBounds(760, 260, 67, 22);
		contentPanel.add(usernameLabel);
		
		/*
		 * Adding username text-field
		 */
		usernameTextField = new JTextField();
		usernameTextField.setMargin(new Insets(2, 10, 2, 2));
		usernameTextField.setFont(UI.LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT);
		usernameTextField.setBounds(760, 283, 280, 34);
		contentPanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		/*
		 * Adding password label
		 */
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(UI.LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT);
		passwordLabel.setBounds(760, 343, 67, 22);
		contentPanel.add(passwordLabel);
		
		/*
		 * Adding password field
		 */
		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(2, 10, 2, 2));
		passwordField.setBounds(760, 365, 280, 34);
		passwordField.setFont(UI.LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT);
		passwordField.setColumns(10);
		contentPanel.add(passwordField);
		
		/*
		 * Adding signin button
		 */
		loginButton = new JPanel();
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(new LineBorder(UI.LOGIN_PANEL_LOGIN_BUTTON_BORDER_COLOR));
		loginButton.setBackground(UI.LOGIN_PANEL_LOGIN_BUTTON_COLOR);
		loginButton.setBounds(923, 437, 117, 44);
		contentPanel.add(loginButton);
		loginButton.setLayout(null);
		
		/*
		 * Adding singin text inside signin button
		 */
		signinLabel = new JLabel("Sign In");
		signinLabel.setBounds(31, 11, 63, 20);
		signinLabel.setFont(UI.LOGIN_PANEL_LOGIN_BUTTON_FONT);
		signinLabel.setForeground(UI.LOGIN_PANEL_LOGIN_BUTTON_TEXT_COLOR);
		loginButton.add(signinLabel);
		
		/*
		 * Adding loading label
		 */
		loadingLabel = new JLabel("Error authenticating...");
		loadingLabel.setFont(UI.LOGIN_PANEL_ERROR_LABEL_FONT);
		loadingLabel.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/loading.gif")));
		loadingLabel.setBounds(760, 437, 153, 44);
		loadingLabel.setVisible(false);
		contentPanel.add(loadingLabel);
		
		/*
		 * Adding error label
		 */
		errorLabel = new JLabel("Please re-check your credentials");
		errorLabel.setFont(UI.LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(760, 216, 280, 22);
		errorLabel.setVisible(false);
		contentPanel.add(errorLabel);
		
		/*
		 * Implementing mouse listeners to enhance user experience
		 * Enhancement implementation : changing signin button and button text color as mouse enters and leaves the signin button
		 * Signin implementation to validate user credentials added on mouse click
		 */
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBorder(new LineBorder(UI.LOGIN_PANEL_SELECTED_LOGIN_BUTTON_BORDER_COLOR));
				loginButton.setBackground(UI.LOGIN_PANEL_SELECTED_LOGIN_BUTTON_COLOR);
				signinLabel.setForeground(UI.LOGIN_PANEL_SELECTED_LOGIN_BUTTON_TEXT_COLOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBorder(new LineBorder(UI.LOGIN_PANEL_LOGIN_BUTTON_BORDER_COLOR));
				loginButton.setBackground(UI.LOGIN_PANEL_LOGIN_BUTTON_COLOR);
				signinLabel.setForeground(UI.LOGIN_PANEL_LOGIN_BUTTON_TEXT_COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					/*
					 * Setting visibility of loading label true
					 * Setting visibility of error label false
					 */
					errorLabel.setVisible(false);
					loadingLabel.setVisible(true);
					
					/*
					 * Setting user inputed values to user object
					 */
					User user = new User();
					user.setUserId(usernameTextField.getText());
					user.setPassword(new String(passwordField.getPassword()));
					
					/*
					 * Encrypting user provided password
					 */
					user.setPassword((String)UniScoreClient.uniscoreInterface.encrypt(user));
					
					/*
					 * Checking if the provided credentials match a user in the database
					 */
					boolean authUser = (boolean)UniScoreClient.uniscoreInterface.isUserAvailable(user);
					
					if(authUser) {
						/*
						 * If provided credentials match a user
						 * Logged user will be set to a static user object to be used as a user cookie through the application untill logout
						 * Current login JFrame will be disposed and new AdminPanel JFrame will be created
						 */
						UniScoreClient.authUser = (User)UniScoreClient.uniscoreInterface.getUser(user);
						loadingLabel.setVisible(false);
						
						/*
						 * Opening up lecturer or student panel accordingly
						 */
						if(UniScoreClient.authUser.getRole().toString().equalsIgnoreCase("Lecturer")) {
							UniScoreClient.loginPanel.dispose();
							UniScoreClient.lecturerPanel = new LecturerPanel();
							UniScoreClient.lecturerPanel.setVisible(true);
						}else if(UniScoreClient.authUser.getRole().toString().equalsIgnoreCase("Student")) {
							UniScoreClient.loginPanel.dispose();
							UniScoreClient.studentPanel = new StudentPanel();
							UniScoreClient.studentPanel.setVisible(true);
							
						}else {
							loadingLabel.setVisible(true);
						}
					}else {
						/*
						 * If provided credentials are incorrect
						 * Setting visibility of loading label false
						 * Setting visibility of error label true
						 */
						errorLabel.setVisible(true);
						loadingLabel.setVisible(false);
					}
					
				} catch (ClassNotFoundException | SQLException | RemoteException | NoSuchAlgorithmException | NoSuchProviderException e) {
					System.out.println("Failed execution on LoginContentPanel. Error : " + e.toString());
				}
			}
		});
		
		/*
		 * Adding login background, again to make the application more user-friendly
		 */
		loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/login-background.jpg")));
		loginBackground.setHorizontalAlignment(SwingConstants.CENTER);
		loginBackground.setBounds(0, 0, 708, 672);
		contentPanel.add(loginBackground);
		
		/*
		 * Adding logo
		 */
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/logo.png")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(784, 91, 214, 115);
		contentPanel.add(logo);
		
	}

	@Override
	public JPanel getContent() {
		return contentPanel;
	}
}
