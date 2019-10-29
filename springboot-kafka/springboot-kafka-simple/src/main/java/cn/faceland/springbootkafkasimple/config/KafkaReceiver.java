package cn.faceland.springbootkafkasimple.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author watermelon on 2019/10/14 20:46
 * @description
 */
@Component
public class KafkaReceiver {
//    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    @KafkaListener(topics = "result_ok")
    public void listen0(ConsumerRecord<?, ?> record) throws IOException {
        String value = (String) record.value();
        System.out.println("我是topic小助手[result_ok]======我消费了=====" + value);
    }

//    @KafkaListener(topics = "result_ok")
//    public void listen1(ConsumerRecord<?, ?> record) throws IOException {
//        String value = (String) record.value();
//        System.out.println("我是topic小助手[result_ok - 01]======我消费了=====" + value);
//    }
//
//    @KafkaListener(topics = "result_ok2")
//    public void listen2(ConsumerRecord<?, ?> record) throws IOException {
//        String value = (String) record.value();
//        System.out.println("我是topic小助手[result_ok2]======我消费了=====" + value);
//    }
//    @KafkaListener(topics = "result_ok3")
//    public void listen3(ConsumerRecord<?, ?> record) throws IOException {
//        String value = (String) record.value();
//        System.out.println("我是topic小助手[result_ok3]======我消费了=====" + value);
//    }
}
