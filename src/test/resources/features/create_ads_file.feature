Feature: Create ads file
  I want to add new ad when there is no ads file

  Scenario: There is no ads file
    Given There is no ads file
    When I add new ad
      | id  | type  | format | advertiser      | price | price_type | url             | title | details |
      | 1   | video | small  | example company | 1 USD  | PER_VIEW   | http://test.com | title | details |
    Then Ads file exists