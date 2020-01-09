package cn.faceland.springbootsingleoffline2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringbootSingleOffline2Application {

    public static void main(String[] args) {
//        SpringApplication.run(SpringbootSingleOffline2Application.class, args);

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootSingleOffline2Application.class, args);

    }

}
