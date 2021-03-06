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
import com.pasmodev.training.app.dto.BookDto
import com.pasmodev.training.app.dto.mapper.BookDtoToBookMapper
import com.pasmodev.training.domain.exception.BookNotFoundException
import com.pasmodev.training.domain.usecase.FindBookByIsbnUsecase
import com.pasmodev.training.domain.usecase.SaveSearchedBookUsecase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.servlet.http.HttpServletResponse

@RestController
class FindBookByIsbnAppService {

    private val logger = LoggerFactory.getLogger(FindBookByIsbnAppService::class.java)

    @Autowired
    lateinit var findBookByIsbn: FindBookByIsbnUsecase

    @Autowired
    lateinit var saveSearchedBook: SaveSearchedBookUsecase

    @Autowired
    lateinit var bookDtoToBookMapper: BookDtoToBookMapper

    @GetMapping(Endpoints.BOOK)
    fun findByIsbn(@PathVariable("isbn") isbn: String): BookDto {
        logger.debug("findByIsbn $isbn")
        try {
            return bookDtoToBookMapper.reverseMap(findBookByIsbn(isbn))
        } catch (e: BookNotFoundException){
            logger.debug(e.message)
            saveSearchedBook(isbn)
            throw e
        }
    }

    @ExceptionHandler(BookNotFoundException::class)
    @Throws(IOException::class)
    internal fun handleBookNotFoundException(e: BookNotFoundException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "${e.javaClass.simpleName}~: ${e.localizedMessage}")
    }

}