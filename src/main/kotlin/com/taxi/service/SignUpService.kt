package com.taxi.service

import com.taxi.ValidateCpf
import com.taxi.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import java.util.*

class SignUpService() {
  
  fun postSignUp(input: User) {
    val id = UUID.randomUUID().toString()
    
    val existingAccount = jdbcTemplate.queryForList("SELECT * FROM ccca.account WHERE email = ?", input.email)
    if (existingAccount.isNotEmpty()) throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-4")
    
    validaInput(input.nome, input.email, input.cpf)
    
    if (input.isMotorista) {
      input.placaCarro?.let { validaPlacaCarro(it) }
        ?: throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-7")
    }
    salvaSenha(input, id)
  }
  
  private fun validaInput(nome: String, email: String, cpf: String) : Boolean {
    val validateCpf = ValidateCpf()
    
    if (!Regex("^[A-Za-zÀ-ÖØ-öø-ÿ]+( [A-Za-zÀ-ÖØ-öø-ÿ]+)*\$").matches(nome))
      throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-3")
    
    if (!Regex("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$").matches(email))
      throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-2")
    
    if (!validateCpf.validateCpf(cpf))
      throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "-1")
    
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
    jdbcTemplate.update("INSERT INTO ccca.account (account_id, name, email, cpf, car_plate, is_passenger, is_driver, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
      id, input.nome, input.email, input.cpf, input.placaCarro, input.isPassageiro, input.isMotorista, input.senha)
  }
  
  fun getAccount(userId : String) : String {
    val account = query("select * from ccca.account where account_id = $1", [userId]);
    
    return if (account.isEmpty()) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found")
    } else ""
  }
}