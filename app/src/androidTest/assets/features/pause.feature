Feature: Pause
    A new button for pause the music

    @pause-feature
    Scenario Outline: A new button for pause the game
        Given I am on activity screen
        When I press the button
        Then The game pause

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |