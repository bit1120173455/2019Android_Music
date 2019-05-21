package com.example.zhaomingyang.music.network;

import com.example.zhaomingyang.music.bean.Cat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICatService {
    @GET("v1/images/search") Call<List<Cat>> randomcat(@Query("limit") int limit);
}
