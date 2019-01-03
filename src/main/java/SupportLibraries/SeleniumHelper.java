package SupportLibraries;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import SupportLibraries.ExcelDB;





public class SeleniumHelper {
	public static WebDriver driver;
	
	public static void setup() {	
			
	selectBrowser("chrome");
	//driver.get(ExcelDB.getData("URL"));
	}	
	
	public static void selectBrowser(String browserName) {
		switch (browserName) {

		case "firefox":
			firefoxProfile();
			break;

		case "ie":
			ie();
			break;

		case "chrome":
			chrome();
			break;

		default:
			firefoxProfile();
			break;
		}

	}

	public static void chrome(){
		String path = Util.homePath;
		
		System.setProperty("webdriver.chrome.driver",path+"\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
		
		//System.setProperty("webdriver.chrome.driver", "C:\\PartnerCommerceWorkSpace\\BPCOps_Automation\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

		driver = new ChromeDriver();
		//driver.manage().window().maximize();
	}

	public static void ie() {
		try{
		String path = Util.homePath;
		System.setProperty("webdriver.ie.driver",path+"\\SeleniumDrivers\\IEDriverServer_x64_3.13.0\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		
		//System.setProperty("webdriver.ie.driver", "C:\\PartnerCommerceWorkSpace\\BPCOps_Automation\\SeleniumDrivers\\IEDriverServer_x64_3.13.0\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        
		//driver.manage().window().maximize();		
		
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		

	}
	
		
	public static void firefoxProfile() {
		
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("browser.download.folderList", 1);
		//profile.setPreference("browser.download.manager.showWhenStarting",false);
		//profile.setPreference("xpinstall.signatures.required", false);
		//profile.setPreference("toolkit.telemetry.reportingpolicy.firstRun", false);
		//profile.setPreference("browser.startup.page", 0); // Empty start page
        //profile.setPreference("browser.startup.homepage_override.mstone", "ignore"); 


		//profile.setPreference("browser.helperApps.neverAsk.saveToDisk",	"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		//driver = new FirefoxDriver(profile);
		
		
		//System.setProperty("webdriver.gecko.driver", "C:\\SeleniumFramework\\SeleniumDrivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		try
		{
			//System.setProperty("webdriver.gecko.driver", "C:\\SeleniumFramework\\SeleniumDrivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			//System.setProperty("webdriver.firefox.marionette","C:\\SeleniumFramework\\SeleniumDrivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			String path = Util.homePath;
			System.setProperty("webdriver.gecko.driver",path+"\\SeleniumDrivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			//System.setProperty("webdriver.gecko.driver", "C:\\PartnerCommerceWorkSpace\\BPCOps_Automation\\SeleniumDrivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			
			// Suppress logs with warning
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
			
			driver = new FirefoxDriver();
		
		//driver.manage().window().maximize();
		
	}
	catch (Exception e1) {
		e1.printStackTrace();
	}

	}

}
