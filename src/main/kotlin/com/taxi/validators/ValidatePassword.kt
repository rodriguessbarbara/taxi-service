package com.taxi.validators

class ValidatePassword() {
    fun validatePassword(password: String) : Boolean {
        return Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matches(password)
    }
}