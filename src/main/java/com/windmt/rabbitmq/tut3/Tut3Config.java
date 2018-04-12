package com.windmt.rabbitmq.tut3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description: 发布/订阅 模式
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-12 10:08
 **/
@Profile({"tut3", "pub-sub"})
@Configuration
public class Tut3Config {

    /**
     * 定义一个 Exchange
     *
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange queue() {
        return new FanoutExchange("tut.fanout");
    }

    /**
     * 消费者的一端的配置：queues、bindings
     */
    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            return new Tut3Receiver();
        }

    }

    @Bean
    @Profile("sender")
    public Tut3Sender sender() {
        return new Tut3Sender();
    }


}
