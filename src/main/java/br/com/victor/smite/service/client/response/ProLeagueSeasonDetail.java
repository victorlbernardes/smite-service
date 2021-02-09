package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProLeagueSeasonDetail extends HiRezApiModel {
    @JsonProperty("away_team_clan_id")
    private long awayTeamClanId;
    @JsonProperty("away_team_name")
    private String awayTeamName;
    @JsonProperty("away_team_tagname")
    private String awayTeamTagname;
    @JsonProperty("home_team_clan_id")
    private long homeTeamClanId;
    @JsonProperty("home_team_name")
    private String homeTeamName;
    @JsonProperty("home_team_tagname")
    private String homeTeamTagname;
    @JsonProperty("map_instance_id")
    private int mapInstanceId;
    @JsonProperty("match_date")
    private String matchDate;
    @JsonProperty("match_number")
    private int matchNumber;
    @JsonProperty("match_status")
    private String matchStatus;
    @JsonProperty("matchup_id")
    private long matchupId;
    private String region;
    @JsonProperty("tournament_name")
    private String tournamentName;
    @JsonProperty("winning_team_clan_id")
    private long winningTeamClanId;
}
