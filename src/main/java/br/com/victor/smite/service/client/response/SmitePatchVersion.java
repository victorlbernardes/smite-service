package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SmitePatchVersion extends HiRezApiModel {
    @JsonProperty("version_string")
    private String versionString;
}
