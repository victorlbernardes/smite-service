package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GodItemDescription {
    @JsonProperty("itemDescription")
    private GodDescription itemDescription;
}
