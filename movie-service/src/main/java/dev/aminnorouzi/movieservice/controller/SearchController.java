package dev.aminnorouzi.movieservice.controller;

import dev.aminnorouzi.movieservice.model.Search;
import dev.aminnorouzi.movieservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/searches")
public class SearchController {

    private final SearchService searchService;

//    @GetMapping
//    public List<Search> searchMovies(@RequestParam String query) {
//        return searchService.search(query);
//    }
}
