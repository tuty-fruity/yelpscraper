package com.yelp.scraper.dto.gvisionapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageAttr {

    @JsonProperty(value = "source")
    private SourceAttr source;
}
