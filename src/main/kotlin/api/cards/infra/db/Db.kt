package api.cards.infra.db

interface Db {

    fun saveValue(key: String, value: Any)

    fun getValue(key: String): Any?
}