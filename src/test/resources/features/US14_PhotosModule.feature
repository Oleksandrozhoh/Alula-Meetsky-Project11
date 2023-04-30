@smoke
Feature: Photos Module
  US: As a user, I want to access to Photos module

  Background:
    Given user is on the "Dashboard" page

  Scenario: Verify user can access to the Photos module
    When user clicks the "Photos" module
    Then user can see following modules
      | Your photos     |
      | Your videos     |
      | Favorites       |
      | On this day     |
      | Your folders    |
      | Shared with you |
      | Tagged photos   |
