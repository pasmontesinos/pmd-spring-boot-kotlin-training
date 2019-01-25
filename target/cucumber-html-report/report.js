$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/get_books.feature");
formatter.feature({
  "line": 1,
  "name": "Get Books",
  "description": "\nAs a guest\nI want to get the list of books\nSo that I see the items of collection",
  "id": "get-books",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 7,
  "name": "Get empty list when there are not any book",
  "description": "",
  "id": "get-books;get-empty-list-when-there-are-not-any-book",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "there are not any book",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "get list of books",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "response list is empty",
  "keyword": "Then "
});
formatter.match({
  "location": "GetBooksSteps.there_are_not_any_book()"
});
formatter.result({
  "duration": 147629326,
  "status": "passed"
});
formatter.match({
  "location": "GetBooksSteps.get_list_of_books()"
});
formatter.result({
  "duration": 320948738,
  "status": "passed"
});
formatter.match({
  "location": "GetBooksSteps.result_list_is_empty()"
});
formatter.result({
  "duration": 87202,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Get all books",
  "description": "",
  "id": "get-books;get-all-books",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "there are 3 books",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "get list of books",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "response list has 3 books",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 10
    }
  ],
  "location": "GetBooksSteps.there_are_books(int)"
});
formatter.result({
  "duration": 32717401,
  "status": "passed"
});
formatter.match({
  "location": "GetBooksSteps.get_list_of_books()"
});
formatter.result({
  "duration": 12564091,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 18
    }
  ],
  "location": "GetBooksSteps.response_list_has_books(int)"
});
formatter.result({
  "duration": 64081,
  "status": "passed"
});
});