package com.kunaalkumar.ignis.web_scraper;

import org.jsoup.nodes.Element;

public class SearchResult {

    private final String BASE_URI = "https://comicvine.gamespot.com";

    String name;
    String imageUrl;
    String type;
    String chipInfo;
    String resultUri;

    public SearchResult(Element result) {
        name = result.childNode(3).childNode(3).childNode(0).toString().trim();
        setImageUri(result.childNode(3).childNode(1).childNode(1).attr("src"));
        setType(result.childNode(3).childNode(5).childNode(1).childNode(0).toString().trim());
        resultUri = BASE_URI + result.children().attr("href");
    }

    private void setType(String type) {
        chipInfo = type;
        String[] arr = type.split(" ");
        this.type = arr[0];
    }

    // Replace low res image with high res image
    private void setImageUri(String image) {
        this.imageUrl = image.replaceAll("square_small", "scale_large");
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Character (14530 issues) (DC Comics)
    public String getType() {
        return type;
    }

    public String getResultUri() {
        return resultUri;
    }

    public String getChipInfo() {
        return chipInfo;
    }
}
