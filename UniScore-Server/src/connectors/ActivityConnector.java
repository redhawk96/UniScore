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

	@Override
	public boolean update(Activity activity) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `activitylogs` SET `activityBrief`=?, `triggeredBy`? WHERE `activitylogs`.`activityId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activity.getActivityBrief());
			ps.setString(2, activity.getTriggeredBy());
			ps.setInt(3, activity.getActivityId());

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
	public boolean remove(Activity activity) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `activitylogs` WHERE `activitylogs`.`activityId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, activity.getActivityId());

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
}
