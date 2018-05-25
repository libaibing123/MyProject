package com.example.myjingdong2.ui.shopcart.presenter;

import com.example.myjingdong2.bean.BaseBean;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.net.DeleteCartApi;
import com.example.myjingdong2.net.GetCartApi;
import com.example.myjingdong2.net.UpdateCartApi;
import com.example.myjingdong2.ui.base.BaseContent;
import com.example.myjingdong2.ui.base.BasePresenter;
import com.example.myjingdong2.ui.shopcart.content.ShopCartContent2;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ShopCartPresenter2 extends BasePresenter<ShopCartContent2.View> implements ShopCartContent2.Presenter {
    private GetCartApi getCartApi;
    private UpdateCartApi updateCartApi;
    private DeleteCartApi deleteCartApi;

    public ShopCartPresenter2(GetCartApi getCartApi, UpdateCartApi updateCartApi, DeleteCartApi deleteCartApi) {
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
                        //用于封装一级列表
                        List<SellerBean> groupList=new ArrayList<>();
                        //用于封装第二级列表
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
                            //true或false要根据该商家下的商品是否全选
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


    /**
     * 判断该商家下的商品是否全选
     * @param dataBean
     * @return
     */
    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean) {
//获取该商家下的所有商品
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for(int i=0;i<list.size();i++){
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if(0==listBean.getSelected()){
                //如果是0的话表示至少有一没有选中
                return  false;


            }

        }

        return true;

    }

    @Override
    public void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token) {
updateCartApi.updateCarts(uid,sellerid,pid,num,selected,token)
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
        if(mView!=null){

            mView.updateSuccess(s);


        }
    }
});
    }

    @Override
    public void delCarts(String uid, String pid, String token) {

        deleteCartApi.deleteCart(uid,pid,token)
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
                if(mView!=null){
                    mView.delSuccess(s);


                }
            }
        });

    }


}
