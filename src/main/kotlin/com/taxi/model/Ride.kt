package com.taxi.model

data class Corrida(
    val id: String,
    val origem: String,
    val destino: String,
    val motorista: User,
    val status: String,
    val distancia: Double? = null,
)