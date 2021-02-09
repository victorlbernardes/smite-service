package br.com.victor.smite.service;

import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.service.client.response.GodSkin;
import br.com.victor.smite.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    @Autowired
    private PlayerRepository playerRepository;

    private String date;
    private String signature;

    Logger logger = LoggerFactory.getLogger(GeneralServiceImpl.class);

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    public GeneralServiceImpl(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository) {
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.playerRepository = playerRepository;
        this.date = Utils.getTimestampFormatted();
    }

    public List<Item> getAllItens(String username, Integer language) {
        logger.info("getAllItens");
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getitems", authKey, this.date);
        System.out.println("/getitemsjson/" + devId +"/"+signature+"/"+player.getLastSessionId()+"/"+this.date+"/{language}");
        return hiRezSmiteApi.getAllItems(devId, this.signature, player.getLastSessionId(), this.date, language);

    }

    public List<God> getAllGods(String username, Integer language) {
        logger.info("getAllGods");
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgods", authKey, this.date);
        return hiRezSmiteApi.getAllGods(devId, this.signature, player.getLastSessionId(), this.date, language);

    }

    public List<GodSkin> getAllGodSkin(String username, int godId, Integer language) {
        logger.info("getAllGodSkin");
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgodskins", authKey, this.date);
        return hiRezSmiteApi.getAllGodSkin(devId, this.signature, player.getLastSessionId(), this.date, godId, language);

    }

    public List<GodRecommendedItems> getGodRecommendedItems(String username, int godId, Integer language) {
        logger.info("getGodRecommendedItems");
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgodrecommendeditems", authKey, this.date);
        return hiRezSmiteApi.getGodRecommendedItems(devId, this.signature, player.getLastSessionId(), this.date, godId, language);

    }

    public List<ProLeagueSeasonDetail> proLeagueSeason(String username) {
        logger.info("proLeagueSeason");
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getesportsproleaguedetails", authKey, this.date);
        return hiRezSmiteApi.proLeagueSeason(devId, this.signature, player.getLastSessionId(), this.date);
    }
}
