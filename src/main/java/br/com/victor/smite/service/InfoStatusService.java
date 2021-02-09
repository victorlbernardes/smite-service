package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.DataUsage;
import br.com.victor.smite.service.client.response.HiRezServerStatus;
import br.com.victor.smite.service.client.response.SmitePatchVersion;

import java.util.List;

public interface InfoStatusService {

    String ping();

    String testSession(String username);

    List<DataUsage> dataUsage(String username);

    List<HiRezServerStatus> hiRezServerStatus(String username);

    SmitePatchVersion smitePatchVersion(String username);
}
