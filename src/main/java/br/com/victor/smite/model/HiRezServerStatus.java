package br.com.victor.smite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class HiRezServerStatus extends HiRezApiModel {
    @Nullable
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
