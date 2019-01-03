package SupportLibraries;
import java.io.File;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DriverScript.DriverScript;
import SupportLibraries.Report.Status;



public class ExtentHTMLReports{
	static ExtentReports extent;
	static ExtentTest logger;
	static int inc =1;
	static String ReportPath;
	
	
	public static void startReport(){
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		String TimeStamp = "Run_"+Util.getCurrentDatenTime("MM-dd-yyyy")+"_"+Util.getCurrentDatenTime("hh-mm-ss_a");
		ReportPath = System.getProperty("user.dir") +"/HTML Results/" + TimeStamp;
		extent = new ExtentReports (ReportPath + "/BPCExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
		extent
                .addSystemInfo("Host Name", "SoftwareTesting")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Nithyakala Rajendran");
               
	}
	
	public static void startTest(){
		// starting test
		inc =1;
		logger = extent.startTest(DriverScript.testcase);
		}
	
	public static void endTest(){
	// ending test
	//endTest(logger) : It ends the current test and prepares to create HTML report
	extent.endTest(logger);
	}
	
	
	public static void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
		
                extent.flush();
                //Call close() at the very end of your session to clear all resources. 
                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
                //Once this method is called, calling any Extent method will throw an error.
                //close() - To close all the operation
                extent.close();
    }
	
	
	
//#############################################################################
//Function Name    	: LogInfo
//Description     	: Function to Log Info in Extends Report
//Input Parameters 	: strStepName, strDescription,strStatus
//Return Value    	: None
//#############################################################################

			public  static void LogInfo(String strStepName,String strDescription,Status strStatus)
			{
				 String ScreenShotPath1 = "";
				 String screenshotName="";
				 
				 if (strStatus.equals(Status.PASS))
				 {
					    //logger = extent.startTest(DriverScript.testcase);
					   	screenshotName = DriverScript.testcase+"_"+ inc +".jpg";;
						ScreenShotPath1 = ReportPath + "//"+screenshotName;
						Util.takeScreenShot(ScreenShotPath1);
						inc++;
						
						String details = strStepName + strDescription + "Test Case Passed" + logger.addScreenCapture(ScreenShotPath1);
						logger.log(LogStatus.PASS, details);
				 }
				 if (strStatus.equals(Status.FAIL))
				 {
					 //logger = extent.startTest("fail test");
					   	screenshotName = DriverScript.testcase+"_"+inc+".jpg";;
						ScreenShotPath1 = ReportPath + "//"+screenshotName;
						Util.takeScreenShot(ScreenShotPath1);
						inc++;
						
						String details = strStepName + strDescription + "Test Case Failed" + logger.addScreenCapture(ScreenShotPath1);
						logger.log(LogStatus.FAIL, details);					
				}

				 	
	}

	
}

