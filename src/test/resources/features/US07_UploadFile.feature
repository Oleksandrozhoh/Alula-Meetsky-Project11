@smoke
Feature: Upload file functionality
  US07: As a user, I should be able to upload a file.

  Background:
    Given users on the dashboard page

  Scenario: verify users to upload a file from Files
    When the users click the "Files" module
    When the users click the add icon on the top
    And users uploads file with the Upload file option
    Then verify file is displayed on the page