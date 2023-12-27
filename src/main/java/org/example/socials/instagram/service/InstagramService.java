package org.example.socials.instagram.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.instagram.connector.InstagramConnector;
import org.example.socials.instagram.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InstagramService {

    private final AccountRepository accountRepository;
    private final InstagramConnector connector;


    public void fetch(String executorScheduler) {
        accountRepository.findInstagramAccountByExecutorScheduler(executorScheduler)
                .forEach(connector::loadData);
    }

    public void refreshAccessToken(String accountName) {
        log.info("refreshing access token : " + accountName);
    }

}
