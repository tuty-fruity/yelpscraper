package com.yelp.scraper.controller;

import com.yelp.scraper.dto.ReviewDto;
import com.yelp.scraper.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ScrapeController {

    private final ScraperService scraper;

    @Autowired
    public ScrapeController(ScraperService scraper) {
        this.scraper = scraper;
    }

    @RequestMapping(value = "/yelp/{restaurant}/{city}")
    public List<ReviewDto> foo(@PathVariable String restaurant, @PathVariable String city) {
        String baseUrl = "https://www.yelp.com/biz";
        String restaurantUrl = String.join("/", baseUrl, restaurant + "-" + city);
        return this.scraper.scrape(restaurantUrl);
    }
}
