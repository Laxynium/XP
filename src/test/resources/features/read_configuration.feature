Feature: Read configuration file
  I want to use configuration values

  Scenario: There is no configuration file in the same directory as the application
  and default configuration values are used
    Given There is no configuration file in the same directory as the application
    When I start the program
    Then Application uses default configuration values

  Scenario: There is a configuration file in the same directory as the application
  and custom configuration values are loaded into the application
    Given There is a configuration file in the same directory as the application
    """
    {"pathToAdvertisements":"data/ads.csv","availableAdvertisementTypes":["IMAGE", "VIDEO", "AUDIO"],"availableAdvertisementFormats":["SMALL", "LARGE"],"availableCurrencies":["PLN", "EUR"],"availablePricingMethods":["PER_VIEW"]}
    """
    When I start the program
    Then Custom configuration values are loaded
