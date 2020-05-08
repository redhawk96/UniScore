/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
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
import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.UI;

import connectivity.UniScoreClient;
import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import main.panels.StudentPanel;
import models.Activity;
import models.User;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		contentPanel.setBounds(0, 0, UI.LOGIN_FRAME_WIDTH, UI.LOGIN_FRAME_HEIGHT);
		contentPanel.setBackground(Color.WHITE);
		
		/*
		 * Adding username label
		 */
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		usernameLabel.setBounds(760, 260, 67, 22);
		contentPanel.add(usernameLabel);
		
		/*
		 * Adding username text-field
		 */
		usernameTextField = new JTextField();
		usernameTextField.setForeground(UI.APPLICATION_THEME_QUATERNARY_COLOR);
		usernameTextField.setMargin(new Insets(2, 10, 2, 2));
		usernameTextField.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		usernameTextField.setBounds(760, 283, 280, 34);
		contentPanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		/*
		 * Adding password label
		 */
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		passwordLabel.setBounds(760, 343, 67, 22);
		contentPanel.add(passwordLabel);
		
		/*
		 * Adding password field
		 */
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					authenticateUser();
				}
			}
		});
		passwordField.setForeground(UI.APPLICATION_THEME_QUATERNARY_COLOR);
		passwordField.setMargin(new Insets(2, 10, 2, 2));
		passwordField.setBounds(760, 365, 280, 34);
		passwordField.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		passwordField.setColumns(10);
		contentPanel.add(passwordField);
		
		/*
		 * Adding signin button
		 */
		loginButton = new JPanel();
		loginButton.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		loginButton.setBorder(new LineBorder(UI.APPLICATION_THEME_PRIMARY_COLOR));
		loginButton.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		loginButton.setBounds(923, 437, 117, 44);
		contentPanel.add(loginButton);
		loginButton.setLayout(null);
		
		/*
		 * Adding singin text inside signin button
		 */
		signinLabel = new JLabel("Sign In");
		signinLabel.setBounds(31, 11, 63, 20);
		signinLabel.setFont(UI.APPLICATION_THEME_FONT_17_PLAIN);
		signinLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		loginButton.add(signinLabel);
		
		/*
		 * Adding loading label
		 */
		loadingLabel = new JLabel("Error authenticating...");
		loadingLabel.setFont(UI.APPLICATION_THEME_FONT_12_PLAIN);
		loadingLabel.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/loading.gif")));
		loadingLabel.setBounds(760, 437, 153, 44);
		loadingLabel.setVisible(false);
		contentPanel.add(loadingLabel);
		
		/*
		 * Adding error label
		 */
		errorLabel = new JLabel("Please re-check your credentials");
		errorLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
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
				loginButton.setBorder(new LineBorder(UI.APPLICATION_THEME_PRIMARY_COLOR));
				loginButton.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
				signinLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBorder(new LineBorder(UI.APPLICATION_THEME_PRIMARY_COLOR));
				loginButton.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
				signinLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				authenticateUser();
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

	public void authenticateUser() {
		try {
			/*
			 * Setting visibility of loading label true
			 * Setting visibility of error label false
			 */
			errorLabel.setVisible(false);
			loadingLabel.setVisible(true);
			
			/*
			 * Validating whether the user to be authenticated is of type lecturer or student 
			 */
			if (usernameTextField.getText().trim().charAt(0) == 'L' || usernameTextField.getText().trim().charAt(0) == 'l' || usernameTextField.getText().trim().charAt(0) == 'S' || usernameTextField.getText().trim().charAt(0) == 's') {

				/*
				 * Setting user inputed values to user object
				 */
				User user = new User();

				user.setUserId(usernameTextField.getText().trim().substring(1));
				user.setPassword(new String(passwordField.getPassword()));

				/*
				 * Encrypting user provided password
				 */
				user.setPassword((String) UniScoreClient.uniscoreInterface.encrypt(user));

				/*
				 * Checking if the provided credentials match a user in the database
				 */
				boolean authUser = (boolean) UniScoreClient.uniscoreInterface.isUserAvailable(user);

				if (authUser) {
					/*
					 * If provided credentials match a user Logged user will be set to a static user object to be used as a user cookie through the application untill logout
					 * Current login JFrame will be disposed and new Lecturer/Student Panel JFrame will be created
					 */
					UniScoreClient.authUser = (User) UniScoreClient.uniscoreInterface.getUser(user);
					loadingLabel.setVisible(false);

					/*
					 * Opening up lecturer or student panel accordingly
					 */
					if (UniScoreClient.authUser.getRole().toString().equalsIgnoreCase("Lecturer") && (usernameTextField.getText().trim().charAt(0) == 'L' || usernameTextField.getText().trim().charAt(0) == 'l')) {
						UniScoreClient.loginPanel.dispose();
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity(usernameTextField.getText()+" logged in to the system from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
						UniScoreClient.lecturerPanel = new LecturerPanel();
						UniScoreClient.lecturerPanel.setVisible(true);
					} else if (UniScoreClient.authUser.getRole().toString().equalsIgnoreCase("Student") && (usernameTextField.getText().trim().charAt(0) == 'S' || usernameTextField.getText().trim().charAt(0) == 's')) {
						UniScoreClient.loginPanel.dispose();
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity(usernameTextField.getText()+" logged in to the system from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
						UniScoreClient.studentPanel = new StudentPanel();
						UniScoreClient.studentPanel.setVisible(true);

					} else {
						/*
						 * If provided credentials match but is role not under any authorized role type (authorized role types are lecturer and student)  
						 * Setting visibility of loading label false 
						 * Setting visibility of error label true
						 */
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Unauthorized login attempt for "+usernameTextField.getText()+" from "+UniScoreClient.authLocation, "000001"));
						errorLabel.setVisible(true);
						loadingLabel.setVisible(false);
					}
					
				} else {
					/*
					 * If provided credentials are incorrect 
					 * Setting visibility of loading label false 
					 * Setting visibility of error label true
					 */
					UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Unauthorized login attempt for "+usernameTextField.getText()+" from "+UniScoreClient.authLocation, "000001"));
					errorLabel.setVisible(true);
					loadingLabel.setVisible(false);
				}
				
			} else {
				/*
				 * If provided first character is invalid 
				 * Setting visibility of loading label false 
				 * Setting visibility of error label true
				 */
				UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Unauthorized login attempt for "+usernameTextField.getText()+" from "+UniScoreClient.authLocation, "000001"));
				errorLabel.setVisible(true);
				loadingLabel.setVisible(false);
			}
			
			
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying authenticate user.\nError refferance : "+ExceptionList.REMOTE);
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on LoginContentPanel.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying authenticate user.\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on LoginContentPanel.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying authenticate user.\nError refferance : "+ExceptionList.SQL);
			en.setVisible(true);
			System.out.println("SQLException execution thrown on LoginContentPanel.java file. Error : "+e.getCause());
		} catch (NoSuchAlgorithmException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying authenticate user.\nError refferance : "+ExceptionList.NO_SUCH_ALGORITHM);
			en.setVisible(true);
			System.out.println("NoSuchAlgorithmException execution thrown on LoginContentPanel.java file. Error : "+e.getCause());
		} catch (NoSuchProviderException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying authenticate user.\nError refferance : "+ExceptionList.NO_SUCH_PROVIDER);
			en.setVisible(true);
			System.out.println("NoSuchProviderException execution thrown on LoginContentPanel.java file. Error : "+e.getCause());
		}
	}
	
	
	@Override
	public JPanel getContent() {
		return contentPanel;
	}
}
