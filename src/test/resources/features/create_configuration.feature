Feature: Create configuration file
  I want to generate configuration file when there is no configuration file

  Scenario: There is no configuration file
    Given There is no configuration file
    When I generate configuration
    Then Configuration file exists
    And Has default configuration
    """
    {"pathToAdvertisements":"data/advertisements.csv","pathToUsers":"data/users.csv","availableAdvertisementTypes":["IMAGE","GIF","VIDEO"],"availableAdvertisementFormats":["SMALL","MEDIUM","LARGE"],"availableCurrencies":["USD","PLN","EUR"],"availablePricingMethods":["PER_VIEW","PER_CLICK"]}
    """