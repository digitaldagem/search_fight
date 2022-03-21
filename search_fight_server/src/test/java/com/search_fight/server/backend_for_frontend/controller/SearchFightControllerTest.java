package com.search_fight.server.backend_for_frontend.controller;

import com.search_fight.server.backend_for_frontend.dto.Scorecard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchFightControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;

    static String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:"+randomServerPort+"/api/search_terms";
    }

    @Test
    void getScorecard_created() {
        // given
        String searchTermOne = "one", searchTermTwo = "two";

        // when
        ResponseEntity<?> responseEntity =
                restTemplate.getForEntity(url + "/" + searchTermOne + "/" + searchTermTwo, Scorecard.class);
        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void getScorecard_not_created() {
        // given
        String searchTermOne = "one", searchTermTwo = "";

        // when
        ResponseEntity<?> responseEntity =
                restTemplate.getForEntity(url + "/" + searchTermOne + "/" + searchTermTwo, Scorecard.class);

        // then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
