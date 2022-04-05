package com.rbgt.rabbitmq.example.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author 俞春旺
 * FanoutExchange策略是把所有到达的消息转发给你所有与它绑定的Queue，在这种策略中，routingKey将不起作用。
 * 广播，交换机将消息发送到所有与之绑定的队列中去
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoRabbitMqApplicationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTCHANGE,null, "hello fanout");
    }
}

