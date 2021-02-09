package br.com.victor.smite.service.client;

import br.com.victor.smite.service.client.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hirez-smite-api", url = "${hirez-smite-api.url}")
public interface HiRezSmiteApi {

    /* Conexao, Status do servico e outras informacoes relativas a API da HiRez*/
    @GetMapping("/pingJson")
    String ping();

    @GetMapping("/testsessionjson/{devId}/{signature}/{sessionId}/{timestamp}")
    String testSession(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp);

    @GetMapping("/getdatausedjson/{devId}/{signature}/{sessionId}/{timestamp}")
    List<DataUsage> dataUsage(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp);

    @GetMapping("/gethirezserverstatusjson/{devId}/{signature}/{sessionId}/{timestamp}")
    List<HiRezServerStatus> hiRezServerStatus(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp);

    @GetMapping("/getpatchinfojson/{devId}/{signature}/{sessionId}/{timestamp}")
    SmitePatchVersion smitePatchVersion(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp);

    @GetMapping("/createsessionJson/{devId}/{signature}/{timestamp}")
    Session createSession(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("timestamp") String timestamp );

    /* Endpoints para recuperar informacoes gerais do jogo*/
    @GetMapping("/getitemsjson/{devId}/{signature}/{sessionId}/{timestamp}/{language}")
    List<Item> getAllItems(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp, @PathVariable("language") Integer language );

    @GetMapping("/getgodsjson/{devId}/{signature}/{sessionId}/{timestamp}/{language}")
    List<God> getAllGods(@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp, @PathVariable("language") Integer language );

    @GetMapping("/getgodskinsjson/{devId}/{signature}/{sessionId}/{timestamp}/{godId}/{language}")
    List<GodSkin> getAllGodSkin (@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp, @PathVariable("godId") Integer godId, @PathVariable("language") Integer language);

    @GetMapping("/getgodrecommendeditemsjson/{devId}/{signature}/{sessionId}/{timestamp}/{godId}/{language}")
    List<GodRecommendedItems> getGodRecommendedItems (@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp, @PathVariable("godId") Integer godId, @PathVariable("language") Integer language);

    @GetMapping("/getesportsproleaguedetailsjson/{devId}/{signature}/{sessionId}/{timestamp}")
    List<ProLeagueSeasonDetail> proLeagueSeason (@PathVariable("devId") String devId, @PathVariable("signature") String signature, @PathVariable("sessionId") String sessionId, @PathVariable("timestamp") String timestamp);

}
