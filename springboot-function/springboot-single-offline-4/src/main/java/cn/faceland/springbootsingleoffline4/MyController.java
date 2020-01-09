package cn.faceland.springbootsingleoffline4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
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

        exitApplication(ctx);

        return "";
    }

    private void exitApplication(ConfigurableApplicationContext context) {
        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);

        System.exit(exitCode);
    }
}
