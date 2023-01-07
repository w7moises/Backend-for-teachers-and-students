@tag
Feature: Create Report
  As tutor
  I want to create a report about the tutorship
  So my clients can see the developement

  Scenario: Make report
    Given the tutor has to create an inform after every tutorship
    When the tutorship has ended
    Then the “Realizar reporte” option will appear.

  Scenario: Edit report
    Given the tutor wants to edit wrong information in the report
    When they go to the option “anterior”
    Then  the report interface will appear with the previous information.

  Scenario: Send report
    Given the tutor wants to send the report
    When they finish editing the report, they need to select the “siguiente” option
    Then the interface with the filled report will appear so they can verify the info so they can select the “Enviar” option.

  Scenario: Complain
    Given the parent hasn't understood completely the report
    When they make a complain
    Then the tutor has to edit it so the client has agreed.