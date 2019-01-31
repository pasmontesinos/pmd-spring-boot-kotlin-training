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

package com.pasmodev.training.app.dto.mapper

import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.app.exception.NullPropertyException
import com.pasmodev.training.domain.model.Book
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as _when

class BookDtoToBookMapperImplTest {

    @Mock lateinit var mockBookDto: BookDto
    @Mock lateinit var mockBook: Book

    private lateinit var mapper: BookDtoToBookMapper

    private val isbn = "isbn"
    private val title = "title"
    private val author = "author"
    private val category = "category"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        _when(mockBookDto.isbn).thenReturn(isbn)
        _when(mockBookDto.title).thenReturn(title)
        _when(mockBookDto.author).thenReturn(author)
        _when(mockBookDto.category).thenReturn(category)

        _when(mockBook.isbn).thenReturn(isbn)
        _when(mockBook.title).thenReturn(title)
        _when(mockBook.author).thenReturn(author)
        _when(mockBook.category).thenReturn(category)

        mapper = BookDtoToBookMapperImpl()
    }

    @Test
    fun `test map`() {
        var book = mapper.map(mockBookDto)

        assertThat(book.isbn, equalTo(mockBookDto.isbn))
        assertThat(book.title, equalTo(mockBookDto.title))
        assertThat(book.author, equalTo(mockBookDto.author))
        assertThat(book.category, equalTo(mockBookDto.category))
    }

    @Test
    fun `test reverseMap`() {
        var bookDto = mapper.reverseMap(mockBook)

        assertThat(bookDto.isbn, equalTo(mockBook.isbn))
        assertThat(bookDto.title, equalTo(mockBook.title))
        assertThat(bookDto.author, equalTo(mockBook.author))
        assertThat(bookDto.category, equalTo(mockBook.category))
    }

    @Test(expected = NullPropertyException::class)
    fun `when map isbn null then throws NullPropertyException`() {
        _when(mockBookDto.isbn).thenReturn(null)

        mapper.map(mockBookDto)
    }

    @Test(expected = NullPropertyException::class)
    fun `when map title null then throws NullPropertyException`() {
        _when(mockBookDto.title).thenReturn(null)

        mapper.map(mockBookDto)
    }

    @Test(expected = NullPropertyException::class)
    fun `when map author null then throws NullPropertyException`() {
        _when(mockBookDto.author).thenReturn(null)

        mapper.map(mockBookDto)
    }

    @Test(expected = NullPropertyException::class)
    fun `when map category null then throws NullPropertyException`() {
        _when(mockBookDto.category).thenReturn(null)

        mapper.map(mockBookDto)
    }
}