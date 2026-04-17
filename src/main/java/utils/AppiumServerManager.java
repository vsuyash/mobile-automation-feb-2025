package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerManager {

    private static AppiumDriverLocalService service;
    private static final String LOG_DIRECTORY = "src/test/Resources/Logs"; 
    private static final String LOG_FILE_NAME = "appium-server.log"; 

    
    public static void startServer(String ipAddress, int port, Duration timeout, boolean uniqueLogFile) {
        if (service != null && service.isRunning()) {
            System.out.println("Appium server is already running at: " + service.getUrl());
            return;
        }

        createLogDirectory();

        String logFilePath = uniqueLogFile ? getUniqueLogFilePath() : getDefaultLogFilePath();

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(ipAddress)
                .usingPort(port)
                .withTimeout(timeout)
                .withLogFile(new File(logFilePath));

        service = builder.build();
        service.start();
        System.out.println("Appium server started on: " + service.getUrl());
        System.out.println("Log file: " + logFilePath);
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped.");
            service = null;
        } else {
            System.out.println("No Appium server is currently running.");
        }
    }


    public static String getServerUrl() {
        if (service == null || !service.isRunning()) {
            throw new IllegalStateException("Appium server is not running!");
        }
        return service.getUrl().toString();
    }


    private static void createLogDirectory() {
        try {
            Files.createDirectories(Paths.get(LOG_DIRECTORY));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log directory: " + LOG_DIRECTORY, e);
        }
    }

    private static String getDefaultLogFilePath() {
        return LOG_DIRECTORY + File.separator + LOG_FILE_NAME;
    }

    private static String getUniqueLogFilePath() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return LOG_DIRECTORY + File.separator + "appium-server_" + timestamp + ".log";
    }
}
