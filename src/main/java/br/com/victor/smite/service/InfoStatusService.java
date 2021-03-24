package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.SmitePatchVersion;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import java.util.List;

public interface InfoStatusService {

    public String ping();

    public String testSession();

    public List<DataUsage> dataUsage();

    public List<HiRezServerStatus> hiRezServerStatus();

    public SmitePatchVersion smitePatchVersion();
}
