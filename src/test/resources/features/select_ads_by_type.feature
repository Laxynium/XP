Feature: Select ads by type
  I want to be able to get only ads of given type

  Scenario: There are ads available with multiple types
    Given there are following ads
      | id | type | format | advertiser      | price | price_type | url             | title  | details  |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title1 | details1 |
      | 2  | 0    | 0      | example company | 1 USD | 0          | http://test.com | title2 | details2 |
      | 3  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title3 | details3 |
      | 4  | 0    | 0      | example company | 1 USD | 0          | http://test.com | title4 | details3 |
    When I ask for ads of type image
    Then only ads with selected type are returned
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |2|IMAGE|SMALL|example company|1 USD|PER_VIEW|http://test.com|title2|details2|
    |4|IMAGE|SMALL|example company|1 USD|PER_VIEW|http://test.com|title4|details3|
    End of Advertisements
    """

  Scenario: There are no ads with selected type
    Given there are following ads
      | id | type | format | advertiser      | price | price_type | url             | title  | details  |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title1 | details1 |
      | 2  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title2 | details2 |
    When I ask for ads of type image
    Then only ads with selected type are returned
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    End of Advertisements
    """