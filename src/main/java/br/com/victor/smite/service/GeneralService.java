package br.com.victor.smite.service;

import br.com.victor.smite.entity.Player;
import br.com.victor.smite.client.HiRezSmiteApi;
import br.com.victor.smite.model.God;
import br.com.victor.smite.model.GodRecommendedItems;
import br.com.victor.smite.model.Item;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.utils.GodSkin;
import br.com.victor.smite.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService {
    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    @Autowired
    private PlayerRepository playerRepository;

    private String date;
    private String signature;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    public GeneralService(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository) {
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.playerRepository = playerRepository;
        this.date = Utils.getTimestampFormatted();
    }

    public List<Item> getAllItens(String username, Integer language) {

        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getitems", authKey, this.date);
        return hiRezSmiteApi.getAllItems(devId, this.signature, player.getLastSessionId(), this.date, language);

    }

    public List<God> getAllGods(String username, Integer language) {

        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgods", authKey, this.date);
        return hiRezSmiteApi.getAllGods(devId, this.signature, player.getLastSessionId(), this.date, language);

    }

    public List<GodSkin> getAllGodSkin(String username, int godId, Integer language) {

        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgodskins", authKey, this.date);
        return hiRezSmiteApi.getAllGodSkin(devId, this.signature, player.getLastSessionId(), this.date, godId, language);

    }

    public List<GodRecommendedItems> getGodRecommendedItems(String username, int godId, Integer language) {

        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getgodrecommendeditems", authKey, this.date);
        return hiRezSmiteApi.getGodRecommendedItems(devId, this.signature, player.getLastSessionId(), this.date, godId, language);

    }
}
