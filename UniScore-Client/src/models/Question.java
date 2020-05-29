/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Question implements Serializable {

	// Declaring properties of the model
	private int questionId, examId, answer;
	private String question, option1, option2, option3, option4;
	private Timestamp createdAt, updatedAt;

	/*
	 * Question class constructor, not required to define necessarly but declared if needed for further development
	 */
	public Question() {}
	
	/*
	 * @param examId which contains the id of the exam which the question belongs to
	 * @param question which contains title of the question(question itself)
	 * @param option1 which contains first answer for the question
	 * @param option2 which is the second answer for the question
	 * @param option3 which is the third answer for the question
	 * @param option4 which is the fourth answer for the question
	 * @param answer which is the correct option for the question
	 * @param createdAt which is the timestamp of the creation od the question
	 * @param updatedAt which is the timestamp of when the question was last updated
	 */
	public Question(int examId, String question, String option1, String option2, String option3, String option4, int answer, Timestamp createdAt, Timestamp updatedAt) {
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

	/*
	 * @returns questionId which contains an incremental figure hence unique to identify and view specific activity without hassle 
	 */
	public int getQuestionId() {
		return questionId;
	}

	/*
	 * @param questionId which is the identifier of the question
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/*
	 * @returns examId which contains the id of the exam which the question belongs to
	 */
	public int getExamId() {
		return examId;
	}

	/*
	 * @param examId which is the exam id of which the question belongs to
	 */
	public void setExamId(int examId) {
		this.examId = examId;
	}

	/*
	 * @returns question which contains title of the question(question itself)
	 */
	public String getQuestion() {
		return question;
	}

	/*
	 * @param question which is the question itself
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/*
	 * @returns option1 which contains first answer for the question
	 */
	public String getOption1() {
		return option1;
	}

	/*
	 * @param option1 which is the first answer for the question
	 */
	public void setOption1(String option1) {
		this.option1 = option1;
	}

	/*
	 * @returns option2 which contains second answer for the question
	 */
	public String getOption2() {
		return option2;
	}

	/*
	 * @param option2 which is the second answer for the question
	 */
	public void setOption2(String option2) {
		this.option2 = option2;
	}

	/*
	 * @returns option3 which contains third answer for the question
	 */
	public String getOption3() {
		return option3;
	}

	/*
	 * @param option3 which is the third answer for the question
	 */
	public void setOption3(String option3) {
		this.option3 = option3;
	}

	/*
	 * @returns option4 which contains fourth answer for the question
	 */
	public String getOption4() {
		return option4;
	}

	/*
	 * @param option4 which is the fourth answer for the question
	 */
	public void setOption4(String option4) {
		this.option4 = option4;
	}

	/*
	 * @returns answer which contains correct option for the question
	 */
	public int getAnswer() {
		return answer;
	}

	/*
	 * @param answer which is the correct option for the question
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/*
	 * @returns createdAt which contains the timestamp of the creation of the question
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/*
	 * @param answer which is the timestamp of the creation od the question
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * @returns createdAt which contains the timestamp of when the question was last updated
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/*
	 * @param answer which is the timestamp of when the question was last updated
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
