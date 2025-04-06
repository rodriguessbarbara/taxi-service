package com.taxi.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class ValidationExceptionHandler {
  
  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(ex: ValidationException, request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
    val body = mapOf(
      "timestamp" to Instant.now(),
      "status" to ex.status.value(),
      "error" to ex.status.reasonPhrase,
      "code" to ex.code,
      "message" to ex.message,
      "path" to request.requestURI
    )
    return ResponseEntity.status(ex.status).body(body)
  }
}
