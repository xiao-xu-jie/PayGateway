package com.xujie.application.redis.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked"})
@Component
public class RedisUtils {
    private static RedisTemplate<String, Object> staticRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void setCacheObject(final String key, final T value) {
        staticRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public static <T> void setCacheObject(final String key, final T value, final long timeout, final TimeUnit timeUnit) {
        staticRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> Optional<T> getCacheObject(final String key) {
        return Optional.ofNullable((T) staticRedisTemplate.opsForValue().get(key));
    }

    /**
     * 删除单个对象
     *
     * @param key 缓存键值
     */
    public static boolean deleteObject(final String key) {
        return Boolean.TRUE.equals(staticRedisTemplate.delete(key));
    }

    /**
     * 获取单个key的过期时间
     *
     * @param key 缓存键值
     * @return 过期时间
     */
    public static Long getExpireTime(final String key) {
        return staticRedisTemplate.getExpire(key);
    }

    /**
     * zset添加元素
     */
    public static boolean zAdd(String key, Object value, double score) {
        return Boolean.TRUE.equals(staticRedisTemplate.opsForZSet().add(key, value, score));
    }

    /**
     * zset添加TypedTuple元素
     */
    public static Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> tuple) {
        return staticRedisTemplate.opsForZSet().add(key, tuple);
    }

    /**
     * 获取zset的元素
     */
    public static Set<ZSetOperations.TypedTuple<Object>> zGet(String key, long start, long end) {
        return staticRedisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
    }

    /**
     * 删除指定分数之前的元素
     */
    public static Long zRemoveRangeByScore(String key, double min, double max) {
        return staticRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }
    // Springboot启动成功之后会调用这个方法
    @PostConstruct
    public void initRedis() {
        // 初始化设置 静态staticRedisTemplate对象，方便后续操作数据
        staticRedisTemplate = redisTemplate;
    }


}
