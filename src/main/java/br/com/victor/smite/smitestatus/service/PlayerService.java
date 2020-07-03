package br.com.victor.smite.smitestatus.service;

import br.com.victor.smite.smitestatus.Entity.Player;
import br.com.victor.smite.smitestatus.client.HiRezSmiteApi;
import br.com.victor.smite.smitestatus.model.Session;
import br.com.victor.smite.smitestatus.repository.PlayerRepository;
import br.com.victor.smite.smitestatus.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerService(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository){
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.playerRepository = playerRepository;
    }

    public Player createSession(String username) {
        try {

            String date = Utils.getTimestampFormatted();
            String signature = Utils.getHash(devId, "createsession", authKey, date);
            Session session = hiRezSmiteApi.createSession(devId, signature, date);
            Player player = playerRepository.findByUsername(username);
            player.setLastSessionId(session.getSessionId());
            return playerRepository.save(player);

        } catch (Exception ex) {
            return new Player();
        }
    }

}
