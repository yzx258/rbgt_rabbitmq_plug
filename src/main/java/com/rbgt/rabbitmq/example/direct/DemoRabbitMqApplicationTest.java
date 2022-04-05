package com.rbgt.rabbitmq.example.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 俞春旺
 * @program: rbgt_rabbitmq_plug
 * @date 2022-04-04 13:08:07
 * @description: DirectExchange策略是将消息对接绑定到一个DirectExchange上，当一条消息到达DirectExchange时，会将消息发送到对应的Queue队列中
 * 定向，交换机按照指定的Routing Key发送到匹配的队列中去
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
}
