package org.example.socials.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstagramAccount {

    @Id
    @Column(length = 128)
    private String accountName;
    private String accessToken;
    private boolean valid;
    private String executorScheduler;
    private String accessTokenScheduler;
    private boolean isApprovedByDefault;

}
