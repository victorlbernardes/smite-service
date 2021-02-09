package br.com.victor.smite.service.client.response;

import br.com.victor.smite.utils.TextToBoolean;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Item extends HiRezApiModel {

    @TextToBoolean
    @JsonProperty("ActiveFlag")
    private boolean active;
    @JsonProperty("ChildItemId")
    private long childId;
    @JsonProperty("DeviceName")
    private String name;
    @JsonProperty("IconId")
    private long iconId;
    @JsonProperty("ItemDescription")
    private ItemDescription description;
    @JsonProperty("ItemId")
    private long id;
    @JsonProperty("ItemTier")
    private int itemTier;
    @JsonProperty("Price")
    private int price;
    @JsonProperty("RestrictedRoles")
    private String restrictedRoles;
    @JsonProperty("RootItemId")
    private long rootItemId;
    @JsonProperty("ShortDesc")
    private String shortDescription;
    @JsonProperty("StartingItem")
    private boolean startingItem;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("itemIcon_URL")
    private String iconURL;

}
