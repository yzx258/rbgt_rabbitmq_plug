package com.rbgt.rabbitmq.example.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 〈RabbitMQ的Fanout策略的配置〉<br>
 * 〈简单理解类似于nginx负载均衡中nginx服务的角色〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Configuration
public class RabbitFanoutConfig {

    public final static String FANOUTCHANGE="admin-fanout-yc";

    @Bean
    Queue queueOne(){
        return new Queue("queue-one");
    }

    @Bean
    Queue queueTwo(){
        return new Queue("queue-two");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        FanoutExchange fanoutExchange = new FanoutExchange(FANOUTCHANGE, true, false,new LinkedHashMap<>());
        fanoutExchange.setDelayed(true);
        return fanoutExchange;
    }

    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo(){
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}

