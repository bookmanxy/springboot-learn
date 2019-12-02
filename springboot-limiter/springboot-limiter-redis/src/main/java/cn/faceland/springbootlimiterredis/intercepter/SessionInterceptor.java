package cn.faceland.springbootlimiterredis.intercepter;

import cn.faceland.springbootlimiterredis.util.ApiUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author watermelon on 2019/11/11 15:03
 * @description 自定义限流
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private final static String KEY = "limit_key";
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 进行拦截 范围：
     * 1: 全局（所有人，所有请求）
     * 2：指定接口(所有人，指定接口)   requestUri
     * 3：指定ip(针对某个人)  ip
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
//        log.debug("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();
        String ip = ApiUtil.getIpAddr(request);
        //允许最大的请求数
        long limitCount = 2;
        long count = redisTemplate.opsForValue().increment(KEY,1);
        if(count == 1){
            //过期单位：1秒
            redisTemplate.expire(KEY,1, TimeUnit.SECONDS);
        }
        boolean needRefuse = count > limitCount;
        if(needRefuse){
            JSONObject object = new JSONObject();
            object.put("success","");
            object.put("code","505");
            object.put("msg","接口被拦截");
            PrintWriter writer = response.getWriter();
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
            //当然也可以做成 放入队列，异步处理来实现削峰，视具体业务而定
            return false;
        }else{
            return true;
        }

    }

}
