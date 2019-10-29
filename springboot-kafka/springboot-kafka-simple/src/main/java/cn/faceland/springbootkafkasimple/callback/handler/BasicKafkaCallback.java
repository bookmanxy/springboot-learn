package cn.faceland.springbootkafkasimple.callback.handler;

/**
 * @author watermelon on 2019/10/16 10:11
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class BasicKafkaCallback implements Callback {
    private static final Logger logger = LoggerFactory.getLogger(BasicKafkaCallback.class);
    private final long startTime;
    private final String message;

    public BasicKafkaCallback(long startTime, String message) {
        this.startTime = startTime;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
    /**
     * 生产者成功发送消息，收到kafka服务端发来的ACK确认消息后，会调用此回调函数
     * @param recordMetadata 生产者发送的消息的元数据，如果发送过程中出现异常，此参数为null
     * @param e 发送过程中出现的异常，如果发送成功，则此参数为null
     */
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (recordMetadata != null) {
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("==================");
            log.info("callback success,send message:{},topic:{},offset:{},serializedValueSize:{},serializedKeySize:{},hasOffset:{} in time:{}",
                    message,
                    recordMetadata.topic(),
                    recordMetadata.offset(),
                    recordMetadata.serializedValueSize(),
                    recordMetadata.serializedKeySize(),
                    recordMetadata.hasOffset(),
                    endTime
            );
        } else {
            e.printStackTrace();
        }
    }
}
