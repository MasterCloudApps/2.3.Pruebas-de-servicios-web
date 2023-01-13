Feature: Create new message

  Background:
    * configure driver = { type: 'chrome', showDriverLog: true }
    * configure driverTarget = { docker: 'ptrthomas/karate-chrome', showDriverLog: true}

  Scenario: Create new message and check it

    Given driver targetUrlBase
      And input('#title-input', 'Hola Mundo')
      And input('#body-input', 'Estoy usando Karate')
    When submit().click("input[type=submit]")
    Then match html('#title') contains 'Hola Mundo'