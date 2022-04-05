package com.rbgt.rabbitmq.controller;

import com.rbgt.rabbitmq.example.direct.RabbitDirectConfig;
import com.rbgt.rabbitmq.example.fanout.RabbitFanoutConfig;
import com.rbgt.rabbitmq.example.topic.RabbitTopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 俞春旺
 * @program: rbgt_rabbitmq_plug
 * @date 2022-04-05 19:37:24
 * @description: 描述
 */
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitmqController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String direct(){
        // 直连
        rabbitTemplate.convertAndSend("hello-queue", "我是直连消息，在该交换机下都会消费");

        // 给延迟队列发送消息
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECTNAME,"direct", "C2000009", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                // 设置延时时间
                message.getMessageProperties().setDelay(6000);
                return message;
            }
        });

        return "DIRECT SEND SUCCESS";
    }

    @GetMapping("/fanout")
    public String fanout(){

        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTCHANGE, null,"我是广播消息，在该交换机下都会消费");

        // 发送 - 延迟消息
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTCHANGE, null, "我是延迟消息", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                // 设置延时时间
                message.getMessageProperties().setDelay(6000);
                System.out.println("执行消息延迟发送");
                return message;
            }
        });
        return "FANOUT SEND SUCCESS";
    }

    @GetMapping("/topic")
    public String topic(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.news", "小米新闻", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                // 设置延时时间
                message.getMessageProperties().setDelay(6000);
                System.out.println("执行消息延迟发送");
                return message;
            }
        });
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.news", "华为新闻");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.phone", "小米手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.phone", "华为手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"phone.news", "手机新闻");
        return "TOPIC SEND SUCCESS";
    }

}
