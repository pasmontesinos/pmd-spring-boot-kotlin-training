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

package com.pasmodev.training.cucumber

import com.pasmodev.training.app.configuration.Endpoints
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.client.RestTemplate

abstract class BaseSteps {

    @LocalServerPort
    private val localServerPort: Int = 0

    private val server = "http://localhost"

    protected val booksEndpoint: String
        get() = "$server:$localServerPort/${Endpoints.BOOKS}"

    protected val searchedBooksEndpoint: String
        get() = "$server:$localServerPort/${Endpoints.SEARCHED}"

    protected val restTemplate = RestTemplate()

    init {
        restTemplate.errorHandler = RestErrorHandler()
    }
}