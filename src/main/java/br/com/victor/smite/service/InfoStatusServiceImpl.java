package br.com.victor.smite.service;

import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import br.com.victor.smite.service.client.response.SmitePatchVersion;
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
    private AuthenticationService authenticationService;

    private String date;
    private String signature;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    Logger logger = LoggerFactory.getLogger(InfoStatusServiceImpl.class);

    public InfoStatusServiceImpl(HiRezSmiteApi hiRezSmiteApi, AuthenticationService authenticationService) {
        this.date = HashGenerator.getTimestampFormatted();
        this.authenticationService = authenticationService;
        this.hiRezSmiteApi = hiRezSmiteApi;
    }
    
    private String getLastSessionId(){
        return this.authenticationService.createSession().getSessionId();
    }
    
    public String ping() {
        return this.hiRezSmiteApi.ping();
    }

    public String testSession() {
        logger.info("testSession");
        this.signature = HashGenerator.getHash(devId, "testsession", authKey, this.date);
        return hiRezSmiteApi.testSession(devId, this.signature, this.getLastSessionId(), this.date);
    }

    public List<DataUsage> dataUsage() {
        logger.info("dataUsage");
        this.signature = HashGenerator.getHash(devId, "getdataused", authKey, this.date);
        return hiRezSmiteApi.dataUsage(devId, this.signature, this.getLastSessionId(), this.date);
    }

    public List<HiRezServerStatus> hiRezServerStatus() {
        logger.info("hiRezServerStatus");
        this.signature = HashGenerator.getHash(devId, "gethirezserverstatus", authKey, this.date);
        return hiRezSmiteApi.hiRezServerStatus(devId, this.signature, this.getLastSessionId(), this.date);
    }

    public SmitePatchVersion smitePatchVersion() {
        logger.info("smitePatchVersion");
        this.signature = HashGenerator.getHash(devId, "getpatchinfo", authKey, this.date);
        return hiRezSmiteApi.smitePatchVersion(devId, this.signature, this.getLastSessionId(), this.date);
    }
}
