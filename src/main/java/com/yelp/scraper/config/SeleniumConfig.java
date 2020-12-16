package com.yelp.scraper.config;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class SeleniumConfig {

    @Bean
    public WebDriver webDriver() {
       return new ChromeDriver();
    }

    static {
        System.setProperty("webdriver.gecko.driver", findFile("geckodriver"));
        System.setProperty("webdriver.chrome.driver", findFile("chromedriver"));
    }

    private static String findFile(String filename) {
        String paths[] = {"src/main/resources/", "target/classes/"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }
}
