package cn.faceland.springbootlimitratelimiter.controller;
import cn.faceland.springbootlimitratelimiter.util.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 血糖
 * @author hf on 2019/9/30 17:23
 */
@Slf4j
@RestController
public class OkController {

    @GetMapping(value = "/ok")
    public JSONObject ok(){
        JSONObject object = new JSONObject();
        object.put("success",true);
        object.put("code",1);
        object.put("msg","ope success !");
        return object;
    }

    @GetMapping(value = "/test")
    public void test(){
        for(int i = 0 ; i < 10 ; i ++){
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(num + HttpRequest.get("http://127.0.0.1:8099/ok").body());
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
