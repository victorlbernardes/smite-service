package br.com.victor.smite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Item extends HiRezApiModel {

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

    @Getter
    private static class ItemDescription {
        @JsonProperty("Description")
        private String description;

        @JsonProperty("SecondaryDescription")
        private String secondaryDescription;

        @JsonProperty("Menuitems")
        private List<Menuitems> menuItems;
    }

    @Getter
    public static class Menuitems {
        @JsonProperty("Description")
        private String description;

        @JsonProperty("Value")
        private String value;
    }


}
