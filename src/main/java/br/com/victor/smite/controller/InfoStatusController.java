package br.com.victor.smite.controller;

import br.com.victor.smite.model.DataUsage;
import br.com.victor.smite.model.HiRezPatchVersion;
import br.com.victor.smite.model.HiRezServerStatus;
import br.com.victor.smite.service.InfoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/info/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoStatusController {

    @Autowired
    private InfoStatusService infoStatusService;

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
    public HiRezPatchVersion smitePatchVersion(Authentication authentication) {
        return this.infoStatusService.smitePatchVersion(authentication.getPrincipal().toString());
    }
}
