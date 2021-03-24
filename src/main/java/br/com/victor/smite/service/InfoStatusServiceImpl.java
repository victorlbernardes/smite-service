package br.com.victor.smite.service;

import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import br.com.victor.smite.service.client.response.SmitePatchVersion;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.utils.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoStatusServiceImpl implements InfoStatusService{
    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    @Autowired
    private final PlayerRepository playerRepository;

    private String date;
    private String signature;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    Logger logger = LoggerFactory.getLogger(InfoStatusService.class);

    public InfoStatusServiceImpl(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository) {
        this.date = HashGenerator.getTimestampFormatted();
        this.playerRepository = playerRepository;
        this.hiRezSmiteApi = hiRezSmiteApi;
    }

    public String ping() {
        return this.hiRezSmiteApi.ping();
    }

    public String testSession(String username) {
        logger.info("testSession");
        Player player = playerRepository.findByUsername(username);
        this.signature = HashGenerator.getHash(devId, "testsession", authKey, this.date);
        return hiRezSmiteApi.testSession(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public List<DataUsage> dataUsage(String username) {
        logger.info("dataUsage");
        Player player = playerRepository.findByUsername(username);
        this.signature = HashGenerator.getHash(devId, "getdataused", authKey, this.date);
        return hiRezSmiteApi.dataUsage(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public List<HiRezServerStatus> hiRezServerStatus(String username) {
        logger.info("hiRezServerStatus");
        Player player = playerRepository.findByUsername(username);
        this.signature = HashGenerator.getHash(devId, "gethirezserverstatus", authKey, this.date);
        return hiRezSmiteApi.hiRezServerStatus(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public SmitePatchVersion smitePatchVersion(String username) {
        logger.info("smitePatchVersion");
        Player player = playerRepository.findByUsername(username);
        this.signature = HashGenerator.getHash(devId, "getpatchinfo", authKey, this.date);
        return hiRezSmiteApi.smitePatchVersion(devId, this.signature, player.getLastSessionId(), this.date);
    }
}
