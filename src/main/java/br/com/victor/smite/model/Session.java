package br.com.victor.smite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Session extends HiRezApiModel {

    @JsonProperty("session_id")
    private String sessionId;
    private String timestamp;

}
