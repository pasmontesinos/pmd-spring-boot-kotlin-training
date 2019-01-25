Feature: Get Books

  As a guest
  I want to get the list of books
  So that I see the items of collection

  Scenario: Get empty list when there are not any book
    Given there are not any book
    When get list of books
    Then response list is empty


  Scenario: Get all books
    Given there are 3 books
    When get list of books
    Then response list has 3 books