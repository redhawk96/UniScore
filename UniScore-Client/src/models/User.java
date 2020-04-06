/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class User implements Serializable{

	private String userId, firstName, lastName, gender, email, nic, address, role, password, status;
	private int phone;
	private Timestamp registeredDate;
	
	public User() {}
	
	public User(String firstName, String lastName, String gender, String email, String nic, String address, String role, Timestamp registeredDate, String password, String status, int phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.nic = nic;
		this.address = address;
		this.role = role;
		this.registeredDate = registeredDate;
		this.password = password;
		this.status = status;
		this.phone = phone;
	}

	public User(String userId, String firstName, String lastName, String gender, String email, String nic, String address, String role, Timestamp registeredDate, String password, String status, int phone) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.nic = nic;
		this.address = address;
		this.role = role;
		this.registeredDate = registeredDate;
		this.password = password;
		this.status = status;
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Timestamp registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}
