package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.service.client.response.GodSkin;

import java.util.List;

public interface GeneralService {

    public List<Item> getAllItens(Integer language);

    public List<God> getAllGods(Integer language);

    public List<GodSkin> getAllGodSkin(int godId, Integer language);

    public List<GodRecommendedItems> getGodRecommendedItems(int godId, Integer language);

    public List<ProLeagueSeasonDetail> proLeagueSeason() ;
}
