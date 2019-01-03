package SupportLibraries;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.text.html.HTML;
import DriverScript.DriverScript;
import SupportLibraries.ExtentHTMLReports;


 public class Report
{
	 /**
		 * Script Name   : Report
		 * Generated     : 3-Aug-2018
		 * Description   : Functional Test Script
		 * @author Nithyakala
		 */
	public static boolean error = false;
	public enum Status {
	    PASS,FAIL,Failed_to_fetch_data,DONE
	}
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}

	 //#############################################################################
	//Function Name    	: createSummaryHeader
	//Description     	: Function to create the summary header
	//Input Parameters 	: HtmlSummaryFile,ExcelSummaryFile
	//Return Value    	: None
	//#############################################################################
	public static void createSummaryHeader(String HtmlSummaryFile)
	{
		ExtentHTMLReports.startReport();
		 HTMLResults.createSummaryHeader(HtmlSummaryFile);
		 
		 
	}

	 //#############################################################################
	//Function Name    	: createTestcaseHeader
	//Description     	: Function to create testcase header
	//Input Parameters 	: HtmlTestcaseFile, ExcelTestCaseFile,ScreenshotPath
	//Return Value    	: None
	//#############################################################################
	public static void createTestcaseHeader(String HtmlTestcaseFile,String ScreenshotPath)
	{
		ExtentHTMLReports.startTest();
		HTMLResults.createTestCaseHeader(HtmlTestcaseFile,ScreenshotPath);	
		
	}

	 //#############################################################################
	//Function Name    	: closeTestcaseReportandUpdateSummary
	//Description     	: Function to  close the testcase report and update the summary
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	public static void closeTestcaseReportandUpdateSummary()
	{
		ExtentHTMLReports.endTest();
		HTMLResults.closeTestCase();		
		updateSummary();
		
	}

	 //#############################################################################
	//Function Name    	: closeTestcaseReport
	//Description     	: Function to close the testcase report
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	public static void closeTestcaseReport()
	{
		HTMLResults.closeTestCase();		
		
	}

	 //#############################################################################
	//Function Name    	: updateSummary
	//Description     	: Function to update the summary
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	
	private static void updateSummary()
	{
		HTMLResults.addRowtoSummary();		
	}

	 //#############################################################################
	//Function Name    	: closeSummary
	//Description     	: Function to close summary
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################
	public static void closeSummary()
	{
		ExtentHTMLReports.endReport();
		HTMLResults.closeSummary();	
		
	}

	 //#############################################################################
	//Function Name    	: LogInfo, strDescription, strStatus
	//Description     	: Function to LogInfo into the reports
	//Input Parameters 	:  strStepName
	//Return Value    	: None
	//#############################################################################
	
	public static void LogInfo(String strStepName,String strDescription,Status strStatus)
	{
		boolean st = true;
		try
		{
			ExtentHTMLReports.LogInfo(strStepName,strDescription,strStatus);
			HTMLResults.addRowtoTestCase(strStepName,strDescription,strStatus);
			if(strStatus.equals(Status.FAIL)||strStatus.equals(Status.Failed_to_fetch_data))
			{
				st= false;
			}
			//logTestResult(strStepName, st, strDescription);
		}
		catch (Exception e)
		{
			
		}
	}

	 //#############################################################################
	//Function Name    	: insertIteration
	//Description     	: Function to insert an Iteration
	//Input Parameters 	: iteration
	//Return Value    	: None
	//#############################################################################
	public static void insertIteration(int iteration)
	{
		HTMLResults.insertIteration(((Integer)iteration).toString());
	}

	 //#############################################################################
	//Function Name    	: LogError
	//Description     	: Function to Log the Error
	//Input Parameters 	: Errordesc
	//Return Value    	: None
	//#############################################################################
	
	public static void LogError(String Errordesc)
	{
		error = true;
		LogInfo("Error",Errordesc,Status.FAIL);
	}

	 //#############################################################################
	//Function Name    	: startRep
	//Description     	: Function to start the report .
	//Input Parameters 	: startRep
	//Return Value    	: None
	//#############################################################################
	
	public static void startRep(String testCaseName,String businesscomp,int iterationNo,int subiterationNo,String ScenarioName )
	{
		String path = Util.homePath;
		String testdatasheet =Util.getValue("TestDataSheet", "TestData"); 
		String paradatasheet =Util.getValue("CheckPointSheet", "ParameterizedCheckpoints"); 
		
		String dbpath = path+"\\Datatables\\"+ScenarioName+".xlsx";
		
		String as= testCaseName.replaceFirst("BusinessComponents.", "");
		ExcelDB.initialize(as,iterationNo,subiterationNo,testdatasheet,paradatasheet,dbpath);
		
		String timestamp = "Run_"+Util.getCurrentDatenTime("dd-MM-yy")+"_"+Util.getCurrentDatenTime("H-mm-ss a");
		String resultPath = path+"\\Results\\"+timestamp;		
		String HtmlResPath = resultPath+"\\HTML Results";
		String ScreenshotsPath = resultPath+"\\Screenshots";
		
		try           
		{       
			new File(HtmlResPath).mkdirs();
		}
		catch (Exception ex )
		{
			
		}
		createTestcaseHeader(HtmlResPath+"\\"+businesscomp+".html",ScreenshotsPath);
		insertIteration(iterationNo);
		LogInfo("Start Component", "Invoking BusinessComponent:"+businesscomp,Status.DONE);  
	}


	 //#############################################################################
	//Function Name    	: closeRep
	//Description     	: Function to close the rep
	//Input Parameters 	: businesscomp
	//Return Value    	: None
	//#############################################################################
	
	public static void closeRep(String businesscomp)
	{
		LogInfo("End Component", "Exiting BusinessComponent:"+businesscomp,Status.DONE);
		closeTestcaseReport();
		String path = Util.homePath;
		String src =path+"_logs\\"+businesscomp;
		//Util.copyDirectory(src, dest);
	}
}

