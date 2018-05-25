package com.example.myrikao0517.ui.homepage.presenters;

import com.example.myrikao0517.base.BasePresenter;
import com.example.myrikao0517.bean.AdBean;
import com.example.myrikao0517.bean.CatagoryBean;
import com.example.myrikao0517.net.Ad.AdApi;
import com.example.myrikao0517.net.CatagoryApi;

import com.example.myrikao0517.ui.homepage.content.HomeContent;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePagePresenter extends BasePresenter<HomeContent.View> implements HomeContent.Presenter{

    private AdApi adApi;
    private CatagoryApi catagoryApi;
    @Inject
    public HomePagePresenter(AdApi adApid,CatagoryApi catagoryApi){
        this.adApi=adApid;
        this.catagoryApi=catagoryApi;




    }
    @Override
    public void getAd() {
        adApi.getAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AdBean adBean) {
                        if(mView!=null) {
                            mView.getAdSuccess(adBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getCatagory() {
        catagoryApi.getCatagory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                        if(mView!=null) {

                            mView.getCatagorySuccess(catagoryBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
