package lecturer.panels.content;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.panels.ContentPanel;
import com.utils.UI;

import connectivity.UniScoreClient;
import models.Question;
import models.User;

@SuppressWarnings("serial")
public class QuestionContentPanel extends ContentPanel {

	JPanel contentPanel = new JPanel();
	JTable table = new JTable();

	public QuestionContentPanel() {
		/*
		 * Adding contentPanel JPanel name is set to identify content panel when
		 * selected
		 */
		contentPanel.setName("question");
		contentPanel.setLayout(null);
		contentPanel.setBounds(UI.CONTENT_PANEL__X_AXIS, UI.CONTENT_PANEL__Y_AXIS, UI.CONTENT_PANEL__WIDTH, UI.CONTENT_PANEL__HEIGHT);
		contentPanel.setBackground(UI.CONTENT_PANEL_BACKGROUND_COLOR);

		try {
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setBackground(Color.WHITE);
			DefaultTableModel model = new DefaultTableModel(new String[] { "QID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer" }, 0);
			table.setBounds(32, 37, 810, 532);

			List<Question> questionList = (List<Question>) UniScoreClient.uniscoreInterface.getQuestions();

			for (Question question : questionList) {
				// Adding a new question record to the table each time the loop executes
				model.addRow(new Object[] { question.getQuestionId(),question.getQuestion(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getAnswer()});
			}

			table.setModel(model);

			contentPanel.add(table);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * returns the JPanel inside ContentPanel
	 * 
	 * @param {}
	 * 
	 * @returns JPanel
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
