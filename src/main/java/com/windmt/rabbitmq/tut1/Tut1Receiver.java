package com.windmt.rabbitmq.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @description: 消费者
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-11 20:30
 **/
@RabbitListener(queues = "hello-world")
public class Tut1Receiver {

    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }

}
