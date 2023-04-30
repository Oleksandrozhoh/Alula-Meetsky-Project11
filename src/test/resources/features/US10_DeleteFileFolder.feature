@smoke
Feature: As a user, I should be able to delete a file/folder.

  Background:
    Given user is on the home page

  Scenario: Verify users delete a file/folder

    When user clicks the "Files" module
    And user clicks action-icon from any file on the page
    And user chooses the "Delete" file or folder option
    When user clicks the "Deleted files" sub-module on the left side
    Then Verify the deleted file is displayed on the Deleted Files page