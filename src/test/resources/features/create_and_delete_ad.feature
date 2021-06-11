Feature: Create and delete advertisement

  Scenario: Create and delete advertisement
    Given There is one ad
      | id | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1  | image | small  | example company | 1 USD | PER_VIEW   | http://test.com | title | details |
    When I add new advertisement
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 12 | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    And I delete ad with id 12
    Then Ads file has one ad
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|IMAGE|SMALL|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """
