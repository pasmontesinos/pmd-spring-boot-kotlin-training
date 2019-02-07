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

package com.pasmodev.training.domain.repository

import com.pasmodev.training.domain.exception.BookAlreadyExistsException
import com.pasmodev.training.domain.exception.BookNotFoundException
import com.pasmodev.training.domain.model.Book

interface BookRepository {
    fun deleteAll()
    fun getAll() : List<Book>

    @Throws(BookAlreadyExistsException::class)
    fun create(book: Book): Book

    @Throws(BookNotFoundException::class)
    fun findByIsbn(isbn: String): Book

    fun deleteByIsbn(isbn: String)
    fun existsByIsbn(isbn: String): Boolean
}
