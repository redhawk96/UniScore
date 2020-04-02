/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package connectivity;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import main.panels.AdminPanel;
import main.panels.LoginPanel;
import models.User;

public class UniScoreServer {

	public static UniScoreInterface uniscoreInterface;
	public static User authUser;
	public static LoginPanel loginPanel;
	public static AdminPanel adminPanel;
	
	public static void main(String[] args) {

		try {
			
			// Opening a new port in rmiregistery
			Registry registry = LocateRegistry.createRegistry(1417);
			LocateRegistry.getRegistry();
			
			// The stub is an object, acts as a gateway for the client side. All the outgoing requests are routed through it
			UniScore stub = new UniScore();
			System.out.println("Connecting to database");
			
			// Setting up connection to database
			DBConnection.getDBConnection();
			System.out.println("Connected to the database");
			System.out.println("Server is starting");
			
			// Creating a proxy for data communication
			registry.bind("rmi://localhost/UniScoreServer", stub);
			System.out.println("Server is ready");
			
			if(stub.getServer()) {
				UniScoreServer.uniscoreInterface = (UniScoreInterface) registry.lookup("rmi://localhost/UniScoreServer");
				/*
				 * Opening login panel
				 */
				loginPanel = new LoginPanel();
				loginPanel.setVisible(true);
			}
			
			/* 
			 * Run following on cmd to find the pid of the process occuping the port
			 * netstat -aon | findstr 1417
			 * 
			 * Run following on cmd to kill process
			 * taskkill /pid 1417 /f
			*/ 

		} catch (Exception e) {
			System.out.println("Failed execution on UniScoreServer. Error : " + e.toString());
		}

	}

}
