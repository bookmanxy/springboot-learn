package cn.faceland.springbootkafkasimple.callback.handler;

/**
 * @author watermelon on 2019/10/16 10:11
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class HandlerExampl2Callback extends BasicKafkaCallback {

    public HandlerExampl2Callback(long startTime, String message) {
        super(startTime, message);
    }

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//        super.onCompletion(recordMetadata, e);
        System.out.println("====我是===HandlerExampl2Callback========");
    }
}
