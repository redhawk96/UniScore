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
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Activity;

public class ActivityConnector implements ConnectorInterface<Activity> {

	/*
	 * add : This will add a new user triggerd activity into the databse
	 * @params {Activity} 
	 * @return {boolen} returns true if the activity was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean add(Activity activity) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `activitylogs`(`activityBrief`, `triggeredBy`) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activity.getActivityBrief());
			ps.setString(2, activity.getTriggeredBy());

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
	 * Ammendment of activity log will not be allowed to any user type
	 */
	@Override
	public boolean update(Activity activity) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * Removal of log activity will not be allowed to any user type
	 */
	@Override
	public boolean remove(Activity activity) throws ClassNotFoundException, SQLException {
		return false;
	}

	/*
	 * get : retrieves a paticular activity by its id
	 * @params {Activity} Obtains activity id from the activity object
	 * @return {Activity} returns an activity object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public Activity get(Activity activity) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `activitylogs` WHERE `activitylogs`.`activityId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, activity.getActivityId());
			ResultSet rs = ps.executeQuery();

			Activity a = new Activity();

			while (rs.next()) {

				a.setActivityId(rs.getInt(1));
				a.setActivityBrief(rs.getString(2));
				a.setTriggeredBy(rs.getString(3));
				a.setTriggeredOn(rs.getTimestamp(4));
			}
			return a;
		}
		return null;
	}

	/*
	 * getAll : retrieves all available activities
	 * @return {List<Activity>} returns a list activities of if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public List<Activity> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `activitylogs`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Activity> activityList = new ArrayList<>();

			while (rs.next()) {
				Activity a = new Activity();

				a.setActivityId(rs.getInt(1));
				a.setActivityBrief(rs.getString(2));
				a.setTriggeredBy(rs.getString(3));
				a.setTriggeredOn(rs.getTimestamp(4));

				activityList.add(a);
			}
			return activityList;
		}
		return null;
	}
	
	/*
	 * getbyUser : retrieves all available activities filtered by a paticular user
	 * @params {Activity} Obtains user id from the activity object
	 * @return {List<Activity>} returns a list of activities of a paticular user if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Activity> getbyUser(Activity activity) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `activitylogs` WHERE `activitylogs`.`triggeredBy`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activity.getTriggeredBy());
			ResultSet rs = ps.executeQuery();

			List<Activity> activityList = new ArrayList<>();

			while (rs.next()) {
				Activity a = new Activity();

				a.setActivityId(rs.getInt(1));
				a.setActivityBrief(rs.getString(2));
				a.setTriggeredBy(rs.getString(3));
				a.setTriggeredOn(rs.getTimestamp(4));

				activityList.add(a);
			}
			return activityList;
		}
		return null;
	}
}
