package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.EmulatorManager;

public class LoginFunctionality extends BaseTest {

    // Step to start the emulator and initialize the Appium driver
   
    @Given("User is at login page {string} with emulator {string}")
    public void user_is_at_login_page(String deviceKey, String avdName) {
        // Start the emulator with the given AVD name
        EmulatorManager.startEmulator(avdName);

        // Initialize the driver with the device capabilities
        initializeDriver(deviceKey);
        System.out.println("Appium is initiated for device: " + deviceKey);
    }

    // Step for entering wrong credentials
    @When("User enters wrong username and password")
    public void user_enters_wrong_username_and_password() {
        // Add code to simulate entering wrong credentials (e.g., using PageObject Model)
    }

    // Step to click on login button
    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        // Add code to simulate clicking the login button
    }

    // Step to check error for wrong credentials
    @Then("User should see an error for wrong credentials")
    public void user_should_see_an_error_for_wrong_credentials() {
        // Add code to assert the error message or pop-up

        // Clean up and stop the emulator and Appium server
        tearDown();
        EmulatorManager.stopEmulator(); // Stop the emulator after the test
    }
}
