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

import com.panels.content.ErrorNotifier;
import com.panels.content.SuccessNotifier;
import com.utils.UI;

import connectivity.UniScoreClient;
import lecturer.panels.navigation.QuestionNavigationPanel;
import models.Exam;
import models.Module;
import models.Question;

@SuppressWarnings("serial")
public class RemoveQuestionNotifier extends JFrame {

	private JPanel contentPane;

	public RemoveQuestionNotifier(Module module, Exam exam, Question question) {
		setTitle("WARNING");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		scrollPane.setBounds(102, 11, 325, 75);
		contentPane.add(scrollPane);
		
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
				try {
					boolean executionStatus = (boolean) UniScoreClient.uniscoreInterface.removeQuestion(question);
	
					if(executionStatus) {
						SuccessNotifier sn = new SuccessNotifier("Question was successfully removed.\nRecord refferance : Question ID - "+question.getQuestionId(), new QuestionNavigationPanel(), new DisplayQuestionsContentPanel(module, exam));
						sn.setVisible(true);
						dispose();
					} else {
						ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 501");
						en.setVisible(true);
						dispose();
					}
					
				} catch (RemoteException re ) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 400");
					en.setVisible(true);
				} catch(ClassNotFoundException ce) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 600");
					en.setVisible(true);
				} catch(SQLException se) {
					ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to remove question.\nRecord refferance : Question ID - "+question.getQuestionId()+"\nError refferance : 500");
					en.setVisible(true);
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
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelButtonPanel.setBorder(new LineBorder(new Color(240, 240, 240)));
				cancelButtonPanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				cancelButtonLabel.setForeground(new Color(240, 240, 240));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cancelButtonPanel.setBorder(new LineBorder(UI.APPLICATION_THEME_SECONDARY_COLOR));
				cancelButtonPanel.setBackground(new Color(240, 240, 240));
				cancelButtonLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}
	
}
