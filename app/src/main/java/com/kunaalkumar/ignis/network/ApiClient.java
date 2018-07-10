package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Character;
import com.kunaalkumar.ignis.comicvine_objects.SearchResult;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.OriginBrief;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Origin;

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

    // Searches everything for given query
    @GET("search/")
    Call<ApiResponse<SearchResult[]>> search(@Query("query") String search,
                                             @Query("api_key") String apiKey,
                                             @Query("format") String format);

     /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                General




     */

    // General character search
    @GET("characters/")
    Call<ApiResponse<CharacterBrief[]>> searchCharacters(@Query("filter") String filter,
                                                         @Query("api_key") String apiKey,
                                                         @Query("format") String format);

    // General origin search
    @GET("origins/")
    Call<ApiResponse<OriginBrief>> searchOrigins(@Query("filter") String filter,
                                                 @Query("api_key") String apiKey,
                                                 @Query("format") String format);

      /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                Brief




     */

    // Gets detailed information about a character
    @GET("character/4005-{character_id}/")
    Call<ApiResponse<Character>> getCharacter(@Path("character_id") Integer characterId,
                                              @Query("api_key") String apiKey,
                                              @Query("format") String format);

    // Gets detailed information about a origin
    @GET("origin/4030-{origin_id}/")
    Call<ApiResponse<Origin>> getOrigin(@Path("origin_id") Integer originId,
                                        @Query("api_key") String apiKey,
                                        @Query("format") String format);
}
