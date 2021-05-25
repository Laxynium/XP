Feature: Delete ads
  I want to delete an ad

  Scenario: Deleting is successful:
    Given Multiple ads exist
    When I delete ad with chosen ID
    Then This add no longer exists

  Scenario: Deleting not existing ad:
    Given Multiple ads exist
    When I delete ad with ID not in presented ads
    Then Nothing is deleted

  Scenario: Deleting last ad removes ads file:
    Given There is last ad available
    When I delete last ad
    Then Ads file is not deleted