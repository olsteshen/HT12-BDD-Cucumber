Feature: As a user I want to get ability to search for a book

  Scenario: Search results contain a book
    Given the user opens bookdepository site
    When searches for a search term "Thinking"
    Then the search results are displayed