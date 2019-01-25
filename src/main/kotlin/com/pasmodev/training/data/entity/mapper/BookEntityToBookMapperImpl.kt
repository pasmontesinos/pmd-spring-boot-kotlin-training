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
import org.springframework.stereotype.Component

@Component
class BookEntityToBookMapperImpl : BookEntityToBookMapper {

    override fun map(entity: BookEntity): Book {
        return Book(
                isbn = entity.isbn,
                title = entity.title,
                author = entity.author,
                category = entity.category
        )
    }

    override fun reverseMap(model: Book): BookEntity {
        return BookEntity(
                isbn = model.isbn,
                title = model.title,
                author = model.author,
                category = model.category
        )
    }
}