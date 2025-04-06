package com.taxi.exceptions

import org.springframework.http.HttpStatus

class ValidationException(
  val code: String,
  override val message: String,
  val status: HttpStatus
) : RuntimeException(message)
