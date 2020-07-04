package br.com.victor.smite.service;

import br.com.victor.smite.Entity.Player;
import br.com.victor.smite.model.Session;
import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.client.HiRezSmiteApi;
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

import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTest {

    @Mock
    private HiRezSmiteApi hiRezSmiteApi;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private final String USERNAME = "Akillian";

    @Before
    public void setUp(){
        this.playerService = new PlayerService(hiRezSmiteApi, playerRepository); //inject the mock
    }

    @DisplayName("Testing Session creation Success")
    @Test
    public void testCreateSession_Success() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdSessionSuccess());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());
        when(playerRepository.save(Mockito.any())).thenReturn(playerSaved());

        Session session = playerService.createSession(USERNAME);
        Assert.assertEquals("Approved", session.getReturnedMessage());
        Assert.assertEquals("13E9A38C6F6E4701ADEA6F158F4DEC5F", session.getSessionId());
        Assert.assertEquals("7/4/2020 6:04:11 PM", session.getTimestamp());
    }

    @DisplayName("Testing Session creation Fail")
    @Test
    public void testCreateSession_Fail() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(createdSessionFail());
        when(playerRepository.findByUsername(USERNAME)).thenReturn(playerSuccess());
        when(playerRepository.save(Mockito.any())).thenReturn(playerSaved());

        Session session = playerService.createSession(USERNAME);
        Assert.assertEquals("Exception while validating developer access.Invalid signature.", session.getReturnedMessage());
        Assert.assertEquals("", session.getSessionId());
        Assert.assertEquals("7/4/2020 7:56:12 PM", session.getTimestamp());
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

    private Session createdSessionSuccess()  {
        String json = "{\"ret_msg\":\"Approved\",\"session_id\":\"13E9A38C6F6E4701ADEA6F158F4DEC5F\",\"timestamp\":\"7/4/2020 6:04:11 PM\"}";
        return (Session) newMock(json, Session.class);
    }

    private Player playerSuccess()  {
        String json = "{\"id\":2,\"username\":\"Akillian\",\"player_id\":\"000\"}";
        return (Player) newMock(json, Player.class);
    }

    private Player playerSaved()  {
        String json = "{\"id\":2,\"username\":\"Akillian\",\"player_id\":\"000\",\"last_session_id\":\"\"}";
        return (Player) newMock(json, Player.class);
    }

    private Session createdSessionFail() {
        String json = "{\"ret_msg\":\"Exception while validating developer access.Invalid signature.\",\"session_id\":\"\",\"timestamp\":\"7/4/2020 7:56:12 PM\"}";
        return (Session) newMock(json, Session.class);
    }


}
