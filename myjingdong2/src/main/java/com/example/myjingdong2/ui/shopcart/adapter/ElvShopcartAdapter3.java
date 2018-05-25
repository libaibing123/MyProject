package com.example.myjingdong2.ui.shopcart.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myjingdong2.R;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.ui.shopcart.presenter.ShopCartPresenter2;
import com.example.myjingdong2.ui.widget.AddSubView;
import com.example.myjingdong2.utils.SharedPreferencesUtils;

import java.util.List;

public class ElvShopcartAdapter3 extends BaseExpandableListAdapter {

    private Context context;
    private List<SellerBean> groupList;
    private List<List<GetCartsBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;
    private ProgressDialog progressDialog;
    private ShopCartPresenter2 shopCartPresenter2;
    private final String uid;
    private final String token;
    private static final int CLICK_PRODUCT = 0;
    private static final int CLICK_SELLER = 1;
    private static int state = 0;
    private int cIndex = 0;//商家下商品的下标
    private int gIndex = 0;//点击的商家下标
    private int selected;//点击商家的时候，该商家的checkbox选中状态

    public ElvShopcartAdapter3(Context context, List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList, ProgressDialog progressDialog, ShopCartPresenter2 shopCartPresenter2) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        this.progressDialog = progressDialog;
        this.shopCartPresenter2 = shopCartPresenter2;
        inflater=LayoutInflater.from(context);
        uid = (String) SharedPreferencesUtils.getParam(context, "uid", "");
        token = (String) SharedPreferencesUtils.getParam(context, "token", "");


    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if(convertView==null){
            groupViewHolder=new GroupViewHolder();
           convertView=inflater.inflate(R.layout.shopcart_seller_item,null);
           groupViewHolder.cbSeller=convertView.findViewById(R.id.cbSeller);
           groupViewHolder.tvSeller=convertView.findViewById(R.id.tvSeller);
           convertView.setTag(groupViewHolder);


        }else{

            groupViewHolder= (GroupViewHolder) convertView.getTag();


        }
        SellerBean sellerBean = groupList.get(groupPosition);

        groupViewHolder.cbSeller.setChecked(sellerBean.isSelected());
        groupViewHolder.tvSeller.setText(sellerBean.getSellerName());
        //给商家checkbox设置点击事件
        groupViewHolder.cbSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=CLICK_SELLER;
                gIndex=groupPosition;
                //显示进度条
                progressDialog.show();
                GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(cIndex);



            }
        });
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class GroupViewHolder {
        CheckBox cbSeller;
        TextView tvSeller;
    }

    class ChildViewHolder {
        CheckBox cbProduct;
        ImageView iv;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDel;
        AddSubView addSubView;
    }

}
