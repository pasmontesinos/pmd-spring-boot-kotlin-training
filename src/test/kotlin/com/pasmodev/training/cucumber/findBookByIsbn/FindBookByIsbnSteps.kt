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

package com.pasmodev.training.cucumber.findBookByIsbn

import com.pasmodev.training.app.TrainingApplication
import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.cucumber.BaseSteps
import com.pasmodev.training.domain.model.Book
import com.pasmodev.training.domain.repository.BookRepository
import cucumber.api.java8.En
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [TrainingApplication::class])
class FindBookByIsbnSteps : BaseSteps(), En {

    @Autowired
    lateinit var bookRepository: BookRepository

    private var responseBookDto: BookDto? = null
    private var responseException: Exception? = null

    init {

        Given("^exists book with isbn \"([^\"]*)\"$") { isbn: String ->
            bookRepository.deleteAll()
            val book = Book(
                    isbn = isbn,
                    title = "Scrum and XP from the Trenches - 2nd Edition",
                    author = "Henrik Kniberg",
                    category = "programming"
            )
            bookRepository.create(book)
        }

        Given("^there are not any book$") {
            bookRepository.deleteAll()
        }

        When("^find book by isbn \"([^\"]*)\"$") { isbn: String ->
            try {
                responseBookDto = restTemplate.getForEntity("$booksEndpoint/$isbn", BookDto::class.java).body
            } catch (e: Exception){
                responseBookDto = null;
                responseException = e
            }
        }

        Then("^response book has isbn \"([^\"]*)\"$") { isbn: String ->
            assertThat(responseBookDto?.isbn, equalTo(isbn))
        }

        Then("^controlled error \"([^\"]*)\"$") { exceptionName: String ->
            assertThat(responseException?.javaClass?.simpleName, equalTo(exceptionName))
        }

    }

}