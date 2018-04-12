package com.windmt.rabbitmq.tut5;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description: topics
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-12 11:30
 **/
@Profile({"tut5", "topics"})
@Configuration
public class Tut5Config {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tut.topic");
    }

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
        public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("*.orange.*");
        }

        @Bean
        public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("*.*.rabbit");
        }

        @Bean
        public Binding binding2b(TopicExchange topic, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("lazy.#");
        }

        @Bean
        public Tut5Receiver receiver() {
            return new Tut5Receiver();
        }

    }

    @Profile("sender")
    @Bean
    public Tut5Sender sender() {
        return new Tut5Sender();
    }

}
