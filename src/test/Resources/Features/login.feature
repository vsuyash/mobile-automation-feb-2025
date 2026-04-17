Feature: Test login functionality

  Scenario: Verify login functionality with wrong credentials
    Given User is at login page "android_device1" with emulator "Pixel_6a_API_26_first"
    When User enters wrong username and password
    And  User clicks on login button
    Then User should see an error for wrong credentials
