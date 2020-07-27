@SmokeTestCases @regression
Feature: Verify user is able to login
	
  Scenario: Verify an invalid login
    Given User opens the website
    When User clicks Login button
    Then User should be redirected to Login page
    When User enters "abc@gmail.com" in Email address field
    And User enters "qqqq" in Password field
    And User clicks the Sign in buttton
    Then User should be redirected to login page
    And User can see the authentication failure message as "Authentication failed."
    