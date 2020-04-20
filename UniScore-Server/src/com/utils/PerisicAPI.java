package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import connectors.SubmissionConnector;
import models.Submission;

public class PerisicAPI {

	
	public static void main (String args[]) {
		SubmissionConnector sc = new SubmissionConnector();
		Submission s = new Submission();
		s.setExamId(1);
		try {
			String what = sc.getExamDatasetToString(s);
			System.out.println(what);
			
			String url = "https://image-charts.com/chart?chs=700x190&chd=t:60,40&cht=p3&chl=Hello%7CWorld&chan&chf=ps0-0,lg,45,ffeb3b,0.2,f44336,1|ps0-1,lg,45,8bc34a,0.2,009688,1";
			URL obj;

			obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", "CIS007-3 Example Code 18/19");

			String urlParameters = "";

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Users\\RED-HAWK\\Desktop\\UniScore\\p.png")));
			 
			 //write contents of StringBuffer to a file
			 bwr.write(response.toString());
			 
			 //flush the stream
			 bwr.flush();
			 
			 //close the stream
			 bwr.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
