package br.com.victor.smite.service;

import br.com.victor.smite.service.Utils.UtilsTest;
import br.com.victor.smite.service.client.response.Session;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.service.client.HiRezSmiteApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTest {

    @Mock
    private HiRezSmiteApi hiRezSmiteApi;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private final String USERNAME = "Akillian";

    @Before
    public void setUp(){
        this.playerService = new PlayerServiceImpl(hiRezSmiteApi, playerRepository); //inject the mock
    }

    @DisplayName("Testing Session creation Success")
    @Test
    public void testCreateSession_Success() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdSessionSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());
        when(playerRepository.save(Mockito.any())).thenReturn(UtilsTest.playerSaved());

        Session session = playerService.createSession(USERNAME);
        Assert.assertEquals("Approved", session.getReturnedMessage());
        Assert.assertEquals("13E9A38C6F6E4701ADEA6F158F4DEC5F", session.getSessionId());
        Assert.assertEquals("7/4/2020 6:04:11 PM", session.getTimestamp());
    }

    @DisplayName("Testing Session creation Fail")
    @Test
    public void testCreateSession_Fail() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UtilsTest.createdSessionFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(UtilsTest.playerSuccess());
        when(playerRepository.save(Mockito.any())).thenReturn(UtilsTest.playerSaved());

        Session session = playerService.createSession(USERNAME);
        Assert.assertEquals("Exception while validating developer access.Invalid signature.", session.getReturnedMessage());
        Assert.assertEquals("", session.getSessionId());
        Assert.assertEquals("7/4/2020 7:56:12 PM", session.getTimestamp());
    }

}
