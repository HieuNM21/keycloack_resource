package com.bkav.keycloackresource.Controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "landing";
    }

    @GetMapping("/protected")
    public String protectedPage(OAuth2AuthenticationToken auth, Model model) {
        String username = auth.getPrincipal().getAttribute("preferred_username");
        String email = auth.getPrincipal().getAttribute("email");
        String fullName = auth.getPrincipal().getAttribute("name");

        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("fullName", fullName);

        return "home";
    }
}