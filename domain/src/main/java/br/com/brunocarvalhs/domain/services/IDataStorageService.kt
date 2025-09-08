package br.com.brunocarvalhs.domain.services

interface IDataStorageService {

    // Salva uma string simples
    suspend fun saveValue(key: String, value: String)

    // Recupera uma string simples
    suspend fun getValue(key: String): String?

    // Salva um objeto genérico como JSON
    suspend fun <T> saveValue(key: String, value: T, clazz: Class<T>)

    // Recupera um objeto genérico a partir do JSON
    suspend fun <T> getValue(key: String, clazz: Class<T>): T?

    // Remove um valor específico
    suspend fun removeValue(key: String)

    // Limpa todos os dados armazenados
    suspend fun clearAll()
}
