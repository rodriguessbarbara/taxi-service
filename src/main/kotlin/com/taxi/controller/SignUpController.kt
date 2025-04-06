package com.taxi.controller

import com.taxi.model.User
import com.taxi.service.SignUpService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SignupController(private val signUpService: SignUpService) {
    
    @PostMapping("/signup")
    fun signup(@RequestBody input: User): ResponseEntity<Long> {
        val response = signUpService.postSignUp(input)
        
        return ResponseEntity.ok().body(input.id)
    }

    @GetMapping("/accounts/{accountId}")
    fun getAccount(@PathVariable accountId: String): ResponseEntity<Any> {
        val account = signUpService.getAccount(accountId)

        return account
    }
}
