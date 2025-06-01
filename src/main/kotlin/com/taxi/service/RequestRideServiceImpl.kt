package com.taxi.service

import com.taxi.model.Ride
import com.taxi.model.database.QueriesSQL
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class RequestRideServiceImpl(
  private val jdbcTemplate: JdbcTemplate,
  private val queriesSQL: QueriesSQL
) : IRequestRideService {
  
  override fun execute(input: Ride): String {
    
    //deve verificar se o account_id tem is_passenger true
    //deve verificar se já não existe uma corrida do passageiro em status diferente de "completed", se existir lançar um erro
    //deve gerar o ride_id (uuid)
    //deve definir o status como "requested"
    //deve definir date com a data atual
    
    return "Ride requested successfully"
  }
}