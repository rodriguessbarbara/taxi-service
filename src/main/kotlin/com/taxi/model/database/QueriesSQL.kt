package com.taxi.model.database

class QueriesSQL {
  val findAccountByEmail: String = "SELECT * FROM ccca.account WHERE email = ?"
  val saveNewAccount: String = "INSERT INTO ccca.account (nome, email, cpf, placa_carro, is_passageiro, is_motorista, senha)" +
      " VALUES (?, ?, ?, ?, ?, ?, ?)"
  val findAccountById: String = "SELECT * FROM ccca.account WHERE id = ?"
  val saveNewRide: String = """
    INSERT INTO ride (
        passageiro_id,
        motorista_id,
        status,
        distancia,
        fare,
        origem_lat,
        origem_long,
        destino_lat,
        destino_long,
        data
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""".trimIndent()
  val findRideById: String = "SELECT * FROM ccca.ride WHERE id = ?"
}