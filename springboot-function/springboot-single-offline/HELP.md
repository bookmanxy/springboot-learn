第一种就是Springboot提供的actuator的功能，它可以执行shutdown, health, info等，默认情况下，actuator的shutdown是disable的，我们需要打开它。首先引入acturator的maven依赖。

　　<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
然后将shutdown节点打开，也将/actuator/shutdown暴露web访问也设置上，除了shutdown之外还有health, info的web访问都打开的话将management.endpoints.web.exposure.include=*就可以。将如下配置设置到application.properties里边。设置一下服务的端口号为3333。

server.port=3333
management.endpoint.shutdown.enabled=true
management.endpoints.web.exposure.include=shutdown
接下来，咱们创建一个springboot工程，然后设置一个bean对象，配置上PreDestroy方法。这样在停止的时候会打印语句。bean的整个生命周期分为创建、初始化、销毁，当最后关闭的时候会执行销毁操作。在销毁的方法中执行一条输出日志。

package com.hqs.springboot.shutdowndemo.bean;

import javax.annotation.PreDestroy;

/**
 * @author huangqingshi
 * @Date 2019-08-17
 */
public class TerminateBean {

    @PreDestroy
    public void preDestroy() {
        System.out.println("TerminalBean is destroyed");
    }

}
做一个configuration，然后提供一个获取bean的方法，这样该bean对象会被初始化。

package com.hqs.springboot.shutdowndemo.config;

import com.hqs.springboot.shutdowndemo.bean.TerminateBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangqingshi
 * @Date 2019-08-17
 */
@Configuration
public class ShutDownConfig {

    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }

}
在启动类里边输出一个启动日志，当工程启动的时候，会看到启动的输出，接下来咱们执行停止命令。

curl -X POST http://localhost:3333/actuator/shutdown
以下日志可以输出启动时的日志打印和停止时的日志打印，同时程序已经停止。是不是比较神奇。

