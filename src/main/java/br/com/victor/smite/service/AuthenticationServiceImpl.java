package br.com.victor.smite.service;

import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.service.client.response.Session;
import br.com.victor.smite.utils.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${dev_id.key}")
    private String devId;
    @Value("${auth.key}")
    private String authKey;
    private String date;

    @Autowired
    private final HiRezSmiteApi hiRezSmiteApi;

    Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public AuthenticationServiceImpl(HiRezSmiteApi hiRezSmiteApi) {
        this.hiRezSmiteApi = hiRezSmiteApi;
        this.date = HashGenerator.getTimestampFormatted();
    }

    public Session createSession() {
        logger.info("createSession");
        return hiRezSmiteApi.createSession(devId,
                HashGenerator.getHash(devId,
                        "createsession",
                        authKey, this.date),
                this.date);

    }

}
