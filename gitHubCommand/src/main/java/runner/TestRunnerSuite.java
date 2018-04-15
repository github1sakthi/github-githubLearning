package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features", glue = { "stepDefinitions" }, tags = { "@AdvancedTest,@WindowHandlerTest,@GmailLoginTest,@OrbitzFlightSearchTest,@QuestDiagnosisAppointmentBookingTest" }, plugin = { "pretty",
		"html:target/cucumber-reports"} , monochrome=true)
//@CucumberOptions(features = "features", glue = { "stepDefinitions" }, tags = { "@AdvancedTest" }, plugin = { "pretty",
//"json:target/json/testreport.json","html:target/html/native-cucumber-reports.html","junit:target/junitt/testreport.xml"} , monochrome=true)

public class TestRunnerSuite {

}
