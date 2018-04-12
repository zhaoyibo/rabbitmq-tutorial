package com.windmt.rabbitmq.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @description:
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-12 11:37
 **/
public class Tut5Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1}")
    public void receiver1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2}")
    public void receiver2(String in) throws InterruptedException {
        receive(in, 2);
    }

    private void receive(String in, int instance) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + instance + " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + instance + " [x] Done in "
                + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char c : in.toCharArray()) {
            if (c == '.') {
                Thread.sleep(1000);
            }
        }
    }

}
