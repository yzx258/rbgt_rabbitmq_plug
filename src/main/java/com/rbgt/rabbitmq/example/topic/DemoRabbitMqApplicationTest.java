package com.rbgt.rabbitmq.example.topic;

import com.rbgt.rabbitmq.example.fanout.RabbitFanoutConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 俞春旺
 * Topic策略中，Queue通过routingKey绑定在TopicExchange策略上，当消息到达TopicExchange后，根据routingKey将消息路由到一个或者多个Queue队列上，比较灵活
 * 通配符，与Direct大致相同，不同在于Routing Key可以根据通配符进行匹配
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoRabbitMqApplicationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queue", "hello direct");
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTCHANGE,null, "hello fanout");
    }
    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.news", "小米新闻");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.news", "华为新闻");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.phone", "小米手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.phone", "华为手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"phone.news", "手机新闻");

    }

}

