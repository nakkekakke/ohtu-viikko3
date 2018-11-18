Feature: User can log in with valid username/password-combination

  Scenario: user can login with correct password
    Given command login is selected
    When  username "pekka" and password "akkep" are entered
    Then  system will respond with "logged in"

  Scenario: user can not log in with incorrect password
    Given command login is selected
    When  username "pekka" and password "wrong" are entered
    Then  system will respond with "wrong username or password"
