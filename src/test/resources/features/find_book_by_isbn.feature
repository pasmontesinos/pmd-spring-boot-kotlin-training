@FindBookByIsbn
Feature: Find book by ISBN

  As a guest
  I want to find a book
  So that I known if it is in collection

  Scenario: Find book when isbn code exists
    Given exists book with isbn "978-1445264714"
    When find book by isbn "978-1445264714"
    Then response book has isbn "978-1445264714"


  Scenario: Controlled error when isbn code does not exist
    Given there are not any book
    When find book by isbn "978-1445264714"
    Then controlled error "BookNotFoundException"