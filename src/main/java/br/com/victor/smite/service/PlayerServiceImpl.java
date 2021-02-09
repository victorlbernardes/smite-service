package br.com.victor.smite.service;

import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.service.client.response.Session;
import br.com.victor.smite.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    private String date;
    private String signature;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;
    @Autowired
    private final PlayerRepository playerRepository;

    Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository) {
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.playerRepository = playerRepository;
        this.date = Utils.getTimestampFormatted();
    }

    public Session createSession(String username) {
        logger.info("createSession");
        this.signature = Utils.getHash(devId, "createsession", authKey, this.date);

        Session session = hiRezSmiteApi.createSession(devId, this.signature, this.date);
        Player player = playerRepository.findByUsername(username);

        player.setLastSessionId(session.getSessionId());
        playerRepository.save(player);
        return session;

    }

}
