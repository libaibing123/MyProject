package com.example.myproject.module;

import com.example.myproject.net.Api;
import com.example.myproject.net.LgoinApi;
import com.example.myproject.net.LgoinApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS);


    }
    @Provides
    LgoinApi provideLgoinApi(OkHttpClient.Builder builder){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        LgoinApiService lgoinApiService = build.create(LgoinApiService.class);
        return LgoinApi.getLgoinApi(lgoinApiService);


    }
}
