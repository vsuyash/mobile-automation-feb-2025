package testrunner;

import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/Resources/Features",   
	    glue = "stepdefinitions",                   
	    plugin = {"pretty", "html:target/cucumber-reports"}
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	@Parameters({"deviceKey"})
    public void setUp(String deviceKey) {
        System.out.println("Setting up for device: " + deviceKey);
    }
}