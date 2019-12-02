package cn.faceland.springbootrequest.request;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author watermelon
 * @Date 2019-12-02
 * @Description
 */
public class ReqExample {

    public static void main(String[] args) {
//        String str = "{\"dateCode\":\"20191125\",\"steps\":[{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":3},{\"hourStepNumber\":30},{\"hourStepNumber\":71},{\"hourStepNumber\":253},{\"hourStepNumber\":490},{\"hourStepNumber\":38},{\"hourStepNumber\":23},{\"hourStepNumber\":12},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":2},{\"hourStepNumber\":3},{\"hourStepNumber\":4},{\"hourStepNumber\":4},{\"hourStepNumber\":4},{\"hourStepNumber\":53},{\"hourStepNumber\":41},{\"hourStepNumber\":9},{\"hourStepNumber\":63},{\"hourStepNumber\":2},{\"hourStepNumber\":17},{\"hourStepNumber\":0},{\"hourStepNumber\":1},{\"hourStepNumber\":1},{\"hourStepNumber\":1}],\"userId\":\"3630117\"}";
        String str = "{\n" +
                "\t\"pageNo\": 1,\n" +
                "\t\"pageSize\": 10,\n" +
                "\t\"tagNum\": 0\n" +
                "}";
        JSONObject obj = JSONObject.parseObject("{\"dateCode\":\"20191125\",\"steps\":[{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":3},{\"hourStepNumber\":30},{\"hourStepNumber\":71},{\"hourStepNumber\":253},{\"hourStepNumber\":490},{\"hourStepNumber\":38},{\"hourStepNumber\":23},{\"hourStepNumber\":12},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":0},{\"hourStepNumber\":2},{\"hourStepNumber\":3},{\"hourStepNumber\":4},{\"hourStepNumber\":4},{\"hourStepNumber\":4},{\"hourStepNumber\":53},{\"hourStepNumber\":41},{\"hourStepNumber\":9},{\"hourStepNumber\":63},{\"hourStepNumber\":2},{\"hourStepNumber\":17},{\"hourStepNumber\":0},{\"hourStepNumber\":1},{\"hourStepNumber\":1},{\"hourStepNumber\":1}],\"userId\":\"3630117\"}");
        List<String> userInfos = new ArrayList<>();
        userInfos.add("user_id=3721966&timestamp=1575256492&sign=d92c7f6d0689e12949d1e1d65cbf19b2");
        userInfos.add("user_id=3312753&timestamp=1575256849&sign=8982251180bb66b698a835101eb3a7bf");
        userInfos.add("user_id=1175332&timestamp=1575256863&sign=b866c3485fb98654b5bc0f41af54c829");
        userInfos.add("user_id=1872757&timestamp=1575256878&sign=3bd5f5112d0af17be48aca23fdfb61c6");

        userInfos.add("user_id=1175332&timestamp=1575256895&sign=7cde599533f19691a4e0104a5261d776");
        userInfos.add("user_id=1175332&timestamp=1575256907&sign=193cf2c6559dd992ed6888f68e7bf948");
        userInfos.add("user_id=1000823&timestamp=1575256921&sign=f0c71b6a2d315bb620720c3bc695fb78");
        userInfos.add("user_id=3630117&timestamp=1575256959&sign=db0b068215f8ce07a6590847bafe5869");

        String addr = "https://xx.xxx.cn";
        for(int o = 0 ; o < 60 ; o ++){
            for(int i = 0 ; i < 5 ; i++){
                String userInfo = userInfos.get(i % userInfos.size());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //                    String reslut = HttpRequest.post("https://api.bianla.cn/api/health_step/uploadHealthStepHour.action?user_id=3630117&timestamp=1575190645&sign=641f723ea9d74e5dce0751f5d0143185").header(HEADER_CONTENT_TYPE,"application/json").send(str).body();
//                    String reslut = HttpRequest.post(addr + "/tang/userInfo/loadKeepTangUsersList?" + userInfo).header(HEADER_CONTENT_TYPE,"application/json").send(str).body();
//                    String reslut = HttpRequest.post(addr + "/tang/userInfo/loadKeepYoungUsersList?" + userInfo).header(HEADER_CONTENT_TYPE,"application/json").send(str).body();
                        String reslut = HttpRequest.get(addr + "/tang/homepage/loadDealerAndDoctor?" + userInfo).body();
                        System.out.println(reslut);
                    }
                }).start();

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
