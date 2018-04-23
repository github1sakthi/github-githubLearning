package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdvancedTest extends StepDefinition {

	@FindBy(tagName = "a")
	List<WebElement> linkTagElements;

	@FindBy(tagName = "img")
	List<WebElement> imgTagElements;

	Map<WebElement, String> validationResultMap = new HashMap<>();
	 File mouseOverBeforeSc;
	 File mouseOverAfterSc;
	public AdvancedTest(WebDriver webDriver) {
		super(webDriver);
	}

	// public static List findAllLinks(WebDriver driver)
	//
	// {
	// List<WebElement> elementList = new ArrayList<>();
	// elementList = driver.findElements(By.tagName("a"));
	// elementList.addAll(driver.findElements(By.tagName("img")));
	// List<WebElement> finalList = new ArrayList<>();
	// for (WebElement element : elementList)
	// {
	// if (element.getAttribute("href") != null)
	// {
	// finalList.add(element);
	// }
	// }
	// return finalList;
	// }

	public void checkAllAvailableLinks() {
		// url = new URL("http://yahoo.com");
		String response = "";
		List<WebElement> linkOnlyElementList = new ArrayList<>();
		for (WebElement element : linkTagElements) {
			if (element.getAttribute("href") != null) {
				linkOnlyElementList.add(element);
			}
		}
		for (WebElement element : imgTagElements) {
			if (element.getAttribute("href") != null) {
				linkOnlyElementList.add(element);
			}
		}
		for (WebElement element : linkOnlyElementList) {
			URL url;
			try {
				url = new URL(element.getAttribute("href"));
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("HEAD");
				connection.connect();
				int res = connection.getResponseCode();
				response = connection.getResponseMessage();
				connection.disconnect();

			} catch (Exception exp) {
				response = exp.getMessage();
			}
			validationResultMap.put(element, response);
		}
	}

	public void validateActiveLinks(List<String> assertionLinkTexts) {
		Set<WebElement> keys = validationResultMap.keySet();
		for (WebElement inspectedElement : keys) {
			String elementTextOrTitle = inspectedElement.getText().isEmpty()
					? inspectedElement.findElement(By.tagName("img")).getAttribute("title")
					: inspectedElement.getText();
			if (assertionLinkTexts.contains(elementTextOrTitle)) {
				assertTrue("OK".equals(validationResultMap.get(inspectedElement)));

			}
		}
	}
	
	public void mouseOverAndScreenShotCaptur() {
		WebElement link_Home = webDriver.findElement(By.linkText("Home"));
        WebElement td_Home = webDriver
                .findElement(By
                .xpath("//html/body/div"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr"));    
         
        Actions builder = new Actions(webDriver);
        
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();
         
        mouseOverBeforeSc= ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        mouseOverHome.perform(); 
        
         mouseOverAfterSc= ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
         
	}
	
	public void mouseOutAndStoreScreenshot() {
		try {
			FileUtils.copyFile(mouseOverBeforeSc, new File("C:\\images\\before.png"));
			FileUtils.copyFile(mouseOverAfterSc, new File("C:\\images\\after.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
