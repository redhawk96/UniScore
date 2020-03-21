/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Uditha Silva (UOB-1938086)
 */

package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Exam implements Serializable{

	private int examId, duration;
	private String examName, moduleId, enrollmentKey, status;
	private Timestamp createdAt, updatedAt;
	
	public Exam() {}

	/**
	 * @param duration
	 * @param examName
	 * @param moduleId
	 * @param enrollmentKey
	 * @param status
	 * @param createdAt
	 * @param updatedAt
	 */
	public Exam(int duration, String examName, String moduleId, String enrollmentKey, String status, Timestamp createdAt, Timestamp updatedAt) {
		this.duration = duration;
		this.examName = examName;
		this.moduleId = moduleId;
		this.enrollmentKey = enrollmentKey;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @param examId
	 * @param duration
	 * @param examName
	 * @param moduleId
	 * @param enrollmentKey
	 * @param status
	 * @param createdAt
	 * @param updatedAt
	 */
	public Exam(int examId, int duration, String examName, String moduleId, String enrollmentKey, String status, Timestamp createdAt, Timestamp updatedAt) {
		this.examId = examId;
		this.duration = duration;
		this.examName = examName;
		this.moduleId = moduleId;
		this.enrollmentKey = enrollmentKey;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(String enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
