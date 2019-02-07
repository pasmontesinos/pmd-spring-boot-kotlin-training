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
import org.springframework.stereotype.Component

@Component
class BookDtoToBookMapperImpl : BookDtoToBookMapper {

    @Throws(NullPropertyException::class)
    override fun map(dto: BookDto): Book {
        return Book(
                isbn = dto.isbn?.let { it } ?: throw NullPropertyException("ISBN must no be null"),
                title = dto.title?.let { it } ?: throw NullPropertyException("Title must no be null"),
                author = dto.author?.let { it } ?: throw NullPropertyException("Author must no be null"),
                category = dto.category?.let { it } ?: throw NullPropertyException("Category must no be null")
        )
    }

    override fun reverseMap(model: Book): BookDto {
        return BookDto(
                isbn = model.isbn,
                title = model.title,
                author = model.author,
                category = model.category
        )
    }
}