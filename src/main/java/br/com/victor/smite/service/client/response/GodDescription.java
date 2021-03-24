package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GodDescription {
    private String cooldown;
    private String cost;
    private String description;
    @JsonProperty("menuitems")
    private List<GodItem> menuGodItems;
    @JsonProperty("rankitems")
    private List<GodItem> rankGodItems;
    private String secondaryDescription;
}
