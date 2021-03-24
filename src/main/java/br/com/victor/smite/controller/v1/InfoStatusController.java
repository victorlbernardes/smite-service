package br.com.victor.smite.controller.v1;

import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.SmitePatchVersion;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import br.com.victor.smite.service.InfoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/smite/info/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoStatusController {

    @Autowired
    private InfoStatusService infoStatusService;

    @GetMapping("/ping")
    public String ping() {
        return this.infoStatusService.ping();
    }

    @GetMapping("/test-session")
    public String testSession(Authentication authentication) {
        return this.infoStatusService.testSession();
    }

    @GetMapping("/data-usage")
    public List<DataUsage> dataUsage(Authentication authentication) {
        return this.infoStatusService.dataUsage();
    }

    @GetMapping("/hi-rez-server-status")
    public List<HiRezServerStatus> hiRezServerStatus(Authentication authentication) {
        return this.infoStatusService.hiRezServerStatus();
    }

    @GetMapping("/smite-patch-version")
    public SmitePatchVersion smitePatchVersion(Authentication authentication) {
        return this.infoStatusService.smitePatchVersion();
    }
}
