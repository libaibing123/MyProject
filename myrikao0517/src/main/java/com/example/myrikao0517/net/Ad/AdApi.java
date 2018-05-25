package com.example.myrikao0517.net.Ad;

import com.example.myrikao0517.bean.AdBean;

import io.reactivex.Observable;

public class AdApi {

    private static AdApi adApi;

    private  AdApiService adApiService;
    public AdApi(AdApiService adApiService){
        this.adApiService=adApiService;


    }

  public static  AdApi getAdApi(AdApiService adApiService){
        if(adApi==null){

            adApi=new AdApi(adApiService);


        }return adApi;


  }


  public Observable<AdBean> getAd(){

        return adApiService.getAd();


  }
}
