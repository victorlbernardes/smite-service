package br.com.victor.smite.service.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DataUsage extends HiRezApiModel {
    @JsonProperty("Active_Sessions")
    private int activeSessions;
    @JsonProperty("Concurrent_Sessions")
    private int concurrentSessions;
    @JsonProperty("Request_Limit_Daily")
    private long requestLimitDaily;
    @JsonProperty("Session_Cap")
    private int sessionCap;
    @JsonProperty("Session_Time_Limit")
    private long sessionTimeLimit;
    @JsonProperty("Total_Requests_Today")
    private long totalRequestsToday;
    @JsonProperty("Total_Sessions_Today")
    private long totalSessionsToday;
}
