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

package com.pasmodev.training.data.repository

import com.pasmodev.training.data.entity.SearchedBookEntity
import com.pasmodev.training.data.entity.mapper.SearchedBookEntityToSearchedBookMapper
import com.pasmodev.training.domain.exception.SearchedBookNotFoundException
import com.pasmodev.training.domain.model.SearchedBook
import com.pasmodev.training.domain.repository.SearchedBookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class SearchedBookRepositoryImpl : SearchedBookRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var searchedBookJpaRepository: SearchedBookJpaRepository

    @Autowired
    lateinit var searchedBookEntityToSearchedBookMapper: SearchedBookEntityToSearchedBookMapper

    @Transactional
    override fun deleteAll() {
        entityManager.createNativeQuery("DELETE FROM searchedbook").executeUpdate()
    }

    override fun getAll(): List<SearchedBook> {
        return searchedBookJpaRepository.findAll(Sort(Sort.Direction.DESC, "times")).map { searchedBookEntity -> searchedBookEntityToSearchedBookMapper.map(searchedBookEntity) }
    }

    @Throws(SearchedBookNotFoundException::class)
    override fun findByIsbn(isbn: String): SearchedBook {
        val searchedBookEntity = searchedBookJpaRepository.findById(isbn)

        if (searchedBookEntity.isPresent){
            return toModel(searchedBookEntity.get())
        } else {
            throw SearchedBookNotFoundException("The searched book with ISBN $isbn does not exist")
        }
    }

    override fun save(searchedBook: SearchedBook): SearchedBook {
        return toModel(searchedBookJpaRepository.save(toEntity(searchedBook)))
    }

    private fun toEntity(searchedBook: SearchedBook): SearchedBookEntity {
        return searchedBookEntityToSearchedBookMapper.reverseMap(searchedBook)
    }

    private fun toModel(searchedBookEntity: SearchedBookEntity): SearchedBook {
        return searchedBookEntityToSearchedBookMapper.map(searchedBookEntity)
    }
}