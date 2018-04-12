package com.windmt.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhaoyibochn@gmail.com
 */
@SpringBootApplication
@EnableScheduling
public class RabbitmqTutorialApplication {

    /**
     * 注意设置成非web环境，否则的话，netty会默认监听8080端口，
     * 同时运行就会接口冲突导致启动失败（当然，也可以直接在用参数绑定不同的端口）
     *
     * @param args
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RabbitmqTutorialApplication.class)
                // 设置成非 web 环境
                .web(WebApplicationType.NONE)
                .run(args);
    }

    /**
     * 默认打印 usage
     *
     * @return
     */
    @Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        return arg0 -> {
            System.out.println("This app uses Spring Profiles to control its behavior.\n");
            System.out.println("Sample usage: java -jar target/rabbitmq-tutorial-0.0.1-SNAPSHOT.jar --spring.profiles.active=hello-world,sender");
        };
    }

    /**
     * 为了让主线程不退出
     *
     * @return
     */
    @Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        return new RabbitTutorialRunner();
    }

}
