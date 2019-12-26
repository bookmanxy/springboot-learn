package cn.faceland.springbootaopaftertransaction;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author watermelon on 2019/1/9 21:04
 * @description
 */
@Service
public class CommonServiceImpl  implements CommonService {

//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private AfterCommitExecutor afterCommitExecutor;
    @Autowired
    private TestService testService;
    @Override
    public JSONObject testCommon(String param) {
        String userName = "张三";
//        User user = new User();
//        user.setUsername(userName);
//        userMapper.insert(user);

            //一些执行库表事务的操作

//        //事务提交后执行
        afterCommitExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //TODO 事务提交后功能  异步方法区数据库表中执行查询
                testService.test(userName);
            }
        });

        //放到异步
//        testService.test("我希望是是事务提交之后才执行的");

        System.out.println("我要睡一會，然後再執行");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //继续执行一些库表事务的操作，模拟当前事务还没有提交

//        user = new User();
//        user.setUsername("6");
//        userMapper.insert(user);

        System.out.println("============我执行完了，准备提交事务");
        return new JSONObject();
    }
}
