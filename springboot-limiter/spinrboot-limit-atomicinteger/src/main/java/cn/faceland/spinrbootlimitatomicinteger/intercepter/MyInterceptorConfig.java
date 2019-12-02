package cn.faceland.spinrbootlimitatomicinteger.intercepter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
