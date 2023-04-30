@smoke
Feature: Update settings
  US89: As a user, I should be able to update settings.

  Background:
    Given the user on the dashboard page

  Scenario: Verify the users can check any checkbox on the Files page
    When the users click "Files" module
    And user clicks Settings on the left bottom corner
    Then the user should be able to click any checkbox
