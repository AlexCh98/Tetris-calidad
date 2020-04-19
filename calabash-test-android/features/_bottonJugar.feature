Feature: Botton Jugar
    A button to start the game

    Scenario: Button to start the game
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then take picture