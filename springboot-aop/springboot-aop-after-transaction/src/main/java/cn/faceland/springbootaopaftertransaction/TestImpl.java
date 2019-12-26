package cn.faceland.springbootaopaftertransaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author watermelon on 2019/1/9 21:04
 * @description
 */
@Service
public class TestImpl  implements TestService {
//    @Autowired
//    private UserMapper userMapper;

    @Override
    public void test(String param) {
//        UserExample userExample = new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andUsernameEqualTo(param);
//        List<User> users = userMapper.selectByExample(userExample);

        //到数据库表中去查询
        List<String> users = new ArrayList<>();
        if(users.size() < 1){
            System.out.println("=======是我没用，我没找到====" + param);
        }else{
            System.out.println("=====我找到了！！======" + param);
        }
    }
}
