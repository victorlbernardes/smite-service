package br.com.victor.smite.controller;

import br.com.victor.smite.service.client.response.Session;
import br.com.victor.smite.service.PlayerService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/smite", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/create-session")
    public Session createSession(Authentication authentication) {
        return this.playerService.createSession(authentication.getPrincipal().toString());
    }

}
