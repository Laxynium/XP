Feature: Load default configuration
  Scenario: There is no configuration file
  Given There is no configuration file
  When I start the program
  Then Default pathToAdvertisements is loaded
  """data/advertisements.csv"""
  And Default availableAdvertisementTypes is loaded
  |   IMAGE    |
  |   GIF    |
  |   VIDEO    |
  And Default availableAdvertisementFormats is loaded
  |   SMALL    |
  |   MEDIUM    |
  |   LARGE    |

  And Default availableCurrencies is loaded
  |   USD    |
  |   PLN    |
  |   EUR    |

  And Default availablePricingMethods is loaded
  |   PER_VIEW    |
  |   PER_CLICK    |