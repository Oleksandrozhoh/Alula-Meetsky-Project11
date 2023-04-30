@smoke
Feature: Login Functionality
  User Story: US01 As a user, I should be able to log in.


  Scenario Outline: User should be able to login with valid credentials
    Given user on the login page
    When user use username "<username>" and passcode "<password>"
    And user click the login button
    Then verify the user should be at the "Dashboard" page
    Examples:
      | username | password    |
      | User1    | Userpass123 |
      | User2    | Userpass123 |
      | User3    | Userpass123 |


  Scenario Outline: Verify user login fail with invalid credentials
    Given user on the login page
    When user enter invalid "<username>" and "<password>"
    And user click the login button
    Then verify "Wrong username or password." message should be displayed
    Examples:
      | username | password     |
      | Use1     | Userpass123  |
      | Uer2     | Userpass123  |
      | Usr3     | Userpass123  |
      | User1    | Userpass1234 |
      | User2    | Useass123    |
      | User3    | $Userpass123 |





