package br.com.mangerotte.simplecrudkotlin.error

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

class StandardError (

    @JsonFormat(shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    timezone = "GMT")
    val timestamp: Date,
    val message: String?,
    val details: String
)