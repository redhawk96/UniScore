package models;

public class Grade {

	private String grade;
	private int passMark;
	
	public Grade() {}

	/**
	 * @param grade
	 * @param passMark
	 */
	public Grade(String grade, int passMark) {
		this.grade = grade;
		this.passMark = passMark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getPassMark() {
		return passMark;
	}

	public void setPassMark(int passMark) {
		this.passMark = passMark;
	}
	
	
}
