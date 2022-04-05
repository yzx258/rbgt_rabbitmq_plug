package com.rbgt.rabbitmq.example.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 俞春旺
 * @program: rbgt_rabbitmq_plug
 * @date 2022-04-04 13:07:28
 * @description: Direct策略的接收
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "hello-queue")
    public void handle1(String msg){
        System.out.println("DirectReceiver:"+msg);
    }

}
