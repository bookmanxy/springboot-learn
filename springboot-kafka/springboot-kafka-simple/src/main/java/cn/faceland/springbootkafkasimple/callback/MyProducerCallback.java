package cn.faceland.springbootkafkasimple.callback;

import cn.faceland.springbootkafkasimple.callback.handler.BasicKafkaCallback;
import cn.faceland.springbootkafkasimple.callback.handler.HandlerExampl1Callback;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;

/**
 * @author watermelon on 2019/10/16 10:15
 * @description
 */
public class MyProducerCallback implements KafkaOperations.ProducerCallback {
    private static final Logger logger = LoggerFactory.getLogger(MyProducerCallback.class);
    private final String message;
    private final String topic;
    private final BasicKafkaCallback callback;

    public MyProducerCallback(String topic, BasicKafkaCallback callback) {
        this.topic = topic;
        this.message = callback.getMessage();
        this.callback = callback;
    }


    @Override
    public Object doInKafka(Producer producer) {
        producer.send(new ProducerRecord<String,String>(this.topic,this.message)
                ,callback);
        return null;
    }
}
