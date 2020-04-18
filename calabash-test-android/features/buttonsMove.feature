Feature: Botton movement
    Buttons to move piece

    Scenario: Button to random movement
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then I press view with id "button1"
        Then I wait for 5 seconds
        Then take picture
        Then I press view with id "button5"
        Then I wait for 2 seconds
        Then take picture
        Then I press view with id "button5"
        Then I wait for 2 seconds
        Then take picture


    Scenario: Button to move left
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then I press view with id "button1"
        Then I wait for 5 seconds
        Then I press view with id "imageButton2"
        Then I wait for 2 seconds
        Then take picture

    Scenario: Button to move right
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then I press view with id "button1"
        Then I wait for 5 seconds
        Then take picture
        Then I press view with id "imageButton3"
        Then I wait for 2 seconds
        Then take picture


    Scenario: Button to rotate left
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then I press view with id "button1"
        Then I wait for 5 seconds
        Then take picture
        Then I press view with id "imageButton4"
        Then I wait for 2 seconds
        Then take picture


    Scenario: Button to rotate right
        Then I press view with id "botonJugar"
        Then I wait for 5 seconds
        Then I press view with id "button1"
        Then I wait for 5 seconds
        Then take picture
        Then I press view with id "imageButton1"
        Then I wait for 2 seconds
        Then take picture

    