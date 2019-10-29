package cn.faceland.springbootkafkakafkaadmin.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
* 参考：https://blog.csdn.net/yjt520557/article/details/90719025
 */
@Configuration
public class KafkaInitialConfiguration {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapservers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        //配置Kafka实例的连接地址
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        KafkaAdmin admin = new KafkaAdmin(props);
        return admin;
    }

    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }

    /**
     * 创建TopicName为topic.quick.initial的Topic并设置分区数为8以及副本数为1
     * 项目运行时自动创建topic
     * @return
     */
    @Bean
    public NewTopic initialTopic() {
        //第一个是参数是topic名字，第二个参数是分区个数，第三个是topic的复制因子个数
        return new NewTopic("watermelon_1",8, (short) 1 );
    }
}