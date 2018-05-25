package com.example.myrikao0517.ui.classfiy.presenter;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.base.BasePresenter;
import com.example.myrikao0517.bean.CatagoryBean;
import com.example.myrikao0517.bean.ProductCatagoryBean;
import com.example.myrikao0517.net.Catagory.CatagoryApi;
import com.example.myrikao0517.net.ProductCatagory.ProductCatagoryApi;
import com.example.myrikao0517.ui.classfiy.content.ClassifyConntet;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassifyPresenter extends BasePresenter<ClassifyConntet.View> implements ClassifyConntet.Presenter {
  private CatagoryApi catagoryApi;
  private ProductCatagoryApi productCatagoryApi;
    @Inject
    public ClassifyPresenter(CatagoryApi catagoryApi, ProductCatagoryApi productCatagoryApi) {
        this.catagoryApi = catagoryApi;
        this.productCatagoryApi = productCatagoryApi;
    }







    @Override
    public void getProductCatagory(String pid) {
        productCatagoryApi.getProductCatagory(pid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductCatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductCatagoryBean productCatagoryBean) {
                        if(mView!=null){
                            mView.getProductCatagorySuccess(productCatagoryBean);

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
                    if(mView!=null){
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
