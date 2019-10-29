package cn.faceland.springbootkafkasimple.callback.handler;

/**
 * @author watermelon on 2019/10/16 10:11
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class HandlerExampl1Callback extends BasicKafkaCallback {

    public HandlerExampl1Callback(long startTime, String message) {
        super(startTime, message);
    }

}
