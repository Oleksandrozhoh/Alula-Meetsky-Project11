@smoke
Feature: As a user, I should be accessing all the main modules of the app.
  User Story: US03 As a user, I should be accessing all the main modules of the app.

  Background:
    Given user on the login page


  Scenario:  Verify users accessing all the main modules of the app.
    And user logins with valid credentials
    Then Verify the user see the following modules:

      | Dashboard |
      | Files     |
      | Photos    |
      | Activity  |
      | Talk      |
      | Mail      |
      | Contacts  |
      | Calendar  |
      | Notes     |
      | Deck      |
      | Tasks     |