Feature: Random_Movement
    A new button for do a random movement

    @randomMovement-feature
    Scenario Outline: A new button for do a random movement
        Given I am on activity screen
        When I press the button
        Then Do random movement

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |