package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
	public static WebDriver loadDriver() {
		System.setProperty("webdriver.gecko.driver",
				"drivers/geckodriver.exe");
		DesiredCapabilities desiredCapabilities =
			    new DesiredCapabilities("firefox", "", Platform.ANY);
		FirefoxProfile profile= new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", "False");
		profile.setPreference("browser.cache.memory.enable", "False");
		profile.setPreference("browser.cache.offline.enable", "False");
//		profile.setPreference("network.cookie.cookieBehavior", 2);
		desiredCapabilities.setCapability("firefox_profile", profile);
		//WebDriver driver = new RemoteWebDriver(desiredCapabilities);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setCapability("firefox_profile", profile);
		WebDriver driver = new FirefoxDriver(firefoxOptions);
//					driver.manage().window().maximize();

				//Implicit wait
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
				return driver;

	}

	public static WebDriver loadChromeDriver() {
		System.setProperty("webdriver.gecko.driver",
				"drivers/chromedriver.exe");
				WebDriver driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}
	/**
	 * This method is to wait for webElements to load. WebElements are identified by
	 * Name.
	 * 
	 * @param elementName
	 * @param driver
	 */
	public static void waitForElementPresenceOrvisibilityByName(String elementName, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name(elementName)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)));
	}

	/**
	 * This method is to wait for webElements to load. WebElements are identified by
	 * Id.
	 * 
	 * @param elementId
	 * @param driver
	 */

	public static void waitForElementPresenceOrvisibilityById(String elementId, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
	}
	/**
	 * This method is to wait for webElements to load. WebElements are identified by
	 * cssSelector.
	 * 
	 * @param cssSelector
	 * @param driver
	 */
	public static void waitForElementPresenceOrvisibilityByCssSelector(String cssSelector, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
	}

}
