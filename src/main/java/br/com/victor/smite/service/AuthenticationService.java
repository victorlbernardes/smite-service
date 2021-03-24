package br.com.victor.smite.service;
import br.com.victor.smite.service.client.response.Session;

public interface AuthenticationService {
    public Session createSession();

}
