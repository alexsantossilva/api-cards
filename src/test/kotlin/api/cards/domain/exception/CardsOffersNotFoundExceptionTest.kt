package api.cards.domain.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardsOffersNotFoundExceptionTest {

    @Test
    fun `should create exception with correct message`() {
        val message = "Card offers not found"
        val exception = CardsOffersNotFoundException(message)

        assertEquals(message, exception.message)
    }

    @Test
    fun `should be a subclass of RuntimeException`() {
        val exception = CardsOffersNotFoundException("Test message")

        assertTrue(exception is RuntimeException)
    }
}