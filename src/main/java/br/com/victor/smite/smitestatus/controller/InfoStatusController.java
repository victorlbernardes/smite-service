package br.com.victor.smite.smitestatus.controller;

import br.com.victor.smite.smitestatus.service.InfoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoStatusController {

    @Autowired
    private InfoStatusService infoStatusService;

    @GetMapping("/ping")
    public String ping() {

        return this.infoStatusService.ping();
    }
}
