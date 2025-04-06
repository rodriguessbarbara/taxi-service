package com.taxi

class ValidateCpf {
  
  fun validateCpf(cpf: String): Boolean {
    val cleanCpf = clean(cpf)
    if (cleanCpf.length != CPF_LENGTH || allDigitsAreTheSame(cleanCpf)) return false
    
    val digit1 = calculateDigit(cleanCpf, 10)
    val digit2 = calculateDigit(cleanCpf, 11)
    
    return extractDigit(cleanCpf) == "$digit1$digit2"
  }
  
  private fun clean(cpf: String): String = cpf.filter { it.isDigit() }
  
  private fun allDigitsAreTheSame(cpf: String): Boolean = cpf.all { it == cpf[0] }
  
  private fun calculateDigit(cpf: String, factor: Int): Int {
    var total = 0
    var currentFactor = factor
    
    for (digit in cpf) {
      if (currentFactor > 1) total += digit.digitToInt() * currentFactor--
    }
    
    val rest = total % 11
    return if (rest < 2) 0 else 11 - rest
  }
  
  private fun extractDigit(cpf: String): String = cpf.takeLast(2)
  
  companion object {
    private const val CPF_LENGTH = 11
  }
}