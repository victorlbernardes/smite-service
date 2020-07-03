package br.com.victor.smite.smitestatus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Session extends SmiteApiModel {

    @JsonProperty("session_id")
    private String sessionId;
    private String timestamp;

}
