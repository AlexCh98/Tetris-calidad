Feature: Piece
    A new piece for the game

    @piece-feature
    Scenario Outline: in game, in all games modes
        Given The user has began the game
        Then You have to positionate the new piece

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |