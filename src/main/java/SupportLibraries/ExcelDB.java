package SupportLibraries;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;
//import com.sun.rowset.CachedRowSetImpl;
import java.lang.reflect.Method;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;


import SupportLibraries.Report.Status;

import java.lang.reflect.Method;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;


public class ExcelDB 
{
	/**
	 * Script Name   : ExcelDB
	 * Generated     : 3-Aug-2018
	 * Description   : Functional Test Script
	 * @author Nithyakala
	 */
	
	static Connection c1=null;
	public static String  dbpath =null;
	public static String sheet = null;
	public static int iterationno = 0;
	public static String testcase =null;
	public static int subiter = 0;
	public static String PSheet = null;
	
	
	public ExcelDB()
	{
		 
	}

    //#############################################################################
	//Function Name    	: setparameters
	//Description     	: Function to set the parameters
	//Input Parameters 	:  testcase, iterationno, sheet, subiter, parasheet
	//Return Value    	: None
	//#############################################################################

	public static void setParameters(String testcase,int iterationno,String sheet,int subiter,String parasheet)
	{
		ExcelDB.testcase = testcase;
		ExcelDB.iterationno = iterationno;
		ExcelDB.sheet = sheet;
		ExcelDB.subiter = subiter;
		PSheet = parasheet;
	}
	
	//#############################################################################
	//Function Name    	: initialize
	//Description     	: Function to initialize the excel values
	//Input Parameters 	:  testcase, iterationno, subiter,sheet, parasheet, dbpath
	//Return Value    	: strFrameworkRootFolder
	//#############################################################################
	public static void initialize(String testcase,int iterationno,int subiter,String sheet,String parasheet,String dbpath)
	{
		ExcelDB.dbpath = dbpath;
		setParameters(testcase, iterationno, sheet, subiter, parasheet);
	}
	
	//#############################################################################
	//Function Name    	: getConnected
	//Description     	: Function to connect to the database
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	private static void getConnected()
	{
		//closeConnection();
		try {
			//Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver");
			//c1= java.sql.DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+dbpath+";READONLY=FALSE");
			
			} 
		catch (Exception e) {
			System.out.println(dbpath);
			e.printStackTrace();
			
			try {
				c1.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
		

	//#############################################################################
	//Function Name    	:  closeConnection
	//Description     	: Function to close the connection
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	private static void closeConnection()
	{
		try {
			c1.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	//#############################################################################
	//Function Name    	: executequery
	//Description     	: Function to execute the query
	//Input Parameters 	: query, dbpath
	//Return Value    	: CachedRowSet
	//#############################################################################
	public static Recordset executeQuery(String query,String dbpath)
	{
		ExcelDB.dbpath=dbpath;
//		Statement st1 = null;
//		ResultSet rs =null;
//		CachedRowSetImpl css=null;
//		getConnected();
		
//		st1 = c1.createStatement();
//		if(Util.debug)
//		{
//			JOptionPane.showMessageDialog(null, "query = "+query);
//		}
//		rs= st1.executeQuery(query);
		//css= new CachedRowSetImpl();
		//css.populate(rs);

		Fillo fillo=new Fillo();
		Recordset recordset = null;
		
		try {

			c1= fillo.getConnection(dbpath);
			recordset=c1.executeQuery(query);			
			
			
									
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
		
		return recordset;
	}
	//#############################################################################
	//Function Name    	: getData
	//Description     	: Function to get the data from the testdata sheet
	//Input Parameters 	: colName
	//Return Value    	: String
	//#############################################################################
	public static String getData(String colName) 
	{
		String query = "SELECT "+colName+" FROM "+sheet+ " where TC_ID ='"+testcase+"' and Iteration ='"+iterationno+"'";
		return getDat(colName, query);
	}
	//#############################################################################
	//Function Name    	: getParametrizedData
	//Description     	: Function to get the parametrised  checkpoints from the parametrisedcheckpoint sheet
	//Input Parameters 	: colName
	//Return Value    	: Strdata
	//#############################################################################
	public static String getParametrizedData(String colName) 
	{
		String query = "SELECT "+colName+" FROM ["+PSheet+ "$] where TC_ID ='"+testcase+"' and Iteration ='"+iterationno+"'";
		return getDat(colName, query);
	}
	
	              
	//#############################################################################
	//Function Name    	: remtime
	//Description     	: Function to get the remaining time
	//Input Parameters 	: data
	//Return Value    	: StrTime
	//#############################################################################
	private static String remTime(String data)
	{
		
			String[] time = data.split(":");
			if (time.length >2)
			{
				int index = data.indexOf(":");
				index = index-2;
				data = data.substring(0,index);
			}
		
		return data;
	}
	
	//#############################################################################
	//Function Name    	: getDat
	//Description     	: Function to help the getData function retrieve the data from the testdata sheet
	//Input Parameters 	: colName, query
	//Return Value    	: StrData
	//#############################################################################
	private static String getDat(String colName,String query)
	{
		String refchar = Util.getValue("DataReferenceIdentifier","#");
		String data = null;
		//ResultSet rs =null;
		//Statement st1 = null;
		String dbpathbackup = dbpath;
		
		Fillo fillo=new Fillo();
		Recordset recordset = null;
		try {			
			//getConnected();
			//st1 = c1.createStatement();
			//rs= st1.executeQuery(query);			
			c1= fillo.getConnection(dbpath);
			recordset=c1.executeQuery(query);			
								
			
			recordset.next();
			int i =0;
			while (i!=subiter-1)
			{
				recordset.next();
				i++;
			}
			data = recordset.getField(colName);
			if (data.startsWith(refchar))
			{
				String[] ref = data.split(refchar);
				dbpath = dbpath.substring(0,dbpath.lastIndexOf("\\"))+ "\\CommonTestdata.xlsx";
				String query1 = "Select  *  from CommonTestdata where TD_ID = '"+ref[1]+"'";
				getConnected();
				c1= fillo.getConnection(dbpath);
				recordset=c1.executeQuery(query1);	
				recordset.next();
				data = recordset.getField(colName);
				dbpath = dbpathbackup;
			}
			
		} catch (Exception e) {
			Report.LogInfo("Fetching Data from Datatable", "Testcase : "+testcase +" ColumnName : "+colName+" SubiterationNo : "+subiter +"    Exception : "+e.toString(),Status.Failed_to_fetch_data);

			//System.out.println(e.toString());
			System.out.println(colName +"-"+sheet+"-"+testcase+"-"+ iterationno);
			e.printStackTrace();
			
		}
		finally
		{
			try {
				recordset.close();
				closeConnection();
				
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
		}
		return data;
	}
	
}

