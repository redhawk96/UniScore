package connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectivity.DBConnection;
import models.Module;

public class ModuleConnector implements ConnectorInterface<Module> {

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

	@Override
	public boolean remove(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "DELETE FROM `modules` WHERE `modules`.`moduleId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
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

	@Override
	public Module get(Module module) throws ClassNotFoundException, SQLException {
		if (DBConnection.getDBConnection() != null) {
			Connection con = DBConnection.getDBConnection();
			String sql = "SELECT * FROM `modules` WHERE `modules`.`moduleId`=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(5, module.getModuleId());
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

}
