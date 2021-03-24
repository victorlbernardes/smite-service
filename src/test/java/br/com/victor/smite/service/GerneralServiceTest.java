package br.com.victor.smite.service;

import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.service.client.response.GodSkin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class GerneralServiceTest {
    @Mock
    private HiRezSmiteApi hiRezSmiteApi;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GeneralServiceImpl generalService;

    private final String USERNAME = "Akillian";

    @Before
    public void setUp(){
        this.generalService = new GeneralServiceImpl(hiRezSmiteApi, playerRepository); //inject the mock
    }

    @DisplayName("Testing Get All Items Success")
    @Test
    public void testGetAllItems_Success() {
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdItemSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<Item> listItems = generalService.getAllItens(USERNAME, 1);
        Assert.assertEquals(3, listItems.size());
    }

    @DisplayName("Testing Get All Items Fail")
    @Test
    public void testGetAllItems_Fail() {
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdItemFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<Item> listItems = generalService.getAllItens(USERNAME, 1);
        Assert.assertEquals(1, listItems.size());
    }

    @DisplayName("Testing Get All Gods Success")
    @Test
    public void testGetAllGods_Success() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodsSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<God> listGods = generalService.getAllGods(USERNAME, 1);
        Assert.assertEquals(2, listGods.size());
    }

    @DisplayName("Testing Get All Gods Fail")
    @Test
    public void testGetAllGods_Fail() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodsFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<God> listGods = generalService.getAllGods(USERNAME, 1);
        Assert.assertEquals(1, listGods.size());
    }

    @DisplayName("Testing Get All God Skin Success")
    @Test
    public void testGodSkin_Success() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodSkinSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(USERNAME, 1, 1);
        Assert.assertEquals(2, godSkinList.size());
    }

    @DisplayName("Testin Get All God Skin Fail")
    @Test
    public void testGodSkin_Fail() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodSkinFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(USERNAME, 1, 1);
        Assert.assertEquals(1, godSkinList.size());
    }

    @DisplayName("Testing Get God Recommended Items Success")
    @Test
    public void testGodRecommendedItems_Success() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodRecommendedItemsSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(USERNAME, 1, 1);
        Assert.assertEquals(2, recommendedItemsList.size());
    }

    @DisplayName("Testing Get God Recommended Items Fail")
    @Test
    public void testGetGodRecommendedItems_Fail() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdGodRecommendedItemsFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(USERNAME, 1, 1);
        Assert.assertEquals(1, recommendedItemsList.size());
    }

    @DisplayName("Testing Get Pro League Season Success")
    @Test
    public void testProLeagueSeason_Success() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdProLeagueSeasonSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason(USERNAME);
        Assert.assertEquals(2, proLeagueSeasonDetailList.size());
    }

    @DisplayName("Testing Get Pro League Season Fail")
    @Test
    public void testProLeagueSeason_Fail() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdProLeagueSeasonFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason(USERNAME);
        Assert.assertEquals(1, proLeagueSeasonDetailList.size());
    }

    private Object newMock(String json, Class<?> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> createdItemSuccess()  {
        String json = "[{\"ActiveFlag\":\"y\",\"ChildItemId\":0,\"DeviceName\":\"Iron Mail\",\"IconId\":2866,\"ItemDescription\":{\"Description\":\"Physical Protection and Health.\",\"Menuitems\":[{\"Description\":\"Health\",\"Value\":\"+75\"},{\"Description\":\"Physical Protection\",\"Value\":\"+10\"}],\"SecondaryDescription\":null},\"ItemId\":7526,\"ItemTier\":1,\"Price\":650,\"RestrictedRoles\":\"no restrictions\",\"RootItemId\":7526,\"ShortDesc\":\"Physical Protection and Health.\",\"StartingItem\":false,\"Type\":\"Item\",\"itemIcon_URL\":\"https://web2.hirez.com/smite/item-icons/iron-mail.jpg\",\"ret_msg\":null},{\"ActiveFlag\":\"y\",\"ChildItemId\":7526,\"DeviceName\":\"Steel Mail\",\"IconId\":2865,\"ItemDescription\":{\"Description\":\"\",\"Menuitems\":[{\"Description\":\"Health\",\"Value\":\"+200\"},{\"Description\":\"Physical Protection\",\"Value\":\"+20\"}],\"SecondaryDescription\":\"\"},\"ItemId\":7527,\"ItemTier\":2,\"Price\":750,\"RestrictedRoles\":\"no restrictions\",\"RootItemId\":7526,\"ShortDesc\":\"Physical Protection and Health.\",\"StartingItem\":false,\"Type\":\"Item\",\"itemIcon_URL\":\"https://web2.hirez.com/smite/item-icons/steel-mail.jpg\",\"ret_msg\":null},{\"ActiveFlag\":\"y\",\"ChildItemId\":7527,\"DeviceName\":\"Sovereignty\",\"IconId\":2864,\"ItemDescription\":{\"Description\":\"This item gives the owner an aura of physical protections.\",\"Menuitems\":[{\"Description\":\"Health\",\"Value\":\"+250\"},{\"Description\":\"Physical Protection\",\"Value\":\"+45\"}],\"SecondaryDescription\":\"AURA - Allied gods within 70 units have their Physical Protections increased by 15 and their HP5 increased by 35.\"},\"ItemId\":7528,\"ItemTier\":3,\"Price\":700,\"RestrictedRoles\":\"no restrictions\",\"RootItemId\":7526,\"ShortDesc\":\"Physical Protection Aura\",\"StartingItem\":false,\"Type\":\"Item\",\"itemIcon_URL\":\"https://web2.hirez.com/smite/item-icons/sovereignty.jpg\",\"ret_msg\":null}]";
        return (List<Item>) newMock(json, List.class);
    }

    private Player playerSuccess()  {
        String json = "{\"id\":2,\"username\":\"Akillian\",\"player_id\":\"000\"}";
        return (Player) newMock(json, Player.class);
    }

    private List<Item> createdItemFail()  {
        String json = "[{\"ActiveFlag\":null,\"ChildItemId\":0,\"DeviceName\":null,\"IconId\":0,\"ItemDescription\":null,\"ItemId\":0,\"ItemTier\":0,\"Price\":0,\"RestrictedRoles\":null,\"RootItemId\":0,\"ShortDesc\":null,\"StartingItem\":false,\"Type\":null,\"itemIcon_URL\":null,\"ret_msg\":\"Invalid session id.\"}]";
        return (List<Item>) newMock(json, List.class);
    }

    private List<God> createdGodsSuccess()  {
        String json = "[{\"id\":3492,\"ret_msg\":null,\"Ability_1\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"15s\",\"cost\":\"60/65/70/75/80\",\"description\":\"Achilles punches forward with the edge of his Shield, inflicting massive damage and stunning enemy targets hit by the impact. The force of his punch continues to radiate past his initial target area, dealing 85% damage to targets farther away.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Cone\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"50\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"80/135/190/245/300 (90% of your Physical Power)\"},{\"description\":\"Stun Duration:\",\"value\":\"1s\"}]}},\"Id\":15676,\"Summary\":\"Shield of Achilles\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/shield-of-achilles.jpg\"},\"Ability_2\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"10s\",\"cost\":\"40/45/50/55/60\",\"description\":\"Achilles is blessed by the gods, giving him bonus Physical Power, Protections, and Crowd Control Reduction for 6 seconds. While this blessing is active, Achilles will heal himself upon successfully damaging enemies with abilities.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Buff\"},{\"description\":\"Affects:\",\"value\":\"Self\"}],\"rankitems\":[{\"description\":\"Heal:\",\"value\":\"20/23/26/29/32 (10% of your Physical Power)\"},{\"description\":\"Max Heals per Ability:\",\"value\":\"2/2/3/3/4\"},{\"description\":\"Physical Power:\",\"value\":\"+6/7/8/9/10%\"},{\"description\":\"Protections:\",\"value\":\"+10/12.5/15/17.5/20%\"},{\"description\":\"Crowd Control Reduction:\",\"value\":\"20%\"}]}},\"Id\":15677,\"Summary\":\"Radiant Glory\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/radiant-glory.jpg\"},\"Ability_3\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"14/13/12/11/10s\",\"cost\":\"24/28/32/36/40\",\"description\":\"Achilles dodges his enemies' attacks before striking them in swift response. If Achilles successfully hits an enemy god with this strike, Achilles can use this ability once more before it goes on Cooldown.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"35\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"50/85/120/155/190 (+45% of your Physical Power)\"}]}},\"Id\":15679,\"Summary\":\"Combat Dodge\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/combat-dodge.jpg\"},\"Ability_4\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"90s\",\"cost\":\"80/85/90/95/100\",\"description\":\"Achilles dashes forward and attacks. While dashing, Achilles will pass through minions, stop and hit the first enemy god he encounters, dealing damage to all he hits and executing gods below 30% Health. If Achilles kills a god with this ability, he can use it again, up to 5 times. As Achilles successfully Executes his enemies, he becomes more reckless in combat and leaves his heel exposed. Achilles will become more susceptible to damage, stacking up to 5 times.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"35\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"180/270/360/450/540 (100% of your Physical Power)\"},{\"description\":\"Execute Threshold:\",\"value\":\"30%\"},{\"description\":\"Damage Taken Increase:\",\"value\":\"10%\"}]}},\"Id\":15680,\"Summary\":\"Fatal Strike\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/fatal-strike.jpg\"},\"Ability_5\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"Achilles adapts to the tide of Battle. While in the Fountain, Achilles can choose to wear armor, granting him bonus Health and Protections, or forgo it, granting him bonus Movement Speed and Physical Power. To swap, use Achilles' Basic Attack while the Passive targeter is active. \",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Passive\"}],\"rankitems\":[{\"description\":\"Health Bonus:\",\"value\":\"25 +15 per Level\"},{\"description\":\"Protections Bonus:\",\"value\":\"5 +2 per Level\"},{\"description\":\"Movement Speed Bonus:\",\"value\":\"1% +.25% per Level\"},{\"description\":\"Physical Power Bonus:\",\"value\":\"3 +2 per Level\"}]}},\"Id\":15678,\"Summary\":\"Gift of the Gods\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/gift-of-the-gods.jpg\"},\"Ability1\":\"Shield of Achilles\",\"Ability2\":\"Radiant Glory\",\"Ability3\":\"Combat Dodge\",\"Ability4\":\"Fatal Strike\",\"Ability5\":\"Gift of the Gods\",\"abilityDescription1\":{\"itemDescription\":{\"cooldown\":\"15s\",\"cost\":\"60/65/70/75/80\",\"description\":\"Achilles punches forward with the edge of his Shield, inflicting massive damage and stunning enemy targets hit by the impact. The force of his punch continues to radiate past his initial target area, dealing 85% damage to targets farther away.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Cone\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"50\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"80/135/190/245/300 (90% of your Physical Power)\"},{\"description\":\"Stun Duration:\",\"value\":\"1s\"}]}},\"abilityDescription2\":{\"itemDescription\":{\"cooldown\":\"10s\",\"cost\":\"40/45/50/55/60\",\"description\":\"Achilles is blessed by the gods, giving him bonus Physical Power, Protections, and Crowd Control Reduction for 6 seconds. While this blessing is active, Achilles will heal himself upon successfully damaging enemies with abilities.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Buff\"},{\"description\":\"Affects:\",\"value\":\"Self\"}],\"rankitems\":[{\"description\":\"Heal:\",\"value\":\"20/23/26/29/32 (10% of your Physical Power)\"},{\"description\":\"Max Heals per Ability:\",\"value\":\"2/2/3/3/4\"},{\"description\":\"Physical Power:\",\"value\":\"+6/7/8/9/10%\"},{\"description\":\"Protections:\",\"value\":\"+10/12.5/15/17.5/20%\"},{\"description\":\"Crowd Control Reduction:\",\"value\":\"20%\"}]}},\"abilityDescription3\":{\"itemDescription\":{\"cooldown\":\"14/13/12/11/10s\",\"cost\":\"24/28/32/36/40\",\"description\":\"Achilles dodges his enemies' attacks before striking them in swift response. If Achilles successfully hits an enemy god with this strike, Achilles can use this ability once more before it goes on Cooldown.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"35\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"50/85/120/155/190 (+45% of your Physical Power)\"}]}},\"abilityDescription4\":{\"itemDescription\":{\"cooldown\":\"90s\",\"cost\":\"80/85/90/95/100\",\"description\":\"Achilles dashes forward and attacks. While dashing, Achilles will pass through minions, stop and hit the first enemy god he encounters, dealing damage to all he hits and executing gods below 30% Health. If Achilles kills a god with this ability, he can use it again, up to 5 times. As Achilles successfully Executes his enemies, he becomes more reckless in combat and leaves his heel exposed. Achilles will become more susceptible to damage, stacking up to 5 times.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemies\"},{\"description\":\"Damage:\",\"value\":\"Physical\"},{\"description\":\"Range:\",\"value\":\"35\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"180/270/360/450/540 (100% of your Physical Power)\"},{\"description\":\"Execute Threshold:\",\"value\":\"30%\"},{\"description\":\"Damage Taken Increase:\",\"value\":\"10%\"}]}},\"abilityDescription5\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"Achilles adapts to the tide of Battle. While in the Fountain, Achilles can choose to wear armor, granting him bonus Health and Protections, or forgo it, granting him bonus Movement Speed and Physical Power. To swap, use Achilles' Basic Attack while the Passive targeter is active. \",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Passive\"}],\"rankitems\":[{\"description\":\"Health Bonus:\",\"value\":\"25 +15 per Level\"},{\"description\":\"Protections Bonus:\",\"value\":\"5 +2 per Level\"},{\"description\":\"Movement Speed Bonus:\",\"value\":\"1% +.25% per Level\"},{\"description\":\"Physical Power Bonus:\",\"value\":\"3 +2 per Level\"}]}},\"AbilityId1\":15676,\"AbilityId2\":15677,\"AbilityId3\":15679,\"AbilityId4\":15680,\"AbilityId5\":15678,\"AttackSpeed\":0.95,\"AttackSpeedPerLevel\":0.012,\"basicAttack\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Damage:\",\"value\":\"38 + 2/Lvl (+100% of Physical Power)\"},{\"description\":\"Progression:\",\"value\":\"None\"}],\"rankitems\":[]}},\"Cons\":\"\",\"godAbility1_URL\":\"https://web2.hirez.com/smite/god-abilities/shield-of-achilles.jpg\",\"godAbility2_URL\":\"https://web2.hirez.com/smite/god-abilities/radiant-glory.jpg\",\"godAbility3_URL\":\"https://web2.hirez.com/smite/god-abilities/combat-dodge.jpg\",\"godAbility4_URL\":\"https://web2.hirez.com/smite/god-abilities/fatal-strike.jpg\",\"godAbility5_URL\":\"https://web2.hirez.com/smite/god-abilities/gift-of-the-gods.jpg\",\"godCard_URL\":\"https://web2.hirez.com/smite/god-cards/achilles.jpg\",\"godIcon_URL\":\"https://web2.hirez.com/smite/god-icons/achilles.jpg\",\"HP5PerLevel\":0.75,\"Health\":475,\"HealthPerFive\":9,\"HealthPerLevel\":85,\"latestGod\":false,\"Lore\":\"King Agamemnon brought his fury to bear against gilded Troy, for Prince Paris had stolen his Helen, his wife, whose beauty rivaled that of Athena and Aphrodite. To famed Achilles, invincible warrior, the king gave command of a thousand ships.\\\\n\\\\nAcross stormy seas and salted beach, soldiers sieged the city. Arrow and stone, blade and barb bounced from Achilles’ skin. Bathed as a babe in the River Styx by his Nereid mother, his hide was hardened, imperviously made. Through every charge, every death-defying battle, Achilles was at the fore. Troy hung poised to crumble.\\\\n\\\\nUntil Agamemnon gave slight to the mighty myrmidon. In grave offense, Achilles pulled his forces from the field. Hector, boldest, bravest, eldest of the Trojan princes seized the chance to push the Greeks to the sea. Water’s reflection mirrored scorching sails as Hector fired their ships. All seemed lost until Achilles rose to meet him. Fierce and fast the two titans fought, but Hector’s spear felled Achilles fair. Though Patroclus, it was, in the armor of Achilles, not Achilles who lay dead.\\\\n\\\\nWrathful at the loss of his faithful friend, Achilles donned armor newly-made and challenged Hector alone. Spear and blade and amor rang, but Achilles could not be harmed. Hector, prince of Troy, died in battle that day.\\\\n\\\\nParis, brother lost, tearful-eyed, let arrow loose, guided by divine envy. For there were Gods that could not suffer Achilles to survive. Straight and true the arrow flew and harpooned Achilles’ heel, where his mother held him when submerged. The wound was deep, his weakness found, Achilles met his end.\\\\n\\\\nA decade thence, from Hades’ depths, Achilles has been drawn. Armored now, upon the heel, revenge his only aim. For envious Gods stole from him his glory and his life. Now they tremble at the wrath of the man who cannot be harmed.\",\"MP5PerLevel\":0.39,\"MagicProtection\":30,\"MagicProtectionPerLevel\":0.9,\"MagicalPower\":0,\"MagicalPowerPerLevel\":0,\"Mana\":205,\"ManaPerFive\":4.7,\"ManaPerLevel\":35,\"Name\":\"Achilles\",\"OnFreeRotation\":false,\"Pantheon\":\"Greek\",\"PhysicalPower\":38,\"PhysicalPowerPerLevel\":2,\"PhysicalProtection\":17,\"PhysicalProtectionPerLevel\":3,\"Pros\":\" High Single Target Damage, High Mobility\",\"Roles\":\" Warrior\",\"Speed\":370,\"Title\":\"Hero of the Trojan War\",\"Type\":\" Melee, Physical\"},{\"id\":1737,\"ret_msg\":null,\"Ability_1\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"12s\",\"cost\":\"60/65/70/75/80\",\"description\":\"Agni summons a cloud of noxious fumes at his ground target location, doing damage every second. Firing any of Agni's abilities into the fumes detonates the gas, Stunning all enemies in the radius.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Ground Target\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"},{\"description\":\"Radius:\",\"value\":\"20\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"10/20/30/40/50 (+5% of your Magical Power)\"},{\"description\":\"Fumes Duration:\",\"value\":\"10s\"},{\"description\":\"Stun Duration:\",\"value\":\"1s\"}]}},\"Id\":7812,\"Summary\":\"Noxious Fumes\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/noxious-fumes.jpg\"},\"Ability_2\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"15/14/13/12/11s\",\"cost\":\"60/70/80/90/100\",\"description\":\"Agni summons a wave of fire in front of him that scorches all enemies in its path. Ignites Noxious Fumes.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Line\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"90/140/190/240/290 (+65% of your Magical Power)\"}]}},\"Id\":7811,\"Summary\":\"Flame Wave\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/flame-wave.jpg\"},\"Ability_3\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"15s\",\"cost\":\"70/75/80/85/90\",\"description\":\"Agni blazes a path forward in a quick dash, leaving flames trailing behind him. Any enemies passing through the flames catch fire and burn for damage every .5s for 2s. Ignites Noxious Fumes. Agni is immune to Knockback while dashing.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"20/30/40/50/60 (+15% of your Magical Power)\"},{\"description\":\"Path Duration:\",\"value\":\"3s\"}]}},\"Id\":7818,\"Summary\":\"Path of Flames\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/path-of-flames.jpg\"},\"Ability_4\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"Dependent on Halos\",\"cost\":\"0\",\"description\":\"Every 18 seconds, Agni gains a flaming halo that can be expended to summon a giant meteor at his ground target location. He can summon 1 every .8 seconds. Ignites Noxious Fumes.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Ground Target\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"},{\"description\":\"Radius:\",\"value\":\"20\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"140/180/220/260/300 (+70% of your Magical Power)\"},{\"description\":\"Max Halos:\",\"value\":\"3\"}]}},\"Id\":7824,\"Summary\":\"Rain Fire\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/rain-fire.jpg\"},\"Ability_5\":{\"Description\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"After hitting with 4 Basic Attacks, Agni will gain a Buff. On the next cast of Flame Wave or Rain Fire, all enemies hit by those abilities will be additionally set ablaze, taking damage every .5s for 3s.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"5 (+10% of your Magical Power)\"}]}},\"Id\":7822,\"Summary\":\"Combustion\",\"URL\":\"https://web2.hirez.com/smite/god-abilities/combustion.jpg\"},\"Ability1\":\"Noxious Fumes\",\"Ability2\":\"Flame Wave\",\"Ability3\":\"Path of Flames\",\"Ability4\":\"Rain Fire\",\"Ability5\":\"Combustion\",\"abilityDescription1\":{\"itemDescription\":{\"cooldown\":\"12s\",\"cost\":\"60/65/70/75/80\",\"description\":\"Agni summons a cloud of noxious fumes at his ground target location, doing damage every second. Firing any of Agni's abilities into the fumes detonates the gas, Stunning all enemies in the radius.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Ground Target\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"},{\"description\":\"Radius:\",\"value\":\"20\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"10/20/30/40/50 (+5% of your Magical Power)\"},{\"description\":\"Fumes Duration:\",\"value\":\"10s\"},{\"description\":\"Stun Duration:\",\"value\":\"1s\"}]}},\"abilityDescription2\":{\"itemDescription\":{\"cooldown\":\"15/14/13/12/11s\",\"cost\":\"60/70/80/90/100\",\"description\":\"Agni summons a wave of fire in front of him that scorches all enemies in its path. Ignites Noxious Fumes.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Line\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"90/140/190/240/290 (+65% of your Magical Power)\"}]}},\"abilityDescription3\":{\"itemDescription\":{\"cooldown\":\"15s\",\"cost\":\"70/75/80/85/90\",\"description\":\"Agni blazes a path forward in a quick dash, leaving flames trailing behind him. Any enemies passing through the flames catch fire and burn for damage every .5s for 2s. Ignites Noxious Fumes. Agni is immune to Knockback while dashing.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Dash\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"20/30/40/50/60 (+15% of your Magical Power)\"},{\"description\":\"Path Duration:\",\"value\":\"3s\"}]}},\"abilityDescription4\":{\"itemDescription\":{\"cooldown\":\"Dependent on Halos\",\"cost\":\"0\",\"description\":\"Every 18 seconds, Agni gains a flaming halo that can be expended to summon a giant meteor at his ground target location. He can summon 1 every .8 seconds. Ignites Noxious Fumes.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Ability:\",\"value\":\"Ground Target\"},{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"},{\"description\":\"Radius:\",\"value\":\"20\"}],\"rankitems\":[{\"description\":\"Damage:\",\"value\":\"140/180/220/260/300 (+70% of your Magical Power)\"},{\"description\":\"Max Halos:\",\"value\":\"3\"}]}},\"abilityDescription5\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"After hitting with 4 Basic Attacks, Agni will gain a Buff. On the next cast of Flame Wave or Rain Fire, all enemies hit by those abilities will be additionally set ablaze, taking damage every .5s for 3s.\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Affects:\",\"value\":\"Enemy\"},{\"description\":\"Damage:\",\"value\":\"Magical\"}],\"rankitems\":[{\"description\":\"Damage per Tick:\",\"value\":\"5 (+10% of your Magical Power)\"}]}},\"AbilityId1\":7812,\"AbilityId2\":7811,\"AbilityId3\":7818,\"AbilityId4\":7824,\"AbilityId5\":7822,\"AttackSpeed\":1.0,\"AttackSpeedPerLevel\":0.012,\"basicAttack\":{\"itemDescription\":{\"cooldown\":\"\",\"cost\":\"\",\"description\":\"\",\"secondaryDescription\":null,\"menuitems\":[{\"description\":\"Damage:\",\"value\":\"34 + 1.5/Lvl (+20% of Magical Power)\"},{\"description\":\"Progression:\",\"value\":\"None\"}],\"rankitems\":[]}},\"Cons\":\"\",\"godAbility1_URL\":\"https://web2.hirez.com/smite/god-abilities/noxious-fumes.jpg\",\"godAbility2_URL\":\"https://web2.hirez.com/smite/god-abilities/flame-wave.jpg\",\"godAbility3_URL\":\"https://web2.hirez.com/smite/god-abilities/path-of-flames.jpg\",\"godAbility4_URL\":\"https://web2.hirez.com/smite/god-abilities/rain-fire.jpg\",\"godAbility5_URL\":\"https://web2.hirez.com/smite/god-abilities/combustion.jpg\",\"godCard_URL\":\"https://web2.hirez.com/smite/god-cards/agni.jpg\",\"godIcon_URL\":\"https://web2.hirez.com/smite/god-icons/agni.jpg\",\"HP5PerLevel\":0.47,\"Health\":360,\"HealthPerFive\":7,\"HealthPerLevel\":71,\"latestGod\":false,\"Lore\":\"There are few elements as destructive or as purifying as fire. Agni, God of Fire, is the embodiment of both of these qualities, with a head for each.\\\\n\\\\nThough the source of his origin warrants debate - for there are many tales of his parentage ranging from two simple sticks rubbed together, to the cosmic energy that made all things at the beginning of time - Agni is a pivotal and important God with many duties to the Pantheon. He is the twin brother to Indra, God of the Heavens and Rains and chief among warriors. Conversely, Agni is chief among priests, acting as messenger between mortals and Gods. Every Hindu ritual and prayer is performed in front of a fire of some kind, so Agni carries the words and sacrifices, traveling between the Earth and the Heavens. He is welcome in every home and every hearth and much beloved by the Faithful.\\\\n\\\\nThrough his flames, Agni provides heat and light, but also cleanses impurities. Smoke from his pyres create the air and hold the Heavens aloft. The sun, a source of fire itself, brings life-giving energy to the world, and his lightning streaks the sky during storms.\\\\n\\\\nFor all his kindness and service, Agni has two faces. One is the face of kindness and purity, turned towards the people and Gods. His other face, grim and resolute, guides the God of Fire, to play his role in the cosmic cycle of creation and destruction, to burn and blacken all the atrocities of the world to ash.\",\"MP5PerLevel\":0.37,\"MagicProtection\":30,\"MagicProtectionPerLevel\":0.9,\"MagicalPower\":170,\"MagicalPowerPerLevel\":7,\"Mana\":255,\"ManaPerFive\":4.7,\"ManaPerLevel\":45,\"Name\":\"Agni\",\"OnFreeRotation\":false,\"Pantheon\":\"Hindu\",\"PhysicalPower\":0,\"PhysicalPowerPerLevel\":0,\"PhysicalProtection\":11,\"PhysicalProtectionPerLevel\":2,\"Pros\":\" High Area Damage\",\"Roles\":\" Mage\",\"Speed\":355,\"Title\":\"God of Fire\",\"Type\":\" Ranged, Magical\"}]";
        return (List<God>) newMock(json, List.class);
    }

    private List<God> createdGodsFail()  {
        String json = "[{\"Ability1\":null,\"Ability2\":null,\"Ability3\":null,\"Ability4\":null,\"Ability5\":null,\"AbilityId1\":0,\"AbilityId2\":0,\"AbilityId3\":0,\"AbilityId4\":0,\"AbilityId5\":0,\"Ability_1\":null,\"Ability_2\":null,\"Ability_3\":null,\"Ability_4\":null,\"Ability_5\":null,\"AttackSpeed\":0,\"AttackSpeedPerLevel\":0,\"Cons\":null,\"HP5PerLevel\":0,\"Health\":0,\"HealthPerFive\":0,\"HealthPerLevel\":0,\"Lore\":null,\"MP5PerLevel\":0,\"MagicProtection\":0,\"MagicProtectionPerLevel\":0,\"MagicalPower\":0,\"MagicalPowerPerLevel\":0,\"Mana\":0,\"ManaPerFive\":0,\"ManaPerLevel\":0,\"Name\":null,\"OnFreeRotation\":null,\"Pantheon\":null,\"PhysicalPower\":0,\"PhysicalPowerPerLevel\":0,\"PhysicalProtection\":0,\"PhysicalProtectionPerLevel\":0,\"Pros\":null,\"Roles\":null,\"Speed\":0,\"Title\":null,\"Type\":null,\"abilityDescription1\":null,\"abilityDescription2\":null,\"abilityDescription3\":null,\"abilityDescription4\":null,\"abilityDescription5\":null,\"basicAttack\":null,\"godAbility1_URL\":null,\"godAbility2_URL\":null,\"godAbility3_URL\":null,\"godAbility4_URL\":null,\"godAbility5_URL\":null,\"godCard_URL\":null,\"godIcon_URL\":null,\"id\":0,\"latestGod\":null,\"ret_msg\":\"Invalid session id.\"}]";
        return (List<God>) newMock(json, List.class);
    }

    private List<GodSkin> createdGodSkinSuccess()  {
        String json = "[{\"godIcon_URL\":\"https://web2.hirez.com/smite/god-icons/athena.jpg\",\"godSkin_URL\":\"https://web2.hirez.com/smite/god-skins/athena_standard-athena.jpg\",\"god_id\":1919,\"god_name\":\"Athena\",\"obtainability\":\"Normal\",\"price_favor\":0,\"price_gems\":0,\"ret_msg\":null,\"skin_id1\":9114,\"skin_id2\":8886,\"skin_name\":\"Standard Athena\"},{\"godIcon_URL\":\"https://web2.hirez.com/smite/god-icons/athena.jpg\",\"godSkin_URL\":\"https://web2.hirez.com/smite/god-skins/athena_allegiance.jpg\",\"god_id\":1919,\"god_name\":\"Athena\",\"obtainability\":\"Exclusive\",\"price_favor\":0,\"price_gems\":0,\"ret_msg\":null,\"skin_id1\":24461,\"skin_id2\":14047,\"skin_name\":\"Allegiance\"}]";
        return (List<GodSkin>) newMock(json, List.class);
    }

    private List<GodSkin> createdGodSkinFail()  {
        String json = "[{\"godIcon_URL\":null,\"godSkin_URL\":null,\"god_id\":0,\"god_name\":null,\"obtainability\":null,\"price_favor\":0,\"price_gems\":0,\"ret_msg\":\"Invalid session id.\",\"skin_id1\":0,\"skin_id2\":0,\"skin_name\":null}]";
        return (List<GodSkin>) newMock(json, List.class);
    }

    private List<GodRecommendedItems> createdGodRecommendedItemsSuccess()  {
        String json = "[{\"Category\":\"Consumable\",\"Item\":\"Ward\",\"Role\":\"Standard\",\"category_value_id\":10779,\"god_id\":1919,\"god_name\":\"Athena\",\"icon_id\":1992,\"item_id\":7668,\"ret_msg\":null,\"role_value_id\":10770},{\"Category\":\"Consumable\",\"Item\":\"Potion of Magical Might\",\"Role\":\"Standard\",\"category_value_id\":10779,\"god_id\":1919,\"god_name\":\"Athena\",\"icon_id\":1977,\"item_id\":7529,\"ret_msg\":null,\"role_value_id\":10770}]";
        return (List<GodRecommendedItems>) newMock(json, List.class);
    }

    private List<GodRecommendedItems> createdGodRecommendedItemsFail()  {
        String json = "[{\"Category\":null,\"Item\":null,\"Role\":null,\"category_value_id\":0,\"god_id\":0,\"god_name\":null,\"icon_id\":0,\"item_id\":0,\"ret_msg\":\"Invalid session id.\",\"role_value_id\":0}]";
        return (List<GodRecommendedItems>) newMock(json, List.class);
    }

    private List<ProLeagueSeasonDetail> createdProLeagueSeasonSuccess()  {
        String json = "[{\"away_team_clan_id\":898,\"away_team_name\":\"eUnited\",\"away_team_tagname\":\"EUN\",\"home_team_clan_id\":903,\"home_team_name\":\"Ghost Gaming\",\"home_team_tagname\":\"RVL\",\"map_instance_id\":\"0\",\"match_date\":\"4/4/2020 7:00:00 PM\",\"match_number\":\"1\",\"match_status\":\"ended\",\"matchup_id\":\"9219\",\"region\":\"NA\",\"ret_msg\":null,\"tournament_name\":\"Dev Event Season 7\",\"winning_team_clan_id\":898},{\"away_team_clan_id\":901,\"away_team_name\":\"Knights\",\"away_team_tagname\":\"PK\",\"home_team_clan_id\":900,\"home_team_name\":\"Radiance\",\"home_team_tagname\":\"GHOST\",\"map_instance_id\":\"0\",\"match_date\":\"4/4/2020 9:00:00 PM\",\"match_number\":\"1\",\"match_status\":\"ended\",\"matchup_id\":\"9220\",\"region\":\"NA\",\"ret_msg\":null,\"tournament_name\":\"Dev Event Season 7\",\"winning_team_clan_id\":900}]";
        return (List<ProLeagueSeasonDetail>) newMock(json, List.class);
    }

    private List<ProLeagueSeasonDetail> createdProLeagueSeasonFail()  {
        String json = "[{\"away_team_clan_id\":null,\"away_team_name\":null,\"away_team_tagname\":null,\"home_team_clan_id\":null,\"home_team_name\":null,\"home_team_tagname\":null,\"map_instance_id\":null,\"match_date\":null,\"match_number\":null,\"match_status\":null,\"matchup_id\":null,\"region\":null,\"ret_msg\":\"Invalid SeasonId.\",\"tournament_name\":null,\"winning_team_clan_id\":null}]";
        return (List<ProLeagueSeasonDetail>) newMock(json, List.class);
    }

}
