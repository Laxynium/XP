Feature: Show available ads
  I want to choose ad which I would like to emit

  Scenario: There are ads available
    Given there are ads available
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1 USD  | PER_VIEW   | http://test.com | title | details |
    When I ask to show in console
    Then I can see them in console
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|video|small|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """

  Scenario: There are no ads available
    Given there are no ads available
    When I ask to show in console
    Then I can see zero ads
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    End of Advertisements
    """

  Scenario: There is one ad available
    Given there is one ad available
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1 USD  | PER_VIEW   | http://test.com | title | details |
    When I add new ad and ask to show in console
      | id    | type  | format | advertiser      | price | price_type | url             | title | details |
      | 12    | video | small  | example company | 1 USD  | PER_VIEW   | http://test.com | title | details |
    Then  I can see two ads
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |1|video|small|example company|1 USD|PER_VIEW|http://test.com|title|details|
    |12|video|small|example company|1 USD|PER_VIEW|http://test.com|title|details|
    End of Advertisements
    """