package org.example.socials.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialItem {

    private String id;
    private String type;
    private String feedName;
    private String link;
    private String title;
    private String message;
    private String thumbnail;
    private String photoLink;
    private String videoLink;
    private Instant createdAt;
    private boolean approved;

}
