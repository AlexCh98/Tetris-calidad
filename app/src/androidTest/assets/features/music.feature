Feature: Music
    A new button for pause the music

    @music-feature
    Scenario Outline: A new button for pause the music
        Given I am on activity screen
        When I press the button
        Then The music stops

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |