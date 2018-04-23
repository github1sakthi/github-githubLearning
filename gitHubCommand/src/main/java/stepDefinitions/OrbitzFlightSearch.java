package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import utils.TestUtils;

public class OrbitzFlightSearch extends StepDefinition {
	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flightTab;
	@FindBy(id = "flight-origin-hp-flight")
	private WebElement flightOrigin;
	@FindBy(how = How.CSS, using = "div.autocomplete-dropdown")
	private WebElement originDropDown;
	@FindBy(id = "flight-destination-hp-flight")
	private WebElement flightDestination;
	@FindBy(id = "aria-option-0")
	private WebElement selectOption;
	@FindBy(id = "flight-departing-hp-flight")
	private WebElement startDate;
	@FindBy(how = How.CSS, using = "button[data-year='2018'][data-month='3'][data-day='26']")
	private WebElement selectStrtDate;
	@FindBy(id = "flight-returning-hp-flight")
	private WebElement returnDate;
	@FindBy(how = How.CSS, using = "button[data-year='2018'][data-month='4'][data-day='26']")
	private WebElement selectRetDate;
	@FindBy(id = "flight-advanced-options-hp-flight")
	private WebElement advancedOption;
	@FindBy(id = "advanced-flight-nonstop-hp-flight")
	private WebElement nonStop;

	@FindBy(id = "flight-advanced-preferred-airline-hp-flight")
	private WebElement prefAirline;
	@FindBy(id = "flight-advanced-preferred-class-hp-flight")
	private WebElement preferedClass;
	@FindBy(how = How.CSS, using = "button[class=\"btn-primary btn-action gcw-submit \"]")
	private WebElement submit;
	@FindBy(how = How.CSS, using = "div.title-date-rtv")
	private WebElement searchDate;

	public OrbitzFlightSearch(WebDriver webDriver) {
		super(webDriver);
	}

public void clickFlightTab() {
	flightTab.click();
}
public void selectOrginAndDestination(String orig, String dest) {
	flightOrigin.sendKeys(orig);
	TestUtils.waitForElementPresenceOrvisibilityByCssSelector("div.autocomplete-dropdown", webDriver);
	selectOption.click();

	flightDestination.sendKeys(dest);
	TestUtils.waitForElementPresenceOrvisibilityById("aria-option-0", webDriver);
	selectOption.click();

	
}

public void selectStartAndEndDate() {
	
startDate.click();
	selectStrtDate.click();
	returnDate.click();
	selectRetDate.click();
	
}
public void selectAdvancedOptions() {
	advancedOption.click();
	TestUtils.waitForElementPresenceOrvisibilityById("advanced-flight-nonstop-hp-flight", webDriver);
	nonStop.click();

}
public void selectPreference(String airline, String prefClass) {
	Select preferedAirline = new Select(prefAirline);
	preferedAirline.selectByValue(airline);
	Select prefClassSelect = new Select(preferedClass);
	prefClassSelect.selectByValue(prefClass);
}
public void submit() {
	submit.click();
}
public void validateSuccess() {
	assertTrue(searchDate.getText().equals("Thu, Apr 26"));
}
}
