package com.example.myjingdong2.ui.shopcart.presenter;

import android.annotation.SuppressLint;

import com.example.myjingdong2.bean.BaseBean;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.net.DeleteCartApi;
import com.example.myjingdong2.net.GetCartApi;
import com.example.myjingdong2.net.UpdateCartApi;
import com.example.myjingdong2.ui.base.BasePresenter;
import com.example.myjingdong2.ui.shopcart.content.ShopCartContent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ShopCartPresenter extends BasePresenter<ShopCartContent.View> implements ShopCartContent.Presenter {
  private GetCartApi getCartApi;
  private UpdateCartApi updateCartApi;
  private DeleteCartApi deleteCartApi;
@Inject
    public ShopCartPresenter(GetCartApi getCartApi, UpdateCartApi updateCartApi, DeleteCartApi deleteCartApi) {
        this.getCartApi = getCartApi;
        this.updateCartApi = updateCartApi;
        this.deleteCartApi = deleteCartApi;
    }


    @Override
    public void getCarts(String uid, String token) {
        getCartApi.getCatagory(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetCartsBean>() {
                    @Override
                    public void accept(GetCartsBean getCartsBean) throws Exception {
                        List<SellerBean> groupList=new ArrayList<>();
                        List<List<GetCartsBean.DataBean.ListBean>> childList=new ArrayList<>();
                        List<GetCartsBean.DataBean> data = getCartsBean.getData();
                        if(data==null){
                            return;

                        }

                        for(int i=0;i<data.size();i++){
                            GetCartsBean.DataBean dataBean = data.get(i);
                            SellerBean sellerBean=new SellerBean();
                            sellerBean.setSellerName(dataBean.getSellerName());
                            sellerBean.setSellerid(dataBean.getSellerid());
                            sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                            groupList.add(sellerBean);
                            List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                            childList.add(list);

                        }
                        if(mView!=null){
                            mView.showCartList(groupList,childList);


                        }
                    }
                });

    }

    @Override
    public void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token) {
        updateCartApi.updateCarts(uid, sellerid, pid, num, selected, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
                }
            }
        });
    }

    @Override
    public void deleteCart(String uid, String pid, String token) {
        deleteCartApi.deleteCart(uid, pid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
                }
            }
        });
    }



    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean) {
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for(int i=0;i<list.size();i++){
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if(0==listBean.getSelected()){

                return false;


            }

        }
        return true;

    }


}
