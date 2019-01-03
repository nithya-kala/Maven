package DriverScript;

import java.io.*;
import SupportLibraries.*;
import SupportLibraries.Report.Status;
import java.sql.*;
import java.util.*;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import com.codoid.products.fillo.Field;
import com.codoid.products.fillo.Recordset;
//import com.thoughtworks.selenium.DefaultSelenium;
import org.testng.annotations.Test;
/**
 * Description   : Functional Test Script
 * @author 163497, 163392
 */
public class DriverScript
{
	
	/**
	 * Script Name   : DriverScript
	 * Generated     : 3-Aug-2018
	 * Description   : Functional Test Script
	 * @author Nithyakala
	 */
	public static int iteration =0;
	public static int subiteration = 1;
	public static String testcase="";
	public static String functionality ="";
	public static String desc = null;
	public static boolean error = false;
	

	
	public void testMain(Object[] args) 
	{
		//To run a TestCase directly, give Scenario, test case, description, iteration start, and iteration end.
		String Scenario = "Scenario3";
		testcase = "SEL_TC1";
		desc ="desc";
		int start = 1;
		int end =1;
		
		String path = Util.homePath;
		String dbpath =path+"\\Datatables\\"+Scenario+".xlsx";
		String timestamp = "Run_"+Util.getCurrentDatenTime("dd-MM-yy")+"_"+Util.getCurrentDatenTime("H-mm-ss a");
		String resultPath = path+"\\Results\\"+timestamp;		
		String HtmlResPath = resultPath+"\\HTML Results";
		String ScreenshotsPath = resultPath+"\\Screenshots";
		
		try 
		{   
			new File(HtmlResPath).mkdirs();
		}
		catch (Exception ex){}
		
		Report.createTestcaseHeader(HtmlResPath+"\\"+testcase+".html", ScreenshotsPath);
		executeTestCase(dbpath,start,end);
		Report.closeTestcaseReport();
		 		
	}
	public static void setParam(String TestCase,String desc,String functionality)
	{
		DriverScript.testcase = TestCase;
		DriverScript.desc =desc;
		DriverScript.functionality = functionality;
	}
	
	public void executeTestCase(String dbpath,int startIteration,int endIteration)
	{
		error = false;
		ArrayList<String> bc = new ArrayList<String>();
		String onError = Util.getValue("OnError", "NextIteration");
		String bsf = Util.getValue("BusinessFlowSheet","BusinessFlow");
		String testdatasheet =Util.getValue("TestDataSheet", "TestData");
		String parameterizedcheckpt = Util.getValue("CheckPointSheet", "ParameterizedCheckpoints");
		String q1="SELECT * FROM "+bsf+" where TC_ID ='"+testcase+"'";
		Recordset rs2 =  ExcelDB.executeQuery(q1,dbpath);
		//SeleniumHelper.setup();
		try
		{
			rs2.next();
			//int colcount=rs2.getCount();
			int colcount = 20;
			System.out.println(colcount);
			String[] classnmethod = new String[colcount];
			int p,q;
			//for(p=2,q=0;p<colcount;p++,q++)
			//{
			//	classnmethod[q]= rs2.getField(p).toString();
			//	if(classnmethod[q]==null)
			//		break;
			//}
			
			for(p=0;p<colcount;p++)
			{
				classnmethod[p]= rs2.getField("Keyword_"+ (p+1));				
				if(classnmethod[p].length()==0)
					break;
				
			}
			colcount = p;
			bc.clear();
			for(p=0;p<colcount;p++)
			{
			bc.add(classnmethod[p]);
			}
			
			for (int j =startIteration;j<=endIteration;j++)
			{
				if (error||Report.error)
				{
					error = false;
					Report.error = false;
					break;
				}
				iteration = j;
				Report.insertIteration(j);
				for (int i=0;i<colcount;i++)
				{
					if (error||Report.error)
					{
						if (onError.equals("nextIteration"))
						{
							error = false;
							Report.error = false;
						}
						break;
					}
					//String[] clnm = classnmethod[i].split(",");
					//String[] clnm = classnmethod[];
					int iterno = 1;
					//if (clnm.length>1)
					//{
						//iterno = Integer.valueOf(clnm[1]);
					//}
					//bc.add(clnm[0]);
					if (iterno!=0)
					{
						for (int l=1;l<iterno+1;l++)
						{
							if (error||Report.error)
							{
								break;
							}
							for (int k = 0;k<bc.size();k++ )
							{
								if (error||Report.error)
								{
									break;
								}
								ExcelDB.setParameters(testcase, j, testdatasheet,l,parameterizedcheckpt);
								subiteration = l+1;
								Report.LogInfo("Start Component","Invoking Business component: "+bc.get(k),Status.DONE);
								try
								{
									//Reflection.execute("BusinessComponents."+bc.get(k),"ExecuteComponent");
									//Reflection.execute("BusinessComponents.AppLibrary",bc.get(k));
									//Reflection.execute("BusinessComponents."+ DriverScript.functionality,bc.get(k));
									String[] words=bc.get(k).split(",");
									Reflection.execute("BusinessComponents."+ words[0], words[1]);						
								
								}
								
								catch (Exception ex)
								{
									ex.printStackTrace();
									error = true;
									Report.LogInfo("Unhandled Exception occured while exectuing "+ bc.get(k),ex.toString(),Status.FAIL);
								}
								if (!error&&!Report.error)
								{
									Report.LogInfo("End Component","Exiting Business component: "+bc.get(k),Status.DONE);
								}
								else
								{
									reportError(onError);
								}
							}
						}	
						bc.clear();
					 }
				 }
			}
				
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
			SeleniumHelper.driver.quit();
		}
		
		if (SeleniumHelper.driver == null) {}
		else {SeleniumHelper.driver.quit();}
	}
	
	private void reportError(String onError)
	{
		if (onError.equals("nextIteration"))
		{
			Report.LogInfo("Error found", "Moving to next Iteration", Status.DONE);
		}
		else if (onError.equals("nextTestCase"))
		{
			Report.LogInfo("Error found", "Moving to next TestCase", Status.DONE);
		}
		else if (onError.equals("stop"))
		{
			Report.LogInfo("Error found", "Stopping the execution", Status.DONE);
			Report.closeTestcaseReportandUpdateSummary();
			Report.closeSummary();
			//selenium.stop();
		}
	}
}



