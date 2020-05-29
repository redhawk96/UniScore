/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package lecturer.panels.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.SuccessNotifier;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.ExamNavigationPanel;
import models.Activity;
import models.Exam;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class SubmissionMailer extends JFrame {

	// Declaring element properties needed to add UI and required properties to create SubmissionMailer JFrame
	private JPanel contentPane;
	private JTextField recipientsText;
	private JTextField subjectText;

	/*
	 * SubmissionMailer method : used to initialize JFrame, required properties and add UI elements to the JFrame
	 * @param exam 	Exam object contains the necessary module/exam information about the selected exam
	 */
	public SubmissionMailer(Exam exam) {
		// Defining the JFrame properties
		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());
		setTitle("Mailer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 551, 260);		
		setLocationRelativeTo(null); 
		setResizable(false);
		
		// Creating a JPanel to containt all the sub elements, panel buttons, recipient address(es) and mail subject
		contentPane = new JPanel();
		contentPane.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel recipientsLabel = new JLabel("Recipients  :");
		recipientsLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		recipientsLabel.setFont(UI.APPLICATION_THEME_FONT_15_PLAIN);
		recipientsLabel.setBorder(null);
		recipientsLabel.setBounds(10, 34, 525, 19);
		contentPane.add(recipientsLabel);
		
		// JTextField created to collect the mail address from the lecturer
		recipientsText = new JTextField();
		recipientsText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		recipientsText.setFont(UI.APPLICATION_THEME_FONT_15_PLAIN);
		recipientsText.setColumns(10);
		recipientsText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		recipientsText.setBounds(103, 34, 432, 19);
		contentPane.add(recipientsText);
		
		JLabel recipientsHintLabel = new JLabel("Seperate recipients by a comma ( , )");
		recipientsHintLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		recipientsHintLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
		recipientsHintLabel.setBorder(null);
		recipientsHintLabel.setBounds(103, 59, 432, 19);
		contentPane.add(recipientsHintLabel);
		
		JLabel subjectLabel = new JLabel("Subject      :");
		subjectLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		subjectLabel.setFont(UI.APPLICATION_THEME_FONT_15_PLAIN);
		subjectLabel.setBorder(null);
		subjectLabel.setBounds(10, 125, 525, 19);
		contentPane.add(subjectLabel);
		
		subjectText = new JTextField();
		subjectText.setText(exam.getModuleId()+" - "+exam.getExamName()+" Submissions");
		subjectText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		subjectText.setFont(UI.APPLICATION_THEME_FONT_15_PLAIN);
		subjectText.setColumns(10);
		subjectText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) UI.APPLICATION_THEME_SECONDARY_COLOR));
		subjectText.setBounds(103, 125, 432, 19);
		contentPane.add(subjectText);
		
		// Adding a new JPanel to set the look and feel of the pop-up like native windows pop-up(UX)
		JPanel panel = new JPanel();
		panel.setBounds(0, 178, 547, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel sendButtonPanel = new JPanel();
		sendButtonPanel.setBounds(455, 11, 82, 32);
		sendButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		sendButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
		sendButtonPanel.setBackground(new Color(240, 240, 240));
		panel.add(sendButtonPanel);
		sendButtonPanel.setLayout(null);
		
		JLabel sendButtonLabel = new JLabel("SEND");
		sendButtonLabel.setBounds(0, 0, 82, 32);
		sendButtonPanel.add(sendButtonLabel);
		sendButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sendButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		sendButtonLabel.setFont(UI.APPLICATION_THEME_FONT_12_PLAIN);
		sendButtonPanel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseEntered to handle mouse click events
			 * sendButtonPanel and sendButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that panel can be clicked 
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				sendButtonPanel.setBorder(new LineBorder(new Color(240, 240, 240)));
				sendButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				sendButtonLabel.setForeground(new Color(240, 240, 240));
			}
			
			/*
			 * Method mouseExited to handle mouse click events
			 * sendButtonPanel and sendButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that mouse is not in click range
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				sendButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
				sendButtonPanel.setBackground(new Color(240, 240, 240));
				sendButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
			
			/*
			 * Method mouseClicked to handle mouse click events
			 * SubmissionMailer will execute the following mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					// If the character length of the mail address(es) < 1, error alert will pop-up indicating that recepient address(es) is a required field 
					if(recipientsText.getText().trim().length() < 1) {
						ErrorNotifier en = new ErrorNotifier("Recipient address is a required field");
						en.setVisible(true);
					
						// If the character length of the mail subject < 1, error alert will pop-up indicating that mail subject is a required field 
					} else if(subjectText.getText().trim().length() < 1) {
						ErrorNotifier en = new ErrorNotifier("Mail subject is a required field");
						en.setVisible(true);
					
					// If everything is validated an recpient and  subject both has a character length > 1, following will execute to send the mail(s) 
					} else {
						// Sending mail using the sendMail method 
						UniScoreClient.uniscoreInterface.sendMail(recipientsText.getText().trim(), subjectText.getText().trim(), UniScoreClient.uniscoreInterface.getSubmissionTableByExam(exam));
						
						// Adding a record to the database of the mails sent with the lecturer id under activities table
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Exam "+exam.getExamId()+" submission grading report has been sent to "+recipientsText.getText().trim()+" through email from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
						
						// Calling SuccessNotifier pop-up alert to indicate lecturer that mails has been sent successfully 
						SuccessNotifier sn = new SuccessNotifier("Mail(s) has been successfully sent.", new ExamNavigationPanel(), new ExamContentPanel());
						sn.setVisible(true);
						
						// Closing the JFrame
						dispose();
					}
			
				/*
				 * If there was exception thrown when trying to send mails,
				 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
				 */
				} catch (RemoteException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to send mail(s).\nError refferance : "+ExceptionList.REMOTE);
					en.setVisible(true);
					System.out.println("RemoteException execution thrown on SubmissionMailer.java file. Error : "+e.getCause());
					dispose();
				} catch (ClassNotFoundException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to send mail(s).\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on SubmissionMailer.java file. Error : "+e.getCause());
					dispose();
				} catch (SQLException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to send mail(s).\nError refferance : "+ExceptionList.SQL);
					en.setVisible(true);
					System.out.println("SQLException execution thrown on SubmissionMailer.java file. Error : "+e.getCause());
					dispose();
				} catch (AddressException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to send mail(s).\nError refferance : "+ExceptionList.ADDRESS);
					en.setVisible(true);
					System.out.println("AddressException execution thrown on SubmissionMailer.java file. Error : "+e.getCause());
					dispose();
				} catch (MessagingException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to send mail(s).\nError refferance : "+ExceptionList.MESSAGING);
					en.setVisible(true);
					System.out.println("MessagingException execution thrown on SubmissionMailer.java file. Error : "+e.getCause());
					dispose();
				}			
			}
		});
	}
}
