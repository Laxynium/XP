Feature: Delete ads
  I want to delete an ad

  Scenario: Deleting is successful:
    Given Multiple ads exist
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
      | 2  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    When I delete ad with chosen ID 2
    Then This add no longer exists
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|VIDEO|SMALL|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """

  Scenario: Deleting not existing ad:
    Given Multiple ads exist
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 1    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
      | 2  | 1    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    When I delete ad with ID 3 not in presented ads
    Then Nothing is deleted
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|VIDEO|SMALL|example company|1 USD|PER_VIEW|http://test.com|title|details|
    |2|VIDEO|SMALL|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """

  Scenario: Deleting last ad removes ads file:
    Given There is last ad available
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    When I delete last ad with id 2
    Then Ads file is not deleted
