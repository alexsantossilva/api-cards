package api.cards.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class RetrieveClienteDataParamsTest {

    @Test
    fun `test RetrieveClienteDataParams creation`() {
        val id = UUID.randomUUID()
        val params = RetrieveClienteDataParams(id)

        assertNotNull(params)
        assertEquals(id, params.id)
    }

    @Test
    fun `test RetrieveClienteDataParams default id`() {
        val params = RetrieveClienteDataParams()

        assertNotNull(params)
        assertNotNull(params.id)
    }

    @Test
    fun `test RetrieveClienteDataParams equality`() {
        val id = UUID.randomUUID()
        val params1 = RetrieveClienteDataParams(id)
        val params2 = RetrieveClienteDataParams(id)

        assertEquals(params1, params2)
    }

    @Test
    fun `test RetrieveClienteDataParams copy`() {
        val id1 = UUID.randomUUID()
        val params = RetrieveClienteDataParams(id1)
        val id2 = UUID.randomUUID()
        val copiedParams = params.copy(id = id2)

        assertNotEquals(params, copiedParams)
        assertEquals(id2, copiedParams.id)
    }
}