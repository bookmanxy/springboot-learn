package cn.faceland.springbootkafkasimple.util;

import cn.faceland.springbootkafkasimple.callback.MyProducerCallback;
import cn.faceland.springbootkafkasimple.callback.handler.BasicKafkaCallback;
import cn.faceland.springbootkafkasimple.callback.handler.HandlerExampl1Callback;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author watermelon on 2019/10/29 15:28
 * @description
 */
@Slf4j
public class KafkaMqUtil {
    /**
     * 发送信息
     * @param kafkaTemplate
     * @param topic  主题
     * @param msg  消息内容
     */
    public static void sendMsg(KafkaTemplate kafkaTemplate,String topic, String msg){
        log.info("start to send msg,kafkaTemplate:{},topic:{},msg:{}",kafkaTemplate, topic, msg);

        kafkaTemplate.execute(new KafkaOperations.ProducerCallback() {
            @Override
            public Object doInKafka(Producer producer) {
                producer.send(new ProducerRecord<String, String>(topic, msg), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if(e == null){
                            log.info("done to send msg success,topic:{},offset:{},serializedValueSize:{},serializedKeySize:{},hasOffset:{}",
                                    recordMetadata.topic(),
                                    recordMetadata.offset(),
                                    recordMetadata.serializedValueSize(),
                                    recordMetadata.serializedKeySize(),
                                    recordMetadata.hasOffset()
                            );
                        }else{
                            e.printStackTrace();
                            log.info("done to send msg fail");
                        }
                    }
                });
                return null;
            }
        });
    }

    /**
     * 发送信息 - - 指定回调函数
     * @param kafkaTemplate
     * @param topic  主题
     */
    public static void sendMsgWithCallBack(KafkaTemplate kafkaTemplate,String topic, BasicKafkaCallback callback){

        kafkaTemplate.execute(new MyProducerCallback(topic,callback));
    }
}
