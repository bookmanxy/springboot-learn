package cn.faceland.spinrbootlimitatomicinteger.intercepter;

import cn.faceland.spinrbootlimitatomicinteger.util.ApiUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author watermelon on 2019/11/11 15:03
 * @description 自定义限流
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {
    //设定计数器
    private static final AtomicInteger atomicInteger = new AtomicInteger();
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

        int count = atomicInteger.incrementAndGet();
        System.out.println(String.format("限定最大数量:[ %d ];当前消耗第：[ %d ] 个", limitCount, count));

        //因为atomicInteger是从1开始的
        boolean needRefuse = count > (limitCount + 1);
        if(needRefuse){

            //限流的话加上这个回退，如果是秒杀的就不必了
//            atomicInteger.decrementAndGet();
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
