# pmd-spring-boot-kotlin-training

Training project for Clean Architecture, BDD and TDD with Spring Boot 2.1.2 and Kotlin

## User Stories

### [Get books](https://github.com/pasmontesinos/pmd-spring-boot-kotlin-training/blob/master/src/test/resources/features/get_books.feature)
> **As a** guest **I want** to get the list of books **So that** I see the items of collection

##### Definition of Done
- [x] Get empty list when there are no books
- [x] Get all books when there are books


### [New book](https://github.com/pasmontesinos/pmd-spring-boot-kotlin-training/blob/master/src/test/resources/features/new_book.feature)
> **As a** guest **I want** to add new book **So that** expand the collection

##### Definition of Done
- [x] Controlled error when not filled all required fields
- [x] Book created when isbn code not exists and all required fields are filled 
- [x] Controlled error when isbn code already exists in collection


### [Find book by ISBN](https://github.com/pasmontesinos/pmd-spring-boot-kotlin-training/blob/master/src/test/resources/features/find_book_by_isbn.feature)
> **As a** guest **I want** to find a book **So that** I known if it is in collection

##### Definition of Done
- [x] Find book when isbn code exists
- [x] Controlled error when isbn code does not exist


### [Searched ISBN codes](https://github.com/pasmontesinos/pmd-spring-boot-kotlin-training/blob/master/src/test/resources/features/searched_isbn_codes.feature)
> **As a** guest **I want** to get the list of ISBN codes not founded by users **So that** I add in collection

##### Definition of Done
- [x] Register isbn code searched when user not found book by isbn code
- [x] Increment times when isbn code searched that already exists
- [x] Get empty list when there are no searched isbn codes
- [X] Get searched isbn code list sorted by times descendant





