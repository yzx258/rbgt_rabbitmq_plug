package com.rbgt.rabbitmq.example.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈Fanout策略的发送〉
 *
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Component
public class FanoutReciver {

    @RabbitListener(queues = "queue-one")
    public void handle1(String msg){
        System.out.println("Fanout_Reciver:queue-one:"+msg);
    }

    @RabbitListener(queues = "queue-two")
    public void handle2(String msg){
        System.out.println("Fanout_Reciver:queue-two:"+msg);
    }
}
