# pmd-spring-boot-kotlin-training

Training project for Clean Architecture, BDD and TDD with Spring Boot 2.1.2 and Kotlin

## User Stories

### *Get Books*
**As a** guest **I want** to get the list of books **So that** I see the items of collection

*Definition of Done*
* Get empty list when there are no books
* Get all books when there are books

### *New Book*
**As a** guest **I want** to add new book **So that** expand the collection

*Definition of Done*
* Controlled error when not filled all required fields
* Book created when isbn code not exists and all required fields are filled 
* Controlled error when isbn code already exists in collection


