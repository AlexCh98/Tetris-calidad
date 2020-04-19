Feature: ChangeColors
    A new button to change the color of pieces in game

    @changecolor-feature
    Scenario Outline: A new button to change the color of pieces in game
        Given I am on activity screen
        When I press the button
        Then The color of pieces changes

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |