package BusinessComponents;


import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;


import SupportLibraries.ExcelDB;
import SupportLibraries.Report;
import SupportLibraries.Report.Status;
import SupportLibraries.SeleniumHelper;


/**
 * Script Name   : MBP
 * Generated     : 3-Aug-2018
 * Description   : Reusable Functions
 * @author Nithyakala
 */

public class MBP {
	
	//#############################################################################
  	//Function Name    	: ClickCreateMBP
  	//Description     	: Function to Click on Create MBP Link
  	//Input Parameters 	: None
  	//Author            : Nithyakala
  	//#############################################################################
  		
	public static void ClickCreateMBP() throws Exception
	{		
		SeleniumHelper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Home.waitForPageLoaded();
		Home.ExplicitWait("//a[@routerlink='MasterBPDocsAM/MasterBPDocsAM']");

		SeleniumHelper.driver.findElement(By.xpath("//a[@routerlink='MasterBPDocsAM/MasterBPDocsAM']")).click();
		Thread.sleep(10000);
		
		//Home.waitforelemDiappear("//td[@class='dataTables_empty'][contains(text(),'Loading...')]");
		
		
		//while (SeleniumHelper.driver.findElement(By.xpath("//td[@class='dataTables_empty'][contains(text(),'Loading...')]")) == null)
		//{int i=0;i++;}
		
		SeleniumHelper.driver.findElement(By.xpath("//app-mbprecordsview-am//button[@class='btn btn-primary'][contains(text(),'Create MBP')]")).click();
		Thread.sleep(10000);
		
		Report.LogInfo("ClickCreateMBP: ", "Create MBP Link is clicked successfully", Status.PASS);
	}
	
	//#############################################################################
  	//Function Name    	: EnterMBPBasicInfo
  	//Description     	: Function to enter the Basic details in Create MBP Form
  	//Input Parameters 	: None
  	//Author            : Nithyakala
  	//#############################################################################
  	
		public static void EnterMBPBasicInfo() throws Exception
	{		
		SeleniumHelper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txLegalName']")).sendKeys(ExcelDB.getData("BusinessPartnerName"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txAddress1']")).sendKeys(ExcelDB.getData("MBPAddress"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txCity']")).sendKeys(ExcelDB.getData("MBPCity"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txState']")).sendKeys(ExcelDB.getData("MBPState"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txZip']")).sendKeys(ExcelDB.getData("MBPPostalCode"));

		WebElement selectCountryCode = SeleniumHelper.driver.findElement(By.xpath("//select[@id='kwCountryCode']"));
		Select dropdownCountryCode= new Select(selectCountryCode);		
		//dropdownCountryCode.selectByVisibleText(ExcelDB.getData("MBPCountryCode"));
		dropdownCountryCode.selectByVisibleText("840 - United States (of America)");
		
		SeleniumHelper.driver.findElement(By.xpath("//input[@id='txWebsite']")).sendKeys(ExcelDB.getData("MBPWebsite"));
		Select dropdownCountry = new Select(SeleniumHelper.driver.findElement(By.xpath("//select[@id='txCountry']")));
		dropdownCountry.selectByVisibleText(ExcelDB.getData("MBPCountry"));
		
		Select dropdownIBMRelation = new Select(SeleniumHelper.driver.findElement(By.xpath("//*[@id='kwPartnerRel']")));
		dropdownIBMRelation.selectByVisibleText(ExcelDB.getData("MBPIBMRelation"));

		
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txLOCID']")).sendKeys(ExcelDB.getData("MBPLocId"));
		Home.ExplicitWait("//*[@id='txTelNum']");
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txTelNum']")).sendKeys(ExcelDB.getData("MBPTelNo"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txCustNum']")).sendKeys(ExcelDB.getData("MBPCustNum"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txFaxNum']")).sendKeys(ExcelDB.getData("MBPFaxNum"));
		SeleniumHelper.driver.findElement(By.xpath("//*[@id='txGeoID']")).sendKeys(ExcelDB.getData("MBPGeoId"));
		
		Report.LogInfo("EnterMBPBasicInfo: ", "MBP Basic Details entered Successfully", Status.PASS);
	}
	
	//#############################################################################
	//Function Name    	: SaveMBP
	//Description     	: Function to click on Save MBP and success Alert
	//Input Parameters 	: None
	//Author            : Nithyakala
	//#############################################################################
	  	
	public static void SaveMBP() throws Exception
	{
		SeleniumHelper.driver.findElement(By.xpath("//button[contains(text(),'Save')][@class='btn btn-primary']")).click();
		Thread.sleep(5000);

        // accepting javascript alert
        Alert alert = SeleniumHelper.driver.switchTo().alert();
        alert.accept();
        Thread.sleep(5000);
        Report.LogInfo("SaveMBP: ", "MBP is saved successfully", Status.PASS);
	}
	

}
