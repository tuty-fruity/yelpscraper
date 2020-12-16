package com.yelp.scraper.service;

import com.yelp.scraper.dto.Avatar;
import com.yelp.scraper.dto.ReviewDto;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ScraperService {

    private final WebDriver webDriver;
    private final CloudVisionService cloudVisionService;

    @Autowired
    public ScraperService(WebDriver webDriver, CloudVisionService cloudVisionService) {
        this.webDriver = webDriver;
        this.cloudVisionService = cloudVisionService;
    }

    public List<ReviewDto> scrape(String url) {
        log.info("scraping {}", url);
        webDriver.get(url);

        List<WebElement> reviewDiv = webDriver.findElements(By.cssSelector("div[class*='review__']"));
        List<ReviewDto> reviews = new ArrayList<>();

        for (WebElement reviewItem : reviewDiv) {
            ReviewDto reviewDto = new ReviewDto();

            // reviewer info
            List<WebElement> spans = reviewItem.findElements(By.cssSelector("span"));
            String name = spans.get(0).getText();
            String address = spans.get(1).getText();
            String reviewText = spans.stream()
                    .filter(span -> span.getAttribute("lang").equals("en"))
                    .findFirst()
                    .get()
                    .getText();

            reviewDto.setName(name);
            reviewDto.setAddress(address);
            reviewDto.setReviewText(reviewText);

            // reviewer avatar info
            String avatarUrl = reviewItem.findElement(By.cssSelector("img")).getAttribute("src")
                    .replace("60", "180");
            Avatar avatar = new Avatar();
            avatar.setUrl(avatarUrl);
            cloudVisionService.run(avatar);

            reviewDto.setAvatar(avatar);

            reviews.add(reviewDto);
        }

        return reviews;
    }
}