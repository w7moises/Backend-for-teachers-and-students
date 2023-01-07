@tag
Feature: Save Tutor as Favorite
  As parent
  I want to save the good tutors
  So I can contact them easily.

  Scenario: The tutor isn't marked
    Given the loged in parent
    When enters the option “Buscar clases” and founds a tutor they like
    Then they can mark the tutor as favorite.

  Scenario: The tutor is marked
    Given the loged in parent
    When enters the option “Buscar clases” and founds a tutor they like
    Then  they can't mark the tutor as favorite again.

  Scenario: "View favorite tutors list"
    Given the parent wants to check their favorite tutors
    When they enter the “Tutorías” section and select the “Favoritos” filter
    Then they will get a list of all the favorite tutors with their detailed info.