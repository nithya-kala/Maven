package BusinessComponents;


import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import SupportLibraries.ExcelDB;
import SupportLibraries.Report;
import SupportLibraries.Report.Status;
import SupportLibraries.SeleniumHelper;

/**
 * Script Name   : Home
 * Generated     : 3-Aug-2018
 * Description   : Reusable Functions
 * @author Nithyakala
 */

public class Home {
	
	
	//#############################################################################
	//Function Name    	: ExplicitWait
	//Description     	: Function to wait explicitly for object occurence
	//Input Parameters 	: Xpath of WebElement to Wait
	//Author            : Nithyakala
	//Return Value    	: None
	//#############################################################################
		
	public static void ExplicitWait(String WelmtXpath){	
	   try
	   {
		WebDriverWait wait=new WebDriverWait(SeleniumHelper.driver, 90);
		WebElement Welmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(WelmtXpath)));
	   }
	   catch(Exception e){
	        e.printStackTrace();
	    }
	 
	}
	
	//#############################################################################
		//Function Name    	: waitforelemDiappear
		//Description     	: Function to wait until element disappear
		//Input Parameters 	: Xpath of WebElement to Wait
		//Author            : Nithyakala
		//Return Value    	: None
		//#############################################################################
	public static void waitforelemDiappear(String WelmtXpath)
	{
		 try
		   {
			WebDriverWait wait=new WebDriverWait(SeleniumHelper.driver, 90);
			Boolean WaitForElementDisappear = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(WelmtXpath)));
						
		   }
		   catch(Exception e){
		        e.printStackTrace();
		    }
	}
	
	//#############################################################################
	//Function Name    	: waitForLoad
	//Description     	: Function to wait for page load
	//Input Parameters 	: None
	//Author            : Nithyakala
	//Return Value    	: None
	//#############################################################################
	
    public static void waitForLoad() {
    	
    	WebDriverWait wait = new WebDriverWait(SeleniumHelper.driver, 30);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) SeleniumHelper.driver).executeScript(
                    "return document.readyState"
                ).equals("complete");
            }
        });
    }
    
  //#############################################################################
  	//Function Name    	: waitForPageLoaded
  	//Description     	: Function to wait for page load
  	//Input Parameters 	: None
  	//Author            : Nithyakala
  	//Return Value    	: None
  	//#############################################################################
  	
    public static void waitForPageLoaded() { 
    	      ExpectedCondition<Boolean> expectation = new 
    	                ExpectedCondition<Boolean>() { 
    	                    public Boolean apply(WebDriver driver) { 
    	                        return ((JavascriptExecutor) SeleniumHelper.driver).executeScript("return document.readyState").toString().equals("complete"); 
    	                    } 
    	                }; 
    	        try { 
    	            Thread.sleep(1000); 
    	            WebDriverWait wait = new WebDriverWait(SeleniumHelper.driver, 30); 
    	            wait.until(expectation); 
    	        } catch (Throwable error) {} 
    	    } 

    
    //#############################################################################
  	//Function Name    	: waitForPageLoad
  	//Description     	: Function to wait for page load
  	//Input Parameters 	: None
  	//Author            : Nithyakala
  	//Return Value    	: None
  	//#############################################################################
  	
    public static void waitForPageLoad() {
    	  
    	  JavascriptExecutor js = (JavascriptExecutor)SeleniumHelper.driver;
    	  
    	  
    	  //Initially bellow given if condition will check ready state of page.
    	  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
    	   System.out.println("Page Is loaded.");
    	   return; 
    	  } 
    	  
    	  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
    	  //You can replace your value with 25 If you wants to Increase or decrease wait time.
    	  for (int i=0; i<25; i++){ 
    	   try {
    	    Thread.sleep(1000);
    	    }catch (InterruptedException e) {} 
    	   //To check page ready state.
    	   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
    	    break; 
    	   }   
    	  }
    	 }

    //#############################################################################
  	//Function Name    	: VerifyBPCFeatures
  	//Description     	: Function to verify BPC Features
  	//Input Parameters 	: None
  	//Author            : Nithyakala
  	//#############################################################################
  	
        public static void VerifyBPCFeatures() throws Exception
	{
		SeleniumHelper.setup();
		SeleniumHelper.driver.get(ExcelDB.getData("URL"));
			
		
		if(SeleniumHelper.driver.findElements(By.xpath("value"))!= null)	
		Report.LogInfo("VerifyBPCFeatures: ", "BPC Features are successfully displayed", Status.PASS);
	}
	
      //#############################################################################
      //Function Name    	: BPCOpsLogin
      //Description     	: Function to Login to BPCOps Application
      //Input Parameters 	: None
      //Author             : Nithyakala
      //#############################################################################
      	
	public static void BPCOpsLogin() throws Exception
	{
		SeleniumHelper.setup();
		SeleniumHelper.driver.get(ExcelDB.getData("URL"));
		String parentWindowHandle = SeleniumHelper.driver.getWindowHandle();
		
		
		SeleniumHelper.driver.findElement(By.xpath("//a[@id='bpcopsforms']")).click();
				
		Set<String> allWindowHandles = SeleniumHelper.driver.getWindowHandles();
		String lastWindowHandle = ""; 
		for(String handle : allWindowHandles)
		{
			System.out.println("Window handle - > " + handle);
			System.out.println(SeleniumHelper.driver.getTitle());
			SeleniumHelper.driver.switchTo().window(handle);
			lastWindowHandle = handle;
			
		}
		
		//Switch to the parent window
		SeleniumHelper.driver.switchTo().window(parentWindowHandle);
		//close the parent window
		SeleniumHelper.driver.close();
		//at this point there is no focused window, we have to explicitly switch back to some window.
		SeleniumHelper.driver.switchTo().window(lastWindowHandle);


		SeleniumHelper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(SeleniumHelper.driver.getTitle());
		waitForPageLoaded();
		Report.LogInfo("BPC Login Page: ", "BPC Login page is displayed successfully", Status.PASS);
		
		ExplicitWait("//*[@name='username']");
		SeleniumHelper.driver.findElement(By.xpath("//*[@name='username']")).sendKeys(ExcelDB.getData("Username"));
		SeleniumHelper.driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ExcelDB.getData("Password"));
		
		SeleniumHelper.driver.findElement(By.xpath("//button[@id='btn_signin']")).click();
		
		SeleniumHelper.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		waitForPageLoaded();
		ExplicitWait("//a[@routerlink='MasterBPDocsAM/MasterBPDocsAM']");
		
		Report.LogInfo("BPC Login: ", "BPC Application is logged in successfully", Status.PASS);
	}

}
