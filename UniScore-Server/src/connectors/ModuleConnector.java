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
import models.Module;
import models.User;

public class ModuleConnector implements ConnectorInterface<Module> {

	/*
	 * add : This will add a module into the database reffered by the year and semester
	 * @params {Module}
	 * @return {boolen} returns true if the module was added to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean add(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "INSERT INTO `modules`(`moduleId`, `moduleName`, `year`, `semester`, `teacherId`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, module.getModuleId());
			ps.setString(2, module.getModuleName());
			ps.setInt(3, module.getYear());
			ps.setInt(4, module.getSemester());
			ps.setString(5, module.getTeacherId());

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
	 * update : This will update a paticular module reffered by the module id
	 * @params {Module}
	 * @return {boolen} returns true if the module was updated to the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean update(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "UPDATE `modules` SET `moduleName`=?, `year`=?, `semester`=?, `teacherId`=? WHERE `modules`.`moduleId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, module.getModuleName());
			ps.setInt(2, module.getYear());
			ps.setInt(3, module.getSemester());
			ps.setString(4, module.getTeacherId());
			ps.setString(5, module.getModuleId());

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
	 * remove : This will remove a paticular module from the database along with the associated exams and submissions related to it
	 * @params {Module}
	 * @return {boolen} returns true if the module was removed from the database and false if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public boolean remove(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `modules` WHERE `modules`.`moduleId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, module.getModuleId());

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
	 * get : retrieves a paticular module by its id
	 * @params {Module} obtains module id from the module object
	 * @return {Module} returns a module object if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public Module get(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `modules` WHERE `modules`.`moduleId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, module.getModuleId());
			ResultSet rs = ps.executeQuery();

			Module m = new Module();

			while (rs.next()) {

				m.setModuleId(rs.getString(1));
				m.setModuleName(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setSemester(rs.getInt(4));
				m.setTeacherId(rs.getString(5));
				m.setCreatedAt(rs.getTimestamp(6));
				m.setUpdatedAt(rs.getTimestamp(7));
			}
			return m;
		}
		return null;
	}

	/*
	 * getAll : retrieves all available modules
	 * @return {List<Module>} returns a list of modules if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	@Override
	public List<Module> getAll() throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `modules`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Module> moduleList = new ArrayList<>();

			while (rs.next()) {
				Module m = new Module();

				m.setModuleId(rs.getString(1));
				m.setModuleName(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setSemester(rs.getInt(4));
				m.setTeacherId(rs.getString(5));
				m.setCreatedAt(rs.getTimestamp(6));
				m.setUpdatedAt(rs.getTimestamp(7));
				
				moduleList.add(m);
			}
			return moduleList;
		}
		return null;
	}
	
	/*
	 * getByYearAndUser : retrieves all available modules reffered by year, semester and user or year and semester
	 * @return {List<Module>} returns a list of modules for a paticular year, semester and  user or year and semester if found and null if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public List<Module> getByYearAndUser(Module module, int year, int semester)throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			
			PreparedStatement ps = null;
			
			if (module.getTeacherId() != null && year != 0 && semester != 0) {
				String sql = "SELECT * FROM `modules` WHERE `modules`.`teacherId`=? AND `modules`.`year`=? AND `modules`.`semester`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, module.getTeacherId());
				ps.setInt(2, year);
				ps.setInt(3, semester);
				
			} else if (module.getTeacherId() != null && year == 0 && semester == 0) {
				String sql = "SELECT * FROM `modules` WHERE `modules`.`teacherId`=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, module.getTeacherId());
				
			} else if (year != 0 && semester != 0) {
				String sql = "SELECT * FROM `modules` WHERE `modules`.`year`=? AND `modules`.`semester`=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, year);
				ps.setInt(2, semester);
			}
			
			ResultSet rs = ps.executeQuery();

			List<Module> moduleList = new ArrayList<>();

			while (rs.next()) {
				Module m = new Module();

				m.setModuleId(rs.getString(1));
				m.setModuleName(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setSemester(rs.getInt(4));
				m.setTeacherId(rs.getString(5));
				m.setCreatedAt(rs.getTimestamp(6));
				m.setUpdatedAt(rs.getTimestamp(7));
				
				moduleList.add(m);
			}
			return moduleList;
		}
		return null;
	}
	
	/*
	 * getCountByUser : retrieves count of all available modules filtered by an user
	 * @params {User} obtains user id from user object
	 * @return {int} returns returns an integer representing the number of modules if found and -1 if not
	 * @throws ClassNotFoundException, SQLException
	 */
	public int getCountByUser(User user) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			int mCount = 0;

			Module m = new Module();
			m.setTeacherId(user.getUserId());

			List<Module> moduleList = getByYearAndUser(m, 0, 0);

			for (@SuppressWarnings("unused")
			Module module : moduleList) {
				mCount = mCount + 1;
			}
			return mCount;
		}
		return -1;
	}

}
