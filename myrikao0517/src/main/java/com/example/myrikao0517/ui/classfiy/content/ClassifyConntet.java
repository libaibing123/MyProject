package com.example.myrikao0517.ui.classfiy.content;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.CatagoryBean;
import com.example.myrikao0517.bean.ProductCatagoryBean;

public interface ClassifyConntet {
    interface View extends BaseContent.BaseView{

        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);
        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContent.BasePresenter<View>{

        void getProductCatagory(String pid);
        void getCatagory();



    }
}
