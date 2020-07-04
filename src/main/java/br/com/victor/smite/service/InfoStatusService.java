package br.com.victor.smite.service;

import br.com.victor.smite.model.DataUsage;
import br.com.victor.smite.model.HiRezPatchVersion;
import br.com.victor.smite.model.HiRezServerStatus;
import br.com.victor.smite.client.HiRezSmiteApi;
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

    private String date;
    private String signature;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    public InfoStatusService(HiRezSmiteApi hiRezSmiteApi) {
        this.date = Utils.getTimestampFormatted();
        this.hiRezSmiteApi = hiRezSmiteApi;
    }

    public String ping() {
        return this.hiRezSmiteApi.ping();
    }

    public String testSession(String sessionId) {

        this.signature = Utils.getHash(devId, "testsession", authKey, this.date);
        return hiRezSmiteApi.testSession(devId, this.signature, sessionId, this.date);
    }

    public List<DataUsage> dataUsage(String sessionId) {
        this.signature = Utils.getHash(devId, "getdataused", authKey, this.date);
        return hiRezSmiteApi.dataUsage(devId, this.signature, sessionId, this.date);
    }

    public List<HiRezServerStatus> hiRezServerStatus(String sessionId) {
        this.signature = Utils.getHash(devId, "gethirezserverstatus", authKey, this.date);
        return hiRezSmiteApi.hiRezServerStatus(devId, this.signature, sessionId, this.date);
    }

    public HiRezPatchVersion smitePatchVersion(String sessionId) {
        this.signature = Utils.getHash(devId, "getpatchinfo", authKey, this.date);
        return hiRezSmiteApi.smitePatchVersion(devId, this.signature, sessionId, this.date);
    }
}
