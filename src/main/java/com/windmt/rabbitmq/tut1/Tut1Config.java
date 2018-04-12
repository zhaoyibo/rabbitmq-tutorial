package com.windmt.rabbitmq.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description:
 * @author: zhaoyibochn@gmail.com
 * @create: 2018-04-11 20:28
 **/
@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    /**
     * 定义一个 queue，名字为 hello-world
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("hello-world");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }


}
