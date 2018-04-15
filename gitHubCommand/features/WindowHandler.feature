Feature: Title of your feature
  I want to use this template for my feature file

  @WindowHandlerTest
  Scenario Outline: Control switched to child window and closes the window
    Given Html "<page>" is loaded
    	
    When User clicks on child a child window open
    And Webdriver control switches to the child window
    And Logs into the application loaded in child window
    Then Closes the child window
    And control moves back to parent window

  Examples:
  |page |
      | file:///c:/Users/devis/git/automationlearning/automationTesting/testHtml/windowHandle.html |