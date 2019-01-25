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

package com.pasmodev.training.data.entity.mapper

import com.pasmodev.training.data.entity.BookEntity
import com.pasmodev.training.domain.model.Book
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class BookEntityToBookMapperImplTest {
    
    private lateinit var mapper: BookEntityToBookMapper

    @Before
    fun setUp() {
        mapper = BookEntityToBookMapperImpl()
    }

    @Test
    fun map() {
        var bookEntity = BookEntity(
                isbn = "isbn",
                title = "title",
                author = "author",
                category = "category"
        )

        var book = mapper.map(bookEntity)

        assertThat(book.isbn, equalTo(bookEntity.isbn))
        assertThat(book.title, equalTo(bookEntity.title))
        assertThat(book.author, equalTo(bookEntity.author))
        assertThat(book.category, equalTo(bookEntity.category))
    }

    @Test
    fun reverseMap() {
        var book = Book(
                isbn = "isbn",
                title = "title",
                author = "author",
                category = "category"
        )

        var bookEntity = mapper.reverseMap(book)

        assertThat(bookEntity.isbn, equalTo(book.isbn))
        assertThat(bookEntity.title, equalTo(book.title))
        assertThat(bookEntity.author, equalTo(book.author))
        assertThat(bookEntity.category, equalTo(book.category))
    }
}