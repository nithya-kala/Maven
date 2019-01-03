package SupportLibraries;
import java.lang.reflect.*;
import DriverScript.DriverScript;
import SupportLibraries.Report.Status;


public class Reflection 
{
	/**
	 * Script Name   : Reflection
	 * Generated     : 3-Aug-2018
	 * Description   : Functional Test Script
	 * @author Nithyakala
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	@SuppressWarnings("unchecked")
	public static void execute(String cname,String mname) throws Exception
	{
		Class cl;
		try {
			cl = Class.forName(cname);
			Object o = cl.newInstance(); 
			Method m = cl.getDeclaredMethod(mname,null);
			m.invoke(o,null);		
			
			}
		catch (Exception e)
		{
			System.out.println(e.toString());
			DriverScript.error = true;
			Report.LogInfo("Unhandled Exception occured while exectuing "+ cname,e.toString(),Status.FAIL);
		}
	}
}



