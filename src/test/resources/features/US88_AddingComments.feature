@smoke
Feature: Add comments to files/folders
  User Story:
  As a user, I should be able to write comments to files/folders.

  Scenario: User should be able to write comments to files/folders
    Given User is on the home page
    When user clicks "Files" option from top app menu
    And user clicks on the three dots icon next to any file or folder.
    And user clicks "Details" option from file 3dotsMenu
    And user clicks Comments option
    And user writs "A comment" inside the comment input box
    And user clicks submit button to post it
    Then user should see "A comment" displayed in the comment section