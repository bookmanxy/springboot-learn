package cn.faceland.springbootlearnredisrank;

import cn.faceland.springbootlearnredisrank.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLearnRedisRankApplicationTests {
    private final String RANG_KEY = "rang_key";
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 测试 tryAddAndGet 方法
     * 新增（更新）数据和获取并打印
     */
    @Test
    public void tryAddAndGet(){
        RedisUtil.add(redisTemplate, RANG_KEY,"刘备",130);
        RedisUtil.add(redisTemplate, RANG_KEY,"张飞",55.89);
        RedisUtil.add(redisTemplate, RANG_KEY,"关羽",120);
        RedisUtil.add(redisTemplate, RANG_KEY,"曹操",59.6);
        RedisUtil.add(redisTemplate, RANG_KEY,"孙权",28);

        Set<ZSetOperations.TypedTuple<String>> set2 = RedisUtil.rangeWithScores(redisTemplate, RANG_KEY, 0, -1);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }
    }

    /**
     * 测试 testRange 方法  不带分数
     * 获取排行榜（score从大到小排列）前5 和 后5
     */
    @Test
    public void testRange(){
        Set<String> set2 = RedisUtil.range(redisTemplate, RANG_KEY, 0, 5);
        System.out.println(set2);

        set2 = RedisUtil.reverseRange(redisTemplate, RANG_KEY, 0, 5);
        System.out.println(set2);
    }

    /**
     * 测试 testRangeWithScores 方法  带分数
     * 获取排行榜（score从大到小排列）前5 和 后5
     */
    @Test
    public void testRangeWithScores(){
        Set<ZSetOperations.TypedTuple<String>> set2 = RedisUtil.reverseRangeWithScores(redisTemplate, RANG_KEY, 0, 5);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }

        System.out.println("=======分割线=======");

        set2 = RedisUtil.rangeWithScores(redisTemplate, RANG_KEY, 0, 5);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }
    }
    /**
     * 测试 testSize 方法
     * 获取获取排行榜总长度
     */
    @Test
    public void testSize(){
        System.out.println("排行榜数量：" + RedisUtil.size(redisTemplate, RANG_KEY));
    }
}
