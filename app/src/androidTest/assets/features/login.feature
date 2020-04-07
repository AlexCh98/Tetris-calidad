Feature: Login
    Perform login on email and password are inputted

    @login-feature
    Scenario Outline: Input email and password in wrong format
        Given I am on login screen
        When I input email <email>
        And I input password "<password>"
        And I press submit button
        Then I should see error on the <view>

    Examples:
        | email | password  | view  |
        | test  | lemoncake | email |
        | test@test.com || password |