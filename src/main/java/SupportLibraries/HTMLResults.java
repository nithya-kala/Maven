package SupportLibraries;
import DriverScript.DriverScript;
import java.io.*;
import java.util.*;
import java.text.*;
import SupportLibraries.Report.Status;
/**
 * Description   : Functional Test Script
 * @author 163497
 */
public class HTMLResults 
{
	/**
	 * Script Name   : HTMLResults
	 * Generated     : 3-Aug-2018
	 * Description   : Functional Test Script
	 * @author Nithyakala
	 */
	
	 public static String SummaryHtmlfile = null;
	 public static String TestCaseHtmlfile = null;
	 static String ScreenShotPath = null;
	 static int Pass = 0;
	 static int Fail = 0;
	 static long start = 0;
	 static long end =0;
	 static String duration ="";
	 static int overallPass = 0;
	 static int overallFail = 0;
	 static int stepNo = 1;
	 static int inc =1;
	 static String h1color="";
	 static String h2color="";
	 static String fontColor="";
	
	public HTMLResults() {
		// TODO Auto-generated constructor stub
	}
	
	 //#############################################################################
	//Function Name    	: setColorsforResults
	//Description     	: Function to set the rquired colors for results
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################

	private static void setColorsforResults()
	{
		String theme = Util.getValue("ReportsTheme", "default");
		if(theme.equals("AUTUMN"))
		{
			h1color="#7E5D56";
            h2color="#EDE9CE";
            fontColor="#F6F3E4";
		}
		else if (theme.equalsIgnoreCase("OLIVE"))
		{
			h1color="#686145";
	        h2color="#EDE9CE";
	        fontColor="#E8DEBA";
		}
		else if (theme.equalsIgnoreCase("CLASSIC"))
		{
	        h1color="#687C7D";
	        h2color="#C6D0D1";
	        fontColor="#EDEEF0";
		}
		else if (theme.equalsIgnoreCase("RETRO"))
		{
			h1color="#CE824E";
			h2color="#F3DEB1";
			fontColor="#F8F1E7";
		}
		else if (theme.equalsIgnoreCase("MYSTIC"))
		{
			 h1color="#4D7C7B";
             h2color="#FFFFAE";
             fontColor="#FAFAC5" ;
		}
		else if (theme.equalsIgnoreCase("SERENE"))
		{
			h1color="#7B597A";
            h2color="#ADE0FF";
            fontColor="#C5AFC6";
		}
		else if (theme.equalsIgnoreCase("REBEL"))
		{
			h1color="#953735";
            h2color="#A6A6A6";
            fontColor="#D9D9D9";
			
		}
		else if (theme.equalsIgnoreCase("qwe"))
		{
			h1color="#12579D";
	        h2color="#BCE1FB";
	        fontColor="#FFFFFF" ;
		}
		else
		{
			h1color="#B3D9FF";
	        h2color="#CCD9FF";
	        fontColor="#8A4117";
		}
	}
	

	 //#############################################################################
	//Function Name    	: createTestCaseHeader
	//Description     	: Function to create the testcase header
	//Input Parameters 	: TestCaseHtmlfile,Screenshotpath
	//Return Value    	: void
	//#############################################################################

	
	public static void createTestCaseHeader(String TestCaseHtmlfile,String Screenshotpath)
	{
		inc=1;
		ScreenShotPath = Screenshotpath;
		HTMLResults.TestCaseHtmlfile = TestCaseHtmlfile;
		FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object
        setColorsforResults();
        try
        {
	        // Create a new file output stream
	        // connected to "myfile.txt"
	        out = new FileOutputStream(TestCaseHtmlfile);
	
	        // Connect print stream to the output stream
	        p = new PrintStream( out );
	      
	        String header="<html><head><title>Test Case Automation Execution Results</title>";
	        header +="</head><Body>"+
			"<p align = center><table border=1 bordercolor=#000000 id=table1 width=1000 height=100 >"+
			
			"<tr><td COLSPAN = 6 bgcolor = "+h1color+">";
			header+="<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+DriverScript.testcase+" Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
			header +="</td></tr>"+
			"<tr>"+
			"<td COLSPAN = 6 bgcolor = "+h1color+">"+
			"<p align=justify><b><font color="+fontColor+"size=2 face= Verdana>DATE:"+ Util.getCurrentDatenTime("dd MMMMM yyyy")+
			"</td></tr>";
			header+="<tr bgcolor="+h2color+">"+
			"<td><b>Step No</b></td>"+
			"<td><b>Step Name</b></td>"+
			"<td><b>Description	</b></td>"+
			"<td><b>Status</b>	</td>"+
			"<td><b>Time	</b></td>"+
			"</tr>";	
			
	        p.println (header);
	
	        p.close();
	        start = Util.getLastsetTimeinmili();  //Used to calculate the total time taken to execute testcase    
        }
        catch (Exception ex)
        {
        	System.err.println ("Error writing to file");
            ex.printStackTrace();
        }
	}

