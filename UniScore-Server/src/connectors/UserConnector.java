/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.User;

public class UserConnector implements ConnectorInterface<User> {

	/*
	 * add : This will add a user into the databse and will not have any refference tables
	 * @params {User}
	 * @return {boolen} returns true if the user was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean add(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `users`(`userId`, `firstName`, `lastName`, `gender`, `email`, `nic`, `phone`, `address`, `avatar`, `role`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getNic());
			ps.setInt(7, user.getPhone());
			ps.setString(8, user.getAddress());
			ps.setString(9, user.getAvatar());
			ps.setString(10, user.getRole());
			ps.setString(11, user.getPassword());

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * update : This will update a paticular user reffered by the user id
	 * @params {User} 
	 * @return {boolen} returns true if the user was updated to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean update(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = null;
			PreparedStatement ps = null;

			if (user.getPassword() != null) {
				sql = "UPDATE `users` SET  `firstName`=?, `lastName`=?, `gender`=?, `email`=?, `nic`=?, `phone`=?, `address`=?, `role`=?, `password`=? WHERE `users`.`userId`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getGender());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getNic());
				ps.setInt(6, user.getPhone());
				ps.setString(7, user.getAddress());
				ps.setString(8, user.getRole());
				ps.setString(9, user.getPassword());
				ps.setString(10, user.getUserId());
			} else {
				sql = "UPDATE `users` SET  `firstName`=?, `lastName`=?, `gender`=?, `email`=?, `nic`=?, `phone`=?, `address`=?, `role`=? WHERE `users`.`userId`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getGender());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getNic());
				ps.setInt(6, user.getPhone());
				ps.setString(7, user.getAddress());
				ps.setString(8, user.getRole());
				ps.setString(9, user.getUserId());
			}

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * remove : This will a paticular user from the databse along with all the related submissions
	 * @params {User}
	 * @return {boolen} returns true if the user was removed from the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean remove(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `users` WHERE `users`.`userId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());

			int execution = ps.executeUpdate();

			if (execution != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * get : retrieves a paticular user by its id
	 * @params {User} obtains user id from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public User get(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `users` WHERE `users`.`userId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ResultSet rs = ps.executeQuery();

			User u = new User();

			while (rs.next()) {

				u.setUserId(rs.getString(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setNic(rs.getString(6));
				u.setPhone(rs.getInt(7));
				u.setAddress(rs.getString(8));
				u.setAvatar(rs.getString(9));
				u.setRole(rs.getString(10));
				u.setRegisteredDate(rs.getTimestamp(11));
				u.setStatus(rs.getString(13));
			}
			return u;
		}
		return null;
	}

	
	/*
	 * getbyCredentials : retrieves a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {User} returns a user object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public User getbyCredentials(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `users` WHERE `users`.`userId`=? AND `users`.`password`=?  AND `users`.`status`='Active'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();

			User u = new User();

			while (rs.next()) {

				u.setUserId(rs.getString(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setNic(rs.getString(6));
				u.setPhone(rs.getInt(7));
				u.setAddress(rs.getString(8));
				u.setAvatar(rs.getString(9));
				u.setRole(rs.getString(10));
				u.setRegisteredDate(rs.getTimestamp(11));
				u.setStatus(rs.getString(13));
			}
			return u;
		}
		return null;
	}
	
	
	
	
	/*
	 * getAll : retrieves all available users
	 * @return {List<User>} returns a list of users if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `users`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<User> userList = new ArrayList<>();

			while (rs.next()) {
				User u = new User();
				System.out.println(rs.getString(1));
				u.setUserId(rs.getString(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setNic(rs.getString(6));
				u.setPhone(rs.getInt(7));
				u.setAddress(rs.getString(8));
				u.setAvatar(rs.getString(9));
				u.setRole(rs.getString(10));
				u.setRegisteredDate(rs.getTimestamp(11));
				u.setStatus(rs.getString(13));

				userList.add(u);
			}
			return userList;
		}
		return null;
	}
	
	
	/*
	 * getByType : retrieves all available users filtered by user type
	 * @return {List<User>} returns a list of filtered users by user type if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<User> getByType(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `users` WHERE `users`.`role`= ? AND `users`.`status`='Active'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getRole());
			ResultSet rs = ps.executeQuery();

			List<User> userList = new ArrayList<>();

			while (rs.next()) {
				User u = new User();

				u.setUserId(rs.getString(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setNic(rs.getString(6));
				u.setPhone(rs.getInt(7));
				u.setAddress(rs.getString(8));
				u.setAvatar(rs.getString(9));
				u.setRole(rs.getString(10));
				u.setRegisteredDate(rs.getTimestamp(11));
				u.setStatus(rs.getString(13));

				userList.add(u);
			}
			return userList;
		}
		return null;
	}
}
