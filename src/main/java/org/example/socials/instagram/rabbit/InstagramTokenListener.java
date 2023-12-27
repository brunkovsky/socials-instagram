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
public class InstagramTokenListener {

    private final InstagramService instagramService;

    @RabbitListener(queues = "instagram-token-refresher")
    public void catchMessage(String message) {
        log.debug("catching token-refresh message: {}", message);
        instagramService.refreshAccessToken(message);
    }

}
