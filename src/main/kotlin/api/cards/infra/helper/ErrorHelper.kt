package api.cards.infra.helper

import api.cards.api.exception.ErrorResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson

object ErrorHelper {

    fun toErrorResponse(json: String): ErrorResponse {
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(json, ErrorResponse::class.java)
    }

    fun errorResponseToJson(errorResponse: ErrorResponse): String {
        val gson = Gson()
        return gson.toJson(errorResponse)
    }
}