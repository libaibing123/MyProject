package com.example.myjingdong2.ui.base;

public class BasePresenter<T extends BaseContent.BaseView> implements BaseContent.BasePresenter<T> {
    protected T mView;


    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    @Override
    public void dettachView() {
        if(mView!=null){
            mView=null;

        }

    }
}
