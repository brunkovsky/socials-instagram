package org.example.socials.instagram.rabbit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.instagram.service.InstagramService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableRabbit
@AllArgsConstructor
public class InstagramExecutorListener {

    private final InstagramService instagramService;

    @RabbitListener(queues = "instagram-executor")
    public void catchMessage(String message) {
        log.debug("catching executing message: {}", message);
        new Thread(() -> instagramService.fetch(message)).start();

//        Executors.newSingleThreadExecutor().submit(() -> {
//            log.debug("catching executing message: {}", message);
//            facebookService.fetch(message);
//        });

    }

}
