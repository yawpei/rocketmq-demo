package com.rocketmq.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 生产者，同步
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //生产者组
        DefaultMQProducer producer = new
                DefaultMQProducer("sync-producer-group");
        // 指定路由地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            //创建消息实例，指定主题、标签和消息体
            Message msg = new Message("TopicTest" ,
                    "TagA" ,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //发送消息
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        //关闭生产者
        producer.shutdown();
    }
}