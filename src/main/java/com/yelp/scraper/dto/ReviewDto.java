package com.yelp.scraper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private String name;
    private String address;
    private String reviewText;

    private Avatar avatar;
}
