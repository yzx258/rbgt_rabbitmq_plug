package com.rbgt.rabbitmq.example.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈RabbitMQ的Topic策略〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Configuration
public class RabbitTopicConfig {
    // 交换机名称
    public final static String TOPICNAME="admin-topic-yc";

    @Bean
    TopicExchange topicExchange(){
        TopicExchange topicExchange = new TopicExchange(TOPICNAME, true, false, new LinkedHashMap<>());
        topicExchange.setDelayed(true);
        return topicExchange;
    }
    @Bean
    Queue xiaomi(){
        return new Queue("xiaomi");
    }

    @Bean
    Queue huawei(){
        return new Queue("huawei");
    }

    @Bean
    Queue phone(){
        return new Queue("phone");
    }

    /**
     * routingKey以xiaomi开头的
     * @return
     */
    @Bean
    Binding bindingXiaomi(){
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }
    /**
     * routingKey以huawei开头的
     * @return
     */
    @Bean
    Binding bindingHuawei(){
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
    }
    /**
     * routingKey包含phone的
     * @return
     */
    @Bean
    Binding bindingPhone(){
        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
    }

}

