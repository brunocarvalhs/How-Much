package br.com.brunocarvalhs.data.extensions

import java.security.SecureRandom

val secureRandom = SecureRandom()

fun randomToken(): String {
    val number = secureRandom.nextInt(900_000) + 100_000 // Garante 6 dígitos
    return number.toString()
}