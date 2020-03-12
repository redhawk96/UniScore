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

	@Override
	public User get(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `users` WHERE WHERE `users`.`userId`=?";
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
				u.setStatus(rs.getString(12));
			}
			return u;
		}
		return null;
	}

	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `activitylogs`";
			PreparedStatement ps = con.prepareStatement(sql);
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
				u.setStatus(rs.getString(12));

				userList.add(u);
			}
			return userList;
		}
		return null;
	}
}
