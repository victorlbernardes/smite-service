package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemDescription {
    @JsonProperty("Description")
    private String description;

    @JsonProperty("SecondaryDescription")
    private String secondaryDescription;

    @JsonProperty("Menuitems")
    private List<MenuItems> menuItems;
}
