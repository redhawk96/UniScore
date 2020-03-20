package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Module implements Serializable{
	
	private String moduleId, moduleName, teacherId;
	private int year, semester;
	private Timestamp createdAt, updatedAt;
	
	public Module() {}
	
	/**
	 * @param moduleName
	 * @param year
	 * @param semester
	 * @param teacherId
	 * @param createdAt
	 * @param updatedAt
	 */
	public Module(String moduleName, int year, int semester, String teacherId, Timestamp createdAt, Timestamp updatedAt) {
		this.moduleName = moduleName;
		this.year = year;
		this.semester = semester;
		this.teacherId = teacherId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @param moduleId
	 * @param moduleName
	 * @param year
	 * @param semester
	 * @param teacherId
	 * @param createdAt
	 * @param updatedAt
	 */
	public Module(String moduleId, String moduleName,  int year, int semester, String teacherId, Timestamp createdAt, Timestamp updatedAt) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.year = year;
		this.semester = semester;
		this.teacherId = teacherId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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
