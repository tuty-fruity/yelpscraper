package com.yelp.scraper.service;

import com.yelp.scraper.dto.Avatar;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class CloudVisionService {

    private final static String API_KEY = "AIzaSyBJVQh1rupj7PZu8w920bZUPW0YHZtFTI0";
    private final static String VISION_API = "https://vision.googleapis.com/v1/images:annotate";

    private final RestTemplate restTemplate;

    @Autowired
    public CloudVisionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void run(Avatar avatar) {
        // restTemplate.postForEntity(VISION_API, )
    }
}
