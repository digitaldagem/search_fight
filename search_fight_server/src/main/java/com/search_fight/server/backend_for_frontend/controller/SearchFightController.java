package com.search_fight.server.backend_for_frontend.controller;

import com.search_fight.server.service.SearchFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search_terms")
@CrossOrigin(origins = "*")
public class SearchFightController {

    private final SearchFightService searchFightService;

    @Autowired
    public SearchFightController(SearchFightService searchFightService) {
        this.searchFightService = searchFightService;
    }

    @GetMapping("/{searchTermOne}/{searchTermTwo}")
    public ResponseEntity<?> getScorecard(@PathVariable("searchTermOne") String searchTermOne,
                                          @PathVariable("searchTermTwo") String searchTermTwo) {
        try {
            return new ResponseEntity<>(
                    searchFightService.getScorecard(searchTermOne, searchTermTwo), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
