package stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.TestUtils;

public class TestSteps {
	WebDriver webDriver;
	GmailLogin gmailLoginTest;
	OrbitzFlightSearch orbitzFlightSearchTest;
	WindowHandler windowHandlerTest;
	AdvancedTest advancedTest;

	@Given("^Open browser and Gmail application is loaded$")
	public void open_browser_and_Gmail_application_is_loaded() throws Throwable {
		webDriver = TestUtils.loadDriver();
		gmailLoginTest = new GmailLogin(webDriver);
		webDriver.get("https://gmail.com");
	}

	@When("^User enters user id and clicks Next$")
	public void user_enters_user_id_and_clicks_Next() throws Throwable {
		gmailLoginTest.enterLoginId("testcucumberSelinium");
	}

	@When("^User enters password and clicks Next$")
	public void user_enters_password_and_clicks_Next() throws Throwable {
		TestUtils.waitForElementPresenceOrvisibilityByName("password", webDriver);
		gmailLoginTest.enterLoginPassword("2018test");

	}

	@Then("^Should take user to landing page$")
	public void should_take_user_to_landing_page() throws Throwable {
		gmailLoginTest.validateSuccess();
	}

	@When("^User enters test user id and clicks Next$")
	public void user_enters_test_user_id_and_clicks_Next(DataTable userIDs) {
		List<List<String>> data = userIDs.raw();
		gmailLoginTest.enterLoginId(data.get(0).get(0));

	}

	@And("^User enters test password and clicks Next$")
	public void user_enters_test_password_and_clicks_Next(DataTable userPasswords) {
		List<List<String>> data = userPasswords.raw();
		TestUtils.waitForElementPresenceOrvisibilityByName("password", webDriver);
		gmailLoginTest.enterLoginPassword(data.get(0).get(0));

	}

	@Given("^Open browser and loaded orbitz portal$")
	public void open_browser_and_loaded_orbitz_portal() throws Throwable {
		webDriver = TestUtils.loadDriver();
		orbitzFlightSearchTest = new OrbitzFlightSearch(webDriver);
		webDriver.get("https://www.orbitz.com/");

	}

	@When("^User clicks on flight tab$")
	public void user_clicks_on_flight_tab() throws Throwable {
		orbitzFlightSearchTest.clickFlightTab();
		TestUtils.waitForElementPresenceOrvisibilityById("flight-type-roundtrip-hp-flight", webDriver);
	}

	@When("^Enters flying from and flying to airport codes$")
	public void enters_flying_from_and_flying_to_airport_codes() throws Throwable {
		orbitzFlightSearchTest.selectOrginAndDestination("MCI", "MAA");
	}

	@When("^Enters departing and returning dates$")
	public void enters_departing_and_returning_dates() throws Throwable {
		orbitzFlightSearchTest.selectStartAndEndDate();
	}

	@When("^Clicks advanced option link and choses nonStop checkbox$")
	public void clicks_advanced_option_link_and_choses_nonStop_checkbox() throws Throwable {
		orbitzFlightSearchTest.selectAdvancedOptions();
	}

	@When("^Selects Emirates as the prefered airline$")
	public void selects_Emirates_as_the_prefered_airline() throws Throwable {
		orbitzFlightSearchTest.selectPreference("EY", "premium");
	}

	@When("^clicks on Search button$")
	public void clicks_on_Search_button() throws Throwable {
		orbitzFlightSearchTest.submit();
	}

	@Then("^Should load results page with available flights$")
	public void should_load_results_page_with_available_flights() throws Throwable {
		orbitzFlightSearchTest.validateSuccess();
		;
	}

	@Given("^Html \"([^\"]*)\" is loaded$")
	public void html_is_loaded(String url) throws Throwable {
		webDriver = TestUtils.loadDriver();
		webDriver.get(url);
		windowHandlerTest = new WindowHandler(webDriver);
		advancedTest = new AdvancedTest(webDriver);
	}

	@When("^User clicks on child a child window open$")
	public void user_clicks_on_child_a_child_window_open() throws Throwable {
		windowHandlerTest.parent = webDriver.getWindowHandle();
		webDriver.findElement(By.id("childWin")).click();
	}

	@When("^Webdriver control switches to the child window$")
	public void webdriver_control_switches_to_the_child_window() throws Throwable {
		windowHandlerTest.switchToChildWindow();
	}

	@When("^Logs into the application loaded in child window$")
	public void logs_into_the_application_loaded_in_child_window() throws Throwable {
		windowHandlerTest.childWindowActions();
	}

	@Then("^Closes the child window$")
	public void closes_the_child_window() throws Throwable {
		webDriver.close();
	}

	@Then("^control moves back to parent window$")
	public void control_moves_back_to_parent_window() throws Throwable {
		windowHandlerTest.switchToParentAndOpenLink();

	}
	@When("^a connection check is performed on all available links$")
	public void a_connection_check_is_performed_on_all_available_links() throws Throwable {
		advancedTest.checkAllAvailableLinks();
	}

	@Then("^should assert all links are active$")
	public void should_assert_all_links_are_active(List<String> linkTexts) throws Throwable {
		advancedTest.validateActiveLinks(linkTexts);
	}
	@When("^mouse over home and capture screenshot$")
	public void mouse_over_home_and_capture_screenshot() throws Throwable {
		advancedTest.mouseOverAndScreenShotCaptur();
	}

	@Then("^check mouse out and store captured screenshot$")
	public void check_mouse_out_and_store_captured_screenshot() throws Throwable {
		advancedTest.mouseOutAndStoreScreenshot();
	}
}
