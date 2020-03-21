/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Ishani Welagedara (UOB-1940672)
 */

package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Submission implements Serializable{
	
	private String moduleId, studentId, answerList, grade;
	private int examId;
	private double overallScore;
	private Timestamp submittedOn;
	
	public Submission() {}
	
	/**
	 * @param moduleId
	 * @param studentId
	 * @param examId
	 * @param answerList
	 * @param grade
	 * @param overallScore
	 * @param submittedOn
	 */
	public Submission(String moduleId, String studentId, int examId, String answerList, String grade, double overallScore, Timestamp submittedOn) {
		this.moduleId = moduleId;
		this.studentId = studentId;
		this.examId = examId;
		this.answerList = answerList;
		this.grade = grade;
		this.overallScore = overallScore;
		this.submittedOn = submittedOn;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getAnswerList() {
		return answerList;
	}

	public void setAnswerList(String answerList) {
		this.answerList = answerList;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(double overallScore) {
		this.overallScore = overallScore;
	}

	public Timestamp getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Timestamp submittedOn) {
		this.submittedOn = submittedOn;
	}

	
}
