package com.yelp.scraper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avatar {
    private String url;

    // vision api data
    private String joy;
    private String sorrow;
}
