package com.taxi.service

import com.taxi.exceptions.ValidationException
import com.taxi.validators.ValidateCpf
import com.taxi.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignUpService(private val jdbcTemplate: JdbcTemplate) {
  
  fun postSignUp(input: User) {
    val id = UUID.randomUUID().toString()
    
    val existingAccount = jdbcTemplate.queryForList("SELECT * FROM ccca.account WHERE email = ?", input.email)
    if (existingAccount.isNotEmpty()) throw ValidationException("-4",
      "uma conta já existe com esse endereço de email",
        HttpStatus.UNPROCESSABLE_ENTITY)
    
    validaInput(input.nome, input.email, input.cpf)
    
    if (input.isMotorista) {
      input.placaCarro?.let { validaPlacaCarro(it) }
        ?: throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-7")
    }
    
    salvaSenha(input, id)
    println("Conta criada com sucesso. id :: ${id}")
    
  }
  
  private fun validaInput(nome: String, email: String, cpf: String) : Boolean {
    val validateCpf = ValidateCpf()
    
    if (!Regex("^[A-Za-zÀ-ÖØ-öø-ÿ]+( [A-Za-zÀ-ÖØ-öø-ÿ]+)*$").matches(nome)) {
      throw ValidationException("-3",
        "Nome inválido. Apenas letras e espaços são permitidos.",
        HttpStatus.UNPROCESSABLE_ENTITY)
    }
    
    if (!Regex("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$").matches(email)) {
      throw ValidationException("-2",
        "E-mail inválido. Formato esperado: exemplo@dominio.com.",
        HttpStatus.UNPROCESSABLE_ENTITY)
    }
    
    if (!validateCpf.validateCpf(cpf)) {
      throw ValidationException("-1",
        "CPF inválido.",
        HttpStatus.UNPROCESSABLE_ENTITY)
    }
    
    return true
  }
  
  private fun validaPlacaCarro(placa: String) {
    val regexAntigo = Regex("^[A-Z]{3}[0-9]{4}\$")
    val regexMercosul = Regex("^[A-Z]{3}[0-9][A-Z][0-9]{2}\$")
    
    if (!regexAntigo.matches(placa) && !regexMercosul.matches(placa)) {
      throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-6")
    }
  }
  
  private fun salvaSenha(input: User, id: String) {
    jdbcTemplate.update("INSERT INTO ccca.account (nome, email, cpf, placa_carro, is_passageiro, is_motorista, senha)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?)",
      input.nome, input.email, input.cpf, input.placaCarro, input.isPassageiro, input.isMotorista, input.senha)
  }
  
  fun getAccount(userId: String): ResponseEntity<Any> {
    val account = jdbcTemplate.queryForList(
      "SELECT * FROM ccca.account WHERE id = ?",
      userId.toInt()
    )
    
    return if (account.isEmpty()) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found")
    } else {
      ResponseEntity.ok(account)
    }
  }
}