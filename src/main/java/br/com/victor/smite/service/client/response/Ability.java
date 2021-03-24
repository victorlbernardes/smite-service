package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Ability {
    @JsonProperty("Description")
    private GodItemDescription description;
    @JsonProperty("Id")
    private long id;
    @JsonProperty("Summary")
    private String name;
    @JsonProperty("URL")
    private String url;
}