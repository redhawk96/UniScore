/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Uditha Silva (UOB-1938086)
 */


package connectivity;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static java.sql.Connection conn;
	
	/*
	 * Singleton design pattern is implemented to avoid creation of multiple instances of DBConnetion
	 */
	public DBConnection() {
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

		/*
		 * Remote mysql database credentials
		 */
//		String url = "jdbc:mysql://remotemysql.com/";
//		String dbname = "mSu5HWWlOJ";
//		String ssl = "?useSSL=false";
//		String username = "mSu5HWWlOJ";
//		String password = "63RL0V8Dh9";
		
		/*
		 * local mysql database credentials
		 */
		String url = "jdbc:mysql://localhost/";
		String dbname = "uniscoredb";
		String ssl = "?useSSL=false";
		String username = "root";
		String password = "";

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url + dbname + ssl, username, password);
	}

}
