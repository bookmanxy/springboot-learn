package cn.faceland.springbootlearnredisgeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootLearnRedisGeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearnRedisGeoApplication.class, args);
    }

}
