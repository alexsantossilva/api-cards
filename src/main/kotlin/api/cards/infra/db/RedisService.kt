package api.cards.infra.db

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(private val redisTemplate: RedisTemplate<String, Any>): Db {

    override fun saveValue(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
    }

    override fun getValue(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }
}