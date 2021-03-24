package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HiRezServerStatus extends HiRezApiModel {
    @JsonProperty("entry_datetime")
    private String entryDatetime;
    private String environment;
    private boolean limitedAccess;
    private String platform;
    private Status status;
    private String version;

    public enum Status {
        UP,
        DOWN
    }
}
