package br.com.victor.smite.controller.v1;

import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.service.GeneralService;
import br.com.victor.smite.service.client.response.GodSkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/smite/general", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping("/get-items")
    public List<Item> getAllItens(Authentication authentication, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllItens( Integer.parseInt(language));
    }

    @GetMapping("/get-gods")
    public List<God> getAllGods(Authentication authentication, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllGods(Integer.parseInt(language));
    }

    @GetMapping("/all-god-skin")
    public List<GodSkin> getAllGodSkin(Authentication authentication, @RequestParam(value = "godId") int godId, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllGodSkin(godId, Integer.parseInt(language));
    }

    @GetMapping("/god-recommended-items")
    public List<GodRecommendedItems> getGodRecommendedItems(Authentication authentication, @RequestParam(value = "godId") int godId, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getGodRecommendedItems(godId, Integer.parseInt(language));
    }

    @GetMapping("/pro-league-season")
    public List<ProLeagueSeasonDetail> proLeagueSeason(Authentication authentication) {
        return this.generalService.proLeagueSeason();
    }
}
