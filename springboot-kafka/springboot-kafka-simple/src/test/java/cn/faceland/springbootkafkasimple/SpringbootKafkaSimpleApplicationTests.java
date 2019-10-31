package cn.faceland.springbootkafkasimple;

import cn.faceland.springbootkafkasimple.callback.handler.HandlerExampl1Callback;
import cn.faceland.springbootkafkasimple.callback.handler.HandlerExampl2Callback;
import cn.faceland.springbootkafkasimple.util.KafkaMqUtil;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootKafkaSimpleApplicationTests {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 测试 testSendToKafka 方法
     * 推送消息值kafka
     */
    @Test
    void testSendToKafka() {
        for(int i = 0 ; i < 20 ; i ++){
            String topic = "result_ok2";
            String msg = "我是大英雄" + i;
            KafkaMqUtil.sendMsg(kafkaTemplate,topic, msg);
        }
    }

    @Test
    void testSendToKafkaWithCallBack() {
        String topic = "watermelon_1";
        String msg = "我是大英雄9990000";
        HandlerExampl1Callback handlerExampl1Callback = new HandlerExampl1Callback(System.currentTimeMillis(),msg);
        HandlerExampl2Callback handlerExampl2Callback = new HandlerExampl2Callback(System.currentTimeMillis(),msg);
        KafkaMqUtil.sendMsgWithCallBack(kafkaTemplate,topic, handlerExampl2Callback);

    }

}
