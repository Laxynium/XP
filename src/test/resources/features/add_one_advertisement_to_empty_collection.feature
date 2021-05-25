Feature: Add one advertisement to the empty collection
  I want to be able to see the advertisement added to empty collection

  Scenario: Add one advertisement to the empty collection
    Given there are no ads available
    When I add one advertisement
    Then I can see one advertisement