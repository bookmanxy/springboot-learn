package cn.faceland.springbootdataserver.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author cjie
 * @Date 2018/12/8 0008.
 */
@Slf4j
@Configuration
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        try{
            Object object = getApplicationContext().getBean(name);
            return object;
        }catch (Exception e){
            LogUtil.fatalStackTrace(log, e);
            return null;
        }
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    // 获取当前环境
    public static Stream<String> getActiveProfilesStream() {
        return Arrays.stream(getActiveProfiles());
    }
    // 获取当前环境
    public static List<String> getActiveProfilesList() {
        return getActiveProfilesStream().collect(Collectors.toList());
    }
    // 获取当前环境
    public static String[] getActiveProfiles() {
        return getApplicationContext().getEnvironment().getActiveProfiles();
    }
    public static boolean isDev() {
        AtomicBoolean isDev = new AtomicBoolean(false);
        getActiveProfilesStream().forEach(s->{
            String dev = "dev";
            if (StringUtils.contains(s, dev)) {
                isDev.set(true);
            }
        });
        return isDev.get();
    }

    public static boolean isProd() {
        AtomicBoolean isProd = new AtomicBoolean(false);
        getActiveProfilesStream().forEach(s->{
            String prod = "prod";
            if (StringUtils.contains(s, prod)) {
                isProd.set(true);
            }
        });
        return isProd.get();
    }
}
