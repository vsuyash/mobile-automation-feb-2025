package utils;

import java.io.IOException;

public class EmulatorManager {

    // Starts the emulator with the given AVD name
	public static void startEmulator(String avdName) {
	    try {
	        System.out.println("Starting emulator: " + avdName);
	        ProcessBuilder processBuilder = new ProcessBuilder(
	                "cmd.exe", "/c", "emulator -avd " + avdName );
	        
	        processBuilder.start();
	        
	        System.out.println("Waiting for emulator to boot...");
	        Process waitProcess = new ProcessBuilder(
	                "cmd.exe", "/c", "adb wait-for-device")
	                .start();

	        waitProcess.waitFor();

	        System.out.println("Emulator started: " + avdName);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to start emulator: " + e.getMessage());
	    }
	}


    // Stops the emulator
    public static void stopEmulator() {
        try {
            System.out.println("Stopping emulator...");
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "adb emu kill"
            );

            processBuilder.start();

            System.out.println("Emulator stopped.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to stop emulator: " + e.getMessage());
        }
    }
}
