package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.superhero_data.SuperheroResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuperheroAPIEndpointInterface {

    @GET("search/{superheroSearch}")
    Call<SuperheroResults> getSuperheroResults(@Path("superheroSearch") String superheroSearch);
}
