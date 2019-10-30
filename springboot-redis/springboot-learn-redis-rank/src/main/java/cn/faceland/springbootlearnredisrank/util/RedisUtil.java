package cn.faceland.springbootlearnredisrank.util;

import cn.faceland.springbootlearnredisrank.entity.UserScore;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @author watermelon on 2019/10/30 9:48
 * @description
 */
public class RedisUtil {

    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     *（如果存在就会发生更新）
     * @param redisTemplate  可以传 StringRedisTemplate 或 RedisTemplate
     * @param key
     * @param value
     * @param score
     */
    public static void add(RedisTemplate redisTemplate, String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除元素 zrem
     *
     * @param key
     * @param value
     */
    public static void remove(RedisTemplate redisTemplate, String key, String value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 清空排行榜
     *
     * @param key
     */
    public static boolean removeAll(RedisTemplate redisTemplate, String key) {
        return redisTemplate.delete(key);
    }

    /**
     * score的增加or减少 zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public static Double incrementScore(RedisTemplate redisTemplate, String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 查询value对应的score   zscore
     *
     * @param key
     * @param value
     * @return
     */
    public static Double score(RedisTemplate redisTemplate, String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 判断value在zset中的排名  zrank
     *
     * 积分小的在前面
     *
     * @param key
     * @param value
     * @return
     */
    public static Long rank(RedisTemplate redisTemplate, String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     *
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> range(RedisTemplate redisTemplate, String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }


    /**
     * 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<ZSetOperations.TypedTuple<String>> rangeWithScores(RedisTemplate redisTemplate, String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值  zrevrange
     *
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> reverseRange(RedisTemplate redisTemplate, String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值  zrevrange   -- 带上分数
     *
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(RedisTemplate redisTemplate, String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 根据score的值，来获取满足条件的集合  zrangebyscore
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static Set<String> rangeByScore(RedisTemplate redisTemplate, String key, long min, long max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 返回集合的长度
     *
     * @param key
     * @return
     */
    public static Long size(RedisTemplate redisTemplate, String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 返回当前用户的名次（从大到小），并携带分数
     * @param redisTemplate
     * @param key
     * @param user
     * @return 数组中的角标
     */
    public static UserScore getUserScore(RedisTemplate redisTemplate, String key, String user) {
        UserScore userScore = new UserScore();
        userScore.setRank(redisTemplate.opsForZSet().reverseRank(key,user));
        userScore.setScore(redisTemplate.opsForZSet().score(key,user));
        return userScore;
    }
}
