@smoke
Feature: As a user, I should be able to access to Contacts module.
  As a user, I should be able to access to Contacts module.
   Background:
     Given user is on the "Dashboard" page

  Scenario:  verify user access to the contacts module

    When the user clicks the "Contacts" module

    Then verify the page title is "Contacts - QA - Meetsky"
