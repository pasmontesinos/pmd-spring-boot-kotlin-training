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
import com.pasmodev.training.app.exception.NullPropertyException
import com.pasmodev.training.domain.exception.BookAlreadyExistsException
import com.pasmodev.training.domain.model.Book
import com.pasmodev.training.domain.usecase.GetBooksUsecase
import com.pasmodev.training.domain.usecase.RegisterBookUsecase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletResponse

@RestController
class RegisterBookAppService {

    @Autowired
    lateinit var registerBook: RegisterBookUsecase

    @Autowired
    lateinit var bookDtoToBookMapper: BookDtoToBookMapper

    @PostMapping(BOOKS_ENDPOINT)
    fun register(@RequestBody bookDto: BookDto): BookDto {
        return toDto(registerBook(toModel(bookDto)))
    }

    private fun toDto(book: Book): BookDto {
        return bookDtoToBookMapper.reverseMap(book)
    }

    private fun toModel(bookDto: BookDto): Book {
        return bookDtoToBookMapper.map(bookDto)
    }

    @ExceptionHandler
    @Throws(IOException::class)
    internal fun handleNullPropertyException(e: NullPropertyException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "${e.javaClass.simpleName}~: ${e.localizedMessage}")
    }

    @ExceptionHandler
    @Throws(IOException::class)
    internal fun handleBookAlreadyExistsException(e: BookAlreadyExistsException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "${e.javaClass.simpleName}~: ${e.localizedMessage}")
    }

    companion object {
        const val BOOKS_ENDPOINT: String = "/books"
    }
}