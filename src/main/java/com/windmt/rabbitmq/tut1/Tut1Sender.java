package com.windmt.rabbitmq.tut1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @description: 生产者
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-11 20:32
 **/
public class Tut1Sender {

    /**
     * AmqpTemplate 的默认实现就是 RabbitTemplate
     */
    @Autowired
    private AmqpTemplate template;

    @Autowired
    private Queue queue;

    /**
     * 用定时任务来模拟生产者定时发送消息
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello World!" + new Date();
        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
