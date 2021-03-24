package br.com.victor.smite.service;

import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.service.client.response.GodSkin;
import br.com.victor.smite.utils.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GeneralServiceImpl implements GeneralService{
    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    @Autowired
    private AuthenticationService authenticationService;

    private String date;
    private String signature;

    Logger logger = LoggerFactory.getLogger(GeneralServiceImpl.class);

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    public GeneralServiceImpl(HiRezSmiteApi hiRezSmiteApi, AuthenticationService authenticationService) {
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.authenticationService = authenticationService;
        this.date = HashGenerator.getTimestampFormatted();
    }

    private String getLastSessionId(){
        return this.authenticationService.createSession().getSessionId();
    }

    public List<Item> getAllItens(Integer language) {
        logger.info("getAllItens");
        this.signature = HashGenerator.getHash(devId, "getitems", authKey, this.date);
        return hiRezSmiteApi.getAllItems(devId, this.signature, this.getLastSessionId(), this.date, language);

    }

    public List<God> getAllGods(Integer language) {
        logger.info("getAllGods");
        this.signature = HashGenerator.getHash(devId, "getgods", authKey, this.date);
        return hiRezSmiteApi.getAllGods(devId, this.signature, this.getLastSessionId(), this.date, language);

    }

    public List<GodSkin> getAllGodSkin(int godId, Integer language) {
        logger.info("getAllGodSkin");
        this.signature = HashGenerator.getHash(devId, "getgodskins", authKey, this.date);
        return hiRezSmiteApi.getAllGodSkin(devId, this.signature, this.getLastSessionId(), this.date, godId, language);

    }

    public List<GodRecommendedItems> getGodRecommendedItems(int godId, Integer language) {
        logger.info("getGodRecommendedItems");
        this.signature = HashGenerator.getHash(devId, "getgodrecommendeditems", authKey, this.date);
        return hiRezSmiteApi.getGodRecommendedItems(devId, this.signature, this.getLastSessionId(), this.date, godId, language);

    }

    public List<ProLeagueSeasonDetail> proLeagueSeason() {
        logger.info("proLeagueSeason");
        this.signature = HashGenerator.getHash(devId, "getesportsproleaguedetails", authKey, this.date);
        return hiRezSmiteApi.proLeagueSeason(devId, this.signature, this.getLastSessionId(), this.date);
    }
}
