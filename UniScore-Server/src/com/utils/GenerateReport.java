package com.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import connectivity.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class GenerateReport {
	
	private static Date currentDate = new Date();
	
	public GenerateReport(int reportIndex, int fileIndex, int queryIndex, String optionalParameter) throws ClassNotFoundException, SQLException, JRException {
		
		if (DBConnection.getDBConnection() != null) {

            Connection con = DBConnection.getDBConnection();

            String reportPath = "C:\\Users\\RED-HAWK\\Documents\\Ecplise-workspace\\UniScore\\UniScore-Server\\src\\com\\reports\\templates\\"+ getReportNameByIndex(reportIndex);

            Map<String, Object> parameters = new HashMap<String, Object>();

            //loading the design from the file path
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);

            //Designing the query for the report
            JRDesignQuery newQuery = new JRDesignQuery();

            //setting the query text
            newQuery.setText(getQueryByIndex(queryIndex, optionalParameter));

            //seeting the query for the report design
            jasperDesign.setQuery(newQuery);

            //compile the jasper report for the design
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            //creating a JasperPrint object to fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);

            //JasperViewer.viewReport(jasperPrint);
            String folderPath = "C:\\Users\\RED-HAWK\\Desktop\\UniScore\\Reports\\";
            
            JasperExportManager.exportReportToPdfFile(jasperPrint, folderPath + getFileNameByIndex(fileIndex, optionalParameter) + "-" + getDate() + "-" + getMonth() + "-" + getYear() + ".pdf");            
        }
	}
	
	
	public String getQueryByIndex(int index, String optionalParameter) {
		switch(index) {
			case 1 : return "SELECT `studentId` AS 'SID', `overallScore` AS 'Score', `grade` AS 'Grade' FROM submissions WHERE `examId` = "+optionalParameter;
		}
		
		return null;
	}
	
	public String getReportNameByIndex(int index) {
		switch(index) {
			case 1 : return "exam_submission_report.jrxml";
		}
		
		return null;
	}
	
	public String getFileNameByIndex(int index, String optionalParameter) {
		switch(index) {
			case 1 : return "exam-submission-results-e"+optionalParameter;
		}
		
		return null;
	}
	

    public static String getDate() {
        DateFormat date_Format = new SimpleDateFormat("dd");
        String date = date_Format.format(currentDate);
        return date;
    }

    public static String getMonth() {
        DateFormat month_Format = new SimpleDateFormat("MM");
        String month = month_Format.format(currentDate);
        return month;
    }

    public static String getYear() {
        DateFormat year_Format = new SimpleDateFormat("yyyy");
        String year = year_Format.format(currentDate);
        return year;
    }
}
