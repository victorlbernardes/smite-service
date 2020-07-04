package br.com.victor.smite.service;

import br.com.victor.smite.Entity.Player;
import br.com.victor.smite.client.HiRezSmiteApi;
import br.com.victor.smite.model.DataUsage;
import br.com.victor.smite.model.HiRezServerStatus;
import br.com.victor.smite.model.SmitePatchVersion;
import br.com.victor.smite.repository.PlayerRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class InfoStatusServiceTest {
    @Mock
    private HiRezSmiteApi hiRezSmiteApi;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private InfoStatusService infoStatusService;

    private final String USERNAME = "Akillian";

    @Before
    public void setUp(){
        this.infoStatusService = new InfoStatusService(hiRezSmiteApi, playerRepository); //inject the mock
    }

    @DisplayName("Testing ping")
    @Test
    public void testPing_Success() {
        when(hiRezSmiteApi.ping()).thenReturn("SmiteAPI (ver 3.24.0.27286) [PATCH - 7.6] - Ping successful. Server Date:7/4/2020 8:03:29 PM");

        String pingResponse = infoStatusService.ping();
        Assert.assertEquals("SmiteAPI (ver 3.24.0.27286) [PATCH - 7.6] - Ping successful. Server Date:7/4/2020 8:03:29 PM", pingResponse);
    }

    @DisplayName("Testing session")
    @Test
    public void testSession_Fail() {
        when(hiRezSmiteApi.testSession(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("This was a successful test with the following parameters added: developer: 3526 time: 7/4/2020 8:13:20 PM signature: 1466a0f6d51764d67358564c6b713c1f session: 7E637098219745E291614146C1415876");
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        String testSession = infoStatusService.testSession(USERNAME);
        Assert.assertEquals("This was a successful test with the following parameters added: developer: 3526 time: 7/4/2020 8:13:20 PM signature: 1466a0f6d51764d67358564c6b713c1f session: 7E637098219745E291614146C1415876", testSession);
    }

    @DisplayName("Testing data usage Success")
    @Test
    public void testDataUsage_Success() {
        when(hiRezSmiteApi.dataUsage(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdDataUsageSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<DataUsage> listDataUsage = infoStatusService.dataUsage(USERNAME);
        Assert.assertEquals(1, listDataUsage.get(0).getActiveSessions());
        Assert.assertEquals(50, listDataUsage.get(0).getConcurrentSessions());
        Assert.assertEquals(7500, listDataUsage.get(0).getRequestLimitDaily());
        Assert.assertEquals(500, listDataUsage.get(0).getSessionCap());
        Assert.assertEquals(15, listDataUsage.get(0).getSessionTimeLimit());
        Assert.assertEquals(38, listDataUsage.get(0).getTotalRequestsToday());
        Assert.assertEquals(14, listDataUsage.get(0).getTotalSessionsToday());
        Assert.assertNull(listDataUsage.get(0).getReturnedMessage());
    }

    @DisplayName("Testing data usage Fail")
    @Test
    public void testDataUsage_Fail() {
        when(hiRezSmiteApi.dataUsage(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdDataUsageFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<DataUsage> dataUsage = infoStatusService.dataUsage(USERNAME);
        Assert.assertEquals(0, dataUsage.get(0).getActiveSessions());
        Assert.assertEquals(0, dataUsage.get(0).getConcurrentSessions());
        Assert.assertEquals(0, dataUsage.get(0).getRequestLimitDaily());
        Assert.assertEquals(0, dataUsage.get(0).getSessionCap());
        Assert.assertEquals(0, dataUsage.get(0).getSessionTimeLimit());
        Assert.assertEquals(0, dataUsage.get(0).getTotalRequestsToday());
        Assert.assertEquals(0, dataUsage.get(0).getTotalSessionsToday());
        Assert.assertEquals("Invalid signature.", dataUsage.get(0).getReturnedMessage());
    }

    @DisplayName("Testing Hi Rez Service status Success")
    @Test
    public void testHiRezSeverStatus_Success() {
        when(hiRezSmiteApi.hiRezServerStatus(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdServerStautsSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<HiRezServerStatus> listHiRezServerStatus = infoStatusService.hiRezServerStatus(USERNAME);
        Assert.assertEquals(5, listHiRezServerStatus.size());
    }

    @DisplayName("Testing Hi Rez Service status Fail")
    @Test
    public void testHiRezSeverStatus_Fail() {
        when(hiRezSmiteApi.hiRezServerStatus(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdServerStautsFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        List<HiRezServerStatus> listHiRezServerStatus = infoStatusService.hiRezServerStatus(USERNAME);
        Assert.assertEquals(1, listHiRezServerStatus.size());
    }

    @DisplayName("Testing Smite Patch Version Success")
    @Test
    public void testSmitePatchVersion_Success() {
        when(hiRezSmiteApi.smitePatchVersion(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdSmitePatchVersionSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        SmitePatchVersion smitePatchVersion = infoStatusService.smitePatchVersion(USERNAME);
        Assert.assertNull(smitePatchVersion.getReturnedMessage());
        Assert.assertEquals("7.6", smitePatchVersion.getVersionString());
    }

    @DisplayName("Testing Smite Patch Version Fail")
    @Test
    public void testSmitePatchVersion_Fail() {
        when(hiRezSmiteApi.smitePatchVersion(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdSmitePatchVersionFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());

        SmitePatchVersion smitePatchVersion = infoStatusService.smitePatchVersion(USERNAME);
        Assert.assertEquals("Invalid signature.", smitePatchVersion.getReturnedMessage());
        Assert.assertNull(smitePatchVersion.getVersionString());
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

    private List<DataUsage> createdDataUsageSuccess()  {
        String json = "{\"Active_Sessions\":1,\"Concurrent_Sessions\":50,\"Request_Limit_Daily\":7500,\"Session_Cap\":500,\"Session_Time_Limit\":15,\"Total_Requests_Today\":38,\"Total_Sessions_Today\":14,\"ret_msg\":null}";
        DataUsage dataUsage =  (DataUsage) newMock(json, DataUsage.class);
        return Arrays.asList(dataUsage);
    }

    private Player playerSuccess()  {
        String json = "{\"id\":2,\"username\":\"Akillian\",\"player_id\":\"000\"}";
        return (Player) newMock(json, Player.class);
    }

    private List<DataUsage> createdDataUsageFail()  {
        String json = "{\"Active_Sessions\":0,\"Concurrent_Sessions\":0,\"Request_Limit_Daily\":0,\"Session_Cap\":0,\"Session_Time_Limit\":0,\"Total_Requests_Today\":0,\"Total_Sessions_Today\":0,\"ret_msg\":\"Invalid signature.\"}";
        DataUsage dataUsage =  (DataUsage) newMock(json, DataUsage.class);
        return Arrays.asList(dataUsage);
    }

    private List<HiRezServerStatus> createdServerStautsSuccess()  {
        String json = "[{\"entry_datetime\":\"2020-07-04 20:58:53.681\",\"environment\":\"live\",\"limited_access\":false,\"platform\":\"pc\",\"ret_msg\":null,\"status\":\"UP\",\"version\":\"7.6.6084.6\"},{\"entry_datetime\":\"2020-07-04 20:58:53.681\",\"environment\":\"live\",\"limited_access\":false,\"platform\":\"switch\",\"ret_msg\":null,\"status\":\"UP\",\"version\":\"7.6.6084.6\"},{\"entry_datetime\":\"2020-07-04 20:58:53.681\",\"environment\":\"live\",\"limited_access\":false,\"platform\":\"xbox\",\"ret_msg\":null,\"status\":\"UP\",\"version\":\"7.6.6084.6\"},{\"entry_datetime\":\"2020-07-04 20:58:53.681\",\"environment\":\"live\",\"limited_access\":false,\"platform\":\"ps4\",\"ret_msg\":null,\"status\":\"UP\",\"version\":\"7.6.6084.6\"},{\"entry_datetime\":null,\"environment\":\"pts\",\"limited_access\":false,\"platform\":\"pc\",\"ret_msg\":null,\"status\":\"UP\",\"version\":\"7.7.6119.0\"}]";
        return (List<HiRezServerStatus>) newMock(json, List.class);
    }

    private List<HiRezServerStatus> createdServerStautsFail()  {
        String json = "[{\"entry_datetime\":null,\"environment\":null,\"limited_access\":false,\"platform\":null,\"ret_msg\":\"Invalid session id.\",\"status\":null,\"version\":null}]";
        DataUsage dataUsage =  (DataUsage) newMock(json, DataUsage.class);
        return (List<HiRezServerStatus>) newMock(json, List.class);
    }

    private SmitePatchVersion createdSmitePatchVersionSuccess()  {
        String json = "{\"ret_msg\":null,\"version_string\":\"7.6\"}";
        return (SmitePatchVersion) newMock(json, SmitePatchVersion.class);
    }

    private SmitePatchVersion createdSmitePatchVersionFail()  {
        String json = "{\"ret_msg\":\"Invalid signature.\",\"version_string\":null}";
        return (SmitePatchVersion) newMock(json, SmitePatchVersion.class);
    }
}
