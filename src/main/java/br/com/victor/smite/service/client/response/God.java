package br.com.victor.smite.service.client.response;

import br.com.victor.smite.utils.TextToBoolean;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class God extends HiRezApiModel {

    @JsonProperty("Ability_1")
    private Ability ability1;
    @JsonProperty("Ability_2")
    private Ability ability2;
    @JsonProperty("Ability_3")
    private Ability ability3;
    @JsonProperty("Ability_4")
    private Ability ability4;
    @JsonProperty("Ability_5")
    private Ability ability5;

    @JsonProperty("Ability1")
    private String ability1Name;
    @JsonProperty("Ability2")
    private String ability2Name;
    @JsonProperty("Ability3")
    private String ability3Name;
    @JsonProperty("Ability4")
    private String ability4Name;
    @JsonProperty("Ability5")
    private String ability5Name;

    @JsonProperty("abilityDescription1")
    private GodItemDescription abilityDescription1;
    @JsonProperty("abilityDescription2")
    private GodItemDescription abilityDescription2;
    @JsonProperty("abilityDescription3")
    private GodItemDescription abilityDescription3;
    @JsonProperty("abilityDescription4")
    private GodItemDescription abilityDescription4;
    @JsonProperty("abilityDescription5")
    private GodItemDescription abilityDescription5;

    @JsonProperty("AbilityId1")
    private long ability1Id;
    @JsonProperty("AbilityId2")
    private long ability2Id;
    @JsonProperty("AbilityId3")
    private long ability3Id;
    @JsonProperty("AbilityId4")
    private long ability4Id;
    @JsonProperty("AbilityId5")
    private long ability5Id;

    @JsonProperty("AttackSpeed")
    private double attackSpeed;
    @JsonProperty("AttackSpeedPerLevel")
    private double attackSpeedPerLevel;
    @JsonProperty("basicAttack")
    private GodItemDescription basicAttack;
    @JsonProperty("Cons")
    private String cons;

    @JsonProperty("godAbility1_URL")
    private String ability1URL;
    @JsonProperty("godAbility2_URL")
    private String ability2URL;
    @JsonProperty("godAbility3_URL")
    private String ability3URL;
    @JsonProperty("godAbility4_URL")
    private String ability4URL;
    @JsonProperty("godAbility5_URL")
    private String ability5URL;

    @JsonProperty("godCard_URL")
    private String cardURL;
    @JsonProperty("godIcon_URL")
    private String iconURL;

    @JsonProperty("HP5PerLevel")
    private double hp5PerLevel;
    @JsonProperty("Health")
    private int health;
    @JsonProperty("HealthPerFive")
    private int healthPerFive;
    @JsonProperty("HealthPerLevel")
    private int healthPerLevel;
    private long id;
    @TextToBoolean
    @JsonProperty("latestGod")
    private boolean latestGod;
    @JsonProperty("Lore")
    private String lore;
    @JsonProperty("MP5PerLevel")
    private double mp5PerLevel;
    @JsonProperty("MagicProtection")
    private int magicProtection;
    @JsonProperty("MagicProtectionPerLevel")
    private double magicProtectionPerLevel;
    @JsonProperty("MagicalPower")
    private int magicalPower;
    @JsonProperty("MagicalPowerPerLevel")
    private int magicalPowerPerLevel;
    @JsonProperty("Mana")
    private int mana;
    @JsonProperty("ManaPerFive")
    private double manaPerFive;
    @JsonProperty("ManaPerLevel")
    private int manaPerLevel;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("OnFreeRotation")
    private boolean onFreeRotation;
    @JsonProperty("Pantheon")
    private String pantheon;
    @JsonProperty("PhysicalPower")
    private int physicalPower;
    @JsonProperty("PhysicalPowerPerLevel")
    private int physicalPowerPerLevel;
    @JsonProperty("PhysicalProtection")
    private int physicalProtection;
    @JsonProperty("PhysicalProtectionPerLevel")
    private int physicalProtectionPerLevel;
    @JsonProperty("Pros")
    private String pros;
    @JsonProperty("ret_msg")
    private String returnedMessage;
    @JsonProperty("Roles")
    private String roles;
    @JsonProperty("Speed")
    private int speed;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Type")
    private String type;
}
