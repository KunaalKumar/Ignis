package com.kunaalkumar.ignis.superhero_data;

public class SuperheroResults {
    private String response;
    private String response_for;
    private Results results;

    public SuperheroResults(String response, String response_for, Results results) {
        this.response = response;
        this.response_for = response_for;
        this.results = results;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse_for() {
        return response_for;
    }

    public void setResponse_for(String response_for) {
        this.response_for = response_for;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
