package org.example.socials.instagram.connector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.instagram.model.InstagramAccount;
import org.example.socials.instagram.model.SocialItem;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstagramConnector {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate(); // TODO: to use bean creation using restTemplateBuilder?


    @SneakyThrows
    public void loadData(InstagramAccount instagramAccount) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(generateUrl(instagramAccount), String.class);
        if (forEntity.getStatusCode().isError()) {
            log.error("ERROR");
            return;
        }
        List<SocialItem> socialItems = parseResult(forEntity.getBody(), instagramAccount);
        log.debug("Got {} items from instagram", socialItems.size());
        rabbitTemplate.convertAndSend("instagram-socials-storage",
                "instagram-routing",
                objectMapper.writeValueAsString(socialItems));
    }

    @SneakyThrows
    private List<SocialItem> parseResult(String body, InstagramAccount instagramAccount) {
        InstagramResponse instagramResponse = objectMapper.readValue(body, InstagramResponse.class);
        return instagramResponse.getData()
                .stream()
                .map(data -> new SocialItem(
                        data.getId(),
                        "INSTAGRAM",
                        instagramAccount.getAccountName(),
                        data.getPermalink(),
                        null,
                        null,
                        null,
                        data.getMediaUrl(),
                        null,
                        data.getTimestamp(),
                        instagramAccount.isApprovedByDefault()))
                .collect(Collectors.toList());
    }

    private String generateUrl(InstagramAccount instagramAccount) {
        return "https://graph.instagram.com/me/media?fields=caption,id,media_type,media_url,permalink,thumbnail_url,timestamp,username&access_token=" +
                instagramAccount.getAccessToken();
    }

    @Data
    static class InstagramResponse {

        private List<InstagramData> data;

        @Data
        static class InstagramData {
            String id;
            @JsonProperty("media_type")
            private String mediaType;
            @JsonProperty("media_url")
            private String mediaUrl;
            private String permalink;
            private Instant timestamp;
            private String username;
        }
    }

}
