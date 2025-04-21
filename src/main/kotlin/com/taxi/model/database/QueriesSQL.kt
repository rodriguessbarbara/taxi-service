package com.taxi.model.database

class QueriesSQL {
  val findAccountByEmail: String = "SELECT * FROM ccca.account WHERE email = ?"
  val salvaNovaConta: String = "INSERT INTO ccca.account (nome, email, cpf, placa_carro, is_passageiro, is_motorista, senha)" +
      " VALUES (?, ?, ?, ?, ?, ?, ?)"
  val findAccountById: String = "SELECT * FROM ccca.account WHERE id = ?"
}