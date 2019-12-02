package cn.faceland.springbootlimiterredis;

import cn.faceland.springbootlimiterredis.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLimiterRedisApplicationTests {

    /**
     * 预期结果：
     * 9{"msg":"?????","code":"505","success":""}
     * 1{"msg":"?????","code":"505","success":""}
     * 3{"msg":"?????","code":"505","success":""}
     * 2{"msg":"?????","code":"505","success":""}
     * 8{"msg":"?????","code":"505","success":""}
     * 6{"msg":"?????","code":"505","success":""}
     * 0{"msg":"?????","code":"505","success":""}
     * 7{"msg":"?????","code":"505","success":""}
     * 4<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><div id='created'>Mon Nov 11 17:01:36 CST 2019</div><div>There was an unexpected error (type=Not Found, status=404).</div><div>No message available</div></body></html>
     * 5<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><div id='created'>Mon Nov 11 17:01:36 CST 2019</div><div>There was an unexpected error (type=Not Found, status=404).</div><div>No message available</div></body></html>
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
