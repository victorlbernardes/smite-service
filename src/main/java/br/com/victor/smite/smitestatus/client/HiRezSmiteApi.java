package br.com.victor.smite.smitestatus.client;

import br.com.victor.smite.smitestatus.model.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hirez-smite-api", url = "${hirez-smite-api.url}")
public interface HiRezSmiteApi {

    @GetMapping("/pingJson")
    String ping();

    @GetMapping("/createsessionJson/{devId}/{signature}/{timestamp}")
    Session createSession(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("timestamp") String timestamp );

}
