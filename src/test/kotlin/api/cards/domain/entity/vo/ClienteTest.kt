package api.cards.domain.entity.vo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ClienteTest {

    @Test
    fun `test Cliente creation`() {
        val nome = "João Silva"
        val cpf = "123.456.789-00"
        val idade = 30
        val dataNascimento = "1995-01-01"
        val uf = "SP"
        val rendaMensal = 5000.0
        val email = "joao.silva@example.com"
        val telefoneWhatsapp = "11987654321"

        val cliente = Cliente(
            nome = nome,
            cpf = cpf,
            idade = idade,
            data_nascimento = dataNascimento,
            uf = uf,
            renda_mensal = rendaMensal,
            email = email,
            telefone_whatsapp = telefoneWhatsapp
        )

        assertNotNull(cliente)
        assertEquals(nome, cliente.nome)
        assertEquals(cpf, cliente.cpf)
        assertEquals(idade, cliente.idade)
        assertEquals(dataNascimento, cliente.data_nascimento)
        assertEquals(uf, cliente.uf)
        assertEquals(rendaMensal, cliente.renda_mensal, 0.0)
        assertEquals(email, cliente.email)
        assertEquals(telefoneWhatsapp, cliente.telefone_whatsapp)
    }

    @Test
    fun `test Cliente equality`() {
        val nome = "João Silva"
        val cpf = "123.456.789-00"
        val idade = 30
        val dataNascimento = "1995-01-01"
        val uf = "SP"
        val rendaMensal = 5000.0
        val email = "joao.silva@example.com"
        val telefoneWhatsapp = "11987654321"

        val cliente1 = Cliente(
            nome = nome,
            cpf = cpf,
            idade = idade,
            data_nascimento = dataNascimento,
            uf = uf,
            renda_mensal = rendaMensal,
            email = email,
            telefone_whatsapp = telefoneWhatsapp
        )

        val cliente2 = Cliente(
            nome = nome,
            cpf = cpf,
            idade = idade,
            data_nascimento = dataNascimento,
            uf = uf,
            renda_mensal = rendaMensal,
            email = email,
            telefone_whatsapp = telefoneWhatsapp
        )

        assertEquals(cliente1, cliente2)
    }

    @Test
    fun `test Cliente copy`() {
        val nome = "João Silva"
        val cpf = "123.456.789-00"
        val idade = 30
        val dataNascimento = "1995-01-01"
        val uf = "SP"
        val rendaMensal = 5000.0
        val email = "joao.silva@example.com"
        val telefoneWhatsapp = "11987654321"

        val cliente = Cliente(
            nome = nome,
            cpf = cpf,
            idade = idade,
            data_nascimento = dataNascimento,
            uf = uf,
            renda_mensal = rendaMensal,
            email = email,
            telefone_whatsapp = telefoneWhatsapp
        )

        val copiedCliente = cliente.copy(email = "joao.novo@example.com")

        assertNotEquals(cliente, copiedCliente)
        assertEquals("joao.novo@example.com", copiedCliente.email)
        assertEquals(cliente.nome, copiedCliente.nome)
        assertEquals(cliente.cpf, copiedCliente.cpf)
        assertEquals(cliente.idade, copiedCliente.idade)
        assertEquals(cliente.data_nascimento, copiedCliente.data_nascimento)
        assertEquals(cliente.uf, copiedCliente.uf)
        assertEquals(cliente.renda_mensal, copiedCliente.renda_mensal, 0.0)
        assertEquals(cliente.telefone_whatsapp, copiedCliente.telefone_whatsapp)
    }
}