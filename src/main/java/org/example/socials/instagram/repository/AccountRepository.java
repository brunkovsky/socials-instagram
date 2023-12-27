package org.example.socials.instagram.repository;

import org.example.socials.instagram.model.InstagramAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<InstagramAccount, String> {

    List<InstagramAccount> findInstagramAccountByExecutorScheduler(String message);

}
