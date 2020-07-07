package br.com.victor.smite.controller;

import br.com.victor.smite.model.God;
import br.com.victor.smite.model.GodRecommendedItems;
import br.com.victor.smite.model.Item;
import br.com.victor.smite.model.ProLeagueSeasonDetail;
import br.com.victor.smite.service.GeneralService;
import br.com.victor.smite.utils.GodSkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/general", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping("/get-items")
    public List<Item> getAllItens(Authentication authentication, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllItens(authentication.getPrincipal().toString(), Integer.parseInt(language));
    }

    @GetMapping("/get-gods")
    public List<God> getAllGods(Authentication authentication, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllGods(authentication.getPrincipal().toString(), Integer.parseInt(language));
    }

    @GetMapping("/all-god-skin")
    public List<GodSkin> getAllGodSkin(Authentication authentication, @RequestParam(value = "godId") int godId, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getAllGodSkin(authentication.getPrincipal().toString(), godId, Integer.parseInt(language));
    }

    @GetMapping("/god-recommended-items")
    public List<GodRecommendedItems> getGodRecommendedItems(Authentication authentication, @RequestParam(value = "godId") int godId, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {
        return this.generalService.getGodRecommendedItems(authentication.getPrincipal().toString(), godId, Integer.parseInt(language));
    }

    @GetMapping("/pro-league-season")
    public List<ProLeagueSeasonDetail> proLeagueSeason(Authentication authentication) {
        return this.generalService.proLeagueSeason(authentication.getPrincipal().toString());
    }
}
