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
public class Exam implements Serializable{

	// Declaring properties of the model
	private int examId, duration;
	private String examName, moduleId, enrollmentKey, status;
	private Timestamp createdAt, updatedAt;
	
	/*
	 * Exam class constructor, not required to define necessarly but declared if needed for further development
	 */
	public Exam() {}

	/*
	 * @param duration which is the duration in minutes of the exam
	 * @param examName which is the name of the exam
	 * @param moduleId which is the id of the module the exam belongs to
	 * @param enrollmentKey which is the key used to enroll for the exam
	 * @param status which is the status of the exam, which can be 'Not started', 'Started' or 'Finished'
	 * @param createdAt which is the timestamp of the creation of the exam
	 * @param updatedAt which is the timestamp when the exam was last updated
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

	/*
	 * @returns examId which contains an incremental figure hence unique to identify and view specific exam without hassle 
	 */
	public int getExamId() {
		return examId;
	}

	/*
	 * @param examId which is the identifier of the exam
	 */
	public void setExamId(int examId) {
		this.examId = examId;
	}

	/*
	 * @returns duration which contains the number which defines the number minutes for the reffering exam 
	 */
	public int getDuration() {
		return duration;
	}

	/*
	 * @param duration which is the duration in minutes of the exam
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*
	 * @returns examName which contains the name of the exam, same name can be repeated for different modules 
	 */
	public String getExamName() {
		return examName;
	}

	/*
	 * @param examName which is the name of the exam
	 */
	public void setExamName(String examName) {
		this.examName = examName;
	}

	/*
	 * @returns moduleId which contains an id of the module the exam belongs to 
	 */
	public String getModuleId() {
		return moduleId;
	}

	/*
	 * @param moduleId which is the id of the module the exam blongs to
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/*
	 * @returns enrollmentKey which contains a key which the student has to enter inorder to take the exam 
	 */
	public String getEnrollmentKey() {
		return enrollmentKey;
	}

	/*
	 * @param enrollmentKey which is the key used to enroll for the exam
	 */
	public void setEnrollmentKey(String enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
	}

	/*
	 * @returns status which contains the status of the exam whether it has already started, not started or finished
	 */
	public String getStatus() {
		return status;
	}

	/*
	 * @param status which is the status of the exam, which can be 'Not started', 'Started' or 'Finished'
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * @returns createdAt which contains the timestamp of which the paticular exam was created
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/*
	 * @param createdAt which is the timestamp of the creation of the exam
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * @returns getUpdatedAt which contains the timestamp of which the paticular exam was updated
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/*
	 * @param updatedAt which is the timestamp when the exam was last updated
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
