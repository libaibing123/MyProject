package com.example.myjingdong2.ui.base;

public interface BaseContent {
    //抽取所有Presenter共性，比如绑定，解绑
    interface BasePresenter<T extends BaseView>{
        void attachView(T view);
        void dettachView();



    }
    //抽取所有View的共性，比如显示进度条和关闭进度

    interface BaseView{
        void showLoading();
        void dissmissLoading();


    }
}
