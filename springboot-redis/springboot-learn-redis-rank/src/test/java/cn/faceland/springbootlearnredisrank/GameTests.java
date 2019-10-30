package cn.faceland.springbootlearnredisrank;

import cn.faceland.springbootlearnredisrank.entity.UserScore;
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
public class GameTests {
    private final String RANG_KEY = "rang_key";
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 测试
     * 记录每个玩家的分数 + 对玩家的分数进行更新
     */
    @Test
    public void tryAddAndGet(){
        //先清空一波
        RedisUtil.removeAll(redisTemplate, RANG_KEY);

        //记录每个玩家的分数
        RedisUtil.add(redisTemplate, RANG_KEY,"玩家-刘备",100);

        Set<ZSetOperations.TypedTuple<String>> set2 = RedisUtil.rangeWithScores(redisTemplate, RANG_KEY, 0, -1);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }

        //对玩家的分数进行更新
        RedisUtil.add(redisTemplate, RANG_KEY,"玩家-刘备",145.5);

        set2 = RedisUtil.rangeWithScores(redisTemplate, RANG_KEY, 0, -1);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }
    }

    /**
     * 测试 查询每个玩家的分数和名次
     */
    @Test
    public void testRange(){
        System.out.println(RedisUtil.getUserScore(redisTemplate,RANG_KEY,"玩家-刘备"));
    }

    /**
     * 测试 名次查询排名前N名的玩家
     */
    @Test
    public void testRangeWithIndex(){
        UserScore userScore = RedisUtil.getUserScore(redisTemplate,RANG_KEY,"玩家-刘备");

        Set<ZSetOperations.TypedTuple<String>> set2 = RedisUtil.reverseRangeWithScores(redisTemplate, RANG_KEY, 0, userScore.getRank());
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }
    }
    /**
     * 测试 排在指定玩家前后M名的玩家
     */
    @Test
    public void testBeforeAndLast(){
        Integer num = 5;
        UserScore userScore = RedisUtil.getUserScore(redisTemplate,RANG_KEY,"玩家-刘备");
        long first = (userScore.getRank() - num) > 0 ? (userScore.getRank() - num ): 0L;
        Set<ZSetOperations.TypedTuple<String>> set2 = RedisUtil.reverseRangeWithScores(redisTemplate, RANG_KEY, first, userScore.getRank() + num);
        for(ZSetOperations.TypedTuple<String> item : set2){
            System.out.println(item.getValue() + item.getScore());
        }
    }
}
