package br.com.victor.smite.service;

import br.com.victor.smite.entity.Player;
import br.com.victor.smite.model.DataUsage;
import br.com.victor.smite.model.SmitePatchVersion;
import br.com.victor.smite.model.HiRezServerStatus;
import br.com.victor.smite.client.HiRezSmiteApi;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoStatusService {
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

    public InfoStatusService(HiRezSmiteApi hiRezSmiteApi, PlayerRepository playerRepository) {
        this.date = Utils.getTimestampFormatted();
        this.playerRepository = playerRepository;
        this.hiRezSmiteApi = hiRezSmiteApi;
    }

    public String ping() {
        return this.hiRezSmiteApi.ping();
    }

    public String testSession(String username) {

        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "testsession", authKey, this.date);
        return hiRezSmiteApi.testSession(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public List<DataUsage> dataUsage(String username) {
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getdataused", authKey, this.date);
        return hiRezSmiteApi.dataUsage(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public List<HiRezServerStatus> hiRezServerStatus(String username) {
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "gethirezserverstatus", authKey, this.date);
        return hiRezSmiteApi.hiRezServerStatus(devId, this.signature, player.getLastSessionId(), this.date);
    }

    public SmitePatchVersion smitePatchVersion(String username) {
        Player player = playerRepository.findByUsername(username);
        this.signature = Utils.getHash(devId, "getpatchinfo", authKey, this.date);
        return hiRezSmiteApi.smitePatchVersion(devId, this.signature, player.getLastSessionId(), this.date);
    }
}
