@US13-LF @smoke
Feature: Search functionality
  US13: As a user, I should be able to search any item/ users from the homepage.

  Background:
    Given user logged in to the app

  Scenario: Verify users can search any files/folder/users from the search box.
    When the user clicks the magnifier icon on the right top
    And users search any existing file or folder or user name
    Then verify the app displays the expected result option