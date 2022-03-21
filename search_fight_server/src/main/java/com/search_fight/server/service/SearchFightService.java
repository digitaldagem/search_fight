package com.search_fight.server.service;

import com.search_fight.server.backend_for_frontend.dto.Scorecard;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
@PropertySource("classpath:/application.properties")
public class SearchFightService {

    @Value("${search_url.engine_context.google}")
    private String googleContext;
    @Value("${search_url.api_key.google}")
    private String googleApiKey;
    @Value("${search_url.api_key.bing}")
    private String bingApiKey;

    public Scorecard getScorecard(String searchTermOne, String searchTermTwo) throws InterruptedException {

        long searchTermOneGoogleHits = getGoogleSearchTotalResults(URLEncoder.encode(searchTermOne, StandardCharsets.UTF_8));
        long searchTermOneBingHits = getBingSearchTotalEstimatedMatches(URLEncoder.encode(searchTermOne, StandardCharsets.UTF_8));
        Thread.sleep(1000);
        long searchTermTwoGoogleHits = getGoogleSearchTotalResults(URLEncoder.encode(searchTermTwo, StandardCharsets.UTF_8));
        long searchTermTwoBingHits = getBingSearchTotalEstimatedMatches(URLEncoder.encode(searchTermTwo, StandardCharsets.UTF_8));

        String googleWinner, bingWinner, overallWinner;

        if (searchTermOneGoogleHits > searchTermTwoGoogleHits)
            googleWinner = searchTermOne;
        else
            googleWinner = searchTermTwo;
        if (searchTermOneBingHits > searchTermTwoBingHits)
            bingWinner = searchTermOne;
        else
            bingWinner = searchTermTwo;
        if ((searchTermOneGoogleHits + searchTermOneBingHits) > (searchTermTwoGoogleHits + searchTermTwoBingHits))
            overallWinner = searchTermOne;
        else
            overallWinner = searchTermTwo;
        return new Scorecard(searchTermOneGoogleHits, searchTermTwoGoogleHits, searchTermOneBingHits,
                searchTermTwoBingHits, googleWinner, bingWinner, overallWinner);
    }

    private long getGoogleSearchTotalResults(String searchTerm) {
        String googleUrl = "https://www.googleapis.com/customsearch/v1?" +
                "key=" + googleApiKey + "&cx=" + googleContext + "&q=";
        JSONObject jsonResponse = getResponseAsJSONObject(googleUrl + searchTerm, "key", googleApiKey);
        checkJsonResponseThrowExceptionIfNull(jsonResponse, "Google Api Quota reached. Try again tomorrow.");
        String requestArrayElementString = jsonResponse.getJSONObject("queries").getJSONArray("request").get(0).toString();
        JSONObject requestArrayElementJson = new JSONObject(requestArrayElementString);
        String totalResults = requestArrayElementJson.getString("totalResults");
        return Long.parseLong(totalResults);
    }

    private long getBingSearchTotalEstimatedMatches(String searchTerm) {
        String bingUrl = "https://api.bing.microsoft.com/v7.0/custom/search?setLang=en&" +
                "customConfig=7b55709f-86d3-4766-89ad-03f330472391&responseFilter=Webpages&q=";
        JSONObject jsonResponse =
                getResponseAsJSONObject(bingUrl + searchTerm, "Ocp-Apim-Subscription-Key", bingApiKey);
        checkJsonResponseThrowExceptionIfNull(jsonResponse, "Bing Api Quota reached. Try again tomorrow.");
        String totalEstimatedMatches = jsonResponse.getJSONObject("webPages").get("totalEstimatedMatches").toString();
        return Long.parseLong(totalEstimatedMatches);
    }

    private JSONObject getResponseAsJSONObject(String url, String key, String value) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .setHeader("Content-Type", "application/json")
                .setHeader(key, value)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkJsonResponseThrowExceptionIfNull(JSONObject jsonResponse, String message) {
        if (jsonResponse == null) {
            throw new RuntimeException(message);
        }
    }
}
