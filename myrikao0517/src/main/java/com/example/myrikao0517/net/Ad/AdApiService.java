package com.example.myrikao0517.net.Ad;

import com.example.myrikao0517.bean.AdBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AdApiService {
    @GET("ad/getAd")
    Observable<AdBean> getAd();

}
