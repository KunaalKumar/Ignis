package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.Character;
import com.kunaalkumar.ignis.comicvine_objects.Result;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * NOTE: Format should always be json
 */

public interface ApiClient {

    @Headers("user-agent: android")

    @GET("search/")
    Call<ApiResponse<Result[]>> search(@Query("query") String search,
                                       @Query("api_key") String apiKey,
                                       @Query("format") String format);

    // Searches for character via given name
    @GET("characters/")
    Call<ApiResponse<CharacterBrief>> searchCharacters(@Query("filter") String filter,
                                                       @Query("api_key") String apiKey,
                                                       @Query("format") String format);

    // TODO: Test
    // Gets character via given character id
    @GET("character/4005-{character_id}/")
    Call<ApiResponse<Character>> getCharacter(@Path("character_id") Integer characterId,
                                              @Query("api_key") String apiKey,
                                              @Query("format") String format);
}
