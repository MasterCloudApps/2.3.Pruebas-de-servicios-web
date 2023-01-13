Feature: Finding Rick Astley in Wikipedia

  Background:
    * configure driver = { type: 'chrome', showDriverLog: true }
    # * configure driverTarget = { docker: 'ptrthomas/karate-chrome', showDriverLog: true }

  Scenario: Search for Rick Astley in Wikipedia and find the RickRoll reference

    Given driver 'https://wikipedia.org'
    And input('input[name=search]', 'Rick Astley')
    When submit().click("button[type=submit]")
    Then match html('#mw-content-text') contains 'RickRoll'