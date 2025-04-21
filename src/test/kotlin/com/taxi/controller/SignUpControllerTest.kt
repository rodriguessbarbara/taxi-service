package com.taxi.controller

import com.taxi.model.User
import com.taxi.service.SignUpService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus

@ExtendWith(MockKExtension::class)
class SignUpControllerTest {
  
  @MockK
  lateinit var signUpService: SignUpService
  
  @InjectMockKs
  private lateinit var signUpController: SignUpController
  
  @Test
  fun `should return account when found`() {
    val userId = "1"
    val expectedResult = listOf(mapOf("id" to 1, "email" to "user@example.com"))
    
    every {
      signUpService.getAccount(userId)
    } returns expectedResult
    
    val result = signUpController.getAccount(userId)
    
    assertEquals(expectedResult, result.body)
    assertTrue(result.statusCode.is2xxSuccessful)
  }
  
  @Test
  fun `should return empty list when account is not found`() {
    val userId = "2"
    
    every { signUpService.getAccount(userId) } returns emptyList()
    
    val result = signUpController.getAccount(userId)
    assertTrue(result.statusCode.is2xxSuccessful)
  }
  
  @Test
  fun `should create account successfully`() {
    val user = User(
      id = 123L,
      nome = "John Doe",
      email = "john@example.com",
      cpf = "1234567890",
      senha = "senha123",
      isPassageiro = true,
    )
    justRun { signUpService.postSignUp(user) }
    
    val response = signUpController.signup(user)
    
    assertEquals(HttpStatus.OK, response.statusCode)
    assertEquals(user.id, response.body)
  }
  
  @Test
  fun `should return 502 Bad Gateway when signup fails`() {
    val user = User(
      id = 123L,
      nome = "John Doe",
      email = "john@example.com",
      cpf = "1234567890",
      senha = "senha123",
      isPassageiro = true,
    )
    every { signUpService.postSignUp(user) } throws RuntimeException("DB error")
    
    val response = signUpController.signup(user)
    
    assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    assertEquals("Error creating account", response.body)
  }
}