package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Menuitems {
    @JsonProperty("Description")
    private String description;

    @JsonProperty("Value")
    private String value;
}