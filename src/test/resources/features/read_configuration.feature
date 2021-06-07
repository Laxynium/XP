Feature: Read configuration file
  I want to load parameter values from a configuration file

  Scenario: There is a configuration file
    Given There is a configuration file
    """
    {"pathToAdvertisements":"data/ads.csv","availableAdvertisementTypes":["IMAGE", "VIDEO", "AUDIO"],"availableAdvertisementFormats":["SMALL", "LARGE"],"availableCurrencies":["PLN", "EUR"],"availablePricingMethods":["PER_VIEW"]}
    """
    When I start the program
    Then Parameter values are loaded
