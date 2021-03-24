package br.com.victor.smite.service;

import br.com.victor.smite.Utils.UtilsTest;
import br.com.victor.smite.entity.Player;
import br.com.victor.smite.service.client.response.Session;
import br.com.victor.smite.service.client.HiRezSmiteApi;
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
public class AuthenticationServiceTest {

    @Mock
    private HiRezSmiteApi hiRezSmiteApi;

    @InjectMocks
    private AuthenticationServiceImpl playerService;

    @Before
    public void setUp(){
        this.playerService = new AuthenticationServiceImpl(hiRezSmiteApi); //inject the mock
    }

    @DisplayName("Testing Session creation Success")
    @Test
    public void testCreateSession_Success() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdSessionSuccess());

        Session session = playerService.createSession();
        Assert.assertEquals("Approved", session.getReturnedMessage());
        Assert.assertEquals("13E9A38C6F6E4701ADEA6F158F4DEC5F", session.getSessionId());
        Assert.assertEquals("7/4/2020 6:04:11 PM", session.getTimestamp());
    }

    @DisplayName("Testing Session creation Fail")
    @Test
    public void testCreateSession_Fail() {
        when(hiRezSmiteApi.createSession(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(UtilsTest.createdSessionFail());

        Session session = playerService.createSession();
        Assert.assertEquals("Exception while validating developer access.Invalid signature.", session.getReturnedMessage());
        Assert.assertEquals("", session.getSessionId());
        Assert.assertEquals("7/4/2020 7:56:12 PM", session.getTimestamp());
    }

}
