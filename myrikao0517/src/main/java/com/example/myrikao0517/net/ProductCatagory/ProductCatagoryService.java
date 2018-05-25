package com.example.myrikao0517.net.ProductCatagory;

import com.example.myrikao0517.bean.ProductCatagoryBean;

import dagger.Module;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductCatagoryService {
    @FormUrlEncoded
    @POST("product/getProductCatagory")
    Observable<ProductCatagoryBean> getProductCatagory(@Field("cid") String cid);

}
