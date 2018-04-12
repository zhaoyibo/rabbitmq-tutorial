package com.windmt.rabbitmq.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description: 工作队列
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-11 21:13
 **/
@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {

    @Bean
    public Queue queue() {
        return new Queue("work-queues");
    }

    /**
     * 定义两个消费者，并且给了他们不同的标识
     */
    @Profile("receiver")
    private static class ReceiverConfig {
        @Bean
        public Tut2Receiver receiver1() {
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            return new Tut2Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Tut2Sender sender() {
        return new Tut2Sender();
    }

}
