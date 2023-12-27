package org.example.socials.instagram.controller;

import lombok.AllArgsConstructor;
import org.example.socials.instagram.model.InstagramAccount;
import org.example.socials.instagram.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/socials/instagram")
@AllArgsConstructor
public class UiController {

    private final AccountRepository accountRepository;

    @GetMapping("/ui")
    public String search(Model model) {
        model.addAttribute("instagramAccounts", accountRepository.findAll());
        return "accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") String id) {
        accountRepository.deleteById(id);
        return "redirect:/api/socials/instagram/ui";
    }

    @PostMapping("/addAccount")
    public String addAccount(InstagramAccount instagramAccount) {
        accountRepository.save(instagramAccount);
        return "redirect:/api/socials/instagram/ui";
    }

}
