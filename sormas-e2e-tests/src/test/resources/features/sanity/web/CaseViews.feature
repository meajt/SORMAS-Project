@UI @Sanity @CaseView
Feature: Case view tests

  @env_main
  Scenario: Create a new Case and check details in Detailed view table
    Given API: I create a new person
    Then API: I check that POST call body is "OK"
    And API: I check that POST call status code is 200
    When API: I create a new case
    Then API: I check that POST call body is "OK"
    And API: I check that POST call status code is 200
    Then I log in with National User
    Given I click on the Cases button from navbar
    When I click on the DETAILED button from Case directory
    And I filter by CaseID on Case directory page
    And I am checking if all the fields are correctly displayed in the Case directory Detailed table

  @issue=SORQA-68 @Locale_DE
  Scenario: German date format check
    Given I log in with National User
    And I click on the Cases button from navbar
    And I click on the NEW CASE button
    When I create a new case with specific data for DE version
    Then I check the created data is correctly displayed on Edit case page for DE version
    Then I back to Case Directory using case list button
    And I check if Case date format is correct