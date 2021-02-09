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
    private List<GodItem> menuItems;
    @JsonProperty("rankitems")
    private List<GodItem> rankItems;
    private String secondaryDescription;

}
