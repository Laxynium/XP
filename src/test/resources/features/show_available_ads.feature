Feature: Show available ads
  I want to choose ad which I would like to emit

  Scenario: There are ads available
    Given there are ads available
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title | details |
    When I ask to show in console
    Then I can see them in console
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """