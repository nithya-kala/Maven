package DriverScript;
import com.codoid.products.fillo.Recordset;

//import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.lang.reflect.*;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import SupportLibraries.SeleniumHelper;
import SupportLibraries.Util;
import SupportLibraries.Report;
import SupportLibraries.ExcelDB;
import SupportLibraries.HTMLResults;
import SupportLibraries.Reflection;

import javax.swing.JOptionPane.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Allocator {

	/**
	 * Script Name   : Allocator
	 * Generated     : 3-Aug-2018
	 * Description   : Functional Test Script
	 * @author Nithyakala
	 */
	
	@Test
	public void testDriver(){
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbRunManagerpath =null;
		String dbDatatablepath=null;
		String path = null;
		try
		{
			path = new File(".").getCanonicalPath();
		}
		catch(IOException ex)
		{
			
		}

		Util.homePath = path;
		Util.debug = Boolean.parseBoolean(Util.getValue("Debug", "false"));
		//String path = System.getProperty("user.dir");
		if (Util.debug)
		{
			JOptionPane.showMessageDialog(null, "path = "+path, "Message", JOptionPane.OK_OPTION);
		}

	   String timestamp = "Run_"+Util.getCurrentDatenTime("MM-dd-yyyy")+"_"+Util.getCurrentDatenTime("hh-mm-ss_a");
	    
		String resultPath = path+"\\Results\\"+timestamp;		
		String HtmlResPath = resultPath+"\\HTML Results";
		String ScreenshotsPath = resultPath+"\\Screenshots";
		
		try 
		{      
			if (Util.debug)
			{
				JOptionPane.showMessageDialog(null, "Inside try -Allocator.java", "Message", JOptionPane.OK_OPTION);
			}
			new File(HtmlResPath).mkdirs();
			new File(ScreenshotsPath).mkdirs();
			
			Report.createSummaryHeader(HtmlResPath+"\\Summary.html");
			
			dbRunManagerpath = path+"\\DataTables\\Run Manager.xlsx";
			String query="SELECT * FROM Main";
			Recordset rs = ExcelDB.executeQuery(query,dbRunManagerpath);
			
			 while (rs.next())
			 {
				 
				 
				
		         String testValue= rs.getField("Execute");
				 
				if (rs.getField("Execute").equals("true"))
				{
		 			String Scenario = rs.getField("Test Scenarios");
		 			query="SELECT * FROM "+Scenario+"";
					dbDatatablepath = path+"\\Datatables\\"+Scenario+".xlsx";
					Recordset rs1 =  ExcelDB.executeQuery(query,dbRunManagerpath);
					
					 while (rs1.next())
					 {
						 if (rs1.getField("Execute").equals("true"))
						 {
							  String testcase = rs1.getField("Test Cases");
							  String desc = rs1.getField("Description");
							  String functionality = rs1.getField("Functionality");
							  String iterationMode = rs1.getField("Iteration Mode");
							  String st1,end1;
							  int st=1, end=1;
							  
							  if (Util.debug)
							  {
								  System.out.println(iterationMode);
							  }
							  
							  if (iterationMode.equals("Run all iterations"))
							  {
								  query = "SELECT iteration FROM TestData where TC_ID ='"+testcase+"'";
								    //query = "SELECT * FROM Test Data";
								  Recordset rs2 = ExcelDB.executeQuery(query,dbDatatablepath);
								  st=1;
								  end = rs2.getCount();
							  }
							  else if (iterationMode.equals("Run one iteration only"))
							  {
								  st=1;
								  end=1;
							  }
							  else
							  {
								  st1 = rs1.getField("Start Iteration");
								  try
								  {
									  st = (int)Float.parseFloat(st1);
								  }
								  catch(Exception ex)
								  {
								  }
								  end1 = rs1.getField("End Iteration");
								  try
								  {
									  end =(int)Float.parseFloat(end1);
								  }
								  catch(Exception ex)
								  {
								  }
							  }
							  
							  DriverScript.setParam(testcase,desc,functionality);
							  Report.createTestcaseHeader(HtmlResPath+"\\"+testcase+".html", ScreenshotsPath);
							  
							  DriverScript ds = new DriverScript();
							  ds.executeTestCase(dbDatatablepath,st,end);
							  
							  Report.closeTestcaseReportandUpdateSummary();
						 }
					  }
				  }
			   }
			   Report.closeSummary();
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
			ex.printStackTrace();
		}
		finally
		{
			if (Util.debug)
			{
				JOptionPane.showMessageDialog(null, path, "", JOptionPane.OK_OPTION);
			}
		
		} 
		//selenium.close();
		try {
			Process p =	Runtime.getRuntime().exec("RunDLL32.EXE shell32.dll,ShellExec_RunDLL "
				+ HtmlResPath + "/Summary.Html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

