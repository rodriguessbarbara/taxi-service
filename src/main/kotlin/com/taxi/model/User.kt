package com.taxi.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class User(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val cpf: String,
    val senha: String,
    val placaCarro: String? = null,
    val isPassageiro: Boolean = false,
    val isMotorista: Boolean = false
)