package connectivity;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

	public UniScoreClient() {
	}

	public static void main(String args[]) {
		try {

			/*
			 * Error occured while trying to start the client server. Error -> no security
			 * manager: RMI class loader disabled Solution -> Both client and server packages which uses RMI server should be named the same. 
			 * Source refferance : https://stackoverflow.com/questions/6322107/java-no-security-manager-rmi-
			 * class-loader-disabled. Answered by erickson, edited by Paulo Ebermann
			 */
			Registry registry = LocateRegistry.getRegistry(1417);
			UniScoreClient.uniscoreInterface = (UniScoreInterface) registry.lookup("rmi://localhost/UniScoreServer");
			
			if (UniScoreClient.uniscoreInterface.getServer()) {
				UniScoreClient.loginPanel = new LoginPanel();
				UniScoreClient.loginPanel.setVisible(true);
				
				/*
				 * For development purposes
				 */
//				User user = new User();
//				user.setUserId("uditha@uniscore.com");
//				user.setUserId("ishani@uniscore.com");
//				UniScoreClient.authUser = user;
//				UniScoreClient.lecturerPanel = new LecturerPanel();
//				UniScoreClient.lecturerPanel.setVisible(true);
			}

		} catch (Exception e) {
			System.out.println("Failed execution on UniScoreClient. Error : " + e);
		}
	}
}
