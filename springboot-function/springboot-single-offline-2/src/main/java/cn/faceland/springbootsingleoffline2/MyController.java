package cn.faceland.springbootsingleoffline2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author watermelon
 * @Date 2020-01-09
 * @Description   发送请求：get   http://localhost:8080/do
 */
@RestController
public class MyController {
    @Autowired
    private ConfigurableApplicationContext ctx;
    @GetMapping(value = "/do")
    public String test(){

        Integer seconds = 10;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(seconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ctx.close();
            }
        }).start();
        return "触发成功," + seconds+ "秒后系统关闭";
    }
}
