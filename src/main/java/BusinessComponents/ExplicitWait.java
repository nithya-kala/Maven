package BusinessComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
	public ExplicitWait() {
	}

	public static void testMethod(WebDriver driver, String[] argumentArray) {
		try

		{
		WebDriverWait wait=new WebDriverWait(driver, 90);
		WebElement Welmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(argumentArray[0])));
		} catch (Exception e) {
			throw new RuntimeException(" Custom code failed");
		}
	}
}

