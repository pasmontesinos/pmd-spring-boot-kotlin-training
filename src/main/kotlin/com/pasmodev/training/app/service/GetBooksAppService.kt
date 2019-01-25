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

package com.pasmodev.training.app.service

import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.app.dto.mapper.BookDtoToBookMapper
import com.pasmodev.training.domain.usecase.GetBooksUsecase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetBooksAppService {

    @Autowired
    lateinit var getBooks: GetBooksUsecase

    @Autowired
    lateinit var bookDtoToBookMapper: BookDtoToBookMapper

    @GetMapping(BOOKS_ENDPOINT)
    fun findAll(): List<BookDto> {
        return getBooks().map { book -> bookDtoToBookMapper.reverseMap(book) }
        /*
        return
        )
        */
    }

    companion object {
        const val BOOKS_ENDPOINT: String = "/books"
    }
}