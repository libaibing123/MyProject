package com.example.myproject.base;

public class BasePresenter<T extends BaseContent.BaseView> implements BaseContent.BasePresenter<T> {
    protected  T mView;


    @Override
    public void attachView(T view) {
        if(mView!=null){
            this.mView=view;


        }
    }

    @Override
    public void dettachView() {
        if(mView!=null){
            mView=null;

        }

    }
}
