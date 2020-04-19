Feature: Botton Exit
    A button to Exit the game

    Scenario: Button to start the game
    	Then I wait for 2 seconds
        Then take picture
        Then I press view with id "botonSalir"
  		Then I wait for 2 seconds
        Then take picture