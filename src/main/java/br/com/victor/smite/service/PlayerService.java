package br.com.victor.smite.service;

import br.com.victor.smite.service.client.response.Session;

public interface PlayerService {

    Session createSession(String username);
}
