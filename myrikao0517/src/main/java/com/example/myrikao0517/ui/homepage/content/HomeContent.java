package com.example.myrikao0517.ui.homepage.content;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.AdBean;
import com.example.myrikao0517.bean.CatagoryBean;

public interface HomeContent {
    interface View extends BaseContent.BaseView{
        void getAdSuccess(AdBean adBean);
        void getCatagorySuccess(CatagoryBean catagoryBean);


    }

    interface Presenter extends BaseContent.BasePresenter<View>{
        void getAd();

        void getCatagory();



    }
}
