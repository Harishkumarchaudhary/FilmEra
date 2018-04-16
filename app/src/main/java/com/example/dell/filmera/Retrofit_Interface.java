package com.example.dell.filmera;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dell on 20-03-2018.
 */

public interface Retrofit_Interface{
    @GET("movie/popular?api_key=43a0943f86f6e8ed3c98741083eab9d9")
    Call<Api_class_for_popular> getresponse();
    @GET("movie/top_rated?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<Api_class_for_topRated> getresponse2();
    @GET("movie/{movie_id}/reviews?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForReviews> getresponse3(@Path("movie_id") int id);
    @GET("movie/{movie_id}/videos?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForVideos> getResponse4(@Path("movie_id") int id);
    @GET("movie/{movie_id}/credits?api_key=43a0943f86f6e8ed3c98741083eab9d9")
    Call<ApiClassForCast> getCast(@Path("movie_id") int id);
    @GET("movie/{movie_id}/similar?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForSimilarMovies> getSimilarMovies(@Path("movie_id") int id);
    @GET("movie/upcoming?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForUpcoming> getUpcoming();
   @GET("search/movie?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
   Call<ApiClassForSearch> getSearch(@Query("query") String query);
    @GET("person/{person_id}?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForMembers> getMemberDetails(@Path("person_id") int id);
    @GET("person/{person_id}/movie_credits?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForMemberOtherMovies> getOtherMovies(@Path("person_id") int id);
    @GET("tv/popular?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForTvPopular> getTvPopular();
    @GET("tv/on_the_air?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForTvOnTheAir> getTvOnTheAir();
    @GET("tv/top_rated?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForTvTopRated> getTvTopRated();
    @GET("tv/{tv_id}/credits?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForCast> gettvcast(@Path("tv_id") int id);
    @GET("tv/{tv_id}/similar?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US&page=1")
    Call<ApiClassForSimilarTvShows> getSimilarTvsows(@Path("tv_id") int id);
    @GET("tv/{tv_id}/videos?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForVideos> getTvVideos(@Path("tv_id") int id);
    @GET("person/{person_id}/tv_credits?api_key=43a0943f86f6e8ed3c98741083eab9d9&language=en-US")
    Call<ApiClassForTvMemberOtherShows> getOtherTvShows(@Path("person_id") int id);
}
