Feature: Show available ads
  I want to choose ad which I would like to emit

  Scenario: There are ads available
    Given there are ads available
    When I ask to show in console
    Then I can see them in console

  Scenario: There are no ads available
    Given there are no ads available
    When I ask to show in console
    Then I can see zero ads