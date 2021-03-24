package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class HiRezApiModel {

    @JsonProperty("ret_msg")
    private String returnedMessage;
}

