package com.example.myrikao0517.ui.shopcart.content;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.GetCartsBean;
import com.example.myrikao0517.bean.SellerBean;

import java.util.List;

public interface ShopCartContent {
    interface View extends BaseContent.BaseView {
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);
}
interface Presenter extends BaseContent.BasePresenter<View>{
    void getCarts(String uid, String token);

    void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

    void deleteCart(String uid, String pid, String token);


}

}
