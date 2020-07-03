package br.com.victor.smite.smitestatus.service;

import br.com.victor.smite.smitestatus.client.HiRezSmiteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoStatusService {
    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    public InfoStatusService(HiRezSmiteApi hiRezSmiteApi) {
        this.hiRezSmiteApi = hiRezSmiteApi;
    }

    public String ping() {

        return this.hiRezSmiteApi.ping();

    }
}
