Feature: Delete ads
  I want to delete an ad

  Scenario: Deleting is successful:
    Given Multiple ads exist
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
      | 2   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
    When I delete ad with chosen ID
    """2"""
    Then This add no longer exists
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """

  Scenario: Deleting not existing ad:
    Given Multiple ads exist
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
      | 2    | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
    When I delete ad with ID not in presented ads
    """3"""
    Then Nothing is deleted
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|
    |2|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """

  Scenario: Deleting last ad removes ads file:
    Given There is last ad available
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
    When I delete last ad
    """1"""
    Then Ads file is not deleted
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    End of Advertisements
    """
