 package SupportLibraries;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;




 public class Util 
 {
	
	static Calendar cc = null;
	public static String homePath = "";
	public static Boolean debug = false;
	
	//#############################################################################
	//Function Name    	: SetValue
	//Description     	: Function to set the value for a key in the config file.
	//Input Parameters 	: key,  val
	//Return Value    	: None
	//#############################################################################
	public static void setValue(String key, String val) throws IOException
    {
		BufferedReader br = null;
        BufferedWriter bw = null;
        Util.homePath = new File(".").getCanonicalPath();
        String path = Util.homePath+"\\Config.ini";
        String newpath = path+"1";
        String line = "";
        File f = null;
        File f1 = null;
        try
        {
            f = new File(path);
            f.createNewFile();
            f1 = new File(newpath);
            f1.createNewFile();

            br = new BufferedReader(new FileReader(path));
            bw = new BufferedWriter(new FileWriter(newpath));
            String regex = "=";
            boolean found = false;
            String keyval = key+"="+val;

            while ((line = br.readLine())!=null)
            {
                if (line.trim().length()>0)
                {
                    String[] pairs = line.split(regex);

                    if (pairs[0].trim().equals(key))
                    {
                        bw.newLine();
                        bw.write(keyval);
                        found = true;
                    }
                    else
                    {
                        bw.newLine();
                        bw.write(line);
                    }
                }
            }

            if (!found)
            {
                bw.newLine();
                bw.write(keyval);
            }
        }
        catch (Exception ex)
        {
        }
        finally
        {
            try
            {
                bw.close();
            }
            catch (Exception ex)
            {
            }
            try
            {
                br.close();
            }
            catch (Exception ex)
            {
            }
        }

        try
        {
            //FileCopy(newpath, path);
        }
        catch (Exception ex)
        {
        }
        try
        {
        	f1.delete();
        }
        catch (Exception ex)
        {
        	
        }
    }


    //#############################################################################
	//Function Name    	: GetValue
	//Description     	: Function to get the value from the config file
	//Input Parameters 	: key, def
	//Return Value    	: Value
	//#############################################################################
    public static String getValue(String key, String def)
    {
        BufferedReader br = null;
    
    	String Path1= null;
		try {
			Path1 = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String path =Path1+"\\Config.ini";
        if (Util.debug)
        {
        	System.out.println("get val path="+path+", key = "+key);
        }
        String line = "";
        String val = "";
        Boolean found = false;
        try
        {
            File f = new File(path);
            f.createNewFile();

            br = new BufferedReader(new FileReader(path));

            String regex = "=";

            while ((line = br.readLine())!=null)
            {
                if (line.trim().length()>0)
                {
                    String[] pairs = line.split(regex);

                    if (pairs[0].trim().equals(key))
                    {
                        val = pairs[1];
                        found = true;
                    }
                }
            }
        }
        catch (Exception ex)
        {
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (Exception ex)
            {
            }
            if (!found)
            {
                val = def;
                try
                {
                	//Util.SetValue(key, def);
                }
                catch (Exception ex)
                {
                }
            }
        }

        return val;
    }

    //#############################################################################
	//Function Name    	: FileCopy
	//Description     	: Function to Copy a file
	//Input Parameters 	:  source dest
	//Return Value    	: None
	//#############################################################################
    public static void fileCopy(String source, String dest)
    {
        File inputFile = new File(source);
        File outputFile =  new File(dest);

        FileInputStream in = null;
        FileOutputStream out = null;
        try
        {
        	//new File(source).createNewFile();
        	new File(dest).createNewFile();
            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);
            int c;

            while ((c = in.read()) != -1)
            {
            	
                out.write(c);
            }
        }
        catch (Exception ex)
        {
        	ex.toString();
        	ex.printStackTrace();
        }

        try
        {
            in.close();
        }
        catch (Exception ex)
        {
        }
        try
        {
            out.close();
        }
        catch (Exception ex)
        {
        }
    }
    

   //#############################################################################
	//Function Name    	: copyDirectory
	//Description     	: Function to copy directory
	//Input Parameters 	:  src,  dest
	//Return Value    	: None
	//#############################################################################
    public static void copyDirectory(String src, String dest)
    {
    	File[] f = new File(src).listFiles();
    	if (Util.debug)
    	{
    		JOptionPane.showMessageDialog(null, "Inside directorycopy function"+src+" to "+dest, "Message", JOptionPane.OK_OPTION);
    	}
      }
    

    //#############################################################################
	//Function Name    	: getCurrentDatenTime
	//Description     	: Function to get Current Date and Time
	//Input Parameters 	: format
	//Return Value    	: Current Time
	//#############################################################################
    public static String getCurrentDatenTime(String format)
    {
    	Calendar cal = Calendar.getInstance();
    	cc = cal;
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.format(cal.getTime());
    }
    

   //#############################################################################
	//Function Name    	: getLastsetTimeinmili
	//Description     	: Function to get Last set Time in miliseconds
	//Input Parameters 	: None
	//#############################################################################
    public static long getLastsetTimeinmili()
    {
    	return cc.getTimeInMillis();
    }
    

    //#############################################################################
	//Function Name    	: getFormattedTime
	//Description     	: Function to get Formatted Time
	//Input Parameters 	: time
	//Return Value    	: FormattedTime
	//#############################################################################
    public static String getFormattedTime(long time)
    {
    	long timeMillis = time;   
    	long time1 = timeMillis / 1000;   
    	String seconds = Integer.toString((int)(time1 % 60));   
    	String minutes = Integer.toString((int)((time1 % 3600) / 60));   
    	String hours = Integer.toString((int)(time1 / 3600));   
    	for (int i = 0; i < 2; i++) {   
    	if (seconds.length() < 2) {   
    	seconds = "0" + seconds;   
    	}   
    	if (minutes.length() < 2) {   
    	minutes = "0" + minutes;   
    	}   
    	if (hours.length() < 2) {   
    	hours = "0" + hours;   
    	}   
    	}  
    	return hours+": "+minutes+": "+seconds;

    	/*
    	Calendar cal = Calendar.getInstance();
    	DateFormat d = null;
    	cc = cal;
    	
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    d = sdf.getInstance();
	    cal.setTimeInMillis(diff);
	    return sdf.format(cal.getTime());*/
    }

	 //#############################################################################
	//Function Name    	: takeScreenShot
	//Description     	: Function to take screenshot
	//Input Parameters 	: Path
	//Return Value    	: None
	//#############################################################################
	public static void takeScreenShot(String path)
	{
		try  
		{  
		      //Get the screen size  
		      Toolkit toolkit = Toolkit.getDefaultToolkit();  
		      Dimension screenSize = toolkit.getScreenSize();  
		      Rectangle rect = new Rectangle(0, 0,  
		                                     screenSize.width,  
		                                     screenSize.height);  
		      Robot robot = new Robot();  
		      BufferedImage image = robot.createScreenCapture(rect);  
		      File file;  
		    
		      //Save the screenshot as a png  
		     // file = new File(path);  
		      //ImageIO.write(image, "png", file);  
		    
		      //Save the screenshot as a jpg  
		      file = new File(path);  
		      ImageIO.write(image, "jpg", file);  
		}  
		catch (Exception e)  
		{  
		      System.out.println(e.getMessage());  
		}  
	}
}

