package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("medium_url")
    private String mediumUrl;

    @SerializedName("screen_url")
    private String screenUrl;

    @SerializedName("screen_large_url")
    private String screenLargeUrl;

    @SerializedName("small_url")
    private String smallUrl;

    @SerializedName("super_url")
    private String superUrl;

    @SerializedName("thumb_url")
    private String thumbUrl;

    @SerializedName("tiny_url")
    private String tinyUrl;

    @SerializedName("original_url")
    private String originalUrl;

    public Image(String iconUrl, String mediumUrl, String screenUrl, String screenLargeUrl, String smallUrl, String superUrl, String thumbUrl, String tinyUrl, String originalUrl) {
        this.iconUrl = iconUrl;
        this.mediumUrl = mediumUrl;
        this.screenUrl = screenUrl;
        this.screenLargeUrl = screenLargeUrl;
        this.smallUrl = smallUrl;
        this.superUrl = superUrl;
        this.thumbUrl = thumbUrl;
        this.tinyUrl = tinyUrl;
        this.originalUrl = originalUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getScreenUrl() {
        return screenUrl;
    }

    public void setScreenUrl(String screenUrl) {
        this.screenUrl = screenUrl;
    }

    public String getScreenLargeUrl() {
        return screenLargeUrl;
    }

    public void setScreenLargeUrl(String screenLargeUrl) {
        this.screenLargeUrl = screenLargeUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getSuperUrl() {
        return superUrl;
    }

    public void setSuperUrl(String superUrl) {
        this.superUrl = superUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}