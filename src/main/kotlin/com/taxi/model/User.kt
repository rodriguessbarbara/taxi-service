package com.taxi.model

data class User(
    val id: String,
    val nome: String,
    val email: String,
    val cpf: String,
    val senha: String,
    val placaCarro: String? = null,
    val isPassageiro: Boolean = false,
    val isMotorista: Boolean = false
)