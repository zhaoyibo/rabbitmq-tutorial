package com.windmt.rabbitmq.tut3;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description: 生产者
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-12 10:20
 **/
public class Tut3Sender {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private FanoutExchange fanout;

    private int dots = 0;
    private int count = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }
        builder.append(Integer.toString(++count));
        String message = builder.toString();
        template.convertAndSend(fanout.getName(), "", message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