	 //#############################################################################
	//Function Name    	: createSummaryHeader
	//Description     	: Function to  create the summary header
	//Input Parameters 	: SummaryHtmlfile
	//Return Value    	: None
	//#############################################################################

	public static void createSummaryHeader(String SummaryHtmlfile)
	{
		HTMLResults.SummaryHtmlfile = SummaryHtmlfile;
		
		FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object
        setColorsforResults();


        try
        {
            // Create a new file output stream
            out = new FileOutputStream(SummaryHtmlfile);

            // Connect print stream to the output stream
            p = new PrintStream( out );
            String header="<html><head><title>Automation Execution Results</title>";
            header +="</head><Body>"+
			"<p align = center><table border=2 bordercolor=#000000 id=table1 width=900 height=31 bordercolorlight=#000000>"+
			"<tr><td COLSPAN = 6 bgcolor = "+h1color+">";
			header+= "<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+Util.getValue("ProjectName", "Selenium Project")+" Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
			header +="</td></tr>"+
			"<tr>"+
			"<td COLSPAN = 6 bgcolor = "+h1color+">"+
			"<p align=justify><b><font color="+fontColor+" size=2 face= Verdana>DATE:"+ Util.getCurrentDatenTime("dd MMMMM yyyy")+
			"</td></tr>";
			header+="<tr bgcolor="+h2color+"><td><b>Test Case ID</b></td>"+
			
			"<td><b>Description</b>	</td>"+
			"<td><b>Execution Time</b></td>"+
			"<td><b>Status</b>	</td>"+
			"</tr>";
			
			p.println (header);

            p.close();	
        }
        catch (Exception ex)
        {
        	System.err.println ("Error writing to file");
            ex.printStackTrace();
        	
        }
	
	}
	

	 //#############################################################################
	//Function Name    	: addRowtoSummary
	//Description     	: Function to add a row to summary
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################

	public  static void addRowtoSummary()
	{
		 BufferedWriter bw = null;
		 String row = null;
	      try {
	         bw = new BufferedWriter(new FileWriter(SummaryHtmlfile, true));
	         String status = "";
		     if (Fail>0)
		     {
		     	overallFail++;
		     	status = "FAIL";
		     }
		     else if (Pass > 0)
		     {
		     	overallPass++;
		     	status = "PASS";
		     }
		     else
		     {
		     	status = "DONE";
		     }
	         row = "<tr><td><a href='" + DriverScript.testcase +".html" + "'" + "target=" + "about_blank" + ">"+DriverScript.testcase+"</a></td><td>"+DriverScript.desc+"</td><td>"+duration+"</td><td>"+status+"</td></tr>";
	         Pass =0;
	         Fail =0;
	         bw.write(row);
	         bw.newLine();
	         
	      }
	      catch (Exception e)
	      {
	    	  
	      }
	      finally
	      {
	    	  try
	    	  {
	    		  bw.close();
	    	  }
	    	  catch (Exception e)
	    	  {
	    		  
	    	  }
	      }
	}
	
	 //#############################################################################
	//Function Name    	: addRowtoTestCase
	//Description     	: Function to add row to testcase
	//Input Parameters 	: strStepName, strDescription,strStatus
	//Return Value    	: None
	//#############################################################################

