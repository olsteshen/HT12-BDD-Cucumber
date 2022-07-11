Feature: As a user I want to get ability to search for a book

  Scenario: Search results contain a book
    Given Guest user opens bookdepository site
    When I search for "Thinking"
    Then the search results are displayed