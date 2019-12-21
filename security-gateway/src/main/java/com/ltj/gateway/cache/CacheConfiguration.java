package com.ltj.gateway.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author: Liu Tian Jun
 * @Date: 2019/12/21 9:33
 * @describe：
 * @version: 1.0
 */


@Slf4j
@Configuration
@EnableCaching
//@ConfigurationProperties(prefix = "spring.cache.redis")
public class CacheConfiguration {

    /*private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive) {
        log.info("timeToLive：{}", timeToLive);
        this.timeToLive = timeToLive;
    }*/

    /*@Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
        log.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }*/

    @Bean(name = "reactiveRedisTemplate")
    @Primary
    public ReactiveRedisTemplate<String, Object> redisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
                .<String, Object>newSerializationContext()
                .key(keySerializer())
                .hashKey(keySerializer())
                .value(valueSerializer())
                .hashValue(valueSerializer())
                .build();

        ReactiveRedisTemplate redisTemplate = new ReactiveRedisTemplate(connectionFactory,serializationContext);
        log.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}
