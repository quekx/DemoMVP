package com.example.qkx.demomvp.data.source.remote;

import com.example.qkx.demomvp.data.Welfare;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by qkx on 16/5/1.
 */
public interface MyHttpService {
    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Call<Welfare> queryPage(@Path("page") String page);

    @GET("random/data/%E7%A6%8F%E5%88%A9/10")
    Call<Welfare> queryRandom();
}
