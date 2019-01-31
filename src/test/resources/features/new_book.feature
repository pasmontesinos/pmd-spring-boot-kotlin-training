@NewBook
Feature: New Book

  As a guest
  I want to add new book
  So that expand the collection

  Scenario: Controlled error when not filled all required fields
    When add new book with isbn ""
    Then controlled error "NullPropertyException"

  Scenario: Book created when isbn code not exists and all required fields are filled
    Given there are not any book
    When add new book with isbn "978-1445264714"
    Then response book has isbn "978-1445264714"

  Scenario: Controlled error when isbn code already exists in collection
    Given exists book with isbn "978-1445264714"
    When add new book with isbn "978-1445264714"
    Then controlled error "BookAlreadyExistsException"