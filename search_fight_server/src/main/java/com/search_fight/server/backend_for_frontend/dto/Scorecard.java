package com.search_fight.server.backend_for_frontend.dto;

import lombok.Value;

@Value
public class Scorecard {
    long searchTermOneGoogleHits;
    long searchTermTwoGoogleHits;
    long searchTermOneBingHits;
    long searchTermTwoBingHits;
    String googleWinner;
    String bingWinner;
    String overallWinner;
}
