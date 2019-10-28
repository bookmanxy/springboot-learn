package cn.faceland.springbootlearnredislock;

import cn.faceland.springbootlearnredislock.util.RedisTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLearnRedisLockApplicationTests {

    private final String LOCK_KEY = "face_lock";

    /**
     * 尝试加锁
     * 满足互斥性、非死锁、自解性、容错性（集群再演示）
     */
    @Autowired
    private JedisPool jedisPool;
    @Test
    public void tryGetLockTest(){
        String idNumber = "123456789";
        Jedis jedis = jedisPool.getResource();
        //尝试加锁
        RedisTool.tryGetDistributedLock(jedis,LOCK_KEY,idNumber,3000);
        //打印当前持有者
        System.out.println("当前持有锁的idNumber是：" + jedis.get(LOCK_KEY));

        //释放锁
        RedisTool.tryReleaseLock(jedis, LOCK_KEY, idNumber);
        //打印当前持有者
        System.out.println("当前持有锁的idNumber是：" + jedis.get(LOCK_KEY));
    }
}
