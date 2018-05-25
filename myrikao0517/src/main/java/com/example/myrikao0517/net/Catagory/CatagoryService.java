package com.example.myrikao0517.net.Catagory;

import com.example.myrikao0517.bean.CatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CatagoryService {
    @GET("product/getCatagory")
    Observable<CatagoryBean> getCatagory();

}
