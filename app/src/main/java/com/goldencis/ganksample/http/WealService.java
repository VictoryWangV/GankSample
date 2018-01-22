package com.goldencis.ganksample.http;

import com.goldencis.ganksample.main.WealEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Tom on 2018-1-22.
 */

public interface WealService {

    @GET("福利/{count}/{page}")
    Call<WealEntity> getData(@Path("count") int count, @Path("page") int page);

}
