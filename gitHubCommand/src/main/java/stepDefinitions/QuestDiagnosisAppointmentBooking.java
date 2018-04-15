package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.TestUtils;

public class QuestDiagnosisAppointmentBooking {
	WebDriver questTestdriver;

	@Given("^Open browser and load quest diagnostics page$")
	public void open_browser_and_load_quest_diagnostics_page() throws Throwable {
		questTestdriver = TestUtils.loadChromeDriver();;
		questTestdriver.get("https://secure.questdiagnostics.com/hcp/psc/jsp/SearchLocation.do");
	}

	@When("^User enters location$")
	public void user_enters_location() throws Throwable {
		questTestdriver.findElement(By.id("city")).sendKeys("Topeka");
		questTestdriver.findElement(By.id("state")).click();
		Select state = new Select(questTestdriver.findElement(By.id("state")));
		state.selectByValue("KS");

	}

	@When("^User enters reason for testing$")
	public void user_enters_reason_for_testing() throws Throwable {
		questTestdriver.findElement(By.id("serviceTypeId")).click();
		WebDriverWait wait = new WebDriverWait(questTestdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("servicetype2")));
		questTestdriver.findElement(By.id("servicetype2")).click();
	}

	@When("^User enters date and time and clicks Continue$")
	public void user_enters_date_and_time_and_clicks_Continue() throws Throwable {
		// WebElement date = questTestdriver.findElement(By.id("date"));
		// date.sendKeys("04302018");
		// date.sendKeys(Keys.DOWN);

		questTestdriver.findElement(By.cssSelector("img.trigger.datepick-trigger")).click();

		WebDriverWait wait = new WebDriverWait(questTestdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.datepick-nav")));
		questTestdriver.findElement(By.partialLinkText("30")).click();
		Select timeSlot = new Select(questTestdriver.findElement(By.id("timeSlot")));
		timeSlot.selectByValue("11:00");

		questTestdriver.findElement(By.cssSelector("input.qdbutton.find")).click();

	}

	@Then("^Should reload the page with appointment details$")
	public void should_reload_the_page_with_appointment_details() throws Throwable {
		questTestdriver.findElement(By.partialLinkText("Quest Diagnostics - Topeka"));
		//
		questTestdriver.findElement(By.partialLinkText("08:15")).click();
	}
	
	@When("^User enters a weekend date and time and clicks Continue$")
	public void user_enters_a_weekend_date_and_time_and_clicks_Continue() throws Throwable {
		questTestdriver.findElement(By.cssSelector("img.trigger.datepick-trigger")).click();

		WebDriverWait wait = new WebDriverWait(questTestdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.datepick-nav")));
		questTestdriver.findElement(By.partialLinkText("28")).click();
		Select timeSlot = new Select(questTestdriver.findElement(By.id("timeSlot")));
		timeSlot.selectByValue("11:00");
		questTestdriver.findElement(By.cssSelector("input.qdbutton.find")).click();

	}

	@Then("^Should reload the page with error message$")
	public void should_reload_the_page_with_error_message() throws Throwable {
		TestUtils.waitForElementPresenceOrvisibilityByCssSelector("td.clientErrorMessage",questTestdriver);
		String validationError=questTestdriver.findElement(By.cssSelector("td.clientErrorMessage")).getText();
		assertTrue(validationError.equals("No appointment slots found. Please change date and search again."));
	}


}
