package com.goldencis.ganksample.http;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tom on 2018-1-22.
 */

public class Api {

    private static final String BASE_URL = "http://gank.io/api/data/";

    private static Retrofit retrofit;
    private static WealService wealService;

    private static Retrofit initRetrofit() {
        if (retrofit == null) {
            synchronized (Api.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OkHttpClient--->", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    public static WealService getWealService() {
        if (wealService == null) {
            synchronized (Api.class) {
                if (wealService == null) {
                    wealService = initRetrofit().create(WealService.class);
                }
            }
        }
        return wealService;
    }

}
