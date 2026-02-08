Feature: Login and Get Users API

  @TC-POST-01
  Scenario: Successful login POST
    Given user logs in
    When user gets users
    Then status is 200

  @TC-POST-GET-02
  Scenario: Successful login and retrieve users PORT and GET
    Given user logs in
    When user gets users
    Then status is 200

  @TC-MATCH-USER-03
  Scenario: Login and get users with chaining and schema validation
    Given user logs in
    When user gets users
    Then status is 200
    And response matches users schema

