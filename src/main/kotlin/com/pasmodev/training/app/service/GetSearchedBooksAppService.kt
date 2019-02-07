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

import com.pasmodev.training.app.configuration.Endpoints
import com.pasmodev.training.app.dto.SearchedBookDto
import com.pasmodev.training.app.dto.mapper.SearchedBookDtoToSearchedBookMapper
import com.pasmodev.training.domain.usecase.GetSearchedBooksUsecase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetSearchedBooksAppService {

    private val logger = LoggerFactory.getLogger(GetSearchedBooksAppService::class.java)

    @Autowired
    lateinit var getSearchedBooks: GetSearchedBooksUsecase

    @Autowired
    lateinit var searchedBookDtoToSearchedBookMapper: SearchedBookDtoToSearchedBookMapper

    @GetMapping(Endpoints.SEARCHED)
    fun findAll(): List<SearchedBookDto> {
        logger.debug("findAll")
        return getSearchedBooks().map { book -> searchedBookDtoToSearchedBookMapper.reverseMap(book) }
    }
}