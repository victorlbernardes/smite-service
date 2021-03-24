package br.com.victor.smite.service;

import br.com.victor.smite.Utils.UtilsTest;
import br.com.victor.smite.service.client.HiRezSmiteApi;
import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.response.God;
import br.com.victor.smite.service.client.response.GodRecommendedItems;
import br.com.victor.smite.service.client.response.Item;
import br.com.victor.smite.service.client.response.ProLeagueSeasonDetail;
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
    private AuthenticationService authenticationService;

    @InjectMocks
    private GeneralServiceImpl generalService;

    private final String USERNAME = "Akillian";

    @Before
    public void setUp(){
        this.generalService = new GeneralServiceImpl(hiRezSmiteApi, authenticationService); //inject the mock
    }

    @DisplayName("Testing Get All Items Success")
    @Test
    public void testGetAllItems_Success() {
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdItemSuccess());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<Item> listItems = generalService.getAllItens(1);
        Assert.assertEquals(3, listItems.size());
    }

    @DisplayName("Testing Get All Items Fail")
    @Test
    public void testGetAllItems_Fail() {
        when(hiRezSmiteApi.getAllItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdItemFail());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<Item> listItems = generalService.getAllItens(1);
        Assert.assertEquals(1, listItems.size());
    }

    @DisplayName("Testing Get All Gods Success")
    @Test
    public void testGetAllGods_Success() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodsSuccess());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<God> listGods = generalService.getAllGods(1);
        Assert.assertEquals(2, listGods.size());
    }

    @DisplayName("Testing Get All Gods Fail")
    @Test
    public void testGetAllGods_Fail() {
        when(hiRezSmiteApi.getAllGods(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodsFail());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<God> listGods = generalService.getAllGods(1);
        Assert.assertEquals(1, listGods.size());
    }

    @DisplayName("Testing Get All God Skin Success")
    @Test
    public void testGodSkin_Success() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodSkinSuccess());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(1, 1);
        Assert.assertEquals(2, godSkinList.size());
    }

    @DisplayName("Testin Get All God Skin Fail")
    @Test
    public void testGodSkin_Fail() {
        when(hiRezSmiteApi.getAllGodSkin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodSkinFail());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<GodSkin> godSkinList = generalService.getAllGodSkin(1, 1);
        Assert.assertEquals(1, godSkinList.size());
    }

    @DisplayName("Testing Get God Recommended Items Success")
    @Test
    public void testGodRecommendedItems_Success() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodRecommendedItemsSuccess());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(1, 1);
        Assert.assertEquals(2, recommendedItemsList.size());
    }

    @DisplayName("Testing Get God Recommended Items Fail")
    @Test
    public void testGetGodRecommendedItems_Fail() {
        when(hiRezSmiteApi.getGodRecommendedItems(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdGodRecommendedItemsFail());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<GodRecommendedItems> recommendedItemsList = generalService.getGodRecommendedItems(1, 1);
        Assert.assertEquals(1, recommendedItemsList.size());
    }

    @DisplayName("Testing Get Pro League Season Success")
    @Test
    public void testProLeagueSeason_Success() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdProLeagueSeasonSuccess());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason();
        Assert.assertEquals(2, proLeagueSeasonDetailList.size());
    }

    @DisplayName("Testing Get Pro League Season Fail")
    @Test
    public void testProLeagueSeason_Fail() {
        when(hiRezSmiteApi.proLeagueSeason(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdProLeagueSeasonFail());
        when(authenticationService.createSession()).thenReturn(UtilsTest.createdSessionSuccess());

        List<ProLeagueSeasonDetail> proLeagueSeasonDetailList = generalService.proLeagueSeason();
        Assert.assertEquals(1, proLeagueSeasonDetailList.size());
    }

}
