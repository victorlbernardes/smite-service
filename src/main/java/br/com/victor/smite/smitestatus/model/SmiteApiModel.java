package br.com.victor.smite.smitestatus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class SmiteApiModel {

    @JsonProperty("ret_msg")
    private String returnedMessage;
}

