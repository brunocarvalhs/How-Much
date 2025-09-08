package br.com.brunocarvalhs.domain.exceptions

class InvalidTokenException(token: String) :
    Exception("Invalid token: $token")
