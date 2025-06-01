package com.taxi.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

data class Ride(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val passageiroId: User?,
    val motoristaId: User?,
    val status: StatusRide,
    val distancia: Double,
    val fare: Double? = null,
    val origemLat: Double,
    val origemLong: Double,
    val destinoLat: Double,
    val destinoLong: Double,
    val data: Date,
)