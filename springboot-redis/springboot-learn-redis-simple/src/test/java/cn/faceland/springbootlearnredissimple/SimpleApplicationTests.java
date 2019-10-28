package cn.faceland.springbootlearnredissimple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleApplicationTests {
    /**
     * 通用的简单key
     */
    private final String SIMPLE_KEY = "faceland_key";
    /**
     * 使用 StringRedisTemplate 管理字符串类型数据
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void stringRedisTemplateTest(){
        stringRedisTemplate.opsForValue().set(SIMPLE_KEY, "jack");
        System.out.println(stringRedisTemplate.opsForValue().get(SIMPLE_KEY));
    }


    /**
     * 使用 RedisTemplate 管理复杂对象（字符串也可以）
     *
     * ps：两者的数据是不共通的；
     * 也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，RedisTemplate只能管理RedisTemplate中的数据。
     * 因为他们使用的序列化类:
     * RedisTemplate使用的是JdkSerializationRedisSerializer    存入数据会将数据先序列化成字节数组然后在存入Redis数据库。
     * StringRedisTemplate使用的是StringRedisSerializer
     *
     * 参考：https://www.cnblogs.com/MyYJ/p/10778874.html
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void redisTemplateTest(){
        //普通的键值对存储，可自定义类型
        ValueOperations<String,String> valueOperations =  redisTemplate.opsForValue();
        System.out.println(valueOperations.get(SIMPLE_KEY));
        valueOperations.set(SIMPLE_KEY, "jack");
        System.out.println(valueOperations.get(SIMPLE_KEY));
//        redisTemplate.delete(SIMPLE_KEY);

//        HashOperations hashOperations = redisTemplate.opsForHash();
    }


}
