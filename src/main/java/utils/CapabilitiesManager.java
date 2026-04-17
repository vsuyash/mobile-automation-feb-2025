package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CapabilitiesManager {

    private static final String CAPABILITIES_FILE_PATH = "src/test/Resources/capabilities.json";
    private Map<String, Map<String, String>> capabilitiesMap;

    public CapabilitiesManager() {
        loadCapabilities();
    }

    private void loadCapabilities() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            capabilitiesMap = objectMapper.readValue(new File(CAPABILITIES_FILE_PATH), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load capabilities.json file.");
        }
    }

    public DesiredCapabilities getCapabilities(String deviceKey) {
        Map<String, String> deviceCapabilities = capabilitiesMap.get(deviceKey);
        if (deviceCapabilities == null) {
            throw new IllegalArgumentException("Device key not found: " + deviceKey);
        }
        

        DesiredCapabilities capabilities = new DesiredCapabilities();

        deviceCapabilities.forEach(capabilities::setCapability);    
    
    

        return capabilities;
    }

   
}
