package com.example.myrikao0517.ui.classfiy.content;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.ProductsBean;

import java.util.List;

public interface ListContract {
    interface View extends BaseContent.BaseView{
        void onSuccess(List<ProductsBean.DataBean> list);


    }
    interface Presenter extends BaseContent.BasePresenter<View>{
        void getProducts(String pscid);


    }

}
