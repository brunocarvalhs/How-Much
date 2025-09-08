package br.com.brunocarvalhs.data.constants

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun `EMPTY_STRING should be an empty string`() {
        assertEquals("", EMPTY_STRING)
    }

    @Test
    fun `EMPTY_DOUBLE should be zero`() {
        assertEquals(0.0, EMPTY_DOUBLE, 0.0)
    }

    @Test
    fun `EMPTY_LONG should be zero`() {
        assertEquals(0L, EMPTY_LONG)
    }

    @Test
    fun `ONE_INT should be one`() {
        assertEquals(1, ONE_INT)
    }
}
