Feature: Add one advertisement to the empty collection
  I want to be able to see the advertisement added to empty collection

  Scenario: Add one advertisement to the empty collection
    Given there are zero ads available
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    End of Advertisements
    """
    When I add one advertisement
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    Then I can see one advertisement
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|VIDEO|SMALL|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """