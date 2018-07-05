package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {

    @SerializedName("error")
    private String error;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("number_of_page_results")
    private Integer numberOfPageResults;

    @SerializedName("number_of_total_results")
    private Integer numberOfTotalResults;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("results")
    private T[] results;

    @SerializedName("version")
    private String version;

    public ApiResponse(String error, Integer limit, Integer offset, Integer numberOfPageResults, Integer numberOfTotalResults, Integer statusCode, T[] results, String version) {
        this.error = error;
        this.limit = limit;
        this.offset = offset;
        this.numberOfPageResults = numberOfPageResults;
        this.numberOfTotalResults = numberOfTotalResults;
        this.statusCode = statusCode;
        this.results = results;
        this.version = version;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumberOfPageResults() {
        return numberOfPageResults;
    }

    public void setNumberOfPageResults(Integer numberOfPageResults) {
        this.numberOfPageResults = numberOfPageResults;
    }

    public Integer getNumberOfTotalResults() {
        return numberOfTotalResults;
    }

    public void setNumberOfTotalResults(Integer numberOfTotalResults) {
        this.numberOfTotalResults = numberOfTotalResults;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T[] getResults() {
        return results;
    }

    public void setResults(T[] results) {
        this.results = results;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

