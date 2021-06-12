Feature: Generate advertisements csv file

  Scenario: Generate advertisements csv file
    Given Two ads exist
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
      | 2  | 2    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    When I generate advertisements csv file
    Then Csv file is created and contains two ads
    """
    "id","url"
    "1","http://test.com"
    "2","http://test.com"
    """