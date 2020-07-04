package br.com.victor.smite.service;

import br.com.victor.smite.entity.Player;
import br.com.victor.smite.client.HiRezSmiteApi;
import br.com.victor.smite.model.Item;
import br.com.victor.smite.repository.PlayerRepository;
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
}
