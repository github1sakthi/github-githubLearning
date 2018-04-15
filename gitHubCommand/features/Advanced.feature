Feature: Advanced selenium test scenarios
  Description: Test advanced test features

  @AdvancedTest
  Scenario Outline: Finding broken links
    Given Html "<page>" is loaded
    When a connection check is performed on all available links
    Then should assert all links are active
      | Google Link |
      | Gmail Link  |
      | Yahoo Link  |

    Examples: 
      | page                                                                                        |
      | file:///c:/Users/devis/git/automationlearning/automationTesting/testHtml/MultipleLinks.html |

  @AdvancedTest
  Scenario Outline: Mouse over and screenshot
    Given Html "<page>" is loaded
    When mouse over home and capture screenshot
    Then check mouse out and store captured screenshot

    Examples: 
      | page                                                                                        |
      | http://demo.guru99.com/test/newtours/ |
