package com.ryokugyoku.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class TopPageController {

    @GetMapping("/")
    public String index(Model model,
                        @AuthenticationPrincipal OidcUser oidcUser) {
        // ログインしていない場合は空 Map を渡す
        Map<String, Object> googleUser =
                oidcUser != null ? oidcUser.getClaims() : Collections.emptyMap();

        model.addAttribute("googleUser", googleUser);

        return "index";
    }
}
