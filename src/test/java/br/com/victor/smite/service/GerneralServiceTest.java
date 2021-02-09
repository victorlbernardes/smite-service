package br.com.victor.smite.service;

import br.com.victor.smite.service.Utils.UtilsTest;
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
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdItemSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<Item> listItems = generalService.getAllItens(USERNAME, 1);
        Assert.assertEquals(3, listItems.size());
    }

    @DisplayName("Testing Get All Items Fail")
    @Test
    public void testGetAllItems_Fail() {
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdItemFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<Item> listItems = generalService.getAllItens(USERNAME, 1);
        Assert.assertEquals(1, listItems.size());
    }

    @DisplayName("Testing Get All Gods Success")
    @Test
    public void testGetAllGods_Success() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodsSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<God> listGods = generalService.getAllGods(USERNAME, 1);
        Assert.assertEquals(2, listGods.size());
    }

    @DisplayName("Testing Get All Gods Fail")
    @Test
    public void testGetAllGods_Fail() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodsFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<God> listGods = generalService.getAllGods(USERNAME, 1);
        Assert.assertEquals(1, listGods.size());
    }

    @DisplayName("Testing Get All God Skin Success")
    @Test
    public void testGodSkin_Success() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodSkinSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(USERNAME, 1, 1);
        Assert.assertEquals(2, godSkinList.size());
    }

    @DisplayName("Testin Get All God Skin Fail")
    @Test
    public void testGodSkin_Fail() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodSkinFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(USERNAME, 1, 1);
        Assert.assertEquals(1, godSkinList.size());
    }

    @DisplayName("Testing Get God Recommended Items Success")
    @Test
    public void testGodRecommendedItems_Success() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodRecommendedItemsSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(USERNAME, 1, 1);
        Assert.assertEquals(2, recommendedItemsList.size());
    }

    @DisplayName("Testing Get God Recommended Items Fail")
    @Test
    public void testGetGodRecommendedItems_Fail() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdGodRecommendedItemsFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(USERNAME, 1, 1);
        Assert.assertEquals(1, recommendedItemsList.size());
    }

    @DisplayName("Testing Get Pro League Season Success")
    @Test
    public void testProLeagueSeason_Success() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdProLeagueSeasonSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason(USERNAME);
        Assert.assertEquals(2, proLeagueSeasonDetailList.size());
    }

    @DisplayName("Testing Get Pro League Season Fail")
    @Test
    public void testProLeagueSeason_Fail() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdProLeagueSeasonFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason(USERNAME);
        Assert.assertEquals(1, proLeagueSeasonDetailList.size());
    }

}
