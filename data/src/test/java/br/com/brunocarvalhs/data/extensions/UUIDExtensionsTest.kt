package br.com.brunocarvalhs.data.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UUIDExtensionsTest {

    @Test
    fun `randomToken should generate a 6-digit token`() {
        // Act
        val token = randomToken()

        // Assert
        assertEquals(6, token.length)
        assertTrue(token.all { it.isDigit() })
        assertTrue(token.toInt() in 100000..999999)
    }
}
