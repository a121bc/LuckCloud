package com.ltj.gateway.cache;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @describe： TODO
 * @author: Liu Tian Jun
 * @Date: 2019-12-18 15:14
 * @version: 1.0
 */

@Service
public interface IRedisService<K, V> {

    <V> Mono<V> getValue(String key);

    <V> Mono<Boolean> putValue(String key, V value, long time, ChronoUnit timeUnit);

    <V> Mono<Boolean> putValue(String key, V value);

    Mono<Boolean> delete(String key);

    <K, V> Mono<Boolean> putHashValue(String key, K hashKey, V hashValue);

    <K, V> Mono<V> getHashValue(String key, K hashKey);

    <K, V> Mono<List<V>> getMultiHashValue(String key, Collection<K> hashKeys);

    <K, V> Flux<K> getHashKeys(String key);

    <K, V> Mono<Long> deleteHashKeys(String key, Collection<K> hashKeys);

    Flux<Object> executeLuaScript(String luaScript, List<String> keys, List<String> values);

    <T> Flux<T> executeLuaScript(String luaScript, List<String> keys, List<String> values, Class<T> resultType);

    Mono<Boolean> tryGetDistributedLock(String lockKey, String requestId, long expireTime);

    Mono<Boolean> releaseDistributedLock(String lockKey, String requestId);

    String buildLockKey(String lockKey);

}
