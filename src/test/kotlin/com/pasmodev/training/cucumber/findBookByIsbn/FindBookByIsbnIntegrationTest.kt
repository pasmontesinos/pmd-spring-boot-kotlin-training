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

package com.pasmodev.training.cucumber.findBookByIsbn

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["classpath:features/find_book_by_isbn.feature"],
        plugin = ["json:target/cucumber/find_book_by_isbn.json", "junit:target/cucumber/find_book_by_isbn.xml"],
        glue = ["classpath:com.pasmodev.training.cucumber.findBookByIsbn"],
        tags = ["@FindBookByIsbn"]
)
class FindBookByIsbnIntegrationTest