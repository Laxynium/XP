Feature: Create ads file
  I want to add new ad when there is no ads file

  Scenario: There is no ads file
    Given There is no ads file
    When I add new ad
    Then Ads file exists