package connectivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import com.panels.content.ErrorNotifier;

import main.panels.LecturerPanel;
import main.panels.LoginPanel;
import main.panels.StudentPanel;
import models.User;

public class UniScoreClient {

	public static UniScoreInterface uniscoreInterface;
	public static LecturerPanel lecturerPanel;
	public static StudentPanel studentPanel;
	public static LoginPanel loginPanel;
	public static User authUser;
	public static String authLocation;

	public UniScoreClient() {
	}

	public static void main(String args[]) {

		try {
			/*
			 * Error occured while trying to start the client server. Error -> no security manager: RMI class loader disabled Solution -> Both client and server packages which uses RMI server should be named the same. 
			 * Source refferance : https://stackoverflow.com/questions/6322107/java-no-security-manager-rmi-class-loader-disabled. Answered by erickson, edited by Paulo Ebermann
			 */
			Registry registry = LocateRegistry.getRegistry(1417);
			System.out.println("Registry located");
			UniScoreClient.uniscoreInterface = (UniScoreInterface) registry.lookup("rmi://localhost/UniScoreServer");
			System.out.println("Server located");

			if (UniScoreClient.uniscoreInterface.getServer()) {
				System.out.println("Database connectied");
				
				UniScoreClient.authLocation = UniScoreClient.uniscoreInterface.getLocation();
				
//				UniScoreClient.loginPanel = new LoginPanel();
//				UniScoreClient.loginPanel.setVisible(true);

				/*
				 * For development purposes
				 */
				User user = new User();
				user.setUserId("000015");
				UniScoreClient.authUser = user;
				UniScoreClient.lecturerPanel = new LecturerPanel();
				UniScoreClient.lecturerPanel.setVisible(true);
				
			} else {
				ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : 501");
				en.setVisible(true);
			}
		} catch (RemoteException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : 400");
			en.setVisible(true);
			System.out.println("RemoteException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (ClassNotFoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : 600");
			en.setVisible(true);
			System.out.println("ClassNotFoundException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (SQLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : 500");
			en.setVisible(true);
			System.out.println("SQLException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (NotBoundException e) {
			ErrorNotifier en = new ErrorNotifier("Failed to establish connection to the server !\nPlease contact the administrator\nError refferance : 700");
			en.setVisible(true);
			System.out.println("NotBoundException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (MalformedURLException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve user location.\nError refferance : 800");
			en.setVisible(true);
			System.out.println("JRException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		} catch (IOException e) {
			ErrorNotifier en = new ErrorNotifier("Failed. Unexpected Error occured while trying to retrieve user location.\nError refferance : 800");
			en.setVisible(true);
			System.out.println("JRException execution thrown on UniScoreClient.java file. Error : "+e.getCause());
		}

	}
}
