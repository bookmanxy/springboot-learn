package cn.faceland.springbootkafkakafkaadmin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 参考：https://www.jianshu.com/p/aa196f24f332
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootKafkaKafkaadminApplicationTests {

    @Autowired
    private AdminClient adminClient;

    /**
     * 测试 testSelectTopicInfo 方法
     * 获取topic信息
     */
    @Test
    void testSelectTopicInfo() throws ExecutionException, InterruptedException{
        String topicName = "result_ok";
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
        KafkaFuture<Map<String, TopicDescription>> all = result.all();
        result.all().get().forEach((k,v)->System.out.println("k: "+k+" ,v: "+v.toString()+"\n"));
        //k: result_ok ,v: (name=result_ok, internal=false, partitions=(partition=0, leader=127.0.0.1:9092 (id: 0 rack: null), replicas=127.0.0.1:9092 (id: 0 rack: null), isr=127.0.0.1:9092 (id: 0 rack: null)), authorizedOperations=null)

//        adminClient.listTopics();
    }

    /**
     * 主要功能包括：
     *
     * 创建Topic：createTopics(Collection<NewTopic> newTopics)
     * 删除Topic：deleteTopics(Collection<String> topics)
     * 显示所有Topic：listTopics()
     * 查询Topic：describeTopics(Collection<String> topicNames)
     * 查询集群信息：describeCluster()
     * 查询ACL信息：describeAcls(AclBindingFilter filter)
     * 创建ACL信息：createAcls(Collection<AclBinding> acls)
     * 删除ACL信息：deleteAcls(Collection<AclBindingFilter> filters)
     * 查询配置信息：describeConfigs(Collection<ConfigResource> resources)
     * 修改配置信息：alterConfigs(Map<ConfigResource, Config> configs)
     * 修改副本的日志目录：alterReplicaLogDirs(Map<TopicPartitionReplica, String> replicaAssignment)
     * 查询节点的日志目录信息：describeLogDirs(Collection<Integer> brokers)
     * 查询副本的日志目录信息：describeReplicaLogDirs(Collection<TopicPartitionReplica> replicas)
     * 增加分区：createPartitions(Map<String, NewPartitions> newPartitions)
     */

}
