package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.service.client.response.GodSkin;

import java.util.List;

public interface GeneralService {

    List<Item> getAllItens(String username, Integer language);

    List<God> getAllGods(String username, Integer language);

    List<GodSkin> getAllGodSkin(String username, int godId, Integer language);

    List<GodRecommendedItems> getGodRecommendedItems(String username, int godId, Integer language);

    List<ProLeagueSeasonDetail> proLeagueSeason(String username);
}
