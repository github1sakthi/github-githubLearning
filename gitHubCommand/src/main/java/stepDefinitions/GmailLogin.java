package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailLogin extends StepDefinition {
	@FindBy(id = "identifierId")
	public WebElement loginId;

	@FindBy(id = "identifierNext")
	public WebElement loginNext;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(id = "passwordNext")
	public WebElement passwordNext;

	@FindBy(how = How.CSS, using = "a.J-Ke.n0")
	public WebElement inboxText;

	public GmailLogin(WebDriver webDriver) {
		super(webDriver);
	}

	public void enterLoginId(String userLoginId) {
		this.loginId.sendKeys(userLoginId);
		this.loginNext.click();

	}

	public void enterLoginPassword(String userPassword) {
		this.password.sendKeys(userPassword);
		this.passwordNext.click();

	}

	public void validateSuccess() {

		assertTrue(this.inboxText.getText().equals("Inbox (1)"));

	}
}
