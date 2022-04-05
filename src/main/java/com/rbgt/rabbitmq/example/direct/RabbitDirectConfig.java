package com.rbgt.rabbitmq.example.direct;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 〈RabbitMQ的四种策略之Direct策略配置〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Configuration
public class RabbitDirectConfig {

    public final static String DIRECTNAME="admin-direct-yc";

    /**
     * 创建队列
     * @return
     */
    @Bean
    Queue queue(){
        return new Queue("hello-queue");
    }

    /**
     * 如果使用direct策略该配置可以省略
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        DirectExchange directExchange = new DirectExchange(DIRECTNAME, true, false,new LinkedHashMap<>());
        directExchange.setDelayed(true);
        return directExchange;
    }

    /**
     * 如果使用direct策略该配置可以省略
     * @return
     */
    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }


}

