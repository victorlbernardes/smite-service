package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GodRecommendedItems extends HiRezApiModel {

    @JsonProperty("Category")
    private String category;
    @JsonProperty("Item")
    private String name;
    @JsonProperty("Role")
    private String role;
    @JsonProperty("category_value_id")
    private long categoryValueId;
    @JsonProperty("god_id")
    private long godId;
    @JsonProperty("god_name")
    private String godName;
    @JsonProperty("icon_id")
    private long iconId;
    @JsonProperty("item_id")
    private long id;
    @JsonProperty("ret_msg")
    private String returnedMessage;
    @JsonProperty("role_value_id")
    private long roleValueId;
}
