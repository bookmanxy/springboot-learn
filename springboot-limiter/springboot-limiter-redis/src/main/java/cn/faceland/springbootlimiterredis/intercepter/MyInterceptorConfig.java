package cn.faceland.springbootlimiterredis.intercepter;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author watermelon on 2018/11/9 15:13
 * @description 统一的aop拦截器 配置使用入口
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //接口拦截
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**");


        super.addInterceptors(registry);
    }

    @Bean
    public SessionInterceptor userInterceptor() {
        return new SessionInterceptor();
    }

}
