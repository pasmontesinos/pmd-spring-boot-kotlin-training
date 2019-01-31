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

package com.pasmodev.training.cucumber.getBooks

import com.pasmodev.training.app.TrainingApplication
import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.app.service.GetBooksAppService
import com.pasmodev.training.cucumber.BaseSteps
import com.pasmodev.training.domain.model.Book
import com.pasmodev.training.domain.repository.BookRepository
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [TrainingApplication::class])
class GetBooksSteps : BaseSteps(), En {

    @Autowired
    lateinit var bookRepository: BookRepository

    private var responseList: ResponseEntity<Array<BookDto>>? = null

    init {

        Given("^there are not any book$") {
            bookRepository.deleteAll()
        }

        Given("^there are (\\d+) books$") { counter: Int ->
            (0 until counter).forEach { bookRepository.create(sampleBooks[it]) }
        }

        When("^get list of books$"){
            responseList = restTemplate.getForEntity(booksEndpoint, Array<BookDto>::class.java)
        }

        Then("^response list is empty$") {
            assertThat(responseList?.body?.size, equalTo(0))
        }

        Then("^response list has (\\d+) books$") { counter: Int ->
            assertThat(responseList?.body?.size, equalTo(counter))
        }
    }


    private val sampleBooks = listOf<Book>(
            Book(
                    "978-0134494166",
                    "Clean Architecture: A Craftsman's Guide to Software Structure and Design",
                    "Robert C. Martin",
                    "programming"
            ),
            Book(
                    "978-0132350884",
                    "Clean Code: A Handbook of Agile Software Craftsmanship",
                    "Robert C. Martin",
                    "programming"
            ),
            Book(
                    "978-0451239372",
                    "The Day of the Jackal",
                    "Frederick Forsyth",
                    "thriller"
            ),
            Book(
                    "978-1530075614",
                    "Kotlin for Android Developers: Learn Kotlin the easy way while developing an Android App",
                    "Antonio Leiva",
                    "programming"
            )
    )
}