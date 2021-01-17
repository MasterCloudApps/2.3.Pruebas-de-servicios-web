Feature: items end-point

    Background:
    * url targetUrlBase
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

    Scenario: create and retrieve a item

        Given path 'items/'
        And request { description: 'Leche', checked: true }
        When method post
        Then status 201
        And match response == { id: '#number', description: 'Leche', checked: true }