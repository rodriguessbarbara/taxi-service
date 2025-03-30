package com.taxi

class ValidateCpf {
    val CPF_LENGTH = 11;

    fun validateCpf(cpf: String): Boolean {
        val cleanCpf = clean(cpf)
        if (cleanCpf.length != CPF_LENGTH) return false
        if (allDigitsAreTheSame(cleanCpf)) return false
        val digit1 = calculateDigit(cleanCpf, 10)
        val digit2 = calculateDigit(cleanCpf, 11)
        return extractDigit(cleanCpf) == "$digit1$digit2"
    }

    private fun clean(cpf: String): String {
        return cpf.replace(Regex("\D"), "")
    }

    private fun allDigitsAreTheSame(cpf: String): Boolean {
        return cpf.all { it == cpf[0] }
    }

    private fun calculateDigit(cpf: String, factor: Int): Int {
        var total = 0
        var currentFactor = factor
        for (digit in cpf) {
            if (currentFactor > 1) total += digit.digitToInt() * currentFactor--
        }
        val rest = total % 11
        return if (rest < 2) 0 else 11 - rest
    }

    private fun extractDigit(cpf: String): String {
        return cpf.takeLast(2)
    }
}

