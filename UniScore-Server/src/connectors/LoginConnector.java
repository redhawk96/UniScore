/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Uditha Silva (UOB-1938086)
 */

package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectivity.DBConnection;
import models.User;

public class LoginConnector {

	/*
	 * authenticateUser : retrieves a boolean to whether a paticular user by his/her username and password
	 * @params {User} obtains username and password from the user object
	 * @return {boolean} returns true if a user is found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public boolean authenticateUser(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT COUNT(*) FROM `users` WHERE `users`.`userId`=? AND `users`.`password`=?  AND `users`.`status`='Active'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();

			int rowCount = 0;

            while (rs.next()) {
                rowCount = rs.getInt(1);
            }
            
            return (rowCount == 0) ? false : true;
			
		}
		return false;
	}
}
