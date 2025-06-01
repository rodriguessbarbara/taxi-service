package com.taxi.service

import com.taxi.model.Ride
import com.taxi.model.database.QueriesSQL
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class GetRideServiceImpl(
  private val jdbcTemplate: JdbcTemplate,
  private val queriesSQL: QueriesSQL
) : IGetRideService {
  
  override fun execute(rideId: String): Ride {
    TODO("Not yet implemented")
  }
}