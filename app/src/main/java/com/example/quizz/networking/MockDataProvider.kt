package com.example.quizz.networking

import okhttp3.*

class MockDataProvider(private val fileName: String) {

    fun getResponse(request: Request, responseCode: Int): Response {
        val json = readResourceOnClassPath()

        return Response.Builder()
            .code(responseCode)
            .addHeader("Content-Type", "application/json")
            .protocol(Protocol.HTTP_2)
            .body(ResponseBody.create(MediaType.parse("application/json"),json))
            .request(request)
            .message(json)
            .build()
    }

    private fun readResourceOnClassPath(): String {
        val classLoader = Thread.currentThread().contextClassLoader
        return classLoader.getResourceAsStream(fileName).use {
            it.reader().readText()
        }
    }
}