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

import com.pasmodev.training.app.exception.NullPropertyException
import com.pasmodev.training.domain.exception.BookAlreadyExistsException
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import sun.nio.ch.IOUtil
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class RestErrorHandler : DefaultResponseErrorHandler() {

    @Throws(IOException::class)
    override fun handleError(httpResponse: ClientHttpResponse) {

        if (httpResponse.statusCode.series() === HttpStatus.Series.SERVER_ERROR) {

        } else if (httpResponse.statusCode.series() === HttpStatus.Series.CLIENT_ERROR) {
            val body = JSONObject(mapInputStreamToString(httpResponse.body))
            val fields = body.getString("message").split("~:")

            when (fields[0]){
                "NullPropertyException" -> throw NullPropertyException(fields[1])
                "BookAlreadyExistsException" -> throw BookAlreadyExistsException(fields[1])
                else -> throw Exception("Generic Rest exception")
            }
        }
    }

    private fun mapInputStreamToString(inputStream: InputStream) : String {
        val outputStream = ByteArrayOutputStream()

        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        return String(outputStream.toByteArray(), Charsets.UTF_8)
    }
}