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

import com.pasmodev.training.data.entity.BookEntity
import com.pasmodev.training.data.entity.mapper.BookEntityToBookMapper
import com.pasmodev.training.domain.exception.BookAlreadyExistsException
import com.pasmodev.training.domain.exception.BookNotFoundException
import com.pasmodev.training.domain.model.Book
import com.pasmodev.training.domain.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Component
class BookRepositoryImpl : BookRepository {
    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var bookJpaRepository: BookJpaRepository

    @Autowired
    lateinit var bookEntityToBookMapper: BookEntityToBookMapper

    @Transactional
    override fun deleteAll() {
        entityManager.createNativeQuery("DELETE FROM book").executeUpdate()
    }

    @Transactional
    override fun deleteByIsbn(isbn: String) {
        bookJpaRepository.deleteById(isbn)
    }

    override fun existsByIsbn(isbn: String): Boolean {
        return bookJpaRepository.existsById(isbn)
    }

    override fun getAll() : List<Book> {
        return bookJpaRepository.findAll().map { bookEntity -> bookEntityToBookMapper.map(bookEntity) }
    }

    @Transactional
    @Throws(BookAlreadyExistsException::class)
    override fun create(book: Book): Book {
        if (bookJpaRepository.existsById(book.isbn))
            throw BookAlreadyExistsException("The book with ISBN ${book.isbn} already exist")

        return toModel(bookJpaRepository.save(toEntity(book)))
    }

    @Throws(BookNotFoundException::class)
    override fun findByIsbn(isbn: String): Book {

        val bookEntity = bookJpaRepository.findById(isbn)

        if (bookEntity.isPresent){
            return toModel(bookEntity.get())
        } else {
            throw BookNotFoundException("The book with ISBN $isbn does not exist")
        }
    }

    private fun toEntity(book: Book): BookEntity {
        return bookEntityToBookMapper.reverseMap(book)
    }

    private fun toModel(bookEntity: BookEntity): Book {
        return bookEntityToBookMapper.map(bookEntity)
    }
}
