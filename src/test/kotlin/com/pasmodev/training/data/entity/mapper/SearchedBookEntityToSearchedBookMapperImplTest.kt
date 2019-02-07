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

import com.pasmodev.training.data.entity.SearchedBookEntity
import com.pasmodev.training.domain.model.SearchedBook
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SearchedBookEntityToSearchedBookMapperImplTest {

    private lateinit var mapper: SearchedBookEntityToSearchedBookMapper

    @Before
    fun setUp() {
        mapper = SearchedBookEntityToSearchedBookMapperImpl()
    }

    @Test
    fun map() {
        val searchedBookEntity = SearchedBookEntity("isbn",1)
        val searchedBook = mapper.map(searchedBookEntity)

        assertThat(searchedBook.isbn, Matchers.equalTo(searchedBookEntity.isbn))
        assertThat(searchedBook.times, Matchers.equalTo(searchedBookEntity.times))
    }

    @Test
    fun reverseMap() {
        val searchedBook = SearchedBook("isbn", 1)
        val searchedBookEntity = mapper.reverseMap(searchedBook)

        assertThat(searchedBookEntity.isbn, Matchers.equalTo(searchedBook.isbn))
        assertThat(searchedBookEntity.times, Matchers.equalTo(searchedBook.times))
    }
}