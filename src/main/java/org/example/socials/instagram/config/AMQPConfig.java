package org.example.socials.instagram.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

//    @Bean
//    DirectExchange storageExchange(@Value("${socials.rabbit.exchange.storage.name}") String exchangeName) {
//        return new DirectExchange(exchangeName);
//    }


    @Bean
    public DirectExchange instagramSocialsStorageExchange() {
            return new DirectExchange("instagram-socials-storage");
    }

    @Bean
    public Queue socialsStorageQueue() {
        return new Queue("socials-storage");
    }

    @Bean
    public Binding instagramStorageBinding(DirectExchange instagramSocialsStorageExchange, Queue socialsStorageQueue) {
        return BindingBuilder.bind(socialsStorageQueue)
                .to(instagramSocialsStorageExchange)
                .with("instagram-routing");
    }

}
