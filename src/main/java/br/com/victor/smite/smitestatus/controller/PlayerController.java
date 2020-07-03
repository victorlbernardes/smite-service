package br.com.victor.smite.smitestatus.controller;

import br.com.victor.smite.smitestatus.Entity.Player;
import br.com.victor.smite.smitestatus.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/smite/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("create-session")
    public Player createSession(Authentication authentication) {
        return this.playerService.createSession(authentication.getPrincipal().toString());
    }

}
