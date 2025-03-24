package site.nansan.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, YourType> yourCustomRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, YourType> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Key 타입이 String 고정일 때 설정
        template.setKeySerializer(new StringRedisSerializer());
        // Value 타입이 어떤 값이 와도 안전하게 직렬화하는 설정
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // Hash 자료형에 대항ㄴ 설정 값
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 설정 반영
        template.afterPropertiesSet();

        return template;
    }
}
