package com.example.myrikao0517.base;

public class BasePresenter<T extends BaseContent.BaseView> implements BaseContent.BasePresenter<T>{
    protected T mView;



    @Override
    public void attachView(T view) {
        if(mView!=null){
            mView=view;


        }

    }

    @Override
    public void dettachView() {

        if(mView!=null){

            mView=null;

        }


    }
}
