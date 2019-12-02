package cn.faceland.springbootlimitratelimiter;

import cn.faceland.springbootlimitratelimiter.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLimitRatelimiterApplicationTests {

    /**
     * 很奇怪，会少一个流量
     */
    @Test
    void contextLoads() {
        for(int i = 0 ; i < 10 ; i ++){
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(num + HttpRequest.get("http://127.0.0.1:8099/ok").body());
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
