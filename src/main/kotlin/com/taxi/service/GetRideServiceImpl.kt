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
    //    salvaCorrida(input)
    
    return "Ride requested successfully"
  }
}