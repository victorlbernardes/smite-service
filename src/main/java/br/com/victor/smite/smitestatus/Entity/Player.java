package br.com.victor.smite.smitestatus.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PLAYER")
@Getter
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("player_id")
    private String playerId;
    private String username;
    @JsonIgnore
    private String password;
    @Setter
    @JsonProperty("last_session_id")
    private String lastSessionId;
}
