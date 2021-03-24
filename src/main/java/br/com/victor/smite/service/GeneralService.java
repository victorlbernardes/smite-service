package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.service.client.response.GodSkin;

import java.util.List;

public interface GeneralService {

    public List<Item> getAllItens(String username, Integer language);


    public List<God> getAllGods(String username, Integer language);

    public List<GodSkin> getAllGodSkin(String username, int godId, Integer language);

    public List<GodRecommendedItems> getGodRecommendedItems(String username, int godId, Integer language);

    public List<ProLeagueSeasonDetail> proLeagueSeason(String username) ;
}
