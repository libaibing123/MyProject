package com.example.myjingdong2.ui.shopcart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myjingdong2.R;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.ui.base.BaseActivity;
import com.example.myjingdong2.ui.shopcart.adapter.ElvShopcartAdapter3;
import com.example.myjingdong2.ui.shopcart.content.ShopCartContent2;
import com.example.myjingdong2.ui.shopcart.presenter.ShopCartPresenter2;
import com.example.myjingdong2.utils.DialogUtil;

import java.util.List;

public class ShopCart2Activity extends BaseActivity<ShopCartPresenter2> implements ShopCartContent2.View {

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
    private ElvShopcartAdapter3 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        //请求购物车的接口
        mPresenter.getCarts(getUid(),getToken());
        progressDialog = DialogUtil.getProgressDialog(this);



    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart3;
    }

    @Override
    public void inject() {

    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        adapter = new ElvShopcartAdapter3(this,groupList,childList,progressDialog,mPresenter);

        mElv.setAdapter(adapter);
        //展开列表
        for(int i=0;i<groupList.size();i++){
            mElv.expandGroup(i);


        }



    }

    @Override
    public void updateSuccess(String msg) {

        if(adapter==null){
         //   adapter

        }

    }

    @Override
    public void delSuccess(String msg) {

    }
}
