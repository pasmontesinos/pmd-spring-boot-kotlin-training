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

import com.pasmodev.training.app.dto.SearchedBookDto
import com.pasmodev.training.app.exception.NullPropertyException
import com.pasmodev.training.domain.model.SearchedBook

interface SearchedBookDtoToSearchedBookMapper {

    @Throws(NullPropertyException::class)
    fun map(dto: SearchedBookDto): SearchedBook

    fun reverseMap(model: SearchedBook): SearchedBookDto
}