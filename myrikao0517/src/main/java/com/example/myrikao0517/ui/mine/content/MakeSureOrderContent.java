package com.example.myrikao0517.ui.mine.content;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.AddrsBean;

import java.util.List;

public interface MakeSureOrderContent {
    interface View extends BaseContent.BaseView{

        void addrsSuccess(List<AddrsBean.DataBean> list);

        void createOrderSuccess(String msg);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);

    }

    interface Presenter extends BaseContent.BasePresenter<View>{


        void getAddrs(String uid, String token);

        void createOrder(String uid, String price, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);


    }
}
