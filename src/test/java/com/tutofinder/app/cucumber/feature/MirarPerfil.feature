@tag
Feature: View profile
  As user
  I want to check if my info is correct
  So there isn't any error

  Scenario: Profile option
    Given the user wants to verify their registered profile
    When pressing the “Mi Perfil” button
    Then they'll be able to check their information.

  Scenario: Personal data visualization
    Given the user is in the “Mi Perfil” option
    When they want to access their detailed information
    Then  their data and picture will appear.

  Scenario: Activity registry
    Given the user wants to see their recent activities
    When the user will press the “Mi Perfil” option
    Then a list of activities such as tutor services and their payments will appear.