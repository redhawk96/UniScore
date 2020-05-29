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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.utils.ErrorNotifier;
import com.utils.ExceptionList;
import com.utils.SuccessNotifier;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import models.Activity;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class RemoveQuestionNotifier extends JFrame {

	/*
	 * RemoveQuestionNotifier method : used to initialize JFrame, required properties and add UI elements to the JFrame
	 * @param module 	Module object contains the necessary module information about the selected exam
	 * @param exam 		Exam object contains the necessary exam information about the selected exam
	 * @param question 	Question object contains the question information from the selected exam
	 */
	public RemoveQuestionNotifier(Module module, Exam exam, Question question) {
		// Defining the JFrame properties
		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());
		setTitle("WARNING");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 177);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		// Creating a JPanel to containt all the sub elements, panel buttons, message and icon
		JPanel contentPane = new JPanel();
		contentPane.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(102, 11, 325, 75);
		contentPane.add(scrollPane);
		
		// Adding a JTextPane to display the confirmation message
		JTextPane errorText = new JTextPane();
		errorText.setText("Are you sure that you want to remove question "+question.getQuestionId()+" from "+exam.getExamName()+" questionnaire ?");
		errorText.setEditable(false);
		errorText.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		errorText.setSelectionColor(UI.APPLICATION_THEME_TERTIARY_COLOR);
		scrollPane.setViewportView(errorText);
		errorText.setFont(UI.APPLICATION_THEME_FONT_13_PLAIN);
		
		JLabel errorIconLabel = new JLabel("");
		errorIconLabel.setIcon(new ImageIcon(RemoveQuestionNotifier.class.getResource("/resources/warning_icon.png")));
		errorIconLabel.setBounds(21, 21, 50, 50);
		contentPane.add(errorIconLabel);
		
		// Adding a new JPanel to set the look and feel of the pop-up like native windows pop-up(UX)
		JPanel panel = new JPanel();
		panel.setBounds(0, 92, 437, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel okButtonPanel = new JPanel();
		okButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		okButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
		okButtonPanel.setBackground(new Color(240, 240, 240));
		okButtonPanel.setBounds(249, 11, 82, 32);
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
			 * okButtonPanel and okButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that panel can be clicked 
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
			 * okButtonPanel and okButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that mouse is not in click range
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
			 * SubmissionMailer will execute the following mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					// Method removeQuestion will remove the question with the defined question id form the database and return the execution status as true for succesfull and false if not 
					boolean executionStatus = (boolean) UniScoreClient.uniscoreInterface.removeQuestion(question);
	
					// If question was successfullt remove if block will execute and if not else block will execute
					if(executionStatus) {
						// Adding a record to the database of the question removal with the lecturer id under activities table
						UniScoreClient.uniscoreInterface.addLogActivity(new Activity("Question "+question.getQuestionId()+" was removed in exam "+exam.getExamId()+" from "+UniScoreClient.authLocation, UniScoreClient.authUser.getUserId()));
						
						// Calling SuccessNotifier pop-up alert to indicate lecturer that question has been removed successfully 
						SuccessNotifier sn = new SuccessNotifier("Question was successfully removed.\nRecord refferance : Question ID - "+question.getQuestionId(), new QuestionNavigationPanel(), new DisplayQuestionsContentPanel(module, exam));
						sn.setVisible(true);
						
						// Closing the JFrame
						dispose();
						
					} else {
						// Calling ErrorNotifier pop-up alert to indicate lecturer that there was an error occured while trying to remove question 
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : "+ExceptionList.SQL_FAILED_EXECUTION);
						en.setVisible(true);
						
						// Closing the JFrame
						dispose();
					}
			
				/*
				 * If there was exception thrown when executing the removal of the question,
				 * following catch statements will handle the paticular exception and show a error notification with a unique number to identify the error
				 */
				} catch (RemoteException e ) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : "+ExceptionList.REMOTE);
					en.setVisible(true);
					System.out.println("RemoteException execution thrown on RemoveQuestionNotifier.java file. Error : "+e.getCause());
				} catch(ClassNotFoundException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : "+ExceptionList.CLASS_NOT_FOUND);
					en.setVisible(true);
					System.out.println("ClassNotFoundException execution thrown on RemoveQuestionNotifier.java file. Error : "+e.getCause());
				} catch(SQLException e) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : "+ExceptionList.SQL);
					en.setVisible(true);
					System.out.println("SQLException execution thrown on RemoveQuestionNotifier.java file. Error : "+e.getCause());
				}
			}
		});
		
		JButton cancelButtonPanel = new JButton();
		cancelButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		cancelButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
		cancelButtonPanel.setLayout(null);
		cancelButtonPanel.setBackground(new Color(240, 240, 240));
		cancelButtonPanel.setBounds(341, 11, 82, 32);
		panel.add(cancelButtonPanel);
		
		JLabel cancelButtonLabel = new JLabel("Cancel");
		cancelButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButtonLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		cancelButtonLabel.setBounds(0, 0, 82, 32);
		cancelButtonPanel.add(cancelButtonLabel);
		
		cancelButtonPanel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseEntered to handle mouse click events
			 * cancelButtonPanel and cancelButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that panel can be clicked 
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelButtonPanel.setBorder(new LineBorder(new Color(240, 240, 240)));
				cancelButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				cancelButtonLabel.setForeground(new Color(240, 240, 240));
			}
			
			/*
			 * Method mouseExited to handle mouse click events
			 * cancelButtonPanel and cancelButtonLabel will change color accordingly on mouse exit to enhance UX, user will know that mouse is not in click range
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				cancelButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
				cancelButtonPanel.setBackground(new Color(240, 240, 240));
				cancelButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
			
			/*
			 * Method mouseClicked to handle mouse click events
			 * JFrame will close on mouse click
			 * @param arg0 to get information about the mosue click 
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}
	
}
