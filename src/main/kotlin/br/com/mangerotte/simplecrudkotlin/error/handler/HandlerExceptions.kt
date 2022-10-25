package br.com.mangerotte.simplecrudkotlin.error.handler

import br.com.mangerotte.simplecrudkotlin.error.StandardError
import br.com.mangerotte.simplecrudkotlin.error.exceptions.ResourceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date

@ControllerAdvice
@RestController
class HandlerExceptions : ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handlerAllExceptions(ex : Exception, request : WebRequest)
        : ResponseEntity<StandardError> {
        val error: StandardError = StandardError(Date(),
                                                ex.message,
                                                request.getDescription(false))
        return ResponseEntity<StandardError>(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ResourceNotFound::class)
    fun resourceNotFound(ex : ResourceNotFound, request: WebRequest)
                            : ResponseEntity<StandardError> {
        val error : StandardError = StandardError(Date(),
                                    ex.message,
                                    request.getDescription(false))
        return ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND)
    }
}