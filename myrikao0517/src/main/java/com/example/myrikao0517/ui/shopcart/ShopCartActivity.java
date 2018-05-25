package com.example.myrikao0517.ui.shopcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myrikao0517.R;
import com.example.myrikao0517.base.BaseActivity;
import com.example.myrikao0517.bean.GetCartsBean;
import com.example.myrikao0517.bean.SellerBean;
import com.example.myrikao0517.bean.eventbus.MessageEvent;
import com.example.myrikao0517.ui.shopcart.adapter.ElvShopcartAdapter;
import com.example.myrikao0517.ui.shopcart.content.ShopCartContent;
import com.example.myrikao0517.ui.shopcart.presenter.ShopCartPresenter;
import com.example.myrikao0517.utils.DialogUtil;
import com.example.myrikao0517.utils.SharedPreferencesUtils;

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
    private ElvShopcartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        //初始化dialog
        progressDialog = DialogUtil.getProgressDialog(this);
        String token = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "token", "");
        String uid = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "uid", "");
        mPresenter.getCarts(uid,token);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void inject() {

    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);

        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    progressDialog.show();
                    adapter.changeAllState(mCbAll.isChecked());
                }
            }
        });
        mTvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopCartActivity.this, MakeSureOrderActivity.class);
                startActivity(intent);
                //把用户选中的商品传过去
                List<SellerBean> gList = adapter.getGroupList();
                List<List<GetCartsBean.DataBean.ListBean>> cList = adapter.getchildList();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setcList(cList);
                messageEvent.setgList(gList);
                EventBus.getDefault().postSticky(messageEvent);

            }
        });

    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));

        //创建适配器
        adapter = new ElvShopcartAdapter(this, groupList, childList, mPresenter,
                progressDialog);
        mElv.setAdapter(adapter);
        //获取数量和总价
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoney.setText("总计：" + strings[0] + "元");
        mTvTotal.setText("去结算("+strings[1]+"个)");
        //        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
        //关闭进度条
        progressDialog.dismiss();
    }

    @Override
    public void updateCartsSuccess(String msg) {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void deleteCartSuccess(String msg) {
        //调用适配器里的delSuccess()方法
        if (adapter!=null){
            adapter.delSuccess();
        }
    }

    /**
     * 判断所有商家是否全部选中
     *
     * @param groupList
     * @return
     */
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
}
