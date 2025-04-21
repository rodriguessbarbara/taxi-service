package com.taxi.controller

import com.taxi.model.User
import com.taxi.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SignUpController(private val signUpService: SignUpService) {
  
  @PostMapping("/signup")
  fun signup(@RequestBody input: User): ResponseEntity<Any> {
    return try {
      signUpService.postSignUp(input)
      
      return ResponseEntity.ok().body(input.id)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating account")
    }
  }
  
  @GetMapping("/accounts/{accountId}")
  fun getAccount(@PathVariable accountId: String) : ResponseEntity<Any> {
    return try {
      val result = signUpService.getAccount(accountId)
      ResponseEntity.ok(result)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found")
    }
  }
}
