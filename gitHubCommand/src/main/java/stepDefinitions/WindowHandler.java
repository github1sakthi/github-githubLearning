package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandler extends StepDefinition {

	public WindowHandler(WebDriver webDriver) {
		super(webDriver);
	}

	public void switchToChildWindow() {
		// This will return the number of windows opened by Webdriver and will return
		// Set of St//rings
		Set<String> s1 = webDriver.getWindowHandles();
		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child window then we
			// will close

			if (!parent.equals(child_window)) {
				webDriver.switchTo().window(child_window);
				webDriver.manage().window().maximize();
				System.out.println(webDriver.switchTo().window(child_window).getTitle());

			}

		}

	}

	public void childWindowActions() {
		webDriver.findElement(By.id("identifierId")).sendKeys("testcucumberSelinium");
		webDriver.findElement(By.id("identifierNext")).click();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		webDriver.findElement(By.name("password")).sendKeys("2018test");
		webDriver.findElement(By.id("passwordNext")).click();

		WebElement inboxCheck = webDriver.findElement(By.cssSelector("a.J-Ke.n0"));
		assertTrue(inboxCheck.getText().equals("Inbox (2)"));
	}

	public void switchToParentAndOpenLink() {
		// once all pop up closed now switch to parent window
		webDriver.switchTo().window(parent);
		webDriver.findElement(By.id("googlelink")).click();

	}

}
