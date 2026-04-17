package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.AppiumServerManager;
import utils.CapabilitiesManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AppiumDriver driver;

    public void initializeDriver(String deviceKey) {
        try {
            
        	//startServer takes 4 parameter's as input (String(server address), int(port),Duration(in seconds), boolean)
            
            AppiumServerManager.startServer("127.0.0.1", 4723, Duration.ofSeconds(30), true);

            // Get capabilities dynamically
            DesiredCapabilities capabilities = new CapabilitiesManager().getCapabilities(deviceKey);

            // Fetch Appium server URL
            String appiumServerUrl = AppiumServerManager.getServerUrl();

            // Initialize driver based on platform
            String platformName = capabilities.getCapability("platformName").toString();
            System.out.println("Initializing driver for platform: " + platformName);
            if (platformName.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(new URL(appiumServerUrl), capabilities);
            } else if (platformName.equalsIgnoreCase("ios")) {
                driver = new IOSDriver(new URL(appiumServerUrl), capabilities);
            } else {
                throw new RuntimeException("Unsupported platform: " + platformName);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize driver: " + e.getMessage());
        }
    }

    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Stop the Appium server
        AppiumServerManager.stopServer();
    }
}
