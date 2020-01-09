package cn.faceland.springbootsingleoffline;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author watermelon
 * @Date 2020-01-09
 * @Description
 */
@Component
public class TerminateBean {
    @PreDestroy
    public void preDestroy() {
        System.out.println("TerminalBean is destroyed");
    }
}
