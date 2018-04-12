package com.windmt.rabbitmq.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @description: 消费者
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-11 21:16
 **/
@RabbitListener(queues = "work-queues")
public class Tut2Receiver {

    private int instance;

    public Tut2Receiver(int instance) {
        this.instance = instance;
    }

    /**
     *
     * @param in 从 queue 接收到的 msg
     * @throws InterruptedException
     */
    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance +
                " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + this.instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}