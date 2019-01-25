/*
 * Copyright [yyyy] Pascual Montesinos - https://es.linkedin.com/in/pasmontesinos
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

package com.pasmodev.training.app.dto.mapper

import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.domain.model.Book
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BookDtoToBookMapperImplTest {
    private lateinit var mapper: BookDtoToBookMapper

    @Before
    fun setUp() {
        mapper = BookDtoToBookMapperImpl()
    }

    @Test
    fun map() {
        var bookDto = BookDto(
                isbn = "isbn",
                title = "title",
                author = "author",
                category = "category"
        )

        var book = mapper.map(bookDto)

        assertThat(book.isbn, equalTo(bookDto.isbn))
        assertThat(book.title, equalTo(bookDto.title))
        assertThat(book.author, equalTo(bookDto.author))
        assertThat(book.category, equalTo(bookDto.category))
    }

    @Test
    fun reverseMap() {
        var book = Book(
                isbn = "isbn",
                title = "title",
                author = "author",
                category = "category"
        )

        var bookDto = mapper.reverseMap(book)

        assertThat(bookDto.isbn, equalTo(book.isbn))
        assertThat(bookDto.title, equalTo(book.title))
        assertThat(bookDto.author, equalTo(book.author))
        assertThat(bookDto.category, equalTo(book.category))
    }

}