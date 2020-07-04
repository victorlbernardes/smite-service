package br.com.victor.smite.controller;

import br.com.victor.smite.model.DataUsage;
import br.com.victor.smite.model.HiRezPatchVersion;
import br.com.victor.smite.model.HiRezServerStatus;
import br.com.victor.smite.service.InfoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public String testSession(@RequestParam(value = "sessionId") String sessionId) {
        return this.infoStatusService.testSession(sessionId);
    }

    @GetMapping("/data-usage")
    public List<DataUsage> dataUsage(@RequestParam(value = "sessionId") String sessionId) {
        return this.infoStatusService.dataUsage(sessionId);
    }

    @GetMapping("/hi-rez-server-status")
    public List<HiRezServerStatus> hiRezServerStatus(@RequestParam(value = "sessionId") String sessionId) {
        return this.infoStatusService.hiRezServerStatus(sessionId);
    }

    @GetMapping("/smite-patch-version")
    public HiRezPatchVersion smitePatchVersion(@RequestParam(value = "sessionId") String sessionId) {
        return this.infoStatusService.smitePatchVersion(sessionId);
    }
}
