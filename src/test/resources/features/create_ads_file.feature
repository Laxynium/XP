Feature: Create ads file
  I want to add new ad when there is no ads file

  Scenario: There is no ads file
    Given There is no ads file
    When I add new ad
      | id | type | format | advertiser      | price | price_type | url             | title | details |
      | 1  | 0    | 0      | example company | 1 USD | 0          | http://test.com | title | details |
    Then Ads file exists