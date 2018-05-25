package com.example.myjingdong2.ui.shopcart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myjingdong2.R;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.ui.base.BaseActivity;
import com.example.myjingdong2.ui.base.BaseContent;
import com.example.myjingdong2.ui.shopcart.adapter.ElvShopcartAdapter2;
import com.example.myjingdong2.ui.shopcart.content.ShopCartContent;
import com.example.myjingdong2.ui.shopcart.presenter.ShopCartPresenter;
import com.example.myjingdong2.utils.DialogUtil;

import java.util.List;

public class ShopCartActivity extends BaseActivity<ShopCartPresenter> implements ShopCartContent.View {

    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 合计：
     */
    private TextView mTvMoney;
    /**
     * 去结算：
     */
    private TextView mTvTotal;
    private ProgressDialog progressDialog;
    private ElvShopcartAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView();
    mPresenter.getCarts(getUid(),getToken());
        progressDialog = DialogUtil.getProgressDialog(this);


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart2;
    }

    @Override
    public void inject() {

    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        adapter2 = new ElvShopcartAdapter2(this,groupList,childList,progressDialog,mPresenter);
        for(int i=0;i<groupList.size();i++){
            mElv.expandGroup(i);


        }

    }

    @Override
    public void updateCartsSuccess(String msg) {

        if(adapter2!=null){

           // adapter2.updateSuccess();
        }

    }

    @Override
    public void deleteCartSuccess(String msg) {

    }
}
