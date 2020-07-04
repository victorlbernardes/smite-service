package br.com.victor.smite.controller;

import br.com.victor.smite.model.Item;
import br.com.victor.smite.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<Item> getAllItens(@RequestParam(value = "sessionId") String sessionId, @RequestParam(value = "language", required = false, defaultValue = "1") String language) {

        return this.generalService.getAllItens(sessionId, Integer.parseInt(language));
    }
}
