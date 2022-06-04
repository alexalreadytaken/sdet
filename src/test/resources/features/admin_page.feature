Feature: admin page functions
  Scenario: check users table sorting
    Given user open login page
    Then user login "admin1" and password "[9k<k8^z!+$$GkuP"
    Then user open players page
    When user sort players by "External ID"
    And first user id starts with "1"
    When user sort players by "External ID"
    And first user id starts with "9"