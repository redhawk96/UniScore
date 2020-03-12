
package connectivity;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static java.sql.Connection conn;
	
	/*
	 * Singleton design pattern is implemented to avoid creation of multiple instances of DBConnetion
	 */
	private DBConnection() {
	}

	/*
	 * initiates a new connection to database or returns the existing connection
	 * @param {}
	 * @returns java.sql.Connection
	 */

	public static java.sql.Connection getDBConnection() throws ClassNotFoundException, SQLException {

		if (conn == null) {
			DBConnection.setConnection();
		}
		return conn;
	}

	/*
	 * initiates a new connection to database
	 * @param {}
	 * @returns void
	 */
	private static void setConnection() throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost/";
		String dbname = "uniscoredb";
		String username = "root";
		String password = "";

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url + dbname, username, password);
	}

}
