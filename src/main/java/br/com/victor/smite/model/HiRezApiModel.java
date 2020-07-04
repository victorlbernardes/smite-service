package br.com.victor.smite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class HiRezApiModel {

    @JsonProperty("ret_msg")
    private String returnedMessage;
}