	public  static void addRowtoTestCase(String strStepName,String strDescription,Status strStatus)
	{
		 String ScreenShotPath1 = "";
		 String screenshotName="";
		 if (strStatus.equals(Status.PASS))
		 {
			Pass++;
			screenshotName = DriverScript.testcase+"_"+inc+".jpg";;
			ScreenShotPath1 = ScreenShotPath+ "\\"+screenshotName;
			Util.takeScreenShot(ScreenShotPath1);
			inc++;
		 }
		 if (strStatus.equals(Status.FAIL))
		 {
			Fail++;
			//String testCaseNameAlone = TestCaseHtmlfile.substring(TestCaseHtmlfile.lastIndexOf("\\"),TestCaseHtmlfile.length()-1);
			screenshotName = DriverScript.testcase+"_"+inc+".jpg";;
			ScreenShotPath1 = ScreenShotPath+ "\\"+screenshotName;
			Util.takeScreenShot(ScreenShotPath1);
			inc++;
		 }
		 BufferedWriter bw = null;
		 String row = null;
	      try {
	         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
	        // row = "<tr><td>"+DriverScript.iteration+"</td><td>"+DriverScript.subiteration+"</td><td>"+strStepName+"</td><td>"+strDescription+"</td><td>"+strStatus+"</td><td>"+Util.getcurrentdate("H:mm:ss")+"</td></tr>";
	       
	         row = "<tr><td>"+stepNo+"</td><td>"+strStepName+"</td><td>"+strDescription+"</td><td>";
	         stepNo++;
        	 if (strStatus.equals(Status.FAIL))
        	 {
        		 int i = DriverScript.subiteration -1; 
        		 //+ DriverScript.testcase +" "+DriverScript.iteration+" "+i+(inc-1)+".jpg" 
        		 row+="<a href='..\\Screenshots\\"+screenshotName+"'"+"target=" + "about_blank" + "><font color = red><B>"+strStatus+"</B></font>";
        	 } 
        	 else if (strStatus.equals(Status.PASS))
        	 {
        		 row+="<a href='..\\Screenshots\\"+screenshotName+"'"+"target=" + "about_blank" + "><font color = green><B>"+strStatus+"</B></font>";
        		 
        	 }
        	 else
        	 {
        		 row+="<b>"+strStatus+"</b>";
        	 }
        	 row+="</td><td>"+Util.getCurrentDatenTime("H:mm:ss")+"</td></tr>";
	         bw.write(row);
	         bw.newLine();
	         
	      }
	      catch (Exception e)
	      {
	    	  
	      }
	      finally
	      {
	    	  try
	    	  {
	    		  bw.close();
	    	  }
	    	  catch (Exception e)
	    	  {
	    		  
	    	  }
	      }

	}

	 //#############################################################################
	//Function Name    	: insertIteration
	//Description     	: Function to insert Iteration
	//Input Parameters 	: iteration
	//Return Value    	: None
	//#############################################################################

	public static void insertIteration(String iteration)
	{
		BufferedWriter bw = null;
		try {
	         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
	         
	         String insertiter=  "<tr><td COLSPAN = 6> <center><b>Iteration: "+ iteration+
	        	 "</b></center></td></tr>";
	        
	         bw.write(insertiter);
	         stepNo=1;
	         
		}catch (Exception e)
		{
			
		}
		finally
	      {
	    	  try
	    	  {
	    		  bw.close();
	    	  }
	    	  catch (Exception e)
	    	  {
	    		  
	    	  }
	      }
		
	}

	 //#############################################################################
	//Function Name    	: closeSummary
	//Description     	: Function to close the summary file
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################

	public static void closeSummary()
	{
		BufferedWriter bw = null;
		try {
	         bw = new BufferedWriter(new FileWriter(SummaryHtmlfile, true));
	         String closetags = "";
	         closetags+=  "<tr><center> <B><td COLSPAN = 3> <font color = green>Passed:</font> "+overallPass+"</td><td  COLSPAN = 3><font color = red>Failed:</font> "+overallFail;
	         overallPass =0;
	         overallFail =0;
			 closetags+="</b></center></td></tr></Table></Body></HTML>";
	         bw.write(closetags);
		}
		catch (Exception e)
		{
			
		}
		finally
	      {
	    	  try
	    	  {
	    		  bw.close();
	    	  }
	    	  catch (Exception e)
	    	  {
	    		  
	    	  }
	      }
	}

	 //#############################################################################
	//Function Name    	: closeTestCase
	//Description     	: Function to close testcase
	//Input Parameters 	: None
	//Return Value    	: None
	//#############################################################################

	public static void closeTestCase()
	{
		end = Util.getLastsetTimeinmili();
		long dur = end - start;
		duration = Util.getFormattedTime(dur);
		
		BufferedWriter bw = null;
		try {
	         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
	         String closetags = "";
	         closetags+=  "<tr><td COLSPAN = 6> <center><B>Total Duration: "+ duration
        	 +"</B></center></td></tr><tr><td COLSPAN = 5 align = right><B><font color = green align = right>PASS:</font>"+Pass+" </b></td></tr><tr><td COLSPAN = 5 align = right><B><font color = red>FAIL:</font> "+Fail;
	         closetags+="</B></td></tr></Table></Body></HTML>";
	         bw.write(closetags);
	         
		}catch (Exception e)
		{
			
		}
		finally
	      {
	    	  try
	    	  {
	    		  bw.close();
	    	  }
	    	  catch (Exception e)
	    	  {
	    		  
	    	  }
	      }
	}
}

