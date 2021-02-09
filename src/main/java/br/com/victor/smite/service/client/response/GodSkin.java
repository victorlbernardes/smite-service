package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GodSkin extends HiRezApiModel {
    @JsonProperty("godIcon_URL")
    private String godIconUrl;
    private long godId;
    @JsonProperty("god_name")
    private String godName;
    @JsonProperty("godSkin_URL")
    private String skinURL;
    private String obtainability;
    private int priceFavor;
    @JsonProperty("price_gems")
    private int priceGems;
    @JsonProperty("ret_msg")
    private String returnedMessage;
    @JsonProperty("skin_id1")
    private long id1;
    @JsonProperty("skin_id2")
    private long id2;
    @JsonProperty("skin_name")
    private String name;
}
