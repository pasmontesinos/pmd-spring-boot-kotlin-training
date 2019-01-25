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

package com.pasmodev.training.app

import com.pasmodev.training.app.configuration.SwaggerConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

@SpringBootApplication
@ComponentScan(basePackages = ["com.pasmodev.training"])
@EnableJpaRepositories(basePackages = ["com.pasmodev.training.data.repository"])
@EntityScan(basePackages = ["com.pasmodev.training.data.entity"])
@Import(SwaggerConfiguration::class)
class TrainingApplication

fun main(args: Array<String>) {
	runApplication<TrainingApplication>(*args)
}

fun addResourceHandlers(registry: ResourceHandlerRegistry) {

	registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/")

}

