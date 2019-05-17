/*
 * Copyright 2019 Pascual Montesinos - https://www.linkedin.com/in/pasmontesinos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pasmodev.training.cucumber.searchedIsbnCodes

import com.pasmodev.training.app.TrainingApplication
import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.app.dto.SearchedBookDto
import com.pasmodev.training.cucumber.BaseSteps
import com.pasmodev.training.domain.model.SearchedBook
import com.pasmodev.training.domain.repository.BookRepository
import com.pasmodev.training.domain.repository.SearchedBookRepository
import cucumber.api.java8.En
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [TrainingApplication::class])
class SearchedIsbnCodesSteps : BaseSteps(), En {

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var searchedRepository: SearchedBookRepository

    private var responseSearchedBookDtoList: Array<SearchedBookDto>? = null

    init {

        Given("^not exists book with isbn \"([^\"]*)\"$") { isbn: String ->
            if (bookRepository.existsByIsbn(isbn)) {
                bookRepository.deleteByIsbn(isbn)
            }
        }

        Given("^there are not any searched books$") {
            searchedRepository.deleteAll()
        }

        Given("^find book by isbn \"([^\"]*)\"$") { isbn: String ->
            try {
                restTemplate.getForEntity("$booksEndpoint/$isbn", BookDto::class.java).body
            } catch (e: Exception){
                Unit
            }
        }

        Given("^exists book with isbn \"([^\"]*)\" and times (\\d+)$") { isbn: String, times: Int ->
            searchedRepository.save(SearchedBook(isbn, times))
        }

        When("^get list of searched books$") {
            responseSearchedBookDtoList = restTemplate.getForEntity(searchedBooksEndpoint, Array<SearchedBookDto>::class.java).body
        }

        Then("^response list has searched book with isbn \"([^\"]*)\"$") { isbn: String ->
            assertThat(responseSearchedBookDtoList?.toList(), contains(hasProperty("isbn", equalTo(isbn)) ))
        }

        Then("^response list has searched book with isbn \"([^\"]*)\" and times (\\d+)$") { isbn: String, times: Int ->
            assertThat(responseSearchedBookDtoList?.toList(), hasItem(SearchedBookDto(isbn, times)))
        }


        Then("^response list is empty$") {
            assertThat(responseSearchedBookDtoList?.size, equalTo(0))
        }


        Then("^position (\\d+) of response list has searched book with isbn \"([^\"]*)\"$") { index: Int, isbn: String ->
            assertThat(responseSearchedBookDtoList?.get(index)?.isbn, equalTo(isbn))
        }

    }

}