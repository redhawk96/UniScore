package connectivity;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import main.panels.LecturerPanel;
import models.Question;

public class UniScoreClient {

	UniScoreInterface uniscoreInterface;

	public UniScoreClient() {
		try {
			
			/*
			 * Error occured while trying to start the client server. Error -> no security manager: RMI class loader disabled
			 * Solution -> Both client and server packages which uses RMI server should be named the same.
			 * Source refferance : https://stackoverflow.com/questions/6322107/java-no-security-manager-rmi-class-loader-disabled. Answered by erickson, edited by Paulo Ebermann
			 */
			System.out.println("Searching server");
			Registry registry = LocateRegistry.getRegistry(1417); 
			System.out.println("Registry located");
			this.uniscoreInterface = (UniScoreInterface) registry.lookup("rmi://localhost/UniScoreServer");
			System.out.println("Server located");
			
			if(this.uniscoreInterface.getServer()) {
			
				/*
				 * Test retrieving from the database
				 */
				List<Question> list = (List<Question>)this.uniscoreInterface.getQuestions(); 
				for(Question q : list) {
					System.out.println("ID: " + q.getQuestionId()); 
				}
				
				LecturerPanel tp = new LecturerPanel();
				tp.setVisible(true);
			}
			
			
		} catch (Exception e) {
			System.out.println("Failed execution on UniScoreClient. Error : " + e.toString());
		}
	}

	public static void main(String args[]) {
		new UniScoreClient();
	}
}
