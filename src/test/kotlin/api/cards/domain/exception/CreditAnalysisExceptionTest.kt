package api.cards.domain.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CreditAnalysisExceptionTest {

    @Test
    fun `should create exception with correct message and protocol`() {
        val message = "Credit analysis failed"
        val protocol = "123-ABC"
        val exception = CreditAnalysisException(message, protocol)

        assertEquals(message, exception.message)
        assertEquals(protocol, exception.protocol)
    }

    @Test
    fun `should be a subclass of RuntimeException`() {
        val exception = CreditAnalysisException("Test message", "123-ABC")

        assertTrue(exception is RuntimeException)
    }
}