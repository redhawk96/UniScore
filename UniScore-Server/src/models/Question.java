package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Question implements Serializable {

	private int questionId, examId;
	private String question, option1, option2, option3, option4, answer;
	private Timestamp createdAt, updatedAt;

	public Question() {}
	
	/**
	 * @param examId
	 * @param question
	 * @param option1
	 * @param option2
	 * @param option3
	 * @param option4
	 * @param answer
	 * @param createdAt
	 * @param updatedAt
	 */
	public Question(int examId, String question, String option1, String option2, String option3, String option4, String answer, Timestamp createdAt, Timestamp updatedAt) {
		this.examId = examId;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @param questionId
	 * @param examId
	 * @param question
	 * @param option1
	 * @param option2
	 * @param option3
	 * @param option4
	 * @param answer
	 * @param createdAt
	 * @param updatedAt
	 */
	public Question(int questionId, int examId, String question, String option1, String option2, String option3, String option4, String answer, Timestamp createdAt, Timestamp updatedAt) {
		this.questionId = questionId;
		this.examId = examId;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
