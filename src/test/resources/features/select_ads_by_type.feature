Feature: Select ads by type
  I want to be able to get only ads of given type

  Scenario: There are ads available with multiple types
    Given there are following ads
      | id  | type  | format | advertiser      | price | price_type | url             | title  | details  |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title1 | details1 |
      | 2   | image | small  | example company | 1USD  | PER_VIEW   | http://test.com | title2 | details2 |
      | 3   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title3 | details3 |
      | 4   | image | small  | example company | 1USD  | PER_VIEW   | http://test.com | title4 | details3 |
    When I ask for ads of type image
    Then only ads with selected type are returned
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    |2|image|small|example company|1USD|PER_VIEW|http://test.com|title2|details2|
    |4|image|small|example company|1USD|PER_VIEW|http://test.com|title4|details3|
    End of Advertisements
    """
  Scenario: There are no ads with selected type
    Given there are following ads
      | id  | type  | format | advertiser      | price | price_type | url             | title  | details  |
      | 1   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title1 | details1 |
      | 2   | video | small  | example company | 1USD  | PER_VIEW   | http://test.com | title2 | details2 |
    When I ask for ads of type image
    Then only ads with selected type are returned
    """
    |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
    End of Advertisements
    """