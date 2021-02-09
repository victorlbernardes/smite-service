package br.com.victor.smite.controller;

import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.SmitePatchVersion;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import br.com.victor.smite.service.InfoStatusService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/info/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoStatusController {

    private InfoStatusService infoStatusService;

    public InfoStatusController(InfoStatusService infoStatusService) {
        this.infoStatusService = infoStatusService;
    }

    @GetMapping("/ping")
    public String ping() {
        return this.infoStatusService.ping();
    }

    @GetMapping("/test-session")
    public String testSession(Authentication authentication) {
        return this.infoStatusService.testSession(authentication.getPrincipal().toString());
    }

    @GetMapping("/data-usage")
    public List<DataUsage> dataUsage(Authentication authentication) {
        return this.infoStatusService.dataUsage(authentication.getPrincipal().toString());
    }

    @GetMapping("/hi-rez-server-status")
    public List<HiRezServerStatus> hiRezServerStatus(Authentication authentication) {
        return this.infoStatusService.hiRezServerStatus(authentication.getPrincipal().toString());
    }

    @GetMapping("/smite-patch-version")
    public SmitePatchVersion smitePatchVersion(Authentication authentication) {
        return this.infoStatusService.smitePatchVersion(authentication.getPrincipal().toString());
    }
}
