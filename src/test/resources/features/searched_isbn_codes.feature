@SearchedIsbnCodes
Feature: Searched ISBN codes

  As a guest
  I want to get the list of ISBN codes not founded by users
  So that I add in collection

  Scenario: Register isbn code searched when user not found book by isbn code
    Given not exists book with isbn "978-1445264714"
    And there are not any searched books
    And find book by isbn "978-1445264714"
    When get list of searched books
    Then response list has searched book with isbn "978-1445264714"

  Scenario: Increment counter when isbn code searched that already exists
    Given not exists book with isbn "978-1445264714"
    And there are not any searched books
    And find book by isbn "978-1445264714"
    And find book by isbn "978-1445264714"
    When get list of searched books
    Then response list has searched book with isbn "978-1445264714" and times 2

  Scenario: Get empty list when there are no searched isbn codes
    Given there are not any searched books
    When get list of searched books
    Then response list is empty

  Scenario: Get searched isbn code list sorted by counter descendant